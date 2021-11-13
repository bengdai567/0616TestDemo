package com.example.demo.designModel.templet;

public abstract class Benchmark {
	/**
	 * 　　* 下面操作是我们在子类中完成
	 */
	public abstract void benchmark();

	public final long repeat(int count) {
		if (count <= 0){
			return 0;
		}else {
			long startTime = System.currentTimeMillis();
			System.out.println(startTime);

			for (int i = 0; i < count; i++){
				benchmark();
			}

			long stopTime = System.currentTimeMillis();
			System.out.println(stopTime);
			return stopTime - startTime;
		}
	}


}
