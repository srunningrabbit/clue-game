/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

/*
Card

Represents a card in the deck, can be a person, weapon, or room
 */

public class Card {
	private String cardName;
	private CardType cardType;

	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}

	/*
	Getters
	 */

	public String getCardName() {
		return cardName;
	}

	public CardType getCardType() {
		return cardType;
	}

	/*
	Methods
	 */

	@Override
	public String toString() {
		return cardName;
	}
}