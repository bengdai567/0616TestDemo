package com.example.demo.buildFactory;

public class TestOne {
  public static void main(String[] args){
    PersonDiractor diractor = new PersonDiractor();
    Person person = diractor.personDiractor(new ManPersonBuildImpl());
    System.out.println(person.getHead());
    System.out.println(person.getBody());
    System.out.println(person.getFoot());
  }
}
