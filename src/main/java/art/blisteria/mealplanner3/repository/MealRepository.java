package art.blisteria.mealplanner3.repository;

import art.blisteria.mealplanner3.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal getMealByName(String name);
}
