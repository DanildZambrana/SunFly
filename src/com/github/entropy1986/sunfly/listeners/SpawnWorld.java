package com.github.entropy1986.sunfly.listeners;

import com.github.entropy1986.sunfly.Main;
import com.github.entropy1986.sunfly.utils.ItsFly;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class SpawnWorld implements Listener {

  private Main main;

  public SpawnWorld(Main main){
    this.main = main;
  }


  @EventHandler
  public void onSpawn(PlayerChangedWorldEvent event){
    Player p = event.getPlayer();
    Location loc = p.getLocation();
    String world = loc.getWorld().getName();

    FileConfiguration config = main.getConfig();

    List<String> worlds = config.getStringList("Worlds");

    if(!(worlds.contains(world))){
      if (ItsFly.active(main, p)) {
        p.setAllowFlight(false);
        p.setFlying(false);
        ItsFly.remove(main, p);
      }
    }else if(worlds.contains(world)){
      if (p.isOp() || p.hasPermission("*") || p.hasPermission("fly.use")) {
        p.setAllowFlight(true);
        p.setFlying(true);
        ItsFly.add(main, p);
      }
    }
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event){
    Player p = event.getPlayer();
    Location loc = p.getLocation();
    String world = loc.getWorld().getName();

    FileConfiguration config = main.getConfig();

    List<String> worlds = config.getStringList("Worlds");


    if(!(worlds.contains(world))){
      if (ItsFly.active(main, p)) {
        p.setAllowFlight(false);
        p.setFlying(false);
        ItsFly.remove(main, p);
      }
    }else if(worlds.contains(world)){
      if (p.isOp() || p.hasPermission("*") || p.hasPermission("fly.use")) {
        p.setAllowFlight(true);
        p.setFlying(true);
        ItsFly.add(main, p);
      }
    }
  }
}
