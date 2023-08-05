package quiztest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TestPageForQuiz extends JFrame implements ActionListener 
{
	JLabel heading, image, sic, name, sem, sub, namevalue, sicvalue;
	JComboBox<String> cb1, cb2, cb3; // extra variable for future use
	JButton show, next;

	String id, brn, sname,name1,semIs,subid,subname,qname;

	TestPageForQuiz(String id) 
	{
		this.id = id;
		getContentPane().setBackground(Color.GRAY);
		setLayout(null);
		setSize(1010, 800);
		setLocation(210, 70);

		// inserting image
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("sit.png"));
		image = new JLabel(i1);
		image.setBounds(-5, 0, 1010, 400);
		add(image);
		
		//retrieving name from student database & set it to NAME:
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","silicon");
			String strinput = "select NAME from register_std where SIC='" + id + "'";
			Statement smt = con.createStatement();
			ResultSet set = smt.executeQuery(strinput);
			if (set.next()) {
				sname = set.getString("NAME");
			}
			
			con.close();
		} catch (Exception ob) 
		{
			System.out.println(ob);
		}

		heading = new JLabel("QUIZ TEST");
		heading.setBounds(370, 400, 300, 50);
		heading.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
		add(heading);

		sic = new JLabel("SIC:");
		sic.setBounds(200, 485, 100, 30);
		sic.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		sic.setForeground(Color.BLACK);
		add(sic);

		sicvalue = new JLabel(id);
		sicvalue.setBounds(275, 480, 100, 30);
		sicvalue.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		sicvalue.setForeground(Color.RED);
		add(sicvalue);

		name = new JLabel("NAME:");
		name.setBounds(200, 565, 100, 30);
		name.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		name.setForeground(Color.BLACK);
		add(name);

		namevalue = new JLabel(sname);
		namevalue.setBounds(275, 563, 300, 30);
		namevalue.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		namevalue.setForeground(Color.RED);
		add(namevalue);

		sem = new JLabel("SEMESTER: ");
		sem.setBounds(570, 485, 150, 30);
		sem.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		sem.setForeground(Color.BLACK);
		add(sem);

		String yr[] = { "1", "2", "3", "4", "5", "6", "7", "8" };
		cb1 = new JComboBox<>(yr);
		cb1.setBounds(690, 486, 80, 25);
		add(cb1);

		sub = new JLabel("SUBJECT: ");
		sub.setBounds(570, 565, 100, 25);
		sub.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		sub.setForeground(Color.BLACK);
		add(sub);

		cb2 = new JComboBox<>();
		cb2.setBounds(690, 560, 200, 25);
		add(cb2);

		show = new JButton("Show Sub");
		show.setBounds(790, 484, 100, 30);
		show.setBackground(new Color(30, 144, 254));
		show.setFont(new Font("Arial Black", Font.BOLD, 10));
		show.setForeground(Color.WHITE);
		show.addActionListener(this);
		add(show);

		next = new JButton("Next");
		next.setBounds(445, 650, 100, 30);
		next.setBackground(new Color(30, 144, 254));
		next.setFont(new Font("Arial Black", Font.BOLD, 12));
		next.setForeground(Color.WHITE);
		next.addActionListener(this);
		add(next);

		setVisible(true);
	}

	public void showsub() 
	{

		int sem = Integer.parseInt(cb1.getItemAt(cb1.getSelectedIndex()));

		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "silicon");
			String str = "select BRANCH from register_std where SIC='" + id + "'";//retrieving branch from student data base
			Statement st = con.createStatement();
			ResultSet in = st.executeQuery(str);
			if (in.next()) 
			{
				brn = in.getString("BRANCH");
			}
			String strinput = "select NAME from SUB_INFO where BRANCH='" + brn + "' and SEM='" + sem + "'";//retrieving corresponding subject name using branch and semester from subject information data base
			Statement smt = con.createStatement();
			ResultSet set = smt.executeQuery(strinput);
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
			while (set.next()) 
			{
				String name1 = set.getString("NAME");
				model.addElement(name1);
			}
			cb2.setModel(model);//set the subject names in the drop ddown list
			
			String getsubid="select SUBID from sub_info where BRANCH='" + brn + "' and NAME='" + subname+"'";
			Statement smtid=con.createStatement();
			ResultSet setid=smtid.executeQuery(getsubid);
			if(setid.next())
			{
				subid=setid.getString("SUBID");
			}
			
			con.close();
		} 
		catch (Exception ob) 
		{
			System.out.println(ob);
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == show) 
		{
			showsub();
		} 
		else if (e.getSource() == next) 
		{
			semIs=cb1.getItemAt(cb1.getSelectedIndex());
			subname = cb2.getItemAt(cb2.getSelectedIndex());
			try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "silicon");
			 
			 String getsubid="select SUBID from sub_info where BRANCH='" + brn + "' and NAME='" + subname +"'";
			Statement smtid=con.createStatement();
			ResultSet setid=smtid.executeQuery(getsubid);
			if(setid.next())
			{
				subid=setid.getString("SUBID");
			}
			
			//retrieving question from que bank on the basis of branch and subid
			String strQue = "SELECT QUE FROM que_bank WHERE SUBID=? AND BRANCH=? AND QNO=1";
            PreparedStatement smt = con.prepareStatement(strQue);
            smt.setString(1, subid);
            smt.setString(2, brn);

            ResultSet set = smt.executeQuery();
            if (set.next()) {
                qname = set.getString("QUE");
               
            }
            con.close();
        } 
		
		catch (Exception ob) 
		{
			System.out.println(ob);
		}
			if(qname==null)//if there is no question
			{
				JOptionPane.showMessageDialog(this,"Questions have not uploaded yet!");
				setVisible(false);
				new HomePageForQuiz();
			}
			else//forwarding towards exam section
			{
				setVisible(false);
				new RulesForQuiz(subname,id,semIs,brn,sname,subid);
			}
		}
	}

	public static void main(String args[]) 
	{
		new TestPageForQuiz("user");
	}
}
