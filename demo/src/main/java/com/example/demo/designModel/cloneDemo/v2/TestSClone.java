package com.example.demo.designModel.cloneDemo.v2;

public class TestSClone {
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
    System.out.println(clone.location);

  }
}

class Person implements Cloneable{
  int num = 30;
  String name = "小伙子";
  Location location = new Location(11,"乱离");

  @Override
  protected Object clone() throws CloneNotSupportedException {
    Person p = (Person) super.clone();
    p.location = (Location) location.clone();
    return p                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ;
  }
}

   class Location implements Cloneable{
     int size;
     String teacher;

    public Location(int size,String teacher){
      this.size = size;
      this.teacher = teacher;
    }

     @Override
     protected Object clone() throws CloneNotSupportedException {
       return super.clone();
     }

     @Override
    public String toString() {
      return "Location{" +
        "size=" + size +
        ", teacher='" + teacher + '\'' +
        '}';
    }
  }
