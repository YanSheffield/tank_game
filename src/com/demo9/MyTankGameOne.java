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

	// æž„é€ å‡½æ•°
	public MyTankGameOne() {
		myPanel = new MyPanel();
		// å�¯åŠ¨ Mypanel çº¿ç¨‹ï¼Œä»¥ä¿�è¯�é�¢æ�¿è¢«ä¸�å�œé‡�ç”»
		Thread thread = new Thread(myPanel);
		thread.start();
		this.add(myPanel);
//		this.setSize(400, 300);
		this.setBounds(300, 400, 400, 300);
		this.setVisible(true);
		// äº‹ä»¶å�‘ç”Ÿçš„åœ°ç‚¹ï¼Œäº‹ä»¶ç±»åž‹ï¼Œäº‹ä»¶æŽ¥å�—ï¼ˆéœ€è¦�å®Œæˆ� Event æ–¹æ³•ï¼Œä»»æ„�ç±»éƒ½å�¯ä»¥ï¼‰
		// this.addMouseListener()
		this.addKeyListener(myPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MyPanel extends JPanel implements KeyListener, Runnable {

	private int heroX = 150;
	private int heroY = 150;
	// å®šä¹‰æˆ‘çš„å�¦å…‹
	MyHero hero = null;
	int enemySize = 3;
	// å®šä¹‰æ•Œäººçš„å�¦å…‹
	Vector<Enemy> enemies = new Vector<>();

	// åœ¨æž„é€ æ–¹æ³•ä¸­è¿›è¡Œä¸€äº›åˆ�å§‹åŒ–çš„å·¥ä½œ
	public MyPanel() {
		// åˆ�å§‹åŒ–æˆ‘çš„å�¦å…‹
		hero = new MyHero(heroX, heroY);
		// åˆ�å§‹åŒ–æ•Œäººçš„å�¦å…‹
		for (int i = 0; i < enemySize; i++) {
			Enemy em = new Enemy((i+1) * 50, 50);
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
		
		// heroå�¦å…‹çš„å­�å¼¹
		// draw bulletsï¼Œå¾ˆæ˜Žæ˜¾ï¼Œä¸€å¼€å§‹æ˜¯æ²¡æœ‰å­�å¼¹çš„ï¼Œæ‰€ä»¥ä¸�ä¼šè¿›å…¥
		// åŠ ä¸Šä¸€äº›æ�¡ä»¶å�Žï¼Œæ—¢èƒ½å¤Ÿä¿�è¯�ä¸�æŠ›å‡ºå¼‚å¸¸ï¼Œå�ˆä½¿å¾—æ­¤åŠŸèƒ½ä¸�ä¼šè¢«ç™½ç™½è°ƒç”¨ï¼Œè€Œå½±å“�æ€§èƒ½ä¸�æµªè´¹èµ„æº�
		graphics.setColor(Color.red);
		if (hero.bullet != null && hero.bullet.isAlive() == true) {
			graphics.fillRect(hero.bullet.getxCoordinate(), hero.bullet.getyCoordinate(), 1, 2);
			 System.out.println("X"+hero.bullet.getxCoordinate()+"Y "+hero.bullet.getyCoordinate());
		}
		// æ•Œäººå�¦å…‹çš„å­�å¼¹.ä¸ºä»€ä¹ˆå­�å¼¹ä¼šåŠ¨ï¼Ÿå­�å¼¹çš„å��æ ‡ä¾�èµ–å�¦å…‹çš„å��æ ‡ï¼Œå�¦å…‹ä¸�åŠ¨ï¼Œå­�å¼¹ä¹Ÿä¸�ä¼šåŠ¨
		
		if (enemies.get(1).bullet != null) {
			for (int i = 0; i < enemies.size(); i++) {
				// å› ä¸ºæ¯�æ¬¡é‡�ç»˜éƒ½é‡�æ–° bullet å¯¹è±¡ï¼Œæ‰€ä»¥åˆ›å»ºäº†éƒ½ä¸€ç›´æ¸…é™¤åŽŸæ�¥çš„æ•°æ�®
				graphics.fillRect(enemies.get(i).bullet.getxCoordinate(), enemies.get(i).bullet.getyCoordinate(), 3, 3);
				// System.out.println("@@@@"+enemies.get(i).bullet.getxCoordinate());
			}
		}
		
//		enemies.get(0).shoot();
		moveEnemyTanks();
	}

	public void drawTanks(int x, int y, Graphics graphics, int dirction, int type) {
		switch (type) {
		case 0:
			graphics.setColor(Color.cyan);
			break;
		case 1:
			graphics.setColor(Color.yellow);
			break;
		case 2:
			graphics.setColor(Color.red);
		default:
			break;
		}
		// åˆ¤æ–­æ–¹å�‘
		switch (dirction) {
		case 0:// å�‘ä¸Š
				// ç”»å‡ºæˆ‘çš„å�¦å…‹,two wheels
			graphics.fillRect(x, y, 5, 30);

			graphics.fillRect(x + 15, y, 5, 30);
			// body
			graphics.fillRect(x + 5, y + 5, 10, 20);
			// circle
			graphics.fillOval(x + 5, y + 10, 10, 10);
			// A line
			graphics.drawLine(x + 10, y + 15, x + 10, y);
			break;

		case 1:// å�‘å�³
			graphics.fillRect(x, y, 30, 5);
			graphics.fillRect(x, y + 15, 30, 5);
			graphics.fillRect(x + 5, y + 5, 20, 10);
			graphics.fillOval(x + 10, y + 5, 10, 10);
			graphics.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
		case 2:// å�‘ä¸‹
				// ç”»å‡ºæˆ‘çš„å�¦å…‹,two wheels
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
			// ç”»å‡ºæˆ‘çš„å�¦å…‹,two wheels
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
		if(e.getKeyCode() == KeyEvent.VK_E){
			
			
		}
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
	
	public void moveEnemyTanks(){
		Thread thread = new Thread(enemies.get(0));
		Thread thread2 = new Thread(enemies.get(1));
		Thread thread3 = new Thread(enemies.get(2));
		thread.start();
		thread2.start();
		thread3.start();
	}
}
