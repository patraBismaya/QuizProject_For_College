package quiztest;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class ExamPageForQuiz extends JFrame implements ActionListener 
{
    JLabel heading, sic, sub, sem, que, qno, branch, stdname, subjects_id, q1;
    JRadioButton bop1, bop2, bop3, bop4;
    ButtonGroup bg;
    JButton next, back, submit;

    String subName, semIs, sic_id, brn, qname, std_name, sub_id, option1, option2, option3, option4, answer, select,selectarray[][] = new String[10][3];
    public static int count = 1, i = 0,j, time = 150,flag=0;

    ExamPageForQuiz(String subName, String sic_id, String semIs, String brn, String std_name, String sub_id) 
	{
        this.brn = brn;
        this.subName = subName;
        this.sic_id = sic_id;
        this.semIs = semIs;
        this.std_name = std_name;
        this.sub_id = sub_id;
		
		
		//initialising matrix as null value
		for (int m  = 0; m < selectarray.length; m++) 
		{
            for ( int n = 0; n < selectarray[m].length; n++) 
			{
                selectarray[m][n] = null;
            }
        }

        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setSize(1930, 1200);
        setLocation(-5, 0);

        heading = new JLabel("QUIZ TEST");
        heading.setBounds(650, 10, 500, 60);
        heading.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
		heading.setForeground(Color.WHITE);
        add(heading);

        sic = new JLabel("SIC:   " + sic_id);
        sic.setBounds(190, 100, 200, 30);
        sic.setFont(new Font("Eras Bold ITC", Font.BOLD, 14));
		sic.setForeground(new Color(255,253,208));
        add(sic);

        sub = new JLabel("SUBJECT:   " + subName);
        sub.setBounds(380, 100, 200, 30);
        sub.setFont(new Font("Eras Bold ITC", Font.BOLD, 14));
		sub.setForeground(new Color(255,253,208));
        add(sub);

        sem = new JLabel("SEMESTER:   " + semIs);
        sem.setBounds(600, 100, 200, 30);
        sem.setFont(new Font("Eras Bold ITC", Font.BOLD, 14));
		sem.setForeground(new Color(255,253,208));
        add(sem);

        branch = new JLabel("Branch:   " + brn);
        branch.setBounds(740, 100, 250, 30);
        branch.setFont(new Font("Eras Bold ITC", Font.BOLD, 14));
		branch.setForeground(new Color(255,253,208));
        add(branch);

        stdname = new JLabel("Name:   " + std_name);
        stdname.setBounds(880, 100, 250, 30);
        stdname.setFont(new Font("Eras Bold ITC", Font.BOLD, 14));
		stdname.setForeground(new Color(255,253,208));
        add(stdname);

        subjects_id = new JLabel("Sub Id:   " + sub_id);
        subjects_id.setBounds(1150, 100, 250, 30);
        subjects_id.setFont(new Font("Eras Bold ITC", Font.BOLD, 14));
		subjects_id.setForeground(new Color(255,253,208));
        add(subjects_id);

        que = new JLabel("Question. ");
        que.setBounds(140, 160, 200, 50);
        que.setFont(new Font("Arial Black", Font.BOLD, 18));
        que.setForeground(Color.YELLOW);
        add(que);
        que.setVisible(true);

        qno = new JLabel();
        qno.setBounds(240, 160, 200, 50);
        qno.setFont(new Font("Arial Black", Font.BOLD, 18));
        qno.setForeground(Color.YELLOW);
        add(qno);
        qno.setVisible(true);

        q1 = new JLabel();
        q1.setBounds(150, 200, 800, 50);
        q1.setFont(new Font("Arial black", Font.BOLD, 16));
        q1.setForeground(Color.WHITE);
        add(q1);
        q1.setVisible(true);

        bop1 = new JRadioButton();
        bop1.setBounds(150, 320, 650, 50);
        bop1.setBackground(Color.BLACK);
        bop1.setForeground(Color.CYAN);
        bop1.setFont(new Font("Arial black", Font.BOLD, 16));
        bop1.setVisible(true);

        bop2 = new JRadioButton();
        bop2.setBounds(150, 430, 650, 50);
        bop2.setBackground(Color.BLACK);
        bop2.setForeground(Color.CYAN);
        bop2.setFont(new Font("Arial black", Font.BOLD, 16));
        bop2.setVisible(true);

        bop3 = new JRadioButton();
        bop3.setBounds(150, 540, 650, 50);
        bop3.setBackground(Color.BLACK);
        bop3.setForeground(Color.CYAN);
        bop3.setFont(new Font("Arial black", Font.BOLD, 16));
        bop3.setVisible(true);

        bop4 = new JRadioButton();
        bop4.setBounds(150, 650, 650, 50);
        bop4.setBackground(Color.BLACK);
        bop4.setForeground(Color.CYAN);
        bop4.setFont(new Font("Arial black", Font.BOLD, 16));
        bop4.setVisible(true);

        bg = new ButtonGroup();
        bg.add(bop1);
        bg.add(bop2);
        bg.add(bop3);
        bg.add(bop4);

        add(bop1);
        add(bop2);
        add(bop3);
        add(bop4);

        next = new JButton("Save&Next");
        next.setBounds(700, 800, 150, 30);
        next.setBackground(new Color(30, 144, 254));
        next.setFont(new Font("Arial Black", Font.BOLD, 12));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);
        next.setVisible(true);

        back = new JButton("Back");
        back.setBounds(100, 800, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setFont(new Font("Arial Black", Font.BOLD, 12));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        back.setVisible(true);
        back.setEnabled(false);

        submit = new JButton("Submit");
        submit.setBounds(1350, 800, 100, 30);
        submit.setBackground(new Color(30, 144, 254));
        submit.setFont(new Font("Arial Black", Font.BOLD, 12));
        submit.setForeground(Color.RED);
        submit.addActionListener(this);
        add(submit);
        submit.setVisible(true);
        submit.setEnabled(false);

        showQuestion();
        setVisible(true);
    }

//when thisethod will call corresponding question and options will be shown on the screen
    public void showQuestion() 
	{
        qno.setText("" + count + "");
        if (count == 10) 
		{
            next.setEnabled(false);
            submit.setEnabled(true);
        } 
		else if (count == 1) 
		{
            back.setEnabled(false);
        }
        try 
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "silicon");
            String strQue = "SELECT QUE, OPT1, OPT2, OPT3, OPT4, ANS FROM que_bank WHERE subid=? AND BRANCH=? AND QNO='" + count + "'";
            PreparedStatement smt = con.prepareStatement(strQue);
            smt.setString(1, sub_id);
            smt.setString(2, brn);

            ResultSet set = smt.executeQuery();
            if (set.next()) 
			{
                qname = set.getString("QUE");
                option1 = set.getString("OPT1");
                option2 = set.getString("OPT2");
                option3 = set.getString("OPT3");
                option4 = set.getString("OPT4");
                answer = set.getString("ANS");
            }
            con.close();
        } 
		catch (Exception ob) 
		{
            System.out.println(ob);
        }
        q1.setText(qname);
        bop1.setText(option1);
        bop2.setText(option2);
        bop3.setText(option3);
        bop4.setText(option4);

    }

