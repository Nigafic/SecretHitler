import java.util.ArrayList;
import java.util.Scanner;

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

    public void getActionFascistLaw (int numberOfLawForHitlerChancellorWin, Player president, ArrayList<Player> players, ArrayList <Law> laws) {
        Scanner scanner = new Scanner(System.in);

        if (numberOfLawForHitlerChancellorWin == 1 ){
            switch (this.getFascistZone().size()){
                case 1:
                    System.out.println("Это должен видеть только Президент");
                    scanner.nextInt();

                    president.presidentCheckLaw(laws);
                    break;
                case 2:
                    //todo kill player
                    break;
            }
        }
        if (numberOfLawForHitlerChancellorWin == 2){
            switch (this.getFascistZone().size()){
                case 1:
                    System.out.println("Это должен видеть только Президент");
                    scanner.nextInt();

                    president.presidentCheckPartyMember(players);
                    break;
                case 2:
                    //todo choice president

                    break;
            }
        }
        if (numberOfLawForHitlerChancellorWin == 3){
            switch (this.getFascistZone().size()){
                case 1:
                case 2:
                    System.out.println("Это должен видеть только Президент");
                    scanner.nextInt();

                    president.presidentCheckPartyMember(players);
                    break;
                case 3:
                    //todo choice president
                    break;
            }
        }
    }

}
