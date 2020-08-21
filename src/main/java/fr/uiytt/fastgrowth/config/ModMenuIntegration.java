package fr.uiytt.fastgrowth.config;

import fr.uiytt.fastgrowth.FastGrowth;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public String getModId() {
        return FastGrowth.MODID;
    }
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            return ClothConfigManager::build;
        } else {
            return ModMenuApi.super.getModConfigScreenFactory();
        }

    }
}
