package com.jordi.jimenez.guil.cosmic.core.infraestructure.service.encryption;


import com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier.IdentifierInfo;

public interface IdentifierService {

  String generate(String subjectName);

  IdentifierInfo getClaimFromJWT(String token);
}
