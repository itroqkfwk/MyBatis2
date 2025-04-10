import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.dao.StudentDAO;
import com.dto.PageDTO;
import com.dto.StudentDTO;
import com.service.StudentService;
import com.service.StudentServiceImpl;

public class StudentMain {
	
	static StudentService service = new StudentServiceImpl();

	public static void main(String[] args) {

		service.setDao(new StudentDAO());
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			printMainMenu();
			
			String inputMenu = scan.next();
			
			switch (inputMenu) {
				case "0": exit();break;
				case "1": printSelectAllStudent();break;
				case "2": { 
					System.out.print("검색할 학생명을 입력하시오 =>");
					String searchName = scan.next();
					printSelectByName(searchName);
				}break;
				case "3": { 
					System.out.print("시작 입학년도 입력하시오 =>");
					String startDate = scan.next();
					System.out.print("끝 입학년도 입력하시오 =>");
					String endDate = scan.next();
					printSelectByEntranceDate(startDate, endDate);
				}break;	
				case "4": { 
					System.out.print("검색할 학생의 학번을 입력하시오 =>");
					String searchNO = scan.next();  // A000,A111,A222
					printSelectBySearchNo(searchNO);
				}break;
				case "5": { 
					System.out.print("수정할 학생의 학번을 입력하시오 =>");
					String searchNO = scan.next();  // A000,A111,A222
					printUpdateAbsenceChange(searchNO);
				
				}break;	
				
				case "6":printUpdateCapacityChange();break;	
				case "7": { 
					System.out.print("학생의 학번을 입력하시오 =>");
					String searchNO = scan.next();  // A000
					printSelectByGrade(searchNO);
				}break;
				case "8": { 
					System.out.print("페이지당 보여줄 레코드 갯수를 입력하시오 =>");
					int perPage = scan.nextInt();  // 4
					int curPage=1;
					printSelectAllPageStudent(curPage, perPage);
				    // N  B Q
					while(true) {
						System.out.println("N: 다음 페이지 B: 이전 페이지 Q: 메인화면");
						String str = scan.next();
						if("N".equalsIgnoreCase(str)) {
							curPage++; // totalPage와 비교 필요
							printSelectAllPageStudent(curPage, perPage);
						}else if("B".equalsIgnoreCase(str)) {
							curPage=(curPage-1==0)?1:curPage-1;
							printSelectAllPageStudent(curPage, perPage);
						}else {
							break;
						}
					}//end while
				}//end case
			}//end switch
		}//end while
	}//end main
	
	 // 학생 정보 페이징
	private static void printSelectAllPageStudent(int curPage, int perPage) {
		PageDTO pageDTO = service.selectAllPageStudent(curPage, perPage);
		int totalRecord = pageDTO.getTotalRecord();
		List<StudentDTO> list = pageDTO.getList();
		
		System.out.println("================================");
		System.out.println("학번\t이름\t주민번호\t\t\t주소\t\t\t입학년도\t\t휴학여부");
		System.out.println("================================");
	    for (StudentDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t\t%s \n", dto.getStuNo(),
					         dto.getStuName(), dto.getStuSsn(), dto.getStuAddress(),
					         dto.getEntDate(), dto.getAbsYn());
		}
	    
	    int totalPage = totalRecord / pageDTO.getPerPage();
	    if(totalRecord % pageDTO.getPerPage() !=0 ) totalPage++;
	    	
	    System.out.printf("%d/%d \n", curPage, totalPage);	
	}//end printSelectAllPageStudent
	
	
	  
	 // 학생 학점 조회
	private static void printSelectByGrade(String searchNO) {
		
		System.out.println("================================");
		System.out.println("학기\t학번\t이름\t과목명\t\t점수\t\t학점");
		System.out.println("================================");
		List<HashMap<String, String>> list = service.selectByGrade(searchNO);
		for (HashMap<String, String> m : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s \n", m.get("term_no"),
					m.get("student_no"),m.get("student_name"),m.get("class_name"),
					m.get("point"),m.get("grade"));
		}
	}
	
	
	
       // 학과 정원 일괄 수정
	  private static void printUpdateCapacityChange() {
		  
		   int n = service.updateCapacityChange();
		   System.out.printf("총 변경된 학생수: %d명 \n", n);
	  }

      // 수정할 학번 출력
	 private static void printUpdateAbsenceChange(String searchNO) {
		 
		 int n = service.updateAbsenceChange(searchNO);
		 System.out.printf("총 변경된 학생수: %d명 \n", n);
	 }
	
	
	
	// 학생번호로 다중 검색
	private static void printSelectBySearchNo(String searchNO) {
		List<StudentDTO> list = service.selectBySearchNo(searchNO);
		System.out.println("================================");
		System.out.println("학번\t이름\t주민번호\t\t\t주소\t\t\t입학년도\t\t휴학여부");
		System.out.println("================================");
	    for (StudentDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t\t%s \n", dto.getStuNo(),
					         dto.getStuName(), dto.getStuSsn(), dto.getStuAddress(),
					         dto.getEntDate(), dto.getAbsYn());
		}
		System.out.printf("총 학생수: %d 명 \n", list.size() );
	}
	
	
	
	// 입학 년도 범위 검색
	private static void printSelectByEntranceDate(String startDate,String endDate) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		List<StudentDTO> list = service.selectByEntranceDate(map);
		System.out.println("================================");
		System.out.println("학번\t이름\t주민번호\t\t\t주소\t\t\t입학년도\t\t휴학여부");
		System.out.println("================================");
	    for (StudentDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t\t%s \n", dto.getStuNo(),
					         dto.getStuName(), dto.getStuSsn(), dto.getStuAddress(),
					         dto.getEntDate(), dto.getAbsYn());
		}
		System.out.printf("총 학생수: %d 명 \n", list.size() );
	}
	
	
	
	
	// 학생명 검색 목록
	private static void printSelectByName(String searchName) {
		List<StudentDTO> list = service.selectByName(searchName);
		System.out.println("================================");
		System.out.println("학번\t이름\t주민번호\t\t\t주소\t\t\t입학년도\t\t휴학여부");
		System.out.println("================================");
	    for (StudentDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t\t%s \n", dto.getStuNo(),
					         dto.getStuName(), dto.getStuSsn(), dto.getStuAddress(),
					         dto.getEntDate(), dto.getAbsYn());
		}
		System.out.printf("총 학생수: %d 명 \n", list.size() );
	}
	
	 // 전체 학생 목록
	private static void printSelectAllStudent() {
		
		List<StudentDTO> list = service.selectAllStudent();
		System.out.println("================================");
		System.out.println("학번\t이름\t주민번호\t\t\t주소\t\t\t입학년도\t\t휴학여부");
		System.out.println("================================");
	    for (StudentDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\t\t%s\t\t%s \n", dto.getStuNo(),
					         dto.getStuName(), dto.getStuSsn(), dto.getStuAddress(),
					         dto.getEntDate(), dto.getAbsYn());
		}
		System.out.printf("총 학생수: %d 명 \n", list.size() );
	}//end printSelectAllStudent
	
	
     // 프로그램 종료하는 메서드
	private static void exit() {
		System.out.println("프로그램이 종료되었습니다.");
		System.exit(0);
	}
		
	// 메뉴 출력 하는 메서드
	private static void printMainMenu() {
		System.out.println("*******************************");
		System.out.println("\t [학생 정보 관리 메뉴]");
		System.out.println("*******************************");
		System.out.println("1. 전체 학생 목록");
		System.out.println("2. 학생 이름 검색");
		System.out.println("3. 학생 입학년도 범위 검색 ( 예>2000년부터 2003년까지");
		System.out.println("4. 학생 학번으로 다중 검색 (쉼표 구분자)");
		System.out.println("5. 학생 휴학 일괄수정");
		System.out.println("6. 학과 정원 일괄수정");
		System.out.println("7. 학생 학점 검색");
		System.out.println("8. 전체 학생 목록 (페이징)");
		System.out.println("0. 종료");
		System.out.println("*******************************");
		System.out.print("메뉴 입력 =>");
	}
	
	
	
}//end class
