"use strict";

// Aplicação sem dependências. Todos os caminhos são relativos ao mesmo servidor Spring.
const ARCHITECTURES = {
  context: { label: "Com contexto", endpoint: "/v1/ai/perguntarContext" },
  direct: { label: "Sem contexto", endpoint: "/v1/ai/perguntarNoContext" }
};

// A API atual consulta uma pergunta por ID, mas não oferece um endpoint de listagem.
// Este catálogo acompanha os IDs e textos de resources/perguntas-padrao.yaml.
const QUESTIONS = [
  { id: "jogadores-cruzeiro", text: "Quais são os jogadores do time Cruzeiro?" },
  { id: "maiores-artilheiros", text: "Quais são os 3 jogadores com mais gols?" },
  { id: "clubes-jogadores-argentinos", text: "Qual clube tem mais jogadores argentinos?" },
  { id: "vitorias-cruzeiro", text: "Quantas partidas o time Cruzeiro ganhou no Campeonato Brasileiro Série A?" },
  { id: "time-mais-vitorias", text: "Qual o time com mais vitórias?" }
];

const TABLES = [
  { id: "times", label: "Times", endpoint: "/v1/time/allTimes", icon: "flag" },
  { id: "jogadores", label: "Jogadores", endpoint: "/v1/jogador/allJogador", icon: "users" },
  { id: "campeonatos", label: "Campeonatos", endpoint: "/v1/campeonato/allCampeonatos", icon: "trophy" },
  { id: "partidas", label: "Partidas", endpoint: "/v1/partidas/allPartidas", icon: "calendar" },
  { id: "gols", label: "Gols", endpoint: "/v1/gols/allGols", icon: "target" }
];

const ICONS = {
  database: '<ellipse cx="12" cy="5" rx="8" ry="3"/><path d="M4 5v14c0 1.7 3.6 3 8 3s8-1.3 8-3V5M4 12c0 1.7 3.6 3 8 3s8-1.3 8-3"/>',
  users: '<circle cx="9" cy="7" r="4"/><path d="M2 21v-2a7 7 0 0 1 14 0v2m1-18a4 4 0 0 1 0 8m5 10v-2a7 7 0 0 0-4-6"/>',
  trophy: '<path d="M8 3h8v7a4 4 0 0 1-8 0V3Zm0 2H4v3a4 4 0 0 0 4 4m8-7h4v3a4 4 0 0 1-4 4m-4 2v7m-4 0h8"/>',
  flag: '<path d="M4 22V3m0 0c5-4 10 4 16 0v12c-6 4-11-4-16 0"/>',
  target: '<circle cx="12" cy="12" r="9"/><circle cx="12" cy="12" r="5"/><circle cx="12" cy="12" r="1"/>',
  calendar: '<rect x="3" y="5" width="18" height="16" rx="2"/><path d="M16 3v4M8 3v4M3 11h18m-13 5h2m4 0h2"/>',
  "chevron-left": '<path d="m15 18-6-6 6-6"/>',
  "chevron-right": '<path d="m9 18 6-6-6-6"/>',
  search: '<circle cx="10.5" cy="10.5" r="7.5"/><path d="m16 16 5 5"/>',
  refresh: '<path d="M20 7a9 9 0 1 0 1 8M20 3v5h-5"/>',
  play: '<path d="m8 4 13 8-13 8V4Z"/>',
  info: '<circle cx="12" cy="12" r="9"/><path d="M12 11v6m0-10v.1"/>',
  copy: '<rect x="8" y="8" width="12" height="13" rx="2"/><path d="M16 8V3H3v13h5"/>',
  check: '<path d="m5 12 4 4L20 5"/>'
};

