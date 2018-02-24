package org.test.filemanager;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages= "org.test.filemanager.configuration")
public class Main {

    public static void main(String[] args) {
        run(Main.class);
    }
}
