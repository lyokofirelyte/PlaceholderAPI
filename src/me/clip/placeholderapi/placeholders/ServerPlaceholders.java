package me.clip.placeholderapi.placeholders;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.PlaceholderHook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerPlaceholders {
	
	private PlaceholderAPIPlugin plugin;

	public ServerPlaceholders(PlaceholderAPIPlugin i) {
		plugin = i;
	}

	public void hook() {

		boolean hooked = PlaceholderAPI.registerPlaceholderHook("server",
				new PlaceholderHook() {

					@Override
					public String onPlaceholderRequest(Player p, String identifier) {

						switch(identifier) {
						
						case "online":
							return String.valueOf(Bukkit.getOnlinePlayers().size());
						case "unique_joins":
							return String.valueOf(Bukkit.getOfflinePlayers().length);
						
						}

						return null;
					}

				}, true);

		if (hooked) {
			plugin.log.info("Server placeholders registered!");
		}
	}
}
