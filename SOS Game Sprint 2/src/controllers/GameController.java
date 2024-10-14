package controllers;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import views.*;

public class GameController {
	
	private GameView gameView;
	
	public GameController(GameView gv) {
		this.gameView = gv;
	
		gameView.getBtnEnter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JButton[] buttons = gameView.getButtons();
				JPanel buttonPanel = gameView.getButtonPanel();
				
				String input = gameView.getNumSize().getText();
				int boardSize = 0;
				//Function to check if an input is made up of numbers
		        try {
				boardSize = Integer.parseInt(input);  // Takes the string and converts it to an integer
		        }
		        catch(Exception f) {
		        	JOptionPane.showMessageDialog(gameView,"Please input a valid boardsize","Error", JOptionPane.ERROR_MESSAGE);
		        }
				//Checks to see if the board size input is in the proper range to play and if it is it starts the game
				if(3 <= boardSize && boardSize <= 10 && (gameView.getGameMode1().isSelected() || gameView.getGameMode2().isSelected())) {
					buttonPanel.setLayout(new GridLayout(boardSize, boardSize)); // Set the board size by the length given
					buttons = new JButton[boardSize * boardSize];
			        for (int i = 0; i < boardSize * boardSize; i++) {
			            buttons[i] = new JButton("");  // Initially, buttons are blank
			            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40)); // Larger font for visibility
			            buttonPanel.add(buttons[i]);
			            
			            // Add action listener to handle button clicks
			            buttons[i].addActionListener(new ActionListener() {
			                @Override
			                public void actionPerformed(ActionEvent e) {
			                    JButton clickedButton = (JButton) e.getSource();
			                    //Tests to make sure either S or O are seleected and makes sure it is the correct players turn
			                    if (gameView.getSelector1().isSelected() && gameView.getPlayer1()|| gameView.getSelector2().isSelected()&& gameView.getPlayer1()) {
			                        if (gameView.getSelector1().isSelected()) {
			                            clickedButton.setText("S");
			                        } else if (gameView.getSelector2().isSelected()) {
			                            clickedButton.setText("O");
			                        }
			                        clickedButton.setEnabled(false);  // Disable after clicking
			                        gameView.setPlayer1(false);   // Changes the players turn
			                        gameView.setPlayer2(true);
			                        gameView.getCurrentTurn().setText("Current turn is Red");
			                    }
			                    //Tests to make sure either S or O are seleected and makes sure it is the correct players turn
			                    else if (gameView.getSelector3().isSelected() && gameView.getPlayer2()|| gameView.getSelector4().isSelected()&& gameView.getPlayer2()) {
			                        if (gameView.getSelector3().isSelected()) {
			                            clickedButton.setText("S");
			                        } else if (gameView.getSelector4().isSelected()) {
			                            clickedButton.setText("O");
			                        }
			                        clickedButton.setEnabled(false);  // Disable after clicking
			                        gameView.setPlayer2(false);   // Changes the players turn
			                        gameView.setPlayer1(true);
			                        gameView.getCurrentTurn().setText("Current turn is Blue");
			                    }
			                }
			            });
			        }
			        //Sync the list of buttons stored in gameView
			        gameView.setButtons(buttons);
			        //Refreshes the UI
			    	gameView.revalidate();
			    	gameView.repaint();
				}
				else {
					JOptionPane.showMessageDialog(gameView,"Please confirm gamemode is selected and valid boardsize is entered","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
