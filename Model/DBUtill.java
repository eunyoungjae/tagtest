package Model;

import java.sql.Connection;
import java.sql.DriverManager;

import Controller.MainController;

public class DBUtill {
	public static Connection getConnection() {
	Connection con=null;
	//1. MySql database class �ε��Ѵ�.
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//2. �ּ�,ID,��й�ȣ�� ���ؼ� ���ӿ�û
		con=DriverManager.getConnection("jdbc:mysql://192.168.0.173/musicdb", "root", "123456");
	} catch (Exception e) {
		
	}
	return con;
	}
}