const PAGE = document.body.dataset.page;
const STORAGE = { evaluations: "texttosql.evaluations.v1", chat: "texttosql.chat.v1" };
const LABELS = { id: "ID", nome: "Nome", cidade: "Cidade", pais: "País", fundacao: "Fundação", dataFundacao: "Fundação", dataNascimento: "Nascimento", posicao: "Posição", nacionalidade: "Nacionalidade", nomeTime: "Time", temporada: "Temporada", data: "Data", campeonato: "Campeonato", timeCasa: "Mandante", timeVisitante: "Visitante", golCasa: "Gols da casa", golVisitante: "Gols do visitante", jogador: "Jogador", partida: "Partida", minuto: "Minuto", gols: "Gols", quantidade_gols: "Quantidade de gols", quantidade_vitorias: "Vitórias", quantidade_jogadores: "Jogadores" };
const allowedRequests = new Set([
  ...TABLES.map(table => `GET ${table.endpoint}`),
  ...QUESTIONS.map(question => `GET /questoes-padrao/${question.id}`),
  ...Object.values(ARCHITECTURES).map(architecture => `POST ${architecture.endpoint}`)
]);

const $ = selector => document.querySelector(selector);
const element = (tag, className = "", text) => {
  const node = document.createElement(tag);
  if (className) node.className = className;
  if (text !== undefined) node.textContent = String(text);
  return node;
};

function icon(name) {
  const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg.setAttribute("viewBox", "0 0 24 24");
  svg.setAttribute("class", "icon");
  svg.setAttribute("aria-hidden", "true");
  // Apenas o catálogo de ícones acima é inserido como marcação. Dados da API usam textContent.
  svg.innerHTML = ICONS[name] || ICONS.info;
  return svg;
}

function decorateIcons(scope = document) {
  scope.querySelectorAll("[data-icon]").forEach(node => {
    if (!node.querySelector("svg")) node.prepend(icon(node.dataset.icon));
  });
}

function readSession(key, fallback) {
  try { return JSON.parse(sessionStorage.getItem(key)) ?? fallback; }
  catch { return fallback; }
}

function saveSession(key, value) {
  try { sessionStorage.setItem(key, JSON.stringify(value)); }
  catch { toast("Não foi possível guardar os resultados nesta aba. Eles continuam disponíveis nesta página."); }
}

let toastTimer;
function toast(message) {
  const node = $("#toast");
  node.textContent = message;
  node.hidden = false;
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => { node.hidden = true; }, 4500);
}

async function apiRequest(path, options = {}) {
  const method = options.method || "GET";
  if (!allowedRequests.has(`${method} ${path}`)) throw new Error("Operação não disponível nesta interface.");
  const controller = new AbortController();
  const timer = setTimeout(() => controller.abort(), 180000);
  try {
    const response = await fetch(path, {
      method,
      headers: options.body ? { "Content-Type": "application/json", Accept: "application/json" } : { Accept: "application/json" },
      body: options.body ? JSON.stringify(options.body) : undefined,
      signal: controller.signal,
      cache: "no-store"
    });
    const text = await response.text();
    let data;
    try { data = text ? JSON.parse(text) : null; }
    catch {
      throw new Error(response.ok
        ? "A API retornou uma resposta JSON inválida. Consulte o log do servidor."
        : `A requisição falhou (HTTP ${response.status}). Consulte o servidor e tente novamente.`);
    }
    if (!response.ok) {
      const detail = data?.message || data?.detail || data?.error;
      throw new Error(typeof detail === "string" ? detail.slice(0, 800) : `Não foi possível concluir a requisição (HTTP ${response.status}).`);
    }
    if (data === null) throw new Error("A API retornou uma resposta vazia.");
    return data;
  } catch (error) {
    if (error.name === "AbortError") throw new Error("A requisição demorou mais de 3 minutos. Tente novamente ou confira o servidor.");
    if (error instanceof TypeError) throw new Error("Não foi possível acessar o servidor. Confira se a aplicação está em execução e tente novamente.");
    throw error;
  } finally { clearTimeout(timer); }
}

