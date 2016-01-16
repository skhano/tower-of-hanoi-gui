package hanoi;

import org.junit.Test;

import ucb.junit.textui;

/**
 * The suite of all of the JUnit tests for the hanoi package.
 * @author Sam Khano
 */
public class UnitTest {

    /**
     * Run the JUnit tests in the hanoi package. Add xxxTest.class entries to
     * the arguments of runClasses to run other JUnit tests.
     */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTest.class, HanoiTowerTest.class, GameTest.class);
    }

    /** A dummy test to avoid complaint. */
    @Test
    public void placeholderTest() {
    }

}
