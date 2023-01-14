package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;

//첨부파일은 수정 필요없음
@Mapper //proxy 생성
public interface AttatchDAO {
	public int insertAttatches(BoardVO board); //첨부된 파일, 게시글에 딸려있기에 boardVO를 파라미터로 받음
	public List<AttatchVO> selectAttatchList(int boNo); //
	public AttatchVO selectAttatch(int attNO); //하나하나 다운을 하게 해주기 위해
	public int deleteAttatches(BoardVO board);	// 첨부파일 삭제, rawcount = delAttNos.length
}
