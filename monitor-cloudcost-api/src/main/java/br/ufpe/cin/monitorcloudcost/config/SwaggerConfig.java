package br.ufpe.cin.monitorcloudcost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String PACKAGE_BR_UFPE_CIN_API_RESOURCES = "br.ufpe.cin.monitorcloudcost.resource";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_BR_UFPE_CIN_API_RESOURCES)) //
                .paths(PathSelectors.any()) //
                .build()//
                .apiInfo(
                        new ApiInfoBuilder().title("API - Monitor Cloud Cost API")
                                .description("API responsavel por monitorar o billing em uma determinada cloud.")
                                .build())
                .enable(true);
    }

}
