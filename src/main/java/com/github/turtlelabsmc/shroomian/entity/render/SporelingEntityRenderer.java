package com.github.turtlelabsmc.shroomian.entity.render;

import com.github.turtlelabsmc.shroomian.Shroomian;
import com.github.turtlelabsmc.shroomian.entity.SporelingEntity;
import com.github.turtlelabsmc.shroomian.entity.model.SporelingEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SporelingEntityRenderer extends MobEntityRenderer<SporelingEntity, SporelingEntityModel>
{
    public SporelingEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new SporelingEntityModel(), 0.5F);
    }

    @Override
    public Identifier getTexture(SporelingEntity entity)
    {
        return Shroomian.modId("textures/entity/sporeling/" + entity.getVariant().getName() + ".png");
    }
}
