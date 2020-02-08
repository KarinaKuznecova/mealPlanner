package art.blisteria.mealplanner3.repository;

import art.blisteria.mealplanner3.domain.Meal;
import art.blisteria.mealplanner3.domain.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal getMealByName(String name);

    List<Meal> findAllByMealCategory(MealCategory category);
}
