import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private static final int WINDOW_X = GameWindow.WINDOW_X + 50;
    private static final int WINDOW_Y = GameWindow.WINDOW_Y + 50;
    private static final int WINDOW_WIDTH = GameWindow.WINDOW_WIDTH - 100;
    private static final int WINDOW_HEIGHT = GameWindow.WINDOW_HEIGHT - 100;

    static final int MODE_HUM_VS_AI = 0;
    static final int MODE_HUM_VS_HUM = 1;

    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;


    private JSlider slFieldSize;
    private JSlider slWinningLength;

    private GameWindow gameWindow;

    private JRadioButton humVsAi;
    private JRadioButton humVsHum;
    private ButtonGroup gameMode;

    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe setting");
        setLayout(new GridLayout(8, 1));

        add(new JLabel("Choose game mode:"));
        humVsAi = new JRadioButton("Human vs Ai",true);
        add(humVsAi);
        humVsHum = new JRadioButton("Human vs Human");
        humVsHum.setEnabled(false);
        add(humVsHum);
        gameMode = new ButtonGroup();
        gameMode.add(humVsAi);
        gameMode.add(humVsHum);

        add(new JLabel("Choose size:"));
        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintTicks(true);
        slFieldSize.setPaintLabels(true);
        add(slFieldSize);

        add(new JLabel("Choose winning length :"));
        slWinningLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slWinningLength.setMajorTickSpacing(1);
        slWinningLength.setPaintTicks(true);
        slWinningLength.setPaintLabels(true);
        add(slWinningLength);

        slFieldSize.addChangeListener(e -> {
            slWinningLength.setMaximum(slFieldSize.getValue());
        });

        JButton btnStartTheGame = new JButton("Start the game");
        add(btnStartTheGame);
        btnStartTheGame.addActionListener(e -> {
            int mode;
            if (humVsAi.isSelected()){
                mode = MODE_HUM_VS_AI;
            }else {
                mode = MODE_HUM_VS_HUM;
            }

            int fieldSize = slFieldSize.getValue();
            int winningLength = slWinningLength.getValue();

            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winningLength;
            Logic.initMap();
            Logic.gameFinished = false;

            gameWindow.startNewGame(mode, fieldSize, winningLength);
            setVisible(false);
        });

        setVisible(false);
    }
}
