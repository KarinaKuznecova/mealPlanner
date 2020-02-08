package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.domain.MealCategory;
import art.blisteria.mealplanner3.repository.MealRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private MealRepository mealRepository;
    private static Logger logger = LogManager.getLogger(MealService.class);

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(Meal meal) {
        mealRepository.save(meal);
        logger.info("Meal saved: " + meal);
        return meal;
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getById(Long id) {
        return mealRepository.getOne(id);
    }

    public Meal getByName(String name) {
        return mealRepository.getMealByName(name);
    }

    public List<Meal> getMealsByCategory(MealCategory category) {
        return mealRepository.findAllByMealCategory(category);
    }

    public Meal editMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

}
