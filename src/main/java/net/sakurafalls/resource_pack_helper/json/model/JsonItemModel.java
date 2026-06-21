package net.sakurafalls.resource_pack_helper.json.model;

import com.google.gson.JsonObject;

import java.io.*;

public class JsonItemModel extends JsonModel  {

    public JsonItemModel(File file) throws IOException {
        super(file);
    }

    public void setParticlePath(String particlePath) {
        jsonObject.getAsJsonObject("textures").addProperty("particle", particlePath);
    }

    public void setTexturePath(int index, String texturePath) {
        JsonObject textures = jsonObject.getAsJsonObject("textures");
        textures.addProperty(Integer.toString(index), texturePath);
    }

}
