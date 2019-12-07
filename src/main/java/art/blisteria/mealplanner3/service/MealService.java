package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(Meal meal) {
        mealRepository.save(meal);
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

    public Meal editMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

}
