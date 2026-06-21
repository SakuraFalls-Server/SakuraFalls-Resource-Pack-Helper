package net.sakurafalls.resource_pack_helper.json.model;

import com.google.gson.JsonObject;

import java.io.*;

public class JsonFurnitureModel extends JsonModel {

    public JsonFurnitureModel(File file) throws IOException {
        super(file);
    }

    public void setTexturePath(String texturePath) {
        JsonObject textures = jsonObject.getAsJsonObject("textures");
        textures.addProperty("0", texturePath);
        jsonObject.getAsJsonObject("textures").addProperty("particle", texturePath);
    }

}
