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
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("생성자 dataSource-->" + e.getMessage());
			//e.printStackTrace();
		}
	}
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>(); // list문으로 Oracle DB에 있는 데이터를 가져오는 것
		Connection connection = null;
		// Statement와 PreparedStatement의 차이점 : PreparedStatement가 ?로 작성되어 가독성이 좋고, 더 보안성이 좋음
		// OWASP TOP 10 : 10가지 웹 애플리케이션 보안 취약점 목록 <- PreparedStatement를 사용하면 OWASP이 최소화되어 보안이 강화됨
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from MVC_BOARD order by BGROUP desc, BSTEP asc";
		
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
		}
		
		return dtos;
	}
	public BDto contentView(String strID) {
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
		}
		
		return dto;
	}

}
