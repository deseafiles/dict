package Search.Utils;

import java.util.ArrayList;
import java.util.List;
import Search.Models.Node;
import Search.Models.rbTree;

public class PrintJson {
    public static List<String> printRedBlackTreeResults(rbTree rbTree, String searchParam) {
        List<Node> result = rbTree.search(searchParam);
        ArrayList<String> combinedResults = new ArrayList<>();

        if (result != null && !result.isEmpty()) {
            for (Node node : result) {
                String resultString = "Key: " + node.getKey() + ", Value: " + node.getValue();
                if (!combinedResults.contains(resultString)) {
                    combinedResults.add(resultString);
                }
            }
        }
        return combinedResults;
    }
}
