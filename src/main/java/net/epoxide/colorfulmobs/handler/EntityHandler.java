package net.epoxide.colorfulmobs.handler;

import net.darkhax.bookshelf.helper.ItemHelper;
import net.darkhax.bookshelf.objects.ColorObject;
import net.epoxide.colorfulmobs.ColorfulMobs;
import net.epoxide.colorfulmobs.common.ColorProperties;
import net.epoxide.colorfulmobs.common.PacketColorSync;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityHandler {

    @SubscribeEvent
    public void onEntityConstructed(EntityEvent.EntityConstructing event) {

        if (event.entity instanceof EntityLiving && (!(event.entity instanceof EntityPlayer) || ConfigurationHandler.dyePlayer))
            ColorProperties.setPropsToEntity((EntityLivingBase) event.entity);
    }

    @SubscribeEvent
    public void onEntityTracked(PlayerEvent.StartTracking event) {

        if (event.target instanceof EntityLiving && (!(event.target instanceof EntityPlayer) || ConfigurationHandler.dyePlayer) && !event.target.worldObj.isRemote) {

            ColorfulMobs.network.sendToAll(new PacketColorSync(ColorProperties.getPropsFromEntity((EntityLivingBase) event.target).colorObj, (EntityLivingBase) event.target));
        }
    }

    @SubscribeEvent
    public void onMobDeath(LivingDropsEvent event) {

        if (ColorProperties.hasColorProperties(event.entityLiving) && !ColorObject.isGeneric(ColorProperties.getPropsFromEntity(event.entityLiving).colorObj)) {

            ItemStack stack = new ItemStack(ColorfulMobs.itemPowder);
            stack.setTagCompound(ColorObject.getTagFromColor(ColorProperties.getPropsFromEntity(event.entityLiving).colorObj));
            ItemHelper.dropStackInWorld(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, stack, false);
        }
    }
}