package trinity.poolmanager;

import java.sql.ResultSet;
import java.sql.SQLException;

import trinity.structures.Creature;
import trinity.structures.CreatureTemplate;
import trinity.structures.GameObject;
import trinity.structures.GameObjectTemplate;
import trinity.structures.PoolCreature;
import trinity.structures.PoolEntry;
import trinity.structures.PoolGameObject;
import trinity.structures.PoolObject;
import trinity.structures.SpawnPoint;

public class Adapters 
{
	protected static PoolEntry getPoolData(ResultSet results) throws SQLException
	{
		PoolEntry thisEntry = new PoolEntry();
		thisEntry.map = results.getInt("map");
		thisEntry.poolId = results.getInt("poolId");
		thisEntry.poolType = results.getInt("poolType");
		thisEntry.phaseMask = results.getLong("phaseMask");
		thisEntry.spawnMask = results.getInt("spawnMask");
		thisEntry.minLimit = results.getLong("minLimit");
		thisEntry.maxLimit = results.getLong("maxLimit");
		thisEntry.movementType = results.getInt("movementType");
		thisEntry.spawnDist = results.getFloat("spawnDist");
		thisEntry.spawntimeSecsMin = results.getLong("spawntimeSecsMin");
		thisEntry.spawntimeSecsMax = results.getLong("spawntimeSecsMax");
		thisEntry.spawntimeSecsFast = results.getLong("spawntimeSecsFast");
		thisEntry.corpsetimeSecsLoot = results.getLong("corpsetimeSecsLoot");
		thisEntry.corpsetimeSecsNoLoot = results.getLong("corpsetimeSecsNoLoot");
		thisEntry.description = results.getString("description");
		return thisEntry;
	}

	protected static GameObjectTemplate getGameObjectTemplateData(ResultSet results) throws SQLException
	{
		GameObjectTemplate goTemplate = new GameObjectTemplate();
		goTemplate.entry = results.getInt("entry");
		goTemplate.type = results.getInt("type");
		goTemplate.displayId = results.getInt("displayId");
		goTemplate.name = results.getString("name");
		goTemplate.iconName = results.getString("iconName");
		goTemplate.castBarCaption = results.getString("castBarCaption");
		goTemplate.unk1 = results.getString("unk1");
		goTemplate.size = results.getFloat("size");
		goTemplate.data[0] = results.getLong("Data0");
		goTemplate.data[1] = results.getLong("Data1");
		goTemplate.data[2] = results.getLong("Data2");
		goTemplate.data[3] = results.getLong("Data3");
		goTemplate.data[4] = results.getLong("Data4");
		goTemplate.data[5] = results.getLong("Data5");
		goTemplate.data[6] = results.getLong("Data6");
		goTemplate.data[7] = results.getLong("Data7");
		goTemplate.data[8] = results.getLong("Data8");
		goTemplate.data[9] = results.getLong("Data9");
		goTemplate.data[10] = results.getLong("Data10");
		goTemplate.data[11] = results.getLong("Data11");
		goTemplate.data[12] = results.getLong("Data12");
		goTemplate.data[13] = results.getLong("Data13");
		goTemplate.data[14] = results.getLong("Data14");
		goTemplate.data[15] = results.getLong("Data15");
		goTemplate.data[16] = results.getLong("Data16");
		goTemplate.data[17] = results.getLong("Data17");
		goTemplate.data[18] = results.getLong("Data18");
		goTemplate.data[19] = results.getLong("Data19");
		goTemplate.data[10] = results.getLong("Data20");
		goTemplate.data[21] = results.getLong("Data21");
		goTemplate.data[22] = results.getLong("Data22");
		goTemplate.data[23] = results.getLong("Data23");
		goTemplate.aIName = results.getString("aIName");
		return goTemplate;
	}

