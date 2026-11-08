package com.example.nofarlandsportal.mixin;

import net.minecraft.block.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Blocks portal use when the portal is in the Far Lands.
 * Uses Math.abs so both +X/+Z and -X/-Z Far Lands are covered.
 * Nether coordinates are scaled x8 so the check matches the linked Overworld position.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {

    private static final double FARLANDS = 12550821.0;

    @Inject(method = "tryUsePortal", at = @At("HEAD"), cancellable = true)
    private void nofarlandsportal$block(Portal portal, BlockPos pos, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        // If this line fails to compile, change getEntityWorld() to getWorld()
        World world = self.getEntityWorld();

        double scale = world.getDimension().coordinateScale(); // 1.0 overworld, 8.0 nether
        double x = Math.abs((double) pos.getX()) * scale;
        double z = Math.abs((double) pos.getZ()) * scale;

        if (x > FARLANDS || z > FARLANDS) {
            ci.cancel();
        }
    }
}
