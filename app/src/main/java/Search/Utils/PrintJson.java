package Search.Utils;

import java.util.ArrayList;
import java.util.List;
import Search.Models.Node;
import Search.Models.rbTree;

public class PrintJson {
    public static List<String> printRedBlackTreeResults(rbTree<String, String> rbTree, String searchParam) {
        // Memanggil search pada rbTree untuk mendapatkan hasil pencarian
        List<Node<String, String>> result = rbTree.search(searchParam);
        ArrayList<String> combinedResults = new ArrayList<>();

        if (result != null && !result.isEmpty()) {
            // Mengonversi hasil Node ke dalam format String dan menambahkannya ke combinedResults
            for (Node<String, String> node : result) {
                // Menambahkan key dan value dari node ke dalam combinedResults sebagai string
                String resultString = "Key: " + node.getKey() + ", Value: " + node.getValue();
                if (!combinedResults.contains(resultString)) {
                    combinedResults.add(resultString);
                }
            }
        }
        return combinedResults;
    }
}
