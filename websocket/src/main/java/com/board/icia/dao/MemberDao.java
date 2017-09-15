package com.board.icia.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.icia.bean.Member;
@Repository  //@Component
public class MemberDao { //쿼리문 ---->마이바이티스.XML
	//ibatis==>mybatis==>DB프레임워크
	@Autowired
	private SqlSession sqlSession;
	public int getLoginResult(Member mb){
		return sqlSession.selectOne("Member.getLoginResult",mb);
	}
	public int memberInsert(Member mb) {
		return sqlSession.insert("Member.memberInsert",mb); 
	}
	public Member getMemberInfo(String id) {
		return sqlSession.selectOne("Member.getMemberInfo",id);
	}	
}//end
