import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
public class dbmanager {
	Connection con=null;
	Statement stmt=null;
	public dbmanager() throws SQLException{
			DriverManager.registerDriver(new SQLServerDriver());
			String url="jdbc:sqlserver://127.0.0.1;instanceName=MSSQLSERVER;databaseName=Studentdata;encrypt=true;trustServerCertificate=true";
			con=DriverManager.getConnection(url,"sa","Talha@8259");
			stmt=con.createStatement();		
	}
	public void insertUpdateDelete(String query) throws SQLException{		
			stmt.execute(query);
	}
	public ResultSet select(String query) throws SQLException{
			ResultSet rs= stmt.executeQuery(query);
			return rs;
	}
}