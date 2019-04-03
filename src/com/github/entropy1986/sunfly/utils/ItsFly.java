package com.github.entropy1986.sunfly.utils;

import com.github.entropy1986.sunfly.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ItsFly {

  public static void add(Main main, Player player){
    Languages langF = new Languages(main);
    FileConfiguration messages = langF.getFile();
    Players playersF = new Players(main);
    FileConfiguration players = playersF.getFile();

    UUID uuid = player.getUniqueId();

    if(players.contains(uuid+"")){
      if(players.getString(uuid+".Fly").equals("true")){
        return;
      }else if(players.getString(uuid+".Fly").equals("false")){
        players.set(uuid + ".Fly", "true");
        playersF.saveFile();
      }else{
        Color.toConsole(messages.getString("FileError").replace("%prefix%", langF.getFile().getString("Prefix")).replaceAll("%file%", "Players.yml"));
      }
    }else {
      players.set(uuid + ".Fly", "true");
      playersF.saveFile();
    }
  }
  public static void remove(Main main, Player player){
    Languages langF = new Languages(main);
    FileConfiguration messages = langF.getFile();
    Players playersF = new Players(main);
    FileConfiguration players = playersF.getFile();

    String uuid = player.getUniqueId().toString();

    if(players.contains(uuid)){
      if(players.getString(uuid+".Fly").equals("false")){
        return;
      }else if(players.getString(uuid+".Fly").equals("true")){
        players.set(uuid + ".Fly", "false");
        playersF.saveFile();
      }else{
        Color.toConsole(messages.getString("FileError").replace("%prefix%", langF.getFile().getString("Prefix")).replaceAll("%file%", "Players.yml"));
      }
    }else {
      players.set(uuid + ".Fly", "false");
      playersF.saveFile();
    }
  }
  public static boolean active(Main main, Player player){
    Languages langF = new Languages(main);
    FileConfiguration messages = langF.getFile();
    Players playersF = new Players(main);
    FileConfiguration players = playersF.getFile();

    String uuid = player.getUniqueId().toString();

    if(players.contains(uuid)) {
      if (players.getString(uuid + ".Fly").equals("true")) {
        return true;
      } else if (players.getString(uuid + ".Fly").equals("false")) {
        return false;
      } else {
        Color.toConsole(messages.getString("FileError").replace("%prefix%", langF.getFile().getString("Prefix")).replaceAll("%file%", "Players.yml"));
        return false;
      }
    }else {
      return false;
    }
  }
}
