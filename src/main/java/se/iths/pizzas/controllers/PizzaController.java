package se.iths.pizzas.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.pizzas.entities.Pizza;
import se.iths.pizzas.repositories.PizzaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzas() {
        return pizzaRepository.findAll();
    }


    @PostMapping("/pizzas")
    public String postpizza(Long id, String name, int price, String ingredients){
        
        if(pizzaRepository.findById(id).isPresent()){
            Pizza pizza = pizzaRepository.findById(id).get();
            pizza.setName(name);
            pizza.setPrice(price);
            pizza.setIngredients(ingredients);
            pizzaRepository.save(pizza);
            return "pizza with id:"+id+" edited";
            
        }else if(pizzaRepository.findById(id).isEmpty()){
            Pizza newPizza = new Pizza(id,name,price,ingredients);
            pizzaRepository.save(newPizza);
            return "pizza added!";
        }
        
        
        
    }
    @DeleteMapping("/pizzas")
    public String delPizza(Long id){
        if(pizzaRepository.findById(id).isPresent()){
            pizzaRepository.deleteById(id);
            return "deleted pizza with id: "+id;
        }else{

            return "no pizza with that id found";
        }

    }

}
