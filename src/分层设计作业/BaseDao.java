package 分层设计作业;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC的工具类，负责执行数据的DML操作
 * @author 兵兵
 * @version 1.0
 */
public class BaseDao {
    //指定连接数据库的属性
	//驱动类
	private final String driverClassName="com.mysql.jdbc.Driver";  
	//连接数据库的地址url
	private final String url="jdbc:mysql://localhost:3306/dt72";
	//用户名
	private final String username="root";
	//密码
	private final String password="123456";
	
	//JDBC的连接对象
	private Connection con=null;
	//JDBC的预执行对象
	private PreparedStatement ps=null;
	//JDBC的结果集对象
	private ResultSet rs=null;
	
	//获取连接对象
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
	 * 执行添加修改删除的方法
	 * @param sql  允带参sql
	 * @param params参数值数组  有参就传参数值数组，无参数传null
	 * @return 影响行数
	 */
	public int executeUpdate(String sql,Object params[]){
		int temp=-1;
		try {
		    this.getConn();
			ps=con.prepareStatement(sql);
		    //传参
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
	 * 执行查询获取查询结果
	 * @param sql  sql语句 ，支持参数化sql
	 * @param params  参数化sql的参数，无参传null
	 * @return 结果集对象
	 */
	public ResultSet executeQuery(String sql,Object [] params){
		try {
			this.getConn();
			ps=con.prepareStatement(sql);
		    //传参
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
	
	//关闭资源的方法
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
