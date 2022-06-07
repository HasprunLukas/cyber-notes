package sk.cybersoft.cybernotes.cybernotesspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyberNotesSpringApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(CyberNotesSpringApplication.class);

        SpringApplication.run(CyberNotesSpringApplication.class, args);
        logger.info("To access h2-console: http://localhost:8080/h2-console");
    }


}
