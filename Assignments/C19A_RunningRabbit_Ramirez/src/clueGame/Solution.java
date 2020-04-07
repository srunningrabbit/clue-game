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

	public String getPerson() {
		return person;
	}

	public String getWeapon() {
		return weapon;
	}

	public String getRoom() {
		return room;
	}
	
	@Override
	public int compareTo(Solution otherSol) {
		if(this.person.equals(otherSol.getPerson()) && this.weapon.equals(otherSol.getWeapon()) && this.room.equals(otherSol.getRoom())) {
			return 0;
		} else {
			return -1;
		}
	}
	
	// TESTING PURPOSES ONLY
	
	public void setPerson(String person) {
		this.person = person;
	}
	
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public boolean hasCard(Card card) {
		return person.equals(card.getCardName()) || weapon.equals(card.getCardName()) || room.equals(card.getCardName());
	}
	
}
