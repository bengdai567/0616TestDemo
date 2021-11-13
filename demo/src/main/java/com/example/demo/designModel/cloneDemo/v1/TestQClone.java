package com.example.demo.designModel.cloneDemo.v1;

public class TestQClone {
  public static void main(String[] args) throws Exception {
    Person person = new Person();
    Person clone = (Person) person.clone();
    System.out.println(clone.name);
    System.out.println(clone.num);
    System.out.println(clone.location);

    System.out.println(clone.location==person.location);
    clone.name = "丫子";
    System.out.println(person.name);
    System.out.println(clone.name);
    person.location.size = 100;
    System.out.println(clone.location);
    clone.location.size = 3000;
    System.out.println(person.location);

  }
}
  class Person implements Cloneable{
    int num = 30;
    String name = "小伙子";
    Location location = new Location(11,"乱离");

    @Override
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }
  }

   class Location{
     int size;
     String teacher;

    public Location(int size,String teacher){
      this.size = size;
      this.teacher = teacher;
    }

    @Override
    public String toString() {
      return "Location{" +
        "size=" + size +
        ", teacher='" + teacher + '\'' +
        '}';
    }
  }
