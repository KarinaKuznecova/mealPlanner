package art.blisteria.mealplanner3.controller;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.domain.MealCategory;
import art.blisteria.mealplanner3.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/meals")
public class MealController {

    private MealService service;
    private static Logger logger = LoggerFactory.getLogger(MealController.class);

    @Autowired
    public MealController(MealService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal, UriComponentsBuilder builder) {
        logger.debug("trying to create Meal: " + meal);
        Meal response = service.createMeal(meal);
        logger.info("created Meal: " + meal);
        return ResponseEntity.created(builder.path("api/v1/meals/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Meal> getMealById(@RequestParam("id") Long id) {
        logger.debug("Getting Meal with id: " + id);
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Meal> getMealByName(@RequestParam("name") String name) {
        logger.debug("Getting Meal by name: " + name);
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping()
    public ResponseEntity<List<Meal>> getAllMeals() {
        logger.debug("Getting all meals");
        return ResponseEntity.ok(service.getAllMeals());
    }

    @GetMapping(params = {"name", "description", "category"})
    public Meal testCreate(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("category") String category) {
        Map<Long, Double> products = new HashMap<>();
        products.put(7L, 12.0);
        products.put(8L, 22.5);
        Meal meal = new Meal(name, description, MealCategory.valueOf(category), products);
        service.createMeal(meal);
        return meal;
    }

    @GetMapping(params = {"category"})
    public ResponseEntity<List<Meal>> getMealsByCategory(@RequestParam("category") String category) {
        logger.debug("Getting Meals by category: " + category);
        return ResponseEntity.ok(service.getMealsByCategory(MealCategory.valueOf(category)));
    }

    @PutMapping
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal, UriComponentsBuilder builder) {
        logger.info("Updating Meal: " + meal);
        Meal response = service.editMeal(meal);
        logger.info("Meal updated: " + response);
        return ResponseEntity.created(builder.path("api/v1/meal/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteMeal(@RequestParam("id") Long id) {
        logger.info("Deleting meal with id: " + id);
        service.deleteMeal(id);
        return ResponseEntity.ok().build();
    }
}
