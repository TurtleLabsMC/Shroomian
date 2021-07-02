package com.github.turtlelabsmc.reverie.client;


import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.entity.model.ShroomianEntityModel;
import com.github.turtlelabsmc.reverie.entity.render.ShroomianEntityRenderer;
import com.github.turtlelabsmc.reverie.registry.EntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.lwjgl.system.CallbackI;

@Environment(EnvType.CLIENT)
public class EntityModels {

    public static final EntityModelLayer SHROOMIAN = new EntityModelLayer(new Identifier("reverie", "shroomian"), "main");

    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(SHROOMIAN, () -> ShroomianEntityModel.createModelData());
        registerEntityRenders();
    }

    private static void registerEntityRenders() {
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.SHROOMIAN, ShroomianEntityRenderer::new);
    }
}
