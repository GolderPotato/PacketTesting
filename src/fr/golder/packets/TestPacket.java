package fr.golder.packets;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

public class TestPacket extends PacketReader{

	public TestPacket(Player player) {
		super(player);
	}
	
	
	@Override
	public void readPackets(Packet<?> packet) {
		if(packet == null)return;
		System.out.println("> "+packet.getClass().getSimpleName());
	}
	
}