function setupShell() {
  const navigation = [
    { page: "chat", href: "index.html", title: "Chat" },
    { page: "data", href: "dados.html", title: "Dados do banco" },
    { page: "questions", href: "perguntas.html", title: "Perguntas padrão" }
  ];
  const nav = element("nav", "navigation");
  nav.setAttribute("aria-label", "Páginas");
  navigation.forEach(item => {
    const link = element("a", `nav-link${PAGE === item.page ? " active" : ""}`, item.title);
    link.href = item.href;
    if (PAGE === item.page) link.setAttribute("aria-current", "page");
    nav.append(link);
  });
  $("#page-navigation").append(nav);
  $("[data-close-dialog]").addEventListener("click", () => $("#record-dialog").close());
  decorateIcons();
}

function loading(text = "Carregando…") {
  const node = element("div", "loading");
  node.append(element("span", "spinner"), element("span", "", text));
  node.setAttribute("role", "status");
  return node;
}

function errorBox(message) {
  const node = element("div", "error-message");
  node.setAttribute("role", "alert");
  node.append(element("strong", "", "Não foi possível concluir"), element("p", "", message));
  return node;
}

function emptyState(title, description, iconName = "database") {
  const node = element("div", "empty-state");
  node.append(icon(iconName), element("h3", "", title), element("p", "", description));
  return node;
}

function statusBadge(status = "Não executada") {
  const normalized = String(status).trim().toLocaleLowerCase("pt-BR");
  const style = normalized === "correto" ? "correct" : /inválido|falha|erro/.test(normalized) ? "error" : "neutral";
  return element("span", `badge ${style}`, status);
}

function showObject(value) {
  $("#record-json").textContent = JSON.stringify(value, null, 2);
  $("#record-dialog").showModal();
}

function scalarText(value) {
  if (value === null || value === undefined) return "—";
  if (typeof value === "boolean") return value ? "Sim" : "Não";
  if (typeof value === "object") return value.nome || value.id || (Array.isArray(value) ? `${value.length} itens` : "Ver detalhes");
  if (/^\d{4}-\d{2}-\d{2}$/.test(String(value))) return String(value).split("-").reverse().join("/");
  return String(value);
}

function cellContent(value) {
  if (value === null || value === undefined) return element("span", "null-value", "—");
  if (typeof value === "object") {
    const button = element("button", "object-button", scalarText(value));
    button.type = "button";
    button.append(element("span", "", "↗"));
    button.setAttribute("aria-label", `Ver detalhes: ${scalarText(value)}`);
    button.addEventListener("click", () => showObject(value));
    return button;
  }
  return document.createTextNode(scalarText(value));
}

function recordsTable(records, options = {}) {
  const wrap = element("div", `table-wrap ${options.className || "result-table"}`);
  if (!Array.isArray(records) || records.length === 0) {
    wrap.append(emptyState(options.emptyTitle || "Nenhum registro encontrado", options.emptyDescription || "A consulta foi concluída e retornou uma lista vazia."));
    return wrap;
  }
  const rows = records.filter(row => row && typeof row === "object" && !Array.isArray(row));
  if (rows.length === 0) { wrap.append(emptyState("Sem registros tabulares", "A resposta não contém objetos que possam ser exibidos em uma tabela.")); return wrap; }
  const columns = [...new Set(rows.flatMap(row => Object.keys(row)))];
  const table = element("table");
  table.append(element("caption", "sr-only", options.caption || "Registros retornados pelo servidor"));
  const head = element("thead");
  const headings = element("tr");
  columns.forEach(column => {
    const th = element("th");
    th.scope = "col";
    if (options.onSort) {
      const button = element("button", "sort-button", LABELS[column] || column);
      button.type = "button";
      button.append(element("span", "sort-marker", options.sortKey === column ? (options.sortDirection === 1 ? "↑" : "↓") : "↕"));
      button.addEventListener("click", () => options.onSort(column));
      if (options.sortKey === column) th.setAttribute("aria-sort", options.sortDirection === 1 ? "ascending" : "descending");
      th.append(button);
    } else th.textContent = LABELS[column] || column;
    headings.append(th);
  });
  head.append(headings);
  const body = element("tbody");
  rows.forEach(row => {
    const tr = element("tr");
    columns.forEach(column => { const td = element("td"); td.append(cellContent(row[column])); tr.append(td); });
    body.append(tr);
  });
  table.append(head, body);
  wrap.append(table);
  return wrap;
}

