package art.blisteria.mealplanner3.controller;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.domain.MealCategory;
import art.blisteria.mealplanner3.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/meals")
public class MealController {

    private MealService service = MealService.getInstance();

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal, UriComponentsBuilder builder) {
        Meal response = service.createMeal(meal);
        return ResponseEntity.created(builder.path("api/v1/meals/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Meal> getMealById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Meal> getMealByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping(params = {"name", "description", "category"})
    public Meal testCreate(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("category") MealCategory category) {
        Meal meal = new Meal(name, description, category);
        service.createMeal(meal);
        return meal;
    }

    @PutMapping
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal, UriComponentsBuilder builder) {
        Meal response = service.editMeal(meal);
        return ResponseEntity.created(builder.path("api/v1/meal/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteMeal(@RequestParam("id") Long id) {
        service.deleteMeal(id);
        return ResponseEntity.ok().build();
    }
}
