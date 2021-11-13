package com.example.demo.designModel.singleto;

public enum SingletoTest {
  SYSTEM_EXC(10, "分配异常"),
  BUSINESS_EXC(20, "分配失败"),
  REPLENISH_EXC(30, "分配补货");

  private int value;

  private String name;

  private SingletoTest(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public int getValue() {
    return this.value;
  }

  public String getName() {
    return this.name;
  }

  public static String getName(int value) {
    SingletoTest locateStatus = parse(value);
    if (locateStatus != null) {
      return locateStatus.getName();
    }
    return null;
  }

  public static SingletoTest parse(int value) {
    for (SingletoTest locateStatus : SingletoTest.values()) {
      if (locateStatus.getValue() == value) {
        return locateStatus;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    String name = SingletoTest.getName(30);
    System.out.println(name);
  }
}
