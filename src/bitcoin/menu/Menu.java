package bitcoin.menu;

import java.util.Scanner;

import bitcoin.board.BoardController;
import bitcoin.board.BoardDaoImpl;
import bitcoin.board.BoardServiceImpl;
import bitcoin.coin.CoinController;
import bitcoin.coin.CoinDaoImpl;
import bitcoin.coin.CoinServiceImpl;
import bitcoin.comment.CommentController;
import bitcoin.comment.CommentDaoImpl;
import bitcoin.comment.CommentServiceImpl;
import bitcoin.custom.CustomController;
import bitcoin.custom.CustomDaoImpl;
import bitcoin.custom.CustomServiceImpl;

public class Menu {
	private CustomController mCont;
	private BoardController bCont;
	private CoinController cCont;
	private CommentController cmCont;
	
	public static String[] coinName;
	public static int[] coinPrice;

	public Menu() {
		mCont = new CustomController(new CustomServiceImpl(new CustomDaoImpl()));
		bCont = new BoardController(new BoardServiceImpl(new BoardDaoImpl()));
		cCont = new CoinController(new CoinServiceImpl(new CoinDaoImpl()),new CustomServiceImpl(new CustomDaoImpl()));
		cmCont = new CommentController(new CommentServiceImpl(new CommentDaoImpl()));
	}
	
	public void setCoinName(String[] coinName) {
		this.coinName = coinName;
	}

	public void setCoinPrice(int[] coinPrice) {
		this.coinPrice = coinPrice;
	}

	// �ʱ�޴�
	public void run(Scanner sc) {
		System.out.println("�α��� �������Դϴ�.");
		
		boolean flag = true;
		int admin;
		String str = "1.�α���  2.ȸ������  0.����";
		int menu;

		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				mCont.login(sc);
				admin = mCont.adminCheck();
				if (admin == 1) {
					admin(sc, coinName, coinPrice);
				} else if (admin == 2) {
					custom(sc, coinName, coinPrice);
				} else {
					System.out.println("...");
				}
				break;
			case 2:
				System.out.println("ȸ�� ���� ���� �Է�");
				mCont.join(sc);
				break;
			case 0:
				mCont.logout();
				flag = false;
			}
		}
	}

	// ������ �޴�
	public void admin(Scanner sc, String[] coinName, int[] coinPrice) {
		System.out.println("������ �������Դϴ�.");
		
		boolean flag = true;
		String str = "1.ȸ��  2.������  0.�α׾ƿ�";
		int menu;

		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				adminMem(sc);
				break;
			case 2:
				adminMy(sc);
				break;
			case 0:
				mCont.logout();
				flag = false;
			}
		}
	}

	// ȸ���޴�
	private void adminMem(Scanner sc) {
		System.out.println("������: ȸ�� �������Դϴ�..");
		boolean flag = true;
		String str = "1.ȸ���߰�  2.ȸ������  3.ȸ����ȸ  4.ȸ��Ż��  0.���ư���";
		int menu;

		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				mCont.join(sc);
				break;
			case 2:
				mCont.updateSuper(sc);
				break;
			case 3:
				mCont.printList();
				break;
			case 4:
				mCont.deleteSuper(sc);
				break;
			case 0:
				mCont.logout();
				flag = false;
			}
		}
	}

	private void adminMy(Scanner sc) {
		System.out.println("������ ������ �Դϴ�.");
		boolean flag = true;
		String str = "1.����������  2.�н����庯��  0.�ڷΰ���";
		int menu;

		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				mCont.myInfo();
				break;
			case 2:
				mCont.editMyInfo(sc);
				break;
			case 0:
				flag = false;
			}
		}
	}

	public void custom(Scanner sc, String[] coinName, int[] coinPrice) {
		System.out.println("�� ������ �Դϴ�.");
		boolean flag = true;
		String str = "1.����  2.����  3.Ż��  4.ȸ��  5.�Խñ�  0.�α׾ƿ�";
		int menu;

		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				userMem(sc);
				break;
			case 2:
				userCoin(sc);
				break;
			case 3:
				mCont.out(sc);
				break;
			case 4:
				mCont.subWallet(sc);
			case 5:
				userBoard(sc);
			case 0:
				flag = false;
			}
		}
	}

	private void userMem(Scanner sc) {
		System.out.println("�������Դϴ�.");
		boolean flag = true;
		String str = "1.����������  2.�н����庯��  3.�����ϱ�  0.�ڷΰ���";
		int menu;

		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				mCont.myInfo();
				break;
			case 2:
				mCont.editMyInfo(sc);
				break;
			case 3:
				mCont.editWallet(sc);
			case 0:
				flag = false;
			}
		}
	}
	
	private void userCoin(Scanner sc) {
		System.out.println("���� ������ �Դϴ�.");
		String str = "1.���θ��  2.����  3.�Ǹ�  4.�������θ��  0.�ڷΰ���";
		int menu;
		boolean flag = true;
		
		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				cCont.coinSelect(coinName, coinPrice);
				break;
			case 2:
				cCont.coinAdd(sc, coinName, coinPrice);
				break;
			case 3:
				cCont.coinSelectList(sc, coinName, coinPrice);
				cCont.sellCoin(sc, coinName, coinPrice);
				break;
			case 4:
				cCont.coinSelectList(sc, coinName, coinPrice);
				break;
			case 0:
				flag = false;
			}
		}
	}
	
	private void userBoard(Scanner sc) {
		System.out.println("�Խ��� �������Դϴ�.");		
		String str = "1.�۾���  2.�ۼ���  3.�ۻ���  4.�ۺ���  5.��ü���  6.�ۼ��ڷΰ˻�  0.���ư���";
		int menu;
		boolean flag = true;
		
		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				bCont.write(sc);
				break;
			case 2:
				bCont.edit(sc);
				break;
			case 3:
				bCont.del(sc);
				break;
			case 4:
				bCont.getBoard(sc, cmCont);
				break;
			case 5:
				bCont.getAll();
				break;
			case 6:
				bCont.getByWriter(sc);
				break;
			case 0:
				flag = false;
			}
		}
	}
}
