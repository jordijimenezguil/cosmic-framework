package com.jordi.jimenez.guil.cosmic.core.infraestructure.service.encryption;

import java.util.UUID;

public class UniqueIdentifierHasher implements Hasher {

  @Override
  public String create(String something) {
    String raw = UUID.randomUUID().toString() + UUID.randomUUID();
    return raw.replace("-", "");
  }
}
