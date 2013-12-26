package com.wasteofplastic.QuickShopMW.Util;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sign;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

import com.wasteofplastic.QuickShopMW.QuickShop;

public class Util{
	private static HashSet<Material> tools = new HashSet<Material>();
	private static HashSet<Material> blacklist = new HashSet<Material>();
	private static HashSet<Material> shoppables = new HashSet<Material>();
	private static HashSet<Material> transparent = new HashSet<Material>();
	private static QuickShop plugin;
	
	static{
		plugin = QuickShop.instance;
		
		for(String s : plugin.getConfig().getStringList("shop-blocks")){
			Material mat = Material.getMaterial(s.toUpperCase());
			if(mat == null){
				try{
					mat = Material.getMaterial(Integer.parseInt(s));
				}
				catch(NumberFormatException e){}
			}
			if(mat == null){
				plugin.getLogger().info("Invalid shop-block: " + s);
			}
			else{
				shoppables.add(mat);
			}
		}
	
		tools.add(Material.BOW);
		tools.add(Material.SHEARS);
		tools.add(Material.FISHING_ROD);
		tools.add(Material.FLINT_AND_STEEL);

		tools.add(Material.CHAINMAIL_BOOTS);
		tools.add(Material.CHAINMAIL_CHESTPLATE);
		tools.add(Material.CHAINMAIL_HELMET);
		tools.add(Material.CHAINMAIL_LEGGINGS);
		
		tools.add(Material.WOOD_AXE);
		tools.add(Material.WOOD_HOE);
		tools.add(Material.WOOD_PICKAXE);
		tools.add(Material.WOOD_SPADE);
		tools.add(Material.WOOD_SWORD);
		
		tools.add(Material.LEATHER_BOOTS);
		tools.add(Material.LEATHER_CHESTPLATE);
		tools.add(Material.LEATHER_HELMET);
		tools.add(Material.LEATHER_LEGGINGS);
		
		tools.add(Material.DIAMOND_AXE); 
		tools.add(Material.DIAMOND_HOE);
		tools.add(Material.DIAMOND_PICKAXE);
		tools.add(Material.DIAMOND_SPADE);
		tools.add(Material.DIAMOND_SWORD);

		tools.add(Material.DIAMOND_BOOTS);
		tools.add(Material.DIAMOND_CHESTPLATE);
		tools.add(Material.DIAMOND_HELMET);
		tools.add(Material.DIAMOND_LEGGINGS);
		tools.add(Material.STONE_AXE); 
		tools.add(Material.STONE_HOE);
		tools.add(Material.STONE_PICKAXE);
		tools.add(Material.STONE_SPADE);
		tools.add(Material.STONE_SWORD);

		tools.add(Material.GOLD_AXE); 
		tools.add(Material.GOLD_HOE);
		tools.add(Material.GOLD_PICKAXE);
		tools.add(Material.GOLD_SPADE);
		tools.add(Material.GOLD_SWORD);

		tools.add(Material.GOLD_BOOTS);
		tools.add(Material.GOLD_CHESTPLATE);
		tools.add(Material.GOLD_HELMET);
		tools.add(Material.GOLD_LEGGINGS);
		tools.add(Material.IRON_AXE); 
		tools.add(Material.IRON_HOE);
		tools.add(Material.IRON_PICKAXE);
		tools.add(Material.IRON_SPADE);
		tools.add(Material.IRON_SWORD);

		tools.add(Material.IRON_BOOTS);
		tools.add(Material.IRON_CHESTPLATE);
		tools.add(Material.IRON_HELMET);
		tools.add(Material.IRON_LEGGINGS);
		
		List<String> configBlacklist = plugin.getConfig().getStringList("blacklist");
		
		for(String s : configBlacklist){
			Material mat = Material.getMaterial(s.toUpperCase());
			if(mat == null){
				mat = Material.getMaterial(Integer.parseInt(s));
				if(mat == null){
					plugin.getLogger().info(s + " is not a valid material.  Check your spelling or ID");
					continue;
				}
			}
			blacklist.add(mat);
		}
		
		transparent.clear();
		//ToDo: add extras to config file
		addTransparentBlock(Material.AIR);
		/* Misc */
		addTransparentBlock(Material.CAKE_BLOCK);
		
		/* Redstone Material */
		addTransparentBlock(Material.REDSTONE_WIRE);
		
		/* Redstone Torches */
		addTransparentBlock(Material.REDSTONE_TORCH_OFF);
		addTransparentBlock(Material.REDSTONE_TORCH_ON);
		
		/* Diodes (Repeaters) */
		addTransparentBlock(Material.DIODE_BLOCK_OFF);
		addTransparentBlock(Material.DIODE_BLOCK_ON);
		
		/* Power Sources */
		addTransparentBlock(Material.DETECTOR_RAIL);
		addTransparentBlock(Material.LEVER);
		addTransparentBlock(Material.STONE_BUTTON);
		addTransparentBlock(Material.WOOD_BUTTON);
		addTransparentBlock(Material.STONE_PLATE);
		addTransparentBlock(Material.WOOD_PLATE);
		
		/* Nature Material */
		addTransparentBlock(Material.RED_MUSHROOM);
		addTransparentBlock(Material.BROWN_MUSHROOM);
		
		addTransparentBlock(Material.RED_ROSE);
		addTransparentBlock(Material.YELLOW_FLOWER);
		
		addTransparentBlock(Material.FLOWER_POT);

		/* Greens */
		addTransparentBlock(Material.LONG_GRASS);
		addTransparentBlock(Material.VINE);
		addTransparentBlock(Material.WATER_LILY);

		/* Seedy things */
		addTransparentBlock(Material.MELON_STEM);
		addTransparentBlock(Material.PUMPKIN_STEM);
		addTransparentBlock(Material.CROPS);
		addTransparentBlock(Material.NETHER_WARTS);
		
		/* Semi-nature */
		addTransparentBlock(Material.SNOW);
		addTransparentBlock(Material.FIRE);
		addTransparentBlock(Material.WEB);
		addTransparentBlock(Material.TRIPWIRE);
		addTransparentBlock(Material.TRIPWIRE_HOOK);
		
		/* Stairs */
		addTransparentBlock(Material.COBBLESTONE_STAIRS);
		addTransparentBlock(Material.BRICK_STAIRS);
		addTransparentBlock(Material.SANDSTONE_STAIRS);
		addTransparentBlock(Material.NETHER_BRICK_STAIRS);
		addTransparentBlock(Material.SMOOTH_STAIRS);
		
		/* Wood Stairs */
		addTransparentBlock(Material.BIRCH_WOOD_STAIRS);
		addTransparentBlock(Material.WOOD_STAIRS);
		addTransparentBlock(Material.JUNGLE_WOOD_STAIRS);
		addTransparentBlock(Material.SPRUCE_WOOD_STAIRS);
		
		/* Lava & Water */
		addTransparentBlock(Material.LAVA);
		addTransparentBlock(Material.STATIONARY_LAVA);
		addTransparentBlock(Material.WATER);
		addTransparentBlock(Material.STATIONARY_WATER);
		
		/* Saplings and bushes */
		addTransparentBlock(Material.SAPLING);
		addTransparentBlock(Material.DEAD_BUSH);
		
		/* Construction Material */
		/* Fences */
		addTransparentBlock(Material.FENCE);
		addTransparentBlock(Material.FENCE_GATE);
		addTransparentBlock(Material.IRON_FENCE);
		addTransparentBlock(Material.NETHER_FENCE);
		
		/* Ladders, Signs */
		addTransparentBlock(Material.LADDER);
		addTransparentBlock(Material.SIGN_POST);
		addTransparentBlock(Material.WALL_SIGN);
		
		/* Bed */
		addTransparentBlock(Material.BED_BLOCK);
		
		/* Pistons */
		addTransparentBlock(Material.PISTON_EXTENSION);
		addTransparentBlock(Material.PISTON_MOVING_PIECE);
		addTransparentBlock(Material.RAILS);
		
		/* Torch & Trapdoor */		
		addTransparentBlock(Material.TORCH);
		addTransparentBlock(Material.TRAP_DOOR);
		
		/* New */
		addTransparentBlock(Material.BREWING_STAND);
		addTransparentBlock(Material.WOODEN_DOOR);
		addTransparentBlock(Material.WOOD_STEP);
	}
	
