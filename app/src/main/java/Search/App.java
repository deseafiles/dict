package Search;


import Search.Models.rbTree;
import Search.Utils.JsonParsing;

public class App {
    public static void main(String[] args) {
        // Menentukan path untuk resource JSON
        String resourcePath = "/data.json"; // pastikan path sesuai dengan struktur folder Anda

        // Membuat pohon Red-Black
        rbTree<String, String> rbTree = new rbTree<>();

        // Memuat data JSON ke dalam pohon
        JsonParsing.loadJsonData(rbTree, resourcePath);

        // Setelah data dimuat, Anda bisa melakukan pencarian atau operasi lain di pohon
        String searchKey = "Mengenal"; // ganti dengan key yang sesuai
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
