package com.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.StudentDAO;
import com.dto.PageDTO;
import com.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {

	StudentDAO dao;
	
	public void setDao(StudentDAO dao) {
		this.dao = dao;
	}


	@Override
	public List<StudentDTO> selectAllStudent() {
		List<StudentDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		   list = dao.selectAllStudent(session);
		}finally {
			session.close();
		}
		return list;
	}//end selectAllStudent


	@Override
	public List<StudentDTO> selectByName(String searchName) {
		List<StudentDTO> list = null;
	
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			list = dao.selectByName(session, searchName);
		}finally {
			session.close();
		}
		return list;
	}


	@Override
	public List<StudentDTO> selectByEntranceDate(HashMap<String, String> map) {
		List<StudentDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			list = dao.selectByEntranceDate(session, map);
		}finally {
			session.close();
		}
		
		return list;
	}


	@Override
	public List<StudentDTO> selectBySearchNo(String searchNO) {  // A000,A111,A222
		
		String [] no_arr = searchNO.split(",");
		List<String> no_list = Arrays.stream(no_arr).toList();
//		System.out.println("배열: " + Arrays.toString(no_arr));
//		System.out.println("List: " + no_list);
		
		List<StudentDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		    list = dao.selectBySearchNo(session, no_list);
		}finally {
			session.close();
		}
		return list;
	}


	@Override
	public int updateAbsenceChange(String searchNO) {  // A000,A111,A222
		
		String [] no_arr = searchNO.split(",");
		List<String> no_list = Arrays.stream(no_arr).toList();
		
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		    n = dao.updateAbsenceChange(session, no_list);
		    session.commit();
		}finally {
			session.close();
		}
		return n;
	}


	@Override
	public int updateCapacityChange() {
		
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		  n = dao.updateCapacityChange(session);
		  session.commit();
		}finally {
			session.close();
		}
		return n;
	}


	@Override
	public List<HashMap<String, String>> selectByGrade(String searchNO) {
		List<HashMap<String, String>> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		   list = dao.selectByGrade(session, searchNO);
		}finally {
			session.close();
		}
		return list;
	}


	@Override
	public PageDTO selectAllPageStudent(int curPage, int perPage) {
		PageDTO pageDTO = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		   pageDTO = 
	dao.selectAllPageStudent(session, curPage, perPage);
		}finally {
			session.close();
		}
		return pageDTO;
	}

}

/*
	SqlSession session = MySqlSessionFactory.getSession();
		try {
		
		}finally {
			session.close();
		}
*/



