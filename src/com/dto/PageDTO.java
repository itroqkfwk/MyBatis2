package com.dto;

import java.util.List;

/*

 * 페이징

 1. 정렬
 2. 페이지당 보여줄 레코드 갯수
    int perPage
 3. 현재 페이지 번호
    int curPage=1;
 4. 전체 레코드 갯수
    int totalRecord
    전체 페이지 번호 = 전체 레코드 갯수 / 페이지당 보여줄 레코드 갯수;
 5. 보여줄 레코드 정보
   List<StudentDTO> list
   ==> 위 4개의 변수값을 가진 클래스가 필요함.
       PageDTO
 */
public class PageDTO {

	  int perPage;
	  int curPage=1;
	  int totalRecord;
	  List<StudentDTO> list;
	  
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<StudentDTO> getList() {
		return list;
	}
	public void setList(List<StudentDTO> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageDTO [perPage=" + perPage + ", curPage=" + curPage + ", totalRecord=" + totalRecord + ", list="
				+ list + "]";
	}
	  
	  
	  
}





