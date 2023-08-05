package quiztest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Score extends JFrame implements ActionListener {
	public static int score=0;
	JButton ok;
	String sic_id,std_name,subName,semIs;
    Score(String[][] selectarray,String sic_id,String std_name,String subName,String semIs) {
		this.sic_id=sic_id;
		this.std_name=std_name;
		this.subName=subName;
		this.semIs=semIs;
		
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1200, 1000);
        setLocation(200, 0);

        JLabel scoreLabel = new JLabel("Congratulations!");
        scoreLabel.setBounds(505, 0, 250, 50);
        scoreLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
        scoreLabel.setForeground(Color.RED);
        add(scoreLabel);

        JLabel scoreLabel2 = new JLabel();
        scoreLabel2.setBounds(520, 30, 250, 50);
        scoreLabel2.setFont(new Font("Eras Bold ITC", Font.BOLD, 16));
        scoreLabel2.setForeground(Color.RED);
        add(scoreLabel2);

        JEditorPane answerArea = new JEditorPane();
        answerArea.setContentType("text/html"); // Set content type to HTML
        answerArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(answerArea);
        scrollPane.setBounds(20, 70, 1150, 800);
        add(scrollPane);
		
		ok = new JButton("OK");
        ok.setBounds(1105, 10, 60, 20);
        ok.setBackground(new Color(30, 144, 254));
        ok.setFont(new Font("Arial Black", Font.BOLD, 10));
        ok.setForeground(Color.WHITE);
        ok.addActionListener(this);
        add(ok);

        StringBuilder answerText = new StringBuilder();
        for (int i = 0; i < selectarray.length; i++) {
            if (selectarray[i][0] != null && selectarray[i][1] != null && selectarray[i][2] != null) {
                if (selectarray[i][1].equals(selectarray[i][2])) {
                    // If the user gives the correct answer, show in green color
                    answerText.append("<font color='green'>Question ").append(i + 1).append(": ").append(selectarray[i][0]).append("</font><br>");
                    answerText.append("<font color='green'>Your Answer: ").append(selectarray[i][1]).append("</font><br>");
                    answerText.append("<font color='green'>Correct Answer: ").append(selectarray[i][2]).append("</font><br><br>");
					score++;
                } else {
                    // If the user gives an incorrect answer, show in red color
                    answerText.append("<font color='red'>Question ").append(i + 1).append(": ").append(selectarray[i][0]).append("</font><br>");
                    answerText.append("<font color='red'>Your Answer: ").append(selectarray[i][1]).append("</font><br>");
                    answerText.append("<font color='red'>Correct Answer: ").append(selectarray[i][2]).append("</font><br><br>");
                }
            }
        }
        answerArea.setText("<html>" + answerText.toString() + "</html>");
		scoreLabel2.setText("Your Score: "+score+"/10");
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","silicon");
			String strinput = "Insert into score_table values('" + sic_id + "','" + std_name + "','" + subName + "','" + semIs + "','" + score + "')";
			Statement smt = con.createStatement();
			smt.executeUpdate(strinput);
			con.close();
		}
		catch (Exception ob) 
		{
			System.out.println(ob);
		}
		
        setVisible(true);
    }
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(this,"Your response has been recorded!");
		setVisible(false);
	}
}
