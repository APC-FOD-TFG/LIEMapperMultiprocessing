package Multiprocessing.GlobalsSpark;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import ASTMCore.ASTMSource.CompilationUnit;
import com.google.gson.Gson;
import gastmappers.liemapper.LIEMapper;
import gastmappers.misc.Misc;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Class to get the clustering initial values that
 * we need to implement the multiprocessing
 * @author Fede
 */
public final class Globals implements Serializable{

    public static final int finalPartitionsParallelized = 4;
    private static SparkConf sparkConf;
    private static JavaSparkContext sc;
    private static final LIEMapper map = new LIEMapper();
    /*the value where we are going to store the transformed the
     compilation units to json's in a string format.*/
    private static final ArrayList<String> finalJsonCompUnit = new ArrayList<>();

    /**
     * Method to initialized the spark context to create the local clustering
     * this method can run with as many core as the machine has.(local[*])
     * @return the context for the spark multiprocessing initialized
     */
    public static JavaSparkContext getSparkContext() {
        // configure spark
        if (sparkConf == null) {
            sparkConf = new SparkConf().setAppName("LIEMapperClustering").setMaster("local[*]").set("spark.executor.memory", "4g").
                    set("spark.driver.host", "127.0.0.1").set("spark.driver.bindAddress", "127.0.0.1");
            // start a spark context
            sc = new JavaSparkContext(sparkConf);
        }
        return sc;
    }

    /**
     * Method to reset the variables
     */
    public static void reset() {
        finalJsonCompUnit.clear();
        if(sc != null) {
            sc.close();
            sc = null;
            sparkConf = null;
        }
    }

    /**
     * Method to map the LIE++ source code into a compilation
     * unit, then into a Json, and then saved into an array of strings
     * @param pSourceCode a string object that holds the source code
     *                    that we want to map.
     */
    public static void mapSourceCode(String pSourceCode){
        compilationUnitToJson(map.getGastSingleCompUnit(pSourceCode));
    }

    /**
     * Method to transform the array of compilation units to an
     * array of strings that holds the json equivalent.
     * @return returns an array of string that has the equivalent
     *         of the compilation units in json format.
     */
    public static ArrayList<String> getCompilationUnitsIntoJsons(){
        return finalJsonCompUnit;
    }

    /**
     * Method to transform the array of compilation units to an
     * array of strings that holds the json equivalent.
     * @param compUnit object from the GAST that holds the equivalent
     *                 of the LIE++ language mapped.
     */
    public static void compilationUnitToJson(CompilationUnit compUnit){
        // Transform the compilation unit into its JSON representation.
        Gson gson = new Gson();
        String jsonRepresentation = gson.toJson(compUnit);

        // Remove the "null"'s values into a empty string.
        jsonRepresentation = jsonRepresentation.replaceAll("null", "");

        // Store the JSON representation in the parsed file list.
        finalJsonCompUnit.add(jsonRepresentation);
    }

    /**
     * Method to read all the files that we need the source code in order to
     * proceed with the mapping of those codes, it's only meant to use
     * for unit testing.
     * @implNote DO NOT USE THIS METHOD FOR ANYTHING OTHER THAN UNIT TESTING
     * @param filesLocation the array that holds all the files locations that
     *                      we want to extract the source code from them.
     * @return returns an array that holds the source code from all the
     *         files that we're going to test in the unit testing.
     * @throws IOException since what we insert is a path to collect all the
     *                     source code that we want to transform, it's need it
     *                     this exception to alert that a possible missed reading
     *                     can happen.
     */
    public static ArrayList<String> fillTestArray(String[] filesLocation) throws IOException {
        ArrayList<String> finalSourceCode = new ArrayList<>();
        for(int i =0 ; i<1000; i++){
            for (String s : filesLocation){
                finalSourceCode.add(Misc.readFileToString(s));
            }
        }
        return finalSourceCode;
    }
}
