package com.example.demo.designModel.bridge;

public class Test2 {
	public static void main(String[] args) {
		jianaiBuCoffee jianaiBuCoffee = new jianaiBuCoffee();
		jianaiCoffee jianaiCoffee = new jianaiCoffee();
		beizi beizi = new beizi();
		beizi2 beizi2 = new beizi2();
		beizi.chooseBeizi(jianaiCoffee);
		beizi2.chooseBeizi(jianaiCoffee);

		beizi.chooseBeizi(jianaiBuCoffee);
		beizi2.chooseBeizi(jianaiBuCoffee);

	}
}


abstract class Coffee{
	private BeiziClass beiziClass;
	private String naiBuNai;
	abstract void jiabujiaNai();
	public BeiziClass getBeiziClass() {
		return beiziClass;
	}

	public void setBeiziClass(BeiziClass beiziClass) {
		this.beiziClass = beiziClass;
	}

	public String getNaiBuNai() {
		return naiBuNai;
	}

	public void setNaiBuNai(String naiBuNai) {
		this.naiBuNai = naiBuNai;
	}
}
abstract class BeiziClass{
	abstract void chooseBeizi(Coffee coffee);
}

class jianaiCoffee extends Coffee{

	public jianaiCoffee(){
		setNaiBuNai("加奶");
	}
	@Override
	void jiabujiaNai() {
		BeiziClass beiziClass = getBeiziClass();
		beiziClass.chooseBeizi(this);
	}
}

class jianaiBuCoffee extends Coffee{

	public jianaiBuCoffee(){
		setNaiBuNai("不加奶");
	}
	@Override
	void jiabujiaNai() {
		BeiziClass beiziClass = getBeiziClass();
		beiziClass.chooseBeizi(this);
	}
}

class beizi extends BeiziClass{

	@Override
	void chooseBeizi(Coffee coffee) {
		System.out.println(coffee.getNaiBuNai()+"小杯");
	}
}
class beizi2 extends BeiziClass{

	@Override
	void chooseBeizi(Coffee coffee) {
		System.out.println(coffee.getNaiBuNai()+"大杯");
	}
}