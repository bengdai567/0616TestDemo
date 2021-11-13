package com.example.demo.designModel.factoryDemo;

public class StudentWorkFactoryFactory implements WorkFaceFactory {
  @Override
  public Work getWork() {
    return new StudentWork();
  }
}
