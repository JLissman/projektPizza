package se.iths.pizzas.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.iths.pizzas.entities.Pizza;
import se.iths.pizzas.msgRabbitMQ.RabbitMQConfiguration;
import se.iths.pizzas.repositories.PizzaRepository;

@RestController
public class OrderController {

    private final RabbitTemplate rabbitTemplate;
    private final PizzaRepository pizzaRepo;

    public OrderController(RabbitTemplate rabbitTemplate, PizzaRepository pizzaRepo) {
        this.rabbitTemplate = rabbitTemplate;
        this.pizzaRepo = pizzaRepo;
    }

    @PostMapping("/orders")
    String orderPizza(@RequestBody String newOrder) {
        var orderId = (int)(Math.random() * 10000);

        Pizza pizza = pizzaRepo.getById(Long.valueOf(newOrder));
        String pizzaString = "Pizza with id: "+pizza.getId()+" - "+pizza.getName()+" "+" with ingredients "+pizza.getIngredients();

        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,
                "foo.bar.baz", pizzaString + " ordered with id: " + orderId);

        return "Thanks for your order: " + orderId;
    }
}