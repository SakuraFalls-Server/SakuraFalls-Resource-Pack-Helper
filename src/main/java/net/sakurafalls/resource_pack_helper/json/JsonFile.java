package net.sakurafalls.resource_pack_helper.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public abstract class JsonFile {

    private final File file;
    protected final JsonObject jsonObject;

    public JsonFile(File file) throws IOException {
        this.file = file;
        jsonObject = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
    }

    public final void write() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonObject);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
        }
    }

}
