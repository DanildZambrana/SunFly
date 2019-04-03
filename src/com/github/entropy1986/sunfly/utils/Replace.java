package com.github.entropy1986.sunfly.utils;

import com.github.entropy1986.sunfly.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Replace {
  private Main main;
  public Replace(Main main){
    this.main = main;
  }

  public String all(String text, Player player, Player target){
    Languages langF = new Languages(main);
    FileConfiguration lang = langF.getFile();
    String prefix = lang.getString("Prefix");
    text.replace("%prefix%", prefix);
    if(player !=null) {
      text = text.replace("%player%", player.getName());
      if(target != null){
        text = text.replace("%target%", target.getName());
      }
    }if(target != null){
      text = text.replace("%target%", target.getName());
    }
    return text;
  }
}