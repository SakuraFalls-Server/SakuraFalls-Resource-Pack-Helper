package net.sakurafalls.resource_pack_helper.processor.directory;

import net.sakurafalls.resource_pack_helper.config.Config;
import net.sakurafalls.resource_pack_helper.processor.FileProcessor;
import net.sakurafalls.resource_pack_helper.processor.structure.FurnitureModelStructureProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FurnitureDirectoryProcessor extends FileProcessor {

    public FurnitureDirectoryProcessor() throws IOException {
        super(Config.getInputDirectoryPath() + File.separator + "furniture");
    }

    @Override
    public void process() throws IOException {
        for (File child : Objects.requireNonNull(inputFile.listFiles())) {
            if (!child.isDirectory()) {
                continue;
            }
            FurnitureModelStructureProcessor furnitureProcessor =
                new FurnitureModelStructureProcessor(child.getAbsolutePath());
            furnitureProcessor.process();
        }
    }

}
