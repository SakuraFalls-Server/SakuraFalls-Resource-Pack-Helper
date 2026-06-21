package net.sakurafalls.resource_pack_helper.processor.item;

import net.sakurafalls.resource_pack_helper.config.Config;
import net.sakurafalls.resource_pack_helper.json.model.JsonItemModel;
import net.sakurafalls.resource_pack_helper.processor.FileProcessor;

import java.io.File;
import java.io.IOException;

public class ItemModelProcessor extends ModelProcessor {

    private final String name;
    private final int customModelData;

    public ItemModelProcessor(String path, String material, String name,
                              int customModelData, File[] textures, String author) throws IOException {
        super(path, material, textures, author);
        this.name = name;
        this.customModelData = customModelData;
    }

    @Override
    public void process() throws IOException {
        String itemPath = Config.getResourcePackDirectoryPath() + File.separator +
            "assets" + File.separator + "minecraft" + File.separator +
            "models" + File.separator + "custom" + File.separator +
            material + File.separator + name + ".json";
        File copy = FileProcessor.duplicateFile(inputFile.getAbsolutePath(), itemPath);
        processModel(copy);

        processVanillaItem(customModelData, name);
        if (Config.shouldConsiderPre1_21()) {
            processVanillaItemPre1_21(customModelData, name);
        }
    }

    private void processModel(File file) throws IOException {
        JsonItemModel model = new JsonItemModel(file);
        model.setCreditsMessage("Made by " + author);
        for (int i = 0; i < textures.length; i++) {
            String texturePath = Config.getResourcePackDirectoryPath() + File.separator +
                "assets" + File.separator + "minecraft" + File.separator +
                "textures" + File.separator + "custom" + File.separator +
                material + File.separator + name + File.separator + textures[i].getName();
            FileProcessor.duplicateFile(textures[i].getAbsolutePath(), texturePath);
            model.setTexturePath(i, "custom/" + material + "/" + name + "/" +
                textures[i].getName().replace(".png", ""));
        }
        model.setParticlePath("custom/" + material + "/" + name + "/" +
            textures[0].getName().replace(".png", ""));
        model.write();
    }

}