//when this method will call which option student has selected it will be saved
    public void selection() 
	{
        if (j == i && bop1.isSelected()) 
		{
            select = "a";
        } 
		else if (j == i && bop2.isSelected()) 
		{
            select = "b";
        } 
		else if (j == i && bop3.isSelected()) 
		{
            select = "c";
        } 
		else if (j == i && bop4.isSelected()) 
		{
            select = "d";
        } 
		else 
		{
            select = "Not Answered!";
        }       
		
        if (count <= 10) 
		{
            selectarray[i][0] = qname;
            selectarray[i][1] = select;
            selectarray[i][2] = answer;
        }
    }

    public void actionPerformed(ActionEvent e) 
	{
    if (e.getSource() == next) 
	{
        selection();
        back.setEnabled(true);
        count++;
        i++;
        j++;
        showQuestion();	
		if(flag!=0)
		{
			flag--;
			if (selectarray[i][1] != null)
			{
				if (selectarray[i][1].equals("a")) 
				{
					bop1.setSelected(true);
				} 
				else if (selectarray[i][1].equals("b")) 
				{
					bop2.setSelected(true);
				} 
				else if (selectarray[i][1].equals("c")) 
				{
					bop3.setSelected(true);
				} 
				else if (selectarray[i][1].equals("d")) 
				{
					bop4.setSelected(true);
				} 
				else 
				{
					bg.clearSelection();
				}
			}
			else
			{
				bg.clearSelection();
			}			
		}		
		else 
		{
			bg.clearSelection();
		}
						
		if(count==10)
		{
			selection();
			next.setEnabled(false);
			submit.setEnabled(true);
		}
		
    } 
	else if (e.getSource() == back) 
	{
		flag++;
        count--;
        i--;
        j--;
        showQuestion();
        if (!selectarray[count - 1][1].equals("Not Answered!")) 
		{
            switch (selectarray[count - 1][1]) 
			{
                case "a":
                    bop1.setSelected(true);
                    break;
                case "b":
                    bop2.setSelected(true);
                    break;
                case "c":
                    bop3.setSelected(true);
                    break;
                case "d":
                    bop4.setSelected(true);
                    break;
            }
        } 
		else 
		{
            bg.clearSelection();
        }
		selection();
		
		
        next.setEnabled(true);
        submit.setEnabled(false);
    } 
	else if (e.getSource() == submit) 
	{
        selection();
        setVisible(false);
        new Score(selectarray,sic_id,std_name,subName,semIs);
    }
}

//for timer
    public void paint(Graphics g) 
	{
        super.paint(g);
        String timer = "Time left - " + time + "  sec";
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 18));

        if (time > 0) 
		{
            g.drawString(timer, 640, 200);
     
			time--;
			try
			{
				Thread.sleep(1000);
				repaint();
			}
			catch(Exception ob)
			{
				System.out.println(ob);
			}
		}
        //if times up then it will auto submit your answer and not ansered questions are selected as not answered
        if(time==0)
        {			
			selection();
			
            setVisible(false);
             new Score(selectarray,sic_id,std_name,subName,semIs);
        }
		if(time<2)
		{
			submit.setEnabled(false);
		}
    }

    public static void main(String args[]) 
	{
        new ExamPageForQuiz("", "", "", "", "", "");
    }
}