function sqlBlock(sql, title = "SQL gerado") {
  const block = element("div", "sql-block");
  const header = element("div", "sql-heading");
  header.append(element("span", "", title));
  if (sql) {
    const copy = element("button", "copy-button", "Copiar");
    copy.type = "button";
    copy.prepend(icon("copy"));
    copy.addEventListener("click", async () => {
      try { await navigator.clipboard.writeText(sql); toast("SQL copiado."); }
      catch { toast("Não foi possível copiar. Selecione o SQL e copie manualmente."); }
    });
    header.append(copy);
  }
  const pre = element("pre");
  pre.append(element("code", "", sql || "O servidor não retornou uma consulta SQL."));
  block.append(header, pre);
  return block;
}

function responseCard(mode, response = {}) {
  const card = element("article", "response-card");
  const header = element("div", "response-heading");
  header.append(element("h3", "", ARCHITECTURES[mode].label));
  if (response.pending) {
    header.append(statusBadge("Consultando…"));
    card.append(header, loading("Gerando e executando a consulta…"));
    return card;
  }
  if (response.status) header.append(statusBadge(response.status));
  card.append(header);
  if (response.error) { card.append(errorBox(response.error)); return card; }
  card.append(sqlBlock(response.sql));
  const rows = Array.isArray(response.records) ? response.records : [];
  card.append(element("div", "result-caption", `${rows.length} ${rows.length === 1 ? "registro retornado" : "registros retornados"}`));
  const invalidSql = String(response.status || "").toLocaleLowerCase("pt-BR").includes("inválido");
  card.append(recordsTable(rows, invalidSql ? {
    emptyTitle: "SQL inválido",
    emptyDescription: "O backend rejeitou esta consulta. Confira o SQL retornado acima."
  } : {}));
  return card;
}

/* Chat: cada pergunta é enviada à arquitetura selecionada. */
function setupChat() {
  const stored = readSession(STORAGE.chat, []);
  let conversation = Array.isArray(stored) ? stored.filter(turn => turn && typeof turn.question === "string" && Array.isArray(turn.results)) : [];
  let pending = false;
  const input = $("#chat-input");
  const render = () => {
    const messages = $("#chat-messages");
    messages.replaceChildren();
    if (conversation.length === 0) {
      const welcome = element("p", "chat-empty", "Escreva uma pergunta sobre os dados de futebol.");
      messages.append(welcome);
      return;
    }
    conversation.forEach(turn => {
      const user = element("div", "message user-message");
      user.append(element("div", "user-bubble", turn.question));
      const assistant = element("div", "message assistant-message");
      const grid = element("div", `response-grid${turn.results.length > 1 ? " dual" : ""}`);
      turn.results.forEach(result => { if (ARCHITECTURES[result.mode]) grid.append(responseCard(result.mode, result)); });
      assistant.append(grid);
      messages.append(user, assistant);
    });
  };
  const send = async event => {
    event.preventDefault();
    const question = input.value.trim();
    if (pending || !question) return;
    const mode = $("#chat-model").value;
    const turn = { question, results: [{ mode, pending: true }] };
    conversation.push(turn);
    pending = true;
    $("#send-chat").disabled = true;
    $("#clear-chat").disabled = true;
    $("#chat-model").disabled = true;
    input.value = "";
    render();
    $("#chat-messages").lastElementChild?.scrollIntoView({ block: "nearest" });
    try {
      // A validação atual do backend espera a pergunta em uma única linha.
      const data = await apiRequest(ARCHITECTURES[mode].endpoint, { method: "POST", body: { pergunta: question.replace(/\s+/g, " ") } });
      if (!data || typeof data !== "object" || !("sql" in data)) throw new Error("A resposta recebida não possui o SQL esperado.");
      turn.results[0] = { mode, sql: data.sql, records: data.registros_encontrados };
    } catch (error) {
      turn.results[0] = { mode, error: error.message };
    } finally {
      render();
      pending = false;
      $("#send-chat").disabled = false;
      $("#clear-chat").disabled = false;
      $("#chat-model").disabled = false;
      conversation = conversation.slice(-20);
      saveSession(STORAGE.chat, conversation);
      input.focus();
    }
  };
  $("#chat-form").addEventListener("submit", send);
  input.addEventListener("keydown", event => {
    if (event.key === "Enter" && !event.shiftKey && !event.isComposing) { event.preventDefault(); if (!pending) $("#chat-form").requestSubmit(); }
  });
  $("#clear-chat").addEventListener("click", () => { if (pending) return; conversation = []; saveSession(STORAGE.chat, []); render(); input.focus(); });
  render();
}