	protected static GameObject getGameObjectData(ResultSet results) throws SQLException
	{
		GameObject thisGo = new GameObject();
		
		thisGo.guid = results.getLong("guid");
		thisGo.id = results.getInt("id");
		thisGo.map = results.getInt("map");
		thisGo.zoneId = results.getInt("zoneId");
		thisGo.areaId = results.getInt("areaId");
		thisGo.spawnMask = results.getInt("spawnMask");
		thisGo.phaseMask = results.getLong("phaseMask");
		thisGo.positionX = results.getFloat("position_x");
		thisGo.positionY = results.getFloat("position_y");
		thisGo.positionZ = results.getFloat("position_z");
		thisGo.orientation = results.getFloat("orientation");
		thisGo.rotation0 = results.getFloat("rotation0");
		thisGo.rotation1 = results.getFloat("rotation1");
		thisGo.rotation2 = results.getFloat("rotation2");
		thisGo.rotation3 = results.getFloat("rotation3");
		thisGo.spawntimeSecs = results.getLong("spawntimesecs");
		thisGo.animProgress = results.getInt("animprogress");
		thisGo.state = results.getInt("state");
		thisGo.scriptName = results.getString("ScriptName");

		return thisGo;
	}

	protected static CreatureTemplate getCreatureTemplateData(ResultSet results) throws SQLException
	{
		CreatureTemplate creatureTemplate = new CreatureTemplate();
		creatureTemplate.entry = results.getInt("entry");
		creatureTemplate.difficultyEntry[0] = results.getInt("difficulty_entry_1");
		creatureTemplate.difficultyEntry[1] = results.getInt("difficulty_entry_2");
		creatureTemplate.difficultyEntry[2] = results.getInt("difficulty_entry_3");
		creatureTemplate.killCredit[0] = results.getLong("KillCredit1");
		creatureTemplate.killCredit[1] = results.getLong("KillCredit2");
		creatureTemplate.modelid[0] = results.getInt("modelid1");
		creatureTemplate.modelid[1] = results.getInt("modelid2");
		creatureTemplate.modelid[2] = results.getInt("modelid3");
		creatureTemplate.modelid[3] = results.getInt("modelid4");
		creatureTemplate.name = results.getString("name");
		creatureTemplate.subName = results.getString("subname");
		creatureTemplate.iconName = results.getString("iconName");
		creatureTemplate.gossipMenuId = results.getInt("gossip_menu_id");
		creatureTemplate.minLevel = results.getInt("minlevel");
		creatureTemplate.maxLevel = results.getInt("maxlevel");
		creatureTemplate.exp = results.getInt("exp");
		creatureTemplate.faction = results.getInt("faction");
		creatureTemplate.npcFlag = results.getLong("npcflag");
		creatureTemplate.speedWalk = results.getFloat("speed_walk");
		creatureTemplate.speedRun = results.getFloat("speed_run");
		creatureTemplate.scale = results.getFloat("scale");
		creatureTemplate.rank = results.getInt("rank");
		creatureTemplate.dmgSchool = results.getInt("dmgschool");
		creatureTemplate.baseAttackTime = results.getLong("BaseAttackTime");
		creatureTemplate.rangeAttackTime = results.getLong("RangeAttackTime");
		creatureTemplate.baseVariance = results.getFloat("BaseVariance");
		creatureTemplate.rangeVariance = results.getFloat("RangeVariance");
		creatureTemplate.unit_class = results.getInt("unit_class");
		creatureTemplate.unitFlags[0] = results.getLong("unit_flags");
		creatureTemplate.unitFlags[1] = results.getLong("unit_flags2");
		creatureTemplate.dynamicFlags = results.getLong("dynamicflags");
		creatureTemplate.family = results.getInt("family");
		creatureTemplate.trainerType = results.getInt("trainer_type");
		creatureTemplate.trainerSpell = results.getInt("trainer_spell");
		return creatureTemplate;
	}

