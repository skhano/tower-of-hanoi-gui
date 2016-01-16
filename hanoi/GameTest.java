package hanoi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameTest {

    /**
     * Test getTopDisk method
     */
    @Test
    public void getTopDiskTest() {
        Game game = new Game(3);

        assertEquals(0, game.getTopDisk(0));
        assertEquals(1, game.getTopDisk(0));
        assertEquals(2, game.getTopDisk(0));
        assertEquals(-1, game.getTopDisk(0));
        assertEquals(-1, game.getTopDisk(0));
    }

    /**
     * Test the move and push methods.
     */
    @Test
    public void moveTest() {
        Game game = new Game(4);
        game.move(0, 2);

        assertEquals(1, game.getTopDisk(0));
        assertEquals(0, game.getTopDisk(2));

        game.push(2, 0);
        game.move(2, 0);

        assertEquals(0, game.getTopDisk(0));
        assertEquals(0, game.getDisks(2).length);

        game.push(0, 0);
        game.push(0, 4);
        game.push(0, 5);

        assertEquals(0, game.getTopDisk(0));
    }

    /**
     * Test the solve method.
     */
    public void solveTest() {
        Game game = new Game(4);
        game.solve();

        assertEquals(3, game.getTopDisk(2));
        assertEquals(-1, game.getTopDisk(1));
        assertEquals(-1, game.getTopDisk(0));

        Game game2 = new Game(8);
        game2.solve();

        assertEquals(7, game.getTopDisk(2));
        assertEquals(-1, game.getTopDisk(1));
        assertEquals(-1, game.getTopDisk(0));
    }

    /**
     * Test the lowestScore method.
     */
    public void lowestScoreTest() {
        Game gameOf3 = new Game(3);
        Game gameOf5 = new Game(5);
        Game gameOf7 = new Game(7);

        assertEquals(7, gameOf3.lowestScore());
        assertEquals(31, gameOf5.lowestScore());
        assertEquals(127, gameOf7.lowestScore());
    }

}
