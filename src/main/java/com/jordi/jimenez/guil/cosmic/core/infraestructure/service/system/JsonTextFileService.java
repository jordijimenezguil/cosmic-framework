package com.jordi.jimenez.guil.cosmic.core.infraestructure.service.system;

import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.ParseFileToTextErrorException;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import java.io.IOException;
import java.io.InputStream;

public class JsonTextFileService implements FileService {


  @Override
  public String fileAsString(String nameFile) {
    try {
      return Streams.asString(getFile(nameFile));
    } catch (IOException e) {
      throw new ParseFileToTextErrorException(e);
    }
  }

  private static InputStream getFile(String nameFile) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    return classloader.getResourceAsStream(nameFile);
  }

}