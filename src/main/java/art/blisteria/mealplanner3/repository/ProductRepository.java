package art.blisteria.mealplanner3.repository;

import art.blisteria.mealplanner3.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
