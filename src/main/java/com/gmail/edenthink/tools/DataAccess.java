package com.gmail.edenthink.tools;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

/**
 * Created by Eden on 11/23/2015.
 */
public class DataAccess {
    private FileConfiguration data = null;
    private File dataFile = null;
    private final FactoryLife plugin;

    private String fileName;
    private String parent;

    public DataAccess(FactoryLife plugin, String parent, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.parent = parent;

    }

    public void reloadData() {
        if (dataFile == null) {
            dataFile = new File(parent, fileName);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(plugin.getResource("customConfig.yml"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            FactoryLife.getLog().info("UTF8 is bad!");
        } catch (NullPointerException e) {
            FactoryLife.getLog().info("No default");
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            data.setDefaults(defConfig);
        }
    }

    public FileConfiguration getData() {
        if (data == null) {
            reloadData();
        }
        return data;
    }

    public void saveData() {
        if (data == null || dataFile == null) {
            return;
        }
        try {
            getData().save(dataFile);
        } catch (IOException e) {
            FactoryLife.getLog().log(Level.SEVERE, "Could not save config to " + dataFile, e);
        }
    }
}