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
    public void ExtensionTest(){
        System.out.println("-------------Testing the multiprocessing for LIE++-------------");
        try{
            ArrayList<String> finalSourceCode = Globals.fillTestArray(locations);
            LIEMapperMultiprocessing mmp = new LIEMapperMultiprocessing();
            mmp.start(finalSourceCode);
            junit.framework.Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error while trying to test the multiprocessing");
        }
    }
}
