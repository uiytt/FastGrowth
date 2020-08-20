package fr.uiytt.fastgrowth.config;

import fr.uiytt.fastgrowth.FastGrowth;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public String getModId() {
        return FastGrowth.MODID;
    }
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ClothConfigManager::build;

    }
}
