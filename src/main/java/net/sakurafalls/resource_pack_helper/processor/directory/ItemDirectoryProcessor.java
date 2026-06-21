package net.sakurafalls.resource_pack_helper.processor.directory;

import net.sakurafalls.resource_pack_helper.config.Config;
import net.sakurafalls.resource_pack_helper.processor.FileProcessor;
import net.sakurafalls.resource_pack_helper.processor.structure.ItemModelStructureProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ItemDirectoryProcessor extends FileProcessor {

    public ItemDirectoryProcessor() throws IOException {
        super(Config.getInputDirectoryPath() + File.separator + "items");
    }

    @Override
    public void process() throws IOException {
        for (File child : Objects.requireNonNull(inputFile.listFiles())) {
            if (!child.isDirectory()) {
                continue;
            }
            ItemModelStructureProcessor itemProcessor = new ItemModelStructureProcessor(child.getAbsolutePath());
            itemProcessor.process();
        }
    }

}
