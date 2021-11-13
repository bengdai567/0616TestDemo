package com.example.demo.designModel.observer;

public class HupuPoliceman implements Policeman {
	@Override
	public void action(Citizen citizen) {
		String help = citizen.getHelp();
		if (help.equals("nomal")){
			System.out.println("正常出警》》》》");
		}else if (help.equals("unnomal")){
			System.out.println("有人打击，马上出警》》》》");
		}
	}
}
