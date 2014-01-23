/**
 * Controller for IO Project
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.Game;
import view.IOframe;

/**
 * @author CJ Oman
 * @version 1.3 
 * added scanner. added makeGame method, save game method, and checkNumber method added pickRandom, readAll, convertToText..
 * @date Dec 18, 2013 9:07:10 AM
 */
public class IOcontroller
{
	/**
	 *  Reference GUI
	 */
	private IOframe appFrame;

	/**
	 *  Reference to ProjectGames.
	 */
	private ArrayList<Game> projectGames;
	
	/**
	 * 
	 */
	public IOcontroller()
	{
		projectGames = new ArrayList<Game>();
	}
	
	/**
	 * @return the projectGames
	 */
	public ArrayList<Game> getProjectGames()
	{
		return projectGames;
	}

	
	/**
	 * Starts the Program.
	 */
	public void start()
	{
		appFrame = new IOframe(this);
	}
	
	/**
	 * shows how to read game info.
	 * @return Game Info
	 */
	public Game readGameInfo()
	{
		String fileName = "save file.txt"; // Without a path it will look to the directory of the project!
		File currentSaveFile = new File(fileName);
		
		Scanner fileReader;
		Game currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();
			
		/**
		 * Major Scanner Methods!!!
		 * .next() - the next string seperated by whitespacce so if the file had "twas brillig and the slithey.." .next() would return twas
		 * .nextLine() - returns an entire line
		 * .nextInt() - returns the next integer value
		 * .nextBoolean() - returns the next boolean value 
		 * .nextDouble() - the next double value
		 */
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();
			while(fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}
			
			currentGame = new Game(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		
		return currentGame;
		
	}
	
	/**
	 * Reads Game Info.
	 * @return gameInfo
	 */
	private String readAllGameInfo()
	{
		String fileContents = "";
		String fileName = "saveFile.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			while(fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		
		return fileContents;
	}
	
	/**
	 * Takes text and splits it up to make game object.
	 * @param currentInfo
	 */
	private void convertTextToGames(String currentInfo)
	{
		String [] gameChunks = currentInfo.split(";");
		
		for(String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex + 1, nextIndex);
			String rules = currentBlock.substring(nextIndex + 1);
			Game currentGame = makeGameFromInput(title, ranking, rules);
			projectGames.add(currentGame);
		}
	}
	
	/**
	 * picks a random games save file.
	 * @return save game file
	 */
	public Game pickRandomGameFromSaveFile()
	{
		Game currentGame = null;
		
		String allInfo = readAllGameInfo();
		convertTextToGames(allInfo);
		int randomIndex = (int) (Math.random() * (double) projectGames.size());
		currentGame = projectGames.get(randomIndex);
		
		return currentGame;
	}
	
	/*
	 * creates the game.
	 * @param gameTitle
	 * @param gameRanking
	 * @param gameRules
	 * @return
	 */
	public Game makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		Game currentGame = new Game();
		currentGame.setGameTitle(gameTitle);
		
		if(checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
		}
		else
		{
			return null;
		}
		
		String[] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();
		
		for (String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentGame.setGameRules(tempRules);
		
		return currentGame;
	}
	
	/**
	 * checks if the number really is a number.
	 * @param toBeParsed
	 * @return the number.
	 */
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;
		
		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Please try again with an actual number for the ranking.");
		}
		
		return isNumber;
	}
	
	/**
	 * shows how to save the game.
	 * @param currentGame
	 */
	public void saveGameInfo(Game currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		
		try
		{
			gameWriter = new PrintWriter(saveFile); //Creates the save file.
			
			gameWriter.append(currentGame.getGameTitle()+"\n");
			gameWriter.append(currentGame.getFunRanking()+"\n");
			for(int count = 0; count < currentGame.getGameRules().size(); count++)
			{
				gameWriter.append(currentGame.getGameRules().get(count)+"\n");
			}
				
			gameWriter.append(";"+"\n");
			
			gameWriter.close(); //Required to prevent corruption of date and maintain security of the file.
		}
		catch(FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file. :(");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}
	}
}
