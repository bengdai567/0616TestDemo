package com.example.demo.designModel.abstractFactory;

public class TestOnes {
  public static void main(String[] arges){
    BlackAbstractFacoryImpl blackAbstractFacory = new BlackAbstractFacoryImpl();
    blackAbstractFacory.createCat().eat();
    blackAbstractFacory.createDog().eat();

    System.out.println("================================================");

    WhiteAbstractFacoryImpl whiteAbstractFacory = new WhiteAbstractFacoryImpl();
    whiteAbstractFacory.createCat().eat();
    whiteAbstractFacory.createDog().eat();
  }
}
