package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data	
@EqualsAndHashCode(of="boNo")	//연관관계가 복잡해지면 오버플로우가 발생함으로 id값 설정해줌
@ToString(exclude= {"boContent", "boPass"}) 	//boContent, boPass를 제외하고 출력하겠다, 리스트라 많아짐
public class BoardVO implements Serializable{  //객체 VO를 통신으로 넘기고 싶다면 직렬화를 하고 전송해야함
	private int rnum;				//행 번호
	@NotNull(groups = {UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;			//글번호, 글을 수정할떄와 삭제할때만 검증함
	@NotBlank
	private String boTitle;			//제목
	@NotBlank
	private String boWriter;		//작성자
	@NotBlank
	private String boIp;			//아이피
	@Email
	private String boMail;			//이메일
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	@JsonIgnore //마샬링되어 데이터가 들어가므로 jsonignore붙여서 제외시킴, 비밀번호는 보여지면 안되기 때문
	private transient String boPass;//비밀번호, transient : 직렬화에서 제외
	
	private String boContent;		//내용
	private String boDate;			//작성일
	private Integer boHit;			//조회수
	
	private List<AttatchVO> attatchList; //has many 관계 설정하기 위해 생성
	
	private int[] delAttNos; 		//게시글 수정시 삭제할 첨부파일 번호 유지.
	
	private int attCount;   		//첨부파일수
	
	private MultipartFile[] boFiles;
	
	private int startAttNo;
	
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles!=null && boFiles.length > 0) {
			this.boFiles = boFiles;
			this.attatchList = Arrays.stream(boFiles)
								.filter((f)->!f.isEmpty())
								.map((f)->{
									return new AttatchVO(f);
								}).collect(Collectors.toList());
		}
	}
}
