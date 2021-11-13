package com.example.demo.designModel.templet;

public class MethodBenchmark extends Benchmark {
	@Override
	public void benchmark() {

		for (int i = 0; i < 10; i++) {
			System.out.println("i=" + i);
		}
	}
}

class test{

	public static void main(String[] args) {
		MethodBenchmark methodBenchmark = new MethodBenchmark();
		long duration = methodBenchmark.repeat(100);
		System.out.println("The operation took " + duration + " milliseconds");
	}
	}