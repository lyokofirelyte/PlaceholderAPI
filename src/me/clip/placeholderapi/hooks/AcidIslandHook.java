package me.clip.placeholderapi.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.wasteofplastic.acidisland.ASkyBlock;
import com.wasteofplastic.acidisland.ASkyBlockAPI;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.PlaceholderHook;

public class AcidIslandHook {
	
	private PlaceholderAPIPlugin plugin;
	
	public AcidIslandHook(PlaceholderAPIPlugin i) {
		plugin = i;
	}

	public void hook() {
		
		if (Bukkit.getPluginManager().isPluginEnabled("AcidIsland")) {
				
			ASkyBlock as = (ASkyBlock ) Bukkit.getPluginManager().getPlugin("AcidIsland");
			
			if (as != null) {
				
				boolean hooked = PlaceholderAPI.registerPlaceholderHook("AcidIsland", 
						new PlaceholderHook() {

					@Override
					public String onPlaceholderRequest(Player p, String identifier) {
						
						if (ASkyBlockAPI.getInstance() == null) {
							return null;
						}
						
						if (p == null) {
							return "";
						}
						
						switch (identifier) {
						
						case "level":
							return String.valueOf(ASkyBlockAPI.getInstance().getIslandLevel(p.getUniqueId()));
						case "island_x":
							return String.valueOf(ASkyBlockAPI.getInstance().getIslandLocation(p.getUniqueId()).getBlockX());
						case "island_y":
							return String.valueOf(ASkyBlockAPI.getInstance().getIslandLocation(p.getUniqueId()).getBlockY());
						case "island_z":
							return String.valueOf(ASkyBlockAPI.getInstance().getIslandLocation(p.getUniqueId()).getBlockZ());
						case "island_world":
							return ASkyBlockAPI.getInstance().getIslandLocation(p.getUniqueId()).getWorld().getName();
						case "team_size":
							return ASkyBlockAPI.getInstance().getTeamMembers(p.getUniqueId()) != null ? 
									String.valueOf(ASkyBlockAPI.getInstance().getTeamMembers(p.getUniqueId()).size()) : "0";
						case "coop_islands":
							return ASkyBlockAPI.getInstance().getCoopIslands(p) != null ? 
									String.valueOf(ASkyBlockAPI.getInstance().getCoopIslands(p).size()) : "0";
						}
						return null;
					}
				}, true);
				
				if (hooked) {
					plugin.log.info("Hooked into AcidIsland for placeholders!");
				}
			}	
		}
	}
}
