package ro.ubb.movie_catalog.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ro.ubb.movie_catalog.core.config.*;

@Configuration
@ComponentScan({"ro.ubb.movie_catalog.core"})
@Import({JPAConfig.class})
@PropertySources({@PropertySource(value = "classpath:local/db.properties"),
})
public class AppLocalConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
