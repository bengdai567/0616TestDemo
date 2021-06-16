package com.example.demo.observer;

public class TianHePoliceman implements Policeman {
	@Override
	public void action(Citizen citizen) {
		String help = citizen.getHelp();
		if (help.equals("nomal")){
			System.out.println("天河正常，不需触动");
		}else if(help.equals("unnomal")){
			System.out.println("天河不正常，马上出动");
		}
	}
}