	protected static Creature getCreatureData(ResultSet results) throws SQLException
	{
		Creature thisCreature = new Creature();
		thisCreature.guid = results.getLong("guid");
		thisCreature.id = results.getInt("id");
		thisCreature.map = results.getInt("map");
		thisCreature.zoneId = results.getInt("zoneId");
		thisCreature.areaId = results.getInt("areaId");
		thisCreature.spawnMask = results.getInt("spawnMask");
		thisCreature.phaseMask = results.getLong("phaseMask");
		thisCreature.modelId = results.getInt("modelid");
		thisCreature.equipmentId = results.getInt("equipment_id");
		thisCreature.positionX = results.getFloat("position_x");
		thisCreature.positionY = results.getFloat("position_y");
		thisCreature.positionZ = results.getFloat("position_z");
		thisCreature.orientation = results.getFloat("orientation");
		thisCreature.spawntimeSecs = results.getLong("spawntimesecs");
		thisCreature.spawnDist = results.getFloat("spawndist");
		thisCreature.currentWaypoint = results.getInt("currentwaypoint");
		thisCreature.curHealth = results.getLong("curhealth");
		thisCreature.curMana = results.getLong("curmana");
		thisCreature.movementType = results.getInt("MovementType");
		thisCreature.npcFlag = results.getLong("npcflag");
		thisCreature.unitFlags = results.getLong("unit_flags");
		thisCreature.dynamicFlags = results.getLong("dynamicflags");
		thisCreature.scriptName = results.getString("ScriptName");
		return thisCreature;
	}
	
	protected static SpawnPoint getSpawnPointData(ResultSet results) throws SQLException
	{
		SpawnPoint thisPoint = new SpawnPoint();
		thisPoint.map = results.getInt("map");
		thisPoint.pointId = results.getLong("pointId");
		thisPoint.zoneId = results.getInt("zoneId");
		thisPoint.areaId = results.getInt("areaId");
		thisPoint.gridId = results.getInt("gridId");
		thisPoint.positionX = results.getFloat("positionX");
		thisPoint.positionY = results.getFloat("positionY");
		thisPoint.positionZ = results.getFloat("positionZ");
		thisPoint.orientation = results.getFloat("orientation");
		thisPoint.rotation0 = results.getFloat("rotation0");
		thisPoint.rotation1 = results.getFloat("rotation1");
		thisPoint.rotation2 = results.getFloat("rotation2");
		thisPoint.rotation3 = results.getFloat("rotation3");
		return thisPoint;
	}
	
	private static void getPoolObjectData(PoolObject object, ResultSet results) throws SQLException
	{
		object.map = results.getInt("map");
		object.poolId = results.getInt("poolId");
		object.entry = results.getInt("entry");
		object.chance = results.getFloat("chance");
	}
	
	protected static PoolCreature getPoolCreatureData(ResultSet results) throws SQLException
	{
		PoolCreature thisCreature = new PoolCreature();
		
		// Yeah, it's pointless I know
		getPoolObjectData(thisCreature, results);
		
		thisCreature.modelId = results.getInt("modelId");
		thisCreature.equipmentId = results.getInt("equipmentId");
		thisCreature.currentWaypoint = results.getInt("currentWaypoint");
		thisCreature.curHealth = results.getLong("curHealth");
		thisCreature.curMana = results.getLong("curMana");
		thisCreature.npcFlag = results.getLong("npcFlag");
		thisCreature.unitFlags = results.getLong("unitFlags");
		thisCreature.dynamicFlags = results.getLong("dynamicFlags");
		return thisCreature;
	}
	
	protected static PoolGameObject getPoolGameObjectData(ResultSet results) throws SQLException
	{
		PoolGameObject thisGo = new PoolGameObject();
		getPoolObjectData(thisGo, results);
		
		thisGo.animProgress = results.getInt("animProgress");
		thisGo.state = results.getInt("state");
		return thisGo;
	}
}
