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

    public static void main(String[] args) throws IOException {
        //extracting the code from the files that we want to test
        ArrayList<String> finalSourceCode = Globals.fillTestArray(locations);

        LIEMapperMultiprocessing mmp = new LIEMapperMultiprocessing();
        mmp.start(finalSourceCode);

        ArrayList<String> tempJsons = Globals.getCompilationUnitsIntoJsons();
        for (String s : tempJsons){System.out.println(s);}
    }
}
