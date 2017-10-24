package com.newlecture.webapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;

//boardcontroller�� ���� ������ ����
//��Ʈ�ѷ����� dao > db �̷��� �ߴµ�
//Ʈ����� �������ؼ� �߰��� ������ �ϳ��� �����
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

	//Ʈ����� ����
	//Ʈ����Ǿȿ� Ʈ����� ����Ʈ�� 2�� ������
	//�̰��� �� ����� �ϴ°� ? ����� ���
	//http://springsource.tistory.com/136 ����
	
	//REQUIRES ����Ʈ�� Ʈ����� �����ϰڴ�
	//REQUIRES_NEW ����Ʈ�� Ʈ����� ����x
	//�ɼ� ���������� ã��
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
