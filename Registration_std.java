package quiztest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Registration_std extends JFrame implements ActionListener 
{
    JLabel heading, branch, admission, sic, name, pass, conpass;
    JComboBox<String> cb1, cb2;
    JTextField t1sic, t2name, t3pass, t4pass;
    JButton submit, reset;

    Registration_std() 
	{
        getContentPane().setBackground(Color.GRAY); // setting frame background
        setLayout(null); // we don't need the default layout...we design our own
        setSize(600, 500);
        setLocation(400, 200);

        heading = new JLabel("Register Here");
        heading.setBounds(225, 5, 200, 80);
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

        admission = new JLabel("Admission Year: ");
        admission.setBounds(103, 109, 200, 50);
        admission.setFont(new Font("Arial Black", Font.BOLD, 12));
        admission.setForeground(Color.BLACK);
        String yr[] = { "2022", "2023" };
        cb2 = new JComboBox<>(yr);
        cb2.setBounds(240, 125, 80, 20);
        add(cb2);
        add(admission);

        sic = new JLabel("Enter SIC: ");
        sic.setBounds(103, 139, 200, 80);
        sic.setFont(new Font("Arial Black", Font.BOLD, 12));
        sic.setForeground(Color.BLACK);
        add(sic);

        t1sic = new JTextField();
        t1sic.setBounds(240, 165, 200, 30);
        add(t1sic);

        name = new JLabel("Enter Name: ");
        name.setBounds(103, 180, 200, 80);
        name.setFont(new Font("Arial Black", Font.BOLD, 12));
        name.setForeground(Color.BLACK);
        add(name);

        t2name = new JTextField();
        t2name.setBounds(240, 205, 200, 30);
        add(t2name);

        pass = new JLabel("Enter Password: ");
        pass.setBounds(103, 220, 200, 80);
        pass.setFont(new Font("Arial Black", Font.BOLD, 12));
        pass.setForeground(Color.BLACK);
        add(pass);

        t3pass = new JTextField();
        t3pass.setBounds(240, 245, 200, 30);
        add(t3pass);

        conpass = new JLabel("Confirm Password: ");
        conpass.setBounds(103, 260, 200, 80);
        conpass.setFont(new Font("Arial Black", Font.BOLD, 12));
        conpass.setForeground(Color.BLACK);
        add(conpass);

        t4pass = new JTextField();
        t4pass.setBounds(240, 285, 200, 30);
        add(t4pass);

        submit = new JButton("Submit");
        submit.setBounds(103, 360, 100, 40);
        submit.setBackground(new Color(30, 144, 254));
        submit.setFont(new Font("Arial Black", Font.BOLD, 12));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        reset = new JButton("Reset");
        reset.setBounds(339, 360, 100, 40);
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
            new Registration_std();
        }
		else 
		{
            
            String brn = cb1.getItemAt(cb1.getSelectedIndex());
            branch.setText(brn);
            int year = Integer.parseInt(cb2.getItemAt(cb2.getSelectedIndex()));//select from drop down list
            admission.setText(String.valueOf(year));
            String sic1 = t1sic.getText().toUpperCase();
            String name1 = t2name.getText().toUpperCase();
            String pass1 = t3pass.getText();
			int lengthpass=pass1.length();
			int lengthsic=sic1.length();
			if(lengthsic!=8)//check if the inputed sic below 8 character 
			{
				JOptionPane.showMessageDialog(this,"Invalid SIC!");
				t1sic.setText("");
			}
			else if(lengthpass<8)//check if the inputed password below 8 character
			{
				JOptionPane.showMessageDialog(this,"Password must have 8 character!");
				t3pass.setText("");
				t4pass.setText("");
			}
			
			else
			{
				//process to check whether the inputed password contains alphabet & number or not 
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
				if(containsalpha && containsdigit)//if contains both
				{
					
					String conpass1 = t4pass.getText();
					if (pass1.equals(conpass1)) 
					{
						try 
						{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							//Driver Load...
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","silicon");
							String strinput = "Insert into register_std values('" + brn + "','" + year + "','" + sic1 + "','"+ name1 + "','" + pass1 + "')";
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
					else//if both passwords are not same
					{
						JOptionPane.showMessageDialog(this,"Passwords must be same!");
						setVisible(false);
						new Registration_std();
					}
				}
				else//if password doesnot contain either number or alphabet
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
        new Registration_std();
    }
}
