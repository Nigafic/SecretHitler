import java.util.ArrayList;

public class Board {

    public String fascistsCount;
    private static Board board;

    public static Board getInstance() {
        if (board == null)
            board = new Board();
        return board;
    }

   private ArrayList<Law> fascistZone = new ArrayList<>();
   private ArrayList<Law> liberalZone = new ArrayList<>();

   
    public ArrayList<Law> getFascistZone() {
        return fascistZone;
    }

    public ArrayList<Law> getLiberalZone() {
        return liberalZone;
    }

}
