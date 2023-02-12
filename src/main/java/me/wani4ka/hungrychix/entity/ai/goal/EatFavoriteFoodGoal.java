package me.wani4ka.hungrychix.entity.ai.goal;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import java.util.EnumSet;

public class EatFavoriteFoodGoal extends FavoriteItemLookupGoal {
    private final World world;
    private int timer;

    public EatFavoriteFoodGoal(AnimalEntity animal) {
        super(animal);
        this.world = animal.world;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    @Override
    public boolean canStart() {
        if (!(animal.getBreedingAge() == 0 && animal.canEat()))
            return false;
        var item = getClosestFavoriteItem(4, 4, 4);
        return item != null && item.squaredDistanceTo(animal) < 4;
    }

    @Override
    public void start() {
        timer = getTickCount(40);
        animal.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.timer = 0;
    }

    @Override
    public boolean shouldContinue() {
        return this.timer > 0;
    }

    @Override
    public void tick() {
        this.timer = Math.max(0, this.timer - 1);
        if (this.timer != this.getTickCount(4)) {
            return;
        }
        var item = getClosestFavoriteItem(4, 4, 4);
        if (item != null && item.squaredDistanceTo(animal) < 4 && animal.getBreedingAge() == 0 && animal.canEat()) {
            item.getStack().setCount(item.getStack().getCount() - 1);
            animal.setLoveTicks(600);
            world.sendEntityStatus(animal, EntityStatuses.ADD_BREEDING_PARTICLES);
        }
    }
}
