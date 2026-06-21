package net.sakurafalls.resource_pack_helper.json.vanilla;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.sakurafalls.resource_pack_helper.json.JsonFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class JsonVanillaItemPre1_21 extends JsonFile {

    public JsonVanillaItemPre1_21(File file) throws IOException {
        super(file);
    }

    public void setCustomModelDataItem(int customModelData, String path) {
        Optional<JsonElement> jsonElement = jsonObject.getAsJsonArray("overrides").asList().stream()
            .filter(t -> matchesCustomModelData(t, customModelData))
            .findFirst();
        if (jsonElement.isPresent()) {
            jsonElement.get().getAsJsonObject().addProperty("model", path);
        } else {
            int finalCustomModelData = customModelData;
            if (customModelData == -1) {
                finalCustomModelData = jsonObject.getAsJsonArray("overrides").asList().size() + 1;
            }
            JsonObject modelObject = new JsonObject();
            JsonObject predicateObject = new JsonObject();
            predicateObject.addProperty("custom_model_data", finalCustomModelData);
            modelObject.add("predicate", predicateObject);
            modelObject.addProperty("model", path);
            jsonObject.getAsJsonArray("overrides").add(modelObject);
        }
    }

    private boolean matchesCustomModelData(JsonElement element, int customModelData) {
        return element.getAsJsonObject().getAsJsonObject("predicate")
            .get("custom_model_data").getAsInt() == customModelData;
    }

}
