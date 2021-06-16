package com.example.demo.memento;

import java.io.File;
import java.io.Serializable;

public class Conmit implements Serializable {
	 int num;
	 File file=null;

	public Conmit(Originator originator){

		num = originator.num;
		file = originator.file;
	}
}


class test{
	public static void main(String[] args) {
		Originator originator = new Originator();
		originator.num = 123;

		originator.setConmit(new Conmit(originator));

		originator.num = 223;
		System.out.println(originator.getConmit().num);
	}
}