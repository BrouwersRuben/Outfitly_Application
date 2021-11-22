package be.kdg.outfitly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OutfitlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutfitlyApplication.class, args);
    }

}
