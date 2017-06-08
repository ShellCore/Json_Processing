package com.shellcore.java.json;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Cesar. 08/06/2017.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Create de JSONReader object with the file location
        String inputFile = "./src/students.json";
        JsonReader reader = Json.createReader(new FileReader(inputFile));

        // Create the Json tree structure
        JsonStructure jsonStructure = reader.read();
        JsonObject object = (JsonObject) jsonStructure;

        /**
         * To navigate into the tree, we will use a custom method who navigates recursively
         * in the complete structure.
         */

        navigateTree(jsonStructure, "Student");
    }

    private static void navigateTree(JsonValue tree, String key) {
        if (key != null) {
            System.out.print("Key: " + key + ": ");
        }
        /**
         * Get the element value type, and depending on the type, will excecute the
         * especific code of the matching condition of the switch statement.
         */

        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name :
                        object.keySet()) {
                    navigateTree(object.get(name), name);
                }
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue value :
                        array) {
                    navigateTree(value, null);
                }
                break;
            case STRING :
                JsonString jsonString = (JsonString) tree;
                System.out.println("STRING: " + jsonString.getString());
                break;
            case NUMBER :
                JsonNumber jsonNumber = (JsonNumber) tree;
                System.out.println("NUMBER: " + jsonNumber.toString());
            case TRUE :
            case FALSE :
            case NULL :
                System.out.println(tree.getValueType().toString());
                break;
        }

    }
}
