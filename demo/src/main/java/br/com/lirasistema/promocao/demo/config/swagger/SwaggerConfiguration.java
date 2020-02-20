
package br.com.lirasistema.promocao.demo.config.swagger;

import br.com.lirasistema.promocao.demo.controller.UsuarioController;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket promocaoApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.lirasistema.promocao.demo"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(UsuarioApi.class)
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                    .name("Authorization")
                                    .description("Header para Token JWT")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build()));
    }
}
