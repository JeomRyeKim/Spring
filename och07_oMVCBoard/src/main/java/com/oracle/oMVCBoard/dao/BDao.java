package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oracle.oMVCBoard.dto.BDto;

public class BDao {
	DataSource dataSource;
	
	public BDao() {
		try {
			// JNDI
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("생성자 dataSource-->" + e.getMessage());
			//e.printStackTrace();
		}
	}
	//게시판 글 전체 list 들고오기
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>(); // list문으로 Oracle DB에 있는 데이터를 가져오는 것
		Connection connection = null;
		// Statement와 PreparedStatement의 차이점 : PreparedStatement가 ?로 작성되어 가독성이 좋고, 더 보안성이 좋음
		// OWASP TOP 10 : 10가지 웹 애플리케이션 보안 취약점 목록 <- PreparedStatement를 사용하면 OWASP이 최소화되어 보안이 강화됨
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from MVC_BOARD order by BGROUP desc, BSTEP asc"; // BGROUP의 높은 숫자순(최신글순),BSTEP의 낮은 숫자순(최신글순)
		
		try {
			connection = dataSource.getConnection();
			//mvc_board list 조회
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				// 1.생성자로 만들어 줄 수 있고
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
				// 2. setter로 만들 수 있음.
//				BDto dto = new BDto();
//				dto.setbName(bName);
//				dto.setbContent(bContent);
//				dto.setbTitle(bTitle);
				
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException -->" + e.getMessage());
			//e.printStackTrace();
		}finally {
			try {
				// 안 닫아도 실행에는 문제 없지만 컴퓨터에 과부하가 걸려 컴퓨터가 죽을 수 있음..
				if(resultSet!=null) resultSet.close(); 
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("list dataSource Exception2 -->" + e2.getMessage());
			}
		}
		
		return dtos;
	}
	// 게시판 상세글 눌렀을 때 글 내용 불러오기
	// BContentCommand에서 String bId = request.getParameter("bId");로 보내서 String으로 받음
	// bId로 보냈지만 받을 때 strID로 바꿔서 처리해도 상관없음
	public BDto contentView(String strID) { // strID : 게시판 번호(bId)
		upHit(strID);
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from MVC_BOARD where bId=?";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();
			// mvc_board bId를 가지고 BDto dto를 담아서 return
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, strID);
			//preparedStatement.setInt(1, Integer.parseInt(strID)); //int로 바꿔서 넣어도 됨!
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				dto = new BDto();
				dto.setbId(resultSet.getInt(1));
				dto.setbName(resultSet.getString(2));
				dto.setbTitle(resultSet.getString(3));
				dto.setbContent(resultSet.getString(4));
				dto.setbDate(resultSet.getTimestamp(5));
				dto.setbHit(resultSet.getInt(6));
				dto.setbGroup(resultSet.getInt(7));
				dto.setbStep(resultSet.getInt(8));
				dto.setbIndent(resultSet.getInt(9));
			}
			 
		} catch (Exception e) {
			System.out.println("contentView dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				// 안 닫아도 실행에는 문제 없지만 컴퓨터에 과부하가 걸려 컴퓨터가 죽을 수 있음..
				if(resultSet!=null) resultSet.close(); 
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("contentView dataSource Exception2 -->" + e2.getMessage());
			}
		}
		
		return dto;
	}
	// 상세글 클릭 시 조회수 증가 처리
	private void upHit(String strID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String query = "update MVC_BOARD set bHit=bHit+1 where bId=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, strID);
			
			int rn = preparedStatement.executeUpdate(); //지금은 필요 없지만 나중에 리턴값으로 필요할 수 있으니 int rn 설정해줌
		} catch (Exception e) {
			System.out.println("upHit dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("upHit dataSource Exception2 -->" + e2.getMessage());
			}
		}
		
	}
	//게시판 글 수정
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String query = "update MVC_BOARD set bName=?, bTitle=?, bContent=? where bId=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			
			int rn = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("modify dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("modify dataSource Exception2 -->" + e2.getMessage());
			}
		}
	}
	//새로운 글 작성
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// nextbal : 해당 시퀀스의 다음 값/ currval : 해당 시퀀스의 현재 값
		String query = "insert into MVC_BOARD values(mvc_board_seq.nextval,?,?,?,sysdate,0,mvc_board_seq.currval,0,0)";

		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("write dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("write dataSource Exception2 -->" + e2.getMessage());
			}
		}
		
	}
	//댓글 다는 화면에 원글의 정보 가져오기
	public BDto reply_view(String str) {
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from mvc_board where bId=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(str));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
			int bId = resultSet.getInt("bId");
			String bName = resultSet.getString("bName");
			String bTitle = resultSet.getString("bTitle");
			String bContent = resultSet.getString("bContent");
			Timestamp bDate = resultSet.getTimestamp("bDate");
			int bHit = resultSet.getInt("bHit");
			int bGroup = resultSet.getInt("bGroup");
			int bStep = resultSet.getInt("bStep");
			int bIndent = resultSet.getInt("bIndent");
			
			dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			System.out.println("reply_view dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				if(resultSet!=null) resultSet.close(); 
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("reply_view dataSource Exception2 -->" + e2.getMessage());
			}
		}
		
		return dto;
	}
	//답변저장 버튼 누를 때
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		// 새로 입력하는 댓글이 기존의 댓글과 bGroup =같고   &  기존의 댓글보다 bStep >작으면  =>  bStep + 1
		// 기존의 댓글이 없으면 bStep >이 성립이 되지 않으니 replyShape()메소드가 적용X
		// 최종적으로 원글의 첫댓글 + 대댓글은 replyShape 거치치X
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String query = "insert into mvc_board(bId, bName, bTitle, bContent, bGroup, bStep, bIndent)"
					 + " values (mvc_board_seq.nextval,?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			// 처음에 게시글을 입력할 땐 write()의 insert문에 bStep, bIndent이 0으로 입력되어 있는데 그 정보를 가져와서 댓글을 입력하게됨
			// bStep, bIndent이 insert문 때문에 나란히 +1이 되어 1, 1이 입력됨
			// 대댓글을 달 땐 기존 댓글의 bStep(1) > 대댓글bStep(1)이 성립되지 않아 replyShape()메소드X되고, 또 bStep, bIndent가 insert문 때문에  나란히 +1이 됨.
			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("reply dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("reply dataSource Exception2 -->" + e2.getMessage());
			}
		}
	}
	//기존 댓글의 bStep을 +1 시켜줌
	private void replyShape(String strGroup, String strStep) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// 답변글 중 최신글이 위로 올라오게 하기 위해서
		// 기존 댓글의 bGroup이 새로 입력할 댓글의 bGroup과 같고, 기존 댓글의 bStep(1)이 새로 입력될 bStep(0)보다 클 때 기존댓글의 bStep + 1시켜준다
		// 댓글을 입력할 때마다 처리를 해주기 때문에 그래서 댓글들이 늘어갈 때마다 기존에 있던 댓글들은 계속 +1 +1 +1이 됨
		// bStep, bIndent이 0이면 원글이고 1이면 댓글, 2이면 대댓글...
		// list.jsp - <c:forEach begin="1" end="${mvc_board.bIndent}">-</c:forEach> : bIndent가 1이면 - 댓글, 2개면 --대댓글, 3개면 ---대대댓글... (들여쓰기 의미)
		String query = "update mvc_board set bStep = bStep + 1 where bGroup =? and bStep > ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("replyShape dataSource Exception -->" + e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close(); 
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				System.out.println("replyShape dataSource Exception2 -->" + e2.getMessage());
			}
		}
		
	}

	
	

}
