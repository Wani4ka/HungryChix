package me.wani4ka.hungrychix.mixin.base;

import net.minecraft.entity.passive.PassiveEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PassiveEntity.class)
public abstract class PassiveEntityMixin extends PathAwareEntityMixin {
}
