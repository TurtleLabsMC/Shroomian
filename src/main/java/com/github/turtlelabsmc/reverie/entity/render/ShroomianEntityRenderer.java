package com.github.turtlelabsmc.reverie.entity.render;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.client.EntityModels;
import com.github.turtlelabsmc.reverie.entity.ShroomianEntity;
import com.github.turtlelabsmc.reverie.entity.model.ShroomianEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ShroomianEntityRenderer extends MobEntityRenderer<ShroomianEntity, ShroomianEntityModel>
{
    public ShroomianEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new ShroomianEntityModel(), 0.5F);
    }

    @Override
    public Identifier getTexture(ShroomianEntity entity)
    {
        return Reverie.modId("textures/entity/shroomian/" + entity.getVariant().getName() + ".png");
    }
}
