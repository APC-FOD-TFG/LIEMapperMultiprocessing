package Multiprocessing.Mapper.LIEMapper;

import ASTMCore.ASTMSource.CompilationUnit;
import Multiprocessing.GlobalsSpark.Globals;
import Multiprocessing.Mapper.MapperMultiprocesing;
import gastmappers.liemapper.LIEMapper;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.ArrayList;

public class LIEMapperMultiprocessing implements MapperMultiprocesing {

    /**
     * Method to parallelized a mapping of an array
     * that holds the source code from LIE++ language.
     *
     * @apiNote the compilation units mapped it's going
     * to be held int the array declare in the
     * globalsSpark.Globals method.
     *
     * @param pSourceCodes an Array that holds the source
     *                     code from the LIE++ language.
     */
    @Override
    public void start(ArrayList<String> pSourceCodes){
        //cleaning the array list that is going to hold the
        // compilation units mapped.
        Globals.reset();
        // configure spark
        JavaRDD<String> parallSourceCode = Globals.getSparkContext().parallelize(pSourceCodes);

        //parallSourceCode.collect().forEach(System.out::println);
        parallSourceCode.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                Globals.mapSourceCode(s);
            }
        });
        Globals.getSparkContext().close();
    }
}
