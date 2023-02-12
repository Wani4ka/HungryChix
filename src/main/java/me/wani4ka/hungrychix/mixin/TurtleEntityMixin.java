package me.wani4ka.hungrychix.mixin;

import me.wani4ka.hungrychix.mixin.base.AnimalEntityMixin;
import net.minecraft.entity.passive.TurtleEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TurtleEntity.class)
public class TurtleEntityMixin extends AnimalEntityMixin {
    @Inject(at = @At("TAIL"), method = "initGoals")
    public void addFollowFavoriteFood(CallbackInfo ci) {
        addFoodTargets(2, 1.1);
    }
}
