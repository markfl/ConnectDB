package model;

import java.sql.Connection;
import java.sql.SQLException;

import com.ibm.as400.access.AS400;

import controller.ParseConfig;

public class IBMi {
	
	private String companyName;
	private String userName;
	private String passWord;
	private Connection conn;
	private AS400 as400;
		
	public IBMi() {
	      super();
	}
	
	public IBMi(String companyName) {
		
	      super();
	      setCompanyName(companyName);
	      setAS400(connect());
	}
	
	public IBMi(String companyName, String userName, String passWord) {
		
	      super();
	      setCompanyName(companyName);
	      setUserName(userName);
	      setPassWord(passWord);
	}

	public AS400 connect() {
		
		ParseConfig pc = new ParseConfig();
		String dir = System.getProperty("user.dir");
		String configInput = dir + "/config/IBMi.json";
		String IBMiParms = "iseries_parameters_" + getCompanyName().trim();
		String as400connect[] = pc.parseIBMiJSONConfig(configInput, IBMiParms);
		AS400 as400 = new AS400(as400connect[0], as400connect[1], as400connect[2]);
		setAS400(as400);
		setUserName(as400connect[1]);
		setPassWord(as400connect[2]);
		
		return as400;
	}
	
	public void closeConnection(Connection conn) throws SQLException {

		if (conn != null) {
			conn.close();
		}
	}

	public AS400 getAS400() {
		return as400;
	}

	public void setAS400(AS400 as400) {
		this.as400 = as400;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Connection getConn() {
		return conn;
	}
}