package me.boykev.totem;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
		if(p.getName().equalsIgnoreCase("MyrAdonis")) {
			double health = p.getHealth();
			if(health - e.getDamage() < 1) {
				e.setCancelled(true);
				p.playEffect(EntityEffect.TOTEM_RESURRECT);
				p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 180, 1));
				p.setHealth(p.getMaxHealth());
			}
		}
	}

}
