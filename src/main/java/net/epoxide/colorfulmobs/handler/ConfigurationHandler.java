package net.epoxide.colorfulmobs.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.epoxide.colorfulmobs.lib.Constants;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

    public static boolean spawnRandom = true;
    public static boolean dropPowder = true;
    public static boolean limitMobs = false;
    public static List<String> validMobs = null;
    public static boolean dyePlayer = false;

    public static String GENERAL = "General";

    public static Configuration config;

    public ConfigurationHandler(File file) {

        config = new Configuration(file);

        FMLCommonHandler.instance().bus().register(this);
        syncConfigData();
    }

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equals(Constants.MOD_ID))
            syncConfigData();
    }

    private void syncConfigData() {

        List<String> propOrder = new ArrayList<String>();
        Property prop;

        prop = config.get(GENERAL, "Spawned Dyed Mobs", true);
        prop.comment = "Are mobs dyed on spawn?";
        prop.setLanguageKey("colorfulmobs.configGui.spawnRandom");
        spawnRandom = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(GENERAL, "Drop Dye Powder", true);
        prop.comment = "Do mobs drop dye powder on death?";
        prop.setLanguageKey("colorfulmobs.configGui.dropPowder");
        dropPowder = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(GENERAL, "Player Dye", true);
        prop.comment = "Can players dye each other?";
        prop.setLanguageKey("colorfulmobs.configGui.playerDye");
        dyePlayer = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(GENERAL, "Limit Valid Mobs", false);
        prop.comment = "If only the valid mobs spawn";
        prop.setLanguageKey("colorfulmobs.configGui.limitMobs`");
        limitMobs = prop.getBoolean(false);
        propOrder.add(prop.getName());

        prop = config.get(GENERAL, "Valid Mobs", new String[] {});
        prop.comment = "Valid Mobs that can spawn";
        prop.setLanguageKey("colorfulmobs.configGui.validMobs");
        validMobs = Arrays.asList(prop.getStringList());
        propOrder.add(prop.getName());

        config.setCategoryPropertyOrder(GENERAL, propOrder);

        if (config.hasChanged())
            config.save();
    }
}
