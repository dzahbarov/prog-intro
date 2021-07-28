package game.Players;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {

        for (int r = 0; r < position.getRowSize(); r++) {
            for (int c = 0; c < position.getColSize(); c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
