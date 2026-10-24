package com.txttosql.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.server.servlet.context.ServletWebServerApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProjectApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(ProjectApplication.class, args);


		int port = ((ServletWebServerApplicationContext) context).getWebServer().getPort();
		//printa o endereço da aplicação
		System.out.println(port);
	}

}
