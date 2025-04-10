package com.service;

import java.util.HashMap;
import java.util.List;

import com.dao.StudentDAO;
import com.dto.PageDTO;
import com.dto.StudentDTO;

public interface StudentService {

	public void setDao(StudentDAO dao);
	 // 전체 학생 목록
	public List<StudentDTO> selectAllStudent();
	// 학생명 검색 목록
	public List<StudentDTO> selectByName(String searchName);
	//입학년도 범위 검색
	public List<StudentDTO> selectByEntranceDate(HashMap<String, String> map);
	// 학생번호 다중검색
	public List<StudentDTO> selectBySearchNo(String searchNO);
	// 수정할 학번
	public int updateAbsenceChange(String searchNO);
	// 학과정원 일괄수정
	public int updateCapacityChange();
	// 학생 학점 조회
	public List<HashMap<String, String>> selectByGrade(String searchNO) ;

	// 전체 학생 목록 페이징
	 public PageDTO selectAllPageStudent(int curPage, int perPage);

}
