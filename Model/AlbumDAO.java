package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.MainController;

public class AlbumDAO {
	public static ArrayList<Album> dbArrayList3 = new ArrayList<>();

	// 1. �����ǳ뷡�� ���̺�信 ���
	public static ArrayList<Album> selectAlbumData(String name) {
		String selectAlbum = "select * from albumtbl where name='" + name + "'";
		// 1-2 �����ͺ��̽� Ŀ�ؼ��� �����;��Ѵ�.
		Connection con = null;
		// 1-3. �������� �����ؾ��� Statement�� �����.
		PreparedStatement psmt = null;
		// 2-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;

		try {
			con = DBUtill.getConnection();
			psmt = con.prepareStatement(selectAlbum);

			// executeQuery�� �������� �����ؼ� ����� �����ö� ����ϴ� ������
			// executeUpdate�� �������� �����ؼ� ���̺� �����Ҷ� ����ϴ� ������
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("�������� ���� :  ���ھ�..");
				return null;
			}
			while (rs.next()) {
				Album album = new Album(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				dbArrayList3.add(album);
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ����.....");
		} finally {
			// 1-6. �ڿ���ü�� �ݴ´�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("�ݱ���� : ����");
			}
		}
		return dbArrayList3;
	}
}
