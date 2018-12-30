package bitcoin.comment;

import java.util.ArrayList;


public class CommentController {
	private CommentService service;
	
	public CommentController() {
		
	}
	
	public CommentController(CommentService commentService) {
		this.service = commentService;
	}

	public void cmWrite(Comment cm) {
		service.cmWrite(cm);
	}

	public void printAll(int num) {
		ArrayList<Comment> list = service.cmPrintAll(num);
		for (Comment ct : list) {
			System.out.println(ct);
		}
	}

	public String selectCm_id(int Cm_Num) {
		return service.selectCm_id(Cm_Num);
	}

	public void updateCommnet(Comment cm1) {
		service.updateComment(cm1);
	}

	public void delComment(Comment cm2) {
		service.delComment(cm2);
	}
}
