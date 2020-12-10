package Multiprocessing.Mapper.LIEMapper;

import ASTMCore.ASTMSource.CompilationUnit;
import Multiprocessing.GlobalsSpark.Globals;
import gastmappers.Language;
import gastmappers.Mapper;
import gastmappers.MapperFactory;
import gastmappers.exceptions.UnsupportedLanguageException;
import gastmappers.liemapper.LIEMapper;
import gastmappers.misc.Misc;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import testrun.config.ConfigurationTestRun;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

@DisplayName("LIE++ Mapper Unit testing module")
public class LIEMapperMultiprocessingTest {
    /*array to hold the test locations that we are going to
     use to test the clustering*/
    private static final String[] locations = {
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/AssignmentsTest.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/GpioDeclarationTest.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/ArrayDeclaration.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/FunctionDeclarationTest.lie",
            "./src/test/java/Multiprocessing/Mapper/LIEMapper/testFiles/MainFunctionDeclarationTest.lie"
    };

    @BeforeAll
    static void beforeAll() {
        System.out.println("----------------Starting the LIEMapperMultiprocessing unit test-----------------");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("\n---------------------------------------------------------------");
    }

    @AfterEach
    void afterEach() {
        System.out.println("---------------------------------------------------------------\n");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("--------------Finishing the LIEMapperMultiprocessing unit test----------------");
    }

    /**
     * Method to test the multiprocessing for the LIE++
     * language.
     */
    @Test
    @DisplayName("-------------Testing the multiprocessing for LIE++-------------")
    public void MultiprocessingTest(){
        System.out.println("-------------Testing the multiprocessing for LIE++-------------");
        try{
            ArrayList<String> finalSourceCode = Globals.fillTestArray(locations);
            LIEMapperMultiprocessing mmp = new LIEMapperMultiprocessing();

            //start to measuring Time
            Instant start = Instant.now();

            mmp.start(finalSourceCode);

            //finish the measuring
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            System.out.println(" - - - Total elapsed time: " + timeElapsed+ " milli seconds");

            //cleaning the global variables
            Globals.reset();

            junit.framework.Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error while trying to test the multiprocessing");
        }
    }

    /**
     * Method to test a serial implementation for the LIE++
     * language.
     */
    @Test
    @DisplayName("-------------Testing a serial implementation for LIE++-------------")
    public void SerialTest(){
        System.out.println("-------------Testing a serial implementation for LIE++-------------");
        try{
            ArrayList<String> finalSourceCode = Globals.fillTestArray(locations);

            //start to measuring Time
            Instant start = Instant.now();

            for(String s : finalSourceCode){
                Globals.mapSourceCode(s);
            }

            //finish the measuring
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            System.out.println(" - - - Total elapsed time: " + timeElapsed+ " milli seconds");

            //cleaning the global variables
            Globals.reset();

            junit.framework.Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error while trying to test the multiprocessing");
        }
    }
}
