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

	private static Query instance = null;
	
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
	
	private String username, password, url;
	private Connection con = null;
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;
	private Statement stmt = null;

	private Query(String username, String password, String url){
		try {
			setConnection(username, password, url);
		} catch (SQLException e) {
			// shouldnt ever error unless user / pass / url is wrong
			e.printStackTrace();
		}
	}
	
	public static synchronized Query getInstance(String username, String password, String url){
		if(instance == null)
			instance = new Query(username, password, url);
		return instance;
	}
	
	public static synchronized Query getInstance(){
		return instance;
	}
	
	/*
	 * Runs a query and returns a result set
	 * Result set must be closed after use for security
	 * 
	 * CANNOT run update/insert/delete queries
	 * use method: runInsertUpdateDelete()
	 * */
	public ResultSet runQuery(String query) throws SQLException{
		connect(username, password, url);
		
		pstmt= con.prepareStatement(query);
		rs = pstmt.executeQuery();
		/*
		int numColumns = rs.getMetaData().getColumnCount();
		ArrayList<String> columns = new ArrayList<>();
		
		for ( int i = 1 ; i <= numColumns ; i++ )
			columns.add(rs.getMetaData().getColumnLabel(i));
		
		result = new QueryResult(columns);
		
		while(rs.next()){
	            for ( int i = 1 ; i <= numColumns ; i++ ) {
	               result.addCell(rs.getObject(i));
	            }
			}
			*/
		
		return rs;
	}
	
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
			else if(input.get(i) instanceof Enum)
				pstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Calendar)
				pstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
		}
		rs = pstmt.executeQuery();
		
		return rs;
		
	}

	/*
	 * Runs a query and returns true or false depending it query was a success
	 * 
	 * CANNOT run queries that returns a ResultSet
	 * use method: runQuery()
	 * */
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
			else if(input.get(i) instanceof Enum)
				pstmt.setString(i + 1,(String) input.get(i));
			else if(input.get(i) instanceof Calendar)
				pstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
		}
		result = pstmt.execute();
		
		return result;
		
	}
	
	/*
	 * To be used with stored procedures in the db
	 * Cannot run standard queries
	 * */
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
				else if(input.get(i) instanceof Enum)
					cstmt.setString(i + 1,(String) input.get(i));
				else if(input.get(i) instanceof Calendar)
					cstmt.setDate(i + 1,(Date) ((Calendar) input.get(i)).getTime());
			}
			rs = cstmt.executeQuery();
			/*
			int numColumns = rs.getMetaData().getColumnCount();
			ArrayList<String> columns = new ArrayList<>();
			
			for ( int i = 1 ; i <= numColumns ; i++ )
				columns.add(rs.getMetaData().getColumnLabel(i));
			
			result = new QueryResult(columns);
			
			while(rs.next()){
		            for ( int i = 1 ; i <= numColumns ; i++ ) {
		               result.addCell(rs.getObject(i));
		            }
				}
				*/
		
		return rs;
	}

	/*
	 * DO NOT USE / FOR TESTING ONLY
	 * Does not make use of prepared statements and is insecure :(
	 * 
	 * Its only here because some scripts cannot be run on prepared statements
	 * */
	public boolean runStatement(String query) throws SQLException{
		connect(username, password, url);
		boolean result = false;
		stmt = con.createStatement();
			
		result = stmt.execute(query);

		return result;
	}
	
	/*
	 * Used for closing connection
	 * It should be manually call after every method call
	 * */
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
	
	/*
	 * Changes connection parameters of this class
	 * Does not actually connect by itself
	 * */
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
