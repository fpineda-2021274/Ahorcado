package com.FranklinPineda.Ahorcado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AhorcadoProyectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AhorcadoProyectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("api funcionando");
    }
}

