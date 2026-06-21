package net.sakurafalls.resource_pack_helper.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public abstract class FileProcessor {

    protected final File inputFile;

    public FileProcessor(String path) throws IOException {
        this.inputFile = new File(path);
        if (!inputFile.getParentFile().exists()) {
            if (!inputFile.getParentFile().mkdirs()) {
                throw new IOException("Failed to create directory " + inputFile.getParentFile().getAbsolutePath());
            }
        }
        if (inputFile.isDirectory()) {
            if (!inputFile.exists()) {
                if (!inputFile.mkdir()) {
                    throw new IOException("Failed to create directory " + inputFile.getAbsolutePath());
                }
            }
        }
    }

    public abstract void process() throws IOException;

    public static File duplicateFile(String from, String to) throws IOException {
        File toFile = new File(to);
        if (!toFile.getParentFile().exists()) {
            if (!toFile.getParentFile().mkdirs()) {
                throw new IOException("Could not create directory " + toFile.getParentFile().getAbsolutePath());
            }
        }
        Files.copy(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        return new File(to);
    }

}
