package libs.cardgame;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DemoCardGameApp.run();
    }
}
class DemoCardGameApp {
    public static void run() {
        System.out.println("dial 1 for card deck ; or dial 2 to pick cards from deck");
        Scanner kb = new Scanner(System.in);
        int dial = kb.nextInt();

        switch (dial) {
            case 1 -> createDeck();
            case 2 -> pickCards();
            default -> System.out.println("invalid operation");
        }
    }

    public static void createDeck() {
        for (Card card : Card.newDeck())
            System.out.println(card.toString());
    }

    public static void pickCards() {
        Random random = new Random();
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator(random);
        System.out.println("insert value of how much card you want: ");
        Scanner kb = new Scanner(System.in);
        int value = kb.nextInt();

        while (value-- > 0) {
            Card card = randomCardGenerator.create();

            System.out.printf("card is: %s%n", card.toString());
            System.out.printf("%d is card type's ordinal%n", card.getCardType().ordinal());
            System.out.printf("%d is card value's ordinal%n", card.getCardValue().ordinal());
            System.out.println("--------------------------");
        }
    }
}