	public static boolean isTransparent(Material m){
		boolean trans = transparent.contains(m); 
		return trans;
	}
	
	public static void addTransparentBlock(Material m){
		if(transparent.add(m) == false){
			System.out.println("Already added as transparent: " + m.toString());
		}
		if(!m.isBlock()) System.out.println(m + " is not a block!");
	}
	
	public static void parseColours(YamlConfiguration config){
		Set<String> keys = config.getKeys(true);
		
		for(String key : keys){
			String filtered = config.getString(key);
			if(filtered.startsWith("MemorySection")){
				continue;
			}
			filtered = ChatColor.translateAlternateColorCodes('&', filtered);
			config.set(key, filtered);
		}
	}
	
	/**
	 * Returns true if the given block could be used to make a shop out of.
	 * @param b The block to check. Possibly a chest, dispenser, etc.
	 * @return True if it can be made into a shop, otherwise false.
	 */
	public static boolean canBeShop(Block b){
		BlockState bs = b.getState();
		if(bs instanceof InventoryHolder == false) return false;
		return shoppables.contains(bs.getType());
	}
	
	 /**
	  * Gets the percentage (Without trailing %) damage on a tool.
	  * @param item The ItemStack of tools to check
	  * @return The percentage 'health' the tool has. (Opposite of total damage)
	  */
	public static String getToolPercentage(ItemStack item){
		double dura = item.getDurability();
		double max = item.getType().getMaxDurability();
		
		DecimalFormat formatter = new DecimalFormat("0");
		return formatter.format((1 - dura/max)* 100.0);
	}
	
	/**
	 * Returns the chest attached to the given chest. The given block must be a chest.
	 * @param b The chest to check.
	 * @return the block which is also a chest and connected to b.
	 */
	public static Block getSecondHalf(Block b){
		if(b.getType().toString().contains("CHEST") == false) return null;
		
		Block[] blocks = new Block[4];
		blocks[0] = b.getRelative(1, 0, 0);
		blocks[1] = b.getRelative(-1, 0, 0);
		blocks[2] = b.getRelative(0, 0, 1);
		blocks[3] = b.getRelative(0, 0, -1);
		
		for(Block c : blocks){
			if(c.getType() == b.getType()){
				return c;
			}
		}

		return null;
	}

	/**
	 * Converts a string into an item from the database.
	 * @param itemString The database string.  Is the result of makeString(ItemStack item).
	 * @return A new itemstack, with the properties given in the string
	 */
	public static ItemStack makeItem(String itemString){
		String[] itemInfo = itemString.split(":");
		
		ItemStack item = new ItemStack(Material.getMaterial(itemInfo[0]));
		@SuppressWarnings("deprecation")
		MaterialData data = new MaterialData(Integer.parseInt(itemInfo[1]));
		item.setData(data);
		item.setDurability( Short.parseShort(itemInfo[2]));
		item.setAmount(Integer.parseInt(itemInfo[3]));
		
		for(int i = 4; i < itemInfo.length; i = i + 2){
			int level = Integer.parseInt(itemInfo[i+1]);
			
			Enchantment ench = Enchantment.getByName(itemInfo[i]);
			if(ench == null) continue; //Invalid
			if (ench.canEnchantItem(item)){
				if(level <= 0) continue;
				level = Math.min(ench.getMaxLevel(), level);
				
				item.addEnchantment(ench, level);
			}
			
		}
		return item;
	}
	
	public static String serialize(ItemStack iStack){
		YamlConfiguration cfg = new YamlConfiguration();
		cfg.set("item", iStack);
		return cfg.saveToString();
	}
	public static ItemStack deserialize(String config) throws InvalidConfigurationException{
		YamlConfiguration cfg = new YamlConfiguration();
		cfg.loadFromString(config);
		ItemStack stack = cfg.getItemStack("item");
		return stack;
	}
	
	/**
	 * Fetches an ItemStack's name - For example, converting INK_SAC:11 to Dandellion Yellow, or WOOL:14 to Red Wool
	 * @param i The itemstack to fetch the name of
	 * @return The human readable item name.
	 */
	public static String getName(ItemStack i){
		String vanillaName = getDataName(i);
		return prettifyText(vanillaName);
	}
	
	/**
	 * Converts a name like IRON_INGOT into Iron Ingot to improve readability
	 * @param ugly The string such as IRON_INGOT
	 * @return A nicer version, such as Iron Ingot
	 * 
	 * Credits to mikenon on GitHub!
	 */
	public static String prettifyText(String ugly){
		if(!ugly.contains("_") && (!ugly.equals(ugly.toUpperCase()))) return ugly;
		String fin = "";
		ugly = ugly.toLowerCase();
		if(ugly.contains("_")){
			String[] splt = ugly.split("_");
			int i = 0;
			for(String s : splt){
				i += 1;
				fin += Character.toUpperCase(s.charAt(0)) + s.substring(1);
				if(i<splt.length) fin += " ";
			}
		} else {
			fin += Character.toUpperCase(ugly.charAt(0)) + ugly.substring(1);
		}
		return fin;
	}
	
	public static String toRomain(Integer value) {
		return toRoman(value.intValue());
	}

	private static final String[] ROMAN = {"X", "IX", "V", "IV", "I"};
	private static final int[] DECIMAL = {10, 9, 5, 4, 1};
	
	/**
	 * Converts the given number to roman numerals.  If the number is >= 40 or <= 0, it will just return the number as a string.
	 * @param n The number to convert
	 * @return The roman numeral representation of this number, or the number in decimal form as a string if n <= 0 || n >= 40.
	 */
	public static String toRoman(int n){
		if(n <= 0 || n >= 40) return ""+n;
		String roman = "";
		
		for(int i = 0; i < ROMAN.length; i++){
			while(n >= DECIMAL[i]){
				n -= DECIMAL[i];
				roman += ROMAN[i];
			}
		}
		
		return roman;
	}
	
