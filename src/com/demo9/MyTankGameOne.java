package com.demo9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTankGameOne extends JFrame {

	MyPanel myPanel = null;

	public static void main(String[] args) {
		MyTankGameOne myTankGameOne = new MyTankGameOne();
	}

	// 构造函数
	public MyTankGameOne() {
		myPanel = new MyPanel();
		// 启动 Mypanel 线程，以保证面板被不停重画
		Thread thread = new Thread(myPanel);
		thread.start();
		this.add(myPanel);
		this.setSize(400, 300);
		this.setVisible(true);
		// 事件发生的地点，事件类型，事件接受（需要完成 Event 方法，任意类都可以）
		// this.addMouseListener()
		this.addKeyListener(myPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MyPanel extends JPanel implements KeyListener, Runnable {

	private int x = 150;
	private int y = 150;
	// 定义我的坦克
	MyHero hero = null;
	int enemySize = 3;
	// 定义敌人的坦克
	Vector<Enemy> enemies = new Vector<>();

	// 在构造方法中进行一些初始化的工作
	public MyPanel() {
		// 初始化我的坦克
		hero = new MyHero(x, y);
		// 初始化敌人的坦克
		for (int i = 0; i < enemySize; i++) {
			Enemy em = new Enemy((i + 1) * 50, 0);
			em.setColor(1);
			em.setDirect(2);
			enemies.add(em);
		}
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.fillRect(0, 1, 400, 300);
		// draw hero tank
		this.drawTanks(hero.getTankXCoordinate(), hero.getTankYCoordinate(), graphics, hero.getDirect(), 0);
		// draw enermy tanks
		for (int i = 0; i < enemies.size(); i++) {
			this.drawTanks(enemies.get(i).getTankXCoordinate(), enemies.get(i).getTankYCoordinate(), graphics,
					enemies.get(i).getDirect(), enemies.get(i).getColor());
		}
		// hero坦克的子弹
		// draw bullets，很明显，一开始是没有子弹的，所以不会进入
		// 加上一些条件后，既能够保证不抛出异常，又使得此功能不会被白白调用，而影响性能不浪费资源
		if (hero.bullet != null && hero.bullet.isAlive() == true) {
			graphics.setColor(Color.white);
			graphics.fillRect(hero.bullet.getxCoordinate(), hero.bullet.getyCoordinate(), 1, 1);
			// System.out.println("+++"+hero.bullet.getxCoordinate());
		}
		// 敌人坦克的子弹.为什么子弹会动？子弹的坐标依赖坦克的坐标，坦克不动，子弹也不会动
		graphics.setColor(Color.red);
		if (enemies.get(1).bullet != null) {
			for (int i = 0; i < enemies.size(); i++) {
				// 因为每次重绘都重新 bullet 对象，所以创建了都一直清除原来的数据
				graphics.fillRect(enemies.get(i).bullet.getxCoordinate(), enemies.get(i).bullet.getyCoordinate(), 3, 3);
				// System.out.println("@@@@"+enemies.get(i).bullet.getxCoordinate());
			}
		}
		
//		enemies.get(0).shoot();
			Thread thread = new Thread(enemies.get(0));
			Thread thread2 = new Thread(enemies.get(1));
			Thread thread3 = new Thread(enemies.get(2));
			thread.start();
			thread2.start();
			thread3.start();
	}

	public void drawTanks(int x, int y, Graphics graphics, int dirction, int type) {
		switch (type) {
		case 0:
			graphics.setColor(Color.cyan);
			break;
		case 1:
			graphics.setColor(Color.yellow);
			break;
		default:
			break;
		}
		// 判断方向
		switch (dirction) {
		case 0:// 向上
				// 画出我的坦克,two wheels
			graphics.fillRect(x, y, 5, 30);

			graphics.fillRect(x + 15, y, 5, 30);
			// body
			graphics.fillRect(x + 5, y + 5, 10, 20);
			// circle
			graphics.fillOval(x + 5, y + 10, 10, 10);
			// A line
			graphics.drawLine(x + 10, y + 15, x + 10, y);
			break;

		case 1:// 向右
			graphics.fillRect(x, y, 30, 5);
			graphics.fillRect(x, y + 15, 30, 5);
			graphics.fillRect(x + 5, y + 5, 20, 10);
			graphics.fillOval(x + 10, y + 5, 10, 10);
			graphics.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
		case 2:// 向下
				// 画出我的坦克,two wheels
			graphics.fillRect(x, y, 5, 30);

			graphics.fillRect(x + 15, y, 5, 30);
			// body
			graphics.fillRect(x + 5, y + 5, 10, 20);
			// circle
			graphics.fillOval(x + 5, y + 10, 10, 10);
			// A line
			graphics.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case 3:
			// 画出我的坦克,two wheels
			graphics.fillRect(x, y, 30, 5);
			graphics.fillRect(x, y + 15, 30, 5);
			graphics.fillRect(x + 5, y + 5, 20, 10);
			graphics.fillOval(x + 10, y + 5, 10, 10);
			graphics.drawLine(x + 15, y + 10, x, y + 10);
			break;
		default:		
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// press down,change the y coordinate
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			hero.setDirect(0);
			hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			hero.setDirect(2);
			hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.setDirect(3);
			hero.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.setDirect(1);
			hero.moveRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			hero.shoot();
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			enemies.get(0).shoot();
			enemies.get(1).shoot();
			enemies.get(2).shoot();
		}
//		if(e.getKeyCode() == KeyEvent.VK_E){
			
			
//		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}

	}
}
