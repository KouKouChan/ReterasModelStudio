package com.hiveworkshop.wc3.units;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.hiveworkshop.wc3.gui.ExceptionPopup;
import com.hiveworkshop.wc3.mpq.MpqCodebase;


public class DataTable implements ObjectData {
	static DataTable theTable;
	static DataTable spawnTable;
	static DataTable ginterTable;
	static DataTable buffTable;
	static DataTable itemTable;
	static DataTable theTableDestructibles;
	static DataTable theTableDoodads;
	public static DataTable get() {
		if( theTable == null ) {
			theTable = new DataTable();
			theTable.loadDefaults();
		}
		return theTable;
	}
	public static DataTable getDoodads() {
		if( theTableDoodads == null ) {
			theTableDoodads = new DataTable();
			theTableDoodads.loadDoodads();
		}
		return theTableDoodads;
	}
	public static DataTable getDestructables() {
		if( theTableDestructibles == null ) {
			theTableDestructibles = new DataTable();
			theTableDestructibles.loadDestructibles();
		}
		return theTableDestructibles;
	}
	public static DataTable getItems() {
		if( itemTable == null ) {
			itemTable = new DataTable();
			itemTable.loadItems();
		}
		return itemTable;
	}
	public static DataTable getBuffs() {
		if( buffTable == null ) {
			buffTable = new DataTable();
			buffTable.loadBuffs();
		}
		return buffTable;
	}
	public static DataTable getSpawns() {
		if( spawnTable == null ) {
			spawnTable = new DataTable();
			spawnTable.loadSpawns();
		}
		return spawnTable;
	}
	public static DataTable getGinters() {
		if( ginterTable == null ) {
			ginterTable = new DataTable();
			ginterTable.loadGinters();
		}
		return ginterTable;
	}

	Map<StringKey,Element> dataTable = new LinkedHashMap<StringKey,Element>();

	public DataTable() {

	}

	@Override
	public Set<String> keySet() {
		final Set<String> outputKeySet = new HashSet<String>();
		final Set<StringKey> internalKeySet = dataTable.keySet();
		for(final StringKey key: internalKeySet) {
			outputKeySet.add(key.getString());
		}
		return outputKeySet;
	}

