package com.example.demo.designModel.factoryDemo;

public class TeacherWorkFactoryFactory implements WorkFaceFactory {
  @Override
  public Work getWork() {
    return new TeacherWork();
  }
}
