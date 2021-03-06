package art.blisteria.mealplanner3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebBean {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins("*", "file://", "http://localhost:3000/")
                        .allowedHeaders("Content-Type", "Access-Control-Allow-Credentials")
                        .allowedMethods("PUT", "DELETE", "GET", "POST")
                        .allowCredentials(true).maxAge(3600);
            }
        };
    }
}
