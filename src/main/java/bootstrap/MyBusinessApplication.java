package bootstrap;

import com.jordi.jimenez.guil.cosmic.core.metamodel.MetaModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.jordi.jimenez.guil.cosmic.core.metamodel.FieldType.INTEGER;
import static com.jordi.jimenez.guil.cosmic.core.metamodel.FieldType.STRING;

@SpringBootApplication
public class MyBusinessApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyBusinessApplication.class, args);


    MetaModel metaModel = MetaModel.builder()
        .withDomain("user")
        .withUniqueIdentifierField("id", "ID", INTEGER)
        .withField("name", "NAME", STRING)
        .end()

        .withDomain("article")
        .withUniqueIdentifierField("id", "ID", INTEGER)
        .withField("title", "TITLE", STRING)
        .withField("description", "DESC", STRING)
        .end()

        .build();

    metaModel.toString();
  }


}
