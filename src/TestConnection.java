import java.sql.Connection;
import java.sql.SQLException;

import model.IBMi;
import model.MsSQL;
import model.MySQL;
import model.Oracle;

public class TestConnection {

	public static void main(String[] args) {
		
		String company = new String();
		if (args.length > 0)
			company = args[0];
		else
			company = "westend";
		
		Connection connMSSQL = null;
		MsSQL dbMSSQL = new MsSQL(company);
		try {
			connMSSQL = dbMSSQL.connect();
			System.out.println("MsSQL Database connected: " + connMSSQL);
			dbMSSQL.closeConnection(connMSSQL);
			connMSSQL.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		Connection connMYSQL = null;
		MySQL dbMYSQL = new MySQL(company);
		try {
			connMYSQL = dbMYSQL.connect();
			System.out.println("MySQL Database connected: " + connMYSQL);
			dbMYSQL.closeConnection(connMYSQL);
			connMYSQL.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		IBMi dbIBMi = new IBMi(company);
		System.out.println("IBMi Database connected: " + dbIBMi.getAS400());
		dbIBMi.getAS400().disconnectAllServices();
		
		Connection connOracle = null;
		Oracle dbOracle = new Oracle(company);
		try {
			connOracle = dbOracle.connect();
			System.out.println("Oracle Database connected: " + connOracle);
			dbOracle.closeConnection(connOracle);
			connOracle.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}