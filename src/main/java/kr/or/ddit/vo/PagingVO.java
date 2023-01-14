package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import oracle.jdbc.proxy.annotation.GetProxy;

/**
 * 페이징과 관련한 모든 데이터를 가진 객체
 * 
 */
//setter는 두가지만 사용
@Getter				
@NoArgsConstructor	//기본생성자 생성
@ToString			
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {	//컨트롤러에서 임의 설정
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; 		//총게시물 수, DB 조회 해서 가져옴
	private int screenSize = 10; 	//한 페이지당 리스트 수, 임의 설정
	private int blockSize = 5; 		//한 화면에 보여줄 페이지 수, 임의 설정
	
	private int currentPage; 		//현재페이지, 클라이언트 파라미터로 결정
	
	private int totalPage;			//총페이지 수
	private int startRow;			//시작 게시물 번호
	private int endRow;				//종료 게시물 번호
	private int startPage;			//시작 페이지 번호
	private int endPage;			//종료 페이지 번호
	
	private List<T> dataList;		//해당타입의 데이터 목록
	
	private SearchVO simpleCondition; //단순 키워드 검색용
	private T detailCondition; //상세 검색용
	
	public void setDetailCondition(T detailCondition) {	//상세 검색 받아 넣어줌
		this.detailCondition = detailCondition;
	}
	
	public void setSimpleCondition(SearchVO simpleCondition) {	//단순 키워드 받어 넣어줌
		this.simpleCondition = simpleCondition;
	}
	
	public void setDataList(List<T> dataList) {		//해당타입의 데이터 목록 넣어줌
		this.dataList = dataList;
	}
	
	//이 메소드가 호출되는 시점에서 계산됨
	public void setTotalRecord(int totalRecord) {	
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = ((currentPage+(blockSize - 1))/ blockSize) * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	private final String APATTERN = "<a class='paging' href='#' data-page='%d'>%s</a>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		
		if(startPage > blockSize) {
			html.append(
				String.format(APATTERN, startPage-blockSize, "이전")
			);
		}
		
		endPage = endPage > totalPage ? totalPage : endPage;
		for(int page=startPage; page<=endPage; page++) {
			if(page==currentPage) {
				html.append(
					"<a href='#'>"+page+"</a>"
				);
				
			}else {
				html.append(
					String.format(APATTERN, page, page+"")
				);
			}
		}
		
		if(endPage<totalPage) {
			html.append(
				String.format(APATTERN, endPage+1, "다음")
			);
		}
		
		return html.toString();
	}
	
}

















