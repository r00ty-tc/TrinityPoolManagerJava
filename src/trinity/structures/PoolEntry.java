package trinity.structures;

import java.util.ArrayList;
import java.util.List;

public class PoolEntry 
{
	public int map;
	public int poolId;
	public int poolType;
	public long phaseMask;
	public int spawnMask;
	public long minLimit;
	public long maxLimit;
	public int movementType;
	public float spawnDist;
	public long spawntimeSecsMin;
	public long spawntimeSecsMax;
	public long spawntimeSecsFast;
	public long corpsetimeSecsLoot;
	public long corpsetimeSecsNoLoot;
	public String description;
	public float chance;
	public PoolEntry parentPool;
	public PoolEntry rootPool;
	public List<PoolEntry> childPools;
	public List<PoolCreature> creatures;
	public List<PoolGameObject> gameObjects;
	
	public boolean IsRootPool()
	{
		return (parentPool == null);
	}
	
	public PoolEntry()
	{
		childPools = new ArrayList<PoolEntry>();
		creatures = new ArrayList<PoolCreature>();
		gameObjects = new ArrayList<PoolGameObject>();
	}
}