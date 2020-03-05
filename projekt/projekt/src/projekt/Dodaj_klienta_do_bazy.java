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

public class Dodaj_klienta_do_bazy {

	JFrame frame;
	private JTextField textimie;
	private JTextField textnazwisko;
	private JTextField texttelefon;
	private JTextField textemail;
	private JTextField textid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dodaj_klienta_do_bazy window = new Dodaj_klienta_do_bazy();
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
	public Dodaj_klienta_do_bazy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 863, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setForeground(Color.WHITE);
		lblImie.setBounds(156, 124, 46, 14);
		frame.getContentPane().add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setForeground(Color.WHITE);
		lblNazwisko.setBounds(156, 172, 46, 14);
		frame.getContentPane().add(lblNazwisko);
		
		JLabel lblTelefon = new JLabel("telefon");
		lblTelefon.setForeground(Color.WHITE);
		lblTelefon.setBounds(156, 231, 46, 14);
		frame.getContentPane().add(lblTelefon);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(156, 283, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblNipFirmy = new JLabel("id firmy");
		lblNipFirmy.setForeground(Color.WHITE);
		lblNipFirmy.setBounds(156, 332, 69, 14);
		frame.getContentPane().add(lblNipFirmy);
		
		textimie = new JTextField();
		textimie.setBounds(318, 121, 131, 20);
		frame.getContentPane().add(textimie);
		textimie.setColumns(10);
		
		textnazwisko = new JTextField();
		textnazwisko.setBounds(318, 169, 131, 20);
		frame.getContentPane().add(textnazwisko);
		textnazwisko.setColumns(10);
		
		texttelefon = new JTextField();
		texttelefon.setBounds(318, 228, 131, 20);
		frame.getContentPane().add(texttelefon);
		texttelefon.setColumns(10);
		
		textemail = new JTextField();
		textemail.setBounds(318, 280, 131, 20);
		frame.getContentPane().add(textemail);
		textemail.setColumns(10);
		
		textid = new JTextField();
		textid.setBounds(318, 329, 131, 20);
		frame.getContentPane().add(textid);
		textid.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
				String user = "postgres";
				String password = "admin";
				
			
				
				//int elemente =0;
				try(Connection con = DriverManager.getConnection(url,user,password); 
						PreparedStatement pst = con.prepareStatement("INSERT INTO Klient(   imie, nazwisko, telefon, email,id_firma_klient)"
				                + "VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS))
               // ResultSet rs = pst.executeQuery()) 
				{
					if(textimie.getText().equals("") || textnazwisko.getText().equals("") || texttelefon.getText().equals("") || textemail.getText().equals("") || textid.getText().equals("")) {
						JOptionPane.showMessageDialog(frame, "Proszę wpisac dane");
					}else {
					//pst.setInt(1, Integer.parseInt(tekst_id.getText()));
					//pst.setInit(1, 3);
		            pst.setString(1, (textimie.getText()));
		            pst.setString(2, textnazwisko.getText());
		            pst.setString(3, texttelefon.getText());
		            pst.setString(4,textemail.getText());
					pst.setInt(5, Integer.parseInt(textid.getText()));
					pst.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Dodano pracownika");
					
					pst.close();
					}
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
				
			}
		});
		btnDodaj.setBounds(156, 493, 89, 23);
		frame.getContentPane().add(btnDodaj);
		
		JButton btnWrc = new JButton("Wróć");
		btnWrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnWrc.setBounds(458, 493, 89, 23);
		frame.getContentPane().add(btnWrc);
	}
}
