package net.sakurafalls.resource_pack_helper.config;

import net.sakurafalls.resource_pack_helper.ResourcePackHelperMain;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public final class Config {

    private static Properties properties = new Properties();

    private Config() {
    }

    public static void load() throws IOException {
        File file = tryCreateConfigFile();
        InputStream inputStream = new FileInputStream(file);
        properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
    }

    private static File tryCreateConfigFile() throws IOException {
        File configFile = new File("config.properties");
        if (!configFile.exists()) {
            try (InputStream configStream =
                     ResourcePackHelperMain.class.getResourceAsStream("/config.properties")) {
                assert configStream != null;
                Files.copy(
                    configStream,
                    configFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        return configFile;
    }

    public static String getInputDirectoryPath() {
        return properties.getProperty("input_directory");
    }

    public static String getResourcePackDirectoryPath() {
        return properties.getProperty("resource_pack_directory");
    }

    public static boolean shouldConsiderPre1_21() {
        return Boolean.parseBoolean(properties.getProperty("consider_pre_1_21"));
    }

}
