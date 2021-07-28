package game;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MNKBoard extends Readers implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int rowSize;
    private final int colSize;
    private final int k;
    private final PrintStream out;
    private final Scanner in;


    public MNKBoard(final PrintStream out, final Scanner in) {

        this.out = out;
        this.in = in;

        this.rowSize = getInt(in, out, "Row size");

        this.colSize = getInt(in, out, "Col size");

        int k = getInt(in, out, "K");

        while (k > getColSize() && k > getRowSize()) {
            out.println("Impossible K");
            k = getInt(in, out, "K");
        }
        this.k = k;

        this.cells = new Cell[rowSize][colSize];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public MNKBoard() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        int empty = 0;
        for (int u = 0; u < getRowSize(); u++) {
            for (int v = 0; v < getColSize(); v++) {
                if (cells[u][v] == Cell.E) {
                    empty++;
                }
                int inRow1 = 0;
                int inRow2 = 0;
                int inCol1 = 0;
                int inCol2 = 0;
                int diag1 = 0;
                int diag2 = 0;
                int diag3 = 0;
                int diag4 = 0;
                for (int i = 0; i < k; i++) {
                    if (u + i < getRowSize() && cells[u + i][v] == turn) {
                        inRow1++;
                    }
                    if (u - i >= 0 && cells[u - i][v] == turn) {
                        inRow2++;
                    }
                    if (v + i < getColSize() && cells[u][v + i] == turn) {
                        inCol1++;
                    }
                    if (v - i >= 0 && cells[u][v - i] == turn) {
                        inCol2++;
                    }
                    if (u + i < getRowSize() && v + i < getColSize() && cells[u + i][v + i] == turn) {
                        diag1++;
                    }
                    if (u + i < getRowSize() && v - i >= 0 && cells[u + i][v - i] == turn) {
                        diag2++;
                    }
                    if (u - i >= 0 && v + i < getColSize() && cells[u - i][v + i] == turn) {
                        diag3++;
                    }
                    if (u - i >= 0 && v - i >= 0 && cells[u - i][v - i] == turn) {
                        diag4++;
                    }
                }
                if (inRow1 == k || inRow2 == k || inCol1 == k || inCol2 == k || diag1 == k || diag2 == k || diag3 == k || diag4 == k) {
                    return Result.WIN;
                }
            }

        }
        if (empty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rowSize
                && 0 <= move.getColumn() && move.getColumn() < colSize
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public void clear(){
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    @Override
    public int getRowSize() {
        return rowSize;
    }

    @Override
    public int getColSize() {
        return colSize;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < colSize; i++) {
            sb.append(i + 1);
        }

        for (int r = 0; r < getRowSize(); r++) {
            sb.append("\n");
            sb.append(r + 1);
            for (int c = 0; c < getColSize(); c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
