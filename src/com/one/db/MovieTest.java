package com.one.db;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class MyFrame extends JFrame{
	JTextField id, title, director, casting, description, min; 
	JButton preButton, nextButton, insertButton, delButton, clearBtn;
	ResultSet rs;
	Statement stmt;

	public MyFrame() throws SQLException {
		setTitle("Movie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Connection con=dbConnection();
		stmt=con.createStatement(
				rs.TYPE_SCROLL_SENSITIVE,
				rs.CONCUR_UPDATABLE);
		rs=stmt.executeQuery("select * from movie");
		
		setLayout(new GridLayout(0,2));
		add(new Label("ID", JLabel.CENTER));
		add(id=new JTextField());
		add(new Label("TITLE", JLabel.CENTER));
		add(title=new JTextField());
		add(new Label("DIRECTOR", JLabel.CENTER));
		add(director=new JTextField());
		add(new Label("CASTING", JLabel.CENTER));
		add(casting=new JTextField());
		add(new Label("DESCRIPTION", JLabel.CENTER));
		add(description=new JTextField());
		add(new Label("DURATION_MIN", JLabel.CENTER));
		add(min=new JTextField());
		
		preButton=new JButton("Prev");
		nextButton=new JButton("Next");
		insertButton=new JButton("Insert");
		delButton=new JButton("Del");
		clearBtn=new JButton("Clear");
		
		add(preButton);
		add(nextButton);
		add(insertButton);
		add(delButton);
		add(clearBtn);
		
		delButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int no=Integer.parseInt(id.getText());
				String sql="DELETE FROM movie WHERE ID="+no;
				try {
					int result=stmt.executeUpdate(sql);
					if (result==1) {
						System.out.println("삭제 성공");
					}else {
						System.out.println("삭제 실패");
					}
					rs=stmt.executeQuery("select * from movie");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int no=Integer.parseInt(id.getText());
				String tit=title.getText();
				String dir=director.getText();
				String cast=casting.getText();
				String des=description.getText();
				String m=min.getText();
				
				String sql="INSERT INTO MOVIE VALUES"
						+ "("+no+",'"+tit+"','"+dir+"','"+cast+"','"+des+"','"+m+"')";
				try {
					int result=stmt.executeUpdate(sql);
					if (result==1) {
						System.out.println("성공");
					}else {
						System.out.println("실패");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				title.setText("");
				director.setText("");
				casting.setText("");
				description.setText("");
				min.setText("");
				
			}
		});
		
		preButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("preBtn");
				try {
					boolean ls=rs.isLast();
					System.out.println("last : "+ls);
					rs.previous();
					id.setText(rs.getInt("ID")+"");
					title.setText(rs.getString("title"));
					director.setText(rs.getString("director"));
					casting.setText(rs.getString("casting"));
					description.setText(rs.getString("description"));
					min.setText(rs.getString("min"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("nextBtn");
				try {
					rs.next();
					
					id.setText(rs.getInt("ID")+"");
					title.setText(rs.getString("title"));
					director.setText(rs.getString("director"));
					casting.setText(rs.getString("casting"));
					description.setText(rs.getString("description"));
					min.setText(rs.getString("min"));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		
		setSize(350,500);
		setVisible(true);
	}
	private static Connection dbConnection() {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, pass);			
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}	
	public class MovieTest{
	public static void main(String[] args) throws SQLException {
		new MyFrame();
	}

}
