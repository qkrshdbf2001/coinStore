package bitcoin.board;

import java.util.ArrayList;
import java.util.Scanner;

import bitcoin.comment.Comment;
import bitcoin.comment.CommentController;
import bitcoin.comment.CommentDaoImpl;
import bitcoin.comment.CommentService;
import bitcoin.comment.CommentServiceImpl;
import bitcoin.custom.CustomController;

public class BoardController {
	private BoardService service;
	
	
	public BoardController() {
		
	}
	
	public BoardController(BoardService boardService) {
		this.service = boardService;
	}

	public void write(Scanner sc) {
		Board b = new Board();
		b.setCu_id(CustomController.getLoginId());
		System.out.println("�Խñ� ����");
		b.setBoard_title(sc.next());
		System.out.println("�Խñ� ����");
		b.setBoard_write(sc.next());
		service.writeBoard(b);
		
	}

	public void edit(Scanner sc) {
		Board b = new Board();
		System.out.print("������ �۹�ȣ:");
		b.setBoard_num(sc.nextInt());
		Board b2 = service.getBoard(b.getBoard_num());
		if (b2 == null) {
			System.out.println("���� �۹�ȣ");
			return;
		}
		if (b2.getCu_id().equals(CustomController.getLoginId())) {
			System.out.print("�� ����");
			b.setBoard_title(sc.next());
			System.out.print("�� ����");
			b.setBoard_write(sc.next());
			service.editBoard(b);
		} else {
			System.out.println("���� ���� ���� �Ұ�");
		}
	}

	public void getBoard(Scanner sc, CommentController cmCont) {
		boolean flag = true;
		String str = "1.��۵�� 2.��ۺ���  3.��ۼ���  4.��ۻ���  0.���ư���";
		String id;
		int menu;
		int num;
		
		System.out.print("�˻��� �۹�ȣ:");
		num = sc.nextInt();
		Board b = service.getBoard(num);
		if (b == null) {
			System.out.println("���� �۹�ȣ");
		} else {
			System.out.println(b);
		}
		
		
		
		while (flag) {
			if (b == null) {
				break;
			}
			
			System.out.println(str);
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("����� �Է��ϼ���");
				Comment cm = new Comment();
				cm.setCm_write(sc.next());
				cm.setBoard_num(num);
				cm.setCu_id(CustomController.getLoginId());
				
				
				cmCont.cmWrite(cm);
				break;
			case 2:
				cmCont.printAll(num);
			case 3:
				System.out.println("������ ����� ��ȣ�� �Է��ϼ���");
				Comment cm1 = new Comment();
				cm1.setCm_num(sc.nextInt());
				
				id = cmCont.selectCm_id(cm1.getCm_num());
				
				if(id.equals(CustomController.getLoginId())) {
					System.out.println("������ ��� ������ �Է��ϼ���");
					cm1.setCm_write(sc.next());
					cmCont.updateCommnet(cm1);
				} else {
					System.out.println("������ �����ϴ�.");
				}
				break;
			case 4:
				System.out.println("������ ����� ���θ� �Է��Ͻÿ�");
				Comment cm2 = new Comment();
				cm2.setCm_num(sc.nextInt());
				
				id = cmCont.selectCm_id(cm2.getCm_num());
				System.out.println(id);
				System.out.println(CustomController.getLoginId());
				if(id.equals(CustomController.getLoginId())) {
					cmCont.delComment(cm2);
				} else {
					System.out.println("������ �����ϴ�.");
				}
				break;
			case 0:
				flag = false;
			}
		}
		
	}

	public void del(Scanner sc) {
		System.out.print("������ �۹�ȣ:");
		int num = sc.nextInt();
		Board b = service.getBoard(num);
		if (b == null) {
			System.out.println("���� �۹�ȣ");
			return;
		}
		if (b.getCu_id().equals(CustomController.getLoginId())) {
			service.delBoard(num);
		} else {
			System.out.println("���� ���� ���� �Ұ�");
		}
	}

	public void getAll() {
		ArrayList<Board> list = service.getAll();
		for (Board b : list) {
			System.out.println(b);
		}
	}

	public void getByWriter(Scanner sc) {
		System.out.print("�˻��� �ۼ��� ���̵�:");
		String writer = sc.next();
		ArrayList<Board> list = service.getByWriter(writer);
		for (Board b : list) {
			System.out.println(b);
		}
	}
}
