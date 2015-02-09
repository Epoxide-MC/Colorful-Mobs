package net.darkhax.colourfulmobs.client;

import net.darkhax.colourfulmobs.common.ColorObject;
import net.darkhax.colourfulmobs.common.ColorProperties;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderLivingEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderingHandler {

    @SubscribeEvent
    public void onEntityRender(RenderLivingEvent.Pre event) {

        if (ColorProperties.hasColorProperties(event.entity)) {

            ColorObject obj = ColorProperties.getPropsFromEntity(event.entity).colorObj;
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(obj.red, obj.blue, obj.green, obj.alpha);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }
    }
}