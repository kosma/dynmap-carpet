package pl.kosma.dynmapcarpet.mixin;

import carpet.patches.EntityPlayerMPFake;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.dynmap.fabric_1_19_1.FabricPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FabricPlayer.class)
public class FabricPlayerMixin {
    @Redirect(method = "getDisplayName", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getDisplayName()Lnet/minecraft/text/Text;"))
    Text getDisplayName(ServerPlayerEntity player) {
        String name = player.getDisplayName().getString();
        if (player instanceof EntityPlayerMPFake)
            name += " [bot]";
        if (player.getScoreboardTeam() != null)
            name += " [afk]";
        return Text.of(name);
    }
}
