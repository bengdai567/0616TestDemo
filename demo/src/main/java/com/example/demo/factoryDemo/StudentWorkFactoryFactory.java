package com.example.demo.factoryDemo;

public class StudentWorkFactoryFactory implements WorkFaceFactory {
  @Override
  public Work getWork() {
    return new StudentWork();
  }
}
