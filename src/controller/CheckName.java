package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MsSQL;

public class CheckName {
	
	private static Connection connLibrary;
	
	public CheckName() {
		
		super();
		
		MsSQL dbLibrary = new MsSQL("liblist");
		try {
			connLibrary = dbLibrary.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public CheckName(Connection connection) {
		
		super();
		
		connLibrary = connection;
	}
	
	public void closeConnection() {
		if (connLibrary != null) {
			try {
				connLibrary.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String checkFieldName(String fieldName) {
		
		String search = fieldName.substring(0, 1);
		if (search.equals("#")) {
			fieldName = "a_" + fieldName.substring(1);
		} else if (search.equals("@")) {
			fieldName = "b_" + fieldName.substring(1);
		} else if (search.equals("$")) {
			fieldName = "c_" + fieldName.substring(1);
		} else if (search.equals("%")) {
			fieldName = "d_" + fieldName.substring(1);
		} else if (search.equals("&")) {
			fieldName = "e_" + fieldName.substring(1);
		} else if (search.equals("*")) {
			fieldName = "f_" + fieldName.substring(1);
		} else if (search.equals("_")) {
			fieldName = "g_" + fieldName.substring(1);
		}
		
		fieldName = fieldName.replace("#", "_a");
		fieldName = fieldName.replace("@", "_b");
		fieldName = fieldName.replace("$", "_c");
		fieldName = fieldName.replace("%", "_d");
		fieldName = fieldName.replace("&", "_e");
		fieldName = fieldName.replace("*", "_f");
		
		try {
			String checkSql = "select count(*) as numberOfRecords from qcrtsqlfld "
					+ "Where fieldnamel = ?";
			PreparedStatement checkStmt;
			checkStmt = connLibrary.prepareStatement(checkSql);
			checkStmt.setString(1, fieldName);
			ResultSet results = checkStmt.executeQuery();
			if (results.next()) {
				int numberOfRecords = results.getInt(1);
				if (numberOfRecords > 0) {
					fieldName = fieldName + "_";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fieldName;
	}
}