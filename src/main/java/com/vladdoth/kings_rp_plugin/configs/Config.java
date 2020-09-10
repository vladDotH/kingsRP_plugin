package com.vladdoth.kings_rp_plugin.configs;

import com.vladdoth.kings_rp_plugin.Plugin;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Config {
    private static FileConfiguration cfg = Plugin.getInstance().getConfig();

    public static void save(File file) throws IOException {
        cfg.save(file);
    }

    public static void save(String file) throws IOException {
        cfg.save(file);
    }

    public static String saveToString() {
        return cfg.saveToString();
    }

    public static void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        cfg.load(file);
    }

    public static void load(Reader reader) throws IOException, InvalidConfigurationException {
        cfg.load(reader);
    }

    public static void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        cfg.load(file);
    }

    public static void loadFromString(String contents) throws InvalidConfigurationException {
        cfg.loadFromString(contents);
    }

    public static FileConfigurationOptions options() {
        return cfg.options();
    }

    public static void addDefault(String path, Object value) {
        cfg.addDefault(path, value);
    }

    public static void addDefaults(Map<String, Object> defaults) {
        cfg.addDefaults(defaults);
    }

    public static void addDefaults(Configuration defaults) {
        cfg.addDefaults(defaults);
    }

    public static void setDefaults(Configuration defaults) {
        cfg.setDefaults(defaults);
    }

    public static Configuration getDefaults() {
        return cfg.getDefaults();
    }

    public static ConfigurationSection getParent() {
        return cfg.getParent();
    }

    public static Set<String> getKeys(boolean deep) {
        return cfg.getKeys(deep);
    }

    public static Map<String, Object> getValues(boolean deep) {
        return cfg.getValues(deep);
    }

    public static boolean contains(String path) {
        return cfg.contains(path);
    }

    public static boolean contains(String path, boolean ignoreDefault) {
        return cfg.contains(path, ignoreDefault);
    }

    public static boolean isSet(String path) {
        return cfg.isSet(path);
    }

    public static String getCurrentPath() {
        return cfg.getCurrentPath();
    }

    public static String getName() {
        return cfg.getName();
    }

    public static Configuration getRoot() {
        return cfg.getRoot();
    }

    public static ConfigurationSection getDefaultSection() {
        return cfg.getDefaultSection();
    }

    public static void set(String path, Object value) {
        cfg.set(path, value);
    }

    public static Object get(String path) {
        return cfg.get(path);
    }

    public static Object get(String path, Object def) {
        return cfg.get(path, def);
    }

    public static ConfigurationSection createSection(String path) {
        return cfg.createSection(path);
    }

    public static ConfigurationSection createSection(String path, Map<?, ?> map) {
        return cfg.createSection(path, map);
    }

    public static String getString(String path) {
        return cfg.getString(path);
    }

    public static String getString(String path, String def) {
        return cfg.getString(path, def);
    }

    public static boolean isString(String path) {
        return cfg.isString(path);
    }

    public static int getInt(String path) {
        return cfg.getInt(path);
    }

    public static int getInt(String path, int def) {
        return cfg.getInt(path, def);
    }

    public static boolean isInt(String path) {
        return cfg.isInt(path);
    }

    public static boolean getBoolean(String path) {
        return cfg.getBoolean(path);
    }

    public static boolean getBoolean(String path, boolean def) {
        return cfg.getBoolean(path, def);
    }

    public static boolean isBoolean(String path) {
        return cfg.isBoolean(path);
    }

    public static double getDouble(String path) {
        return cfg.getDouble(path);
    }

    public static double getDouble(String path, double def) {
        return cfg.getDouble(path, def);
    }

    public static boolean isDouble(String path) {
        return cfg.isDouble(path);
    }

    public static long getLong(String path) {
        return cfg.getLong(path);
    }

    public static long getLong(String path, long def) {
        return cfg.getLong(path, def);
    }

    public static boolean isLong(String path) {
        return cfg.isLong(path);
    }

    public static List<?> getList(String path) {
        return cfg.getList(path);
    }

    public static List<?> getList(String path, List<?> def) {
        return cfg.getList(path, def);
    }

    public static boolean isList(String path) {
        return cfg.isList(path);
    }

    public static List<String> getStringList(String path) {
        return cfg.getStringList(path);
    }

    public static List<Integer> getIntegerList(String path) {
        return cfg.getIntegerList(path);
    }

    public static List<Boolean> getBooleanList(String path) {
        return cfg.getBooleanList(path);
    }

    public static List<Double> getDoubleList(String path) {
        return cfg.getDoubleList(path);
    }

    public static List<Float> getFloatList(String path) {
        return cfg.getFloatList(path);
    }

    public static List<Long> getLongList(String path) {
        return cfg.getLongList(path);
    }

    public static List<Byte> getByteList(String path) {
        return cfg.getByteList(path);
    }

    public static List<Character> getCharacterList(String path) {
        return cfg.getCharacterList(path);
    }

    public static List<Short> getShortList(String path) {
        return cfg.getShortList(path);
    }

    public static List<Map<?, ?>> getMapList(String path) {
        return cfg.getMapList(path);
    }

    public static <T extends ConfigurationSerializable> T getSerializable(String path, Class<T> clazz) {
        return cfg.getSerializable(path, clazz);
    }

    public static <T extends ConfigurationSerializable> T getSerializable(String path, Class<T> clazz, T def) {
        return cfg.getSerializable(path, clazz, def);
    }

    public static Vector getVector(String path) {
        return cfg.getVector(path);
    }

    public static Vector getVector(String path, Vector def) {
        return cfg.getVector(path, def);
    }

    public static boolean isVector(String path) {
        return cfg.isVector(path);
    }

    public static OfflinePlayer getOfflinePlayer(String path) {
        return cfg.getOfflinePlayer(path);
    }

    public static OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
        return cfg.getOfflinePlayer(path, def);
    }

    public static boolean isOfflinePlayer(String path) {
        return cfg.isOfflinePlayer(path);
    }

    public static ItemStack getItemStack(String path) {
        return cfg.getItemStack(path);
    }

    public static ItemStack getItemStack(String path, ItemStack def) {
        return cfg.getItemStack(path, def);
    }

    public static boolean isItemStack(String path) {
        return cfg.isItemStack(path);
    }

    public static Color getColor(String path) {
        return cfg.getColor(path);
    }

    public static Color getColor(String path, Color def) {
        return cfg.getColor(path, def);
    }

    public static boolean isColor(String path) {
        return cfg.isColor(path);
    }

    public static ConfigurationSection getConfigurationSection(String path) {
        return cfg.getConfigurationSection(path);
    }

    public static boolean isConfigurationSection(String path) {
        return cfg.isConfigurationSection(path);
    }

    public static String createPath(ConfigurationSection section, String key) {
        return MemorySection.createPath(section, key);
    }

    public static String createPath(ConfigurationSection section, String key, ConfigurationSection relativeTo) {
        return MemorySection.createPath(section, key, relativeTo);
    }
}
