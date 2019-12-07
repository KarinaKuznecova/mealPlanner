package art.blisteria.mealplanner3.controller;

import art.blisteria.mealplanner3.domain.Product;
import art.blisteria.mealplanner3.domain.ProductCategory;
import art.blisteria.mealplanner3.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService service = ProductService.getInstance();

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        Product response = service.createProduct(product);
        return ResponseEntity.created(builder.path("api/v1/products/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Product> getProductById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Product> getProductByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping(params = {"name", "description", "amount", "category"})
    public Product testCreate(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("amount") Double amount,
                              @RequestParam("category") ProductCategory category) {
        Product product = new Product(name, description, amount, category);
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
    public ResponseEntity deleteProduct(@RequestParam("id") Long id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
