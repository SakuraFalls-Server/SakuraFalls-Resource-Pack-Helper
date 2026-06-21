package net.sakurafalls.resource_pack_helper.processor.structure;

import net.sakurafalls.resource_pack_helper.processor.item.FurnitureModelProcessor;
import net.sakurafalls.resource_pack_helper.processor.item.ItemModelProcessor;

import java.io.File;
import java.io.IOException;

public class FurnitureModelStructureProcessor extends ModelStructureProcessor {

    public FurnitureModelStructureProcessor(String path) throws IOException {
        super(path);
    }

    @Override
    public void delegateToModelProcessor(String absolutePath, String material, String modelName,
                                         int i, File[] textureFiles, String authorName) throws IOException {
        FurnitureModelProcessor furnitureModelProcessor = new FurnitureModelProcessor(
            absolutePath, material, modelName,
            -1, textureFiles, authorName
        );
        furnitureModelProcessor.process();
    }
}
