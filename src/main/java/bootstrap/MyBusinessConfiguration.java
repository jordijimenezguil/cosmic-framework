package bootstrap;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;

public class MyBusinessConfiguration {


  public static MetaModel myBusinessMetaModel() {
    return MetaModel.builder().withDomain("user")
        .end()
        .build();
  }
}
