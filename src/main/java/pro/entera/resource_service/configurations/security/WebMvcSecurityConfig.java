package pro.entera.resource_service.configurations.security;

import lombok.AllArgsConstructor;
import org.pac4j.core.config.Config;
import org.pac4j.springframework.annotation.AnnotationConfig;
import org.pac4j.springframework.component.ComponentConfig;
import org.pac4j.springframework.web.SecurityInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
@Import({ComponentConfig.class, AnnotationConfig.class})
@ComponentScan(basePackages = "org.pac4j.springframework.web")
public class WebMvcSecurityConfig implements WebMvcConfigurer {

    private final Config config;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(SecurityInterceptor.build(config, "HeaderClient"))
            //.addPathPatterns("/api/v1/*")
            .addPathPatterns("/**");
    }
}
