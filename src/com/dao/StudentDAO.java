package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.PageDTO;
import com.dto.StudentDTO;

// MySQL DB 연동하는 전담 클래스
public class StudentDAO {

	public List<StudentDTO> selectAllStudent(SqlSession session){
		List<StudentDTO> list = session.selectList("com.config.StudentMapper.selectAllStudent");
		return list;
	}
	public List<StudentDTO> selectByName(SqlSession session,String searchName){
		List<StudentDTO> list = session.selectList("com.config.StudentMapper.selectByName", searchName);
		return list;		
	}
	public List<StudentDTO> selectByEntranceDate(SqlSession session,
			                                         HashMap<String, String> map) {
		List<StudentDTO> list = 
				session.selectList("com.config.StudentMapper.selectByEntranceDate", map);
		return list;		
		
	}
	public List<StudentDTO> selectBySearchNo(SqlSession session,
			List<String> no_list) {
		List<StudentDTO> list = 
				session.selectList("com.config.StudentMapper.selectBySearchNo", no_list);
		return list;
	}
	
	public int updateAbsenceChange(SqlSession session,
			List<String> no_list) {
		int n = session.update("com.config.StudentMapper.updateAbsenceChange", no_list);
		return n;
	}
	public int updateCapacityChange(SqlSession session) {
	    int n = session.update("com.config.StudentMapper.updateCapacityChange");
		return n;
	}
	public List<HashMap<String, String>> selectByGrade(SqlSession session,
			            String searchNO){
		List<HashMap<String, String>> list = 
				session.selectList("com.config.StudentMapper.selectByGrade", searchNO);
		return list;
	}
	
	private int totalRecord(SqlSession session) {
		int n = session.selectOne("com.config.StudentMapper.totalRecord");
		return n;
	}
	
	public PageDTO selectAllPageStudent(SqlSession session,
			          int curPage, int perPage) {
		PageDTO pageDTO = new PageDTO();
		int totalRecord = totalRecord(session);
		int skip = (curPage-1)*perPage; // skip할 레코드 갯수
		List<StudentDTO> list =
				session.selectList("com.config.StudentMapper.selectAllStudent", null, 
						 new RowBounds(skip, perPage));
		
		pageDTO.setTotalRecord(totalRecord);
		pageDTO.setPerPage(perPage);
		pageDTO.setCurPage(curPage);
		pageDTO.setList(list);
		return pageDTO;
	}
}




