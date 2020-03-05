package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Faktura {

	JFrame frame;
	private JTable table;
	private JTextField textdata;
	private JTextField text_nazwawys;
	private JTextField text_nazwados;
	private JTextField text_nipdos;
	private JTextField text_adreswys;
	private JTextField text_nipwys;
	private JTextField textnetto;
	private JTextField textbrutto;
	private JTextField textuwagi;
	private JLabel lblUwagi;

	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
	String user = "postgres";
	String password = "admin";

	int maxxxx = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Faktura window = new Faktura();
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
	public Faktura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1050, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 277, 836, 269);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblDataWystawieniaFaktury = new JLabel("data wystawienia faktury");
		lblDataWystawieniaFaktury.setForeground(Color.WHITE);
		lblDataWystawieniaFaktury.setBounds(31, 56, 122, 14);
		frame.getContentPane().add(lblDataWystawieniaFaktury);

		textdata = new JTextField();
		textdata.setBounds(200, 53, 86, 20);
		frame.getContentPane().add(textdata);
		textdata.setColumns(10);

		text_nazwawys = new JTextField();
		text_nazwawys.setBounds(514, 53, 149, 20);
		frame.getContentPane().add(text_nazwawys);
		text_nazwawys.setColumns(10);

		JLabel lblFirmaWystawiajca = new JLabel("Firma Wystawiająca");
		lblFirmaWystawiajca.setForeground(Color.WHITE);
		lblFirmaWystawiajca.setBackground(Color.DARK_GRAY);
		lblFirmaWystawiajca.setBounds(484, 11, 142, 14);
		frame.getContentPane().add(lblFirmaWystawiajca);

		JLabel lblFirmaDostajaca = new JLabel("Firma dostająca");
		lblFirmaDostajaca.setForeground(Color.WHITE);
		lblFirmaDostajaca.setBounds(828, 11, 111, 14);
		frame.getContentPane().add(lblFirmaDostajaca);

		text_nazwados = new JTextField();
		text_nazwados.setBounds(844, 53, 149, 20);
		frame.getContentPane().add(text_nazwados);
		text_nazwados.setColumns(10);

		JTextField text_adresdos = new JTextField();
		text_adresdos.setBounds(844, 84, 149, 20);
		frame.getContentPane().add(text_adresdos);
		text_adresdos.setColumns(10);

		text_nipdos = new JTextField();
		text_nipdos.setBounds(844, 117, 149, 20);
		frame.getContentPane().add(text_nipdos);
		text_nipdos.setColumns(10);

		text_adreswys = new JTextField();
		text_adreswys.setBounds(514, 84, 149, 20);
		frame.getContentPane().add(text_adreswys);
		text_adreswys.setColumns(10);

		text_nipwys = new JTextField();
		text_nipwys.setBounds(514, 115, 149, 20);
		frame.getContentPane().add(text_nipwys);
		text_nipwys.setColumns(10);

		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setForeground(Color.WHITE);
		lblNazwa.setBounds(444, 56, 46, 14);
		frame.getContentPane().add(lblNazwa);

		JLabel label = new JLabel("Nazwa");
		label.setForeground(Color.WHITE);
		label.setBounds(768, 56, 46, 14);
		frame.getContentPane().add(label);

		JLabel lblAdres = new JLabel("Adres");
		lblAdres.setForeground(Color.WHITE);
		lblAdres.setBounds(444, 92, 46, 14);
		frame.getContentPane().add(lblAdres);

		JLabel label_1 = new JLabel("Adres");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(768, 87, 46, 14);
		frame.getContentPane().add(label_1);

		JLabel lblNip = new JLabel("NIP");
		lblNip.setForeground(Color.WHITE);
		lblNip.setBounds(444, 120, 46, 14);
		frame.getContentPane().add(lblNip);

		JLabel label_2 = new JLabel("NIP");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(768, 120, 46, 14);
		frame.getContentPane().add(label_2);

		textnetto = new JTextField();
		textnetto.setBounds(200, 84, 86, 20);
		frame.getContentPane().add(textnetto);
		textnetto.setColumns(10);

		textbrutto = new JTextField();
		textbrutto.setBounds(200, 115, 86, 20);
		frame.getContentPane().add(textbrutto);
		textbrutto.setColumns(10);

		textuwagi = new JTextField();
		textuwagi.setBounds(200, 146, 229, 84);
		frame.getContentPane().add(textuwagi);
		textuwagi.setColumns(10);

		JLabel lblKwotaNetto = new JLabel("kwota netto");
		lblKwotaNetto.setForeground(Color.WHITE);
		lblKwotaNetto.setBounds(31, 87, 86, 14);
		frame.getContentPane().add(lblKwotaNetto);

		JLabel lblNewLabel = new JLabel("Kwota brutto");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(31, 120, 86, 14);
		frame.getContentPane().add(lblNewLabel);

		lblUwagi = new JLabel("uwagi");
		lblUwagi.setForeground(Color.WHITE);
		lblUwagi.setBounds(31, 167, 46, 14);
		frame.getContentPane().add(lblUwagi);

		
		
		/**
		 * załadowanie danych z ostatniej faktury
		 */
		JButton btnZaaduj = new JButton("Załaduj");
		btnZaaduj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int maksymalna = 0;

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con
								.prepareStatement("SELECT max(id_faktura_przyj) from faktura_przyj")) {

					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						maksymalna = rs.getInt(1);
						maxxxx = maksymalna;
					}

					pst.close();
					rs.close();
					con.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT pr.id_faktura_przyjecie, p.id_prod,p.nazwa,p.opis,p.cena,pr.ilosc,sum(p.cena*pr.ilosc) FROM produkt p "
										+ "JOIN kategorie k ON k.id_kat = p.id_kat_pr "
										+ "JOIN przyjecie pr ON pr.id_prod_przy = p.id_prod where id_faktura_przyjecie ="
										+ maksymalna
										+ " GROUP BY  pr.id_faktura_przyjecie,p.id_prod,p.nazwa,p.opis,p.cena,pr.ilosc  ")) { // where
																																// id_zamowienie_prz
																																// =
																																// "+maksymalna

					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
					con.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				String wyst;
				double netto;
				double brutto;
				double zmienna;
				String uwag;
				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT * from faktura_przyj where id_faktura_przyj = " + maksymalna)) {

					ResultSet rs = pst.executeQuery();
					if (rs.next()) {

						wyst = rs.getDate("data_wyst_faktury").toString();
						netto = rs.getDouble("kwota_netto");
						brutto = rs.getDouble("kwota_brutto");
						uwag = rs.getString(5);
						zmienna = (double) (brutto * 10);
						zmienna = (Math.round(zmienna));
						zmienna = zmienna / 10;
						brutto = zmienna;
						textdata.setText(wyst);
						textnetto.setText(netto + " ");
						textbrutto.setText(brutto + " ");
						textuwagi.setText(uwag);

					}

					pst.close();
					rs.close();
					con.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con
								.prepareStatement("SELECT f.nazwa,f.adres,f.nip from faktura_przyj fw "
										+ "JOIN przyjecie p ON p.id_faktura_przyjecie = fw.id_faktura_przyj "
										+ "JOIN zamowienie z ON z.id_zamowienie = p.id_zamowienie_prz "
										+ "JOIN firma f ON f.id_firma = z.id_firma_zam where fw.id_faktura_przyj = "
										+ maksymalna)) {

					ResultSet rs = pst.executeQuery();
					if (rs.next()) {

						text_nipdos.setText(rs.getString(3));
						text_nazwados.setText(rs.getString(1));
						text_adresdos.setText(rs.getString(2));

						text_nazwawys.setText("IKEA POLAND AGH");
						text_adreswys.setText("Krakowska 32, Kraków ");
						text_nipwys.setText("9996548855");

					}

					pst.close();
					rs.close();
					con.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
		});
		btnZaaduj.setBounds(709, 590, 89, 23);
		frame.getContentPane().add(btnZaaduj);

		
		
		/**
		 * dodanie uwag
		 */
		JButton btnDodajUwagi = new JButton("dodaj uwagi");
		btnDodajUwagi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement("UPDATE  faktura_przyj SET uwagi= '"
								+ textuwagi.getText() + "' where id_faktura_przyj = " + maxxxx)) {

					pst.executeUpdate();

					pst.close();

					con.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
		});
		btnDodajUwagi.setBounds(287, 243, 142, 23);
		frame.getContentPane().add(btnDodajUwagi);

		
		
		/**
		 * Powrót do wyborów
		 */
		JButton btnDoWyborw = new JButton("Do wyborów");
		btnDoWyborw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pracownik_zamowienie p = new pracownik_zamowienie();
				p.frame.setVisible(true);
				frame.dispose();

			}
		});
		btnDoWyborw.setBounds(794, 673, 122, 23);
		frame.getContentPane().add(btnDoWyborw);
	}
}
