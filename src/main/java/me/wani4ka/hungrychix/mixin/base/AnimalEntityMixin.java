package me.wani4ka.hungrychix.mixin.base;

import me.wani4ka.hungrychix.entity.ai.goal.EatFavoriteFoodGoal;
import me.wani4ka.hungrychix.entity.ai.goal.FollowFavoriteFoodGoal;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntityMixin {
    @Unique
    protected void addFoodTargets(int basePriority, double speed) {
        var animal = (AnimalEntity)(Object)this;
        this.goalSelector.add(basePriority-1, new EatFavoriteFoodGoal(animal));
        this.goalSelector.add(basePriority, new FollowFavoriteFoodGoal(animal, speed));
    }
}
