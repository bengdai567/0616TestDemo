package com.example.demo.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Citizen {
	List pols;
	private String help = "nomal";

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	abstract void sendMessage(String help);

	public void setPoliceman(){
		this.pols = new ArrayList();
	}

	public void register(Policeman policeman){
		this.pols.add(policeman);
	}

	public void unregister(Policeman policeman){
		this.pols.remove(policeman);
	}
}
