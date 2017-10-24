package com.newlecture.webapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;

//boardcontroller를 위한 데이터 제공
//컨트롤러에서 dao > db 이렇게 했는데
//트랜잭션 전파위해서 중간에 계층을 하나더 만들었
//
public class BoardService {
	
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private NoticeFileDao noticeFileDao;
	
	

	public List<NoticeView> getNoticeList() {

		return noticeDao.getList(1, "title", "");		

	}

	public List<NoticeView> getNoticeList(String field, String query) {

		return null;

	}

	//트랜잭션 전파
	//트랜잭션안에 트랜잭션 포인트가 2번 오른다
	//이것을 다 묶어야 하는가 ? 방법은 어떻게
	//http://springsource.tistory.com/136 참고
	
	//REQUIRES 디폴트값 트랜잭션 참여하겠다
	//REQUIRES_NEW 디폴트값 트랜잭션 참여x
	//옵션 여러개있음 찾아
	@Transactional
	public int insertAndPointUp(Notice notice) {
		noticeDao.insert(notice);
		memberDao.pointUp(notice.getWriterId());
		return 0;
		
		
	
	}

	public Object getNotice(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getNoticeNext(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNextId() {
		// TODO Auto-generated method stub
		return null;
	}

}
