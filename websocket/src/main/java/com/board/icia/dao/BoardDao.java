package com.board.icia.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.board.icia.bean.Board;
import com.board.icia.bean.Reply;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	//@Autowired //@Resource("transact.....")
	//private DataSourceTransactionManager transactionManager;
	
	//트랜잭션 어노테이션으로 이용
	@Transactional //호출하는 메소드는 모두 public이어야 한다.
	               //모든 메소드호출이 성공되면 자동으로 commit, 아니면 rollback 된다.
	public int boardDelete(int bnum){
		int r=replyListDelete(bnum); 
		int b=articleDelete(bnum);
		if(r!=0 && b!=0)
			return 1; //서비스로 성공리턴
		return 0; //서비스로 실패리턴
	}
	private int articleDelete(int bnum) {
		return sqlSession.delete("Board.articleDelete",bnum);
	}
	private int replyListDelete(int bnum) {
		return sqlSession.delete("Board.replyListDelete",bnum);
	}

	public List<Board> getBoardList(int pageNum) {
		return sqlSession.selectList("Board.getBoardList",pageNum);
	}

	public Board getContents(int bnum) {
		return sqlSession.selectOne("Board.getContents",bnum);
	}

	public List<Reply> getReplyList(int bnum) {
		return sqlSession.selectList("Board.getReplyList",bnum);
	}

	public int replyInsert(Reply reply) {
		return sqlSession.insert("Board.replyInsert",reply);
	}
	public int getReplyMaxNum() {
		return sqlSession.selectOne("Board.getReplyMaxNum");
	}

	public int getBoardCount() {
		return sqlSession.selectOne("Board.getBoardCount");
	}
	@Transactional
	public int boardWrite(Board board, Map<String, String> fMap) {
		int b=boardInsert(board); 
		int bnum=getBoardMaxNum();
		fMap.put("bnum",String.valueOf(bnum));
		int f=fileInsert(fMap);
		if(b!=0 && f!=0)
			return 1; //tx 성공 commit
		return 0; //tx 실패 rollback처리됨
	}
	private int getBoardMaxNum() {
		return sqlSession.selectOne("Board.getBoardMaxNum");
	}
	public int boardInsert(Board board) {
		return sqlSession.insert("Board.boardInsert",board);//,contents,id);
	}
	private int fileInsert(Map<String, String> fMap) {
		return sqlSession.insert("Board.fileInsert",fMap);
	}
	
}
