package com.example.demo.abstractFactory;

public class BlackAbstractFacoryImpl implements AbstractFacory {
  @Override
  public ICat createCat() {
    return new BlackCat();
  }

  @Override
  public IDog createDog() {
    return new BlackDog();
  }
}
