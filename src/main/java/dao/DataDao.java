package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Clob;

import db.MyConnection;
import model.Data;

public class DataDao {
	public static List<Data> getAllFiles(String email) throws SQLException {
		 Connection connection = MyConnection.getConnection();
	        PreparedStatement ps = connection.prepareStatement("select * from data where email = ?");
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();
	        List<Data> files = new ArrayList<>();
	        while (rs.next()) {
	            int id = rs.getInt(1);
	            String name = rs.getString(2);
	            String path = rs.getString(3);
	            files.add(new Data(id, name, path));
	        }
	        return files;
	}
	public static int hideFile(Data file)throws SQLException, IOException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into data(name, path, email, bin_data) values(?,?,?,?)");
        ps.setString(1, file.getFileName());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());
        File f = new File(file.getPath());
        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4, fr);
        int ans = ps.executeUpdate();
        fr.close();
        f.delete();
        return ans;
    }
	public static void Unhide(int id)throws SQLException, IOException {
		Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select path, bin_data from data where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        String path = rs.getString("path");
        java.sql.Clob c = rs.getClob("bin_data");

        Reader r = c.getCharacterStream();
       File f = new File(path);
       f.createNewFile();
       FileWriter fw = new FileWriter(f);
        int i;
        while ((i = r.read()) != -1) {
            fw.write((char) i);
        }
        fw.close();
        ps = connection.prepareStatement("delete from data where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Successfully Unhidden");
        
        }
	}
	

}
