package com.example.demo.designModel.factoryDemo;

public class TestRest {
  public static void main(String[] args) {
    StudentWorkFactoryFactory factoryFactory = new StudentWorkFactoryFactory();
    factoryFactory.getWork().doWork();

    TeacherWorkFactoryFactory factory = new TeacherWorkFactoryFactory();
    Work work = factory.getWork();
    work.doWork();
  }
}
