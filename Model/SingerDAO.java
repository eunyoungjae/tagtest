package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Controller.MainController;

public class SingerDAO {
	public static ArrayList<Singer> dbArrayList2 = new ArrayList<>();
	public static ArrayList<insta> dbArrayList4 = new ArrayList<>();
	// 1. �����ǳ뷡�� ���̺�信 ���
	public static ArrayList<Singer> selectMusicData(String name) {
		String selectSinger = "select * from singertbl where name='" + name + "'";
		// 1-2 �����ͺ��̽� Ŀ�ؼ��� �����;��Ѵ�.
		Connection con = null;
		// 1-3. �������� �����ؾ��� Statement�� �����.
		PreparedStatement psmt = null;
		// 2-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
		ResultSet rs = null;

		try {
			con = DBUtill.getConnection();
			psmt = con.prepareStatement(selectSinger);

			// executeQuery�� �������� �����ؼ� ����� �����ö� ����ϴ� ������
			// executeUpdate�� �������� �����ؼ� ���̺� �����Ҷ� ����ϴ� ������
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("�������� ���� :  ���ھ�..");
				return null;
			}
			while (rs.next()) {
				Singer singer = new Singer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				dbArrayList2.add(singer);
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
		return dbArrayList2;
	}
	
	//4. ���̺�信�� ������ ���ڵ带 �����ͺ��̽� ���̺� �����ϴ� �Լ�
		public static int updateSingerData(Singer singer, String name2) {
			//4-1 �����ͺ��̽��� ����
			String updateSinger = "update singertbl set "+
					"image=?, birth=?, agency=?, prize=?, topsing=? where name=?";
					System.out.println("�Լ�����"+singer.toString());
					System.out.println(name2);
					singer.toString();
					//1-2 �����ͺ��̽� Ŀ�ؼ��� �����;��Ѵ�.
					Connection con=null;
					//1-3. �������� �����ؾ��� Statement�� �����.
					PreparedStatement psmt=null;
					int count=0;
					try {
						con=DBUtill.getConnection();			
						psmt=con.prepareStatement(updateSinger);
						//1-4. �������� �����͸� ����
						
						psmt.setString(1, singer.getImagePath());
						psmt.setString(2, singer.getBirth());
						psmt.setString(3, singer.getAgency());
						psmt.setString(4, singer.getAwards());
						psmt.setString(5, singer.getTopsing());
						psmt.setString(6, name2);
						
						//1-5. �����͸� ������ ������ ����
						count=psmt.executeUpdate();
						
						System.out.println("������ ������ :"+count);
						if(count==0) {
							MainController.callAlert("�������� ���� : �� ���ھ�..");
							return count;
						}else {
							
						}
					} catch (SQLException e) {
						MainController.callAlert("�������� ���� : ����.....");
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

		public static int insertStudentData(Singer singer) {
			int count = 0;

			// 1-1. �����ͺ��̽��� �л����̺� �Է��ϴ� ������
			StringBuffer insertSinger = new StringBuffer();
			insertSinger.append("insert into singertbl ");
			insertSinger.append("values ");
			insertSinger
					.append("(image,name,birth,agency,prize,topsing) ");
			insertSinger.append("(?,?,?,?,?,?) ");

			// 1-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
			Connection con = null;

			// 1-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
			PreparedStatement psmt = null;
			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(insertSinger.toString());
				// 1-4. �������� ���� �����͸� �����Ѵ�.
				psmt.setString(1, singer.getImagePath());
				psmt.setString(2, singer.getName2());
				psmt.setString(3, singer.getBirth());
				psmt.setString(4, singer.getAgency());
				psmt.setString(5, singer.getAwards());
				psmt.setString(6, singer.getTopsing());
				

				// 1-5. ���������͸� ������ �������� �����Ѵ�.
				// executeUpdate(); �������� �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
				count = psmt.executeUpdate();
				if (count == 0) {
					MainController.callAlert("���� �������� : ���� �������� ������ �ֽ��ϴ�.");
					return count;
				}
			} catch (SQLException e) {
				MainController.callAlert("���� ���� : �����ͺ��̽� ���Կ� ������ �ֽ��ϴ�.");
			} finally {
				// 1-6. �ڿ���ü�� �ݾƾ��Ѵ�.
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
				}
			}
			return count;
		}

		// 2. ���̺��� ��ü ������ ��� �������� �Լ�
		public static ArrayList<Singer> getSingerTotalData() {

			// 2-1. �����ͺ��̽� �л����̺� �ִ� ���ڵ带 ��� �������� ������
			String selectSinger = "select * from singertbl";

			// 2-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
			Connection con = null;

			// 2-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
			PreparedStatement psmt = null;

			// 2-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
			ResultSet rs = null;
			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(selectSinger);

				// 2-5. ���������͸� ������ �������� �����Ѵ�.(������ ġ�°�)
				// executeQuery(); �������� �����ؼ� ����� �����ö� ����ϴ� ������
				rs = psmt.executeQuery();
				if (rs == null) {
					MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
					return null;
				}
				while (rs.next()) {
					Singer singer = new Singer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6));
					dbArrayList2.add(singer);
				} // end of while
			} catch (SQLException e) {
				MainController.callAlert("���� ���� : �����ͺ��̽� ���Կ� ������ �ֽ��ϴ�.");
			} finally {
				// 1-6. �ڿ���ü�� �ݾƾ��Ѵ�.
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
				}
			}
			return dbArrayList2;
		}
		
		public static int deleteStudentData(String no) {

			// 3-1. �����ͺ��̽� �л����̺� �ִ� ���ڵ带 �����ϴ� ������
			String deleteSinger = "delete from singertbl where name = ? ";

			// 3-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
			Connection con = null;

			// 3-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
			PreparedStatement psmt = null;

			// 3-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
			int count = 0;
			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(deleteSinger);
				psmt.setString(1, no);
				// 3-5. ���������͸� ������ �������� �����Ѵ�.(������ ġ�°�)
				// executeQuery(); �������� �����ؼ� ����� �����ö� ����ϴ� ������
				count = psmt.executeUpdate();
				if (count == 0) {
					MainController.callAlert("delete ���� : delete�� ������ �ֽ��ϴ�.");
					return count;
				}

			} catch (SQLException e) {
				MainController.callAlert("delete ���� : delete�� ������ �ֽ��ϴ�.");
			} finally {
				// 3-6. �ڿ���ü�� �ݾƾ��Ѵ�.
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
				}
			}
			return count;
		}
		public static ArrayList<insta> selectFollowerData() {
			String selectFollower = "select * from charttbl ";
			// 1-2 �����ͺ��̽� Ŀ�ؼ��� �����;��Ѵ�.
			Connection con = null;
			// 1-3. �������� �����ؾ��� Statement�� �����.
			PreparedStatement psmt = null;
			// 2-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
			ResultSet rs = null;

			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(selectFollower);

				// executeQuery�� �������� �����ؼ� ����� �����ö� ����ϴ� ������
				// executeUpdate�� �������� �����ؼ� ���̺� �����Ҷ� ����ϴ� ������
				rs = psmt.executeQuery();
				if (rs == null) {
					MainController.callAlert("�������� ���� :  ���ھ�..");
					return null;
				}
				while (rs.next()) {
					insta insta= new insta(rs.getString(1), rs.getInt(2));
					dbArrayList4.add(insta);
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
			return dbArrayList4;
		}
}
