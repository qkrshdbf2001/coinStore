package bitcoin.coin;

import java.util.ArrayList;
import java.util.Scanner;

import bitcoin.custom.Custom;
import bitcoin.custom.CustomController;
import bitcoin.custom.CustomService;

public class CoinController {
	private CoinService service;
	private CustomService ctService;
	
	public CoinController() {
		
	}
	
	public CoinController(CoinService coinService, CustomService customService) {
		this.service = coinService;
		this.ctService = customService;
	}

	public void coinSelect(String[] coinName, int[] coinPrice) {
		System.out.println("����ȭ����");
		
		for(int i=0; i<coinName.length; i++) {
			System.out.print(i + 1 + ". " + coinName[i] + " ");
			System.out.println(coinPrice[i]);
		}
	}

	public void coinAdd(Scanner sc, String[] coinName, int[] coinPrice) {
		int select;
		int ctWallet;
		String cu_id;
		float wallet;
		boolean flag=true;
		
		Coin coin = new Coin();
		Custom ct = new Custom();
		
		CustomController ctCont = new CustomController();
		
		cu_id = ctCont.getLoginId();
		ct.setCu_id(cu_id);
		ctWallet = ctService.getWallet(cu_id);
		coin.setCu_id(cu_id);
		
		System.out.println("����ȭ�� ����������");
		
		while(flag) {
			System.out.println("������ ������ ��ȣ�� �Է��ϼ���(����� 0 �Դϴ�)");
			select = sc.nextInt();
			
			if(select == 0) {
				System.out.println("���� ����");
				flag = false;
				
			}
			coin.setCoin_name(coinName[select]);
			System.out.println("������ ���� �Է��Ͻÿ�");
			wallet = sc.nextInt();
			
			if (ctWallet < wallet) {
				System.out.println("�ڱ�� �ִ� ��꺸�� ������ ���� �����ϴ�. ���� �� �̿����ּ���");
				continue;
				
			} else {
				ct.setWallet((int)(ctWallet - wallet));
				ctService.subWallet(ct);
				coin.setCoin_wallet((float)(wallet/coinPrice[select]));
				System.out.println(coin.getCoin_wallet());
				
				service.insertCoin(coin);
				break;
			}
		}
	}

	public void coinSelectList(Scanner sc, String[] coinName, int[] coinPrice) {
		CustomController ctCont = new CustomController();
		
		ArrayList<Coin> list = service.getList(ctCont.getLoginId());
		
		float price = 0;
		
		for (Coin ct : list) {
			for(int i=0; i<coinName.length; i++) {
				
				if (ct.getCoin_name().equals(coinName[i])) {
					price = (float) (coinPrice[i]*ct.getCoin_wallet());
				}
			}
			System.out.println(ct+ " �� �ݾ� -> " + price + "��");
		}
		
	}

	public void sellCoin(Scanner sc, String[] coinName, int[] coinPrice) {
		CustomController ctCont = new CustomController();
		ArrayList<Coin> list = service.getList(ctCont.getLoginId());
		Custom ct1 = new Custom();
		Coin coin1 = new Coin();
		
		String id = ctCont.getLoginId();
		int select;
		int check=0;
		int sellCoin_Num = 0;
		float sellCoin_Price = 0;
		String sellCoin_Name = "";
		float totalPrice = 0;
		int userPrice;
		
		System.out.println("�Ǹ��� ������ ��ȣ�� �Է��ϼ���");
		select = sc.nextInt();
		
		for (Coin ct : list) {
			
			if(ct.getCoin_num() == select) {
				sellCoin_Num = ct.getCoin_num();
				sellCoin_Price = ct.getCoin_wallet();
				sellCoin_Name = ct.getCoin_name();
				check++;
			}
			
			for(int i=0; i<coinName.length; i++) {
			
				if (sellCoin_Name.equals(coinName[i])) {
					totalPrice = (float) (coinPrice[i]*sellCoin_Price);
				}
			}
		}
		
		if (check == 0) {
			System.out.println("�Ǹ��� ������ �����ϴ�.");
		} else {
			userPrice = ctService.getWallet(id);
			
			ct1.setCu_id(id);
			ct1.setWallet(userPrice + (int)totalPrice);
			ctService.walletAdd(ct1);
			
			service.remove(sellCoin_Num);
		}
		System.out.println(sellCoin_Num + " " + sellCoin_Price + " " + sellCoin_Name + " " + totalPrice);
	}
}