	/**
	 * Converts a given ItemStack into a pretty string
	 * 
	 * @param item
	 *            The item stack
	 * @return A string with the name of the item.
	 */
	private static String getDataName(ItemStack item) {
		Material mat = item.getType();
		// Find out durability, which indicates additional information on the
		// item, color, etc.
		short damage = item.getDurability();
		switch (mat) {
		case WOOL:
			switch ((int) damage) {
			case 0:
				return "WHITE_WOOL";
			case 1:
				return "ORANGE_WOOL";
			case 2:
				return "MAGENTA_WOOL";
			case 3:
				return "LIGHT_BLUE_WOOL";
			case 4:
				return "YELLOW_WOOL";
			case 5:
				return "LIME_WOOL";
			case 6:
				return "PINK_WOOL";
			case 7:
				return "GRAY_WOOL";
			case 8:
				return "LIGHT_GRAY_WOOL";
			case 9:
				return "CYAN_WOOL";
			case 10:
				return "PURPLE_WOOL";
			case 11:
				return "BLUE_WOOL";
			case 12:
				return "BROWN_WOOL";
			case 13:
				return "GREEN_WOOL";
			case 14:
				return "RED_WOOL";
			case 15:
				return "BLACK_WOOL";
			}
			return mat.toString();
		case INK_SACK:
			switch ((int) damage) {
			case 0:
				return "INK_SAC";
			case 1:
				return "ROSE_RED";
			case 2:
				return "CACTUS_GREEN";
			case 3:
				return "COCOA_BEANS";
			case 4:
				return "LAPIS_LAZULI";
			case 5:
				return "PURPLE_DYE";
			case 6:
				return "CYAN_DYE";
			case 7:
				return "LIGHT_GRAY_DYE";
			case 8:
				return "GRAY_DYE";
			case 9:
				return "PINK_DYE";
			case 10:
				return "LIME_DYE";
			case 11:
				return "DANDELION_YELLOW";
			case 12:
				return "LIGHT_BLUE_DYE";
			case 13:
				return "MAGENTA_DYE";
			case 14:
				return "ORANGE_DYE";
			case 15:
				return "BONE_MEAL";
			}
			return mat.toString();
		case SMOOTH_BRICK:
			switch ((int) damage) {
			case 0:
				return "STONE_BRICKS";
			case 1:
				return "MOSSY_STONE_BRICKS";
			case 2:
				return "CRACKED_STONE_BRICKS";
			case 3:
				return "CHISELED_STONE_BRICKS";
			}
			return mat.toString();
		case POTION:
			// Special case,.. Why?
			if (damage == 0)
				return "WATER_BOTTLE";
			Potion pot;
			// Convert the item stack to a potion. The try is just in case this
			// is not a potion, which it should be

			try {
				pot = Potion.fromItemStack(item);
			} catch (Exception e) {
				return "CUSTOM_POTION";
			}
			// Now we can parse out what the potion is from its effects and type
			String prefix = "";
			String suffix = "";
			if (pot.getLevel() > 0)
				suffix += "_" + pot.getLevel();
			if (pot.hasExtendedDuration())
				prefix += "EXTENDED_";
			if (pot.isSplash())
				prefix += "SPLASH_";
			// These are the useless or unused potions. Usually, these can only
			// be obtained by /give
			if (pot.getEffects().isEmpty()) {
				switch ((int) damage) {
				case 64:
					return prefix + "MUNDANE_POTION" + suffix;
				case 7:
					return prefix + "CLEAR_POTION" + suffix;
				case 11:
					return prefix + "DIFFUSE_POTION" + suffix;
				case 13:
					return prefix + "ARTLESS_POTION" + suffix;
				case 15:
					return prefix + "THIN_POTION" + suffix;
				case 16:
					return prefix + "AWKWARD_POTION" + suffix;
				case 32:
					return prefix + "THICK_POTION" + suffix;
				case 23:
					return prefix + "BUNGLING_POTION" + suffix;
				case 27:
					return prefix + "SMOOTH_POTION" + suffix;
				case 31:
					return prefix + "DEBONAIR_POTION" + suffix;
				case 39:
					return prefix + "CHARMING_POTION" + suffix;
				case 43:
					return prefix + "REFINED_POTION" + suffix;
				case 47:
					return prefix + "SPARKLING_POTION" + suffix;
				case 48:
					return prefix + "POTENT_POTION" + suffix;
				case 55:
					return prefix + "RANK_POTION" + suffix;
				case 59:
					return prefix + "ACRID_POTION" + suffix;
				case 63:
					return prefix + "STINKY_POTION" + suffix;
				}
			} else {
				String effects = "";
				for (PotionEffect effect : pot.getEffects()) {
					effects += effect.toString().split(":")[0];
				}
				return prefix + effects + suffix;
			}
			return mat.toString();
		case SAPLING:
			switch ((int) damage) {
			case 0:
				return "OAK_SAPLING";
			case 1:
				return "PINE_SAPLING";
			case 2:
				return "BIRCH_SAPLING";
			case 3:
				return "JUNGLE_TREE_SAPLING";
			case 4:
				return "Acacia_Sapling";
			case 5:
				return "Dark_Oak_Sapling";
			}
			return mat.toString();

		case WOOD:
			switch ((int) damage) {
			case 0:
				return "OAK_PLANKS";
			case 1:
				return "PINE_PLANKS";
			case 2:
				return "BIRCH_PLANKS";
			case 3:
				return "JUNGLE_PLANKS";
			case 4:
				return "Acacia Planks";
			case 5:
				return "Dark Oak Planks";
			}
			return mat.toString();
		case LOG:
			switch (damage) {
			case 0:
				return "OAK_LOG";
			case 1:
				return "PINE_LOG";
			case 2:
				return "BIRCH_LOG";
			case 3:
				return "JUNGLE_LOG";
			}
			return mat.toString();
		case LEAVES:
			damage = (short) (damage % 4);
			switch (damage) {
			case 0:
				return "OAK_LEAVES";
			case 1:
				return "PINE_LEAVES";
			case 2:
				return "BIRCH_LEAVES";
			case 3:
				return "JUNGLE_LEAVES";
			} // Note Acacia and Dark Oak are LEAVES_2 for some reason...
		case COAL:
			switch (damage) {
			case 0:
				return "COAL";
			case 1:
				return "CHARCOAL";
			}
			return mat.toString();
		case SANDSTONE:
			switch ((int) damage) {
			case 0:
				return "SANDSTONE";
			case 1:
				return "CHISELED_SANDSTONE";
			case 2:
				return "SMOOTH_SANDSTONE";
			}
			return mat.toString();
		case LONG_GRASS:
			switch ((int) damage) {
			case 0:
				return "DEAD_SHRUB";
			case 1:
				return "TALL_GRASS";
			case 2:
				return "FERN";
			}
			return mat.toString();
		case STEP:
			switch ((int) damage) {
			case 0:
				return "STONE_SLAB";
			case 1:
				return "SANDSTONE_SLAB";
			case 2:
				return "WOODEN_SLAB";
			case 3:
				return "COBBLESTONE_SLAB";
			case 4:
				return "BRICK_SLAB";
			case 5:
				return "STONE_BRICK_SLAB";
			case 6:
				return "Nether Brick Slab";
			case 7:
				return "Quartz Slab";
			}
			return mat.toString();
		case MONSTER_EGG:
			switch ((int) damage) {
			case 50:
				return "CREEPER_EGG";
			case 51:
				return "SKELETON_EGG";
			case 52:
				return "SPIDER_EGG";
			case 53:
				// Unused
				return "GIANT_EGG";
			case 54:
				return "ZOMBIE_EGG";
			case 55:
				return "SLIME_EGG";
			case 56:
				return "GHAST_EGG";
			case 57:
				return "ZOMBIE_PIGMAN_EGG";
			case 58:
				return "ENDERMAN_EGG";
			case 59:
				return "CAVE_SPIDER_EGG";
			case 60:
				return "SILVERFISH_EGG";
			case 61:
				return "BLAZE_EGG";
			case 62:
				return "MAGMA_CUBE_EGG";
			case 63:
				return "ENDER_DRAGON_EGG";
			case 65:
				return "BAT_EGG";
			case 66:
				return "WITCH_EGG";
			case 90:
				return "PIG_EGG";
			case 91:
				return "SHEEP_EGG";
			case 92:
				return "COW_EGG";
			case 93:
				return "CHICKEN_EGG";
			case 94:
				return "SQUID_EGG";
			case 95:
				return "WOLF_EGG";
			case 96:
				return "MOOSHROOM_EGG";
			case 97:
				return "SNOW_GOLEM_EGG";
			case 98:
				return "OCELOT_EGG";
			case 99:
				return "IRON_GOLEM_EGG";
			case 100:
				return "HORSE_EGG";
			case 120:
				return "VILLAGER_EGG";
			case 200:
				return "ENDER_CRYSTAL_EGG";
			case 14:
				return "PRIMED_TNT_EGG";
			}
			return mat.toString();
		case SKULL_ITEM:
			switch ((int) damage) {
			case 0:
				return "SKELETON_SKULL";
			case 1:
				return "WITHER_SKULL";
			case 2:
				return "ZOMBIE_HEAD";
			case 3:
				return "PLAYER_HEAD";
			case 4:
				return "CREEPER_HEAD";
			}
			break;
		case REDSTONE_TORCH_OFF:
		case REDSTONE_TORCH_ON:
			return "REDSTONE_TORCH";
		case NETHER_STALK:
			return "NETHER_WART";
		case WEB:
			return "COBWEB";
		case THIN_GLASS:
			return "GLASS_PANE";
		case IRON_FENCE:
			return "IRON_BARS";
		case WORKBENCH:
			return "CRAFTING_TABLE";
		case REDSTONE_LAMP_ON:
		case REDSTONE_LAMP_OFF:
			return "REDSTONE_LAMP";
		case POTATO_ITEM:
			return "POTATO";
		case SULPHUR:
			return "GUNPOWDER";
		case CARROT_ITEM:
			return "CARROT";
		case GOLDEN_APPLE:
			switch ((int) damage) {
			case 0:
				return "GOLDEN_APPLE";
			case 1:
				return "ENCHANTED_GOLDEN_APPLE";
			}
			break;
		case FLOWER_POT:
			return "FLOWER_POT";
		case ANVIL:
			switch ((int) damage) {
			case 0:
				return "ANVIL";
			case 1:
				return "SLIGHTLY_DAMAGED_ANVIL";
			case 2:
				return "VERY_DAMAGED:ANVIL";
			}
			break;
		case EXP_BOTTLE:
			return "BOTTLE_O'_ENCHANTING";
		case FIREWORK_CHARGE:
			return "FIREWORK_STAR";
		case FIREBALL:
			return "FIREWORK_CHARGE";
		case ACACIA_STAIRS:
			break;
		case ACTIVATOR_RAIL:
			break;
		case AIR:
			break;
		case APPLE:
			break;
		case ARROW:
			break;
		case BAKED_POTATO:
			break;
		case BEACON:
			break;
		case BED:
			break;
		case BEDROCK:
			break;
		case BED_BLOCK:
			break;
		case BIRCH_WOOD_STAIRS:
			break;
		case BLAZE_POWDER:
			break;
		case BLAZE_ROD:
			break;
		case BOAT:
			break;
		case BONE:
			break;
		case BOOK:
			break;
		case BOOKSHELF:
			break;
		case BOOK_AND_QUILL:
			break;
		case BOW:
			break;
		case BOWL:
			break;
		case BREAD:
			break;
		case BREWING_STAND:
			break;
		case BREWING_STAND_ITEM:
			return "Brewing Stand";
		case BRICK:
			break;
		case BRICK_STAIRS:
			break;
		case BROWN_MUSHROOM:
			break;
		case BUCKET:
			break;
		case BURNING_FURNACE:
			break;
		case CACTUS:
			break;
		case CAKE:
			break;
		case CAKE_BLOCK:
			break;
		case CARPET:
			switch ((int) damage) {
			case 0:
				return "WHITE_CARPET";
			case 1:
				return "ORANGE_CARPET";
			case 2:
				return "MAGENTA_CARPET";
			case 3:
				return "LIGHT_BLUE_CARPET";
			case 4:
				return "YELLOW_CARPET";
			case 5:
				return "LIME_CARPET";
			case 6:
				return "PINK_CARPET";
			case 7:
				return "GRAY_CARPET";
			case 8:
				return "LIGHT_GRAY_CARPET";
			case 9:
				return "CYAN_CARPET";
			case 10:
				return "PURPLE_CARPET";
			case 11:
				return "BLUE_CARPET";
			case 12:
				return "BROWN_CARPET";
			case 13:
				return "GREEN_CARPET";
			case 14:
				return "RED_CARPET";
			case 15:
				return "BLACK_CARPET";
			}
			return mat.toString();
		case CARROT:
			break;
		case CARROT_STICK:
			break;
		case CAULDRON:
			break;
		case CAULDRON_ITEM:
			return "CAULDRON";
		case CHAINMAIL_BOOTS:
			break;
		case CHAINMAIL_CHESTPLATE:
			break;
		case CHAINMAIL_HELMET:
			break;
		case CHAINMAIL_LEGGINGS:
			break;
		case CHEST:
			break;
		case CLAY:
			break;
		case CLAY_BALL:
			break;
		case CLAY_BRICK:
			break;
		case COAL_BLOCK:
			break;
		case COAL_ORE:
			break;
		case COBBLESTONE:
			break;
		case COBBLESTONE_STAIRS:
			break;
		case COBBLE_WALL:
			break;
		case COCOA:
			break;
		case COMMAND:
			return "COMMAND_BLOCK";
		case COMMAND_MINECART:
			break;
		case COMPASS:
			break;
		case COOKED_BEEF:
			break;
		case COOKED_CHICKEN:
			break;
		case COOKED_FISH:
			break;
		case COOKIE:
			break;
		case CROPS:
			break;
		case DARK_OAK_STAIRS:
			break;
		case DAYLIGHT_DETECTOR:
			break;
		case DEAD_BUSH:
			break;
		case DETECTOR_RAIL:
			break;
		case DIAMOND:
			break;
		case DIAMOND_AXE:
			break;
		case DIAMOND_BARDING:
			break;
		case DIAMOND_BLOCK:
			break;
		case DIAMOND_BOOTS:
			break;
		case DIAMOND_CHESTPLATE:
			break;
		case DIAMOND_HELMET:
			break;
		case DIAMOND_HOE:
			break;
		case DIAMOND_LEGGINGS:
			break;
		case DIAMOND_ORE:
			break;
		case DIAMOND_PICKAXE:
			break;
		case DIAMOND_SPADE:
			return "Diamond Shovel";
		case DIAMOND_SWORD:
			break;
		case DIODE:
			break;
		case DIODE_BLOCK_OFF:
			break;
		case DIODE_BLOCK_ON:
			break;
		case DIRT:
			break;
		case DISPENSER:
			break;
		case DOUBLE_PLANT:
			switch ((int) damage) {
			case 0:
				return "SUNFLOWER";
			case 1:
				return "LILAC";
			case 2:
				return "DOUBLE_TALL_GRASS";
			case 3:
				return "LARGE_FERN";
			case 4:
				return "Rose Bush";
			case 5:
				return "Peony";
			}
			break;
		case DOUBLE_STEP:
			switch ((int) damage) {
			case 0:
				return "STONE_SLAB (DOUBLE)";
			case 1:
				return "SANDSTONE_SLAB (DOUBLE)";
			case 2:
				return "WOODEN_SLAB (DOUBLE)";
			case 3:
				return "COBBLESTONE_SLAB (DOUBLE)";
			case 4:
				return "BRICK_SLAB (DOUBLE)";
			case 5:
				return "STONE_BRICK_SLAB (DOUBLE)";
			case 6:
				return "Nether Brick Slab (DOUBLE)";
			case 7:
				return "Quartz Slab (DOUBLE)";
			case 8:
				return "Smooth Stone Slab (Double)";
			case 9:
				return "Smooth Sandstone Slab (Double)";
			}
			break;
		case DRAGON_EGG:
			break;
		case DROPPER:
			break;
		case EGG:
			break;
		case EMERALD:
			break;
		case EMERALD_BLOCK:
			break;
		case EMERALD_ORE:
			break;
		case EMPTY_MAP:
			break;
		case ENCHANTED_BOOK:
			break;
		case ENCHANTMENT_TABLE:
			break;
		case ENDER_CHEST:
			break;
		case ENDER_PEARL:
			break;
		case ENDER_PORTAL:
			break;
		case ENDER_PORTAL_FRAME:
			break;
		case ENDER_STONE:
			break;
		case EXPLOSIVE_MINECART:
			break;
		case EYE_OF_ENDER:
			break;
		case FEATHER:
			break;
		case FENCE:
			break;
		case FENCE_GATE:
			break;
		case FERMENTED_SPIDER_EYE:
			break;
		case FIRE:
			break;
		case FIREWORK:
			return "Firework Rocket";
		case FISHING_ROD:
			break;
		case FLINT:
			break;
		case FLINT_AND_STEEL:
			break;
		case FLOWER_POT_ITEM:
			return "Flower Pot";
		case FURNACE:
			break;
		case GHAST_TEAR:
			break;
		case GLASS:
			break;
		case GLASS_BOTTLE:
			break;
		case GLOWING_REDSTONE_ORE:
			break;
		case GLOWSTONE:
			break;
		case GLOWSTONE_DUST:
			break;
		case GOLDEN_CARROT:
			break;
		case GOLD_AXE:
			break;
		case GOLD_BARDING:
			return "Gold Horse Armor";
		case GOLD_BLOCK:
			break;
		case GOLD_BOOTS:
			return "Golden Boots";
		case GOLD_CHESTPLATE:
			return "Golden Chestplate";
		case GOLD_HELMET:
			return "Golden Helmet";
		case GOLD_HOE:
			return "Golden Hoe";
		case GOLD_INGOT:
			break;
		case GOLD_LEGGINGS:
			return "Golden Leggings";
		case GOLD_NUGGET:
			break;
		case GOLD_ORE:
			break;
		case GOLD_PICKAXE:
			return "Golden_Pickaxe";
		case GOLD_PLATE:
			return "Weighted_Pressure_Plate_(Light)";
		case GOLD_RECORD:
			return "Golden Record";
		case GOLD_SPADE:
			return "Golden Shovel";
		case GOLD_SWORD:
			return "Golden Sword";
		case GRASS:
			break;
		case GRAVEL:
			break;
		case GREEN_RECORD:
			break;
		case GRILLED_PORK:
			break;
		case HARD_CLAY:
			break;
		case HAY_BLOCK:
			break;
		case HOPPER:
			break;
		case HOPPER_MINECART:
			break;
		case HUGE_MUSHROOM_1:
			break;
		case HUGE_MUSHROOM_2:
			break;
		case ICE:
			break;
		case IRON_AXE:
			break;
		case IRON_BARDING:
			return "Iron_Horse_Armor";
		case IRON_BLOCK:
			break;
		case IRON_BOOTS:
			break;
		case IRON_CHESTPLATE:
			break;
		case IRON_DOOR:
			break;
		case IRON_DOOR_BLOCK:
			break;
		case IRON_HELMET:
			break;
		case IRON_HOE:
			break;
		case IRON_INGOT:
			break;
		case IRON_LEGGINGS:
			break;
		case IRON_ORE:
			break;
		case IRON_PICKAXE:
			break;
		case IRON_PLATE:
			break;
		case IRON_SPADE:
			return "Iron_Shovel";
		case IRON_SWORD:
			break;
		case ITEM_FRAME:
			break;
		case JACK_O_LANTERN:
			return "Jack_O'Lantern";
		case JUKEBOX:
			break;
		case JUNGLE_WOOD_STAIRS:
			break;
		case LADDER:
			break;
		case LAPIS_BLOCK:
			break;
		case LAPIS_ORE:
			break;
		case LAVA:
			break;
		case LAVA_BUCKET:
			break;
		case LEASH:
			break;
		case LEATHER:
			break;
		case LEATHER_BOOTS:
			break;
		case LEATHER_CHESTPLATE:
			break;
		case LEATHER_HELMET:
			break;
		case LEATHER_LEGGINGS:
			break;
		case LEAVES_2:
			switch ((int) damage) {
			case 0:
				return "Acacia_Leaves";
			case 1:
				return "Dark_Oak_Leaves";
			}
			return mat.toString();
		case LEVER:
			break;
		case LOG_2:
			switch ((int) damage) {
			case 0:
				return "ACACIA_LOG";
			case 1:
				return "DARK_OAK_LOG";
			}
			return mat.toString();
		case MAGMA_CREAM:
			break;
		case MAP:
			break;
		case MELON:
			break;
		case MELON_BLOCK:
			break;
		case MELON_SEEDS:
			break;
		case MELON_STEM:
			break;
		case MILK_BUCKET:
			break;
		case MINECART:
			break;
		case MOB_SPAWNER:
			break;
		case MONSTER_EGGS:
			break;
		case MOSSY_COBBLESTONE:
			break;
		case MUSHROOM_SOUP:
			break;
		case MYCEL:
			return "MYCELIUM";
		case NAME_TAG:
			break;
		case NETHERRACK:
			break;
		case NETHER_BRICK:
			break;
		case NETHER_BRICK_ITEM:
			return "Nether Brick (Small)";
		case NETHER_BRICK_STAIRS:
			break;
		case NETHER_FENCE:
			break;
		case NETHER_STAR:
			break;
		case NETHER_WARTS:
			break;
		case NOTE_BLOCK:
			break;
		case OBSIDIAN:
			break;
		case PACKED_ICE:
			break;
		case PAINTING:
			break;
		case PAPER:
			break;
		case PISTON_BASE:
			break;
		case PISTON_EXTENSION:
			break;
		case PISTON_MOVING_PIECE:
			break;
		case PISTON_STICKY_BASE:
			break;
		case POISONOUS_POTATO:
			break;
		case PORK:
			break;
		case PORTAL:
			break;
		case POTATO:
			break;
		case POWERED_MINECART:
			break;
		case POWERED_RAIL:
			break;
		case PUMPKIN:
			break;
		case PUMPKIN_PIE:
			break;
		case PUMPKIN_SEEDS:
			break;
		case PUMPKIN_STEM:
			break;
		case QUARTZ:
			break;
		case QUARTZ_BLOCK:
			break;
		case QUARTZ_ORE:
			break;
		case QUARTZ_STAIRS:
			break;
		case RAILS:
			break;
		case RAW_BEEF:
			break;
		case RAW_CHICKEN:
			break;
		case RAW_FISH:
			break;
		case RECORD_10:
			return "Ward Record";
		case RECORD_11:
			break;
		case RECORD_12:
			return "Wait Record (12)";
		case RECORD_3:
			return "Blocks Record (3)";
		case RECORD_4:
			return "Chirp Record (4)";
		case RECORD_5:
			return "Far Record (5)";
		case RECORD_6:
			return "Mall Record (6)";
		case RECORD_7:
			return "Mellohi Record (7)";
		case RECORD_8:
			return "Stal Record (8)";
		case RECORD_9:
			return "Strad Record (9)";
		case REDSTONE:
			break;
		case REDSTONE_BLOCK:
			break;
		case REDSTONE_COMPARATOR:
			break;
		case REDSTONE_COMPARATOR_OFF:
			break;
		case REDSTONE_COMPARATOR_ON:
			break;
		case REDSTONE_ORE:
			break;
		case REDSTONE_WIRE:
			break;
		case RED_MUSHROOM:
			break;
		case RED_ROSE:
			switch ((int) damage) {
			case 0:
				return "POPPY";
			case 1:
				return "BLUE_ORCHID";
			case 2:
				return "ALLIUM";
			case 3:
				return "AZURE_BLUET";
			case 4:
				return "RED_TULIP";
			case 5:
				return "ORANGE_TULIP";
			case 6:
				return "WHITE TULIP";
			case 7:
				return "PINK_TULIP";
			case 8:
				return "OXEYE_DAISY";
			}
			return mat.toString();
		case ROTTEN_FLESH:
			break;
		case SADDLE:
			break;
		case SAND:
			break;
		case SANDSTONE_STAIRS:
			break;
		case SEEDS:
			break;
		case SHEARS:
			break;
		case SIGN:
			break;
		case SIGN_POST:
			break;
		case SKULL:
			break;
		case SLIME_BALL:
			break;
		case SMOOTH_STAIRS:
			break;
		case SNOW:
			break;
		case SNOW_BALL:
			break;
		case SNOW_BLOCK:
			break;
		case SOIL:
			break;
		case SOUL_SAND:
			break;
		case SPECKLED_MELON:
			return "Glistering Melon";
		case SPIDER_EYE:
			break;
		case SPONGE:
			break;
		case SPRUCE_WOOD_STAIRS:
			break;
		case STAINED_CLAY:
			switch ((int) damage) {
			case 0:
				return "WHITE_STAINED_CLAY";
			case 1:
				return "ORANGE_STAINED_CLAY";
			case 2:
				return "MAGENTA_STAINED_CLAY";
			case 3:
				return "LIGHT_BLUE_STAINED_CLAY";
			case 4:
				return "YELLOW_STAINED_CLAY";
			case 5:
				return "LIME_STAINED_CLAY";
			case 6:
				return "PINK_STAINED_CLAY";
			case 7:
				return "GRAY_STAINED_CLAY";
			case 8:
				return "LIGHT_GRAY_STAINED_CLAY";
			case 9:
				return "CYAN_STAINED_CLAY";
			case 10:
				return "PURPLE_STAINED_CLAY";
			case 11:
				return "BLUE_STAINED_CLAY";
			case 12:
				return "BROWN_STAINED_CLAY";
			case 13:
				return "GREEN_STAINED_CLAY";
			case 14:
				return "RED_STAINED_CLAY";
			case 15:
				return "BLACK_STAINED_CLAY";
			}
			return mat.toString();
		case STAINED_GLASS:
			switch ((int) damage) {
			case 0:
				return "WHITE_STAINED_GLASS";
			case 1:
				return "ORANGE_STAINED_GLASS";
			case 2:
				return "MAGENTA_STAINED_GLASS";
			case 3:
				return "LIGHT_BLUE_STAINED_GLASS";
			case 4:
				return "YELLOW_STAINED_GLASS";
			case 5:
				return "LIME_STAINED_GLASS";
			case 6:
				return "PINK_STAINED_GLASS";
			case 7:
				return "GRAY_STAINED_GLASS";
			case 8:
				return "LIGHT_GRAY_STAINED_GLASS";
			case 9:
				return "CYAN_STAINED_GLASS";
			case 10:
				return "PURPLE_STAINED_GLASS";
			case 11:
				return "BLUE_STAINED_GLASS";
			case 12:
				return "BROWN_STAINED_GLASS";
			case 13:
				return "GREEN_STAINED_GLASS";
			case 14:
				return "RED_STAINED_GLASS";
			case 15:
				return "BLACK_STAINED_GLASS";
			}
			return mat.toString();
		case STAINED_GLASS_PANE:
			switch ((int) damage) {
			case 0:
				return "WHITE_STAINED_GLASS_PANE";
			case 1:
				return "ORANGE_STAINED_GLASS_PANE";
			case 2:
				return "MAGENTA_STAINED_GLASS_PANE";
			case 3:
				return "LIGHT_BLUE_STAINED_GLASS_PANE";
			case 4:
				return "YELLOW_STAINED_GLASS_PANE";
			case 5:
				return "LIME_STAINED_GLASS_PANE";
			case 6:
				return "PINK_STAINED_GLASS_PANE";
			case 7:
				return "GRAY_STAINED_GLASS_PANE";
			case 8:
				return "LIGHT_GRAY_STAINED_GLASS_PANE";
			case 9:
				return "CYAN_STAINED_GLASS_PANE";
			case 10:
				return "PURPLE_STAINED_GLASS_PANE";
			case 11:
				return "BLUE_STAINED_GLASS_PANE";
			case 12:
				return "BROWN_STAINED_GLASS_PANE";
			case 13:
				return "GREEN_STAINED_GLASS_PANE";
			case 14:
				return "RED_STAINED_GLASS_PANE";
			case 15:
				return "BLACK_STAINED_GLASS_PANE";
			}
			return mat.toString();
		case STATIONARY_LAVA:
			break;
		case STATIONARY_WATER:
			break;
		case STICK:
			break;
		case STONE:
			break;
		case STONE_AXE:
			break;
		case STONE_BUTTON:
			break;
		case STONE_HOE:
			break;
		case STONE_PICKAXE:
			break;
		case STONE_PLATE:
			break;
		case STONE_SPADE:
			return "Stone Shovel";
		case STONE_SWORD:
			break;
		case STORAGE_MINECART:
			break;
		case STRING:
			break;
		case SUGAR:
			break;
		case SUGAR_CANE:
			break;
		case SUGAR_CANE_BLOCK:
			break;
		case TNT:
			break;
		case TORCH:
			break;
		case TRAPPED_CHEST:
			break;
		case TRAP_DOOR:
			break;
		case TRIPWIRE:
			break;
		case TRIPWIRE_HOOK:
			break;
		case VINE:
			break;
		case WALL_SIGN:
			break;
		case WATCH:
			break;
		case WATER:
			break;
		case WATER_BUCKET:
			break;
		case WATER_LILY:
			break;
		case WHEAT:
			break;
		case WOODEN_DOOR:
			break;
		case WOOD_AXE:
			return "Wooden Axe";
		case WOOD_BUTTON:
			return "Wooden Button";
		case WOOD_DOOR:
			return "Wooden Door";
		case WOOD_DOUBLE_STEP:
			return "Wooden Double Step";
		case WOOD_HOE:
			return "Wooden Hoe";
		case WOOD_PICKAXE:
			return "Wooden Pickaxe";
		case WOOD_PLATE:
			return "Pressure Plate";
		case WOOD_SPADE:
			return "Wooden Shovel";
		case WOOD_STAIRS:
			return "Wooden Stairs";
		case WOOD_STEP:
			return "Wooden Slab";
		case WOOD_SWORD:
			return "Wooden Sword";
		case WRITTEN_BOOK:
			break;
		case YELLOW_FLOWER:
			return "Dandelion";
		default:
			break;
		}
		// This covers the rest of the items that have a "reasonable" name
		if (damage == 0 || isTool(mat))
			return mat.toString();
		// This returns something that has a durability qualifier, but we don't
		// know what it is.
		return mat.toString() + ":" + damage;
	}

	
	/**
	 * Converts a given material and data value into a format similar to Material.<?>.toString().
	 * Upper case, with underscores.  Includes material name in result.
	 * @param mat The base material.
	 * @param damage The durability/damage of the item.
	 * @return A string with the name of the item.
	 */
	/*
	private static String getDataName(Material mat, short damage){
		int id = mat.getId();
		switch(id){
		case 35: 
			switch((int) damage){
				case 0: return "WHITE_WOOL";
				case 1: return "ORANGE_WOOL";
				case 2: return "MAGENTA_WOOL";
				case 3: return "LIGHT_BLUE_WOOL";
				case 4: return "YELLOW_WOOL";
				case 5: return "LIME_WOOL";
				case 6: return "PINK_WOOL";
				case 7: return "GRAY_WOOL";
				case 8: return "LIGHT_GRAY_WOOL";
				case 9: return "CYAN_WOOL";
				case 10: return "PURPLE_WOOL";
				case 11: return "BLUE_WOOL";
				case 12: return "BROWN_WOOL";
				case 13: return "GREEN_WOOL";
				case 14: return "RED_WOOL";
				case 15: return "BLACK_WOOL";
			}
			return mat.toString();
		case 351:
			switch((int) damage){
				case 0: return "INK_SAC";
				case 1: return "ROSE_RED";
				case 2: return "CACTUS_GREEN";
				case 3: return "COCOA_BEANS";
				case 4: return "LAPIS_LAZULI";
				case 5: return "PURPLE_DYE";
				case 6: return "CYAN_DYE";
				case 7: return "LIGHT_GRAY_DYE";
				case 8: return "GRAY_DYE";
				case 9: return "PINK_DYE";
				case 10: return "LIME_DYE";
				case 11: return "DANDELION_YELLOW";
				case 12: return "LIGHT_BLUE_DYE";
				case 13: return "MAGENTA_DYE";
				case 14: return "ORANGE_DYE";
				case 15: return "BONE_MEAL";
			}
			return mat.toString();
		case 98:
			switch((int) damage){
				case 0: return "STONE_BRICKS";
				case 1: return "MOSSY_STONE_BRICKS";
				case 2: return "CRACKED_STONE_BRICKS";
				case 3: return "CHISELED_STONE_BRICKS";
			}
			return mat.toString();
		case 373:
			//Special case,.. Why?
			if(damage == 0) return "WATER_BOTTLE";
			
			Potion pot;
			try{
				pot = Potion.fromDamage(damage);
			}
			catch(Exception e){ return "CUSTOM_POTION"; }
			
			String prefix = "";
			String suffix = "";
			if(pot.getLevel() > 0) suffix += "_" + pot.getLevel();
			if(pot.hasExtendedDuration()) prefix += "EXTENDED_";
			if(pot.isSplash()) prefix += "SPLASH_";
			
			
			if(pot.getEffects().isEmpty()){
				switch((int) pot.getNameId()){
				case 0: return prefix + "MUNDANE_POTION" + suffix;
				case 7: return prefix + "CLEAR_POTION" + suffix;
				case 11: return prefix + "DIFFUSE_POTION" + suffix;
				case 13: return prefix + "ARTLESS_POTION" + suffix;
				case 15: return prefix + "THIN_POTION" + suffix;
				case 16: return prefix + "AWKWARD_POTION" + suffix;
				case 32: return prefix + "THICK_POTION" + suffix;
				}
			}
			else{
				String effects = "";
				for(PotionEffect effect : pot.getEffects()){
					effects += effect.toString().split(":")[0];
				}
				return prefix + effects + suffix;
			}
			return mat.toString();
		case 6:
			switch((int) damage){
				case 0: return "OAK_SAPLING";
				case 1: return "PINE_SAPLING";
				case 2: return "BIRCH_SAPLING";
				case 3: return "JUNGLE_TREE_SAPLING";
			}
			return mat.toString();
		
		case 5:
			switch((int) damage){
				case 0: return "OAK_PLANKS";
				case 1: return "PINE_PLANKS";
				case 2: return "BIRCH_PLANKS";
				case 3: return "JUNGLE_PLANKS";
			}
			return mat.toString();
		case 17:
			switch(damage){
				case 0: return "OAK_LOG";
				case 1: return "PINE_LOG";
				case 2: return "BIRCH_LOG";
				case 3: return "JUNGLE_LOG";
			}
			return mat.toString();
		case 18:
			damage = (short) (damage%4);
			switch(damage){
				case 0: return "OAK_LEAVES";
				case 1: return "PINE_LEAVES";
				case 2: return "BIRCH_LEAVES";
				case 3: return "JUNGLE_LEAVES";
			}
		case 263:
			switch(damage){
				case 0: return "COAL";
				case 1: return "CHARCOAL";
			}
			return mat.toString();
		case 24:
			switch((int) damage){
				case 0: return "SANDSTONE";
				case 1: return "CHISELED_SANDSTONE";
				case 2: return "SMOOTH_SANDSTONE";
			}
			return mat.toString();
		case 31:
			switch((int) damage){
				case 0: return "DEAD_SHRUB";
				case 1: return "TALL_GRASS";
				case 2: return "FERN";
			}
			return mat.toString();
		case 44:
			switch((int) damage){
				case 0: return "STONE_SLAB";
				case 1: return "SANDSTONE_SLAB";
				case 2: return "WOODEN_SLAB";
				case 3: return "COBBLESTONE_SLAB";
				case 4: return "BRICK_SLAB";
				case 5: return "STONE_BRICK_SLAB";
			}
			return mat.toString();
		case 383:
			switch((int) damage){
				case 50: return "CREEPER_EGG";
				case 51: return "SKELETON_EGG";
				case 52: return "SPIDER_EGG";
				case 53: return "GIANT_EGG";
				case 54: return "ZOMBIE_EGG";
				case 55: return "SLIME_EGG";
				case 56: return "GHAST_EGG";
				case 57: return "ZOMBIE_PIGMAN_EGG";
				case 58: return "ENDERMAN_EGG";
				case 59: return "CAVE_SPIDER_EGG";
				case 60: return "SILVERFISH_EGG";
				case 61: return "BLAZE_EGG";
				case 62: return "MAGMA_CUBE_EGG";
				case 63: return "ENDER_DRAGON_EGG";
				case 90: return "PIG_EGG";
				case 91: return "SHEEP_EGG";
				case 92: return "COW_EGG";
				case 93: return "CHICKEN_EGG";
				case 94: return "SQUID_EGG";
				case 95: return "WOLF_EGG";
				case 96: return "MOOSHROOM_EGG";
				case 97: return "SNOW_GOLEM_EGG";
				case 98: return "OCELOT_EGG";
				case 99: return "IRON_GOLEM_EGG";
				case 120: return "VILLAGER_EGG";
				case 200: return "ENDER_CRYSTAL_EGG";
				case 14: return "PRIMED_TNT_EGG";
				case 66: return "WITCH_EGG";
				case 65: return "BAT_EGG";
			}
			return mat.toString();
		case 397:
			switch((int) damage){
				case 0: return "SKELETON_SKULL";
				case 1: return "WITHER_SKULL";
				case 2: return "ZOMBIE_HEAD";
				case 3: return "PLAYER_HEAD";
				case 4: return "CREEPER_HEAD";
			}
			break;
		case 76:
			return "REDSTONE_TORCH";
		case 115:
			return "NETHER_WART";
		case 30:
			return "COBWEB";
		case 102:
			return "GLASS_PANE";
		case 101:
			return "IRON_BARS";
		case 58:
			return "CRAFTING_TABLE";
		case 123:
			return "REDSTONE_LAMP";
		case 392:
			return "POTATO";
		case 289:
			return "GUNPOWDER";
		case 391:
			return "CARROT";
		case 322:
			switch((int) damage){
				case 0: return "GOLDEN_APPLE";
				case 1: return "ENCHANTED_GOLDEN_APPLE";
			}
			break;
		case 390:
			return "FLOWER_POT";
		case 145:
			switch((int) damage){
				case 0: return "ANVIL";
				case 1: return "SLIGHTLY_DAMAGED_ANVIL";
				case 2: return "VERY_DAMAGED:ANVIL";
			}
			break;
		case 384:
			return "BOTTLE_O'_ENCHANTING";
		case 402:
			return "FIREWORK_STAR";
		case 385:
			return "FIREWORK_CHARGE";
		}
		
		if(damage == 0 || isTool(mat)) return mat.toString();
		return mat.toString()+ ":" + damage;
	}
	*/
	/**
	 * @param mat The material to check
	 * @return Returns true if the item is a tool (Has durability) or false if it doesn't.
	 */
	public static boolean isTool(Material mat){
		return tools.contains(mat);
	}
	