/* Dados: somente os cinco endpoints GET de listagem são utilizados. */
function setupData() {
  let selected = TABLES[0];
  let page = 1;
  let sortKey = null;
  let sortDirection = 1;
  const pageSize = 25;
  const cache = new Map();
  const errors = new Map();
  const inFlight = new Set();
  const tabs = $("#table-tabs");

  function renderTabs() {
    tabs.replaceChildren();
    TABLES.forEach(table => {
      const button = element("button", `table-tab${selected.id === table.id ? " active" : ""}`);
      button.type = "button";
      button.setAttribute("aria-pressed", String(selected.id === table.id));
      button.append(icon(table.icon), element("span", "", table.label), element("span", "tab-count", cache.has(table.id) ? cache.get(table.id).length : "—"));
      button.addEventListener("click", () => {
        if (selected.id === table.id) return;
        selected = table; page = 1; sortKey = null; sortDirection = 1; $("#table-search").value = "";
        renderTabs(); render(); load();
      });
      tabs.append(button);
    });
  }

  function render() {
    $("#table-title").textContent = selected.label;
    $("#refresh-data").disabled = inFlight.has(selected.id);
    const content = $("#data-content");
    content.replaceChildren();
    $("#previous-page").disabled = true;
    $("#next-page").disabled = true;
    $("#pagination-info").textContent = "";
    if (inFlight.has(selected.id)) {
      content.append(loading(`Carregando ${selected.label.toLocaleLowerCase("pt-BR")}…`));
      $("#table-total").textContent = "Consultando a base";
      return;
    }
    if (errors.has(selected.id)) {
      content.append(errorBox(errors.get(selected.id)));
      $("#table-total").textContent = "Falha ao carregar";
      return;
    }
    if (!cache.has(selected.id)) return;
    const query = $("#table-search").value.trim().toLocaleLowerCase("pt-BR");
    const all = cache.get(selected.id);
    const rows = all.filter(row => !query || JSON.stringify(row).toLocaleLowerCase("pt-BR").includes(query));
    const sortText = value => /^\d{4}-\d{2}-\d{2}$/.test(String(value)) ? String(value) : scalarText(value);
    if (sortKey) rows.sort((a, b) => sortText(a[sortKey]).localeCompare(sortText(b[sortKey]), "pt-BR", { numeric: true, sensitivity: "base" }) * sortDirection);
    const pages = Math.max(1, Math.ceil(rows.length / pageSize));
    page = Math.min(page, pages);
    const start = (page - 1) * pageSize;
    content.append(recordsTable(rows.slice(start, start + pageSize), {
      caption: `Registros da tabela ${selected.label}`,
      className: "data-table",
      emptyTitle: query ? "Nenhum resultado para esta busca" : "Esta tabela está vazia",
      emptyDescription: query ? "Tente outro nome, valor ou termo de busca." : "A API não retornou registros para esta tabela.",
      sortKey, sortDirection,
      onSort: column => { sortDirection = sortKey === column ? -sortDirection : 1; sortKey = column; page = 1; render(); }
    }));
    $("#table-total").textContent = query ? `${rows.length} de ${all.length} ${all.length === 1 ? "registro" : "registros"}` : `${all.length} ${all.length === 1 ? "registro" : "registros"}`;
    $("#pagination-info").textContent = rows.length ? `${start + 1}–${Math.min(start + pageSize, rows.length)} de ${rows.length} · Página ${page} de ${pages}` : "0 registros";
    $("#previous-page").disabled = page <= 1;
    $("#next-page").disabled = page >= pages;
  }

  async function load(force = false) {
    const table = selected;
    if (inFlight.has(table.id) || (!force && cache.has(table.id))) return;
    inFlight.add(table.id);
    errors.delete(table.id);
    render();
    try {
      const rows = await apiRequest(table.endpoint);
      if (!Array.isArray(rows)) throw new Error("A API não retornou uma lista de registros para esta tabela.");
      cache.set(table.id, rows);
    } catch (error) { errors.set(table.id, error.message); }
    finally { inFlight.delete(table.id); renderTabs(); render(); }
  }
  $("#table-search").addEventListener("input", () => { page = 1; render(); });
  $("#refresh-data").addEventListener("click", () => load(true));
  $("#previous-page").addEventListener("click", () => { page--; render(); });
  $("#next-page").addEventListener("click", () => { page++; render(); });
  renderTabs(); load();
}

