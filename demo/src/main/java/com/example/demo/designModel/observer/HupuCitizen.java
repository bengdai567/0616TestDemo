package com.example.demo.designModel.observer;

public class HupuCitizen extends Citizen {

	public HupuCitizen(Policeman p){
		setPoliceman();
		register(p);
	}
	@Override
	void sendMessage(String help) {
		setHelp(help);
		for (int i = 0; i < pols.size(); i++) {

			//遍历并通知
			Policeman o = (Policeman) pols.get(i);
			o.action(this);
		}
	}
}