	/**
	 * Compares two items to each other. Returns true if they match.
	 * @param stack1 The first item stack
	 * @param stack2 The second item stack
	 * @return true if the itemstacks match. (Material, durability, enchants)
	 */
	public static boolean matches(ItemStack stack1, ItemStack stack2){
		if(stack1 == stack2) return true; //Referring to the same thing, or both are null.
		if(stack1 == null || stack2 == null) return false; //One of them is null (Can't be both, see above)
		
		if(stack1.getType() != stack2.getType()) return false; //Not the same material
		if(stack1.getDurability() != stack2.getDurability()) return false; //Not the same durability
		if(!stack1.getEnchantments().equals(stack2.getEnchantments())) return false; //They have the same enchants
		
		try{
			Class.forName("org.bukkit.inventory.meta.EnchantmentStorageMeta");
			boolean book1 = stack1.getItemMeta() instanceof EnchantmentStorageMeta;
			boolean book2 = stack2.getItemMeta() instanceof EnchantmentStorageMeta;
			if(book1 != book2) return false;//One has enchantment meta, the other does not.
			if(book1 == true){ //They are the same here (both true or both false).  So if one is true, the other is true.
				Map<Enchantment, Integer> ench1 = ((EnchantmentStorageMeta) stack1.getItemMeta()).getStoredEnchants();
				Map<Enchantment, Integer> ench2 = ((EnchantmentStorageMeta) stack2.getItemMeta()).getStoredEnchants();
				if(!ench1.equals(ench2)) return false; //Enchants aren't the same.
			}
		}
		catch(ClassNotFoundException e){
			//Nothing. They dont have a build high enough to support this.
		}
		
		return true;
	}
	
