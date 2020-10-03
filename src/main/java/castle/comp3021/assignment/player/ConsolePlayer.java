package castle.comp3021.assignment.player;

import castle.comp3021.assignment.protocol.*;
import com.sun.tools.jconsole.JConsoleContext;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * The player that makes move according to user input from console.
 */
public class ConsolePlayer extends Player {
    public ConsolePlayer(String name, Color color) {
        super(name, color);
    }

    public ConsolePlayer(String name) {
        this(name, Color.GREEN);
    }

    /**
     * Choose a move from available moves.
     * This method will be called by {@link Game} object to get the move that the player wants to make when it is the
     * player's turn.
     * <p>
     * {@link ConsolePlayer} returns a move according to user's input in the console.
     * The console input format should conform the format described in the assignment description.
     * (e.g. {@literal a1->b3} means move the {@link Piece} at {@link Place}(x=0,y=0) to {@link Place}(x=1,y=2))
     * Note that in the {@link Game}.board, the index starts from 0 in both x and y dimension, while in the console
     * display, x dimension index starts from 'a' and y dimension index starts from 1.
     * <p>
     * Hint: be sure to handle invalid input to avoid invalid {@link Move}s.
     * <p>
     * <strong>Attention: Student should make sure the {@link Move} returned is valid.</strong>
     * <p>
     * <strong>Attention: {@link Place} object uses integer as index of x and y-axis, both starting from 0 to
     * facilitate programming.
     * This is VERY different from the coordinate used in console display.</strong>
     *
     * @param game           the current game object
     * @param availableMoves available moves for this player to choose from.
     * @return the chosen move
     */
    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        // TODO student implementation
        while(true) {
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            char[] c = str.toCharArray();

            int sEnd=0;
            int dStart=0;

            for(int i=0;i<c.length;i++){
                if(c[i]=='-'){
                    sEnd=i-1;
                    dStart=i+2;
                }
            }
            if((sEnd==0)||(dStart==0)||(c[dStart-1]!='>')){
                System.out.println("invalid input");
                System.out.println("invalid input");
                continue;
            }

            int x1 = (int) c[0] - 97;
            int x2 = (int) c[dStart] - 97;
            int y1=0;
            int y2=0;

            if(sEnd==1){
                y1=c[1]-49;
            }
            else{
                y1=10*(c[1]-49)+(c[2]-49);
            }

            if(dStart==c.length-2){
                y2=c[c.length-1]-49;
            }
            else{
                y2=10*(c[c.length-2]-49)+(c[c.length-1]-49);
            }

            int size=game.getConfiguration().getSize();

            if((x1<0)||(x1>=size)||(x2<0)||(x2>=size)||(y1<0)||(y1>=size)||(y2<0)||(y2>=size)) {
                System.out.println("invalid source");
                continue;
            }

            Place resource = new Place(x1, y1);
            Place destination = new Place(x2, y2);

            if((game.getNumMoves()<2*game.getConfiguration().getNumMovesProtection())&&(game.getPiece(destination)!=null)){
                System.out.println("protected");
                continue;
            }

            Move move = new Move(resource, destination);

            int length = availableMoves.length;

            for(int i=0; i<length;i++) {
                if (move.equals(availableMoves[i])) {
                    return move;
                }
            }

            System.out.println("invalid move");
        }
    }
}
