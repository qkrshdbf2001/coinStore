package bitcoin;

import java.util.Scanner;

import bitcoin.menu.Menu;

public class MultiThread extends Thread { // Thread 클래스를 상속
	String name;
	
	public MultiThread() {
		
	}

	public void run() {
		while(true) {
			Coin cn = new Coin();
			Menu menu = new Menu();
			menu.setCoinName(cn.coinName());
			menu.setCoinPrice(cn.coinPrice());
			try {
				sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


