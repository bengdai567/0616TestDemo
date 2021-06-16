package com.example.demo.abstractFactory;

public class WhiteAbstractFacoryImpl implements AbstractFacory {
  @Override
  public ICat createCat() {
    return new WhiteCat();
  }

  @Override
  public IDog createDog() {
    return new WhiteDog();
  }
}
