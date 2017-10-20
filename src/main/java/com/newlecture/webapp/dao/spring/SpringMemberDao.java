package com.newlecture.webapp.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.entity.Member;

public class SpringMemberDao implements MemberDao {
	
	@Autowired
	private JdbcTemplate template;


	@Override
	public int pointUp(String id) {
		
		String sql = "update Member set point=point+1 where id = ?";
		
		int result = template.update(sql, id);
				
		return result;
		
		//트랜잭션 적용하기 글을 써야 포인트가 적립된다
	}


	@Override
	public int insert(String id, String pwd, String name, String gender, String birthday, String phone, String email,
			int point) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Member get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
