package org.test.filemanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.run;
import static java.util.Collections.emptyList;

@SpringBootApplication
@EnableSwagger2
public class Main {

    public static void main(String[] args) {
        run(Main.class);
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.test.filemanager.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.info());
    }

    //<editor-fold desc="Support methods">
    private ApiInfo info() {
        return new ApiInfo(
                "FILEMANAGER TEST",
                "Test example of file management",
                "1.0",
                null,
                new Contact("Erick Vega","https://github.com/evdelacruz",null),
                "Apache 2.0",
                "https://github.com/evdelacruz/filemanager-test/blob/master/LICENSE",
                emptyList()
        );
    }
    //</editor-fold>
}
