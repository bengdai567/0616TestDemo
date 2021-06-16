package com.example.demo.memento.v1;

public class Caretaker {
	private Memento memento;

	public Memento getMemento(){
		return this.memento;
	}

	public void setMemento(Memento memento){
		this.memento = memento;
	}
}

class test{
	public static void main(String[] args) {
		Originator org = new Originator();
		org.setState("开会中");

		Caretaker ctk = new Caretaker();
		ctk.setMemento(org.createMemento());//将数据封装在Caretaker

		org.setState("睡觉中");
		org.showState();//显示

		org.setMemento(ctk.getMemento());//将数据重新导入
		org.showState();
	}

}
