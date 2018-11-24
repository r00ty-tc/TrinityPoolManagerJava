package trinity.poolmanager;
import java.sql.*;

import trinity.db.mysql.*;

public class Db
{
    private Connection db = null;
    
	public void dispose()
	{
		try 
		{
			if (db != null)
				db.close();
		}
		catch (SQLException e) 
		{
		}
	}
	
	public SQLConnectResult Connect(String server, String database, String user, String password)
	{
		Manager mysql = new Manager();
		SQLConnectResult result =  mysql.Connect(server, database, user, password);
		if (result == SQLConnectResult.RESULT_MARIADB_OK || result == SQLConnectResult.RESULT_MYSQL_OK)
			db = mysql.GetConnection();
		
		return result;
	}
	
	public ResultSet GetPoolEntryQuery()
	{
		return GetAllRecords("mappool_template");
	}
	
	public ResultSet GetPoolHierarchyQuery()
	{
		return GetAllRecords("mappool_hierarchy");
	}
	
	public ResultSet GetGameObjectTemplateQuery()
	{
		return GetAllRecords("gameobject_template");
	}

	public ResultSet GetGameObjectQuery()
	{
		return GetAllRecords("gameobject");
	}
	
	public ResultSet GetCreatureTemplateQuery()
	{
		return GetAllRecords("creature_template");
	}

	public ResultSet GetCreatureQuery()
	{
		return GetAllRecords("creature");
	}

	public ResultSet GetSpawnPointQuery()
	{
		return GetAllRecords("mappool_spawnpoints");
	}

	public ResultSet GetMapPoolCreatureQuery()
	{
		return GetAllRecords("mappool_creature");
	}
	
	public ResultSet GetMapPoolGameObjectQuery()
	{
		return GetAllRecords("mappool_gameobject");
	}
	
	public String GetGameObjectName(Integer entry)
	{
		try 
		{
			PreparedStatement getGoEntryQuery = db.prepareStatement("SELECT name FROM gameobject_template WHERE entry = ?");
			getGoEntryQuery.setInt(1, entry);
			ResultSet results = getGoEntryQuery.executeQuery();
			if (results.next())
				return results.getString("name");
			else
				return null;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public ResultSet GetAllRecords(String tableName)
	{
		// Queries all pool data and returns result set reference
		try 
		{
			PreparedStatement getAllRecordsQuery = db.prepareStatement("SELECT * FROM " + tableName);
			ResultSet results = getAllRecordsQuery.executeQuery();
			return results;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}				
	}
	
	public int GetRecordCount(String tableName)
	{
		// Return number of rows in gameobject table
		try 
		{
			PreparedStatement getPoolHierarchyQuery = db.prepareStatement("SELECT COUNT(*) AS size FROM " + tableName);
			ResultSet results = getPoolHierarchyQuery.executeQuery();
			if (!results.next())
				return 0;
				
			return results.getInt("size");
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}		
	}	
}
