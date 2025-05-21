package GeneralUtils;

import java.util.Random;

public class TestUtils {

    // Used to generate random test strings as text inputs
    public static String generateString(){
        return new Random()
                .ints(97,123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }
}
