package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Dodaj_firme_do_bazy {

	JFrame frame;
	private JTextField text_nazwa;
	private JTextField text_adres;
	private JTextField text_NIP;
	private JTextField text_mail;
	private JTextField text_telefon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dodaj_firme_do_bazy window = new Dodaj_firme_do_bazy();
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
	public Dodaj_firme_do_bazy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 816, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dodawanie Firmy ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(343, 44, 139, 14);
		frame.getContentPane().add(lblNewLabel);
		
		text_nazwa = new JTextField();
		text_nazwa.setBounds(168, 145, 496, 20);
		frame.getContentPane().add(text_nazwa);
		text_nazwa.setColumns(10);
		
		JLabel lblNazwa = new JLabel("Nazwa Firmy");
		lblNazwa.setForeground(Color.WHITE);
		lblNazwa.setBounds(51, 148, 79, 14);
		frame.getContentPane().add(lblNazwa);
		
		JLabel lblAdres = new JLabel("Adres");
		lblAdres.setForeground(Color.WHITE);
		lblAdres.setBounds(51, 205, 46, 14);
		frame.getContentPane().add(lblAdres);
		
		text_adres = new JTextField();
		text_adres.setBounds(168, 202, 496, 20);
		frame.getContentPane().add(text_adres);
		text_adres.setColumns(10);
		
		JLabel lblNip = new JLabel("NIP");
		lblNip.setForeground(Color.WHITE);
		lblNip.setBounds(51, 270, 46, 14);
		frame.getContentPane().add(lblNip);
		
		text_NIP = new JTextField();
		text_NIP.setBounds(168, 267, 496, 20);
		frame.getContentPane().add(text_NIP);
		text_NIP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Adres email");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(51, 331, 79, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		text_mail = new JTextField();
		text_mail.setBounds(168, 328, 496, 20);
		frame.getContentPane().add(text_mail);
		text_mail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("telefon");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(51, 396, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		text_telefon = new JTextField();
		text_telefon.setBounds(168, 393, 496, 20);
		frame.getContentPane().add(text_telefon);
		text_telefon.setColumns(10);
		
		JButton Dodaj = new JButton("Dodaj");
		Dodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
				String user = "postgres";
				String password = "admin";

				
				//int elemente =0;
				try(Connection con = DriverManager.getConnection(url,user,password); 
						PreparedStatement pst = con.prepareStatement("INSERT INTO firma(  nazwa, adres, NIP, mail,telefon)"
				                + "VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS))
               // ResultSet rs = pst.executeQuery()) 
				{
					
					//pst.setInt(1, Integer.parseInt(tekst_id.getText()));
					//pst.setInt(1, 3);
		            pst.setString(1, (text_nazwa.getText()));
		            pst.setString(2, text_adres.getText());
		            pst.setString(3, text_NIP.getText());
		            pst.setString(4, text_mail.getText());
					pst.setString(5, text_telefon.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Dodano Firme");
					
				pst.close();
				frame.dispose();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				
			}
		});
		Dodaj.setBounds(485, 447, 89, 23);
		frame.getContentPane().add(Dodaj);
		
		JButton btnWroc = new JButton("wroc");
		btnWroc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnWroc.setBounds(641, 447, 89, 23);
		frame.getContentPane().add(btnWroc);
	}
}
