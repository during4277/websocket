package com.board.icia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.icia.bean.Member;
import com.board.icia.userClass.EncryptionEncoding;

//암호화 서비스 클래스
@Service
public class EncryptionService {
	@Autowired
	private EncryptionEncoding ee;
	
	public Member encodingMember(Member mb) throws Exception{
		mb.setPw(ee.TripleDesEncoding(mb.getPw()));
		mb.setMaddr(ee.TripleDesEncoding(mb.getMaddr()));
		//...개인식별정보는 모두 암호화 해야 됨..
		return mb;
	}
	public Member decodingMember(Member en_mb) throws Exception{
		en_mb.setPw(ee.TripleDesDecoding(en_mb.getPw()));
		en_mb.setMaddr(ee.TripleDesDecoding(en_mb.getMaddr()));
		//...개인식별정보는 모두 암호화 해야 됨..
		return en_mb;  //해독된 빈
	}
	public String encodingOne(String msg) throws Exception{
		return ee.TripleDesEncoding(msg);
	}
	public String decodingOne(String msg) throws Exception{
		return ee.TripleDesDecoding(msg);
	}
}
