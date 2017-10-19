package com.newlecture.webapp.dao.spring;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;

public class SpringNoticeDao implements NoticeDao {
	
	private JdbcTemplate template;
	
	

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<NoticeView> getList(int page, String field, String query) {

		String sql = "select * from NoticeView where "+field+" like ? order by regDate desc limit ?, 10";
		
		List<NoticeView> list = template.query(sql, new Object[] {"%"+query+"%", (page-1)*10}, BeanPropertyRowMapper.newInstance(NoticeView.class));
		
		return list;
	}

	@Override
	public int getCount() {
		
		String sql = "select count(id) `count` from Notice";
		
		int count = template.queryForObject(sql, Integer.class);
		
		
		return count;
	}

	@Override
	public NoticeView get(String id) {

		String sql = "select * from Notice where id = ?";
		//스프링기능없이 무식한 방법으로 xml di 없이
	/*	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:211.238.142.247/newlecture?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("sist");
		dataSource.setPassword("cclass");*/
		
/*		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);*/

		//id를 넘긴다 오브젝트 배열로 DB noticeview 
			/*	NoticeView notice = template.queryForObject(sql, new Object[] {id}, new RowMapper<NoticeView>()  {

					@Override
					public NoticeView mapRow(ResultSet rs, int rowNum) throws SQLException {

						NoticeView notice = new NoticeView();
						
						notice.setId(rs.getString("id"));
						notice.setTitle(rs.getString("title")+"메렁");
						notice.setWriterId(rs.getString("writerId"));
						notice.setContent(rs.getString("content"));
						notice.setHit(0);
						
						return null;
					}
					
					
				});*/
		
		NoticeView notice = template.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(NoticeView.class));
				
		return notice;
	}

	@Override
	public int update(String id, String title, String content) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NoticeView getPrev(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticeView getNext(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(String title, String content, String writerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNextId() {
		// TODO Auto-generated method stub
		return null;
	}

}
