package Search.Models;

import java.util.List;

import Search.Utils.JsonParsing;

public class SearchEngine {
    private rbTree index;

    public SearchEngine() {
        index = new rbTree();
    }

    public void addData(String keyword, String value) {
        index.insert(keyword, value);
    }

    public List<Node> search(String keyword) {
        String resourcePath = "/data.json"; 
        JsonParsing.loadJsonData(index, resourcePath);

        var searchResults = index.search(keyword);;
        
        if (searchResults.isEmpty()) {
            System.out.println("No results found for the key: " + keyword);
        }
        return searchResults;
    }
}
