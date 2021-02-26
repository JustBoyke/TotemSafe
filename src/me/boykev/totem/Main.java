package me.boykev.totem;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBoykeDeath(EntityDamageEvent e) {
		if(!(e.getEntityType() == EntityType.PLAYER)) {
			return;
		}
		Player p = (Player) e.getEntity();
		ItemStack m = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
		if(p.getHealth() - e.getDamage() < 1) {
			if(p.getInventory().contains(m)) {
				e.setCancelled(true);
				p.playEffect(EntityEffect.TOTEM_RESURRECT);
				p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
				p.sendMessage(ChatColor.RED + "Een TOTEM heeft je leven gered!");
				p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 180, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 180, 1));
				p.setHealth(p.getMaxHealth());
				p.getInventory().removeItem(m);
				p.updateInventory();
				return;
			}
		}
		if(p.getName().equalsIgnoreCase("boykev")) {
			double health = p.getHealth();
			if(health - e.getDamage() < 1) {
				if(p.getInventory().contains(new ItemStack(Material.TOTEM_OF_UNDYING))) {
					e.setCancelled(true);
					p.playEffect(EntityEffect.TOTEM_RESURRECT);
					p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
					p.sendMessage(ChatColor.RED + "Een TOTEM heeft je leven gered!");
					p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 180, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 180, 1));
					p.setHealth(p.getMaxHealth());
					return;
				}
				e.setCancelled(true);
				p.playEffect(EntityEffect.TOTEM_RESURRECT);
				p.setHealth(p.getMaxHealth());
				p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
			}
		}
	}
	
//	@EventHandler
//	public void onDeath(EntityDamageByEntityEvent e) {
//		if(!(e.getEntityType() == EntityType.PLAYER)) {
//			return;
//		}
//		Player p = (Player) e.getEntity();
//		if(p.getName().equalsIgnoreCase("boykev")) {
//			if(p.getHealth() - e.getDamage() < 1) {
//				if(p.getInventory().contains(new ItemStack(Material.TOTEM_OF_UNDYING))) {
//					e.setCancelled(true);
//					p.playEffect(EntityEffect.TOTEM_RESURRECT);
//					p.setHealth(20);
//					p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
//					p.sendMessage(ChatColor.RED + "Een TOTEM heeft je leven gered!");
//					return;
//				}
//				e.setCancelled(true);
//				p.playEffect(EntityEffect.TOTEM_RESURRECT);
//				p.setHealth(20);
//				p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
//			}
//		}
//	}

}
