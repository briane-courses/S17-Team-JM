package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import java.sql.PreparedStatement;

public class Query {
	
	/* 
	 * Run on init:
	 * 
	 * Query q = new Query("root", "p@ssword", "jdbc:mysql://localhost:3306/my_db");
	 * 
	 * On use:
	 * (this will auto open and close connections when in use)
	 * ResultSet r = q.runQuery("select * from table");
	 * 
	 * Close after use:
	 * r.close();
	 * 
	 * Note:
	 * 
	 * Query class will auto close connection during every call
	 * 
	 * runQuery & runInsertUpdateDelete uses prepared statement
	 * runStatement & runStoredProcedure do not
	 * */

	private static Query instance = null;
	
	private static final String USER = "root";
	private static final String PASS = "p@ssword";
	private static final String URL = "jdbc:mysql://localhost:3306/adm_db";
	
	private String username, password, url;
	private Connection con = null;
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;
	private Statement stmt = null;

	private Query(String username, String password, String url) throws SQLException{
			setConnection(username, password, url);
	}
	
	/**
	 * Gets instance of query class
	 * Forces singleton
	 */
	public static synchronized Query getInstance() throws SQLException{
		if(instance == null)
			instance = new Query(USER, PASS, URL);
		return instance;
	}
	
	/**
	 * 	Runs a query and returns a result set </br>
	 * 	Result set must be closed after use for security </br></br>
	 * 
	 * 	Cannot run update/insert/delete queries </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}</br>
	 * 
	 * @param query - query to be run
	 * 
	 */
	public ResultSet runQuery(String query) throws SQLException{
		connect(username, password, url);
		
		pstmt= con.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		return rs;
	}
	
