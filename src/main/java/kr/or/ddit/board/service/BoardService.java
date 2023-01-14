package kr.or.ddit.board.service;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface BoardService {
	public int createBoard(BoardVO board); //이넘이 없는 경우가 많음
	
	public void retrieveBoardList(PagingVO<BoardVO> board);
	
	/**
	 * @param boNo
	 * @return 존재여부(NotExistBoardException)
	 */
	public BoardVO retrieveBoard(int boNo); //첨부파일(테이블 두개 이상 조인)
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return 존재여부, 인증성공여부(AuthenticationException), rowcnt
	 */
	public int modifyBoard(BoardVO board); //인증구조 필요, 발생 경우 수가 다양함, 인증실패시 예외
	public int removeBoard(int boNo);
	
	public AttatchVO retrieveForDownload(int attNo); 
}
