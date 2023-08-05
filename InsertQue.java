package quiztest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class InsertQue extends JFrame implements ActionListener 
{
    JLabel heading, branch, admission, subject, que, qno, opt1, opt2, opt3, opt4, ans;
    JComboBox<String> cb1, cb2, cb3;
    JTextField o1, o2, o3, o4;
	JTextArea q1;
    JRadioButton bop1, bop2, bop3, bop4;
    ButtonGroup bg;
    JButton submit, reset, show, upload, next;
    String answer,setCount;

    public static int count = 1;

    InsertQue() 
	{
        getContentPane().setBackground(Color.GRAY);
        setLayout(null);
        setSize(900, 700);
        setLocation(300, 100);

        heading = new JLabel("Question Upload Section");
        heading.setBounds(325, 5, 300, 80);
        heading.setFont(new Font("Elephant", Font.BOLD, 18));
        heading.setForeground(Color.RED);
        add(heading);

        branch = new JLabel("Select Branch: ");
        branch.setBounds(103, 79, 200, 50);
        branch.setFont(new Font("Arial Black", Font.BOLD, 12));
        branch.setForeground(Color.BLACK);
        String br[] = { "CSE", "ECE", "EEE", "MCA", "DATA SC" };
        cb1 = new JComboBox<>(br);
        cb1.setBounds(240, 95, 80, 20);
        add(cb1);
        add(branch);

        admission = new JLabel("Semester: ");
        admission.setBounds(103, 109, 200, 50);
        admission.setFont(new Font("Arial Black", Font.BOLD, 12));
        admission.setForeground(Color.BLACK);
        String yr[] = { "1", "2", "3", "4", "5", "6", "7", "8" };
        cb2 = new JComboBox<>(yr);
        cb2.setBounds(240, 125, 80, 20);
        add(cb2);
        add(admission);

        show = new JButton("Show Sub");
        show.setBounds(350, 105, 100, 30);
        show.setBackground(new Color(30, 144, 254));
        show.setFont(new Font("Arial Black", Font.BOLD, 10));
        show.setForeground(Color.WHITE);
        show.addActionListener(this);
        add(show);

        subject = new JLabel("Select Subject: ");
        subject.setBounds(487, 91, 200, 50);
        subject.setFont(new Font("Arial Black", Font.BOLD, 12));
        subject.setForeground(Color.BLACK);
        cb3 = new JComboBox<>();
        cb3.setBounds(599, 105, 200, 20);
        add(cb3);
        add(subject);
		
        que = new JLabel("Question. ");
        que.setBounds(100, 160, 200, 50);
        que.setFont(new Font("Arial Black", Font.BOLD, 12));
        que.setForeground(Color.RED);
        add(que);
		que.setVisible(false);

        qno = new JLabel("1");
        qno.setBounds(170, 160, 200, 50);
        qno.setFont(new Font("Arial Black", Font.BOLD, 12));
        qno.setForeground(Color.RED);
        add(qno);
		qno.setVisible(false);

        q1 = new JTextArea();
        q1.setBounds(100, 200, 650, 50);
        q1.setFont(new Font("Arial black", Font.BOLD, 14));
        add(q1);
		q1.setVisible(false);

        opt1 = new JLabel("Option:1");
        opt1.setBounds(100, 232, 200, 50);
        opt1.setFont(new Font("Arial Black", Font.BOLD, 12));
        opt1.setForeground(Color.BLACK);
        add(opt1);
		opt1.setVisible(false);
		
        o1 = new JTextField();
        o1.setBounds(100, 270, 650, 30);
        o1.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(o1);
		o1.setVisible(false);
		
        opt2 = new JLabel("Option:2");
        opt2.setBounds(100, 300, 200, 50);
        opt2.setFont(new Font("Arial Black", Font.BOLD, 12));
        opt2.setForeground(Color.BLACK);
        add(opt2);
		opt2.setVisible(false);

        o2 = new JTextField();
        o2.setBounds(100, 340, 650, 30);
        o2.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(o2);
		o2.setVisible(false);

        opt3 = new JLabel("Option:3");
        opt3.setBounds(100, 370, 200, 50);
        opt3.setFont(new Font("Arial Black", Font.BOLD, 12));
        opt3.setForeground(Color.BLACK);
        add(opt3);
		opt3.setVisible(false);

        o3 = new JTextField();
        o3.setBounds(100, 410, 650, 30);
        o3.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(o3);
		o3.setVisible(false);

        opt4 = new JLabel("Option:4");
        opt4.setBounds(100, 440, 200, 50);
        opt4.setFont(new Font("Arial Black", Font.BOLD, 12));
        opt4.setForeground(Color.BLACK);
        add(opt4);
		opt4.setVisible(false);

        o4 = new JTextField();
        o4.setBounds(100, 480, 650, 30);
        o4.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(o4);
		o4.setVisible(false);

        ans = new JLabel("Answer: ");
        ans.setBounds(100, 540, 100, 50);
        ans.setFont(new Font("Arial Black", Font.BOLD, 14));
        ans.setForeground(Color.RED);
        add(ans);
		ans.setVisible(false);

        bop1 = new JRadioButton("a");
        bop1.setBounds(200, 550, 40, 30);
		bop1.setVisible(false);

        bop2 = new JRadioButton("b");
        bop2.setBounds(270, 550, 40, 30);
		bop2.setVisible(false);

        bop3 = new JRadioButton("c");
        bop3.setBounds(340, 550, 40, 30);
		bop3.setVisible(false);

        bop4 = new JRadioButton("d");
        bop4.setBounds(410, 550, 40, 30);
		bop4.setVisible(false);
		
        bg = new ButtonGroup();
        bg.add(bop1);
        bg.add(bop2);
        bg.add(bop3);
        bg.add(bop4);

        add(bop1);
        add(bop2);
        add(bop3);
        add(bop4);

        next = new JButton("Next");
        next.setBounds(370, 610, 80, 30);
        next.setBackground(new Color(30, 144, 254));
        next.setFont(new Font("Arial Black", Font.BOLD, 10));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);
		next.setVisible(false);

        upload = new JButton("Upload");
        upload.setBounds(800, 627, 80, 30);
        upload.setBackground(Color.RED);
        upload.setFont(new Font("Arial Black", Font.BOLD, 10));
        upload.setForeground(Color.WHITE);
        upload.addActionListener(this);
        upload.setEnabled(false);
        add(upload);
		upload.setVisible(false);

        setVisible(true);
    }

    public void showsub() 
	{
        String brn = cb1.getItemAt(cb1.getSelectedIndex());
        int sem = Integer.parseInt(cb2.getItemAt(cb2.getSelectedIndex()));

        try 
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "silicon");
            String strinput = "select NAME from SUB_INFO where BRANCH='" + brn + "' and SEM='" + sem + "'";
            Statement smt = con.createStatement();
            ResultSet set = smt.executeQuery(strinput);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (set.next()) 
			{
                String sname = set.getString("NAME");
                model.addElement(sname);
            }
            cb3.setModel(model);
            con.close();
        } 
		catch (Exception ob) 
		{
            System.out.println(ob);
        }
    }

    public void start(int count) 
	{        
        String brn = cb1.getItemAt(cb1.getSelectedIndex());
        String name1 = (cb3.getItemAt(cb3.getSelectedIndex()));
        String question = q1.getText();
        String option1 = o1.getText();
        String option2 = o2.getText();
        String option3 = o3.getText();
        String option4 = o4.getText();

        if (bop1.isSelected()) 
		{
            answer = "a";
        } 
		else if (bop2.isSelected()) 
		{
            answer = "b";
        } 
		else if (bop3.isSelected()) 
		{
            answer = "c";
        } 
		else if (bop4.isSelected()) 
		{
            answer = "d";
        }
       
            try 
			{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "silicon");
                String strfind = "select subid from sub_info where branch='" + brn + "'and name='" + name1 + "'";

                Statement smt = con.createStatement();
                smt.executeQuery(strfind);
				
				ResultSet set=smt.executeQuery(strfind);
			 if (set.next()) 
				{
                String result = set.getString("SUBID");
                String strinput = "Insert into que_bank(BRANCH,SUBID,QNO,QUE,OPT1,OPT2,OPT3,OPT4,ANS)values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(strinput);
				setCount=String.valueOf(count);
                pst.setString(1, brn);
                pst.setString(2, result);
                pst.setString(3, setCount);
                pst.setString(4, question);
                pst.setString(5, option1);
                pst.setString(6, option2);
                pst.setString(7, option3);
                pst.setString(8, option4);
                pst.setString(9, answer);

                pst.executeQuery();
                con.close();
				}
            } catch (Exception ob) 
			{
                System.out.println(ob);
            }
          
        q1.setText("");
        o1.setText("");
        o2.setText("");
        o3.setText("");
        o4.setText("");
        bg.clearSelection();
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == show) 
		{
            showsub();
			que.setVisible(true);
			qno.setVisible(true);
			q1.setVisible(true);
			opt1.setVisible(true);
			o1.setVisible(true);
			opt2.setVisible(true);
			o2.setVisible(true);
			opt3.setVisible(true);
			o3.setVisible(true);
			opt4.setVisible(true);
			o4.setVisible(true);
			ans.setVisible(true);
			bop1.setVisible(true);
			bop2.setVisible(true);
			bop3.setVisible(true);
			bop4.setVisible(true);
			next.setVisible(true);
			upload.setVisible(true);			
		}
       else if (e.getSource() == next) 
		{
			qno.setText("" + (count+1) + "");
			if(count==9)
			{
				next.setEnabled(false);
				upload.setEnabled(true);
			}
			start(count);
			count++;
			cb1.setEnabled(false);
			cb2.setEnabled(false);
			cb3.setEnabled(false);
			
			
        }
        else if (e.getSource() == upload) 
		{
		   start(count);
           JOptionPane.showMessageDialog(this,"Successfully Uploaded!");
		   upload.setEnabled(false);
           next.setEnabled(true);
		   setVisible(false);
		   show.setEnabled(false);
        }
    }
    
    public static void main(String[] args) 
	{
        new InsertQue();
    }
}
