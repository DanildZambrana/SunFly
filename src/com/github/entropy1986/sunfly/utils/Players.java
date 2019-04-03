package com.github.entropy1986.sunfly.utils;

import com.github.entropy1986.sunfly.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class Players {
  private File file = null;
  private FileConfiguration fileC = null;

  private Main main;

  public Players(Main main){
    this.main = main;
  }

  public FileConfiguration getFile(){
    if(fileC == null){
      reloadFile();
    }
    return fileC;
  }

  public void reloadFile() {
    if(fileC == null) {
      file = new File(main.getDataFolder(),"Players.yml");
    }
    fileC = YamlConfiguration.loadConfiguration(file);
    Reader defConfigStream;
    try {
      defConfigStream = new InputStreamReader(main.getResource("Players.yml"),"UTF8");
      if(defConfigStream != null) {
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        fileC.setDefaults(defConfig);

      }
    }catch(UnsupportedEncodingException e){
      e.printStackTrace();
    }
  }

  public void saveFile() {
    try {
      fileC.save(file);
    }catch(IOException e){
      e.printStackTrace();
    }
  }


  public void registerFile() {
    file = new File(main.getDataFolder(),"Players.yml");
    if(!file.exists()) {
      this.getFile().options().copyDefaults(true);
      Bukkit.getConsoleSender().sendMessage(Color.toColor("&cPlayers.yml &7is not founded, creating archive..."));
      saveFile();
    }
  }
}