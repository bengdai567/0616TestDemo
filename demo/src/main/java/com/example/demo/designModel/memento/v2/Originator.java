package com.example.demo.designModel.memento.v2;

public class Originator {
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Memento getMemento(){
		return new Memento(state);
	}

	public void setMemento(Memento memento){
		this.state =memento.getState();
	}

	public void shouState(){
		System.out.println(state);
	}
}
