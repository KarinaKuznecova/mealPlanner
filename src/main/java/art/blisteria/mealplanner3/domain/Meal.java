package art.blisteria.mealplanner3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @Column(name = "meal_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "meal_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private MealCategory mealCategory;

    //key = product_id, value = product_amount
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "products")
    private Map<Long, Double> products;

    //key = product, value = product_amount
    @Transient
    private Map<Product, Double> realProducts = new HashMap<>();

    public Meal() {
    }

    public Meal(String name, String description, MealCategory category, Map<Long, Double> products) {
        this.name = name;
        this.description = description;
        this.mealCategory = category;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MealCategory getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(MealCategory mealCategory) {
        this.mealCategory = mealCategory;
    }

    public Map<Long, Double> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Double> products) {
        this.products = products;
    }

    public Map<Product, Double> getRealProducts() {
        return realProducts;
    }

    public void setRealProducts(Map<Product, Double> realProducts) {
        this.realProducts = realProducts;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mealCategory=" + mealCategory +
                ", products=" + realProducts +
                '}';
    }
}
