package Multiprocessing.Mapper;

import java.util.ArrayList;

public interface MapperMultiprocesing {

    /**
     * Method to parallelized a mapping of an array
     * that holds the source code from a language
     * recognized by the mapper.
     *
     * @apiNote the compilation units mapped it's going
     * to be held int the array declare in the
     * globalsSpark.Globals method.
     *
     * @param pSourceCodes an Array that holds the source
     *                     code that we want to map.
     */
    void start(ArrayList<String> pSourceCodes);
}
