package com.demo9;

import java.util.Random;

public class Enemy extends Tank implements Runnable{

	//override the properties in parent class.Because enemy's movement 
	//should be independent from hero tank
	int tankXCoordinate ;
	int tankYCoordinate ;
	int speed = 1;
	int direct = 2;
	int randomDirect = 2;
	private boolean enemiesAlive = true;
	public int getRandomDirect() {
		return randomDirect;
	}

	public void setRandomDirect(int randomDirect) {
		this.randomDirect = randomDirect;
	}

	private int miniStep= 10;
	
	public Enemy(int xCoortinate, int yCoordinate) {
		super(xCoortinate, yCoordinate);
		this.tankXCoordinate = xCoortinate;
		this.tankYCoordinate = yCoordinate;
		
	}

	@Override
	public void run() {
		if(miniStep==5){
			Random rand = new Random();
			randomDirect = rand.nextInt(4);
		}
		try {
			Thread.sleep(1000);
			if (randomDirect==3) {
					setDirection(randomDirect);
					moveLeft();
					if (tankXCoordinate<=0||tankXCoordinate>400||tankYCoordinate<=0||tankYCoordinate>300){
						setDirection(1);
						moveRight();
					}
				}		
			else if (randomDirect==1) {
				setDirection(randomDirect);
				moveRight();
				if (tankXCoordinate<=0||tankXCoordinate>400||tankYCoordinate<=0||tankYCoordinate>300){
					setDirection(3);
					moveLeft();
				}
			}else if (randomDirect==2) {
				setDirection(randomDirect);
				moveDown();
				if (tankXCoordinate<=0||tankXCoordinate>400||tankYCoordinate<=0||tankYCoordinate>300){
					setDirection(0);
					moveUp();
				}
			}else if(randomDirect==0){
				setDirection(randomDirect);
				moveUp();
				if (tankXCoordinate<=0||tankXCoordinate>400||tankYCoordinate<=0||tankYCoordinate>300){
					setDirection(2);
					moveDown();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	public void moveUp(){		
		tankYCoordinate-=speed;	
		miniStep--;
		if(miniStep==0){
			miniStep=10;
		}
	}
	public void moveDown(){
		tankYCoordinate+=speed;
		miniStep--;
		if(miniStep==0){
			miniStep=10;
		}
	}
	public void moveLeft(){
		tankXCoordinate-=speed;
		miniStep--;
		if(miniStep==0){
			miniStep=10;
		}
	}
	public void moveRight(){
		tankXCoordinate+=speed;
		miniStep--;
		if(miniStep==0){
			miniStep=10;
		}
	}
	
	public int getTankXCoordinate() {
		return tankXCoordinate;
	}

	public void setTankXCoordinate(int tankXCoordinate) {
		this.tankXCoordinate = tankXCoordinate;
	}

	public int getTankYCoordinate() {
		return tankYCoordinate;
	}

	public void setTankYCoordinate(int tankYCoordinate) {
		this.tankYCoordinate = tankYCoordinate;
	}
	
	public int getDirect() {
		return direct;
	}

	public void setDirection(int direct) {
		this.direct = direct;
	}

}
