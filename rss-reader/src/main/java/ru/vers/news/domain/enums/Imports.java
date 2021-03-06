package ru.vers.news.domain.enums;

import lombok.Getter;


@Getter
public enum Imports {
  UNKNOWN,
  BIATLON,
  RT;


  public static Imports from(String imports){
    for (Imports e : Imports.values()){
      if (e.name().equals(imports)){
        return e;
      }
    }
    return UNKNOWN;
  }

}
