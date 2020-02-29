package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.domain.MealCategory;
import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.repository.MealRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MealService {

    private MealRepository mealRepository;
    private static Logger logger = LogManager.getLogger(MealService.class);

    @Autowired
    private ProductService productService;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(Meal meal) {
        mealRepository.save(meal);
        logger.info("Meal saved: " + meal);
        return meal;
    }

    public List<Meal> getAllMeals() {
        List<Meal> all = mealRepository.findAll();
        for (Meal meal : all) {
            fillRealProducts(meal);
        }
        return all;
    }

    public Meal getById(Long id) {
        Meal meal = mealRepository.getOne(id);
        fillRealProducts(meal);
        return meal;
    }

    public Meal getByName(String name) {
        Meal meal = mealRepository.getMealByName(name);
        fillRealProducts(meal);
        return meal;
    }

    public List<Meal> getMealsByCategory(MealCategory category) {
        List<Meal> allByMealCategory = mealRepository.findAllByMealCategory(category);
        for (Meal meal : allByMealCategory) {
            fillRealProducts(meal);
        }
        return allByMealCategory;
    }

    public List<Meal> getAvailableMeals() {
        logger.info("getting available meals");
        List<Meal> allMeals = mealRepository.findAll();
        List<Meal> availableMeals = new ArrayList<>();
        for (Meal meal : allMeals) {
            if (hasAllProducts(meal)) {
                availableMeals.add(meal);
            }
        }
        return availableMeals;
    }

    private boolean hasAllProducts(Meal meal) {
        logger.debug("Checking meal: " + meal.getName() + ", id: " + meal.getId());
        fillRealProducts(meal);
        for (Product product : meal.getRealProducts().keySet()) {
            if (product != null && product.getAmount() == 0) {
                logger.debug("Meal " + meal.getId() + " doesn't have " + product.getName());
                return false;
            }
        }
        if (meal.getRealProducts().isEmpty()) {
            logger.error("Could not find real products for meal " + meal.getId());
            return false;
        }
        logger.debug("Meal " + meal.getId() + " has all products");
        return true;
    }

    public Meal editMeal(Meal meal) {
        fillRealProducts(meal);
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    private void fillRealProducts(Meal meal) {
        logger.debug("filling real products for meal: " + meal);
        for (Map.Entry<Long, Double> entry : meal.getProducts().entrySet()) {
            Long id = entry.getKey();
            Double amount = entry.getValue();
            logger.debug("Going through map, product id: " + id + " product amount: " + amount);
            Product product = productService.getById(id);
            logger.debug("Found product : " + product);
            if (product != null) {
                meal.getRealProducts().put(product, amount);
            } else {
                logger.error("Could not find real product with id: " + id);
            }
        }
    }
}
