import Multiprocessing.GlobalsSpark.Globals;
import Multiprocessing.Mapper.LIEMapper.LIEMapperMultiprocessing;
import java.io.IOException;
import java.util.ArrayList;

public class mainClass {
    
    private static final String[] locations = {
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/AssignmentsTest.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/GpioDeclarationTest.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/ArrayDeclaration.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/FunctionDeclarationTest.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/MainFunctionDeclarationTest.lie"
    };

    public void SerialTest(ArrayList<String> finalSourceCode){
        /*         SERIAL*/
        //LIEMapper mp = new LIEMapper();
        for(String s : finalSourceCode){
            Globals.mapSourceCode(s);
        }

        ArrayList<String> outValue = Globals.getCompilationUnitsIntoJsons();
        for (String ss : outValue){
            System.out.println(ss);
        }
    }

    public static void main(String[] args) throws IOException {
        //extracting the code from the files that we want to test
        ArrayList<String> finalSourceCode = Globals.fillTestArray(locations);

//        mainClass mn = new mainClass();
//        mn.SerialTest(finalSourceCode);

        LIEMapperMultiprocessing mmp = new LIEMapperMultiprocessing();
        mmp.start(finalSourceCode);

//        // configure spark
//        JavaRDD<String> parallSourceCode = Globals.getSparkContext().parallelize(finalSourceCode);
//
//        //parallSourceCode.collect().forEach(System.out::println);
//        parallSourceCode.foreach(new VoidFunction<String>() {
//            @Override
//            public void call(String s) throws Exception {
//                Globals.mapSourceCode(s);
//            }
//        });
//        Globals.getSparkContext().close();

        ArrayList<String> tempJsons = Globals.getCompilationUnitsIntoJsons();
        for (String s : tempJsons){System.out.println(s);}
    }
}
