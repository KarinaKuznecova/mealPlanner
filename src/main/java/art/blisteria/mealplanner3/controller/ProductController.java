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

    @GetMapping (params = {"id"})
    public Product getProduct(@RequestParam("id") Long id) {
        return service.getById(id);
    }

    @GetMapping (params = {"name"})
    public Product testCreate(@RequestParam("name") String test) {
        Product product = new Product(999L, test, "test description", 1D);
        service.createProduct(product);
        return product;
    }

}
