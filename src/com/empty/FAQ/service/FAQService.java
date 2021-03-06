package com.empty.FAQ.service;

import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.getConnection;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.rollback;


import java.sql.Connection;
import java.util.List;

import com.empty.FAQ.model.dao.FAQDao;
import com.empty.FAQ.model.vo.FAQ;

public class FAQService {
	
	private FAQDao dao=new FAQDao();
	
	public int insertFAQ(FAQ f) {
		Connection conn=getConnection();
		int result=dao.insertFAQ(conn,f);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<FAQ> searchFAQ(int cPage,int numPerPage){
		Connection conn=getConnection();
		List<FAQ> list=dao.searchFAQ(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public int FAQCount() {
		Connection conn=getConnection();
		int result=dao.FAQCount(conn);
		close(conn);
		return result;
	}
	
	public FAQ selectFAQ(int no,boolean hasRead) {
		Connection conn=getConnection();
		FAQ f=dao.selectFAQ(conn,no,hasRead);
		if(f!=null&&!hasRead) {
			int result=dao.updateCount(conn, no);
			if(result>0) {
				f.setCount(dao.selectFAQ(conn, no,hasRead).getCount());
				commit(conn);
			}
			else rollback(conn);
		}
		close(conn);
		return f;
	}
	
	public int updateFAQ(int no,String title,String content) {
		Connection conn=getConnection();
		int result=dao.updateFAQ(conn,no, title,content);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public FAQ selectFAQ(int no) {
		Connection conn=getConnection();
		FAQ f=dao.selectFAQ(conn, no);
		close(conn);
		return f;
		
	}
	
	public int deleteFAQ(int no) {
		Connection conn=getConnection();
		int result=dao.deleteFAQ(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
