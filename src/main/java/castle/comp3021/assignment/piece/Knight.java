package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.*;

/**
 * Knight piece that moves similar to knight in chess.
 * Rules of move of Knight can be found in wikipedia (https://en.wikipedia.org/wiki/Knight_(chess)).
 *
 * @see <a href='https://en.wikipedia.org/wiki/Knight_(chess)'>Wikipedia</a>
 */
public class Knight extends Piece {
    public Knight(Player player) {
        super(player);
    }

    @Override
    public char getLabel() {
        return 'K';
    }

    /**
     * Returns an array of moves that are valid given the current place of the piece.
     * Given the {@link Game} object and the {@link Place} that current knight piece locates, this method should
     * return ALL VALID {@link Move}s according to the current {@link Place} of this knight piece.
     * All the returned {@link Move} should have source equal to the source parameter.
     * <p>
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

        Move[] move = new Move[8];
        int count=0;
        Configuration conf = game.getConfiguration();
        var size = conf.getSize();

        if((source.x()+1<size)&&(source.y()+2<size)){
            Place destination = new Place(source.x()+1, source.y()+2);

            if(game.getPiece(destination)==null){
                move[0] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[0]=null;
            }
            else{
                move[0] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()+1<size)&&(source.y()-2>=0)){
            Place destination = new Place(source.x()+1, source.y()-2);

            if(game.getPiece(destination)==null){
                move[1] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[1]=null;
            }
            else{
                move[1] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()-1>=0)&&(source.y()+2<size)){
            Place destination = new Place(source.x()-1, source.y()+2);

            if(game.getPiece(destination)==null){
                move[2] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[2]=null;
            }
            else{
                move[2] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()-1>=0)&&(source.y()-2>=0)){
            Place destination = new Place(source.x()-1, source.y()-2);

            if(game.getPiece(destination)==null){
                move[3] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[3]=null;
            }
            else{
                move[3] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()+2<size)&&(source.y()+1<size)){
            Place destination = new Place(source.x()+2, source.y()+1);

            if(game.getPiece(destination)==null){
                move[4] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[4]=null;
            }
            else{
                move[4] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()+2<size)&&(source.y()-1>=0)){
            Place destination = new Place(source.x()+2, source.y()-1);

            if(game.getPiece(destination)==null){
                move[5] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[5]=null;
            }
            else{
                move[5] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()-2>=0)&&(source.y()+1<size)){
            Place destination = new Place(source.x()-2, source.y()+1);

            if(game.getPiece(destination)==null){
                move[6] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[6]=null;
            }
            else{
                move[6] = new Move(source, destination);
                count++;
            }
        }

        if((source.x()-2>=0)&&(source.y()-1>=0)){
            Place destination = new Place(source.x()-2, source.y()-1);

            if(game.getPiece(destination)==null){
                move[7] = new Move(source,destination);
                count++;
            }
            else if(game.getPiece(destination).getPlayer()==game.getPiece(source).getPlayer()) {
                move[7]=null;
            }
            else{
                move[7] = new Move(source, destination);
                count++;
            }
        }

        Move[] validMove=new Move[count];

        for(int i=0;i<=7;i++){
            if(move[i]!=null){
                validMove[count-1]=move[i];
                count--;
            }
        }

        return validMove;
    }
}
