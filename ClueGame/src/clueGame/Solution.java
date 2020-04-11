package clueGame;

public class Solution implements Comparable<Solution>{
	
	private String person;
	private String weapon;
	private String room;

	public Solution(String person, String weapon, String room) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}

	/*
	Getters and setters, including those for testing purposes
	 */

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	/*
	Methods
	 */

	@Override
	public int compareTo(Solution otherSol) {
		if(this.person.equals(otherSol.getPerson()) && this.weapon.equals(otherSol.getWeapon()) && this.room.equals(otherSol.getRoom())) {
			return 0;
		} else {
			return -1;
		}
	}

	public boolean hasCard(Card card) {
		return person.equals(card.getCardName()) || weapon.equals(card.getCardName()) || room.equals(card.getCardName());
	}
}
