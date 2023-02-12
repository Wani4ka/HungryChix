package me.wani4ka.hungrychix.mixin.base;

import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PathAwareEntity.class)
public abstract class PathAwareEntityMixin extends MobEntityMixin {
}
