package com.example.demo.testCodw;


public class TestOneDemo  {
  private String name;
  private Long id;
  private String classHome;
  private String flagCount;

  public String getFlagCount() {
    return flagCount;
  }

  public void setFlagCount(String flagCount) {
    this.flagCount = flagCount;
  }

  public TestOneDemo() {
  }

  public TestOneDemo(String name, Long id,String classHome) {
    this.name = name;
    this.id = id;
    this.classHome = classHome;
  }

  @Override
  public String toString() {
    return "TestOneDemo{" +
      "name='" + name + '\'' +
      ", id='" + id + '\'' +
      ", classHome='" + classHome + '\'' +
      '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getClassHome() {
    return classHome;
  }

  public void setClassHome(String classHome) {
    this.classHome = classHome;
  }
}
