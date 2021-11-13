package com.example.demo.designModel.observer;

public class TianHeCitizen extends Citizen {
	public TianHeCitizen(Policeman policeman){
		setPoliceman();
		register(policeman);
	}

	@Override
	void sendMessage(String help) {
		setHelp(help);
		for (int i = 0; i < pols.size(); i++) {
			Policeman o = (Policeman) pols.get(i);
			o.action(this);
		}
	}
}
