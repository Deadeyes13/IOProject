/**
 * Panel for IO Project.
 */
package view;

import controller.IOcontroller;
import model.Game;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author CJ Oman
 * @version 1.2 
 * added two listeners and load button. added gameCountLabel.
 * @date Dec 18, 2013 9:07:47 AM
 */
public class IOpanel extends JPanel
{
	private IOcontroller baseController;
	private JButton saveButton;
	private JButton loadButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JLabel gameCountLabel;
	
	/**
	 * defines each object.
	 * @param baseController
	 */
	public IOpanel(IOcontroller baseController)
	{
		this.baseController = baseController;
		
		saveButton = new JButton("save the game stuff");
		loadButton = new JButton("load the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title: ");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking: ");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Games Rules: ");
		gameCountLabel = new JLabel("current game count: ");
		baseLayout = new SpringLayout();
		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * adds things to the panel.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(loadButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(gameCountLabel);
	}
	
	/**
	 * sets up the Layout for panel.
	 */
	private void setupLayout()
	{
		setBackground(Color.CYAN);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 14, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 0, SpringLayout.WEST, rankingField);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 0, SpringLayout.WEST, rankingField);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, 17, SpringLayout.SOUTH, rankingField);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 6, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, 30, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 20, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 33, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 25, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, gameCountLabel, 16, SpringLayout.SOUTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.WEST, gameCountLabel, 0, SpringLayout.WEST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, -37, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, 0, SpringLayout.EAST, rankingField);
	}
	
	/**
	 * decides what clicking buttons do.
	 */
	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInfo(tempGame);
					gameCountLabel.setText("Current game count:" + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInfo();
				if (tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for (String currentRules : tempGame.getGameRules())
					{
						tempRules += currentRules + "\n";
					}
					rulesArea.setText(tempRules);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order.");
				}
			}
		});
	}
}
