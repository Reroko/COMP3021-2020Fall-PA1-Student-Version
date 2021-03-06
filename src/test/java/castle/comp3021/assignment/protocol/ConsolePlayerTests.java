package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPiece;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.player.ConsolePlayer;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsolePlayerTests {
    @Test
    @SampleTest
    public void testNextMove() {
        String data = "c3->c2\r\n"; // correct
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            var player1 = new MockPlayer(Color.PURPLE);
            var player2 = new ConsolePlayer("RandomPlayer");
            var config = new Configuration(3, new Player[]{player1, player2});
            var piece1 = new MockPiece(player1);
            var piece2 = new MockPiece(player2);
            config.addInitialPiece(piece1, 0, 0);
            config.addInitialPiece(piece2, 2, 2);
            var game = new JesonMor(config);
            var move = player2.nextMove(game, game.getAvailableMoves(player2));
            assertEquals(new Move(2, 2, 2, 1), move);
        } finally {
            System.setIn(stdin);
        }
    }
}
