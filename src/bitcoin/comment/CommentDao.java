package bitcoin.comment;

import java.util.ArrayList;

public interface CommentDao {

	void cmInsert(Comment cm);

	ArrayList<Comment> selectAll(int num);

	String selectCm_id(int Cm_Num);

	void updateComment(Comment cm1);

	void delComment(Comment cm2);

}
