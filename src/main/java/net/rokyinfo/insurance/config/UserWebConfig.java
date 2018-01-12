package net.rokyinfo.insurance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class UserWebConfig extends WebMvcConfigurerAdapter {

    @Value("${insurance.res.image.storage.path.root}")
    private String resourcePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/res/**")
                .addResourceLocations("file:" + resourcePath);
    }


}