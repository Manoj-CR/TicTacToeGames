import Controllers.GameController;
import Models.*;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("The Game is On");

        Scanner sc=new Scanner(System.in);
        System.out.println("What is the Size of the game");
        int dimension = sc.nextInt();

        System.out.println("What is the no of Player");
        int noOfPlayers= sc.nextInt();

        System.out.println("Is bot Player available (y/n)");
        String isBot=sc.next();

        List<Player> players = new LinkedList<>();
        if(isBot.equals("y")) {
            noOfPlayers = noOfPlayers - 1;
            System.out.println("What is the name of the Bot");
            String botName = sc.next();
            System.out.println("What is the symbol of the Bot");
            String botSymbol = sc.next();
            System.out.println("What is the difficulty level of the Bot : " +
                    "1 - Easy, 2 - Medium , 3 - Hard");
            int botDifficultyLevel = sc.nextInt();

            //todo for bot Difficulty level from Enum
            players.add(new Bot(botName, botSymbol.charAt(0), BotDifficultyLevel.EASY));
        }
            for(int i=0;i<noOfPlayers;i++){
                System.out.println("What is the name of the player "+(i+1));
                String name=sc.next();
                System.out.println("What is the symbol of the Player"+(i+1));
                String symbol=sc.next(); // Assumption player symbol will be single character

                Player player=new Player(name,symbol.charAt(0), PlayerType.HUMAN);
                players.add(player);

            }

            GameController gameController=new GameController();
            Game game= gameController.createGame(dimension,players);

            while(gameController.getGameStatus(game)==GameStatus.INPROGRESS){
                 //ToDO
                System.out.println("Current Board");
                gameController.displayBoard(game);
                gameController.executeNextMove(game);
            }
            if(gameController.getGameStatus(game)==GameStatus.DRAW){
                System.out.println("Game is Draw");
            }else{
                System.out.println("Winner of the game: "+ gameController.
                        getWinningPlayer(game).getName());
                    gameController.displayBoard(game);
            }
        }
}
