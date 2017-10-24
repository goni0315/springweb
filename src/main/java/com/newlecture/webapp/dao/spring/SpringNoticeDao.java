package com.newlecture.webapp.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;

public class SpringNoticeDao implements NoticeDao {

	@Autowired
	private JdbcTemplate template;

	// 스프링이 제공해주는 트랜잭션매니져
	/*트랜잭션 1번 처리 방법	 * 
	 * @Autowired
	private PlatformTransactionManager transactionManager;*/

	/*
	 * @Autowired public void setTemplate(JdbcTemplate template) { this.template =
	 * template; }
	 */
	
	//트랜잭션 2번으로 했떤거 
	/*@Autowired
	private TransactionTemplate transactionTemplate;*/
	
	@Override
	public List<NoticeView> getList(int page, String field, String query) {

		String sql = "select * from NoticeView where " + field + " like ? order by regDate desc limit ?, 10";

		List<NoticeView> list = template.query(sql, new Object[] { "%" + query + "%", (page - 1) * 10 },
				BeanPropertyRowMapper.newInstance(NoticeView.class));

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
		// 스프링기능없이 무식한 방법으로 xml di 없이
		/*
		 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 * dataSource.setDriverClassName("com.mysql.jdbc.Driver"); dataSource.setUrl(
		 * "jdbc:mysql:211.238.142.247/newlecture?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8"
		 * ); dataSource.setUsername("sist"); dataSource.setPassword("cclass");
		 */

		/*
		 * JdbcTemplate template = new JdbcTemplate();
		 * template.setDataSource(dataSource);
		 */

		// id를 넘긴다 오브젝트 배열로 DB noticeview
		/*
		 * NoticeView notice = template.queryForObject(sql, new Object[] {id}, new
		 * RowMapper<NoticeView>() {
		 * 
		 * @Override public NoticeView mapRow(ResultSet rs, int rowNum) throws
		 * SQLException {
		 * 
		 * NoticeView notice = new NoticeView();
		 * 
		 * notice.setId(rs.getString("id"));
		 * notice.setTitle(rs.getString("title")+"메렁");
		 * notice.setWriterId(rs.getString("writerId"));
		 * notice.setContent(rs.getString("content")); notice.setHit(0);
		 * 
		 * return null; }
		 * 
		 * 
		 * });
		 */

		NoticeView notice = template.queryForObject(sql, new Object[] { id },
				BeanPropertyRowMapper.newInstance(NoticeView.class));

		return notice;
	}

	@Override
	public int update(String id, String title, String content) {

		String sql = "update Notice set title=?, content = ? where id = ?";

		int result = template.update(sql, title, content, id);

		/*
		 * 잘 안쓰는데 이것도 한가지 방법 int result = template.update(sql, new
		 * PreparedStatementSetter() {
		 * 
		 * @Override public void setValues(PreparedStatement st) throws SQLException {
		 * st.setString(1, title); st.setString(2, content); st.setString(3, id); }
		 * 
		 * });
		 */

		return result;

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

		return insert(new Notice(title, content, writerId));
	}

	
	
	//transaction 처리 방법 3번 4번 @Transactional 트랜잭션 필요한곳에 선언을 했네
	//aop으로 처리 하는 방법
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insert(Notice notice) {
		
		/*3번째 방법 aop를 이용함*/
		String sql = "insert into Notice(id, title, content, writerId) values(?, ?, ?, ?)";
		String sql1 = "update Member set point=point+1 where id = ?";

		
			int result = template.update(sql, getNextId(), notice.getTitle(), notice.getContent(),
					notice.getWriterId());

			result += template.update(sql1, notice.getWriterId());

			return result;
	
		
		/*2번 방법.
		 * String sql = "insert into Notice(id, title, content, writerId) values(?, ?, ?, ?)";
		String sql1 = "update Member set point=point+1 where id = ?";
		 
		int result = 0;
		result = (int) transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				template.update(sql, getNextId(), notice.getTitle(), notice.getContent(),
						notice.getWriterId());
				
				template.update(sql1, notice.getWriterId());
			
			}
			});*/
			
			

		/* 1번방법. transactionmanager를 직접 사용 하는 방법
		 * String sql = "insert into Notice(id, title, content, writerId) values(?, ?, ?, ?)";
		String sql1 = "update Member set point=point+1 where id = ?";

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus state = transactionManager.getTransaction(def);

		try {
			int result = template.update(sql, getNextId(), notice.getTitle(), notice.getContent(),
					notice.getWriterId());

			result += template.update(sql1, notice.getWriterId());
			transactionManager.commit(state);

			return result;

		} catch (Exception e) {
			transactionManager.rollback(state);
				throw e;
		}*/

	}

	@Override
	public String getNextId() {

		String sql = "select ifnull(max(cast(id as unsigned)), 0) +1 from Notice";

		String result = template.queryForObject(sql, String.class);

		return result;
	}

}
