package trinity.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Manager 
{
    private Connection connect = null;
    	
	public Connection GetConnection()
	{
		return connect;
	}
	
	public SQLConnectResult Connect(String server, String database, String user, String password)
	{
		SQLConnectResult result = connectSQL("com.mysql.cj.jdbc.Driver", server, database, user, password);
		if (result == SQLConnectResult.RESULT_BADCLASS)
		{
			result = connectSQL("org.mariadb.jdbc.Driver", server, database, user, password);
			if (result == SQLConnectResult.RESULT_OK)
				result = SQLConnectResult.RESULT_MARIADB_OK;
		}
		if (result == SQLConnectResult.RESULT_OK)
			result = SQLConnectResult.RESULT_MYSQL_OK;
		return result;
	}
	
	private SQLConnectResult connectSQL(String className, String server, String database, String user, String password)
	{
		try 
		{			
			Class.forName(className);
			connect = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?"
                                                + "user=" + user + "&password=" + password);
			return SQLConnectResult.RESULT_OK;
		}
		catch (SQLException e)
		{
			if (e.getErrorCode() == 1045)	// ER_ACCESS_DENIED_ERROR
				return SQLConnectResult.RESULT_CREDENTIALERROR;
			
			if (e.getErrorCode() == 1044)	// ER_DBACCESS_DENIED_ERROR
				return SQLConnectResult.RESULT_DBACCESSDENIED;
			
			System.out.println(e.getErrorCode());
			return SQLConnectResult.RESULT_CONNECTERR;
		}
		catch (ClassNotFoundException e)
		{
			return SQLConnectResult.RESULT_BADCLASS;
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			return SQLConnectResult.RESULT_OTHEREXCEPTION;
		}		
	}
}
