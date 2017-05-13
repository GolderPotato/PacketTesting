package fr.golder.packets;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;
 
public class PacketReader {
 
        Player player;
        Channel channel;
 
        public PacketReader(Player player) {
                this.player = player;
        }
 
 
        public void inject() {
              
                CraftPlayer player = (CraftPlayer) this.player;
                
                channel = player.getHandle().playerConnection.networkManager.channel;
              
                channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {
                                        @Override
                                        protected void decode(ChannelHandlerContext arg0, Packet<?> packet, List<Object> arg2) throws Exception {
                                                arg2.add(packet);
                                                readPackets(packet);
                                        }
                                });
        }
 
       
        public void uninject() {
                
                if (channel.pipeline().get("PacketInjector") != null) {
                        
                        channel.pipeline().remove("PacketInjector");
                }
        }
        
        public void readPackets(Packet<?> packet) {
        	if(packet == null)return;
        	System.out.println(packet.getClass().getSimpleName());
        }
        
        public void sendPacket(Packet<?> packet){
        	PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
        	pc.sendPacket(packet);
        }
        
        public int getInt(Packet<?> packet, String field){
        	Integer i = null;
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				i = f.getInt(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
        }
        
        public void setInt(Packet<?> packet, String field, int i){
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				f.setInt(packet, i);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        public boolean getBoolean(Packet<?> packet, String field){
        	boolean i = false;
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				i = f.getBoolean(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
        }
        
        public void setBoolean(Packet<?> packet, String field, boolean bool){
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				f.setBoolean(packet, bool);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        public float getFloat(Packet<?> packet, String field){
        	Float i = null;
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				i = f.getFloat(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
        }
        
        public void setFloat(Packet<?> packet, String field, float fl){
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				f.setFloat(packet, fl);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        public Object getObject(Packet<?> packet, String field){
        	Object i = null;
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				i = f.get(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
        }
        
        public void getObject(Packet<?> packet, String field, Object obj){
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				f.set(packet, obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        public double getDouble(Packet<?> packet, String field){
        	double i = 0;
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				i = f.getDouble(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
        }
        
        public void setDouble(Packet<?> packet, String field, double db){
        	try {
				Field f = packet.getClass().getDeclaredField(field);
				f.setAccessible(true);
				f.setDouble(packet, db);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
 
}