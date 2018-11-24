package trinity.structures;

public class PoolGameObject extends PoolObject 
{
	public int animProgress;
	public int state;
	
	public PoolGameObject()
	{
		type = ObjectType.OBJECT_GAMEOBJECT;
	}
}
