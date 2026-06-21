package net.sakurafalls.resource_pack_helper.json.model;

import net.sakurafalls.resource_pack_helper.json.JsonFile;

import java.io.*;

public abstract class JsonModel extends JsonFile {

    public JsonModel(File file) throws IOException {
        super(file);
    }

    public final void setCreditsMessage(String creditsMessage) {
        jsonObject.getAsJsonObject().addProperty("credit", creditsMessage);
    }

}
