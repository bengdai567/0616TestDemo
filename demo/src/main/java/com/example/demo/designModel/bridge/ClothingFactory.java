package com.example.demo.designModel.bridge;

public class ClothingFactory {
	private static Clothing clothing;
	public ClothingFactory(Clothing clothing){
		this.clothing = clothing;
	}

	public static Clothing getClothing(){
		return clothing;
	}
}
