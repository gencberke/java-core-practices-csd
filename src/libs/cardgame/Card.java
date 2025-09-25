package libs.cardgame;

public class Card {
    private CardType cardType;
    private CardValue cardValue;

    public static Card[] createDeck() {
        Card [] deck = new Card[52];
        int idx = 0;

        for (CardType cardType : CardType.values())
            for (CardValue cardValue : CardValue.values())
                deck[idx++] = new Card(cardType, cardValue);

        return deck;
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
