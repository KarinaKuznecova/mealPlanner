package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.domain.ProductCategory;
import art.blisteria.mealplanner3.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private static Logger logger = LogManager.getLogger(ProductService.class);

    private ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        logger.info("Product saved: " + product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    public Product getByName(String name) {
        return productRepository.getProductByName(name);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findAllByAmountGreaterThan(0);
    }

    public List<Product> getProductsWithAmountZero() {
        return productRepository.findAllByAmountEquals(0);
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.findAllByProductCategory(category);
    }

    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
