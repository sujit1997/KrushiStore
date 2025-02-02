package listeners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestNGListeners.class)
public class TestNGListenersDemo {

    @Test
    public void test1(){
        System.out.println("I am test 1");
        //Assert.assertTrue(false);
    }
    @Test
    public void test2(){
        System.out.println("I am test 2");
    }
    @Test
    public void test3() {
        System.out.println("I am test 3");
        throw new SkipException("This test is skipped");
    }
}