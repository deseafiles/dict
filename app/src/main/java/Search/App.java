package Search;


import Search.Models.rbTree;
import Search.Utils.JsonParsing;
import Search.Utils.PrintJson;

public class App {
    public static void main(String[] args) {
        String resourcePath = "/data.json"; 
        
        rbTree rbTree = new rbTree();

        JsonParsing.loadJsonData(rbTree, resourcePath);

        String searchKey = "Mengenal lahan sawah"; 
        PrintJson.printRedBlackTreeResults(rbTree, searchKey);

        
        
    }
}
