package art.blisteria.mealplanner3.controller;

import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        Product response = service.createProduct(product);
        return ResponseEntity.created(builder.path("api/v1/products/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Product> productById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(params = {"name"})
    public Product testCreate(@RequestParam("name") String test) {
        Product product = new Product(test, "test description", 1D);
        service.createProduct(product);
        return product;
    }

    @GetMapping(params = {"name", "description", "amount"})
    public Product fullCreate(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("amount") Double amount) {
        Product product = new Product(name, description, amount);
        service.createProduct(product);
        return product;
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        Product response = service.editProduct(product);
        return ResponseEntity.created(builder.path("api/v1/products/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @DeleteMapping(params = {"id"})
    public void deleteProduct(@RequestParam("id") Long id) {
        service.deleteProduct(id);
    }
}
