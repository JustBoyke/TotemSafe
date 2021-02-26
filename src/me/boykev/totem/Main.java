package me.boykev.totem;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onBoykeDeath(EntityDamageEvent e) {
		if(!(e.getEntityType() == EntityType.PLAYER)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if(p.getName().equalsIgnoreCase("boykev")) {
			double health = p.getHealth();
			if(health < 5) {
				p.playEffect(EntityEffect.TOTEM_RESURRECT);
				p.setHealth(20);
				p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(!(e.getEntityType() == EntityType.PLAYER)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if(p.getName().equalsIgnoreCase("boykev")) {
			p.playEffect(EntityEffect.TOTEM_RESURRECT);
			p.setHealth(20);
			p.getWorld().playEffect(p.getLocation(), Effect.DRAGON_BREATH, 10);
		}
	}

}
