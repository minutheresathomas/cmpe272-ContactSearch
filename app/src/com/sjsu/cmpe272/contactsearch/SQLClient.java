
package com.sjsu.cmpe272.contactsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SQLClient {
	
	public SQLClient() {
		try {
			createTable();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
		
	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception 
	 */
	public ArrayList<ArrayList<String>> getResults(String v_name) throws Exception {
		String sql = "SELECT contactid,contactname,emailaddress,phone,street,city,zip,state,country  FROM contactstable1 where contactname like " + v_name + "ORDER BY contactid";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();
			ArrayList<ArrayList<String>> texts = new ArrayList<ArrayList<String>>();
			
			
			
			while (results.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(results.getString("contactid"));
				temp.add(results.getString("contactname"));
				temp.add(results.getString("emailaddress"));
				temp.add(results.getString("phone"));
				temp.add(results.getString("street"));
				temp.add(results.getString("city"));
				temp.add(results.getString("zip"));
				temp.add(results.getString("state"));
				temp.add(results.getString("country"));
				texts.add(temp);
			}
			
			return texts;
		} finally {
			if (results != null) {
				results.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
		
		
	}
	
	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception 
	 */
	public Map<String,Map<String,String>>  getContactResults(String v_name, String column) throws Exception {
		Random generator = new Random();
		
		//String sql = "SELECT contactid,contactname,emailaddress,phone,street,city,zip,state,country  FROM contactstable1 where lower(contactname)  ILIKE '" + v_name + "' ORDER BY contactid";
		String sql = "SELECT contactid,contactname,emailaddress,phone,street,city,zip,state,country  FROM contactstable1 where lower("+column+")  ILIKE '" + v_name + "%' ORDER BY contactid";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		Map<String,String> resultParamsMap = new HashMap<String,String>();
		Map<String,Map<String,String>> resultParamsListMap = new HashMap<String,Map<String,String>> ();
		//ArrayList<Map<String,String>> resultParamsList = new ArrayList<Map<String,String>>();

		
		 
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();
			if (!results.isBeforeFirst() ) {    
				 System.out.println("No data retrieved"); 
				} 
		//	ArrayList<ArrayList<String>> texts = new ArrayList<ArrayList<String>>();
		     
			
			//resultParamsMap.put("field","value");
			int i=0;
			while (results.next()) {
				i++;
				 System.out.println("Creating new hashmap - results - " + i); 
			resultParamsMap=new HashMap<String,String>();
//			resultParamsMap.put("contactid",results.getString("contactid"));
//			resultParamsMap.put("contactname",results.getString("contactname"));
//			resultParamsMap.put("emailaddress",results.getString("emailaddress"));
//			resultParamsMap.put("phone",results.getString("phone"));
//			resultParamsMap.put("city",results.getString("city"));
//			resultParamsMap.put("zip",results.getString("zip"));
//			resultParamsMap.put("state",results.getString("state"));
//			resultParamsMap.put("country",results.getString("country"));
//		
				resultParamsMap.put("ID",results.getString("contactid"));
				resultParamsMap.put("Name",results.getString("contactname"));
				resultParamsMap.put("Email Address",results.getString("emailaddress"));
				resultParamsMap.put("Phone",results.getString("phone"));
				resultParamsMap.put("City",results.getString("city"));
				resultParamsMap.put("Zip",results.getString("zip"));
				resultParamsMap.put("State",results.getString("state"));
				resultParamsMap.put("Country",results.getString("country"));
				resultParamsListMap.put( new Integer(generator.nextInt(100)).toString(), resultParamsMap);
				//resultParamsList.add();
			
	
			
//			while (results.next()) {
//				ArrayList<String> temp = new ArrayList<String>();
//				temp.add(results.getString("contactid"));
//				temp.add(results.getString("contactname"));
//				temp.add(results.getString("emailaddress"));
//				temp.add(results.getString("phone"));
//				temp.add(results.getString("phone"));
//				temp.add(results.getString("city"));
//				temp.add(results.getString("zip"));
//				temp.add(results.getString("state"));
//				temp.add(results.getString("country"));
//				texts.add(temp);
//			}
//			
//			return texts;
			} 
		}
		finally {
			if (results != null) {
				results.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
		return resultParamsListMap;
	}
	
	
	/**
	 * Insert text into PostgreSQL
	 * 
	 * param posts List of Strings of text to insert
	 * @return number of rows affected
	 * @throws Exception 
	 * @throws Exception 
	 */
	public int addEmployees() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			

					
			connection = getConnection();
			connection.setAutoCommit(false);
			String sql="";
			sql = "INSERT INTO contactstable1 (contactid, contactname, emailaddress, phone, street, city, zip, state, country) VALUES (1001,'Minu Theresa Thomas','minutheresathomas@gmail.com', '4085250000', '1550 Technology Dr','San Jose', '95100', 'CA', 'United States')";
			statement = connection.prepareStatement(sql);
			statement.execute();	
			statement.close();
			connection.commit();
			
			sql = "INSERT INTO contactstable1 (contactid, contactname, emailaddress, phone, street, city, zip, state, country) VALUES (1002,'Sujith Joseph','sujithjoseph@gmail.com', '4085252652', '1550 Technology Dr','San Jose', '95110', 'CA', 'United States')";

			statement = connection.prepareStatement(sql);
			statement.execute();
			statement.close();
			connection.commit();
			
			sql = "INSERT INTO contactstable1 (contactid, contactname, emailaddress, phone, street, city, zip, state, country) VALUES (1003,'Suresh Joseph','sureshjoseph@gmail.com', '4087927377', '30 Independence Way','Jersey City', '10001', 'NJ', 'United States')";

			statement = connection.prepareStatement(sql);
			statement.execute();
			statement.close();
			connection.commit();
			
			sql = "INSERT INTO contactstable1 (contactid, contactname, emailaddress, phone, street, city, zip, state, country) VALUES (1004,'Sony Joseph','sonyjoseph@gmail.com', '4089057377', '30 Church Rd','Urbana', '70001', 'IL', 'United States')";

			statement = connection.prepareStatement(sql);
			statement.execute();
			statement.close();
			connection.commit();
			
			sql = "INSERT INTO contactstable1 (contactid, contactname, emailaddress, phone, street, city, zip, state, country) VALUES (1005,'Dennis Thomas','dennisthomas@gmail.com', '4087998664', '2nd Main St, MG Rd','Bangalore', '695011', 'Karnataka', 'India')";

			statement = connection.prepareStatement(sql);
			statement.execute();
			statement.close();
			connection.commit();
			
			return 5;
		} catch (SQLException e) {
			SQLException next = e.getNextException();
			
			if (next != null) {
				throw next;
			}
			
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}
	

	private static Connection getConnection() throws Exception {
		Map<String, String> env = System.getenv();
		
		if (env.containsKey("VCAP_SERVICES")) {
			// we are running on cloud foundry, let's grab the service details from vcap_services
			JSONParser parser = new JSONParser();
			JSONObject vcap = (JSONObject) parser.parse(env.get("VCAP_SERVICES"));
			JSONObject service = null;
			
			// We don't know exactly what the service is called, but it will contain "postgresql"
			for (Object key : vcap.keySet()) {
				String keyStr = (String) key;
				if (keyStr.toLowerCase().contains("postgresql")) {
					service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0);
					break;
				}
			}
			
			if (service != null) {
				JSONObject creds = (JSONObject) service.get("credentials");
				String name = (String) creds.get("name");
				String host = (String) creds.get("host");
				Long port = (Long) creds.get("port");
				String user = (String) creds.get("user");
				String password = (String) creds.get("password");
				
				String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
				
				return DriverManager.getConnection(url, user, password);
			}
		}
		
		throw new Exception("No PostgreSQL service URL found. Make sure you have bound the correct services to your app.");
	}
	
	/**
	 * Create the posts table if it doesn't already exist
	 * 
	 * @throws Exception
	 */
	private void createTable() throws Exception {
		String sql = "CREATE TABLE IF NOT EXISTS contactstable1 (" +
						"contactid integer unique NOT NULL, " +
						"contactname text," +
						"emailaddress text," +
						"phone text," +
						"street text," +
						"city text," +
						"zip text," +
						"state text," +
						"country text" +
					 ");";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			addEmployees();
		} finally {			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}
}
