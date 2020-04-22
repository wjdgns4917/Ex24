package com.one.db;

import java.awt.GridBagLayout;
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

class MyFrame2 extends JFrame {
	JTextField id, title, p, year, price, author;
	JButton preButton, nextButton, insertButton, delButton, clearBtn;
	ResultSet rs;
	Statement stmt;
	
	public MyFrame2() throws SQLException {
		setTitle("select data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Connection con = dbConnection();
		stmt = con.createStatement(
				rs.TYPE_SCROLL_SENSITIVE,
				rs.CONCUR_UPDATABLE
				);
		rs = stmt.executeQuery("select * from books");
		
		
		setLayout(new GridLayout(0, 2));
		add(new Label("ID", JLabel.CENTER));
		add(id = new JTextField());
		add(new Label("TITLE", JLabel.CENTER));
		add(title = new JTextField());
		add(new Label("PUBLISHER", JLabel.CENTER));
		add(p = new JTextField());
		add(new Label("YEAR", JLabel.CENTER));
		add(year = new JTextField());
		add(new Label("PRICE", JLabel.CENTER));
		add(price = new JTextField());
		preButton = new JButton("Prev");
		nextButton = new JButton("Next");
		insertButton = new JButton("Insert");
		delButton = new JButton("Del");
		clearBtn = new JButton("Clear");
		
		
		add(preButton);
		add(nextButton);
		add(insertButton);
		add(delButton);
		add(clearBtn);
		delButton.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				int no=Integer.parseInt(id.getText());
				String sql="DELETE FROM books WHERE BOOK_ID="+no;
				try {
					int result=stmt.executeUpdate(sql);
					if (result==1) {
						System.out.println("삭제 성공");
					}else {
						System.out.println("삭제 실패");
					}
					rs=stmt.executeQuery("select * from books");
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
				String pub=p.getText();
				String yr=year.getText();
				int pri=Integer.parseInt(price.getText());
				
				String sql="INSERT INTO books VALUES"
						+ "("+no+",'"+tit+"','"+pub+"','"+yr+"',"+pri+")";
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
				p.setText("");
				year.setText("");
				price.setText("");
				
			}
		});
		
		// 버튼에 기능추가 조회한 데이터 txtfield에 뿌리기

		preButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("preBtn");
				try {
					boolean ls=rs.isLast();
					System.out.println("last : "+ls);
					rs.previous();
					// System.out.println(rs.getInt("book_id"));
					id.setText(rs.getInt("book_id") + "");
					title.setText(rs.getString("title"));
					p.setText(rs.getString("publisher"));
					year.setText(rs.getString("pyear"));
					price.setText(rs.getInt("price") + "");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("nextBtn");
				try {
					rs.next();
				
					// System.out.println(rs.getInt("book_id"));
					id.setText(rs.getInt("book_id") + "");
					title.setText(rs.getString("title"));
					p.setText(rs.getString("publisher"));
					year.setText(rs.getString("pyear"));
					price.setText(rs.getInt("price") + "");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		setSize(350, 200);
		setVisible(true);
	}

	private static Connection dbConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pass = "123456";
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("연결성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}

public class FrameSelect2 {
	public static void main(String[] args) throws SQLException {
		new MyFrame2();
	}

}
