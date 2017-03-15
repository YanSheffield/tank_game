package com.demo1;

public class MultipleThread {

	public static void main(String[] args) {
		Cat cat = new Cat();
		Thread thread = new Thread(cat);
		thread.start();
		
		Dog dog = new Dog();
		dog.start();
	}

}

class Cat implements Runnable{
	int times = 0;
	public void run(){
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("HelloCat");
			times++;
			if (times==10) {
				break;
			}
		}
	}
}


class Dog extends Thread{
	int times = 0;
	public void run(){
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("HelloDog");
			times++;
			if (times==10) {
				break;
			}
		}
	}
}
