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

        int numberOfPlayer;             //колличество игроков
        int numberOfThePresident;       //номер Игрока Президента
        int numberOfTheChancellor;      //номер Игрока Канцлера

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите колличество игроков " );
        numberOfPlayer = scanner.nextInt();                     //считываем запрос

        ArrayList<Player> playerList = new ArrayList<>(numberOfPlayer);                 //список Игроков
        ArrayList <PlayerRole> playerRoleList = new ArrayList<>(numberOfPlayer);        //Список Ролей (позже распределиться по Игрокам)
        Player president = new Player();                                                //Президент
        Player chancellor = new Player();                                               //Канцлер



        //в зависимости от колличества игроков распределяется различное колличество ролей
        //5-6 Гитлер и фашист
        //7-8 Гитлер и 2 фашиста
        //9-10 Гитлер и 3 фашиста
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

        numberOfThePresident = (int)(Math.random()*numberOfPlayer);
        numberOfTheChancellor = 1;


        //todo singleton
        Board board = Board.getInstance();
        ArrayList<Law> laws = SecretHitler.LawListFormer(board); //первая генерация колоды законов

        while (!finGame){

            numberOfThePresident = SecretHitler.presidentElection(playerList, numberOfPlayer, numberOfThePresident, numberOfTheChancellor);

            if (numberOfThePresident == numberOfPlayer-1){
                numberOfThePresident = 0;
            }else {
                numberOfThePresident++;
            }
            if((board.getFascistZone().size()>=2 &&playerList.get(numberOfThePresident).getRole() == PlayerRole.FASCISTHitler)||board.getFascistZone().size()==6)
            {
                finGame = true;
                System.out.println("Фашисты победили!");
            }
            if(board.getLiberalZone().size()==5)
            {
                finGame = true;
                System.out.println("Либерасты победили!");
            }
            SecretHitler.lawAdoption(president, chancellor, laws, board);
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


    //Генерирует колоду законов
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

     public static int presidentElection (ArrayList <Player> players,int numberOfPlayer, int numberOfThePresident, int numberOfTheChancellor ) {
        Scanner scanner = new Scanner(System.in);
        int playerChoice;
        int ja = 0;
        int nein= 0;
         Player president = players.get(numberOfThePresident);
         System.out.println(numberOfThePresident);
         System.out.println(president);


         System.out.println("Президент, выберите Канслера (номер игрока от 1 до "+numberOfPlayer+")");
         numberOfTheChancellor = scanner.nextInt();
         Player chancellor = players.get(numberOfTheChancellor-1);
         System.out.println(chancellor);


         for (int i = 0; i < numberOfPlayer; i++) {
             System.out.println("Игрок "+(i+1)+ ", вы за данного кандидата в президенты ?\n 1.Да.\n2.Нет.\n");
             playerChoice = scanner.nextInt();
             if(playerChoice == 1){
                 players.get(i).setVoiсe(Voiсe.YES);
                 ja++;
             }else {
                 players.get(i).setVoiсe(Voiсe.NO);
                 nein++;
             }
         }

         if (nein<ja){

         }else {
             System.out.println("Президент будет переизбран");
             if (numberOfThePresident == numberOfPlayer-1){
                 numberOfThePresident = 0;
             }else {
                 numberOfThePresident++;
             }
             president = players.get(numberOfThePresident);
             numberOfThePresident = SecretHitler.presidentElection(players,numberOfPlayer,numberOfThePresident,numberOfTheChancellor);
         }

         return numberOfThePresident;
     }

    }

