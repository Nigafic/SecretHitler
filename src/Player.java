import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private static byte playerCount = 1;
    private Scanner scan = new Scanner(System.in);
    private String name = "Player " + playerCount ;
    private PlayerRole role;
    private Voiсe voiсe = Voiсe.NO;
    private static int numberOfTheChancellor;
    private static int numberOfThePresident;


    public Player( PlayerRole role ) {
        playerCount++;
        this.role = role;
    }

    public Player () {

    }

    public Voiсe getVoiсe() {
        return voiсe;
    }

    public void setVoiсe(Voiсe voiсe) {
        this.voiсe = voiсe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    private ArrayList<Law> handPresident = new ArrayList<Law>();


    private ArrayList<Law> handChancellor  = new ArrayList<Law>();

    public ArrayList<Law> getHandPresident() {
        return handPresident;
    }

    public ArrayList<Law> getHandChancellor() {
        return handChancellor;
    }

    public ArrayList <Law> getLawsPresident(ArrayList <Law> laws){

        for(int i = 0; i<3; i++){
            System.out.println("Колода "+laws);
            this.handPresident.add(laws.get(0));
            laws.remove(0);
            System.out.println(this.handPresident);
        }

        return laws;
    }

    public void presidentChoice (Player player){
        System.out.println("Выбери лишний закон: \n" +
                "1."+this.handPresident.get(0).toString()+".\n"+
                "2."+this.handPresident.get(1).toString()+".\n"+
                "3."+this.handPresident.get(2).toString()+".");
        int choice = this.scan.nextInt();
        this.handPresident.remove(choice-1);
        player.handChancellor= this.handPresident;
    }

    public void chancellorChoice (Board board ){
        System.out.println("Выбери лишний закон: \n" +
                "1."+this.handChancellor.get(0).toString()+".\n"+
                "2."+this.handChancellor.get(1).toString()+".");
        int choice = this.scan.nextInt();
        this.handChancellor.remove(choice-1);

        if (this.handChancellor.get(0) == Law.FASCIST){
            board.getFascistZone().add(this.handChancellor.get(0));
        }
        else{
            board.getLiberalZone().add(this.handChancellor.get(0));
        }

        this.handChancellor.clear();
        this.handPresident.clear();
    }

    public void presidentCheckLaw (ArrayList <Law> laws) {
        for(int i = 0; i<3; i++){
            //System.out.println("Колода "+laws);
            this.handPresident.add(laws.get(0));
        }
        System.out.println(this.handPresident);
        this.handPresident.removeAll(this.handPresident);
    }

    public void presidentCheckPartyMember (ArrayList <Player> players) {
        System.out.println("Выберите номер игрока, которого хотите проверить. ");
        Scanner scanner = new Scanner(System.in);
        int presidentChoice = scanner.nextInt();

        System.out.println(players.get(presidentChoice-1).getRole().getParty());
    }

    public void presidentSetNextPresident (ArrayList <Player> players,ArrayList<Law> laws,int numberOfWinHitler) {
        System.out.println("Выберите номер игрока, которого хотите сделать президентом. ");
        Scanner scanner = new Scanner(System.in);
        int presidentChoice = scanner.nextInt() - 1;

        Player president = players.get(presidentChoice);
        System.out.println("Президент, выберите Канслера (номер игрока от 1 до "+players.size()+")");
        int chancellor = scanner.nextInt()-1;

        while((chancellor) == presidentChoice) {
            System.out.println("Президент не может быть канцлером !");
            System.out.println("Президент, выберите Канслера (номер игрока от 1 до "+players.size()+")");
            chancellor = scanner.nextInt()-1;

            Player  Chancellor = players.get(chancellor);
            System.out.println(Chancellor);
        }
        SecretHitler.lawAdoption(president,players.get(chancellor),players,laws,Board.getInstance(),numberOfWinHitler);

    }


}
