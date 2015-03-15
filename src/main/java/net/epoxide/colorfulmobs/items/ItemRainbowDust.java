package net.epoxide.colorfulmobs.items;

import net.epoxide.colorfulmobs.handler.AchievementHandler;
import net.epoxide.colorfulmobs.lib.ColorObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemRainbowDust extends ItemColorSetter {

    public ItemRainbowDust() {

        this.hasSubtypes = true;
        this.setTextureName("colorfulmobs:rainbowdust");
        this.setUnlocalizedName("colorfulmobs.rainbowdust");
    }

    @Override
    public ColorObject getColorToApply(ItemStack stack) {

        return new ColorObject(false);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {

        super.itemInteractionForEntity(stack, player, entity);
        player.triggerAchievement(AchievementHandler.achRainbow);
        return true;
    }
}