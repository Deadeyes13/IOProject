/**
 * The game superclass for IO Project.
 */
package model;

import java.util.ArrayList;

/**
 * @author CJ Oman
 * @version 1.0
 * @date Dec 13, 2013 8:57:30 AM
 * Added data members, constructors and default play method.
 */
public class Game
{
	private ArrayList<String> gameRules;
	private int funRanking;
	private String gameTitle;
	
	public Game()
	{
		gameRules = new ArrayList<String>();
		funRanking = 0;
		gameTitle = "";
	}
	
	/**
	 * Defines the ArrayList.
	 * @param gameRules
	 * @param funRanking
	 * @param gameTitle
	 */
	public Game(ArrayList<String> gameRules, int funRanking, String gameTitle)
	{
		this.gameRules = gameRules;
		this.funRanking = funRanking;
		this.gameTitle = gameTitle;
	}
	
	/**
	 * @return the gameRules
	 */
	public ArrayList<String> getGameRules()
	{
		return gameRules;
	}

	/**
	 * @return the funRanking
	 */
	public int getFunRanking()
	{
		return funRanking;
	}

	/**
	 * @return the gameTitle
	 */
	public String getGameTitle()
	{
		return gameTitle;
	}

	/**
	 * @param gameRules the gameRules to set
	 */
	public void setGameRules(ArrayList<String> gameRules)
	{
		this.gameRules = gameRules;
	}

	/**
	 * @param funRanking the funRanking to set
	 */
	public void setFunRanking(int funRanking)
	{
		this.funRanking = funRanking;
	}

	/**
	 * @param gameTitle the gameTitle to set
	 */
	public void setGameTitle(String gameTitle)
	{
		this.gameTitle = gameTitle;
	}

	public void play()
	{
		
	}

}
