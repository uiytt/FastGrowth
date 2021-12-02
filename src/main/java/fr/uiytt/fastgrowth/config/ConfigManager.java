package fr.uiytt.fastgrowth.config;

import de.leonhard.storage.Json;
import fr.uiytt.fastgrowth.FastGrowth;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;

import java.nio.file.Path;

public class ConfigManager {


    private int chanceOfFertilizing;
    private int particleCount;
    private Path path;
    private Json json;

    public ConfigManager(Path path) {
        this.path = path;
        json = new Json("FastGrowth",path.toAbsolutePath().toString());
    }

    public void load() {
        chanceOfFertilizing = Math.max(0,Math.min(100,json.getOrSetDefault("chanceOfFertilizing",33)));
        particleCount = json.getOrSetDefault("particleCount",10);
    }



    public void saveConfig() {
        json.set("chanceOfFertilizing",chanceOfFertilizing);
        json.set("particleCount",particleCount);
    }

    public int getChanceOfFertilizing() {
        return chanceOfFertilizing;
    }

    public void setChanceOfFertilizing(int chanceOfFertilizing) {
        this.chanceOfFertilizing = chanceOfFertilizing;
    }

    public int getParticleCount() {
        return particleCount;
    }

    public void setParticleCount(int particleCount) {
        this.particleCount = particleCount;
    }
}
