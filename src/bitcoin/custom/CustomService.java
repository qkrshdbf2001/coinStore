package bitcoin.custom;

import java.util.ArrayList;

public interface CustomService {
	void join(Custom ct);

	boolean login(Custom ct);

	Custom getMyInfo(String id);

	Custom getSelectN(String name);
	
	void editMyInfo(Custom m);

	void out(String id);
	
	ArrayList<Custom> getList();

	//어드민권한 수정
	void updateSUID(Custom ct);
	void updateSUPW(Custom ct);
	void updateSUPH(Custom ct);
	
	//어드민권한 삭제
	void deleteSUID(String cu_id);

	//돈을 추가합니다.
	void walletAdd(Custom ct);

	//가지고 있는 돈을 가져옵니다.
	int getWallet(String cu_id);

	//돈빼기
	void subWallet(Custom ct);
}
