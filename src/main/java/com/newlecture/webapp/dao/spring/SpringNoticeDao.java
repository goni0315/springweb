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

	// �������� �������ִ� Ʈ����ǸŴ���
	/*Ʈ����� 1�� ó�� ���	 * 
	 * @Autowired
	private PlatformTransactionManager transactionManager;*/

	/*
	 * @Autowired public void setTemplate(JdbcTemplate template) { this.template =
	 * template; }
	 */
	
	//Ʈ����� 2������ �߶��� 
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
		// ��������ɾ��� ������ ������� xml di ����
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

		// id�� �ѱ�� ������Ʈ �迭�� DB noticeview
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
		 * notice.setTitle(rs.getString("title")+"�޷�");
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
		 * �� �Ⱦ��µ� �̰͵� �Ѱ��� ��� int result = template.update(sql, new
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

	
	
	//transaction ó�� ��� 3�� 4�� @Transactional Ʈ����� �ʿ��Ѱ��� ������ �߳�
	//aop���� ó�� �ϴ� ���
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insert(Notice notice) {
		
		/*3��° ��� aop�� �̿���*/
		String sql = "insert into Notice(id, title, content, writerId) values(?, ?, ?, ?)";
		String sql1 = "update Member set point=point+1 where id = ?";

		
			int result = template.update(sql, getNextId(), notice.getTitle(), notice.getContent(),
					notice.getWriterId());

			result += template.update(sql1, notice.getWriterId());

			return result;
	
		
		/*2�� ���.
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
			
			

		/* 1�����. transactionmanager�� ���� ��� �ϴ� ���
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
