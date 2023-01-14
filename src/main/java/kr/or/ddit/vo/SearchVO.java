package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	//모든 파라미터가 들은 생성자
@NoArgsConstructor	//기본 생성자
public class SearchVO {
	private String searchType;
	private String searchWord;
}
