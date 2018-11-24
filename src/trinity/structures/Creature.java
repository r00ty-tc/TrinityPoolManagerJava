package trinity.structures;

public class Creature 
{
	public long guid;
	public int id;
	public int map;
	public int zoneId;
	public int areaId;
	public int spawnMask;
	public long phaseMask;
	public int modelId;
	public int equipmentId;
	public float positionX;
	public float positionY;
	public float positionZ;
	public float orientation;
	public long spawntimeSecs;
	public float spawnDist;
	public int currentWaypoint;
	public long curHealth;
	public long curMana;
	public int movementType;
	public long npcFlag;
	public long unitFlags;
	public long dynamicFlags;
	public String scriptName;
	public CreatureTemplate creatureTemplate;
	
	public Creature()
	{
		creatureTemplate = null;
	}
}
