package se.iths.pizzas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.iths.pizzas.entities.Pizza;
import se.iths.pizzas.repositories.PizzaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/pizzas/{id}")
    public Pizza getPizza(@PathVariable("id") long id){
        return pizzaRepository.findById(id).get();

    }

    @GetMapping("/pizzas/search={searchterm}")
    public List<Pizza> searchPizzas(@PathVariable("searchterm") String term){
        return pizzaRepository.findAll().stream()
                .filter(e -> !e.getName().toLowerCase().contains(term.toLowerCase()) ||
                        !String.valueOf(e.getId()).equalsIgnoreCase(term) ||
                        !e.getIngredients().toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
    }


    @PostMapping("/pizzas")
    Pizza newPizza(@RequestBody Pizza newPizza){
            return pizzaRepository.save(newPizza);
        }

    @DeleteMapping("/pizzas/{id}")
    public HttpStatus delPizza(@PathVariable("id") long id)
    {
        if(pizzaRepository.findById(id).isPresent()){
            pizzaRepository.deleteById(id);
            return HttpStatus.FOUND;
        }else{

            return HttpStatus.NOT_FOUND;
        }

    }

    @PutMapping("/pizzas")
    public Pizza putPizza(@RequestBody Pizza newPizza){
        return pizzaRepository.save(newPizza);


    }



    @PatchMapping("/pizzas")
    public Pizza patchPizza(@RequestBody Pizza newPizza){


        Pizza pizza = pizzaRepository.getById(newPizza.getId());
        if(!pizza.getName().equals(newPizza.getName())){
            pizza.setName(newPizza.getName());
        }
        if(!pizza.getIngredients().equals(newPizza.getIngredients())){
            pizza.setIngredients(newPizza.getIngredients());
        }
        if(pizza.getPrice() != newPizza.getPrice()){
            pizza.setPrice(newPizza.getPrice());
        }
        return pizzaRepository.save(pizza);
    }

}
