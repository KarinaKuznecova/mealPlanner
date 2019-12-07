package art.blisteria.mealplanner3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "products")
    private Map<Long, Double> products;

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

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mealCategory=" + mealCategory +
                ", products=" + products +
                '}';
    }
}
