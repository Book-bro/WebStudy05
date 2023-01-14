package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data	
@EqualsAndHashCode(of="boNo")	//연관관계가 복잡해지면 오버플로우가 발생함으로 id값 설정해줌
@ToString(exclude= {"boContent", "boPass"}) 	//boContent, boPass를 제외하고 출력하겠다, 리스트라 많아짐
public class BoardVO implements Serializable{  //객체 VO를 통신으로 넘기고 싶다면 직렬화를 하고 전송해야함
	private int rnum;				//행 번호
	private Integer boNo;			//글번호
	private String boTitle;			//제목
	private String boWriter;		//작성자
	private String boIp;			//아이피
	private String boMail;			//이메일
	
	@JsonIgnore //마샬링되어 데이터가 들어가므로 jsonignore붙여서 제외시킴, 비밀번호는 보여지면 안되기 때문
	private transient String boPass;//비밀번호, transient : 직렬화에서 제외
	
	private String boContent;		//내용
	private String boDate;			//작성일
	private Integer boHit;			//조회수
	
	private List<AttatchVO> attatchList; //has many 관계 설정하기 위해 생성
	
	private int[] delAttNos; 		//게시글 수정시 삭제할 첨부파일 번호 유지.
	
	private int attCount;   		//첨부파일수
}
