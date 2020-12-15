import javax.swing.*;
import java.awt.*;

public class WinnerWindow extends JFrame {
    private static final int WINDOW_X = GameWindow.WINDOW_X + 150;
    private static final int WINDOW_Y = GameWindow.WINDOW_Y + 150;
    private static final int WINDOW_WIDTH = GameWindow.WINDOW_WIDTH - 300;
    private static final int WINDOW_HEIGHT = GameWindow.WINDOW_HEIGHT - 300;

    JLabel winnerLabel = new JLabel();

    private GameWindow gameWindow;

    public WinnerWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new GridLayout(2, 1));

        add(winnerLabel);

        JButton btnOk = new JButton("Ok");
        add(btnOk);
        btnOk.addActionListener(e -> {
            setVisible(false);
        });

        setVisible(false);
    }
}