	/**
	 * Formats the given number according to how vault would like it.
	 * E.g. $50 or 5 dollars.
	 * @return The formatted string.
	*/
	public static String format(double n){
		try{
			return plugin.getEcon().format(n);
		}
		catch(NumberFormatException e){
			return "$"+n;
		}
	}
	
	/**
	 * @param m The material to check if it is blacklisted
	 * @return true if the material is black listed. False if not.
	 */
	public static boolean isBlacklisted(Material m){
		return blacklist.contains(m);
	}
	
	/**
	 * Fetches the block which the given sign is attached to
	 * @param sign The sign which is attached
	 * @return The block the sign is attached to
	 */
	public static Block getAttached(Block b){
		try{
			Sign sign = (Sign) b.getState().getData(); //Throws a NPE sometimes??
			BlockFace attached = sign.getAttachedFace();
			
			if(attached == null) return null;
			return b.getRelative(attached);
		}
		catch(NullPointerException e){
			return null; ///Not sure what causes this.
		}
	}
	
	/**
	 * Counts the number of items in the given inventory where Util.matches(inventory item, item) is true.
	 * @param inv The inventory to search
	 * @param item The ItemStack to search for
	 * @return The number of items that match in this inventory.
	 */
	public static int countItems(Inventory inv, ItemStack item){
		int items = 0;
		for(ItemStack iStack : inv.getContents()){
			if(iStack == null) continue;
			if(Util.matches(item, iStack)){
				items += iStack.getAmount();
			}
		}
		return items;
	}
	
