package bootstrap;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.EnableCosmicApplication;
import com.jordi.jimenez.guil.cosmic.core.usecase.CosmicUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableCosmicApplication
public class MyBusinessApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(MyBusinessApplication.class, args);

    applicationContext
        .getBean(CosmicUseCase.class)
        .start(MyBusinessConfiguration
            .myBusinessMetaModel());
  }
}
