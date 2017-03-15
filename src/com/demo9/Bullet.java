package com.demo9;

public class Bullet implements Runnable{

	private int xCoordinate;
	private int yCoordinate;
	private int direct;
	private int speed = 1;
	private boolean isAlive=false;


	public Bullet(int x,int y,int direct){
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.direct = direct;
	}

	@Override
	public void run() {
		
		while(true){
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch (direct) {
			case 0:
				yCoordinate-=speed;
				break;
			case 1:
				xCoordinate+=speed;
				break;
			case 2:
				yCoordinate+=speed;
				break;
			case 3:
				xCoordinate-=speed;
				break;
			default:
				break;
			}
			//判断子弹何时死亡
			//1.碰到墙壁
			if (xCoordinate<0||xCoordinate>400||yCoordinate<0||yCoordinate>300) {
				isAlive = false;
				break;
			}
		}
	}
	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
