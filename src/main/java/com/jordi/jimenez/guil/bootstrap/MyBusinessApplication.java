package com.jordi.jimenez.guil.bootstrap;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.EnableCosmicApplication;
import org.springframework.boot.SpringApplication;

@EnableCosmicApplication
public class MyBusinessApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyBusinessApplication.class, args);
  }

}
