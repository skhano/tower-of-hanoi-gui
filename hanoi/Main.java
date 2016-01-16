package hanoi;

/**
 * Display a GUI for the Towers of Hanoi game.
 * @author Sam Khano
 */
public class Main {

    /**
     * Display GUI and wait for termination.
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI(0);
        gui.go();
    }
}
