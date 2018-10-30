package �ֲ������ҵ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC�Ĺ����࣬����ִ�����ݵ�DML����
 * @author ����
 * @version 1.0
 */
public class BaseDao {
    //ָ���������ݿ������
	//������
	private final String driverClassName="com.mysql.jdbc.Driver";  
	//�������ݿ�ĵ�ַurl
	private final String url="jdbc:mysql://localhost:3306/dt72";
	//�û���
	private final String username="root";
	//����
	private final String password="123456";
	
	//JDBC�����Ӷ���
	private Connection con=null;
	//JDBC��Ԥִ�ж���
	private PreparedStatement ps=null;
	//JDBC�Ľ��������
	private ResultSet rs=null;
	
	//��ȡ���Ӷ���
	public Connection getConn() {
		try {
			Class.forName(this.driverClassName);
			con= DriverManager.getConnection(this.url, this.username,this.password);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	
	/**
	 * ִ������޸�ɾ���ķ���
	 * @param sql  �ʴ���sql
	 * @param params����ֵ����  �вξʹ�����ֵ���飬�޲�����null
	 * @return Ӱ������
	 */
	public int executeUpdate(String sql,Object params[]){
		int temp=-1;
		try {
		    this.getConn();
			ps=con.prepareStatement(sql);
		    //����
		    if(params!=null)
		    {
			    for (int i = 0; i < params.length; i++) {
					ps.setObject((i+1), params[i]);
				}
		    }
		    temp=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return temp;
	}
	
	/**
	 * ִ�в�ѯ��ȡ��ѯ���
	 * @param sql  sql��� ��֧�ֲ�����sql
	 * @param params  ������sql�Ĳ������޲δ�null
	 * @return ���������
	 */
	public ResultSet executeQuery(String sql,Object [] params){
		try {
			this.getConn();
			ps=con.prepareStatement(sql);
		    //����
		    if(params!=null)
		    {
			    for (int i = 0; i < params.length; i++) {
					ps.setObject((i+1), params[i]);
				}
		    }
		    rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//�ر���Դ�ķ���
	public void closeAll() {
		  try {
			    if(rs!=null){
			    	rs.close();   rs=null;
				}
			    if(ps!=null){
			    	ps.close();  ps=null;
				}
				if(con!=null){
					con.close();   con=null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	}


}
