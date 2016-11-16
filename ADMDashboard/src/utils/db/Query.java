package utils.db;

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
	 * On use:
	 * (this will auto open and close connections when in use)
	 * ResultSet r = q.runQuery("select * from table");
	 * 
	 * always close after use:
	 * r.close();
	 * 
	 * Note:
	 * See comments for more info per method
	 * Comments are viewable as tool-tips when auto completing method names
	 * */

	private static Query instance = null;
	
	public static final String[] ACCESS1 = {"sofengg","sofenggADM!"};
	public static final String[] ACCESS2 = {"root","p@ssword"};
	
	private static final String USER = ACCESS2[0];
	private static final String PASS = ACCESS2[1];
	private static final String URL = "jdbc:mysql://localhost:3306/adm";
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private String username, password, url, driver;
	private Connection con = null;
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;
	private Statement stmt = null;

	private Query(String username, String password, String url){
				try {
					setConnection(username, password, url, DRIVER);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * Gets instance of query class </br>
	 * Forces singleton </br></br>
	 */
	public static synchronized Query getInstance(){
		if(instance == null)
			instance = new Query(USER, PASS, URL);
		return instance;
	}
	
	/**
	 * 	Runs a query and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br></br>
	 * 
	 * 	Cannot run update/insert/delete queries. </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param query - Query to be run.
	 * 
	 */
	public ResultSet runQuery(String query) throws SQLException{
		if(connect(username, password, url)){
			pstmt= con.prepareStatement(query);
			rs = pstmt.executeQuery();
			}
		return rs;
	}
	
	/**
	 * 	Runs a query and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br>
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query. Example: </br></br>
	 *  Query: </br>
	 *  <i>
	 *  SELECT * FROM table WHERE text = ? AND int = ?; </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input[0] = "text"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  </i>
	 * 	Cannot run update/insert/delete queries. </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param query - Query to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public ResultSet runQuery(String query, ArrayList<Object> input) throws SQLException{
		if(connect(username, password, url)){
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
						pstmt.setString(i + 1,((Enum) input.get(i)).toString());
					else if(input.get(i) instanceof Calendar)
						pstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
					}
			rs = pstmt.executeQuery();
			}
		return rs;
		
	}
	/**
	 * 	Runs a query and returns true or false depending on whether query was a success. </br>
	 *  Uses array list of objects as its input but can be set to null if no input is needed.</br> 
	 *  Arraylist of input should be ordered by the '?' in the query. Example: </br></br>
	 *  Query: </br>
	 *  <i>
	 *  UPDATE table SET name = ?, age = ? WHERE name = ?; </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input[0] = "name"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  input[2] = "name"; // String </br>
	 *  </i>
	 * 	Cannot run queries that returns a ResultSet / Table. </br>
	 * 	Use instead: {@link #runQuery()}.</br></br>
	 * 
	 * @param query - query to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public boolean runInsertUpdateDelete(String query, ArrayList<Object> input) throws SQLException{
		boolean result = connect(username, password, url);
		if(result){
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
						pstmt.setString(i + 1,((Enum) input.get(i)).toString());
					else if(input.get(i) instanceof Calendar)
						pstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
					}
			result = pstmt.execute();
			}
		return result;
		
	}
	
	/**
	 * 	Runs a stored procedure and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br>
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query. Example: </br></br>
	 *  Call: </br>
	 *  <i>
	 *  CALL procedureName (?, ?) </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input[0] = "text"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  </i>
	 * 	Cannot run queries. </br>
	 * 	Use instead: {@link #runQuery()} or {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param procedure - procedure to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public ResultSet runStoredProcedure(String procedure, ArrayList<Object> input) throws SQLException{
		if(connect(username, password, url)){
			cstmt = con.prepareCall (procedure);
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
					pstmt.setString(i + 1,((Enum) input.get(i)).toString());
				else if(input.get(i) instanceof Calendar)
					cstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
				}
			rs = cstmt.executeQuery();
			}
		return rs;
	}

	/**
	 * Runs a query</br></br>
	 * 
	 * @param query - query to be run.
	 * 
	 * @deprecated  Does not make use of prepared statements and is insecure.</br>
	 *              Only use with scripts that cannot be run on prepared statements.</br>
	 *              Else use {@link #runQuery()}, {@link #runInsertUpdateDelete()} or {@link #runStoredProcedure()} instead. </br></br>
	 * 
	 */
	@Deprecated
	public boolean runStatement(String query) throws SQLException{
		boolean result = connect(username, password, url);
		if(result){
			stmt = con.createStatement();
			result = stmt.execute(query);
		}
		return result;
	}
	
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after every method call. </br></br>
	 */
	public void close() throws SQLException{
		if(rs != null){
			rs.close();
			rs = null;
			}
		if(pstmt != null){
			pstmt.close();
			pstmt = null;
			}
		if(cstmt != null){
			cstmt.close();
			cstmt = null;
			}
		if(stmt != null){
			stmt.close();
			stmt = null;
			}
		if(con != null){
			con.close();
			con = null;
			}
	}
	
	/**
	 * Sets connection of the database. </br>
	 * Must be used in the beginning of every method that accesses the database. </br></br>
	 * <i>Driver URL may be changed by using setDriver<i> </br></br>
	 * @param username - username in database
	 * @param password - password of database
	 * @param url - connection url of database
	 */
	private boolean connect(String username, String password, String url) throws SQLException{
		close();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(url,username,password);
		return con != null && !con.isClosed();
	}
	
	/**
	 * Sets connection parameters of this class. </br>
	 * Does not actually connect to database by itself. </br></br>
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
	/**
	 * Sets connection parameters of this class. </br>
	 * Does not actually connect to database by itself. </br></br>
	 * 
	 * @param username - username in database
	 * @param password - password of database
	 * @param url - connection url of database
	 * @param driver - url of driver
	 */
	public void setConnection(String username, String password, String url, String driver) throws SQLException{
		close();
		setUsername(username);
		setPassword(password);
		setUrl(url);
		setDriver(driver);
	}
	/**
	 * Returns true if database connection is closed. </br>
	 */
	public boolean isDisconnected() throws SQLException {
		if(con != null)
			return con.isClosed();
		return true;
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

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}
