package bitcoin.main;

import java.util.Scanner;

import bitcoin.Coin;
import bitcoin.MultiThread;
import bitcoin.menu.Menu;

class Main {
	public static Coin cn = new Coin();
	
	public static void main(String[] args) {
		
		int select;
		Scanner sc = new Scanner(System.in);
		MultiThread th = new MultiThread();
		
		//�ǽð����� ������ ������ �ֽ��ϴ�.
		th.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Menu menu = new Menu();
		menu.run(sc);

	}
}
