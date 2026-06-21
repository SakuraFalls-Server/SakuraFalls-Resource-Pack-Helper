package net.sakurafalls.resource_pack_helper.processor.item;

import net.sakurafalls.resource_pack_helper.config.Config;
import net.sakurafalls.resource_pack_helper.json.vanilla.JsonVanillaItem;
import net.sakurafalls.resource_pack_helper.json.vanilla.JsonVanillaItemPre1_21;
import net.sakurafalls.resource_pack_helper.processor.FileProcessor;

import java.io.File;
import java.io.IOException;

public abstract class ModelProcessor extends FileProcessor {

    protected final String material;
    protected final File[] textures;
    protected final String author;

    public ModelProcessor(String path, String material, File[] textures, String author) throws IOException {
        super(path);
        this.material = material;
        this.textures = textures;
        this.author = author;
    }

    protected void processVanillaItem(int customModelData, String name) throws IOException {
        String vanillaItemPath = Config.getResourcePackDirectoryPath() + File.separator +
            "assets" + File.separator + "minecraft" + File.separator +
            "items" + File.separator + material + ".json";
        JsonVanillaItem vanillaItem = new JsonVanillaItem(new File(vanillaItemPath));
        vanillaItem.setCustomModelDataItem(customModelData, "custom/" + material + "/" + name);
        vanillaItem.write();
    }

    protected void processVanillaItemPre1_21(int customModelData, String name) throws IOException {
        String vanillaItemPre1_21Path = Config.getResourcePackDirectoryPath() + File.separator +
            "assets" + File.separator + "minecraft" + File.separator +
            "models" + File.separator + "item" + File.separator + material + ".json";
        JsonVanillaItemPre1_21 vanillaItemPre1_21 = new JsonVanillaItemPre1_21(new File(vanillaItemPre1_21Path));

        vanillaItemPre1_21.setCustomModelDataItem(customModelData, "custom/" + material + "/" + name);
        vanillaItemPre1_21.write();
    }

}
