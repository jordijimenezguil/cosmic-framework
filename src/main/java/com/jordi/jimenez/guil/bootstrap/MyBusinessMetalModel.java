package com.jordi.jimenez.guil.bootstrap;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetaModel;
import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetalModelable;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;

@CosmicMetaModel
public class MyBusinessMetalModel implements CosmicMetalModelable {


  @Override
  public MetaModel getMetalModel() {
    return MetaModel.builder().withDomain("user")
        .end()
        .build();
  }
}
