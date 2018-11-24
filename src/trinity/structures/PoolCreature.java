package trinity.structures;

public class PoolCreature extends PoolObject
{
	public int modelId;
	public int equipmentId;
	public int currentWaypoint;
	public long curHealth;
	public long curMana;
	public long npcFlag;
	public long unitFlags;
	public long dynamicFlags;
	
	public PoolCreature()
	{
		type = ObjectType.OBJECT_CREATURE;
	}
}
