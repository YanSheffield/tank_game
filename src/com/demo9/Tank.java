package com.demo9;

//用于画坦克
public class Tank {
	
	private int direct = 0;
	private int speed=4;
	private int color;
	public Bullet bullet = null;
	
	// 表示坦克的横竖坐标
	int tankXCoordinate = 0;
	int tankYCoordinate = 0;
	
	public void moveUp(){
		tankYCoordinate-=speed;
	}
	public void moveDown(){
		tankYCoordinate+=speed;
	}
	public void moveLeft(){
		tankXCoordinate-=speed;
	}
	public void moveRight(){
		tankXCoordinate+=speed;
	}
	
	public void shoot(){ 	
		switch (this.direct) {
		case 0: 
			bullet = new Bullet(tankXCoordinate+10, tankYCoordinate,0);
			break;
		case 1:
			bullet = new Bullet(tankXCoordinate+30, tankYCoordinate+10,1);
			break;
		case 2:
			bullet = new Bullet(tankXCoordinate+10, tankYCoordinate+30,2);
			break;
		case 3:
			bullet = new Bullet(tankXCoordinate, tankYCoordinate+10,3);
			break;		
		default:
			break;
		}
		bullet.setAlive(true);
		Thread bulletThread = new Thread(bullet);
		bulletThread.start();
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

	public void setDirect(int direct) {
		this.direct = direct;
	}

	// 从别的类传值过来到全局变量中
	public Tank(int xCoortinate, int yCoordinate) {
		this.tankXCoordinate = xCoortinate;
		this.tankYCoordinate = yCoordinate;
	}
	
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}



