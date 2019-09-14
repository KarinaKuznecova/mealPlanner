package art.blisteria.mealplanner3.service;

import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

}
