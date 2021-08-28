package se.iths.pizzas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.pizzas.entities.Pizza;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {


}
