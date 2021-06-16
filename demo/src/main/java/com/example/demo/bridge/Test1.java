package com.example.demo.bridge;

public abstract class Test1 {
	private String name;
	private Dress dress;

	public abstract void classes();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dress getDress() {
		return dress;
	}

	public void setDress(Dress dress) {
		this.dress = dress;
	}
}

abstract class Dress{
	abstract void perdress(Test1 test1);
}

class Person1 extends Test1{

	public Person1(){
		setName("小瓜");
	}

	@Override
	public void classes() {
		Dress dress = getDress();
		dress.perdress(this);
	}
}
class Person2 extends Test1{

	public Person2(){
		setName("小雅");
	}

	@Override
	public void classes() {
		Dress dress = getDress();
		dress.perdress(this);
	}
}

class OneDress extends Dress{
	@Override
	public void perdress(Test1 test1) {
		System.out.println(test1.getName()+"一地址");
	}
}

class TwoDress extends Dress{
	@Override
	public void perdress(Test1 test1) {
		System.out.println(test1.getName()+"二地址");
	}
}

class one{
	public static void main(String[] args) {
		Test1 test1 = new Person1();
		Test1 test2 = new Person2();
		Dress oneDress = new OneDress();
		Dress twoDress = new TwoDress();

		oneDress.perdress(test1);
		twoDress.perdress(test1);

		oneDress.perdress(test2);
		twoDress.perdress(test2);

	}
}