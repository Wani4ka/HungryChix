package me.wani4ka.hungrychix.entity.ai.goal;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.Box;

import java.util.function.Predicate;

public abstract class FavoriteItemLookupGoal extends Goal {
    protected final Predicate<ItemEntity> predicate;
    protected final AnimalEntity animal;

    public FavoriteItemLookupGoal(AnimalEntity animal) {
        this.animal = animal;
        this.predicate = this::doesLikeFood;
    }

    protected boolean doesLikeFood(ItemEntity entity) {
        return animal.isBreedingItem(entity.getStack());
    }

    protected ItemEntity getClosestFavoriteItem(double dx, double dy, double dz) {
        var pos = animal.getPos();
        var items = animal.world.getEntitiesByClass(ItemEntity.class, Box.of(pos, dx, dy, dz), predicate);
        double d = -1.0;
        ItemEntity result = null;
        for (var item : items) {
            if (!predicate.test(item)) continue;
            double e = item.squaredDistanceTo(animal);
            if (d != -1.0 && !(e < d)) continue;
            d = e;
            result = item;
        }
        return result;
    }
}
