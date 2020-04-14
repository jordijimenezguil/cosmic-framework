package com.jordi.jimenez.guil.cosmic.core.infraestructure.service.encryption;


import com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier.AccessDeniedException;
import com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier.IdentifierInfo;
import com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import static java.time.Instant.now;
import static java.util.Optional.ofNullable;

public class JWTIdentifierService implements IdentifierService {

  private final static String SECRET_KEY = "837r2ygeyufb";
  private final static String CLAIM_ROLE = "role";
  private final static int EXPIRATION_TOKEN_MINUTES = 20;

  @Override
  public String generate(String subjectName) {
    return Jwts.builder()
        .setId(UUID.randomUUID().toString())
        .setSubject(subjectName)
        .claim(CLAIM_ROLE, UserRole.ADMINISTRATOR)
        .setIssuedAt(Date.from(now()))
        .setExpiration(Date.from(Instant.now().plus(EXPIRATION_TOKEN_MINUTES, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
        .compact();
  }

  @Override
  public IdentifierInfo getClaimFromJWT(String token) {
    UserRole userRole = ofNullable(Jwts.parser()
        .setSigningKey(SECRET_KEY.getBytes())
        .parseClaimsJws(token).getBody())
        .orElseThrow(AccessDeniedException::new)
        .get(CLAIM_ROLE, UserRole.class);

    if (userRole == null) {
      throw new AccessDeniedException();
    }

    return IdentifierInfo.IdentifierInfoBuilder
        .anIdentifierInfo()
        .withRole(userRole)
        .build();
  }
}
