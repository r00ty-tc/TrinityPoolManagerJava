package trinity.structures;

public class CreatureTemplate 
{
	public int entry;
	public int[] difficultyEntry;
	public long[] killCredit;
	public int[] modelid;
	public String name;
	public String subName;
	public String iconName;
	public int gossipMenuId;
	public int minLevel;
	public int maxLevel;
	public int exp;
	public int faction;
	public long npcFlag;
	public float speedWalk;
	public float speedRun;
	public float scale;
	public int rank;
	public int dmgSchool;
	public long baseAttackTime;
	public long rangeAttackTime;
	public float baseVariance;
	public float rangeVariance;
	public int unit_class;
	public long[] unitFlags;
	public long dynamicFlags;
	public int family;
	public int trainerType;
	public int trainerSpell;
	
	public CreatureTemplate()
	{
		difficultyEntry = new int[3];
		killCredit = new long[2];
		modelid = new int[4];
		unitFlags = new long[2];
	}
}
