package trinity.structures;

public class GameObjectTemplate 
{
	public int entry;
	public int type;
	public int displayId;
	public String name;
	public String iconName;
	public String castBarCaption;
	public String unk1;
	public float size;
	public long[] data;
	public String aIName;
	public String scriptName;
	
	public GameObjectTemplate()
	{
		data = new long[24];
	}
}
