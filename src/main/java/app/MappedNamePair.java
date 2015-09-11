package main.java.app;

public class MappedNamePair {
	private final String submittedName;
	private final String gameOfThronesName;
	
	public MappedNamePair(String submittedName, String gameOfThronesName) {
		this.submittedName = submittedName;
		this.gameOfThronesName = gameOfThronesName;
	}
	
	@Override
    public String toString() {
        return String.format(
            "MappedNamePair[name='%s', lastName='%s']",
            submittedName, gameOfThronesName);
    }

    public String getSubmittedName() {
    	return submittedName;
    }
    
    public String getGameOfThronesName() {
    	return gameOfThronesName;
    }
}
