package bootstrap;

import com.jordi.jimenez.guil.cosmic.core.usecase.DatabaseUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication(
    scanBasePackages = {
        "com.jordi.jimenez.guil.cosmic.core.infraestructure"
    })
public class MyBusinessApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(MyBusinessApplication.class, args);

    applicationContext
        .getBean(DatabaseUseCase.class)
        .start(MyBusinessConfiguration
            .myBusinessMetaModel());
  }
}
