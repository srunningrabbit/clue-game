/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

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

	public boolean equals() {
		return false;
	}

	@Override
	public String toString() {
		return cardName;
	}
}