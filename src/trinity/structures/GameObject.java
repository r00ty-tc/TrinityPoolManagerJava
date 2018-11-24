package trinity.structures;

public class GameObject
{
	public long guid;
	public int id;
	public int map;
	public int zoneId;
	public int areaId;
	public int spawnMask;
	public long phaseMask;
	public float positionX;
	public float positionY;
	public float positionZ;
	public float orientation;
	public float rotation0;
	public float rotation1;
	public float rotation2;
	public float rotation3;
	public long spawntimeSecs;
	public int animProgress;
	public int state;
	public String scriptName;
	public GameObjectTemplate goTemplate;
	
	public GameObject()
	{
		goTemplate = null;
	}
}
