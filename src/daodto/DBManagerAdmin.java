package daodto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBManagerAdmin {
	private static DBManagerAdmin dbManagerAdmin=null;
	private static String dbDriver = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost/kadai2";
	private Connection con = null;
	final static String USER = "user";
	final static String PASSWORD = "password";

	private DBManagerAdmin() throws SQLException{
		try{
			Class.forName(dbDriver);
			this.con=DriverManager.getConnection(dbUrl,USER,PASSWORD);

		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			 throw new IllegalStateException(
	                    "fail to class load : "
	                            + cnfe.getMessage());
		}
	}

	public synchronized static DBManagerAdmin getDBManagerAdmin() throws Exception{
		//シングルトン処理
		if(DBManagerAdmin.dbManagerAdmin==null){
			DBManagerAdmin.dbManagerAdmin=new DBManagerAdmin();

		}
		return DBManagerAdmin.dbManagerAdmin;
	}

	public Connection getConnection(){
		return this.con;
	}



}
