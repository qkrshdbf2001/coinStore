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
				System.out.println("이미 존재하는 아이디입니다. 다시 입력해주세요(뒤로 돌아가기 exit)");
				continue;
			} 
			
			System.out.print("passwd:");
			ct.setCu_passwd(sc.next());
			System.out.print("name:");
			ct.setCu_name(sc.next());
			System.out.print("tel:");
			ct.setCu_phone(sc.next());
			System.out.println("회원정보를 모두 입력하였습니다.");
			service.join(ct);
			break;
		}
	}

	public void login(Scanner sc) {
		if (!loginId.equals("")) {
			System.out.println("이미 로그인 상태임");
			return;
		}
		Custom ct = new Custom();
		System.out.print("id:");
		ct.setCu_id(sc.next());
		System.out.print("passwd:");
		ct.setCu_passwd(sc.next());
		boolean flag = service.login(ct);
		if (flag) {
			System.out.println("로그인 성공");
			loginId = ct.getCu_id();
		} else {
			System.out.println("로그인 실패");
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
			System.out.print("새로운 비밀번호:");
			ct.setCu_passwd(sc.next());
			service.editMyInfo(ct);
		}
	}

	public void out(Scanner sc) {
		System.out.println("정말 탈퇴?yes,no");
		String str = sc.next();
		if (str.equals("yes")) {
			service.out(loginId);
			loginId = "";
			System.out.println("탈퇴 완료");
		} else {
			System.out.println("탈퇴 취소");
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
			System.out.println("수정할 회원의 아이디을 입력하세요.");
			Custom ct = new Custom();
			ct.setCu_id(sc.next());
			Custom m1 = service.getMyInfo(ct.getCu_id());
			System.out.println(m1);
			if(m1 == null) {
				System.out.println("존재하지않는 아이디입니다.");
				continue;
			}
			
			System.out.println("1.이름수정  2.비밀번호초기화  3.폰번호수정  0.돌아가기");
			
			while (!sc.hasNextInt()) {
				sc.next(); 
                System.err.print("에러! 숫자가 아닙니다. \n재 선택 : ");
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
				System.out.println("초기화되었습니다.");
				System.out.println("초기화 비밀번호는 000000 입니다.");
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
			System.out.println("삭제할 회원의 이름을 입력하세요.");
			Custom ct = new Custom();
			ct.setCu_name(sc.next());
			Custom m1 = service.getSelectN(ct.getCu_name());
			System.out.println(m1);
			if(m1 == null) {
				System.out.println("존재하지않는 아이디입니다.");
				continue;
			}
			
			service.deleteSUID(m1.getCu_id());
			break;
		}
	}

	public void editWallet(Scanner sc) {
		int wallet;
		int inputWallet;
			
		System.out.println("충전할 돈");
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
		
		System.out.println("회수할 돈");
		Custom ct = new Custom();
		wallet = service.getWallet(loginId);
		
		inputWallet = sc.nextInt();
		if(wallet < inputWallet) {
			System.out.println("원래 있던 돈보다 많은 돈을 입력하였습니다.");
		} else {
			ct.setWallet(wallet - inputWallet);
			ct.setCu_id(loginId);
			service.walletAdd(ct);
		}
	}
}