	/**
	 * 	Runs a query and returns a result set </br>
	 * 	Result set must be closed after use for security </br></br>
	 * 
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query </br> </br>
	 *  Query: </br></br>
	 *  
	 *  SELECT * FROM table WHERE text = ? AND int = ?; </br></br>
	 *  Code:</br></br>
	 *  input[0] = "text"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 * 	Cannot run update/insert/delete queries </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}</br></br>
	 * 
	 * @param query - query to be run
	 * @param input - An ArrayList of objects
	 * 
	 */
	public ResultSet runQuery(String query, ArrayList<Object> input) throws SQLException{
		connect(username, password, url);
		
		pstmt = con.prepareStatement(query);
		if(input != null)
		for(int i = 0; i < input.size(); i++){

			if(input.get(i) instanceof String)
				pstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Integer)
				pstmt.setInt(i + 1,(Integer) input.get(i));
			else if(input.get(i) instanceof Float)
				pstmt.setFloat(i + 1,(Float) input.get(i));
			else if(input.get(i) instanceof Double)
				pstmt.setDouble(i + 1,(Double) input.get(i));
			else if(input.get(i) instanceof Long)
				pstmt.setLong(i + 1, (Long)input.get(i));
			else if(input.get(i) instanceof Boolean)
				pstmt.setBoolean(i + 1, (Boolean)input.get(i));
			else if(input.get(i) instanceof Enum)
				pstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Calendar)
				pstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
		}
		rs = pstmt.executeQuery();
		
		return rs;
		
	}
	/**
	 * 	Runs a query and returns true or false depending it query was a success </br></br>
	 * 
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query </br> </br>
	 *  Query: </br></br>
	 *  
	 *  UPDATE table SET name = ?, age = ? WHERE name = ?; </br></br>
	 *  Code:</br></br>
	 *  input[0] = "name"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  input[2] = "name"; // String </br>
	 * 	Cannot run queries that returns a ResultSet / Table </br>
	 * 	Use instead: {@link #runQuery()}</br></br>
	 * 
	 * @param query - query to be run
	 * @param input - An ArrayList of objects
	 * 
	 */
	public boolean runInsertUpdateDelete(String query, ArrayList<Object> input) throws SQLException{
		connect(username, password, url);
		boolean result = false;
			pstmt = con.prepareStatement(query);
		if(input != null)
		for(int i = 0; i < input.size(); i++){
	
			if(input.get(i) instanceof String)
				pstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Integer)
				pstmt.setInt(i + 1,(Integer) input.get(i));
			else if(input.get(i) instanceof Float)
				pstmt.setFloat(i + 1,(Float) input.get(i));
			else if(input.get(i) instanceof Double)
				pstmt.setDouble(i + 1,(Double) input.get(i));
			else if(input.get(i) instanceof Long)
				pstmt.setLong(i + 1, (Long)input.get(i));
			else if(input.get(i) instanceof Boolean)
				pstmt.setBoolean(i + 1, (Boolean)input.get(i));
			else if(input.get(i) instanceof Enum)
				pstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Calendar)
				pstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
		}
		result = pstmt.execute();
		
		return result;
		
	}
	
	/**
	 * 	Runs a stored procedure and returns a result set </br>
	 * 	Result set must be closed after use for security </br></br>
	 * 
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query </br> </br>
	 *  Call: </br></br>
	 *  
	 *  CALL procedureName (?, ?) </br></br>
	 *  Code:</br></br>
	 *  input[0] = "text"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 * 	Cannot run queries </br>
	 * 	Use instead: {@link #runQuery()} or {@link #runInsertUpdateDelete()}</br></br>
	 * 
	 * @param query - query to be run
	 * @param input - An ArrayList of objects
	 * 
	 */
	public ResultSet runStoredProcedure(String query, ArrayList<Object> input) throws SQLException{
		connect(username, password, url);
		ResultSet rs = null;
		
		cstmt = con.prepareCall (query);
		if(input != null)
		for(int i = 0; i < input.size(); i++){
			if(input.get(i) instanceof String)
				cstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Integer)
				cstmt.setInt(i + 1,(Integer) input.get(i));
			else if(input.get(i) instanceof Float)
				cstmt.setFloat(i + 1,(Float) input.get(i));
			else if(input.get(i) instanceof Double)
				cstmt.setDouble(i + 1,(Double) input.get(i));
			else if(input.get(i) instanceof Long)
				cstmt.setLong(i + 1, (Long)input.get(i));
			else if(input.get(i) instanceof Boolean)
				cstmt.setBoolean(i + 1, (Boolean)input.get(i));
			else if(input.get(i) instanceof Enum)
				cstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Calendar)
				cstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
			}
		rs = cstmt.executeQuery();
		
		return rs;
	}

	/**
	 * @param query - query to be run
	 * 
	 * @deprecated  Does not make use of prepared statements and is insecure </br>
	 *              Only use with scripts that cannot be run on prepared statements </br>
	 *              Else use {@link #runQuery()} instead.
	 * 
	 */
	@Deprecated
	public boolean runStatement(String query) throws SQLException{
		connect(username, password, url);
		boolean result = false;
		stmt = con.createStatement();
			
		result = stmt.execute(query);

		return result;
	}
	
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after every method call.
	 */
	public void close() throws SQLException{
		if(con != null)
			con.close();
		con = null;
		if(cstmt != null)
			cstmt.close();
		if(stmt != null)
			stmt.close();
		if(pstmt != null)
			pstmt.close();
		if(rs != null)
			rs.close();
	}
	
	/*
	 * Sets connection of the db
	 * Also only used within the this before execution of a query
	 * */
	private boolean connect(String username, String password, String url) throws SQLException{
		close();
		con = DriverManager.getConnection(url,username,password);
		return con != null;
	}
	
	/**
	 * Sets connection parameters of this class
	 * Does not actually connect to database by itself
	 * 
	 * @param username - username in database
	 * @param password - password of database
	 * @param url - connection url of database
	 */
	public void setConnection(String username, String password, String url) throws SQLException{
		close();
		setUsername(username);
		setPassword(password);
		setUrl(url);
	}
	
	/*
	 * Getters and setters
	 * */
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isConnected() {
		return con != null;
	}
}
