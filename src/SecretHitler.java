import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SecretHitler {
    public static void main (String[] args){
    init();
    }

    private static void init() {

        int  numberOfPlayer;
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

        System.out.println(playerRoleList.toString());
        System.out.println(playerList.toString());

        Player player1 = new Player(PlayerRole.FASCIST);
        Player player2 = new Player(PlayerRole.LIBERAL);
        Player player3 = new Player(PlayerRole.FASCISTHitler);



        //todo singleton
        Board board = Board.getInstance();

        System.out.println(player1.toString());
        System.out.println(player2.toString());
        System.out.println(player3.toString());

        ArrayList<Law> laws = SecretHitler.LawListFormer(board);

        SecretHitler.lawAdoption(player1, player2, laws, board);
        SecretHitler.lawAdoption(player2, player3, laws, board);
        SecretHitler.lawAdoption(player1, player3, laws, board);
        SecretHitler.lawAdoption(player3, player1, laws, board);
        SecretHitler.lawAdoption(player1, player2, laws, board);
        SecretHitler.lawAdoption(player2, player3, laws, board);
        SecretHitler.lawAdoption(player1, player3, laws, board);
        SecretHitler.lawAdoption(player3, player1, laws, board);
        SecretHitler.lawAdoption(player1, player2, laws, board);
        SecretHitler.lawAdoption(player2, player3, laws, board);
        SecretHitler.lawAdoption(player1, player3, laws, board);
        SecretHitler.lawAdoption(player3, player1, laws, board);


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

