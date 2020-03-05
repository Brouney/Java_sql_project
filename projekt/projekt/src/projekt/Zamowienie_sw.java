package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;

public class Zamowienie_sw {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zamowienie_sw window = new Zamowienie_sw();
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
	public Zamowienie_sw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1203, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWpisanieZamwienia = new JLabel("Wpisanie zamówienia - wydawanie towaru");
		lblWpisanieZamwienia.setFont(new Font("Verdana", Font.BOLD, 16));
		lblWpisanieZamwienia.setForeground(Color.WHITE);
		lblWpisanieZamwienia.setBounds(380, 11, 400, 38);
		frame.getContentPane().add(lblWpisanieZamwienia);
		
		JLabel lblWpisanieIdPracownika = new JLabel("wpisanie id pracownika");
		lblWpisanieIdPracownika.setForeground(Color.WHITE);
		lblWpisanieIdPracownika.setBounds(152, 120, 167, 14);
		frame.getContentPane().add(lblWpisanieIdPracownika);
		
		JLabel lblNewLabel = new JLabel("Sprawdzenie, czy firma jest w bazie");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(562, 68, 208, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(618, 117, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNip = new JLabel("NIP");
		lblNip.setForeground(Color.WHITE);
		lblNip.setBounds(562, 120, 46, 14);
		frame.getContentPane().add(lblNip);
		
		JButton btnNewButton = new JButton("sprawdz czy firma jest w bazie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(562, 161, 234, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(354, 117, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblIdFirmy = new JLabel("id firmy");
		lblIdFirmy.setForeground(Color.WHITE);
		lblIdFirmy.setBounds(150, 165, 46, 14);
		frame.getContentPane().add(lblIdFirmy);
		
		JLabel lblSprawd = new JLabel("dane klienta");
		lblSprawd.setForeground(Color.WHITE);
		lblSprawd.setBounds(152, 223, 91, 14);
		frame.getContentPane().add(lblSprawd);
		
		JLabel lblImi = new JLabel("Imię");
		lblImi.setForeground(Color.WHITE);
		lblImi.setBounds(152, 263, 46, 14);
		frame.getContentPane().add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setForeground(Color.WHITE);
		lblNazwisko.setBounds(150, 305, 46, 14);
		frame.getContentPane().add(lblNazwisko);
		
		textField_3 = new JTextField();
		textField_3.setBounds(276, 260, 110, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(276, 302, 110, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("sprawdz czy klient jest w bazie");
		btnNewButton_1.setBounds(442, 277, 186, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		textField_5 = new JTextField();
		textField_5.setBounds(354, 162, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(152, 443, 167, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNazwaProduktu = new JLabel("Nazwa Produktu");
		lblNazwaProduktu.setForeground(Color.WHITE);
		lblNazwaProduktu.setBounds(52, 446, 100, 14);
		frame.getContentPane().add(lblNazwaProduktu);
		
		JButton btnNewButton_3 = new JButton("Sprawdz magazyn");
		btnNewButton_3.setBounds(354, 442, 128, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Dodaj do zamówienia");
		btnNewButton_2.setBounds(297, 619, 186, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		textField_7 = new JTextField();
		textField_7.setBounds(198, 620, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblIlo = new JLabel("Ilość");
		lblIlo.setBounds(138, 623, 46, 14);
		frame.getContentPane().add(lblIlo);
		
		JLabel lblDaneDoZamwienia = new JLabel("Dane do Zamówienia");
		lblDaneDoZamwienia.setBounds(164, 68, 120, 14);
		frame.getContentPane().add(lblDaneDoZamwienia);
		
		JComboBox Kategorie = new JComboBox();
		Kategorie.setBounds(152, 375, 28, 20);
		frame.getContentPane().add(Kategorie);
		
		JButton btnNewButton_4 = new JButton("Wstępnie dodaj");
		btnNewButton_4.setBounds(442, 341, 166, 23);
		frame.getContentPane().add(btnNewButton_4);
	}
}
