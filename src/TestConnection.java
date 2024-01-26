import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.MsSQL;

public class TestConnection {

	public static void main(String[] args) {
		
		String company = new String();
			company = args[0];
			Connection connMSSQL = null;
			MsSQL dbMSSQL = new MsSQL(company);
			
			try {
				connMSSQL = dbMSSQL.connect();
				System.out.println("Database connected: " + connMSSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}