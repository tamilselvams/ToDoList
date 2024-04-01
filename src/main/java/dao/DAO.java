package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import dto.User;

public class DAO{

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
return con;

	}
	public int  saveUser(User user) throws ClassNotFoundException, SQLException 
	{
Connection con=getConnection();
PreparedStatement pst = con.prepareStatement("insert into user values(?,?,?,?,?,?)");
pst.setInt(1,user.getUserid());
pst.setString(2,user.getUsername());
pst.setString(3,user.getUseremail());
pst.setLong(4,user.getUsercontact());
pst.setString(6,user.getUserpassword());
Blob imageBlob=new SerialBlob(user.getUserimage());
pst.setBlob(5, imageBlob);
int res = pst.executeUpdate();

return res;
}
	public User findByEmail(String email) throws SQLException, ClassNotFoundException {
Connection con=getConnection();
PreparedStatement pst = con.prepareStatement("select * from user where useremail=?");
ResultSet rs = pst.executeQuery();
pst.setString(1, email);
if(rs.next()) {
	User u=new User();
	u.setUserid(rs.getInt(1));
	u.setUsername(rs.getString(2));
	u.setUseremail(rs.getString(3));
	u.setUsercontact(rs.getLong(4));
	u.setUserpassword(rs.getString(6));
	//convert blob image to byte[]
	Blob imageBlob = rs.getBlob(5);
	byte[] image = imageBlob.getBytes(1, (int)imageBlob.length());
	u.setUserimage(image);
return u;
	
}
else {
	return null;
}
}
}
