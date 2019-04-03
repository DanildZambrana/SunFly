package com.github.entropy1986.sunfly;

import com.github.entropy1986.sunfly.commands.Fly;
import com.github.entropy1986.sunfly.commands.FlyReload;
import com.github.entropy1986.sunfly.listeners.SpawnWorld;
import com.github.entropy1986.sunfly.utils.Color;
import com.github.entropy1986.sunfly.utils.Languages;
import com.github.entropy1986.sunfly.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
  private PluginDescriptionFile pdFile = getDescription();
  String name = "&8[&b"+pdFile.getName()+"&8]";
  String version = pdFile.getVersion();

  @Override
  public void onLoad() {
    Color.toConsole( name +"&7Cargando...");
    registerConfig();
    registerFiles();
  }

  @Override
  public void onEnable() {
    registerEvents();
    registerCommands();
    Color.toConsole(name +"&aHabilitado correctamente. &cVersion: " + version);
    Color.toConsole("&f~Entropy1986");
  }

  @Override
  public void onDisable() {
    Color.toConsole(name +"&aDeshabilitado correctamente. &cVersion: " + version);
    Color.toConsole("&f~Entropy1986");
  }

  public void registerFiles(){
    Languages lang = new Languages(this);
    lang.registerFile();

    Players pFile = new Players(this);
    pFile.registerFile();
  }

  public void registerConfig(){
    File config = new File(this.getDataFolder(), ("config.yml"));
    if(!config.exists()){
      this.getConfig().options().copyDefaults(true);
      Bukkit.getConsoleSender().sendMessage(Color.toColor("&cconfig.yml &7is not found, creating file..."));
      saveConfig();
    }
  }
  public void registerCommands(){
    this.getCommand("fly").setExecutor(new Fly(this));
    this.getCommand("flyreload").setExecutor(new FlyReload(this));
  }

  public void registerEvents(){
    PluginManager e = Bukkit.getServer().getPluginManager();
    e.registerEvents(new SpawnWorld(this), this);
  }

}
