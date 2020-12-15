import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;

    private int mode;
    private int fieldSize;
    private int winningLength;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;

    private int indent;
    private int winnerLineIndent;

    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setBackground(Color.gray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;

                if (!Logic.gameFinished) {
                    Logic.humanTurn(cellX, cellY);
                }
                gameWindow.winner();
                repaint();
            }
        });
    }

    public void startNewGame(int mode, int fieldSize, int winningLength) {
        this.mode = mode;
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;
        indent = 80 / fieldSize;
        winnerLineIndent = 230 / fieldSize;

        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit) {
            return;
        }

        cellHeight = getHeight() / fieldSize;
        cellWidth = getWidth() / fieldSize;

        for (int i = 0; i < fieldSize; i++) {
            int y = i * cellHeight;
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.drawLine(0, y, getWidth(), y);
            int x = i * cellWidth;
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.drawLine(x, 0, x, getHeight());
        }

        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, j, i);
                }
                if (Logic.map[i][j] == Logic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }
        if (Logic.checkWin(Logic.DOT_X)){
            drawWinnerLine(g);
        }else if (Logic.checkWin(Logic.DOT_O)){
            drawWinnerLine(g);
        }
        repaint();
    }

    private void drawX(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.blue);
        g.drawLine(cellX * cellWidth + indent, cellY * cellHeight + indent,
                (cellX + 1) * cellWidth - indent, (cellY + 1) * cellHeight - indent);
        g.drawLine((cellX + 1) * cellWidth - indent, cellY * cellHeight + indent,
                (cellX) * cellWidth + indent, (cellY + 1) * cellHeight - indent);
    }

    private void drawO(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.red);
        g.drawOval(cellX * cellWidth + indent, cellY * cellHeight + indent,
                cellWidth - indent * 2, cellHeight - indent * 2);
    }

    private void drawWinnerLine(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.green);
        g.drawLine(Logic.FIRST_X * cellWidth + winnerLineIndent, Logic.FIRST_Y * cellHeight + winnerLineIndent,
                (Logic.LAST_X + 1) * cellWidth - winnerLineIndent, (Logic.LAST_Y + 1) * cellHeight - winnerLineIndent);
    }
}
