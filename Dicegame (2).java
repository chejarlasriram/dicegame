import java.util.Random;
import java.util.Scanner;

class Player {
    int id;
    String name;
    int score;

    Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0; // initially score = 0
    }
}

public class DiceGame {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static Player[] players = new Player[10]; // max 10 players
    static int playerCount = 0;

    // Register a new player
    public static void addPlayer() {
        if (playerCount >= players.length) {
            System.out.println("Player limit reached!");
            return;
        }
        System.out.print("Enter Player Name: ");
        String name = sc.next();
        players[playerCount] = new Player(playerCount + 1, name);
        System.out.println("Player " + name + " added with ID: " + (playerCount + 1));
        playerCount++;
    }

    // Roll the dice
    public static void rollDice() {
        if (playerCount == 0) {
            System.out.println("No players registered!");
            return;
        }
        for (int i = 0; i < playerCount; i++) {
            int dice = rand.nextInt(6) + 1; // 1-6
            players[i].score += dice;
            System.out.println(players[i].name + " rolled " + dice + " | Total Score: " + players[i].score);
        }
    }

    // Find winner (highest score)
    public static void findWinner() {
        if (playerCount == 0) {
            System.out.println("No players to check.");
            return;
        }
        Player winner = players[0];
        for (int i = 1; i < playerCount; i++) {
            if (players[i].score > winner.score) {
                winner = players[i];
            }
        }
        System.out.println("🏆 Winner is: " + winner.name + " with Score: " + winner.score);
    }

    // Display scores in sorted order (Bubble Sort)
    public static void displayScores() {
        if (playerCount == 0) {
            System.out.println("No players registered.");
            return;
        }
        Player[] sorted = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) sorted[i] = players[i];

        // Bubble Sort (descending order)
        for (int i = 0; i < playerCount - 1; i++) {
            for (int j = 0; j < playerCount - i - 1; j++) {
                if (sorted[j].score < sorted[j + 1].score) {
                    Player temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }

        System.out.println("📋 Scores (Highest to Lowest):");
        for (int i = 0; i < playerCount; i++) {
            System.out.println(sorted[i].id + " - " + sorted[i].name + " | Score: " + sorted[i].score);
        }
    }

    // Reset all scores
    public static void resetGame() {
        for (int i = 0; i < playerCount; i++) {
            players[i].score = 0;
        }
        System.out.println("Game reset successfully!");
    }

    // Remove a player
    public static void removePlayer() {
        if (playerCount == 0) {
            System.out.println("No players registered.");
            return;
        }
        System.out.print("Enter Player ID to remove: ");
        int id = sc.nextInt();
        int index = -1;
        for (int i = 0; i < playerCount; i++) {
            if (players[i].id == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Player not found!");
            return;
        }
        for (int i = index; i < playerCount - 1; i++) {
            players[i] = players[i + 1];
        }
        players[playerCount - 1] = null;
        playerCount--;
        System.out.println("Player removed successfully!");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n🎲 Dice Game Menu 🎲");
            System.out.println("1. Add Player");
            System.out.println("2. Roll Dice");
            System.out.println("3. Find Winner");
            System.out.println("4. Display Scores");
            System.out.println("5. Reset Game");
            System.out.println("6. Remove Player");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addPlayer();
                case 2 -> rollDice();
                case 3 -> findWinner();
                case 4 -> displayScores();
                case 5 -> resetGame();
                case 6 -> removePlayer();
                case 7 -> System.out.println("Exiting... Thank you for playing!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }
}
