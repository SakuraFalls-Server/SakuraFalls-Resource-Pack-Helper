package net.sakurafalls.resource_pack_helper.json.vanilla;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.sakurafalls.resource_pack_helper.json.JsonFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class JsonVanillaItem extends JsonFile {

    public JsonVanillaItem(File file) throws IOException {
        super(file);
    }

    public void setCustomModelDataItem(int customModelData, String path) {
        Optional<JsonElement> jsonElement = jsonObject.getAsJsonObject("model")
            .getAsJsonArray("entries").asList().stream()
            .filter(t -> matchesCustomModelData(t, customModelData))
            .findFirst();
        if (jsonElement.isPresent()) {
            jsonElement.get().getAsJsonObject().getAsJsonObject("model").addProperty("model", path);
        } else {
            int finalCustomModelData = customModelData;
            if (customModelData == -1) {
                finalCustomModelData = jsonObject.getAsJsonObject("model")
                    .getAsJsonArray("entries").asList().size() + 1;
            }
            JsonObject entryObject = new JsonObject();
            JsonObject modelObject = new JsonObject();
            modelObject.addProperty("type", "model");
            modelObject.addProperty("model", path);
            entryObject.add("model", modelObject);
            entryObject.addProperty("threshold", finalCustomModelData);
            jsonObject.getAsJsonObject("model").getAsJsonArray("entries").add(entryObject);
        }
    }

    private boolean matchesCustomModelData(JsonElement element, int customModelData) {
        return element.getAsJsonObject().get("threshold").getAsInt() == customModelData;
    }

}
