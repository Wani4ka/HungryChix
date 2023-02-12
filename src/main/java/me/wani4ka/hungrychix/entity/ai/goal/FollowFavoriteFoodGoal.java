package me.wani4ka.hungrychix.entity.ai.goal;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FollowFavoriteFoodGoal extends FavoriteItemLookupGoal {
    private final double speed;
    @Nullable
    protected ItemEntity closestItem;
    private int cooldown;

    public FollowFavoriteFoodGoal(AnimalEntity animal, double speed) {
        super(animal);
        this.speed = speed;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (cooldown > 0) {
            --cooldown;
            return false;
        }
        if (!(animal.getBreedingAge() == 0 && animal.canEat()))
            return false;
        closestItem = this.getClosestFavoriteItem(20, 20, 20);
        return closestItem != null && animal.squaredDistanceTo(closestItem) >= 1;
    }

    @Override
    public void stop() {
        closestItem = null;
        animal.getNavigation().stop();
        cooldown = toGoalTicks(100);
    }

    @Override
    public void tick() {
        animal.getLookControl().lookAt(closestItem, animal.getMaxHeadRotation() + 20, animal.getMaxLookPitchChange());
        animal.getNavigation().startMovingTo(closestItem, speed);
    }
}
