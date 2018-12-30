package bitcoin.custom;

import java.util.ArrayList;

public interface CustomDao {
	void insertCustom(Custom ct);
	
	void updateCustom(Custom ct);
	
	void deleteCustom(String id);
	
	Custom select(String id);
	
	//이름으로 검색
	Custom selectN(String name);
	
	ArrayList<Custom> selectAll();

	//어드민권한 수정
	void updateSUID(Custom ct);
	void updateSUPW(Custom ct);
	void updateSUPH(Custom ct);
	
	//어드민권한 삭제
	void deleteSU(String cu_id);

	//돈 추가
	void walletUpdate(Custom ct);

	//돈 가져오기
	int getWallet(String cu_id);
}
