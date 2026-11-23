package com.sanny64.snowyrivers.mixin;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
    @Inject(
        method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void snowyrivers$noRiverIce(LevelReader level, BlockPos pos, boolean mustBeAtEdge,
                                    CallbackInfoReturnable<Boolean> cir) {
        if (level.getBiome(Objects.requireNonNull(pos)).is(BiomeTags.IS_RIVER)) {
            cir.setReturnValue(false);
        }                      
        if (level.getBiome(pos).is(BiomeTags.IS_RIVER)) {
            cir.setReturnValue(false);
        }
    }
}
