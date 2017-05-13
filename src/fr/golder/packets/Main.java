package fr.golder.packets;

import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.sendMessage("Hey");
		TestPacket pr = new TestPacket(p);
		PacketPlayOutBlockChange ppobc = new PacketPlayOutBlockChange();
		pr.getObject(ppobc, "a");
		pr.inject();
		
	}
	
	
	public static Main getInstance() {
		return instance;
	}
}
