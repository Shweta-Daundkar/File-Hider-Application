package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyConnection;
import model.User;

public class UserDao {
	public static boolean isExists(String email) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false","root","Shweta@45");
		PreparedStatement ps = connection.prepareStatement("select email from users");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int columnIndex;
			String e = rs.getString(columnIndex=1);
			if(e.equals(email))
			return true;
		}
		return false;
		
	}
	public static int saveUser(User user) throws SQLException {
		Connection connection = MyConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("insert into users(name, email) values (?,?)");
		
		int parameterIndex1 = 0;
		ps.setString(1, user.getName());
		
		int parameterIndex2 = 0;
		ps.setString(2, user.getEmail());
		return ps.executeUpdate();	
	}

}
