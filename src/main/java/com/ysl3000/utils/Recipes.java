package com.ysl3000.utils;

import com.ysl3000.plugin.SmartServerTool;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.SpawnEgg;
import org.bukkit.plugin.java.JavaPlugin;

public class Recipes extends JavaPlugin {

    private SmartServerTool plugin;

    public Recipes(SmartServerTool plugin) {
        this.plugin = plugin;
        registerRecipes();
    }

    private void registerRecipes() {

        registerEggs();
        registerGlowstones();
        registerIronIngot();
        registerGoldIngot();
        registerDiamond();
        registerGrass();
        registerMossyCobble();
        registerChainAmor();
        registerSaddle();

        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isFurnaceSponge()) {
            FurnaceRecipe sponge = new FurnaceRecipe(new ItemStack(
                    Material.SPONGE, 1), Material.SANDSTONE);
            plugin.getServer().addRecipe(sponge);
        }
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingEnchantmentTable()) {
            ShapedRecipe sr = new ShapedRecipe(new ItemStack(
                    Material.ENCHANTING_TABLE, 1))
                    .shape("   ", " b ", "www")
                    .setIngredient('b', Material.BOOKSHELF)
                    .setIngredient('w', Material.OAK_PLANKS);

            this.plugin.getServer().addRecipe(sr);
        }

        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCrafttingWeed()) {
            ShapelessRecipe wheat = new ShapelessRecipe(new ItemStack(
                    Material.WHEAT_SEEDS, 3)).addIngredient(Material.WHEAT);
            this.plugin.getServer().addRecipe(wheat);
        }

        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isFurnaceNetherBrick()) {
            FurnaceRecipe nbrick = new FurnaceRecipe(new ItemStack(
                    Material.NETHER_BRICK, 1), Material.NETHERRACK);
            this.plugin.getServer().addRecipe(nbrick);
        }

        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingNet()) {
            ShapelessRecipe netz1 = new ShapelessRecipe(new ItemStack(
                    Material.STRING, 3)).addIngredient(Material.COBWEB);

            ShapedRecipe netz2 = new ShapedRecipe(
                    new ItemStack(Material.COBWEB, 1)).shape(
                    "s s", " s ", "s s").setIngredient('s',
                    Material.STRING);
            this.plugin.getServer().addRecipe(netz1);
            this.plugin.getServer().addRecipe(netz2);
        }

        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingLongGrass()) {
            ShapelessRecipe LongGrass = new ShapelessRecipe(new ItemStack(
                    Material.GRASS, 3, (short) 1))
                    .addIngredient(Material.GRASS);
            this.plugin.getServer().addRecipe(LongGrass);
        }

        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingCommandBlock()) {
            ShapedRecipe commandBlock = new ShapedRecipe(new ItemStack(
                    Material.COMMAND_BLOCK, 1))
                    .shape("nmn", "mdm", "nmn")
                    .setIngredient('n', Material.NOTE_BLOCK)
                    .setIngredient('m', Material.JUKEBOX)
                    .setIngredient('d', Material.DIAMOND);
            this.plugin.getServer().addRecipe(commandBlock);
        }

        ShapelessRecipe magmacream = new ShapelessRecipe(new ItemStack(
                Material.MAGMA_CREAM, 1)).addIngredient(Material.CRAFTING_TABLE)
                .addIngredient(Material.ENCHANTING_TABLE)
                .addIngredient(Material.ENDER_CHEST);
        this.plugin.getServer().addRecipe(magmacream);
    }

    private void registerEggs() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingEgg()) {
            ShapedRecipe villager = new ShapedRecipe(new ItemStack(Material.VILLAGER_SPAWN_EGG, 1))
                    .shape("rcr", "rer", "rlr")
                    .setIngredient('r', Material.EMERALD)
                    .setIngredient('c', Material.CRAFTING_TABLE)
                    .setIngredient('l', Material.LEATHER)
                    .setIngredient('e', Material.EGG);

            ShapedRecipe wolf = new ShapedRecipe(
                    new SpawnEgg(EntityType.WOLF).toItemStack(1))
                    .shape("rbr", "rer", "rcr")
                    .setIngredient('r', Material.EMERALD)
                    .setIngredient('b', Material.BONE)
                    .setIngredient('c', Material.ROTTEN_FLESH)
                    .setIngredient('e', Material.EGG);

            ShapedRecipe pig = new ShapedRecipe(
                    new SpawnEgg(EntityType.PIG).toItemStack(1))
                    .shape("rbr", "rer", "rcr")
                    .setIngredient('r', Material.EMERALD)
                    .setIngredient('b', Material.BONE)
                    .setIngredient('c', Material.COOKED_PORKCHOP)
                    .setIngredient('e', Material.EGG);

            ShapedRecipe sheep = new ShapedRecipe(
                    new ItemStack(Material.SHEEP_SPAWN_EGG, 1))
                    .shape("rbr", "rer", "rcr")
                    .setIngredient('r', Material.EMERALD)
                    .setIngredient('b', Material.BONE)
                    .setIngredient('c', Material.WHITE_WOOL)
                    .setIngredient('e', Material.EGG);

            ShapedRecipe cow = new ShapedRecipe(
                    new ItemStack(Material.COW_SPAWN_EGG, 1))
                    .shape("rbr", "rer", "rcr")
                    .setIngredient('r', Material.EMERALD)
                    .setIngredient('b', Material.BONE)
                    .setIngredient('c', Material.BEEF)
                    .setIngredient('e', Material.EGG);

            ShapedRecipe chicken = new ShapedRecipe(
                    new ItemStack(Material.CHICKEN_SPAWN_EGG, 1))
                    .shape("rbr", "rer", "rcr")
                    .setIngredient('r', Material.EMERALD)
                    .setIngredient('b', Material.BONE)
                    .setIngredient('c', Material.CHICKEN)
                    .setIngredient('e', Material.EGG);

            plugin.getServer().addRecipe(villager);
            plugin.getServer().addRecipe(wolf);
            plugin.getServer().addRecipe(chicken);
            plugin.getServer().addRecipe(cow);
            plugin.getServer().addRecipe(sheep);
            plugin.getServer().addRecipe(pig);
        }
    }

    private void registerGlowstones() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingGlowStone()) {
            ShapedRecipe glows1 = new ShapedRecipe(new ItemStack(
                    Material.GLOWSTONE)).shape(
                    "rr ", "rr ", "   ").setIngredient('r',
                    Material.REDSTONE_TORCH);
            ShapedRecipe glows2 = new ShapedRecipe(new ItemStack(
                    Material.GLOWSTONE)).shape(
                    " rr", " rr", "   ").setIngredient('r',
                    Material.REDSTONE_TORCH);
            ShapedRecipe glows3 = new ShapedRecipe(new ItemStack(
                    Material.GLOWSTONE)).shape(
                    "   ", "rr ", "rr ").setIngredient('r',
                    Material.REDSTONE_TORCH);
            ShapedRecipe glows4 = new ShapedRecipe(new ItemStack(
                    Material.GLOWSTONE)).shape(
                    "   ", " rr", " rr").setIngredient('r',
                    Material.REDSTONE_TORCH);
            registerRecipes(glows1, glows2, glows3, glows4);
        }
    }

    private void registerIronIngot() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isFurnaceIronIngot()) {
            FurnaceRecipe ironingot1 = new FurnaceRecipe(new ItemStack(
                    Material.IRON_INGOT, 1), Material.IRON_PICKAXE);
            FurnaceRecipe ironingot2 = new FurnaceRecipe(new ItemStack(
                    Material.IRON_INGOT, 1), Material.IRON_AXE);
            FurnaceRecipe ironingot3 = new FurnaceRecipe(new ItemStack(
                    Material.IRON_INGOT, 1), Material.IRON_HOE);
            FurnaceRecipe ironingot4 = new FurnaceRecipe(new ItemStack(
                    Material.IRON_INGOT, 1), Material.IRON_SWORD);

            registerRecipes(ironingot1, ironingot2, ironingot3, ironingot4);
        }
    }

    private void registerGoldIngot() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isFurnaceGoldIngot()) {
            FurnaceRecipe goldingot1 = new FurnaceRecipe(new ItemStack(
                    Material.GOLD_INGOT, 1), Material.GOLDEN_PICKAXE);
            FurnaceRecipe goldingot2 = new FurnaceRecipe(new ItemStack(
                    Material.GOLD_INGOT, 1), Material.GOLDEN_AXE);
            FurnaceRecipe goldingot3 = new FurnaceRecipe(new ItemStack(
                    Material.GOLD_INGOT, 1), Material.GOLDEN_HOE);
            FurnaceRecipe goldingot4 = new FurnaceRecipe(new ItemStack(
                    Material.GOLD_INGOT, 1), Material.GOLDEN_SWORD);

            registerRecipes(goldingot1, goldingot2, goldingot3, goldingot4);
        }
    }

    private void registerDiamond() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isFurnaceDiamondIngot()) {
            FurnaceRecipe diamond1 = new FurnaceRecipe(new ItemStack(
                    Material.DIAMOND, 1), Material.DIAMOND_PICKAXE);
            FurnaceRecipe diamond2 = new FurnaceRecipe(new ItemStack(
                    Material.DIAMOND, 1), Material.DIAMOND_AXE);
            FurnaceRecipe diamond3 = new FurnaceRecipe(new ItemStack(
                    Material.DIAMOND, 1), Material.DIAMOND_HOE);
            FurnaceRecipe diamond4 = new FurnaceRecipe(new ItemStack(
                    Material.DIAMOND, 1), Material.DIAMOND_SWORD);

            registerRecipes(diamond1, diamond2, diamond3, diamond4);
        }
    }

    private void registerGrass() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingGrass()) {
            ShapedRecipe grass1 = new ShapedRecipe(new ItemStack(
                    Material.GRASS, 2)).shape(
                    "ee ", "ee ", "   ").setIngredient('e',
                    Material.DIRT);
            ShapedRecipe grass2 = new ShapedRecipe(new ItemStack(
                    Material.GRASS, 2)).shape(
                    " ee", " ee", "   ").setIngredient('e',
                    Material.DIRT);
            ShapedRecipe grass3 = new ShapedRecipe(new ItemStack(
                    Material.GRASS, 2)).shape(
                    "   ", " ee", " ee").setIngredient('e',
                    Material.DIRT);
            ShapedRecipe grass4 = new ShapedRecipe(new ItemStack(
                    Material.GRASS, 2)).shape(
                    "   ", "ee ", "ee ").setIngredient('e',
                    Material.DIRT);

            registerRecipes(grass1, grass2, grass3, grass4);
        }
    }

    private void registerMossyCobble() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingCobblestone()) {

            ShapedRecipe oldcobble1 = new ShapedRecipe(new ItemStack(
                    Material.MOSSY_COBBLESTONE, 1))
                    .shape("g  ", "c  ", "   ")
                    .setIngredient('g', Material.GRASS)
                    .setIngredient('c', Material.COBBLESTONE);
            ShapedRecipe oldcobble2 = new ShapedRecipe(new ItemStack(
                    Material.MOSSY_COBBLESTONE, 1))
                    .shape(" g ", " c ", "   ")
                    .setIngredient('g', Material.GRASS)
                    .setIngredient('c', Material.COBBLESTONE);
            ShapedRecipe oldcobble3 = new ShapedRecipe(new ItemStack(
                    Material.MOSSY_COBBLESTONE, 1))
                    .shape("  g", "  c", "   ")
                    .setIngredient('g', Material.GRASS)
                    .setIngredient('c', Material.COBBLESTONE);
            ShapedRecipe oldcobble4 = new ShapedRecipe(new ItemStack(
                    Material.MOSSY_COBBLESTONE, 1))
                    .shape("   ", "g  ", "c  ")
                    .setIngredient('g', Material.GRASS)
                    .setIngredient('c', Material.COBBLESTONE);
            ShapedRecipe oldcobble5 = new ShapedRecipe(new ItemStack(
                    Material.MOSSY_COBBLESTONE, 1))
                    .shape("   ", " g ", " c ")
                    .setIngredient('g', Material.GRASS)
                    .setIngredient('c', Material.COBBLESTONE);
            ShapedRecipe oldcobble6 = new ShapedRecipe(new ItemStack(
                    Material.MOSSY_COBBLESTONE, 1))
                    .shape("   ", "  g", "  c")
                    .setIngredient('g', Material.GRASS)
                    .setIngredient('c', Material.COBBLESTONE);


            registerRecipes(oldcobble1, oldcobble2, oldcobble3, oldcobble4, oldcobble5, oldcobble6);
        }

    }

    private void registerChainAmor() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingChainHelmet()) {
            ShapedRecipe chainHelm1 = new ShapedRecipe(new ItemStack(
                    Material.CHAINMAIL_HELMET, 1))
                    .shape("gdg", "g g", "   ")
                    .setIngredient('g', Material.GLASS)
                    .setIngredient('d', Material.DIAMOND);

            ShapedRecipe chainHelm2 = new ShapedRecipe(new ItemStack(
                    Material.CHAINMAIL_HELMET, 1))
                    .shape("   ", "gdg", "g g")
                    .setIngredient('g', Material.GLASS)
                    .setIngredient('d', Material.DIAMOND);
            this.plugin.getServer().addRecipe(chainHelm1);
            this.plugin.getServer().addRecipe(chainHelm2);
        }
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingChainChest()) {
            ShapedRecipe chainChestplate = new ShapedRecipe(new ItemStack(
                    Material.CHAINMAIL_CHESTPLATE, 1))
                    .shape("g g", "gdg", "ggg")
                    .setIngredient('g', Material.GLASS)
                    .setIngredient('d', Material.DIAMOND);
            this.plugin.getServer().addRecipe(chainChestplate);
        }
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingChainLegg()) {
            ShapedRecipe chainLeggings = new ShapedRecipe(new ItemStack(
                    Material.CHAINMAIL_LEGGINGS, 1))
                    .shape("gdg", "g g", "g g")
                    .setIngredient('g', Material.GLASS)
                    .setIngredient('d', Material.DIAMOND);
            this.plugin.getServer().addRecipe(chainLeggings);
        }
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingChainFeet()) {
            ShapedRecipe chainBoots1 = new ShapedRecipe(new ItemStack(
                    Material.CHAINMAIL_BOOTS, 1)).shape(
                    "g g", "g g", "   ").setIngredient('g',
                    Material.GLASS);

            ShapedRecipe chainBoots2 = new ShapedRecipe(new ItemStack(
                    Material.CHAINMAIL_BOOTS, 1)).shape(
                    "   ", "g g", "g g").setIngredient('g',
                    Material.GLASS);
            this.plugin.getServer().addRecipe(chainBoots1);
            this.plugin.getServer().addRecipe(chainBoots2);
        }

    }

    private void registerSaddle() {
        if (RecipeConfigloader.createAndReturnRecipeConfig(plugin).isCraftingSadle()) {
            ShapelessRecipe sattel1 = new ShapelessRecipe(new ItemStack(
                    Material.LEATHER, 3)).addIngredient(Material.SADDLE);

            ShapedRecipe sattel2 = new ShapedRecipe(new ItemStack(
                    Material.SADDLE, 1)).shape(
                    "l l", "lll", "   ").setIngredient('l',
                    Material.LEATHER);

            ShapedRecipe sattel3 = new ShapedRecipe(new ItemStack(
                    Material.SADDLE, 1)).shape(
                    "   ", "l l", "lll").setIngredient('l',
                    Material.LEATHER);
            this.plugin.getServer().addRecipe(sattel1);
            this.plugin.getServer().addRecipe(sattel2);
            this.plugin.getServer().addRecipe(sattel3);
        }
    }

    private void registerRecipes(Recipe... recipes) {
        for (Recipe recipe : recipes) {
            this.plugin.getServer().addRecipe(recipe);
        }
    }
}
