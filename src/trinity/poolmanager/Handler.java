package trinity.poolmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import trinity.structures.*;

public class Handler 
{
	private Db db;
	private Map<Integer, Map<Integer, PoolEntry>> mapPoolData;
	private Map<Integer, GameObjectTemplate> goTemplateData;
	private Map<Long, GameObject> goData;
	private Map<Integer, Map<Long, GameObject>> goByZone;
	private Map<Integer, CreatureTemplate> creatureTemplateData;
	private Map<Long, Creature> creatureData;
	private Map<Integer, Map<Long, Creature>> creatureByZone;
	private Map<Integer, Map<Long, SpawnPoint>> mapSpawnData;
	
	public Handler(Db database)
	{
		db = database;
	}
	
	public Boolean LoadData()
	{
		if (!loadPoolData())
			return false;
		if (!loadGameObjectTemplateData())
			return false;
		if (!loadCreatureTemplateData())
			return false;
		if (!loadGameObjectData())
			return false;
		if (!loadCreatureData())
			return false;
		if (!loadMapSpawnPointData())
			return false;
		if (!loadMapPoolCreatures())
			return false;
		if (!loadMapPoolGameObjects())
			return false;
		
		return true;
	}
	
	public Boolean loadPoolData()
	{
		ResultSet results = db.GetPoolEntryQuery();
		if (results == null)
			return false;
		
		mapPoolData = new TreeMap<Integer, Map<Integer, PoolEntry>>(); 
		try 
		{
			while (results.next())
			{
				PoolEntry thisPool = Adapters.getPoolData(results);
				Map<Integer, PoolEntry> poolData = getMapPools(thisPool.map, true);				
				poolData.put(thisPool.poolId, thisPool);
			}
			return loadPoolHierarchyData();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private Boolean loadPoolHierarchyData()
	{
		ResultSet results = db.GetPoolHierarchyQuery();
		if (results == null)
			return false;
		
		try 
		{
			while (results.next())
			{
				// Get the data from the result
				int map = results.getInt("map");
				int poolId = results.getInt("poolId");
				int childPoolId = results.getInt("childPoolId");
				float Chance = results.getFloat("chance");
				
				// Get the entry for this map
				Map<Integer, PoolEntry> poolData = getMapPools(map, false);
				if (poolData == null)
					return false;
				
				// Get the parent pool
				PoolEntry parentPool = poolData.get(poolId);
				if (parentPool == null)
					return false;
				
				// Get the child pool
				PoolEntry childPool = poolData.get(childPoolId);
				if (childPool == null)
					return false;
				
				parentPool.childPools.add(childPool);
				childPool.parentPool = parentPool;
			}
			
			// Now update root pools
			for (Map<Integer, PoolEntry> poolValues : mapPoolData.values())
			{
				for (PoolEntry pool : poolValues.values())
				{
					if (pool.parentPool == null)
					{
						for (PoolEntry childPool : pool.childPools)
						{
							processChildPools(childPool, pool);
						}
					}
						
				}
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void processChildPools(PoolEntry pool, PoolEntry rootPool)
	{
		pool.rootPool = rootPool;
		
		// Deeper down the rabbit hole.
		for (PoolEntry childPool : pool.childPools)
		{
			processChildPools(childPool, pool);
		}
	}
		
	public Boolean loadGameObjectTemplateData()
	{
		ResultSet results = db.GetGameObjectTemplateQuery();
		
		if (results == null)
			return false;
		
		goTemplateData = new TreeMap<Integer, GameObjectTemplate>();
		
		try 
		{
			int currentRow = 1;
			while (results.next())
			{
				GameObjectTemplate thisTemplate = Adapters.getGameObjectTemplateData(results);
				goTemplateData.put(thisTemplate.entry, thisTemplate);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Boolean loadGameObjectData()
	{
		ResultSet results = db.GetGameObjectQuery();
		
		if (results == null)
			return false;
		
		goData = new TreeMap<Long, GameObject>();
		goByZone = new TreeMap<Integer, Map<Long,GameObject>>();
		
		try 
		{
			int currentRow = 1;
			while (results.next())
			{
				GameObject thisGo = Adapters.getGameObjectData(results);
				
				// Get a reference to the template too
				thisGo.goTemplate = goTemplateData.get(thisGo.id);
				
				goData.put(thisGo.guid, thisGo);
				Map<Long, GameObject> goZoneMap = goByZone.get(thisGo.zoneId);
				if (goZoneMap == null)
				{
					goZoneMap = new TreeMap<Long, GameObject>();
					goByZone.put(thisGo.zoneId, goZoneMap);
				}
				goZoneMap.put(thisGo.guid, thisGo);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
		
	public Boolean loadCreatureTemplateData()
	{
		ResultSet results = db.GetCreatureTemplateQuery();
		
		if (results == null)
			return false;
		
		creatureTemplateData = new TreeMap<Integer, CreatureTemplate>();
		
		try 
		{
			int currentRow = 1;
			while (results.next())
			{
				CreatureTemplate thisTemplate = Adapters.getCreatureTemplateData(results);
				creatureTemplateData.put(thisTemplate.entry, thisTemplate);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Boolean loadCreatureData()
	{
		ResultSet results = db.GetCreatureQuery();
		
		if (results == null)
			return false;
		
		creatureData = new TreeMap<Long, Creature>();
		creatureByZone = new TreeMap<Integer, Map<Long,Creature>>();
		
		try 
		{
			int currentRow = 1;
			while (results.next())
			{
				Creature thisCreature = Adapters.getCreatureData(results);
				
				// Add reference to template data
				thisCreature.creatureTemplate = creatureTemplateData.get(thisCreature.id);
				
				creatureData.put(thisCreature.guid, thisCreature);
				Map<Long, Creature> creatureZoneMap = creatureByZone.get(thisCreature.zoneId);
				if (creatureZoneMap == null)
				{
					creatureZoneMap = new TreeMap<Long, Creature>();
					creatureByZone.put(thisCreature.zoneId, creatureZoneMap);
				}
				creatureZoneMap.put(thisCreature.guid, thisCreature);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Boolean loadMapSpawnPointData()
	{
		ResultSet results = db.GetSpawnPointQuery();
		if (results == null)
			return false;
		
		mapSpawnData = new TreeMap<Integer, Map<Long, SpawnPoint>>(); 
		try 
		{
			while (results.next())
			{
				SpawnPoint thisSpawnPoint = Adapters.getSpawnPointData(results);
				Map<Long, SpawnPoint> spawnData = getMapSpawnPoints(thisSpawnPoint.map, true);				
				spawnData.put(thisSpawnPoint.pointId, thisSpawnPoint);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean loadMapPoolCreatures()
	{
		ResultSet results = db.GetMapPoolCreatureQuery();
		if (results == null)
			return false;
		
		try 
		{
			while (results.next())
			{
				PoolCreature thisCreature = Adapters.getPoolCreatureData(results);
				PoolEntry thisPool = getPool(thisCreature.map, thisCreature.poolId);
				
				if (thisPool != null && !thisPool.creatures.contains(thisCreature))
					thisPool.creatures.add(thisCreature);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	public Boolean loadMapPoolGameObjects()
	{
		ResultSet results = db.GetMapPoolGameObjectQuery();
		if (results == null)
			return false;
		
		try 
		{
			while (results.next())
			{
				PoolGameObject thisGo = Adapters.getPoolGameObjectData(results);
				PoolEntry thisPool = getPool(thisGo.map, thisGo.poolId);
				
				if (thisPool != null && !thisPool.gameObjects.contains(thisGo))
					thisPool.gameObjects.add(thisGo);
			}
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}

	public PoolEntry getPool(int mapId, int poolId)
	{
		Map<Integer, PoolEntry> mapPools = getMapPools(mapId, false);
		if (mapPools == null)
			return null;
		
		return mapPools.get(poolId);
	}
	
	private Map<Integer, PoolEntry> getMapPools(Integer mapId, Boolean createIfMissing)
	{
		Map<Integer, PoolEntry> mapEntry = mapPoolData.get(mapId);
		if (mapEntry == null && createIfMissing)
		{
			mapEntry = new TreeMap<Integer, PoolEntry>();
			mapPoolData.put(mapId, mapEntry);
		}
		
		return mapEntry;
	}
	
	private Map<Long, SpawnPoint> getMapSpawnPoints(Integer mapId, Boolean createIfMissing)
	{
		Map<Long, SpawnPoint> mapSpawn = mapSpawnData.get(mapId);
		if (mapSpawn == null && createIfMissing)
		{
			mapSpawn = new TreeMap<Long, SpawnPoint>();
			mapSpawnData.put(mapId, mapSpawn);
		}
		
		return mapSpawn;
	}	
}
