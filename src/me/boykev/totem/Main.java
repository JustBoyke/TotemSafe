package me.boykev.totem;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	public boolean checkBeacon(String name) {
		if(name.equalsIgnoreCase("Saampje")) {
			World wr = Bukkit.getWorld("templeberg");
			Location beacon = new Location(wr, -124, 175, 23);
			if(beacon.getBlock().getType() == Material.BEACON) {
				return true;
			}
			return false;
		}
		if(name.equalsIgnoreCase("MyrAdonis")) {
			World wr = Bukkit.getWorld("templeberg");
			Location beacon = new Location(wr, -118, 90, 162);
			if(beacon.getBlock().getType() == Material.BEACON) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public Location beaconTp(String name) {
		if(name.equalsIgnoreCase("Saampje")) {
			World wr = Bukkit.getWorld("templeberg");
			Location beacon = new Location(wr, -124, 180, 23);
			return beacon;
		}
		if(name.equalsIgnoreCase("MyrAdonis")) {
			World wr = Bukkit.getWorld("templeberg");
			Location beacon = new Location(wr, -120, 93, 162);
			return beacon;
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBoykeDeath(EntityDamageEvent e) {
		if(!(e.getEntityType() == EntityType.PLAYER)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if(p.getName().equalsIgnoreCase("MyrAdonis") || p.getName().equalsIgnoreCase("Saampje")) {
			double health = p.getHealth();
			if(health - e.getDamage() < 1) {
				
				if(this.checkBeacon(p.getName().toString()) == false) {
					p.sendMessage(ChatColor.RED + "Je beacon is gebroken, je bent officieel gestorven.");
					return;
				}
				if(p.hasPotionEffect(PotionEffectType.WEAKNESS)) {
					if(this.checkBeacon(p.getName().toString()) == false) {
						p.sendMessage(ChatColor.RED + "Je beacon is gebroken, je bent officieel gestorven.");
						return;
					}
					Location loc = this.beaconTp(p.getName().toString());
					p.removePotionEffect(PotionEffectType.WEAKNESS);
					p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
					p.teleport(loc);
				}
				
				e.setCancelled(true);
				p.playEffect(EntityEffect.TOTEM_RESURRECT);
				p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 180, 1));
				p.setHealth(p.getMaxHealth());
			}
		}
	}

}
