package com.github.ysl3000;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemMan {

	public static void item(CommandSender sender, String command,
			String[] args, Command cmd) {
		int itemid = 0;
		int amount;
		short damage;
		String[] itemcommand;
		Player player = (Player) sender;

		if (command.equalsIgnoreCase("i") && sender.hasPermission("sst.item")) {

			if (sender instanceof Player) {
				if (args.length == 0) {

					player.sendMessage("/i <itemid> [amount]");
				} else if (args.length == 1) {

					itemcommand = args[0].split(":");

					try {

						itemid = Integer.parseInt(itemcommand[0]);

					} catch (NumberFormatException e) {

						player.sendMessage(ChatColor.RED
								+ "Until now Itemname isn't implemented");
						// itemid = itemconnect(itemcommand[0]);
						return;

					}

					amount = 20;

					if (itemcommand.length == 2) {

						damage = Short.parseShort(itemcommand[1]);
					} else {
						damage = 0;
					}

					ItemStack itemst = new ItemStack(itemid, amount, damage);

					player.getInventory().addItem(itemst);

					player.sendMessage("20x " + args[0] + " are given!");
				} else if (args.length == 2) {

					itemcommand = args[0].split(":");

					try {
						itemid = Integer.parseInt(itemcommand[0]);
					} catch (Exception e) {

						 

						player.sendMessage(ChatColor.RED
								+ "Until now Itemname isn't implemented");
						return;
					}

					amount = Integer.parseInt(args[1]);

					if (itemcommand.length == 2) {
						damage = Short.parseShort(itemcommand[1]);
					} else {
						damage = 0;
					}

					ItemStack itemst = new ItemStack(itemid, amount, damage);
					player.getInventory().addItem(itemst);
					player.sendMessage(amount + "x " + args[0] + " are given!");

				}
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		} else if (command.equalsIgnoreCase("ci")
				&& sender.hasPermission("sst.ci")) {

			if (sender instanceof Player) {
				player.getInventory().clear();
				player.sendMessage("Inventory cleared");
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		} else if (command.equalsIgnoreCase("repair")
				&& sender.hasPermission("sst.repair")) {

			if (sender instanceof Player) {
				Map<Enchantment, Integer> ench = player.getItemInHand()
						.getEnchantments();

				short du = 0;
				player.getItemInHand().setDurability(du);
				player.getItemInHand().addEnchantments(ench);
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		}

	}

	/*public static int itemconnect(String itemname) {

		int id = 0;

		if (itemname == "stone") {
			id = 1;
		} else if (itemname == "grass") {

			id = 2;
		} else if (itemname == "dirt") {

			id = 3;
		} else if (itemname == "cobblestone") {

			id = 4;
		} else if (itemname == "wood") {

			id = 5;
		} else if (itemname == "sapling") {

			id = 6;
		} else if (itemname == "bedrock") {

			id = 7;
		} else if (itemname == "water") {

			id = 8;
		} else if (itemname == "swater") {

			id = 9;
		} else if (itemname == "lava") {

			id = 10;
		} else if (itemname == "slava") {

			id = 11;
		} else if (itemname == "sand") {

			id = 12;
		} else if (itemname == "gravel") {

			id = 13;
		} else if (itemname == "goldore") {

			id = 14;
		} else if (itemname == "ironore") {

			id = 15;
		} else if (itemname == "coal") {

			id = 16;
		} else if (itemname == "log") {

			id = 17;
		} else if (itemname == "leaves") {

			id = 18;
		} else if (itemname == "sponge") {

			id = 19;
		} else if (itemname == "glass") {

			id = 20;
		} else if (itemname == "lapis") {

			id = 21;
		} else if (itemname == "dispenser") {

			id = 22;
		} else if (itemname == "grass_mod") {

			id = 23;
		} else if (itemname == "sandstone") {

			id = 24;
		} else if (itemname == "noteblock") {

			id = 25;
		} else if (itemname == "bed") {

			id = 26;
		} else if (itemname == "boostrail") {

			id = 27;
		} else if (itemname == "detectrail") {

			id = 28;
		} else if (itemname == "spiston") {

			id = 29;
		} else if (itemname == "web") {

			id = 30;
		} else if (itemname == "grass_mod4") {

			id = 31;
		} else if (itemname == "grass_mod3") {

			id = 32;
		} else if (itemname == "grass_mod2") {

			id = 33;
		} else if (itemname == "piston") {

			id = 34;
		} else if (itemname == "wool") {

			id = 35;
		} else if (itemname == "flower") {

			id = 37;
		}

		return id;

	}*/
}

/*
 * stone: 1 grass: 2 dirt: 3 cobblestone: 4 wood: 5 sapling: 6 bedrock: 7 water:
 * 8 lava: 10 sand: 12 gravel: 13 goldore: 14 ironore: 15 coal: 16 log: 17
 * leaves: 18 sponge: 19 glass: 20 lapis: 21 dispenser: 22 sandstone: 24
 * noteblock: 25 bed: 26 boostrail: 27 detectrail: 28 spiston: 29 web: 30
 * piston: 33 wool: 35 flower: 37 rose: 38 bmushroom: 39 rmushroom: 40
 * goldblock: 41 ironblock: 42 doubleslab: 43 slab: 44 brick: 45 tnt: 46
 * bookblock: 47 mossycobble: 48 obsidian: 49 torch: 50 mobspawner: 52
 * woodenstair: 53 chest: 54 redstone: 55 diamondore: 57 workbench: 58
 * weedcrops: 59 bfurnace: 62 sign: 63 wooddoor: 64 ladder: 65 rail: 66
 * stonestair: 67 lever: 69 spplate: 70 irondoor: 71 wpplate: 72 redstoneore: 73
 * rstorch: 75 button: 77 ice: 79 snowblock: 80 cactus: 81 clay: 82 cane: 83
 * jukebox: 84 fence: 85 pumpkin: 86 netherrack: 87 soulsand: 88 glowstone: 89
 * portal: 90 latern: 91 cake: 92 repeater: 93 trapdoor: 96 silverstone: 97
 * stonebrick: 98 bmushblock: 99 rmushblock: 100 ironfence: 101 glasspane: 102
 * melone: 103 pumpseed: 104 meloneseed: 105 liane: 106 fgate: 107 brickstair:
 * 108 stonebrickstair: 109 myzel: 110 searose: 111 netherbrick: 112
 * netherstair: 113 netherwart: 114 magictable: 115 brewstand: 116 cauldron: 118
 * epblock: 120 endstone: 121 dragonegg: 122 redstonelamp: 123 # Items
 * ironshovel: 256 ironpickaxe: 257 ironaxe: 258 flintandsteel: 259 apple: 260
 * bow: 261 arrow: 262 coal: 263 diamond: 264 ironingot: 265 goldingot: 266
 * ironsword: 267 woodensword: 268 woodenshovel: 269 woodenpickaxe: 270
 * woodenaxe: 271 stonesword: 272 stoneshovel: 273 stonepickaxe: 274 stoneaxe:
 * 275 diamondsword: 276 diamondshovel: 277 diamondpickaxe: 278 diamonaxe: 279
 * stick: 280 bowl: 281 mushroomsoup: 282 goldensword: 283 goldenshovel: 284
 * goldenpickaxe: 285 goldenaxe: 286 string: 287 feather: 288 gunpowder: 289
 * woodenhoe: 290 stonehoe: 291 ironhoe: 292 diamondhoe: 293 goldhoe: 294 seeds:
 * 295 wheat: 296 weed: 296 bread: 297 leathercap: 298 leathertunic: 299
 * leatherpants: 300 leatherboots: 301 chainhelmet: 302 chainchestplate: 303
 * chainleggings: 304 chainboots: 305 ironhemet: 306 ironchestplate: 307
 * ironleggings: 308 ironboots: 309 diamondhelmet: 310 diamondchestplate: 311
 * diamondleggings: 312 diamondboots: 313 goldenhelmet: 314 goldenchestplate:
 * 315 goldenleggings: 316 goldenboots: 317 flint: 318 rawporkchop: 319
 * cookedporkchop: 320 paintings: 321 goldenapple: 322 sign: 323 woodendoor: 324
 * bucket: 325 waterbucket: 326 lavabucket: 327 bukkit: 327 minecart: 328
 * saddle: 329 irondoor: 330 redstonedust: 331 snowball: 332 boat: 333 leather:
 * 334 milk: 335 claybrick: 336 clay: 337 sugarcane: 338 paper: 339 book: 340
 * slimeball: 341 minecartwithchest: 342 minecartwithfurnance: 342 chickenegg:
 * 344 compass: 345 fishingrod: 346 clock: 347 glowstonedust: 348 rawfish: 349
 * cookedfish: 350 dye: 351 bone: 352 sugar: 353 cake: 354 bed: 355
 * redstonerepeater: 356 cookie: 357 map: 358 shears: 359 melonslice: 360
 * pumpkinseeds: 361 melonseeds: 362 rawbeef: 363 steak: 364 rawchicken: 365
 * cookedchicken: 366 rottenflesh: 367 enderpearl: 368 blazerod: 369 ghasttear:
 * 370 goldnugget: 371 netherwart: 372 potions: 373 glassbottle: 374 spidereye:
 * 375 fermentedspidereye: 376 blazepowder: 377 magmacream: 378 brewingstand:
 * 379 cauldron: 380 eyeofender: 381 glisteringmelon: 382 spawnegg: 383
 * bottleoenchanting: 384 firecharge: 385 13disc: 2256 catdisc: 2257 blocksdisc:
 * 2258 chirpdisc: 2259 fardisc: 2260 malldisc: 2261 mellohidisc: 2262 staldisc:
 * 2263 straddisc: 2264 warddisc: 2265 11disc: 2266
 */
