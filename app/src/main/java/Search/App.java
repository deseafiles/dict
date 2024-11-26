package Search;


import Search.Models.rbTree;
import Search.Utils.JsonParsing;

public class App {
    public static void main(String[] args) {
        String resourcePath = "/data.json"; 


        rbTree<String, String> rbTree = new rbTree<>();

        JsonParsing.loadJsonData(rbTree, resourcePath);

        String searchKey = "Mengenal"; 
        var searchResults = rbTree.search(searchKey);
        
        if (searchResults.isEmpty()) {
            System.out.println("No results found for the key: " + searchKey);
        } else {
            searchResults.forEach(node -> 
                System.out.println("Found Key: " + node.getKey() + ", Value: " + node.getValue())
            );
        }
    }
}