/* Os resultados das perguntas padrão são mantidos nesta aba. */
const storedEvaluations = readSession(STORAGE.evaluations, {});
let evaluations = storedEvaluations && typeof storedEvaluations === "object" && !Array.isArray(storedEvaluations) ? storedEvaluations : {};
const runningEvaluations = new Set();
let batchActive = false;
let stopBatch = false;
let selectedQuestion = QUESTIONS.find(question => question.id === new URLSearchParams(location.search).get("id")) || QUESTIONS[0];

function updateEvaluationUI() {
  if (PAGE === "questions") { renderQuestionList(); renderQuestionDetail(); }
  document.querySelectorAll("[data-run-all]").forEach(button => { button.disabled = batchActive || runningEvaluations.size > 0; });
}

async function runEvaluation(question) {
  if (runningEvaluations.has(question.id)) return;
  runningEvaluations.add(question.id);
  updateEvaluationUI();
  try {
    const data = await apiRequest(`/questoes-padrao/${question.id}`);
    if (!data || typeof data !== "object" || !("statusNoContext" in data) || !("statusContext" in data)) throw new Error("A resposta recebida não contém os resultados das duas arquiteturas.");
    evaluations[question.id] = { data, checkedAt: new Date().toISOString() };
  } catch (error) { evaluations[question.id] = { error: error.message, checkedAt: new Date().toISOString() }; }
  finally {
    runningEvaluations.delete(question.id);
    saveSession(STORAGE.evaluations, evaluations);
    updateEvaluationUI();
  }
}

