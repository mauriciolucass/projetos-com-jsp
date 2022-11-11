package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleBancoGd {
	
	private static String url = "jdbc:postgresql://localhost:5432/gideoes";
	private static String user = "postgres";
	private static String password = "isluc";
	private static Connection connection = null;
	
	
	
	static {
		try {
			conectarGD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SingleBancoGd() throws SQLException {
		conectarGD();
	}
	
	public static void conectarGD() throws SQLException {
		if(connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url,user,password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Connection gConnection() {
		return connection;
		
	}
	
	

}
