package com.example.demo.factoryDemo;

public class TeacherWorkFactoryFactory implements WorkFaceFactory {
  @Override
  public Work getWork() {
    return new TeacherWork();
  }
}
