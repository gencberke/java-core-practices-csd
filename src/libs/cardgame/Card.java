package libs.cardgame;

import java.util.Random;

public class Card {
    private static final int DEFAULT_SHUFFLE_COUNT = 100;
    private static final int CART_COUNT_OF_DEC = 52;
    private CardType cardType;
    private CardValue cardValue;

    public static Card [] newDeck() {
        Card [] deck = new Card[CART_COUNT_OF_DEC];
        int idx = 0;

        for (CardType cardType : CardType.values())
            for (CardValue cardValue : CardValue.values())
                deck[idx++] = new Card(cardType, cardValue);

        return deck;
    }

    public static Card [] newShuffledDeck(Random random) {
        return newShuffledDeck(random, DEFAULT_SHUFFLE_COUNT);
    }

    public static Card [] newShuffledDeck(Random random, int count) {
        Card [] deck = newDeck();

        for (int i = 0;i < count; ++i){
            swap(deck, random.nextInt(deck.length), random.nextInt(deck.length));
        }

        return deck;
    }

    private static void swap(Card [] cards, int i, int k) {
        if (i == k)
            return;

        Card temp;

        temp = cards[i];
        cards[i] = cards[k];
        cards[k] = temp;
    }

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return "%s-%s".formatted(cardType,cardValue);
    }
}