	/**
	 * Returns the number of items that can be given to the inventory safely.
	 * @param inv The inventory to count
	 * @param item The item prototype.  Material, durabiltiy and enchants must match for 'stackability' to occur.
	 * @return The number of items that can be given to the inventory safely.
	 */
	public static int countSpace(Inventory inv, ItemStack item){
		int space = 0;
		for(ItemStack iStack : inv.getContents()){
			if(iStack == null || iStack.getType() == Material.AIR){
				space += item.getMaxStackSize();
			}
			else if(matches(item, iStack)){
				space += item.getMaxStackSize() - iStack.getAmount();
			}
		}
		return space;
	}
	
	/**
	 * Returns true if the given location is loaded or not.
	 * @param loc The location
	 * @return true if the given location is loaded or not.
	 */
	public static boolean isLoaded(Location loc){
		//System.out.println("Checking isLoaded(Location loc)");
		if(loc.getWorld() == null){
			//System.out.println("Is not loaded. (No world)");
			return false;
		}
		//Calculate the chunks coordinates.  These are 1,2,3 for each chunk, NOT location rounded to the nearest 16.
		int x = (int) Math.floor((loc.getBlockX()) / 16.0);
		int z = (int) Math.floor((loc.getBlockZ()) / 16.0);
		
		if(loc.getWorld().isChunkLoaded(x, z)){
			//System.out.println("Chunk is loaded " + x + ", " + z);
			return true;
		}
		else{
			//System.out.println("Chunk is NOT loaded " + x + ", " + z);
			return false;
		}
	}
}