async function runAllEvaluations() {
  if (batchActive || runningEvaluations.size > 0) return;
  batchActive = true;
  stopBatch = false;
  const status = $("#batch-status");
  status.hidden = false;
  const text = element("span");
  const stop = element("button", "text-button", "Interromper fila");
  stop.type = "button";
  stop.addEventListener("click", () => { stopBatch = true; stop.disabled = true; text.textContent = "Finalizando a pergunta em andamento. As próximas não serão executadas."; });
  status.replaceChildren(element("span", "spinner"), text, stop);
  updateEvaluationUI();
  let completed = 0;
  try {
    // Sequencial para não disparar simultaneamente as dez chamadas ao LLM.
    for (const question of QUESTIONS) {
      if (stopBatch) break;
      text.textContent = `Avaliando ${completed + 1} de ${QUESTIONS.length}: ${question.text}`;
      await runEvaluation(question);
      completed++;
    }
  } finally {
    batchActive = false;
    status.replaceChildren(icon("check"), element("span", "", stopBatch ? `Fila interrompida. ${completed} perguntas processadas nesta execução.` : "Avaliação finalizada. Confira os resultados de cada pergunta."));
    updateEvaluationUI();
  }
}

function renderQuestionList() {
  const list = $("#question-list");
  list.replaceChildren();
  QUESTIONS.forEach((question, index) => {
    const button = element("button", `question-button${question.id === selectedQuestion.id ? " active" : ""}`);
    button.type = "button";
    button.setAttribute("aria-pressed", String(question.id === selectedQuestion.id));
    const content = element("span");
    content.append(element("span", "question-text", question.text));
    if (runningEvaluations.has(question.id)) content.append(statusBadge("Executando…"));
    else if (evaluations[question.id]) content.append(statusBadge(evaluations[question.id].error ? "Falha na avaliação" : "Concluída"));
    button.append(element("span", "question-number", String(index + 1).padStart(2, "0")), content);
    button.addEventListener("click", () => {
      selectedQuestion = question;
      history.replaceState(null, "", `?id=${encodeURIComponent(question.id)}`);
      updateEvaluationUI();
    });
    list.append(button);
  });
}

function renderQuestionDetail() {
  const detail = $("#question-detail");
  const question = selectedQuestion;
  const entry = evaluations[question.id];
  const busy = runningEvaluations.has(question.id);
  detail.replaceChildren();
  const intro = element("div", "panel question-intro");
  intro.append(element("h2", "", question.text));
  const actions = element("div", "question-actions");
  const run = element("button", "button primary", entry ? "Executar novamente" : "Executar pergunta");
  run.type = "button";
  run.prepend(icon("play"));
  run.disabled = batchActive || runningEvaluations.size > 0;
  run.addEventListener("click", () => runEvaluation(question));
  actions.append(run);
  intro.append(actions);
  detail.append(intro);
  if (busy) { detail.append(loading("Avaliando as duas arquiteturas…")); return; }
  if (!entry) { detail.append(element("p", "muted", "Execute a pergunta para ver os resultados e o gabarito.")); return; }
  if (entry.error) { detail.append(errorBox(entry.error)); return; }
  const data = entry.data;
  const grid = element("div", "response-grid dual");
  grid.append(
    responseCard("direct", { sql: data.sqlNoContext, records: data.registrosNoContext, status: data.statusNoContext }),
    responseCard("context", { sql: data.sqlContext, records: data.registrosContext, status: data.statusContext })
  );
  detail.append(grid);
  const reference = element("details", "panel reference-details");
  reference.open = true;
  reference.append(element("summary", "", "Gabarito"), sqlBlock(data.sqlReferencia, "SQL de referência"));
  let gabarito = data.gabarito;
  if (typeof gabarito === "string") { try { gabarito = JSON.parse(gabarito); } catch { gabarito = null; } }
  const expected = Array.isArray(gabarito) ? gabarito : gabarito?.registros;
  reference.append(element("div", "reference-caption", "Registros esperados"));
  reference.append(Array.isArray(expected) ? recordsTable(expected) : emptyState("Gabarito não informado", "O servidor não retornou um gabarito em formato de registros."));
  detail.append(reference);
}


setupShell();
if (PAGE === "chat") setupChat();
if (PAGE === "data") setupData();
if (PAGE === "questions") {
  document.querySelectorAll("[data-run-all]").forEach(button => button.addEventListener("click", runAllEvaluations));
  updateEvaluationUI();
}
