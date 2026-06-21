package net.sakurafalls.resource_pack_helper;

import net.sakurafalls.resource_pack_helper.config.Config;
import net.sakurafalls.resource_pack_helper.processor.directory.FurnitureDirectoryProcessor;
import net.sakurafalls.resource_pack_helper.processor.directory.ItemDirectoryProcessor;

import java.io.File;
import java.io.IOException;

public class ResourcePackHelperMain {

    static void main() throws IOException {
        Config.load();

        long now = System.currentTimeMillis();

        System.out.println("Processing items directory...");
        try {
            ItemDirectoryProcessor itemDirectoryProcessor = new ItemDirectoryProcessor();
            itemDirectoryProcessor.process();
        } catch (IOException e) {
            System.out.println("Failed to process the items directory: " + e.getMessage());
        }

        System.out.println("Processing furniture directory...");
        try {
            FurnitureDirectoryProcessor furnitureDirectoryProcessor = new FurnitureDirectoryProcessor();
            furnitureDirectoryProcessor.process();
        } catch (IOException e) {
            System.out.println("Failed to process the furniture directory: " + e.getMessage());
        }

        long duration = System.currentTimeMillis() - now;
        System.out.println("Finished in " + duration + " ms");
    }

}
