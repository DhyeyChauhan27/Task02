
package guessinggame_task02;

import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGame extends JFrame implements ActionListener {

    private JLabel instructionsLabel, feedbackLabel, attemptsLabel;
    private JTextField guessField;
    private JButton submitButton;
    private int number, attempts;

    public GuessingGame() {
        initComponents();
        startNewGame();
    }

    private void initComponents() {
        instructionsLabel = new JLabel();
        feedbackLabel = new JLabel();
        guessField = new JTextField();
        submitButton = new JButton();
        attemptsLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guessing Game");
        setResizable(false);

        instructionsLabel.setText("Guess a number between 1 and 100:");
        feedbackLabel.setText("");
        guessField.setColumns(10);
        submitButton.setText("Submit");
        submitButton.addActionListener(this);
        attemptsLabel.setText("Attempts: 0");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(instructionsLabel)
                    .addComponent(feedbackLabel)
                    .addComponent(guessField, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(submitButton)
                    .addComponent(attemptsLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionsLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedbackLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guessField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attemptsLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void startNewGame() {
        Random random = new Random();
        number = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        guessField.setText("");
        guessField.requestFocus();
        attemptsLabel.setText("Attempts: 0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String guessText = guessField.getText();
            if (!guessText.isEmpty()) {
                int guess = Integer.parseInt(guessText);
                attempts++;
                if (guess < number) {
                    feedbackLabel.setText("Too low!");
                } else if (guess > number) {
                    feedbackLabel.setText("Too high!");
                } else {
                    feedbackLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                    int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        startNewGame();
                    } else {
                        System.exit(0);
                    }
                }
                guessField.setText("");
                attemptsLabel.setText("Attempts: " + attempts);
            }
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new GuessingGame().setVisible(true);
        });
    }
}