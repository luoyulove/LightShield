//package luoyu.lightshield.SyncShield;
//
//import commonnetwork.Constants;
//import luoyu.lightshield.PlayerShield;
//import net.minecraft.client.Minecraft;
//import net.minecraft.network.FriendlyByteBuf;
//import commonnetwork.networking.data.PacketContext;
//import commonnetwork.networking.data.Side;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Player;
//
//import java.util.UUID;
//
//public class ShieldPacket {
//    public static final ResourceLocation CHANNEL = new ResourceLocation(Constants.MOD_ID, "shield_channel");
//    private float shieldAmount;
//    private UUID player_uuid;
//
//    public ShieldPacket(float shieldAmount, UUID player_uuid){
//        this.shieldAmount = shieldAmount;
//        this.player_uuid = player_uuid;
//    }
//    public static ShieldPacket fromBytes(FriendlyByteBuf buf){
//        return new ShieldPacket(buf.readFloat(), buf.readUUID());
//    }
//    public static void shieldAmountencode(ShieldPacket packet, FriendlyByteBuf buf){
//        buf.writeFloat(packet.shieldAmount);
//        buf.writeUUID(packet.player_uuid);
//    }
//    public void handle(PacketContext<ShieldPacket> shieldPacket){
//        if(shieldPacket.side() == Side.SERVER){
//            float shieldAmount = shieldPacket.message().shieldAmount;
//            Player player = Minecraft.getInstance().player;
//            PlayerShield.getPlayerShield(player).setShieldAmount(shieldAmount);
//        }
//    }
//}
