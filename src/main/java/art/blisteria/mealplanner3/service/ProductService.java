package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final static ProductService service = new ProductService();

    @Autowired
    private ProductRepository productRepository;

    private ProductService() {
    }

    public static ProductService getInstance() {
        return service;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
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

    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
