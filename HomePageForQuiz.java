/*
Before executing these program make sure you have created following databses:
sub_info(branch,subid,name,sem)
regitser_std(branch,year,sic,name,password)
regitser_fac(id,name,password)
que_bank(branch,subid,qno,que,opt1,opt2,opt3,opt4,ans)
score_table(sem,name,sub_name,sem,score)
*/
package quiztest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class HomePageForQuiz extends JFrame implements ActionListener 
{
    JLabel heading, user, password, image;
    JTextField t1user;
    JPasswordField t2pass;
    JButton login, back, register;
	String result;

    HomePageForQuiz() 
	{
        getContentPane().setBackground(Color.GRAY); // pre-defined function used for frame background
        setLayout(null);
        setSize(1010, 800); /* set size and location of frame*/
        setLocation(210, 70);
		
		//inserting image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("sit.png"));
        image = new JLabel(i1);
        image.setBounds(-5, 0, 1010, 400);
        add(image);
		
		heading = new JLabel("QUIZ TEST");
        heading.setBounds(350, 400, 300, 50);
        heading.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        add(heading);

        user = new JLabel("USERNAME");
        user.setBounds(415, 465, 200, 50);
        user.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
        user.setForeground(Color.BLACK);
        add(user);

        password = new JLabel("PASSWORD");
        password.setBounds(415, 540, 200, 50);
        password.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
        password.setForeground(Color.BLACK); // changing font colour
        add(password);

        t1user = new JTextField();
        t1user.setBounds(375, 510, 200, 25);
        t1user.setFont(new Font("Arial Black", Font.BOLD, 20));
        add(t1user);

        t2pass = new JPasswordField();
        t2pass.setBounds(375, 580, 200, 25);
        t2pass.setFont(new Font("Arial Black", Font.BOLD, 20));
        add(t2pass);

        register = new JButton("SignUp");
        register.setBounds(370, 630, 100, 30);
        register.setBackground(new Color(30, 144, 254));
        register.setFont(new Font("Arial Black", Font.BOLD, 15));
        register.setForeground(Color.WHITE);
        register.addActionListener(this);
        add(register);

        login = new JButton("Signin");
        login.setBounds(480, 630, 100, 30);
        login.setBackground(new Color(30, 144, 254));
        login.setFont(new Font("Arial Black", Font.BOLD, 15));
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == login) 
		{
            String struser = t1user.getText().toUpperCase();
            String pass1 = t2pass.getText();
            String substr = struser.substring(0, 1);
            //check for Faculty
            if (substr.equals("F")) 
			{
                try 
				{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    // Driver Load...
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                            "silicon");
                    //retrive password from database
                    String strinput = "select PASSWORD from register_fac where ID='" + struser + "'";
                    Statement smt = con.createStatement();
                    ResultSet set = smt.executeQuery(strinput);
                    if (set.next()) 
					{
                         result = set.getString("PASSWORD");
						
                        
					}
					con.close();
					if(result==null)
					{
						JOptionPane.showMessageDialog(this,"Invalid Username");
						setVisible(false);
						new HomePageForQuiz();
					}
					else
					{
						//check the inputed password is same as database password or not
						if (pass1.equals(result)) 
						{
							setVisible(false);
							new InsertQue(); // directing towards Question Insertion Section
						} 
						else 
						{
							JOptionPane.showMessageDialog(this, "Invalid Password!");
							t2pass.setText("");
						}
					}
                    
                } 
				catch (Exception ob) 
				{
                    System.out.println(ob);
                }

            }
            //check for student
            else 
			{
                try 
				{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    // Driver Load...
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                            "silicon");
                    //retrive password from database
                    String strinput = "select PASSWORD from register_std where SIC='" + struser + "'";
                    Statement smt = con.createStatement();
                    ResultSet set = smt.executeQuery(strinput);
                    if (set.next()) 
					{
                         result = set.getString("PASSWORD");
                        
					}
					con.close();
					if(result==null)
					{
						JOptionPane.showMessageDialog(this,"Invalid Username");
						setVisible(false);
						new HomePageForQuiz();
					}
					else
					{
						//check the inputed password is same as database password or not
						if (pass1.equals(result)) 
						{
							setVisible(false);
							new TestPageForQuiz(struser); // directing towards Exam section
						} 
						else 
						{
							JOptionPane.showMessageDialog(this, "Invalid Password!");
							t2pass.setText("");
						}
					}
                    
                } 
				catch (Exception ob) 
				{
                    System.out.println(ob);
                }
            }
        }
        //for not registered user
        else if (e.getSource() == register) 
		{
			String options[]={"Student","Facult"};
			int choice = JOptionPane.showOptionDialog(
                null,
                "Continue as:",
                "Option Selector",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
			);
			setVisible(false);
			 if (choice == 0) 
			 {
				new Registration_std();
			} 
			else if (choice == 1) 
			{
				new Registration_fac();
			}
        }       
    }

    public static void main(String args[]) 
	{
        new HomePageForQuiz();
    }
}
