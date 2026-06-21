package net.sakurafalls.resource_pack_helper.processor.structure;

import net.sakurafalls.resource_pack_helper.processor.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class ModelStructureProcessor extends FileProcessor {

    public ModelStructureProcessor(String path) throws IOException {
        super(path);
    }

    public abstract void delegateToModelProcessor(String absolutePath, String material, String modelName,
                                                  int i, File[] textureFiles, String authorName) throws IOException;

    @Override
    public void process() throws IOException {
        String material = inputFile.getName();
        for (File child : Objects.requireNonNull(inputFile.listFiles())) {
            if (!child.isDirectory()) {
                continue;
            }

            String modelName = child.getName();
            File modelFile = Objects.requireNonNull(child.listFiles(file -> file.getName().endsWith(".json")))[0];

            File[] textureFiles = child.listFiles(file -> file.getName().endsWith(".png"));
            assert Objects.requireNonNull(textureFiles).length > 0;

            String authorName = Objects.requireNonNull(
                child.listFiles(file -> file.getName().endsWith(".txt")))[0].getName()
                .replace(".txt", "");

            delegateToModelProcessor(modelFile.getAbsolutePath(), material, modelName, -1, textureFiles, authorName);
        }
    }

}
