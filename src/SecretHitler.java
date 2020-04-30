import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SecretHitler {
    public static void main (String[] args){
    init();
    }

    private static void init() {

        boolean finGame = false;

        int numberOfPlayers;                //колличество игроков

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите колличество игроков " );
        numberOfPlayers = scanner.nextInt();                     //считываем запрос

        int numberOfLawForHitlerChancellorWin = SecretHitler.getNumberOfLawForHitlerChancellorWin(numberOfPlayers);

        ArrayList<Player> playerList = SecretHitler.roleGeneratorShuffle(numberOfPlayers); //генерируются игроки и раздаются рандомные роли

        int [] numberOfThePresidentAndChancellor = {(int)(Math.random()*numberOfPlayers),5};       //номер Игрока Президента и Канцлера


        //todo good singleton
        Board board = Board.getInstance();
        ArrayList<Law> laws = SecretHitler.lawListFormer(board); //первая генерация колоды законов

        while (!finGame){

            if(board.getFascistZone().size()==(numberOfLawForHitlerChancellorWin+3))
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

            numberOfThePresidentAndChancellor = SecretHitler.presidentElection(playerList, numberOfPlayers, numberOfThePresidentAndChancellor);

            if (numberOfThePresidentAndChancellor[0] == numberOfPlayers-1){
                numberOfThePresidentAndChancellor[0] = 0;
            }else {
                numberOfThePresidentAndChancellor[0]++;
            }
            System.out.println(numberOfThePresidentAndChancellor[1]);

            if((board.getFascistZone().size()>=numberOfLawForHitlerChancellorWin && playerList.get(numberOfThePresidentAndChancellor[1]).getRole() == PlayerRole.FASCISTHitler))
            {
                finGame = true;
                System.out.println("Фашисты победили!");
                break;
            }


            SecretHitler.lawAdoption(playerList.get(numberOfThePresidentAndChancellor[0]), playerList.get(numberOfThePresidentAndChancellor[1]),playerList, laws, board, numberOfLawForHitlerChancellorWin);
        }

    }

    public static void lawAdoption (Player president, Player chancellor,ArrayList<Player> players, ArrayList<Law> laws, Board board, int numberOfLawForHitlerChancellorWin) {
        if (laws.size() < 3) {
            laws = SecretHitler.lawListFormer(board);
        }
        president.getLawsPresident(laws);
        president.presidentChoice(chancellor);
        System.out.println(chancellor.getHandChancellor().toString());
        chancellor.chancellorChoice(board);
        board.getActionFascistLaw(numberOfLawForHitlerChancellorWin, president, players, laws);


        //todo all print function
        System.out.println(board.getFascistZone().toString());
        System.out.println(board.getLiberalZone().toString()+"\n");
    }


    //генерирует и мешает роли игроков
    public static ArrayList<Player> roleGeneratorShuffle (int numberOfPlayers) {

        ArrayList<Player> playerList = new ArrayList<>(numberOfPlayers);                 //список Игроков
        ArrayList <PlayerRole> playerRoleList = new ArrayList<>(numberOfPlayers);        //Список Ролей (позже распределиться по Игрокам)


        //в зависимости от колличества игроков распределяется различное колличество ролей
        //5-6 Гитлер и фашист
        //7-8 Гитлер и 2 фашиста
        //9-10 Гитлер и 3 фашиста
        switch (numberOfPlayers){
            case 5:
                playerRoleList.add(PlayerRole.FASCISTHitler);
                playerRoleList.add(PlayerRole.FASCIST);
                for (int i = 0; i<3;i++)
                    playerRoleList.add(PlayerRole.LIBERAL);
                Collections.shuffle(playerRoleList);
                for (int i = 0; i<numberOfPlayers; i++) {
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
                for (int i = 0; i<numberOfPlayers; i++) {
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
                for (int i = 0; i<numberOfPlayers; i++) {
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
                for (int i = 0; i<numberOfPlayers; i++) {
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
                for (int i = 0; i<numberOfPlayers; i++) {
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
                for (int i = 0; i<numberOfPlayers; i++) {
                    Player player = new Player(playerRoleList.get(i));
                    playerList.add(player);
                }
                break;
            default:
                System.out.println("Неверное колличество игроков");
        }
        return playerList;
    }

    public static int getNumberOfLawForHitlerChancellorWin (int numberOfPlayers) {

        switch (numberOfPlayers){
            case 5:
            case 6:
                numberOfPlayers = 1;
                break;
            case 7:
            case 8:
                numberOfPlayers = 2;
                break;
            case 9:
            case 10:
                numberOfPlayers = 3;
                break;
        }

        return numberOfPlayers;
    }

    //Генерирует колоду законов
    public static ArrayList<Law> lawListFormer(Board board ){
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
         numberOfThePresidentAndChancellor[1] = scanner.nextInt()-1;

         while((numberOfThePresidentAndChancellor[1]) == numberOfThePresidentAndChancellor[0]) {
             System.out.println("Президент не может быть канцлером !");
             System.out.println("Президент, выберите Канслера (номер игрока от 1 до "+numberOfPlayer+")");
             numberOfThePresidentAndChancellor[1] = scanner.nextInt()-1;
         }
             Player chancellor = players.get(numberOfThePresidentAndChancellor[1]);
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

         if (ja<nein){
             System.out.println("Президент будет переизбран");
             if (numberOfThePresidentAndChancellor[0] == numberOfPlayer-1){
                 numberOfThePresidentAndChancellor[0] = 0;
             }else {
                 numberOfThePresidentAndChancellor[0]++;
             }
             numberOfThePresidentAndChancellor = SecretHitler.presidentElection(players,numberOfPlayer,numberOfThePresidentAndChancellor);

         }

         return numberOfThePresidentAndChancellor;
     }

    }

