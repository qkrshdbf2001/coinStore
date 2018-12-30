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

	// 초기메뉴
	public void run(Scanner sc) {
		System.out.println("로그인 페이지입니다.");
		
		boolean flag = true;
		int admin;
		String str = "1.로그인  2.회원가입  0.종료";
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
				System.out.println("회원 가입 정보 입력");
				mCont.join(sc);
				break;
			case 0:
				mCont.logout();
				flag = false;
			}
		}
	}

	// 관리자 메뉴
	public void admin(Scanner sc, String[] coinName, int[] coinPrice) {
		System.out.println("관리자 페이지입니다.");
		
		boolean flag = true;
		String str = "1.회원  2.내정보  0.로그아웃";
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

	// 회원메뉴
	private void adminMem(Scanner sc) {
		System.out.println("관리자: 회원 페이지입니다..");
		boolean flag = true;
		String str = "1.회원추가  2.회원수정  3.회원조회  4.회원탈퇴  0.돌아가기";
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
		System.out.println("내정보 페이지 입니다.");
		boolean flag = true;
		String str = "1.내정보보기  2.패스워드변경  0.뒤로가기";
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
		System.out.println("고객 페이지 입니다.");
		boolean flag = true;
		String str = "1.정보  2.코인  3.탈퇴  4.회수  5.게시글  0.로그아웃";
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
		System.out.println("내정보입니다.");
		boolean flag = true;
		String str = "1.내정보보기  2.패스워드변경  3.충전하기  0.뒤로가기";
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
		System.out.println("코인 페이지 입니다.");
		String str = "1.코인목록  2.구매  3.판매  4.구매코인목록  0.뒤로가기";
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
		System.out.println("게시판 페이지입니다.");		
		String str = "1.글쓰기  2.글수정  3.글삭제  4.글보기  5.전체목록  6.작성자로검색  0.돌아가기";
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