	public void loadDestructibles() {
		try {
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\DestructableData.slk"));
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
	}

	public void loadDoodads() {
		try {
			readSLK(MpqCodebase.get().getResourceAsStream("Doodads\\Doodads.slk"));
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
	}

	public void loadItems() {
		try {
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\ItemData.slk"));
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemStrings.txt"));
	}

	public void loadBuffs() {
		try {
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\AbilityBuffData.slk"));
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CommonAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CommonAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemAbilityStrings.txt"));
	}

	public void loadSpawns() {
		try {
			readSLK(MpqCodebase.get().getResourceAsStream("Splats\\SpawnData.slk"));
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
	}

	public void loadGinters() {
		try {
			readTXT(MpqCodebase.get().getResourceAsStream("UI\\war3skins.txt"), true);
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
	}

	public void loadDefaults() {
		try {
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\UnitUI.slk"));
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\AbilityData.slk"));
//			readSLK(MpqNativeCodebase.get().getGameFile("Units\\AbilityBuffData.slk"));
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\UnitData.slk"));
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\UnitAbilities.slk"));
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\UnitBalance.slk"));
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\UnitWeapons.slk"));
			readSLK(MpqCodebase.get().getResourceAsStream("Units\\UpgradeData.slk"));
		} catch (final IOException e) {
			ExceptionPopup.display(e);
		}
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignUnitFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignUnitStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanUnitFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanUnitStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralUnitFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralUnitStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfUnitFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfUnitStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcUnitFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcUnitStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadUnitFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadUnitStrings.txt"));


		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignUpgradeFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignUpgradeStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanUpgradeFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanUpgradeStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralUpgradeFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralUpgradeStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfUpgradeFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfUpgradeStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcUpgradeFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcUpgradeStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadUpgradeFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadUpgradeStrings.txt"));

		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CampaignAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CommonAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\CommonAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\HumanAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NeutralAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\NightElfAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\OrcAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\UndeadAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemAbilityFunc.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemAbilityStrings.txt"));
		readTXT(MpqCodebase.get().getResourceAsStream("Units\\ItemStrings.txt"));
		//readTXT(MpqNativeCodebase.get().getGameFile("war3mapMisc.txt"));


		//Specific data edits for tech browser
//		Unit castleAge = dataTable.get("R035");
//		castleAge.setField("Name", "Castle Age");
//		get("h030").addResearches("R03Y");
//
////		getVoidWorker().addToList("n03A","Builds");
//		Unit empoweredNetherling = get("n03J");
//		empoweredNetherling.setField("Upgrade",empoweredNetherling.getField("Upgrade").replace("n03A","null"));
//		get("h02Y").setField("Name", "Shrine of the �ther");
//		get("h02H").setField("Name", "�thergate");
//		get("h02G").setField("Name", "Vault of the �ther");
//		get("h02V").setField("Name", "�therstorm Tower");

//		//Loading of data
//		for( String unitid: dataTable.keySet() ) {
//			Unit u = dataTable.get(unitid);
////			u.setField("Parents",u.getField("Requires"));
//			for( Unit req: u.requires() ) {
//				u.addParent(req.getUnitId());
//			}
//		}
//
//		for( String unitid: dataTable.keySet() ) {
//			Unit u = dataTable.get(unitid);
//			for( Unit upgrade: u.upgrades() ) {
//				upgrade.addParent(u.getUnitId());
//			}
//		}
//
//		for( String unitid: dataTable.keySet() ) {
//			Unit u = dataTable.get(unitid);
//			for( Unit upgrade: u.researches() ) {
//				upgrade.addParent(u.getUnitId());
//			}
//		}
//
//		//Now calculate Children values
//
//
//
//		for( String unitid: dataTable.keySet() ) {
//			Unit u = dataTable.get(unitid);
//			for( Unit upgrade: u.parents() ) {
//				upgrade.addChild(u.getUnitId());
//			}
//			for( Unit upgrade: u.requires() ) {
//				upgrade.addRequiredBy(u.getUnitId());
//			}
//		}
	}

//	public void updateListWithLevels(List<Unit> list, List<Integer> levels) {
//		for( int i = 0; i < levels.size() && i < list.size(); i++ ) {
//			int level = levels.get(i);
//			if( level == 2 && list.get(i).equals(get("R035")) ) {
//				list.set(i, get("R03Y"));
//				//Enforce that "Level 2 Castle Age" is considered
//				// to be "Golden Age"
//			}
//		}
//	}

	public void readTXT(final InputStream inputStream) {
		try {
			readTXT(inputStream, false);
		}
		catch (final IOException e)
		{
			ExceptionPopup.display(e);
		}
	}
	public void readTXT(final File f) {
		readTXT(f, false);
	}
	public void readTXT(final File f, final boolean canProduce) {
		try {
			readTXT(new FileInputStream(f), canProduce);
		}
		catch (final IOException e)
		{
			ExceptionPopup.display(e);
		}
	}

	public void readSLK(final File f) {
		try {
			readSLK(new FileInputStream(f));
		}
		catch (final IOException e)
		{
			ExceptionPopup.display(e);
		}
	}

	public void readTXT(final InputStream txt, final boolean canProduce) throws IOException
	{
		final BufferedReader reader = new BufferedReader(new InputStreamReader(txt));

		String input = "";
		Element currentUnit = null;
		while( (input = reader.readLine()) != null ) {
			if( input.startsWith("//") ) {
				continue;
			}
			if( input.contains("[") && input.contains("]") ) {
				final int start = input.indexOf("[") + 1;
				final int end = input.indexOf("]");
				final String newKey = input.substring(start,end);
				final String newKeyBase = newKey;
				currentUnit = dataTable.get(new StringKey(newKey));
				if( currentUnit == null) {
//					currentUnit = dataTable.get(newKey.charAt(0) + "" + Character.toUpperCase(newKey.charAt(1)) + newKey.substring(2));
//					if( currentUnit == null ) {
//						currentUnit = dataTable.get(newKey.charAt(0) + "" + Character.toLowerCase(newKey.charAt(1)) + newKey.substring(2));
//						if( currentUnit == null ) {
//							currentUnit = dataTable.get(newKeyBase.substring(0,3) + Character.toUpperCase(newKeyBase.charAt(3)));
//							if( currentUnit == null ) {
								currentUnit = new Element(newKey,this);
								if( canProduce ) {
									currentUnit = new LMUnit(newKey,this);
									dataTable.put(new StringKey(newKey), currentUnit);
								}
//								currentUnit.setField("fromTXT", "1");
//							}
//						}
//					}
				}
			}
			else if( input.contains("=") ) {
				final int eIndex = input.indexOf("=");
				String fieldValue = input.substring(eIndex+1);
				if( fieldValue.length() > 1
						&& fieldValue.startsWith("\"")
						&& fieldValue.endsWith("\"") ) {
					fieldValue = fieldValue.substring(1,fieldValue.length() - 1);
				}
				currentUnit.setField(input.substring(0,eIndex), fieldValue);
			}
		}

		reader.close();
	}


	public void readSLK(final InputStream txt) throws IOException
	{
		final BufferedReader reader = new BufferedReader(new InputStreamReader(txt));

		String input = "";
		Element currentUnit = null;
		input = reader.readLine();
		if( !input.contains("ID") ) {
			System.err.println("Formatting of SLK is unusual.");
		}
		input = reader.readLine();
		while( input.startsWith("P;") || input.startsWith("F;") ) {
			input = reader.readLine();
		}
		final int yIndex = input.indexOf("Y")+1;
		final int xIndex = input.indexOf("X")+1;
		int colCount = 0;
		int rowCount = 0;
		boolean flipMode = false;
		if( xIndex > yIndex ) {
			colCount = Integer.parseInt(input.substring(xIndex,input.lastIndexOf(";")));
			rowCount = Integer.parseInt(input.substring(yIndex,xIndex-2));
		}
		else {
			rowCount = Integer.parseInt(input.substring(yIndex,input.lastIndexOf(";")));
			colCount = Integer.parseInt(input.substring(xIndex,yIndex-2));
			flipMode = true;
		}
		int rowStartCount = 0;
		final String [] dataNames = new String[colCount];
//		for( int i = 0; i < colCount && rowStartCount <= 1; i++ ) {
//			input = reader.readLine();
//			dataNames[i] = input.substring(input.indexOf("\"")+1,
//					input.lastIndexOf("\""));
//		}
//
		int col = 0;
		while( (input = reader.readLine()) != null ) {
			if( input.startsWith("O;") ) {
				continue;
			}
			if( input.contains("X1;") ) {
				rowStartCount++;
				col = 0;
			}
			else
			{
				col++;
			}
			if( rowStartCount <= 1 ) {
				final int subXIndex = input.indexOf("X");
				int eIndex = input.indexOf("K");
				if( flipMode && input.contains("Y") ) {
					eIndex = Math.min(input.indexOf("Y"),eIndex);
				}
				final int fieldId = Integer.parseInt(input.substring(subXIndex+1,
						eIndex-1));

				dataNames[fieldId-1] = input.substring(input.indexOf("\"")+1,
						input.lastIndexOf("\""));
				continue;
			}
//			if( rowStartCount == 2)
//			System.out.println(Arrays.toString(dataNames));
			if( input.contains("X1;") ) {
				final int start = input.indexOf("\"") + 1;
				final int end = input.lastIndexOf("\"");
				if( start-1 != end ) {
					final String newKey = input.substring(start,end);
					currentUnit = dataTable.get(new StringKey(newKey));
					if( currentUnit == null) {
						currentUnit = new Element(newKey,this);
						dataTable.put(new StringKey(newKey), currentUnit);
					}
				}
			}
			else if( input.contains("K") ) {
				final int subXIndex = input.indexOf("X");
				int eIndex = input.indexOf("K");
				if( flipMode && input.contains("Y") ) {
					eIndex = Math.min(input.indexOf("Y"),eIndex);
				}
				final int fieldId = Integer.parseInt(input.substring(subXIndex+1,
						eIndex-1));
				String fieldValue = input.substring(eIndex+1);
				if( fieldValue.length() > 1
						&& fieldValue.startsWith("\"")
						&& fieldValue.endsWith("\"") ) {
					fieldValue = fieldValue.substring(1,fieldValue.length() - 1);
				}
				currentUnit.setField(dataNames[fieldId-1], fieldValue);
			}
		}

		reader.close();
	}

	@Override
	public Element get(final String id) {
		return dataTable.get(new StringKey(id));
	}
	@Override
	public void setValue(final String id, final String field, final String value) {
		get(id).setField(field, value);
	}

//	public Unit getFallyWorker() {
//		return dataTable.get("h02Z");
//	}
//
//	public Unit getFallyWorker2() {
//		return dataTable.get("h03P");
//	}
//
//	public Unit getTribeWorker() {
//		return dataTable.get("opeo");
//	}
//
//	public Unit getTideWorker() {
//		return dataTable.get("ewsp");
//	}
//
//	public Unit getVoidWorker() {
//		return dataTable.get("e007");
//	}
//
//	public Unit getElfWorker() {
//		return dataTable.get("e000");
//	}
//
//	public Unit getHumanWorker() {
//		return dataTable.get("h001");
//	}
//
//	public Unit getOrcWorker() {
//		return dataTable.get("o000");
//	}
//
//	public Unit getUndeadWorker() {
//		return dataTable.get("u001");
//	}

//	public static void main(String [] args) {
//		UnitDataTable table = new UnitDataTable();
//		table.loadDefaults();
//		Unit villager = table.get("h02Z");
//		System.out.println(villager.getField("Name")+ " can build: ");
//		System.out.println(villager.builds());
//
//		System.out.println();
//
//		Unit townSquare = table.get("owtw");
//		System.out.println(townSquare.getField("Name")+ " trains: ");
//		System.out.println(townSquare.trains());
//
//		System.out.println(townSquare.getField("Name")+ " upgrades: ");
//		System.out.println(townSquare.upgrades());
//
//		System.out.println(townSquare.getField("Name")+ " researches: ");
//		System.out.println(townSquare.researches());
//
//		System.out.println(townSquare.getField("Name")+ " stats: ");
//		for( String field: townSquare.fields.keySet() ) {
//			System.out.println(field +": "+townSquare.getField(field));
//		}
////		System.out.println(townSquare.getField("goldcost"));
////		System.out.println(townSquare.getField("lumbercost"));
////		System.out.println(townSquare.getField("fmade"));
////		System.out.println(townSquare.getField("fmade"));
//
//		List<Unit> abils = table.getTideWorker().abilities();
//		System.out.println(abils);
//		for( Unit abil: abils ) {
//			System.out.println(abil.getUnitId());
//		}
//	}
	private static final class StringKey {
		private final String string;
		public StringKey(final String string) {
			this.string = string;
		}
		public String getString() {
			return string;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((string.toLowerCase() == null) ? 0 : string.toLowerCase().hashCode());
			return result;
		}
		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final StringKey other = (StringKey) obj;
			if (string == null) {
				if (other.string != null) {
					return false;
				}
			} else if (!string.equalsIgnoreCase(other.string)) {
				return false;
			}
			return true;
		}
	}
}