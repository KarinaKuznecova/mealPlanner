package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private final static MealService service = new MealService();

    @Autowired
    private MealRepository mealRepository;

    private MealService() {
    }

    public static MealService getInstance() {
        return service;
    }

    public Meal createMeal(Meal meal) {
        mealRepository.save(meal);
        return meal;
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
