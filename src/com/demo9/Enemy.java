package com.demo9;

public class Enemy extends Tank implements Runnable{

	public Enemy(int xCoortinate, int yCoordinate) {
		super(xCoortinate, yCoordinate);
	}

	@Override
	public void run() {
		double ran = Math.random();
		if (ran<=0.25) {
			moveLeft();
		}else if (ran>0.25&&ran<=0.5) {
			moveRight();
		}else if (ran>0.5&&ran<=0.75) {
			moveDown();
		}
	}
}
