package se.iths.pizzas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.iths.pizzas.entities.Pizza;
import se.iths.pizzas.repositories.PizzaRepository;

@SpringBootApplication
public class PizzasApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzasApplication.class, args);
    }

/*
    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository){
        return args -> {
            System.out.println(pizzaRepository.findById(0L));
            System.out.println(pizzaRepository.findById(1L));
            var val = pizzaRepository.findById(1L);
            if (val.isPresent()){
                System.out.println(val.get());
            }
            };
        }*/
    }

