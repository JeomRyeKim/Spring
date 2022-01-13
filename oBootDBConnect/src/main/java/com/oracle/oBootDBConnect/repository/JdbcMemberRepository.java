package com.oracle.oBootDBConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils; //jdbc가 다운로드 되어있어야 작성 됨
import org.springframework.stereotype.Repository;

import com.oracle.oBootDBConnect.domain.Member1;

//@Repository //<- MemoryMemberRepository에 있던거 가져옴 (MemoryMemberRepository가 인터페이스여서 @Repository만 가져오면 됨)
public class JdbcMemberRepository implements MemberRepository {
	private final DataSource dataSource; //application.properties에 있는 정보를 DataSource가 가져와서 dataSource에 저장됨
	
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	
	@Override
	public Member1 save(Member1 member1) {
		String sql = "insert into member(id, name) values(member_seq.nextval, ?)";
		System.out.println("JdbcMemberRepository save(Member1 member1) sql->" + sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, member1.getName());
			pstmt.executeUpdate();
			System.out.println("JdbcMemberRepository save(Member1 member1) pstmt.executeUpdate(); After");
			//rs = pstmt.getGeneratedKeys();
//			if(rs.next()) {
//				member1.setId(rs.getLong(1));
//			}else {
//				throw new SQLException("id 조회 실패");
//			}
			return member1;
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(conn, pstmt, rs);
		}
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection conn) throws SQLException{
		// Connection은 종료되지 않는다. 단지 트랜잭션에서만 제거되었을 뿐
		DataSourceUtils.releaseConnection(conn, dataSource); //release : 풀어주다, 필요없는 오래된 데이터들 삭제해서 괘적하게? 11시 10분쯤 수업 다시 보기
	}

	@Override
	public List<Member1> findAll() {
		String sql="select * from member";
		System.out.println("JdbcMemberRepository findAll() sql->" + sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Member1> members = new ArrayList<>();
			while(rs.next()) {
				Member1 member = new Member1();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			return members;
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(conn, pstmt, rs);
		}
	}

}
