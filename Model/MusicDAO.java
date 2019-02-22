package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.MainController;

public class MusicDAO {
	public static ArrayList<Music> dbArrayList=new ArrayList<>();
	//1. �����ǳ뷡�� ���̺�信 ���
	public static int insertMusicData(Music music) {
		String insertMusic = "insert into musictbl" +
	"(name,song,album,date,url)"+"values"+"(?,?,?,?,?)"	;
		
		//1-2�����ͺ��̽� Ŀ�ؼ� ��������
		Connection con=null;
		//1-3�������� �����ؾ��� Statement�� �����.
		PreparedStatement psmt=null;
		int count=0;
		try {		
		con=DBUtill.getConnection();
		psmt=con.prepareStatement(insertMusic);
		//1-4 �������� �����Ϳ���
		psmt.setString(1, music.getName());
		psmt.setString(2, music.getSong());
		psmt.setString(3, music.getAlbum());
		psmt.setString(4, music.getDate());
		psmt.setString(5, music.getUrl());
		
		//1-6 �����͸� ������ ������ ����
		count=psmt.executeUpdate();
		
		if(count==0) {
			MainController.callAlert("������ ���� : �� ���ھ�..");
			return count;
		}
	} catch (SQLException e) {
		MainController.callAlert("���� ���� : ����.....");
	} finally {
		//1-6. �ڿ���ü�� �ݴ´�.
		try {
		if(psmt !=null) {psmt.close();}
		if(con !=null) {con.close();}
			} catch (SQLException e) {
				MainController.callAlert("�ݱ���� : ����");
		}
	}
	return count;		
	}
	
	public static ArrayList<Music> getMusicData(){
		String selectMusic="select * from musictbl ";
		
		Connection con=null;
		
		PreparedStatement psmt=null;
		
		ResultSet rs=null;
		
		try {
			con=DBUtill.getConnection();
			psmt=con.prepareStatement(selectMusic);
			
			rs=psmt.executeQuery();
			if(rs==null) {
				MainController.callAlert("�������� ���� : �ٽ� Ȯ�����ּ���");
				return null;
			}
			while(rs.next()) {
				Music music=new Music(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				dbArrayList.add(music);
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : �ٽýõ��ϼ���");
		}
		return dbArrayList;
	}
	public static int deleteMusicData(int no) {
		String deleteMusic = "delete from musictbl where no =? ";
		Connection con=null;
		PreparedStatement psmt=null;
		int count =0;
		
		try {
			con=DBUtill.getConnection();
			psmt=con.prepareStatement(deleteMusic);
			psmt.setInt(1, no);
			
			count = psmt.executeUpdate();
			if(count==0) {
				MainController.callAlert("���� ���� : �ٽ� �õ����ּ���");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ���� ����");
		}
		return count;
	}
	public static int updateMusicData(Music music) {
		String updataMusic = "update musictbl set "+
	"name=?,song=?,album=?,date=?,url=? ";
		
		Connection con=null;
		PreparedStatement psmt=null;
		int count=0;
		try {
			con=DBUtill.getConnection();
			psmt=con.prepareStatement(updataMusic);
			
			psmt.setString(1, music.getName());
			psmt.setString(2, music.getSong());
			psmt.setString(3, music.getAlbum());
			psmt.setString(4, music.getDate());
			psmt.setString(5, music.getUrl());
			
			count=psmt.executeUpdate();
			if(count==0) {
				MainController.callAlert("�������� : Ȯ���� �ٽýõ����ּ���");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("������������ : �ٽ� �õ����ּ���");
			e.printStackTrace();
		}
		return count;
	}
}
