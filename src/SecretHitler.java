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

        int [] numberOfThePresidentAndChancellor = {(int)(Math.random()*numberOfPlayer),1};       //номер Игрока Президента и Канцлера


        //todo singleton
        Board board = Board.getInstance();
        ArrayList<Law> laws = SecretHitler.LawListFormer(board); //первая генерация колоды законов

        while (!finGame){

            //todo fix it

            numberOfThePresidentAndChancellor = SecretHitler.presidentElection(playerList, numberOfPlayer, numberOfThePresidentAndChancellor);

            if (numberOfThePresidentAndChancellor[0] == numberOfPlayer-1){
                numberOfThePresidentAndChancellor[0] = 0;
            }else {
                numberOfThePresidentAndChancellor[0]++;
            }
            System.out.println(numberOfThePresidentAndChancellor[1]);
            if((board.getFascistZone().size()>=2 && playerList.get(numberOfThePresidentAndChancellor[1]-1).getRole() == PlayerRole.FASCISTHitler)||board.getFascistZone().size()==6)
            {
                finGame = true;
                System.out.println("Фашисты победили!");
                break;
            }
            if(board.getLiberalZone().size()==5)
            {
                finGame = true;
                System.out.println("Либерасты победили!");
                break;
            }

            SecretHitler.lawAdoption(playerList.get(numberOfThePresidentAndChancellor[0]), playerList.get(numberOfThePresidentAndChancellor[1]), laws, board);
        }

    }

    public static void lawAdoption (Player president, Player chancellor, ArrayList<Law> laws, Board board) {
        if (laws.size() < 3) {
            laws = SecretHitler.LawListFormer(board);
        }
        president.getLawsPresident(laws);
        president.presidentChoice(chancellor);
        System.out.println(chancellor.getHandChancellor().toString());
        chancellor.chancellorChoice(board);


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

     public static int[] presidentElection (ArrayList <Player> players,int numberOfPlayer, int []numberOfThePresidentAndChancellor ) {
        Scanner scanner = new Scanner(System.in);
        int playerChoice;
        int ja = 0;
        int nein= 0;
         Player president = players.get(numberOfThePresidentAndChancellor[0]);
         System.out.println(numberOfThePresidentAndChancellor[0]);
         System.out.println(president);


         System.out.println("Президент, выберите Канслера (номер игрока от 1 до "+numberOfPlayer+")");
         numberOfThePresidentAndChancellor[1] = scanner.nextInt();
         Player chancellor = players.get(numberOfThePresidentAndChancellor[1]-1);
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
             if (numberOfThePresidentAndChancellor[0] == numberOfPlayer-1){
                 numberOfThePresidentAndChancellor[0] = 0;
             }else {
                 numberOfThePresidentAndChancellor[0]++;
             }
             president = players.get(numberOfThePresidentAndChancellor[0]);
             numberOfThePresidentAndChancellor = SecretHitler.presidentElection(players,numberOfPlayer,numberOfThePresidentAndChancellor);
         }

         return numberOfThePresidentAndChancellor;
     }

    }

