package com.example.demo.Adapter;


//这种采用接口的方式将两个不相干的方法合到一起，也可以采用继承的方式
public class Adapter implements Target {

  private Adaptee adaptee;

  public Adapter(Adaptee adaptee){
    this.adaptee=adaptee ;
  }


  @Override
  public void adapteeMethod() {
    adaptee.adapteeMethod();
  }

  @Override
  public void adapterMethod() {
    System.out.println("适配后的方法");
  }




  public static void main(String[] args) {
    Adapter adapter = new Adapter(new Adaptee());
    adapter.adapteeMethod();
    adapter.adapterMethod();
  }
}

 class Adapters{
  public void adapterMethod(){
    System.out.println("另一个方法的对象");
  }
}

 class AdExtandapTer extends Adaptee{
  private Adapters adapters;
  public AdExtandapTer(Adapters adapters){
    this.adapters = adapters;
  }
  public void adapterMethod(){
    adapters.adapterMethod();
  }

  @Override
  public void adapteeMethod() {
    super.adapteeMethod();
  }

   public static void main(String[] args) {
     System.out.println("========继承方式========");
     AdExtandapTer adExtandapTer = new AdExtandapTer(new Adapters());
     adExtandapTer.adapteeMethod();
     adExtandapTer.adapterMethod();
   }
}
