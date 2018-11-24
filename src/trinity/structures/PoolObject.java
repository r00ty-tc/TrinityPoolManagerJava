package trinity.structures;

public class PoolObject 
{
	public ObjectType type;
	public int map;
	public int poolId;
	public int entry;
	public float chance;
	
	public PoolCreature AsCreature()
	{
		if (type != ObjectType.OBJECT_CREATURE)
			return null;
		
		return (PoolCreature)this;
	}
	
	public PoolGameObject AsGameObject()
	{
		if (type != ObjectType.OBJECT_GAMEOBJECT)
			return null;
		
		return (PoolGameObject)this;
	}
}
