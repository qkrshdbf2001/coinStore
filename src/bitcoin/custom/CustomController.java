package bitcoin.custom;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomController {
	private CustomService service;
	private static String loginId = "";

	public CustomController() {
		
	}
	
	public CustomController(CustomService customService) {
		this.service = customService;
	}
	
	public static String getLoginId() {
		return loginId;
	}

	public int adminCheck() {
		Custom ct = service.getMyInfo(loginId);
		if (ct == null) {
			return 3;
		}
		if (ct.getCu_id().equals("admin")) {
			return 1;
		}
		return 2;
	}
	
	public void join(Scanner sc) {
		int check = 1;
		Custom ct = new Custom();
		
		while (check == 1) {
			System.out.print("id:");
			ct.setCu_id(sc.next());
			Custom m1 = service.getMyInfo(ct.getCu_id());
			
			if (ct.getCu_id().equals("exit")) {
				break;
			} else if (m1 != null) {
				System.out.println("�̹� �����ϴ� ���̵��Դϴ�. �ٽ� �Է����ּ���(�ڷ� ���ư��� exit)");
				continue;
			} 
			
			System.out.print("passwd:");
			ct.setCu_passwd(sc.next());
			System.out.print("name:");
			ct.setCu_name(sc.next());
			System.out.print("tel:");
			ct.setCu_phone(sc.next());
			System.out.println("ȸ�������� ��� �Է��Ͽ����ϴ�.");
			service.join(ct);
			break;
		}
	}

	public void login(Scanner sc) {
		if (!loginId.equals("")) {
			System.out.println("�̹� �α��� ������");
			return;
		}
		Custom ct = new Custom();
		System.out.print("id:");
		ct.setCu_id(sc.next());
		System.out.print("passwd:");
		ct.setCu_passwd(sc.next());
		boolean flag = service.login(ct);
		if (flag) {
			System.out.println("�α��� ����");
			loginId = ct.getCu_id();
		} else {
			System.out.println("�α��� ����");
		}
	}

	public void logout() {
		loginId = "";
	}

	public boolean myInfo() {
		Custom ct = service.getMyInfo(loginId);
		System.out.println(ct);
		return true;
	}

	public void editMyInfo(Scanner sc) {
		boolean flag = myInfo();
		if (flag) {
			Custom ct = new Custom(loginId, null, null, null, null, 0);
			System.out.print("���ο� ��й�ȣ:");
			ct.setCu_passwd(sc.next());
			service.editMyInfo(ct);
		}
	}

	public void out(Scanner sc) {
		System.out.println("���� Ż��?yes,no");
		String str = sc.next();
		if (str.equals("yes")) {
			service.out(loginId);
			loginId = "";
			System.out.println("Ż�� �Ϸ�");
		} else {
			System.out.println("Ż�� ���");
		}
	}

	public void printList() {
		ArrayList<Custom> list = service.getList();
		for (Custom ct : list) {
			System.out.println(ct);
		}
	}

	public void updateSuper(Scanner sc) {
		boolean flag = true;
		int select;
		
		while (flag) {
			System.out.println("������ ȸ���� ���̵��� �Է��ϼ���.");
			Custom ct = new Custom();
			ct.setCu_id(sc.next());
			Custom m1 = service.getMyInfo(ct.getCu_id());
			System.out.println(m1);
			if(m1 == null) {
				System.out.println("���������ʴ� ���̵��Դϴ�.");
				continue;
			}
			
			System.out.println("1.�̸�����  2.��й�ȣ�ʱ�ȭ  3.����ȣ����  0.���ư���");
			
			while (!sc.hasNextInt()) {
				sc.next(); 
                System.err.print("����! ���ڰ� �ƴմϴ�. \n�� ���� : ");
			}
			select = sc.nextInt();
			
			if (select == 0) {
				break;
			}
			
			switch (select) {
			case 1:
				System.out.print("name :");
				ct.setCu_name(sc.next());
				service.updateSUID(ct);
				break;
			case 2:
				System.out.println("�ʱ�ȭ�Ǿ����ϴ�.");
				System.out.println("�ʱ�ȭ ��й�ȣ�� 000000 �Դϴ�.");
				service.updateSUPW(ct);
				break;
			case 3:
				System.out.print("phone :");
				ct.setCu_phone(sc.next());
				service.updateSUPH(ct);
				break;
			}
			break;
		}
	}

	public void deleteSuper(Scanner sc) {
		boolean flag = true;
		
		while (flag) {
			System.out.println("������ ȸ���� �̸��� �Է��ϼ���.");
			Custom ct = new Custom();
			ct.setCu_name(sc.next());
			Custom m1 = service.getSelectN(ct.getCu_name());
			System.out.println(m1);
			if(m1 == null) {
				System.out.println("���������ʴ� ���̵��Դϴ�.");
				continue;
			}
			
			service.deleteSUID(m1.getCu_id());
			break;
		}
	}

	public void editWallet(Scanner sc) {
		int wallet;
		int inputWallet;
			
		System.out.println("������ ��");
		Custom ct = new Custom();
		wallet = service.getWallet(loginId);
		
		inputWallet = sc.nextInt();
		
		ct.setWallet(wallet + inputWallet);
		ct.setCu_id(loginId);
		service.walletAdd(ct);
			
	}

	public void subWallet(Scanner sc) {
		int wallet;
		int inputWallet;
		
		System.out.println("ȸ���� ��");
		Custom ct = new Custom();
		wallet = service.getWallet(loginId);
		
		inputWallet = sc.nextInt();
		if(wallet < inputWallet) {
			System.out.println("���� �ִ� ������ ���� ���� �Է��Ͽ����ϴ�.");
		} else {
			ct.setWallet(wallet - inputWallet);
			ct.setCu_id(loginId);
			service.walletAdd(ct);
		}
	}
}
