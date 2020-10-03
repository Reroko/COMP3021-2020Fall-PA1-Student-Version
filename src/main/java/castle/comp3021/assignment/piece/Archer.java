package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Piece;
import castle.comp3021.assignment.protocol.Place;
import castle.comp3021.assignment.protocol.Player;

import java.util.Objects;

/**
 * Archer piece that moves similar to cannon in chinese chess.
 * Rules of move of Archer can be found in wikipedia (https://en.wikipedia.org/wiki/Xiangqi#Cannon).
 * <p>
 * <strong>Attention: If you want to implement Archer as the bonus task, you should remove "{@code throw new
 * UnsupportedOperationException();}" in the constructor of this class.</strong>
 *
 * @see <a href='https://en.wikipedia.org/wiki/Xiangqi#Cannon'>Wikipedia</a>
 */
public class Archer extends Piece {
    public Archer(Player player) {
        super(player);
        //throw new UnsupportedOperationException(); // remove this line if you plan to implement Archer
    }

    @Override
    public char getLabel() {
        return 'A';
    }

    /**
     * Returns an array of moves that are valid given the current place of the piece.
     * Given the {@link Game} object and the {@link Place} that current knight piece locates, this method should
     * return ALL VALID {@link Move}s according to the current {@link Place} of this knight piece.
     * All the returned {@link Move} should have source equal to the source parameter.
     * <p
     * Hint: you should consider corner cases when the {@link Move} is not valid on the gameboard.
     * Several tests are provided and your implementation should pass them.
     * <p>
     * <strong>Attention: Student should make sure all {@link Move}s returned are valid.</strong>
     *
     * @param game   the game object
     * @param source the current place of the piece
     * @return an array of available moves
     */
    @Override
    public Move[] getAvailableMoves(Game game, Place source) {
        // TODO student implementation

        int x=source.x();
        int y=source.y();
        Move[] validMoves=null;

        int count1=0;
        for(int i=x-1;i>=0;i--) {
            if ((count1 == 0) && (game.getPiece(i, y) == null)) {
                if (validMoves == null) {
                    validMoves = new Move[]{new Move(source, new Place(i, y))};
                } else {
                    Move[] temp = new Move[validMoves.length + 1];
                    Move[] move = new Move[]{new Move(source, new Place(i, y))};
                    System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                    System.arraycopy(move, 0, temp, validMoves.length, 1);
                    validMoves = temp;
                }
            } else if ((count1 == 0) && (game.getPiece(i, y) != null)) {
                count1++;
            } else if ((count1 == 1) && (game.getPiece(i, y)!=null)){
                if(game.getPiece(i,y).getPlayer()== game.getPiece(source).getPlayer()){
                break;
                } else if (game.getPiece(i, y).getPlayer() != game.getPiece(source).getPlayer()) {
                    if (validMoves == null) {
                        validMoves = new Move[]{new Move(source, new Place(i, y))};
                    } else {
                        Move[] temp = new Move[validMoves.length + 1];
                        Move[] move = new Move[]{new Move(source, new Place(i, y))};
                        System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                        System.arraycopy(move, 0, temp, validMoves.length, 1);
                        validMoves = temp;
                        break;
                    }
                }
            }
        }

        int count2=0;
        for(int i=x+1;i<game.getConfiguration().getSize();i++) {
            if ((count2 == 0) && (game.getPiece(i, y) == null)) {
                if (validMoves == null) {
                    validMoves = new Move[]{new Move(source, new Place(i, y))};
                } else {
                    Move[] temp = new Move[validMoves.length + 1];
                    Move[] move = new Move[]{new Move(source, new Place(i, y))};
                    System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                    System.arraycopy(move, 0, temp, validMoves.length, 1);
                    validMoves = temp;
                }
            } else if ((count2 == 0) && (game.getPiece(i, y) != null)) {
                count2++;
            } else if ((count2 == 1) && (game.getPiece(i, y)!=null)){
                if(game.getPiece(i,y).getPlayer()== game.getPiece(source).getPlayer()){
                    break;
                } else if (game.getPiece(i, y).getPlayer() != game.getPiece(source).getPlayer()) {
                    if (validMoves == null) {
                        validMoves = new Move[]{new Move(source, new Place(i, y))};
                    } else {
                        Move[] temp = new Move[validMoves.length + 1];
                        Move[] move = new Move[]{new Move(source, new Place(i, y))};
                        System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                        System.arraycopy(move, 0, temp, validMoves.length, 1);
                        validMoves = temp;
                        break;
                    }
                }
            }
        }

        int count3=0;
        for(int i=y-1;i>=0;i--) {
            if ((count3 == 0) && (game.getPiece(x, i) == null)) {
                if (validMoves == null) {
                    validMoves = new Move[]{new Move(source, new Place(x, i))};
                } else {
                    Move[] temp = new Move[validMoves.length + 1];
                    Move[] move = new Move[]{new Move(source, new Place(x, i))};
                    System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                    System.arraycopy(move, 0, temp, validMoves.length, 1);
                    validMoves = temp;
                }
            } else if ((count3 == 0) && (game.getPiece(x, i) != null)) {
                count3++;
            } else if ((count3 == 1) && (game.getPiece(x, i)!=null)){
                if(game.getPiece(x,i).getPlayer()== game.getPiece(source).getPlayer()){
                    break;
                } else if (game.getPiece(x, i).getPlayer() != game.getPiece(source).getPlayer()) {
                    if (validMoves == null) {
                        validMoves = new Move[]{new Move(source, new Place(x, i))};
                    } else {
                        Move[] temp = new Move[validMoves.length + 1];
                        Move[] move = new Move[]{new Move(source, new Place(x, i))};
                        System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                        System.arraycopy(move, 0, temp, validMoves.length, 1);
                        validMoves = temp;
                        break;
                    }
                }
            }
        }

        int count4=0;
        for(int i=y+1;i<game.getConfiguration().getSize();i++) {
            if ((count4 == 0) && (game.getPiece(x, i) == null)) {
                if (validMoves == null) {
                    validMoves = new Move[]{new Move(source, new Place(x, i))};
                } else {
                    Move[] temp = new Move[validMoves.length + 1];
                    Move[] move = new Move[]{new Move(source, new Place(x, i))};
                    System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                    System.arraycopy(move, 0, temp, validMoves.length, 1);
                    validMoves = temp;
                }
            } else if ((count4 == 0) && (game.getPiece(x, i) != null)) {
                count4++;
            } else if ((count4 == 1) && (game.getPiece(x, i)!=null)){
                if(game.getPiece(x,i).getPlayer()== game.getPiece(source).getPlayer()){
                    break;
                } else if (game.getPiece(x, i).getPlayer() != game.getPiece(source).getPlayer()) {
                    if (validMoves == null) {
                        validMoves = new Move[]{new Move(source, new Place(x, i))};
                    } else {
                        Move[] temp = new Move[validMoves.length + 1];
                        Move[] move = new Move[]{new Move(source, new Place(x, i))};
                        System.arraycopy(validMoves, 0, temp, 0, validMoves.length);
                        System.arraycopy(move, 0, temp, validMoves.length, 1);
                        validMoves = temp;
                        break;
                    }
                }
            }
        }



        return validMoves;
    }
}