package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")	//연관관계가 복잡해지면 오버플로우가 발생함으로 id값 설정해줌
@NoArgsConstructor 				//DB에서 보내는것을 받으려면 기본생성자가 필요
@ToString(exclude="realFile")
public class AttatchVO implements Serializable{
	@JsonIgnore
	private transient MultipartFile realFile;	//MultipartFile : 파일업로드 타입
	//클라이언트가 보내주는 파일을 받음
	public AttatchVO(MultipartFile realFile) {  
		super();
		this.realFile = realFile;
		this.attFilename = realFile.getOriginalFilename();	//getOriginalFilename : 업로드되는 파일에서 확장자를 포함한 파일의 이름을 반환
		this.attSavename = UUID.randomUUID().toString();	//UUID : 고유 식별자(유일성), randomUUID()을 활용해 유일성을 줌, toString()으로 문자로 표현
		this.attMime = realFile.getContentType();			//요청정보를 전송할때 사용한 컨텐트 타입 구함
		this.attFilesize = realFile.getSize(); 				//파일크기, getSize의 타입이 long이라 필드의 타입 바꿔줌
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);	//byteCountToDisplaySize : 바이트 수를 사람이 읽을 수 있는 형식으로 변환하는 데 사용되는 클래스 의 정적 메서드
	}
	
	private Integer attNo;		//파일번호
	private Integer boNo;		//글번호
	private String attFilename;	//파일명
	private String attSavename;	//저장명
	private String attMime;		//파일MIME
	private Long attFilesize;	//파일크기
	private String attFancysize;//팬시크기
	private Integer attDownload;//다운로드수
	
	public void saveTo(File saveFolder) throws IOException {
		//파일이 있을때 저장해야함
		if(realFile==null || realFile.isEmpty()) return;
		realFile.transferTo(new File(saveFolder, attSavename));
	}
}
