package net.sakurafalls.resource_pack_helper.processor.item;

import net.sakurafalls.resource_pack_helper.config.Config;
import net.sakurafalls.resource_pack_helper.json.model.JsonItemModel;
import net.sakurafalls.resource_pack_helper.processor.FileProcessor;

import java.io.File;
import java.io.IOException;

public class FurnitureModelProcessor extends ModelProcessor {

    private final String name;
    private final int customModelData;

    public FurnitureModelProcessor(String path, String material, String name,
                                   int customModelData, File[] textures, String author) throws IOException {
        super(path, material, textures, author);
        this.name = name;
        this.customModelData = customModelData;
    }

    @Override
    public void process() throws IOException {
        int incrementingCustomModelData = customModelData;
        for (File texture : textures) {
            String itemName = name + "_" + texture.getName().replace(".png", "");
            String itemPath = Config.getResourcePackDirectoryPath() + File.separator +
                "assets" + File.separator + "minecraft" + File.separator +
                "models" + File.separator + "custom" + File.separator +
                material + File.separator + itemName + ".json";
            File copy = FileProcessor.duplicateFile(inputFile.getAbsolutePath(), itemPath);
            processModel(copy, texture);

            processVanillaItem(customModelData == -1 ? -1 : incrementingCustomModelData++, itemName);
            if (Config.shouldConsiderPre1_21()) {
                processVanillaItemPre1_21(customModelData == -1 ? -1 : incrementingCustomModelData, itemName);
            }
        }
    }

    private void processModel(File file, File texture) throws IOException {
        JsonItemModel model = new JsonItemModel(file);
        model.setCreditsMessage("Made by " + author);
        String textureName = name + "_" + texture.getName().replaceAll(".png", "") + "_atlas";
        String texturePath = Config.getResourcePackDirectoryPath() + File.separator +
            "assets" + File.separator + "minecraft" + File.separator +
            "textures" + File.separator + "custom" + File.separator +
            material + File.separator + textureName + ".png";
        FileProcessor.duplicateFile(texture.getAbsolutePath(), texturePath);
        model.setTexturePath(0, "custom/" + material + "/" + textureName);
        model.setParticlePath("custom/" + material + "/" + textureName);
        model.write();
    }

}
