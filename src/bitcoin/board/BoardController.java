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
		System.out.println("게시글 제목");
		b.setBoard_title(sc.next());
		System.out.println("게시글 내용");
		b.setBoard_write(sc.next());
		service.writeBoard(b);
		
	}

	public void edit(Scanner sc) {
		Board b = new Board();
		System.out.print("수정할 글번호:");
		b.setBoard_num(sc.nextInt());
		Board b2 = service.getBoard(b.getBoard_num());
		if (b2 == null) {
			System.out.println("없는 글번호");
			return;
		}
		if (b2.getCu_id().equals(CustomController.getLoginId())) {
			System.out.print("글 제목");
			b.setBoard_title(sc.next());
			System.out.print("글 내용");
			b.setBoard_write(sc.next());
			service.editBoard(b);
		} else {
			System.out.println("남의 글은 수정 불가");
		}
	}

	public void getBoard(Scanner sc, CommentController cmCont) {
		boolean flag = true;
		String str = "1.댓글등록 2.댓글보기  3.댓글수정  4.댓글삭제  0.돌아가기";
		String id;
		int menu;
		int num;
		
		System.out.print("검색할 글번호:");
		num = sc.nextInt();
		Board b = service.getBoard(num);
		if (b == null) {
			System.out.println("없는 글번호");
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
				System.out.println("댓글을 입력하세요");
				Comment cm = new Comment();
				cm.setCm_write(sc.next());
				cm.setBoard_num(num);
				cm.setCu_id(CustomController.getLoginId());
				
				
				cmCont.cmWrite(cm);
				break;
			case 2:
				cmCont.printAll(num);
			case 3:
				System.out.println("수정할 댓글의 번호를 입력하세요");
				Comment cm1 = new Comment();
				cm1.setCm_num(sc.nextInt());
				
				id = cmCont.selectCm_id(cm1.getCm_num());
				
				if(id.equals(CustomController.getLoginId())) {
					System.out.println("수정할 댓글 내용을 입력하세요");
					cm1.setCm_write(sc.next());
					cmCont.updateCommnet(cm1);
				} else {
					System.out.println("권한이 없습니다.");
				}
				break;
			case 4:
				System.out.println("삭제할 댓글의 번로를 입력하시오");
				Comment cm2 = new Comment();
				cm2.setCm_num(sc.nextInt());
				
				id = cmCont.selectCm_id(cm2.getCm_num());
				System.out.println(id);
				System.out.println(CustomController.getLoginId());
				if(id.equals(CustomController.getLoginId())) {
					cmCont.delComment(cm2);
				} else {
					System.out.println("권한이 없습니다.");
				}
				break;
			case 0:
				flag = false;
			}
		}
		
	}

	public void del(Scanner sc) {
		System.out.print("삭제할 글번호:");
		int num = sc.nextInt();
		Board b = service.getBoard(num);
		if (b == null) {
			System.out.println("없는 글번호");
			return;
		}
		if (b.getCu_id().equals(CustomController.getLoginId())) {
			service.delBoard(num);
		} else {
			System.out.println("남의 글은 삭제 불가");
		}
	}

	public void getAll() {
		ArrayList<Board> list = service.getAll();
		for (Board b : list) {
			System.out.println(b);
		}
	}

	public void getByWriter(Scanner sc) {
		System.out.print("검색할 작성자 아이디:");
		String writer = sc.next();
		ArrayList<Board> list = service.getByWriter(writer);
		for (Board b : list) {
			System.out.println(b);
		}
	}
}
