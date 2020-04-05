package clueGame;

public class Solution {
	
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
	
	//TESTING PURPOSES ONLY
	
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
		return false;
	}
	
}
