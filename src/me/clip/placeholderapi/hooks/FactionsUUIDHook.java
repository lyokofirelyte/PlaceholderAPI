package me.clip.placeholderapi.hooks;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.PlaceholderHook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

public class FactionsUUIDHook{

	private PlaceholderAPIPlugin plugin;

	public FactionsUUIDHook(PlaceholderAPIPlugin i) {
		plugin = i;
	}
	
	public void hook() {

		if (Bukkit.getPluginManager().isPluginEnabled("Factions")) {

			Plugin factions = Bukkit.getPluginManager().getPlugin("Factions");

			String version = factions.getDescription().getVersion();

			if (version.startsWith("1.6.9.5")) {

				boolean hooked = PlaceholderAPI.registerPlaceholderHook(factions, new PlaceholderHook() {

					@Override
					public String onPlaceholderRequest(Player p, String identifier) {
						
						if (p == null) {
							return "";
						}
						
						switch (identifier) {
						
						case "faction":
							return getFaction(p);
						case "power":
							return getFPower(p);
						case "powermax":
							return getFPowerMax(p);
						case "factionpower":
							return getFacPower(p);
						case "factionpowermax":
							return getFacPowerMax(p);
						case "title":
							return getFactionTitle(p);
						case "role":
							return getFactionRole(p);
						case "claims":
							return getFactionClaims(p);
						case "onlinemembers":
							return getOnlineFactionMembers(p);
						case "allmembers":
							return getFactionMembers(p);
						case "chat_tag":
							return getChatTag(p);
						}
						return null;
					}
					
				}, true);
				
				if (hooked) {
					plugin.log.info("Hooked into Factions 1.6.9.5 by drtshock for placeholders!");
				}
				
			} else {
				plugin.log.info("Could not hook into Factions 1.6.9.5 by drtshock!");
			}
		}
	}

	private boolean hasFaction(Player p) {
		if (FPlayers.getInstance().getByPlayer(p) == null) {
			return false;
		}

		return FPlayers.getInstance().getByPlayer(p).hasFaction();
	}

	private String getFaction(Player p) {
		if (!hasFaction(p)) {
			return "";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getFaction().getTag());
	}

	private String getFactionTitle(Player p) {
		if (!hasFaction(p)) {
			return "";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getTitle());
	}

	private String getFactionRole(Player p) {
		if (!hasFaction(p)) {
			return "";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getRole().getPrefix());
	}

	private String getFactionClaims(Player p) {
		if (!hasFaction(p)) {
			return "0";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getFaction().getLandRounded());
	}

	private String getFactionMembers(Player p) {
		if (!hasFaction(p)) {
			return "0";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getFaction().getFPlayers().size());
	}

	private String getOnlineFactionMembers(Player p) {
		if (!hasFaction(p)) {
			return "0";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getFaction().getOnlinePlayers().size());
	}

	private String getFPower(Player p) {

		FPlayer fplayer = FPlayers.getInstance().getByPlayer(p);

		if (fplayer == null) {
			return "0";
		}

		return String.valueOf(fplayer.getPowerRounded());
	}

	private String getFPowerMax(Player p) {

		FPlayer fplayer = FPlayers.getInstance().getByPlayer(p);

		if (fplayer == null) {
			return "0";
		}

		return String.valueOf(fplayer.getPowerMaxRounded());
	}

	private String getFacPower(Player p) {
		if (!hasFaction(p)) {
			return "0";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getFaction().getPowerRounded());
	}

	private String getFacPowerMax(Player p) {
		if (!hasFaction(p)) {
			return "0";
		}

		return String.valueOf(FPlayers.getInstance().getByPlayer(p).getFaction().getPowerMaxRounded());
	}

	private String getChatTag(Player p) {

		FPlayer fplayer = FPlayers.getInstance().getByPlayer(p);

		if (fplayer == null) {
			return "";
		}

		return String.valueOf(fplayer.getChatTag());
		
		
	}
}
