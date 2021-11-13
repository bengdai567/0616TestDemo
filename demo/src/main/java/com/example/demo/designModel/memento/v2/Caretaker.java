package com.example.demo.designModel.memento.v2;

public class Caretaker {
	private Memento memento;

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}
}

class test{
	public static void main(String[] args){
		Originator originator = new Originator();
		originator.setState("第一次原始赋值");

		Caretaker caretaker = new Caretaker();
		caretaker.setMemento(originator.getMemento());

		originator.setState("第二次原始赋值");
		originator.shouState();

		originator.setMemento(caretaker.getMemento());
		originator.shouState();
	}
}
