package ohtu;

public class TennisGame {
    
    private int score1;
    private int score2;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
		this.score1 = 0;
		this.score2 = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            this.score1++;
		} else {
            this.score2++;
		}
    }

    public String getScore() {
        if (this.score1 == this.score2) {
            return evenScore(score1);
        } else if (lateGame()) {
			return checkWinCondition();
        } else {
			return tennisfy(score1) + "-" + tennisfy(score2);
        }
    }
	
	private static String evenScore(int score) {
		switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";         
        }
	}
	
	private boolean lateGame() {
		return this.score1 >= 4 || this.score2 >= 4;
	}
	
	private String checkWinCondition() {
		if (player1Leads()) {
			return "Advantage player1";
		} else if (player2Leads()) {
			return "Advantage player2";
		} else if (player1Won()) {
			return "Win for player1";
		} else {
			return "Win for player2";
		}
	}
	
	private int difference() {
		return this.score1 - this.score2;
	}
	
	private boolean player1Leads() {
		return difference() == 1;
	}
	
	private boolean player2Leads() {
		return difference() == -1;
	}
	
	private boolean player1Won() {
		return difference() >= 2;
	}
	
	private String tennisfy(int score) {
		switch(score) {
            case 0:
                return "Love";
            case 1:
				return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
				return "Forty";
			default:
				return "";
        }
	}
}