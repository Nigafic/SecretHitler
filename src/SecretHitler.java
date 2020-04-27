import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class SecretHitler {
    public static void main (String[] args){
    init();
    }

    private static void init() {

        boolean finGame = false;

        int numberOfPlayer;
        int numberOfTheChancellor;
        int numberOfThePresident;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите колличество игроков " );
        numberOfPlayer = scanner.nextInt();

        ArrayList<Player> playerList = new ArrayList<>(numberOfPlayer);
        ArrayList <PlayerRole> playerRoleList = new ArrayList<>(numberOfPlayer);


        switch (numberOfPlayer){
            case 5:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<3;i++)
                playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayer; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            case 6:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<4;i++)
                    playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayer; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            case 7:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<4;i++)
                    playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayer; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            case 8:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<5;i++)
                    playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayer; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            case 9:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                playerRoleList.add(PlayerRole.FASCIST);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<5;i++)
                    playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayer; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            case 10:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                playerRoleList.add(PlayerRole.FASCIST);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<6;i++)
                    playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayer; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            default:
                System.out.println("Неверное колличество игроков");
        }

        Player president = playerList.get((int) (Math.random()*numberOfPlayer));
        System.out.println(president);
        scanner.nextInt();

        System.out.println("Президент, выберите Канслера (номер игрока от 1 до "+numberOfPlayer+")");
        numberOfTheChancellor = scanner.nextInt();
        Player chancellor = playerList.get(numberOfTheChancellor-1);
        System.out.println(chancellor);
        scanner.nextInt();

        System.out.println(playerRoleList.toString());
        System.out.println(playerList.toString());

        //todo singleton
        Board board = Board.getInstance();

        ArrayList<Law> laws = SecretHitler.LawListFormer(board);



        while (!finGame){
            SecretHitler.lawAdoption(president, chancellor, laws, board);
            if((board.getFascistZone().size()>3 &&chancellor.getRole() == PlayerRole.FASCISTHitler)||board.getFascistZone().size()==6)
            {
                finGame = true;
                System.out.println("Фашисты победили!");
            }
            if(board.getLiberalZone().size()==5)
            {
                finGame = true;
                System.out.println("Либерасты победили!");
            }
        }

    }

    public static void lawAdoption (Player player1, Player player2, ArrayList<Law> laws, Board board) {
        if (laws.size() < 3) {
            laws = SecretHitler.LawListFormer(board);
        }
        player1.getLawsPresident(laws);
        player1.presidentChoice(player2);
        System.out.println(player2.getHandChancellor().toString());
        player2.chancellorChoice(board);


        //todo all print function
        System.out.println(board.getFascistZone().toString());
        System.out.println(board.getLiberalZone().toString()+"\n");
    }

    public static ArrayList<Law> LawListFormer (Board board ){
        ArrayList<Law> laws = new ArrayList<>();
        {
            for ( int i = 0;i < 11 - board.getFascistZone().size(); i++){
                laws.add(Law.FASCIST);
            }
            for (int i = 0;i<6 - board.getLiberalZone().size(); i++) {
                laws.add(Law.LIBERAL);
            }
        }
        Collections.shuffle(laws);
        return laws;
    }

    }

