package quiztest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Registration_fac extends JFrame implements ActionListener 
{
    JLabel heading,id, name, pass, conpass;
    JTextField t1id, t2name, t3pass, t4pass;
    JButton submit, reset;

    Registration_fac() 
	{
        getContentPane().setBackground(Color.GRAY); // setting frame background
        setLayout(null); // we don't need the default layout...we design our own
        setSize(600, 400);
        setLocation(400, 200);

        heading = new JLabel("Register Here");
        heading.setBounds(225, 5, 200, 80);
        heading.setFont(new Font("Elephant", Font.BOLD, 18));
        heading.setForeground(Color.RED);
        add(heading);

        id = new JLabel("Enter Faculty ID: ");
        id.setBounds(103, 79, 200, 80);
        id.setFont(new Font("Arial Black", Font.BOLD, 12));
        id.setForeground(Color.BLACK);
        add(id);

        t1id = new JTextField();
        t1id.setBounds(240, 105, 200, 30);
        add(t1id);

        name = new JLabel("Enter Name: ");
        name.setBounds(103, 120, 200, 80);
        name.setFont(new Font("Arial Black", Font.BOLD, 12));
        name.setForeground(Color.BLACK);
        add(name);

        t2name = new JTextField();
        t2name.setBounds(240, 145, 200, 30);
        add(t2name);

        pass = new JLabel("Enter Password: ");
        pass.setBounds(103, 160, 200, 80);
        pass.setFont(new Font("Arial Black", Font.BOLD, 12));
        pass.setForeground(Color.BLACK);
        add(pass);

        t3pass = new JTextField();
        t3pass.setBounds(240, 185, 200, 30);
        add(t3pass);

        conpass = new JLabel("Confirm Password: ");
        conpass.setBounds(103, 200, 200, 80);
        conpass.setFont(new Font("Arial Black", Font.BOLD, 12));
        conpass.setForeground(Color.BLACK);
        add(conpass);

        t4pass = new JTextField();
        t4pass.setBounds(240, 225, 200, 30);
        add(t4pass);

        submit = new JButton("Submit");
        submit.setBounds(103, 300, 100, 40);
        submit.setBackground(new Color(30, 144, 254));
        submit.setFont(new Font("Arial Black", Font.BOLD, 12));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        reset = new JButton("Reset");
        reset.setBounds(339, 300, 100, 40);
        reset.setBackground(new Color(30, 144, 254));
        reset.setFont(new Font("Arial Black", Font.BOLD, 12));
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        add(reset);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == reset) 
		{
            setVisible(false);
            new Registration_fac();
        }
		else 
		{
            String id1 = t1id.getText().toUpperCase();
            String name1 = t2name.getText().toUpperCase();
            String pass1 = t3pass.getText();
			int lengthpass=pass1.length();
			if(lengthpass<8)
			{
				JOptionPane.showMessageDialog(this,"Password must have 8 character!");
				t3pass.setText("");
				t4pass.setText("");
			}
			else
			{
				boolean containsalpha=false;
				boolean containsdigit=false;
				for(int i=0;i<lengthpass;i++)
				{
					char ch=pass1.charAt(i);
					if(Character.isLetter(ch))
					{
						containsalpha=true;
					}
					else if(Character.isDigit(ch))
					{
						containsdigit=true;
					}
					if(containsalpha && containsdigit)
					{
						break;
					}
				}
				if(containsalpha && containsdigit)
				{
					String conpass1 = t4pass.getText();
					if (pass1.equals(conpass1)) 
					{
						try 
						{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							//Driver Load...
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","silicon");
							String strinput = "Insert into register_fac values('" + id1 + "','" + name1 + "','" + pass1 + "')";
							Statement smt = con.createStatement();
							smt.executeUpdate(strinput);
							con.close();
						}
						catch (Exception ob) 
						{
							System.out.println(ob);
						}
						JOptionPane.showMessageDialog(this,"Successfully Registered!");
						setVisible(false);
						new HomePageForQuiz();
					} 
					else
					{
						JOptionPane.showMessageDialog(this,"Passwords must be same!");
						setVisible(false);
						new Registration_fac();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Password must contain atleat one alphabet and one number!");
					t3pass.setText("");
					t4pass.setText("");
				}
			}
        }
    }

    public static void main(String args[]) 
	{
        new Registration_fac();
    }
}
