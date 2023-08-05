package quiztest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RulesForQuiz extends JFrame implements ActionListener
{
    JLabel welcome,rule;
    JButton start;
	String brn,subname,semIs,id,subid,sname;
    RulesForQuiz( String subname,String id,String semIs,String brn,String sname,String subid)
    {
		this.brn=brn;
		this.subname=subname;
		this.semIs=semIs;
		this.id=id;
		this.subid=subid;
		this.sname=sname;
        getContentPane().setBackground(Color.GRAY); //setting frame background
        setLayout(null);//we don't need the default layout...we design our own
        setSize(500,300);
        setLocation(450,0);

        welcome = new JLabel("WELCOME!");
        welcome.setBounds(190,0,150,50);
        welcome.setForeground(Color.RED);
        welcome.setFont(new Font("OCR A Extended",Font.BOLD,16));
        add(welcome);

        rule = new JLabel();
        rule.setBounds(20,20,450,200);
        rule.setFont(new Font("Tahoma",Font.BOLD,14));
        rule.setText(
            "<html>"+
            "1.There are 10 questions in the Test and each question has   4 options."+"<br><br>"+
            "2.You have to choose only one correct option among them."+"<br><br>"+
            "3.A total 20minutes timer will be started for the Test."+"<br><br>"+
            "4.You have to submit in these time period otherwise it will auto-submit your test."+"<br><br>"+
            "<html>"
        );
        add(rule);

        start = new JButton("START");
        start.setBounds(190,220,100,40);
        start.setBackground(new Color(30,144,254));
        start.setFont(new Font("Arial Black",Font.BOLD,12));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        setVisible(true);
    }

     public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)
        {
            setVisible(false);
            new ExamPageForQuiz(subname,id,semIs,brn,sname,subid);
        }
    }
    public static void main(String args[])
    {
        new RulesForQuiz("","","","","","");
    }
}
