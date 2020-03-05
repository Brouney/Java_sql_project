package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class administrator_dodawanie extends JFrame{

	JFrame frame;
	private JTextField tekst_id;
	private JTextField tekst_imie;
	private JTextField tekst_nazwisko;
	private JTextField tekst_telefon;
	private JTextField tekst_email;
	
	/**
	 * logowanie do Postgresql
	 */
	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
	String user = "postgres";
	String password = "admin";

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					administrator_dodawanie window = new administrator_dodawanie();
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
	public administrator_dodawanie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 902, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDodawaniePracownikw = new JLabel("Dodawanie pracowników");
		lblDodawaniePracownikw.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDodawaniePracownikw.setForeground(Color.WHITE);
		lblDodawaniePracownikw.setBounds(200, 121, 385, 75);
		frame.getContentPane().add(lblDodawaniePracownikw);
		
		JLabel lblIdUprawnie = new JLabel("id uprawnień");
		lblIdUprawnie.setFont(new Font("Verdana", Font.BOLD, 14));
		lblIdUprawnie.setForeground(Color.WHITE);
		lblIdUprawnie.setBackground(Color.WHITE);
		lblIdUprawnie.setBounds(200, 207, 112, 36);
		frame.getContentPane().add(lblIdUprawnie);
		
		JLabel lblImi = new JLabel("Imię");
		lblImi.setForeground(Color.WHITE);
		lblImi.setBackground(Color.WHITE);
		lblImi.setFont(new Font("Verdana", Font.BOLD, 14));
		lblImi.setBounds(200, 254, 112, 36);
		frame.getContentPane().add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNazwisko.setForeground(Color.WHITE);
		lblNazwisko.setBounds(200, 301, 112, 36);
		frame.getContentPane().add(lblNazwisko);
		
		JLabel lblTelefon = new JLabel("telefon");
		lblTelefon.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTelefon.setForeground(Color.WHITE);
		lblTelefon.setBounds(200, 348, 112, 40);
		frame.getContentPane().add(lblTelefon);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Verdana", Font.BOLD, 14));
		lblEmail.setBounds(200, 399, 112, 40);
		frame.getContentPane().add(lblEmail);
		
		tekst_id = new JTextField();
		tekst_id.setFont(new Font("Verdana", Font.BOLD, 13));
		tekst_id.setBackground(new Color(255, 250, 205));
		tekst_id.setForeground(new Color(0, 0, 0));
		tekst_id.setBounds(323, 207, 182, 36);
		frame.getContentPane().add(tekst_id);
		tekst_id.setColumns(10);
		
		tekst_imie = new JTextField();
		tekst_imie.setFont(new Font("Verdana", Font.BOLD, 13));
		tekst_imie.setBackground(new Color(255, 248, 220));
		tekst_imie.setBounds(323, 254, 182, 36);
		frame.getContentPane().add(tekst_imie);
		tekst_imie.setColumns(10);
		
		tekst_nazwisko = new JTextField();
		tekst_nazwisko.setFont(new Font("Verdana", Font.BOLD, 13));
		tekst_nazwisko.setBackground(new Color(255, 248, 220));
		tekst_nazwisko.setBounds(323, 301, 182, 36);
		frame.getContentPane().add(tekst_nazwisko);
		tekst_nazwisko.setColumns(10);
		
		tekst_telefon = new JTextField();
		tekst_telefon.setFont(new Font("Verdana", Font.BOLD, 13));
		tekst_telefon.setBackground(new Color(255, 248, 220));
		tekst_telefon.setBounds(323, 348, 182, 40);
		frame.getContentPane().add(tekst_telefon);
		tekst_telefon.setColumns(10);
		
		tekst_email = new JTextField();
		tekst_email.setFont(new Font("Verdana", Font.BOLD, 13));
		tekst_email.setBackground(new Color(255, 248, 220));
		tekst_email.setBounds(323, 399, 182, 40);
		frame.getContentPane().add(tekst_email);
		tekst_email.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				/**
				 * dodawanie pracownika 
				 */
				int elemente =0;
				try(Connection con = DriverManager.getConnection(url,user,password); 
						PreparedStatement pst = con.prepareStatement("INSERT INTO pracownik(  id_uprawnienia, imie, nazwisko, telefon, email)"
				                + "VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS))
               
				{
					
					
		            pst.setInt(1, Integer.parseInt(tekst_id.getText()));
		            pst.setString(2, tekst_imie.getText());
		            pst.setString(3, tekst_nazwisko.getText());
		            pst.setString(4, tekst_telefon.getText());
					pst.setString(5, tekst_email.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Dodano pracownika");
					
				pst.close();	
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				
				
				
				
			}
		});
		btnNewButton.setBackground(new Color(255, 248, 220));
		btnNewButton.setBounds(416, 485, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		/**
		 * funkcja przekierowywująca na okno "logowanie"
		 */
		JButton btnNewButton_1 = new JButton("Logowanie");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login_sw dod = new login_sw();
        		
        		try {
        		dod.frame.setVisible(true);
        		frame.dispose();
        		} catch (Exception e) {
					e.printStackTrace();
        		}
        		
        		
			}
		});
		btnNewButton_1.setBounds(762, 485, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
