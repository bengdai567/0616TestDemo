package com.example.demo.buildFactory;

public class PersonDiractor {
  public Person personDiractor(PersonBuild personBuild){
      personBuild.buildHead();
      personBuild.buildBody();
      personBuild.buildFoot();
      return personBuild.buildPerson();
  }
}
