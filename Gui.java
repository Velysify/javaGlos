import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	Main main = new Main();
	NewWord callableWord = new NewWord();
	JPanel newWord = null;
	String CurrentWord = null;
	JTextField answerField = null;
	Boolean answerShown = false;
	JPanel score = null;
	int nrOfCorrect = 0;
	int nrOfFailed = 0;
	Gui(){
		super("Glosprov");

		
		newWord = new JPanel();
		JLabel word = new JLabel(" ");
		newWord.add(word);
		add(newWord, BorderLayout.CENTER);
		
		JPanel buttonBar = new JPanel();
		JButton button = new JButton("Show answer");
		button.addActionListener(new ShowAnswer());
		buttonBar.add(button);
		button = new JButton("Check answer");
		button.addActionListener(new CheckAnswer());
		buttonBar.add(button);
		button = new JButton("New Word");
		button.addActionListener(new NewWord());
		buttonBar.add(button);
		add(buttonBar, BorderLayout.SOUTH);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Settings för hur programmet visas
		setSize(375,180);
		setLocation(300,300);
		setResizable(false);
		setVisible(true);
	}
		
	public class Exit implements ActionListener{	//Stäng programmet utan förvarning.
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	public class ShowAnswer implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (!answerShown){
				answerShown = true;
				JPanel row = new JPanel();
				JLabel answer = new JLabel(CurrentWord);
				row.add(answer);
				newWord.add(row);
				validate();
				repaint();
			}
		}
	}
	public class CheckAnswer implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String answer = answerField.getText();
			if(CurrentWord.toLowerCase().equals(answer.toLowerCase())){
				nrOfCorrect++;
				callableWord.actionPerformedMethod();
				updateScore();
			}
			else{
				nrOfFailed++;
				updateScore();
			}
		}
	}
	public class NewWord implements ActionListener{
		public void actionPerformed(ActionEvent e){
			actionPerformedMethod();
		}
		public void actionPerformedMethod(){
			if(newWord!=null){
				remove(newWord);
			}
			answerShown = false;
			newWord = new JPanel();
			newWord.setLayout(new BoxLayout(newWord, BoxLayout.Y_AXIS));
			JPanel row = new JPanel();
			String[] list = main.getWord();
			CurrentWord = list[0];
			JLabel word = new JLabel(list[1]);
			row.add(word);
			newWord.add(row);
			row = new JPanel();
			answerField = new JTextField(10); 
			row.add(answerField);
			newWord.add(row);
			add(newWord, BorderLayout.CENTER);
			validate();
			repaint();
		}
	}
	public void updateScore(){
		if(score!=null){
			remove(score);
		}
		score = new JPanel();
		JLabel correct = new JLabel("" + nrOfCorrect);
		correct.setForeground(Color.GREEN);
		JLabel wrong = new JLabel("" + nrOfFailed);
		wrong.setForeground(Color.RED);
		score.add(correct);
		score.add(wrong);
		add(score, BorderLayout.NORTH);
		validate();
		repaint();
	}
}