package Search.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import Search.Models.rbTree;

public class JsonParsing {

    public static void loadJsonData(rbTree<String, String> tree, String resourcePath) {
        try (InputStream filStream = JsonParsing.class.getResourceAsStream(resourcePath)) {
            if (filStream != null) {
                System.out.println("File found: " + resourcePath);
                JSONArray jsonArray = new JSONArray(new JSONTokener(filStream));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String key = jsonObject.keys().next();
                    String value = jsonObject.getString(key);
                    tree.insert(key, value);
                }
            } else {
                System.err.println("Failed to load data.json. The resource is not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
