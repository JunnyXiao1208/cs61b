import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @Author : junnny
 * @Date : 2024/2/2 13:28
 * @Description : test for Flik's lib
 */


public class FlikTest {
    @Test
    public void testIsSameNumber(){
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
