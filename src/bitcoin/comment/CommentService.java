package bitcoin.comment;

import java.util.ArrayList;

public interface CommentService {

	void cmWrite(Comment cm);

	ArrayList<Comment> cmPrintAll(int num);

	String selectCm_id(int board_num);

	void updateComment(Comment cm1);

	void delComment(Comment cm2);

}
