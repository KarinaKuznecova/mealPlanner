package art.blisteria.mealplanner3.repository;

import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductByName(String name);

    List<Product> findAllByAmountGreaterThan(double amount);

    List<Product> findAllByAmountEquals(double amount);

    List<Product> findAllByProductCategory(ProductCategory category);
}
