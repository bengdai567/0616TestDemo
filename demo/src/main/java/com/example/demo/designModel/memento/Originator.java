package com.example.demo.designModel.memento;

import java.io.File;

public class Originator {
	 int num;
	 File file = null;

	public Originator(){}

	public void setConmit(Conmit conmit){
		num = conmit.num;
		file = conmit.file;
	}

	public Conmit getConmit(){
		return new Conmit(this);
	}
}
