package com.jordi.jimenez.guil.cosmic.core.infraestructure.service.encryption;


import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.HasherErrorException;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.bouncycastle.pqc.crypto.xmss.XMSSKeyParameters.SHA_256;

public class SHA256Hasher implements Hasher {

  @Override
  public String create(String target) {
    final MessageDigest digest;

    try {
      digest = MessageDigest.getInstance(SHA_256);
      final byte[] hash = digest.digest(target.getBytes(StandardCharsets.UTF_8));
      return new String(Hex.encode(hash));

    } catch (NoSuchAlgorithmException e) {
      throw new HasherErrorException(e);
    }
  }

}
