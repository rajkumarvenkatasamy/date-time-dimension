package com.github.rajkumarvenkatasamy.dimension.datetime;

import com.github.rajkumarvenkatasamy.dimension.datetime.service.DateTimeDimensionInitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DimensionsApplication implements CommandLineRunner {

    @Autowired
    DateTimeDimensionInitializationService dateTimeDimensionInitializationService;

    public static void main(String[] args) {
        SpringApplication.run(DimensionsApplication.class, args);
    }

    @Override
    public void run(String[] args){
        dateTimeDimensionInitializationService.populateDateTimeDimensionOnStartup();
    }

}
