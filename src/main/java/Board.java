import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame {
    private final JButton[][] squares = new JButton[7][];
    boolean player1t = true;

    public Board() {
        for (int i = 0; i < Constant.BOARD_WIDTH; ++i) {
            this.squares[i] = new JButton[Constant.BOARD_WIDTH];

            for (int j = 0; j < Constant.BOARD_WIDTH; ++j) {
                JButton square = new JButton();
                if (i == 0) {
                    square.setText(String.valueOf(j + 1));
                    square.setBackground(Color.WHITE);
                    square.setFont(new Font(Constant.FONT_NAME, 1, Constant.FONT_SIZE));


                    square.addActionListener((e) -> {
                        int player = getPlayerInSquare(Integer.parseInt(square.getText()), 1);
                        int player1 = 1;
                        int player2 = 2;
                        int y = 1;
                        do {
                            if (getPlayerInSquare(Integer.parseInt(square.getText()), y) == 0) {
                                this.placeSquare(Integer.parseInt(square.getText()), y, 1);
                                if (player1t) {
                                    this.placeSquare(Integer.parseInt(square.getText()), y, player2);
                                    player1t = false;
                                    break;
                                } else {
                                    this.placeSquare(Integer.parseInt(square.getText()), y, player1);
                                    player1t = true;
                                }
                                if (player1 == getPlayerInSquare(1 , 1 ) && player1 == getPlayerInSquare(1 , 2 ) &&
                                        player1 == getPlayerInSquare(1 , 3 ) &&player1 == getPlayerInSquare(1 , 4 )) {
                                    System.out.println("player 1 win ! ");
                                }
                                    break;

                            }

                            y++;

                            } while (y != 7);




                    });
                } else {
                    square.setEnabled(false);
                }
                this.squares[i][j] = square;
                this.add(square);
            }
        }

        this.setLocationRelativeTo((Component) null);
        GridLayout gridLayout = new GridLayout(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        this.setLayout(gridLayout);
        this.setSize(Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE, Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void placeSquare(int x, int y, int player) {
        this.squares[Constant.BOARD_WIDTH - y][x - 1].setBackground(player == 1 ? Color.RED : Color.YELLOW);

    }

    public int getPlayerInSquare(int x, int y) {
        byte player = 0;

        try {
            Color backgroundColor = this.squares[Constant.BOARD_WIDTH - y][x - 1].getBackground();
            if (backgroundColor.equals(Color.RED)) {
                player = 1;
            } else if (backgroundColor.equals(Color.YELLOW)) {
                player = 2;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return player;
    }
}
