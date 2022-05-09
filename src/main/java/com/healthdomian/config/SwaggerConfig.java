package com.healthdomian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.healthdomian.constants.AppConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDoc() {
		return  new Docket(DocumentationType.SWAGGER_2)
				      .select()
				      .apis(RequestHandlerSelectors.basePackage(AppConstants.REST_CONTROLLERS_PACKAGE))
				      .paths(PathSelectors.any())
				      .build();
				      
	}

}
