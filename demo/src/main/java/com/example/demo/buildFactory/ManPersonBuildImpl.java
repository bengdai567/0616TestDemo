package com.example.demo.buildFactory;

public class ManPersonBuildImpl implements PersonBuild {

  Person person;

  public ManPersonBuildImpl(){
    person = new Man();
  }

  @Override
  public void buildHead() {
    person.setHead("头部运动》》》");
  }

  @Override
  public void buildBody() {
    person.setBody("身体运动》》》");
  }

  @Override
  public void buildFoot() {
    person.setFoot("脚步运动》》》");
  }

  @Override
  public Person buildPerson() {
    return person;
  }
}
