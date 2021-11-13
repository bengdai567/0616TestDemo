package com.example.demo.designModel.observer;

public class TestOne {
	public static void main(String[] args) {
		HupuPoliceman hupuPoliceman = new HupuPoliceman();
		TianHePoliceman tianHePoliceman = new TianHePoliceman();
		HupuCitizen hupuCitizen = new HupuCitizen(hupuPoliceman);
		TianHeCitizen tianHeCitizen = new TianHeCitizen(tianHePoliceman);
		hupuCitizen.sendMessage("nomal");
		tianHeCitizen.sendMessage("unnomal");
	}
}
