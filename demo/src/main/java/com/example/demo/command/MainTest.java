package com.example.demo.command;

public class MainTest {
	public static void main(String[] args){
		PersonalVisitor personalVisitor = new PersonalVisitor();
		Cpu cpu = new Cpu();
		Board board = new Board();
		Memory memory = new Memory();
		cpu.accept(personalVisitor);
		board.accept(personalVisitor);
		memory.accept(personalVisitor);

		System.out.println(personalVisitor.totalPrice);
	}

}

//打折优惠
interface Visitor{
	void visitorCpu(Cpu cpu);
	void visitorBoard(Board board);
	void visitorMemory(Memory memory);
}
//电脑配件
interface ComputerPart{
	double getPrice();
	void accept(Visitor v);
}

class Cpu implements ComputerPart{
	@Override
	public double getPrice() {
		return 500;
	}

	@Override
	public void accept(Visitor v) {
		v.visitorCpu(this);
	}
}

class Memory implements ComputerPart{
	@Override
	public double getPrice() {
		return 300;
	}

	@Override
	public void accept(Visitor v) {
		v.visitorMemory(this);
	}
}

class Board implements ComputerPart{
	@Override
	public double getPrice() {
		return 200;
	}

	@Override
	public void accept(Visitor v) {
		v.visitorBoard(this);
	}
}

class PersonalVisitor implements  Visitor{
	double totalPrice;

	@Override
	public void visitorCpu(Cpu cpu) {
		 totalPrice+=cpu.getPrice()*0.9;
	}

	@Override
	public void visitorBoard(Board board) {
		totalPrice+=board.getPrice()*0.95;
	}

	@Override
	public void visitorMemory(Memory memory) {
		totalPrice+=memory.getPrice()*0.9;
	}
}