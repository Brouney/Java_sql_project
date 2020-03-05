package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import java.sql.*;

public class login_sw extends JFrame{

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_sw window = new login_sw();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_sw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnWejdz = new JButton("Wejdz");
		btnWejdz.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		btnWejdz.setForeground(new Color(0, 0, 0));
		btnWejdz.setBackground(new Color(204, 51, 0));
		btnWejdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/**
				 * logowanie do Postgresql
				 */
				String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
				String user = "postgres";
				String password = "admin";
//				
				
                
				/**
				 * ściąganie bazy pracowników, aby się zalogować.
				 */
				int elemente =0;
				try(Connection con = DriverManager.getConnection(url,user,password); 
						PreparedStatement pst = con.prepareStatement("SELECT * FROM pracownik");
                ResultSet rs = pst.executeQuery()) {
				
				
				 
					if(textField.getText().equals("") || textField_1.getText().equals("")  ) {
            		String message = "proszę wpisać dane";
            		        
            		    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
            		        JOptionPane.ERROR_MESSAGE);
            	}
					
					/**
					 * sprawdzenie po każdym rekordzie oraz rozdzielenie na administratora lub pracownika
					 */
            while (rs.next()) {
            	
          
            	if(textField.getText().equals(rs.getString(3)) && textField_1.getText().equals(rs.getString(4)) &&  rs.getInt(2) == 1 ) {
            		elemente++;
            		JOptionPane.showMessageDialog(frame, "zalogowano jako administrator");
            		administrator_dodawanie dod = new administrator_dodawanie();
            		
            		try {
            		dod.frame.setVisible(true);
            		frame.dispose();
            		} catch (Exception e) {
    					e.printStackTrace();
            		}
            		
            		
            		
            	}
            	
            	if(textField.getText().equals(rs.getString(3)) && textField_1.getText().equals(rs.getString(4)) &&  rs.getInt(2) == 2 ) {
            		elemente++;
            		JOptionPane.showMessageDialog(frame, "zalogowano jako pracownik twoje ID "+rs.getInt(1));
            		pracownik_zamowienie dod = new pracownik_zamowienie();
            		
            		try {
            		dod.frame.setVisible(true);
            		frame.dispose();
            		} catch (Exception e) {
    					e.printStackTrace();
            		}
            		
            		
            		
            	}
            	
            	
            }

            	if(elemente ==0) {
            		String message = "proszę wpisać poprawne dane";
    		        
        		    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
					}
            	
            	rs.close();
            	pst.close();
            	
            	
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				
				
				
				
				
			}
		});
		
		
		
		
		
		btnWejdz.setBounds(260, 336, 89, 23);
		frame.getContentPane().add(btnWejdz);
		
		JLabel lblImi = new JLabel("Imię");
		lblImi.setFont(new Font("Verdana", Font.BOLD, 16));
		lblImi.setForeground(Color.WHITE);
		lblImi.setBounds(261, 229, 105, 28);
		frame.getContentPane().add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNazwisko.setForeground(Color.WHITE);
		lblNazwisko.setBounds(261, 268, 105, 28);
		frame.getContentPane().add(lblNazwisko);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.BOLD, 14));
		textField.setForeground(Color.BLACK);
		textField.setBackground(new Color(255, 248, 220));
		textField.setBounds(404, 229, 191, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Verdana", Font.BOLD, 14));
		textField_1.setBackground(new Color(255, 248, 220));
		textField_1.setBounds(404, 268, 191, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblJan = new JLabel("Jan Kowalski -administrator");
		lblJan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJan.setForeground(Color.WHITE);
		lblJan.setBounds(260, 51, 241, 35);
		frame.getContentPane().add(lblJan);
		
		JLabel lblMarekMostowiakpracownik = new JLabel("Marek Mostowiak -pracownik");
		lblMarekMostowiakpracownik.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarekMostowiakpracownik.setForeground(Color.WHITE);
		lblMarekMostowiakpracownik.setBounds(260, 85, 202, 28);
		frame.getContentPane().add(lblMarekMostowiakpracownik);
	}
}
