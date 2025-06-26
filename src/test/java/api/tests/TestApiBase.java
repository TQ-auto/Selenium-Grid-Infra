package api.tests;

import generalutils.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Stack;

public abstract class TestApiBase {

    Logger logger;

    // Saves objects to be deleted at the end of each test.
    Stack<Object> deletionStack = new Stack<>();

    @BeforeClass
    public void setLogger(){
        logger = LogManager.getLogger(this.getClass());
    }

    @AfterClass
    public void cleanup() throws IOException {
        TestUtils.cleanup(deletionStack);
    }
}
