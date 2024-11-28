package Search.Utils;

import java.util.ArrayList;
import java.util.List;
import Search.Models.Node;
import Search.Models.rbTree;

public class PrintJson {
    public static List<String> printRedBlackTreeResults(rbTree rbTree, String searchParam) {
        String resourcePath = "/data.json";
        JsonParsing.loadJsonData(rbTree, resourcePath);
        
        String resultMatchKey = rbTree.searchExactMatchKey(searchParam);
        List<Node> resultIncludedKey = rbTree.searchIncludedMatchKey(searchParam);
        List<Node> resultIncludeValue = rbTree.searchIncludedMatchValue(searchParam);
        
        List<String> combinedResult = new ArrayList<>();
        
        if (resultMatchKey != null && !resultMatchKey.isEmpty()) {
            combinedResult.add(resultMatchKey);
        }
        
        if (resultIncludedKey != null && !resultIncludedKey.isEmpty()) {
            for (Node node : resultIncludedKey) {
                String resultString = "Key: " + node.getKey() + ", Value: " + node.getValue();
                if (!combinedResult.contains(resultString)) {
                    combinedResult.add(resultString);
                }
            }
        }
        
        if (resultIncludeValue != null && !resultIncludeValue.isEmpty()) {
            for (Node node : resultIncludeValue) {
                String resultString = "Key: " + node.getKey() + ", Value: " + node.getValue();
                if (!combinedResult.contains(resultString)) {
                    combinedResult.add(resultString);
                }
            }
        }
        
        return combinedResult;
    }
}
