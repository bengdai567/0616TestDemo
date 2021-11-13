package com.example.demo.designModel.abstractFactory;

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
