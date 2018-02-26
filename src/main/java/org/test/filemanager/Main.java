package org.test.filemanager;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static org.springframework.boot.SpringApplication.run;
import static java.util.Collections.emptyList;

@SpringBootApplication
@EnableSwagger2
public class Main {

    public static void main(String[] args) {
        run(Main.class);
    }

    //<editor-fold desc="Beans">
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("org.test.filemanager.controller"))
                .paths(ant("/api/file/**"))
                .build()
                .apiInfo(this.info())
                .useDefaultResponseMessages(false);
    }

    @Bean
    @ConditionalOnProperty(name="application.storage.type", havingValue="s3")
    public AmazonS3 s3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }
    //</editor-fold>

    //<editor-fold desc="Dependency Injection">
    private @Value("${application.storage.amazon-s3.access-key}") String accessKey;
    private @Value("${application.storage.amazon-s3.secret-key}") String secretKey;
    private @Value("${application.storage.amazon-s3.region}") String region;
    //</editor-fold>

    //<editor-fold desc="Support methods">
    private ApiInfo info() {
        return new ApiInfo(
                "FILEMANAGER TEST",
                "Test example of file management",
                "1.0",
                null,
                new Contact("Erick Vega",null,null),
                "Apache 2.0",
                "https://github.com/evdelacruz/filemanager-test/blob/master/LICENSE",
                emptyList()
        );
    }
    //</editor-fold>
}
