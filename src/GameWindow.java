import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    static final int WINDOW_X = 450;
    static final int WINDOW_Y = 100;
    static final int WINDOW_WIDTH = 500;
    static final int WINDOW_HEIGHT = 500;

    private SettingWindow settingWindow;
    private BattleMap battleMap;
    private WinnerWindow winnerWindow;

    public GameWindow() {
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        settingWindow = new SettingWindow(this);
        battleMap = new BattleMap(this);
        winnerWindow = new WinnerWindow(this);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton btnStartNewGame = new JButton("Start New Game");
        panel.add(btnStartNewGame);
        JButton btnExit = new JButton("Exit");
        panel.add(btnExit);
        add(panel, BorderLayout.SOUTH);

        btnStartNewGame.addActionListener(e -> {
            settingWindow.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        add(battleMap, BorderLayout.CENTER);

        setVisible(true);
    }

    public void startNewGame(int mode, int fieldSize, int winningLength) {
        battleMap.startNewGame(mode, fieldSize, winningLength);
    }

    public void winner() {
        if (Logic.checkWin(Logic.DOT_X)) {
            winnerWindow.winnerLabel.setText("You win!!!");
            winnerWindow.setVisible(true);
            return;
        }
        if (Logic.checkWin(Logic.DOT_O)) {
            winnerWindow.winnerLabel.setText("Ai win!!!");
            winnerWindow.setVisible(true);
            return;
        }
        if (Logic.isFull()) {
            winnerWindow.winnerLabel.setText("Draw!!!");
            winnerWindow.setVisible(true);
            return;
        }
    }
}
