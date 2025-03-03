package Kisan.Sathi.Authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Default Spring Boot static resources (e.g., index.html, css, js)
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");  // default static resources (inside resources/static)

        // Custom resource handler for ProfilePics
        registry.addResourceHandler("/ProfilePics/**")
                .addResourceLocations("file:E:/KisanSathi/ProfilePics/")  // Ensure correct file path
                .setCachePeriod(0);// Optional: cache for 0 sec

        registry.addResourceHandler("/FarmersProduct/**")
                .addResourceLocations("file:E:/KisanSathi/FarmersProduct/")  // Ensure correct file path
                .setCachePeriod(0); // Optional: cache for 0 sec
    }


}
