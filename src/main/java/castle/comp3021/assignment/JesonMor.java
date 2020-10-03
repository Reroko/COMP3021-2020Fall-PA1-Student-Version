package castle.comp3021.assignment;

import castle.comp3021.assignment.protocol.*;
import org.jetbrains.annotations.NotNull;


/**
 * This class extends {@link Game}, implementing the game logic of JesonMor game.
 * Student needs to implement methods in this class to make the game work.
 * Hint: make good use of methods predefined in {@link Game} to get various information to facilitate your work.
 * <p>
 * Several sample tests are provided to test your implementation of each method in the test directory.
 * Please make make sure all tests pass before submitting the assignment.
 */
public class JesonMor extends Game {
    public JesonMor(Configuration configuration) {
        super(configuration);
    }

    /**
     * Start the game
     * Players will take turns according to the order in {@link Configuration#getPlayers()} to make a move until
     * a player wins.
     * <p>
     * In the implementation, student should implement the loop letting two players take turns to move pieces.
     * The order of the players should be consistent to the order in {@link Configuration#getPlayers()}.
     * {@link Player#nextMove(Game, Move[])} should be used to retrieve the player's choice of his next move.
     * After each move, {@link Game#refreshOutput()} should be called to refresh the gameboard printed in the console.
     * <p>
     * When a winner appears, set the local variable {@code winner} so that this method can return the winner.
     *
     * @return the winner
     */
    @Override
    public Player start() {
        // reset all things
        Player winner = null;
        this.numMoves = 0;
        this.board = configuration.getInitialBoard();
        this.currentPlayer = null;
        this.refreshOutput();
        while (true) {
            // TODO student implementation starts here
            this.currentPlayer = configuration.getPlayers()[numMoves%2];
            Move nextMove=this.currentPlayer.nextMove(this,getAvailableMoves(this.currentPlayer));
            updateScore(currentPlayer,getPiece(nextMove.getSource()),nextMove);
            movePiece(nextMove);
            numMoves++;
            if(numMoves>2*this.configuration.getNumMovesProtection()) {
                winner=getWinner(this.currentPlayer, getPiece(nextMove.getSource()), nextMove);
            }
            this.refreshOutput();

            // student implementation ends here
            if (winner != null) {
                System.out.println();
                System.out.println("Congratulations! ");
                System.out.printf("Winner: %s%s%s\n", winner.getColor(), winner.getName(), Color.DEFAULT);
                return winner;
            }
        }
    }

    /**
     * Get the winner of the game. If there is no winner yet, return null;
     * This method will be called every time after a player makes a move and after
     * {@link JesonMor#updateScore(Player, Piece, Move)} is called, in order to
     * check whether any {@link Player} wins.
     * If this method returns a player (the winner), then the game will exit with the winner.
     * If this method returns null, next player will be asked to make a move.
     *
     * @param lastPlayer the last player who makes a move
     * @param lastMove   the last move made by lastPlayer
     * @param lastPiece  the last piece that is moved by the player
     * @return the winner if it exists, otherwise return null
     */
    @Override
    public Player getWinner(Player lastPlayer, Piece lastPiece, Move lastMove) {
        // TODO student implementation
        Player winner=null;
        int size=this.configuration.getSize();

        if (lastMove == null) {
            if (configuration.getPlayers()[0].getScore() < configuration.getPlayers()[1].getScore()) {
                winner = configuration.getPlayers()[0];
            } else if (configuration.getPlayers()[0].getScore() > configuration.getPlayers()[1].getScore()) {
                winner = configuration.getPlayers()[1];
            } else
                winner = lastPlayer;
        }

        if(lastMove!=null) {
            if ((lastMove.getSource().x()==(size - 1) / 2) && (lastMove.getSource().y() == (size - 1) / 2)) {
                winner = lastPlayer;
            }
        }

        if(winner==null) {
            int piece = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if ((getPiece(new Place(i, j)) != null) && (getPiece(new Place(i, j)).getPlayer() != lastPlayer)) {
                        piece++;
                    }
                }
            }
            if (piece == 0) {
                winner = lastPlayer;
            }
        }
        return winner;
    }

    /**
     * Update the score of a player according to the {@link Piece} and corresponding move made by him just now.
     * This method will be called every time after a player makes a move, in order to update the corresponding score
     * of this player.
     * <p>
     * The score of a player is the cumulative score of each move he makes.
     * The score of each move is calculated with the Manhattan distance between the source and destination {@link Place}.
     * <p>
     * Student can use {@link Player#getScore()} to get the current score of a player before updating.
     * {@link Player#setScore(int)} can be used to update the score of a player.
     * <p>
     * <strong>Attention: Student should make NO assumption that the {@link Move} is valid.</strong>
     *
     * @param player the player who just makes a move
     * @param piece  the piece that is just moved
     * @param move   the move that is just made
     */
    public void updateScore(Player player, Piece piece, Move move) {
        // TODO student implementation
        int score = player.getScore();
        Move[] availableMove = getAvailableMoves(player);
        int length = availableMove.length;

        for(int i=0; i<length; i++){
            if(move.equals(availableMove[i])){
                player.setScore(score+3);
                break;
            }
        }
    }


    /**
     * Make a move.
     * This method performs moving a {@link Piece} from source to destination {@link Place} according {@link Move} object.
     * Note that after the move, there will be no {@link Piece} in source {@link Place}.
     * <p>
     * Positions of all {@link Piece}s on the gameboard are stored in {@link JesonMor#board} field as a 2-dimension array of
     * {@link Piece} objects.
     * The x and y coordinate of a {@link Place} on the gameboard are used as index in {@link JesonMor#board}.
     * E.g. {@code board[place.x()][place.y()]}.
     * If one {@link Place} does not have a piece on it, it will be null in {@code board[place.x()][place.y()]}.
     * Student may modify elements in {@link JesonMor#board} to implement moving a {@link Piece}.
     * The {@link Move} object can be considered valid on present gameboard.
     *
     * @param move the move to make
     */
    public void movePiece(@NotNull Move move) {
        // TODO student implementation
        this.board[move.getDestination().x()][move.getDestination().y()]=this.getPiece(move.getSource());
        this.board[move.getSource().x()][move.getSource().y()]=null;
    }

    /**
     * Get all available moves of one player.
     * This method is called when it is the {@link Player}'s turn to make a move.
     * It will iterate all {@link Piece}s belonging to the {@link Player} on board and obtain available moves of
     * each of the {@link Piece}s through method {@link Piece#getAvailableMoves(Game, Place)} of each {@link Piece}.
     * <p>
     * <strong>Attention: Student should make sure all {@link Move}s returned are valid.</strong>
     *
     * @param player the player whose available moves to get
     * @return an array of available moves
     */
    public @NotNull Move[] getAvailableMoves(Player player) {
        // TODO student implementation
        int size=configuration.getSize();
        Move[] availableMoves=null;
        int length1;
        int length2;

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if((board[i][j]!=null)&&(board[i][j].getPlayer()==currentPlayer)) {
                    Move[] moves = board[i][j].getAvailableMoves(this, new Place(i, j));
                    if (availableMoves == null) {
                        availableMoves=moves;
                    }
                    else {
                        length1 = availableMoves.length;

                        length2 = moves.length;
                        Move[] temp = new Move[length1 + length2];

                        System.arraycopy(availableMoves, 0, temp, 0, length1);
                        System.arraycopy(moves, 0, temp, length1, length2);

                        availableMoves = temp;
                    }
                }
            }
        }
        return availableMoves;
    }
}
