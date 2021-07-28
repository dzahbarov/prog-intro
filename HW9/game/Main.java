package game;

import game.Players.RandomPlayer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
//        final Game game = new Game(true, new RandomPlayer(), new RandomPlayer());
//        int result;
//
//        result = game.play(new MNKBoard());
//        System.out.println("Game result: " + result);
        for (int i = 0; i < 1000; i++) {
            final Championship championship = new Championship();
            championship.setPlayers(new RandomPlayer(), new RandomPlayer(), new RandomPlayer());
            championship.playChampionship();
        }
    }
}
