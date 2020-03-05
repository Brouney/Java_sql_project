package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Przyjecie_towaru {

	JFrame frame;
	private JTextField text_idpracownika;
	private JTextField text_idfirmy;
	private JTextField text_imie;
	private JTextField text_nazwisko;
	private JTextField text_NIP;
	private JTextField text_dodaj_kategorie;
	private JTextField text_nazwa;
	private JTextField text_ilosc;
	private JTextField text_opis;
	private JTextField text_kategoria;
	private JTextField text_cena;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JComboBox<String> comboBox;
	private int KATEGORIA = 0;
	private double maxsuma = 0;

	/**
	 * logowanie do Postgresql
	 */
	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
	String user = "postgres";
	String password = "admin";

	private JTextField text_IDPROD;
	private JTextField text_idklienta;
	private JTextField text_idusuwanie;

	private int todel_id_szcz = 0;

	private int idzzamowienia = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Przyjecie_towaru window = new Przyjecie_towaru();
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
	public Przyjecie_towaru() {
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

		JLabel lblWpisanieIdPracownika = new JLabel("Wpisanie id pracownika");
		lblWpisanieIdPracownika.setForeground(Color.WHITE);
		lblWpisanieIdPracownika.setBounds(139, 84, 135, 14);
		frame.getContentPane().add(lblWpisanieIdPracownika);

		text_idpracownika = new JTextField();
		text_idpracownika.setBounds(318, 81, 86, 20);
		frame.getContentPane().add(text_idpracownika);
		text_idpracownika.setColumns(10);

		JLabel lblIdFirmy = new JLabel("id firmy");
		lblIdFirmy.setForeground(Color.WHITE);
		lblIdFirmy.setBounds(139, 115, 107, 14);
		frame.getContentPane().add(lblIdFirmy);

		text_idfirmy = new JTextField();
		text_idfirmy.setBounds(318, 112, 86, 20);
		frame.getContentPane().add(text_idfirmy);
		text_idfirmy.setColumns(10);

		JLabel lblDaneKlienta = new JLabel("Dane Klienta");
		lblDaneKlienta.setForeground(Color.WHITE);
		lblDaneKlienta.setBounds(132, 151, 79, 14);
		frame.getContentPane().add(lblDaneKlienta);

		JLabel lblImi = new JLabel("Imię");
		lblImi.setForeground(Color.WHITE);
		lblImi.setBounds(132, 176, 46, 14);
		frame.getContentPane().add(lblImi);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setForeground(Color.WHITE);
		lblNazwisko.setBounds(132, 201, 46, 14);
		frame.getContentPane().add(lblNazwisko);

		text_imie = new JTextField();
		text_imie.setBounds(265, 173, 100, 20);
		frame.getContentPane().add(text_imie);
		text_imie.setColumns(10);

		text_nazwisko = new JTextField();
		text_nazwisko.setBounds(265, 198, 100, 20);
		frame.getContentPane().add(text_nazwisko);
		text_nazwisko.setColumns(10);

		JLabel lblSprawdzenieCzyFirma = new JLabel("Sprawdzenie, czy firma jest w bazie");
		lblSprawdzenieCzyFirma.setForeground(Color.WHITE);
		lblSprawdzenieCzyFirma.setBounds(522, 72, 184, 14);
		frame.getContentPane().add(lblSprawdzenieCzyFirma);

		JLabel lblNip = new JLabel("NIP");
		lblNip.setForeground(Color.WHITE);
		lblNip.setBounds(506, 97, 46, 14);
		frame.getContentPane().add(lblNip);

		text_NIP = new JTextField();
		text_NIP.setBounds(556, 94, 116, 20);
		frame.getContentPane().add(text_NIP);
		text_NIP.setColumns(10);

		/**
		 * sprawdzanie firmy - kliknięcie buttonu
		 */
		JButton btnSprawdzCzyFirma = new JButton("sprawdz, czy firma jest w bazie");
		btnSprawdzCzyFirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/**
				 * poproszenie o bazę firm zarejestrowanych w bazie oraz sprawdzenie z pola
				 * 
				 * @elemente - zmienna czy jest element.
				 */
				int elemente = 0;
				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement("SELECT * FROM Firma");
						ResultSet rs = pst.executeQuery()) {

					if (text_NIP.getText().equals("")) {
						String message = "proszę wpisać dane";

						JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					}

					while (rs.next()) {

						if (text_NIP.getText().equals(rs.getString(4))) {
							elemente++;
							JOptionPane.showMessageDialog(frame,
									"firma jest w bazie: \nnazwa: " + rs.getString(2) + "\nadres: " + rs.getString(3)
											+ "\nNIP: " + rs.getString(4) + "\nmail: " + rs.getString(5) + "\ntelefon: "
											+ rs.getString(6));

							text_NIP.setText(rs.getString(4));
							text_idfirmy.setText(rs.getString(1));

							try {

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					}

					if (elemente == 0 && !text_NIP.getText().equals("")) {

						JOptionPane.showMessageDialog(frame, "firmy nie ma w bazie");
						Dodaj_firme_do_bazy dod = new Dodaj_firme_do_bazy();

						try {
							dod.frame.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					rs.close();
					pst.close();

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		btnSprawdzCzyFirma.setBackground(new Color(255, 248, 220));
		btnSprawdzCzyFirma.setBounds(759, 93, 212, 23);
		frame.getContentPane().add(btnSprawdzCzyFirma);

		/**
		 * Sprawdzenie klientów danej poprzez kliknięcie
		 */
		JButton btnsprawdzklientow = new JButton("Sprawdz klientow");
		btnsprawdzklientow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * Ściągnięcie klienta z danej firmy
				 */
				int elemente = 0;
				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT * FROM Klient WHERE id_firma_klient = " + text_idfirmy.getText());
						ResultSet rs = pst.executeQuery()) {
					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception ex) {
				}
			}

		});
		btnsprawdzklientow.setBounds(152, 260, 212, 23);
		frame.getContentPane().add(btnsprawdzklientow);

		/**
		 * Dodanie zamówienia do bazy
		 */
		JButton btnWstpnieDodaj = new JButton("Wstępnie dodaj");
		btnWstpnieDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (text_idpracownika.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Wpisz id pracownika");
				} else {

					/**
					 * wpisanie zamówienia do tabeli zamówienie.
					 */
					try (Connection con = DriverManager.getConnection(url, user, password);

							PreparedStatement pst = con.prepareStatement(
									"INSERT INTO zamowienie(  wydanie_lub_przyjecie_towaru,id_pracownik_fk,id_firma_zam,id_klient_fk,data_realizacji,termin_platnosci)"
											+ "VALUES(?,?,?,?,?,?) ",
									Statement.RETURN_GENERATED_KEYS))

					{

						java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

						pst.setString(1, "przyjecie");
						pst.setInt(2, Integer.parseInt(text_idpracownika.getText()));
						pst.setInt(3, Integer.parseInt(text_idfirmy.getText()));
						pst.setInt(4, Integer.parseInt(text_idklienta.getText()));
						pst.setDate(5, date);
						pst.setDate(6, date);

						pst.executeUpdate();
						JOptionPane.showMessageDialog(frame, "Dodano zamowienie");

						pst.close();

						con.close();
					} catch (SQLException exx) {
						System.out.println(exx.getMessage());
					}

					/**
					 * Ściągnięcie wartości id aktualnego zamówienia oraz przypisanie to do zmiennej
					 * idzzamowienia
					 */

					try (Connection con = DriverManager.getConnection(url, user, password);

							PreparedStatement pst = con.prepareStatement("SELECT max(id_zamowienie) from zamowienie ",
									Statement.RETURN_GENERATED_KEYS)) {

						ResultSet rs = pst.executeQuery();

						while (rs.next()) {
							idzzamowienia = rs.getInt(1);

						}
						JOptionPane.showMessageDialog(frame, "Dodano zamowienie z id = " + idzzamowienia);
						pst.close();
						con.close();
					} catch (SQLException exx) {
						System.out.println(exx.getMessage());
					}

				}
			}

		});
		btnWstpnieDodaj.setBounds(152, 327, 122, 23);
		frame.getContentPane().add(btnWstpnieDodaj);

		/**
		 * Ściąganie produktu z magazynu oraz pokazanie zawartości w tabeli.
		 */
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int ile = 0;
				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT p.id_prod,p.nazwa,p.opis,p.cena,m.ilosc FROM produkt p JOIN magazyn m ON p.id_prod = m.id_prod_m JOIN kategorie k ON k.id_kat = p.id_kat_pr WHERE k.opis = ?")) {

					pst.setString(1, (String) comboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					table_2.setModel(DbUtils.resultSetToTableModel(rs));

					text_kategoria.setText((String) comboBox.getSelectedItem());

					pst.close();
					rs.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
		});
		comboBox.setBounds(139, 361, 135, 20);
		frame.getContentPane().add(comboBox);

		JLabel lblJeliBrakujeKategorii = new JLabel("Jeśli brakuje kategorii - dodaj");
		lblJeliBrakujeKategorii.setForeground(Color.WHITE);
		lblJeliBrakujeKategorii.setBounds(403, 364, 193, 14);
		frame.getContentPane().add(lblJeliBrakujeKategorii);

		text_dodaj_kategorie = new JTextField();
		text_dodaj_kategorie.setBounds(606, 361, 132, 20);
		frame.getContentPane().add(text_dodaj_kategorie);
		text_dodaj_kategorie.setColumns(10);

		/**
		 * Dodanie kategorii
		 */
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement("INSERT INTO kategorie(  opis)" + "VALUES(?)",
								Statement.RETURN_GENERATED_KEYS))

				{

					pst.setString(1, text_dodaj_kategorie.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Dodano Kategorie");

					pst.close();
					con.close();
				} catch (SQLException exx) {
					System.out.println(exx.getMessage());
				}
			}

		});
		btnDodaj.setBounds(781, 360, 89, 23);
		frame.getContentPane().add(btnDodaj);

		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setForeground(Color.WHITE);
		lblNazwa.setBounds(139, 418, 46, 14);
		frame.getContentPane().add(lblNazwa);

		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setForeground(Color.WHITE);
		lblOpis.setBounds(139, 468, 46, 14);
		frame.getContentPane().add(lblOpis);

		JLabel lblCena = new JLabel("Cena/szt");
		lblCena.setForeground(Color.WHITE);
		lblCena.setBounds(139, 493, 46, 14);
		frame.getContentPane().add(lblCena);

		text_nazwa = new JTextField();
		text_nazwa.setBounds(265, 415, 161, 20);
		frame.getContentPane().add(text_nazwa);
		text_nazwa.setColumns(10);

		text_ilosc = new JTextField();
		text_ilosc.setBounds(265, 440, 260, 20);
		frame.getContentPane().add(text_ilosc);
		text_ilosc.setColumns(10);

		text_opis = new JTextField();
		text_opis.setBounds(265, 465, 260, 20);
		frame.getContentPane().add(text_opis);
		text_opis.setColumns(10);

		text_kategoria = new JTextField();
		text_kategoria.setBounds(265, 392, 139, 20);
		frame.getContentPane().add(text_kategoria);
		text_kategoria.setColumns(10);

		JLabel lblKategoria = new JLabel("Kategoria");
		lblKategoria.setForeground(Color.WHITE);
		lblKategoria.setBounds(139, 392, 46, 14);
		frame.getContentPane().add(lblKategoria);

		JLabel lblIlo = new JLabel("Ilość");
		lblIlo.setForeground(Color.WHITE);
		lblIlo.setBounds(139, 443, 46, 14);
		frame.getContentPane().add(lblIlo);

		text_cena = new JTextField();
		text_cena.setBounds(265, 490, 260, 20);
		frame.getContentPane().add(text_cena);
		text_cena.setColumns(10);

		/**
		 * Sprawdzenie czy produkt znajduje się w bazie.
		 */
		JButton btnSprawdz = new JButton("Sprawdz");
		btnSprawdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT * FROM produkt WHERE nazwa = '" + text_nazwa.getText() + "' ");
						ResultSet rs = pst.executeQuery()) {
					table_2.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception ex) {
				}

			}
		});
		btnSprawdz.setBounds(436, 414, 89, 23);
		frame.getContentPane().add(btnSprawdz);

		/**
		 * Dodanie do bazy, gdy nie ma danych o dany produkcie
		 */
		JButton btnDodajGdyNie = new JButton("Dodaj, gdy nie ma danych");
		btnDodajGdyNie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = DriverManager.getConnection(url, user, password);

					PreparedStatement pst = con.prepareStatement(
							"SELECT id_kat from kategorie where opis =  '" + text_kategoria.getText() + "' ",
							Statement.RETURN_GENERATED_KEYS);

					ResultSet rss = pst.executeQuery();

					while (rss.next()) {
						KATEGORIA = rss.getInt(1);
					}

					pst.close();
					rss.close();

					pst = con.prepareStatement("INSERT INTO produkt( id_kat_pr,nazwa, opis,cena)" + "VALUES(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

					pst.setInt(1, KATEGORIA);

					pst.setString(2, text_nazwa.getText());
					pst.setString(3, text_opis.getText());
					pst.setDouble(4, Double.parseDouble(text_cena.getText()));

					pst.executeUpdate();

					JOptionPane.showMessageDialog(frame, "Dodano produkt");

					pst = con.prepareStatement("SELECT id_prod FROM produkt WHERE nazwa = '" + text_nazwa.getText()
							+ "' AND id_kat_pr = " + KATEGORIA, Statement.RETURN_GENERATED_KEYS);
					rss = pst.executeQuery();
					int idd = 0;
					while (rss.next()) {
						idd = rss.getInt(1);
					}

					pst.close();

					pst = con.prepareStatement("INSERT INTO magazyn (id_prod_m,ilosc,miejsce)" + "VALUES(?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					pst.setInt(1, idd);

					pst.setInt(2, 0);
					pst.setString(3, "nie przydzielono miejsca");
					pst.executeUpdate();
					JOptionPane.showMessageDialog(frame, "kategoria wynosi: " + idd);

					pst.close();
					con.close();
				} catch (SQLException exx) {
					System.out.println(exx.getMessage());
				}

			}
		});
		btnDodajGdyNie.setBounds(20, 691, 201, 23);
		frame.getContentPane().add(btnDodajGdyNie);

		/**
		 * Dodanie do szczegółowego zamówienia.
		 */
		JButton btnDodaj_1 = new JButton("Dodaj ");
		btnDodaj_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (text_ilosc.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Wpisz ilosc zamowienia");
				} else {

					try (Connection con = DriverManager.getConnection(url, user, password);
							PreparedStatement pst = con.prepareStatement(
									"INSERT INTO szcz_zam( id_zamowienie_szcz,id_prod_szcz,ilosc)" + "VALUES(?,?,?)",
									Statement.RETURN_GENERATED_KEYS))

					{

						pst.setInt(1, idzzamowienia);
						pst.setInt(2, Integer.parseInt(text_IDPROD.getText()));
						pst.setInt(3, Integer.parseInt(text_ilosc.getText()));

						pst.executeUpdate();
						JOptionPane.showMessageDialog(frame, "Dodano do zamowienia");

						pst.close();
						con.close();
					} catch (SQLException exx) {
						JOptionPane.showMessageDialog(frame, "Proszę dodać wstępnie zamówienie");

						System.out.println(exx.getMessage());
					}

				}
			}
		});
		btnDodaj_1.setBounds(436, 691, 89, 23);
		frame.getContentPane().add(btnDodaj_1);

		/**
		 * Odświeżenie tabeli ze szczegółowym zamówieniem.
		 */
		JButton btnOdwie = new JButton("odśwież");
		btnOdwie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT sz.id_szcz,p.nazwa,p.cena,sz.ilosc,SUM(p.cena*sz.ilosc) from produkt p join szcz_zam sz ON p.id_prod = sz.id_prod_szcz AND sz.id_zamowienie_szcz = "
										+ idzzamowienia + " group by p.nazwa,p.cena,sz.ilosc,sz.id_szcz")) {

					ResultSet rs = pst.executeQuery();

					table_1.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnOdwie.setBounds(792, 691, 89, 23);
		frame.getContentPane().add(btnOdwie);

		/**
		 * Zaznaczenie z tabeli klienta, aby przypisać zaznaczenie do pól tekstowych.
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int wiersz = table.getSelectedRow();
				String ID = (table.getModel().getValueAt(wiersz, 0)).toString();
				String imie = (table.getModel().getValueAt(wiersz, 1)).toString();
				String nazwisko = (table.getModel().getValueAt(wiersz, 2)).toString();

				text_imie.setText(imie);
				text_nazwisko.setText(nazwisko);
				text_idklienta.setText(ID);

			}
		});
		scrollPane.setBounds(467, 171, 538, 112);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		/**
		 * dodanie klienta, otwiera frame aby dodać klienta
		 */
		JButton btnDodajKlienta = new JButton("Dodaj klienta");
		btnDodajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dodaj_klienta_do_bazy dod = new Dodaj_klienta_do_bazy();
				dod.frame.setVisible(true);
			}
		});
		btnDodajKlienta.setBounds(153, 293, 213, 23);
		frame.getContentPane().add(btnDodajKlienta);

		/**
		 * zaznaczenie jakie id elementów usunąć z tabeli ze szczegółowym zamówieniem
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int wiersz = table_1.getSelectedRow();
				String ID = (table_1.getModel().getValueAt(wiersz, 0)).toString();
				text_idusuwanie.setText(ID);

			}
		});
		scrollPane_1.setBounds(551, 418, 454, 265);
		frame.getContentPane().add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 518, 515, 162);
		frame.getContentPane().add(scrollPane_2);

		/**
		 * wpisanie elementów z tabeli z magazynem do pól tekstowych
		 */
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int wiersz = table_2.getSelectedRow();
				String ID = (table_2.getModel().getValueAt(wiersz, 0)).toString();
				String nazwa = (table_2.getModel().getValueAt(wiersz, 1)).toString();
				String opis = (table_2.getModel().getValueAt(wiersz, 2)).toString();
				String cena = (table_2.getModel().getValueAt(wiersz, 3)).toString();

				text_nazwa.setText(nazwa);
				text_opis.setText(opis);
				text_cena.setText(cena);
				text_IDPROD.setText(ID);

			}
		});
		scrollPane_2.setViewportView(table_2);

		JLabel lblKlienci = new JLabel("Klienci");
		lblKlienci.setForeground(Color.WHITE);
		lblKlienci.setBounds(556, 151, 46, 14);
		frame.getContentPane().add(lblKlienci);

		/**
		 * usuwanie klienta z bazy
		 */
		JButton btnUsunKlienta = new JButton("usun klienta");
		btnUsunKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnUsunKlienta.setBounds(882, 294, 123, 23);
		frame.getContentPane().add(btnUsunKlienta);

		text_IDPROD = new JTextField();
		text_IDPROD.setBounds(380, 692, 46, 20);
		frame.getContentPane().add(text_IDPROD);
		text_IDPROD.setColumns(10);

		/**
		 * Załadowanie magazynu, czyli załadowanie elementów do comboBoxa, poprzez
		 * kliknięcie wyświetlane będą poszczególne wartości
		 */
		JButton btnZaadujMagazyn = new JButton("Załaduj magazyn");
		btnZaadujMagazyn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				comboBox.removeAllItems();
				int elemente = 0;
				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement("SELECT * FROM kategorie ");
						ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						comboBox.addItem(rs.getString("opis"));
					}

					pst.close();
					rs.close();
				} catch (Exception ex) {
				}

			}
		});
		btnZaadujMagazyn.setBounds(10, 360, 122, 23);
		frame.getContentPane().add(btnZaadujMagazyn);

		text_idklienta = new JTextField();
		text_idklienta.setBounds(265, 223, 100, 20);
		frame.getContentPane().add(text_idklienta);
		text_idklienta.setColumns(10);

		JLabel lblIdKlienta = new JLabel("id klienta");
		lblIdKlienta.setForeground(Color.WHITE);
		lblIdKlienta.setBounds(132, 226, 46, 14);
		frame.getContentPane().add(lblIdKlienta);

		/**
		 * Generowanie faktury Opis szczegółowy znajduje się w przepływie danych.
		 */
		JButton btnZaaduj = new JButton("faktura");
		btnZaaduj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int faktury = 0;
				int updateilosc = 0;

				int rows = 0;
				rows = table_1.getRowCount();
				JOptionPane.showMessageDialog(frame, "jest tyle rows : " + rows);

				try {
					Connection con = DriverManager.getConnection(url, user, password);
					PreparedStatement pst = con.prepareStatement(
							"SELECT sz.id_prod_szcz,sz.ilosc from produkt p join szcz_zam sz ON p.id_prod = sz.id_prod_szcz AND sz.id_zamowienie_szcz = "
									+ idzzamowienia + " group by sz.ilosc,sz.id_prod_szcz");

					ResultSet rs = pst.executeQuery();

					while (rs.next()) {

						try {
							Connection cont = DriverManager.getConnection(url, user, password);
							PreparedStatement pstt = cont.prepareStatement(
									"INSERT INTO przyjecie( id_zamowienie_prz,id_prod_przy,ilosc,id_faktura_przyjecie)"
											+ "VALUES(?,?,?,NULL)",
									Statement.RETURN_GENERATED_KEYS);

							pstt.setInt(1, idzzamowienia);
							pstt.setInt(2, rs.getInt(1));
							pstt.setInt(3, rs.getInt(2));

							pstt.executeUpdate();

							pstt.close();
							cont.close();
						} catch (SQLException exxxx) {
							System.out.println(exxxx.getMessage() + " exxxx ");
						}

					}

					JOptionPane.showMessageDialog(frame, "dodano do przyjecia poprawnie");

					pst.close();
					rs.close();

					try {
						pst = con.prepareStatement(
								"SELECT SUM(p.cena*sz.ilosc) from produkt p join szcz_zam sz ON p.id_prod = sz.id_prod_szcz AND sz.id_zamowienie_szcz = "
										+ idzzamowienia);
						ResultSet rsss = pst.executeQuery();
						while (rsss.next()) {
							maxsuma = rsss.getDouble(1);
						}
					} catch (Exception exxc) {
					}

					JOptionPane.showMessageDialog(frame, "suma wynosi " + maxsuma);

				} catch (Exception ex) {
					System.out.println(ex.getMessage() + "ex ");
				}

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"INSERT INTO faktura_przyj( data_wyst_faktury,kwota_netto,kwota_brutto,uwagi)"
										+ "VALUES(?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS))

				{
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

					pst.setDate(1, date);
					pst.setDouble(2, maxsuma);
					pst.setDouble(3, maxsuma * 1.23);
					pst.setString(4, "");

					pst.executeUpdate();
					ResultSet rs = pst.getGeneratedKeys();

					if (rs.next()) {
						faktury = rs.getInt(1);
					}
					JOptionPane.showMessageDialog(frame, "Dodano do faktury");

					pst.close();
					con.close();
				} catch (SQLException exx) {
					System.out.println(exx.getMessage() + "  exx  ");
				}

				try {
					Connection con = DriverManager.getConnection(url, user, password);
					PreparedStatement pst = con
							.prepareStatement("SELECT id_przy from przyjecie where id_zamowienie_prz = " + idzzamowienia
									+ " and id_faktura_przyjecie IS NULL");
					ResultSet rsss = pst.executeQuery();
					while (rsss.next()) {

						try {
							Connection conn = DriverManager.getConnection(url, user, password);
							PreparedStatement pstn = conn
									.prepareStatement("UPDATE  przyjecie SET  id_faktura_przyjecie = " + faktury
											+ " WHERE id_przy = " + rsss.getInt(1));

							pstn.executeUpdate();
							conn.close();
							pstn.close();

						} catch (Exception exxcc) {
							exxcc.getMessage();
						}

					}

					pst.close();
					con.close();

				} catch (Exception exxc) {
					exxc.getMessage();
				}

				try {
					Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement pst = conn.prepareStatement(
							"SELECT id_prod_przy,ilosc from przyjecie where  id_faktura_przyjecie = " + faktury);
					ResultSet rsssn = pst.executeQuery();

					while (rsssn.next()) {
						try {

							PreparedStatement pstn = conn
									.prepareStatement("SELECT miejsce,ilosc from magazyn where id_prod_m ="
											+ rsssn.getInt(1) + " AND miejsce = 'nie przydzielono miejsca'");
							ResultSet rsssnn = pstn.executeQuery();

							if (rsssnn.next()) {
								updateilosc = rsssnn.getInt(2) + rsssn.getInt(2);
//								
								try {

									PreparedStatement pstnt = conn.prepareStatement(
											"UPDATE  magazyn SET  ilosc = " + updateilosc + " WHERE id_prod_m = "
													+ rsssn.getInt(1) + " AND miejsce ='nie przydzielono miejsca'");
									pstnt.executeUpdate();

									pstnt.close();
								} catch (Exception exxcc) {
									exxcc.getMessage();
								}

								updateilosc = 0;

							} else {

								try {
									PreparedStatement pstnt = conn.prepareStatement(
											"INSERT INTO magazyn (id_prod_m,ilosc,miejsce)" + "VALUES(?,?,?)",
											Statement.RETURN_GENERATED_KEYS);

									pstnt.setInt(1, rsssn.getInt(1));

									pstnt.setInt(2, rsssn.getInt(2));
									pstnt.setString(3, "nie przydzielono miejsca");
									pstnt.executeUpdate();
									pstnt.close();
								} catch (Exception exxcc) {
									exxcc.getMessage();
								}

							}
							pstn.close();

						} catch (Exception exxcc) {
							exxcc.getMessage();
						}

					}

					conn.close();
					pst.close();

				} catch (Exception exxcc) {
					exxcc.getMessage();
				}

				Faktura fak = new Faktura();
				fak.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnZaaduj.setBounds(916, 691, 89, 23);
		frame.getContentPane().add(btnZaaduj);

		/**
		 * usuwanie wiersza ze szczegółowego zamówienia.
		 */
		JButton btnUsunWiersz = new JButton("usun wiersz");
		btnUsunWiersz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement("DELETE FROM szcz_zam WHERE id_szcz = ?"))

				{

					pst.setInt(1, Integer.parseInt(text_idusuwanie.getText()));

					pst.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Usunięto " + Integer.parseInt(text_idusuwanie.getText()));

					pst.close();
					con.close();
				} catch (SQLException exx) {
					System.out.println(exx.getMessage());
				}

			}
		});
		btnUsunWiersz.setBounds(649, 691, 89, 23);
		frame.getContentPane().add(btnUsunWiersz);

		text_idusuwanie = new JTextField();
		text_idusuwanie.setBounds(594, 692, 53, 20);
		frame.getContentPane().add(text_idusuwanie);
		text_idusuwanie.setColumns(10);

		/**
		 * odświeżenie tabeli z produktami.
		 */
		JButton btnOdswiez = new JButton("odswiez");
		btnOdswiez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT p.id_prod,p.nazwa,p.opis,p.cena,m.ilosc FROM produkt p JOIN magazyn m ON p.id_prod = m.id_prod_m JOIN kategorie k ON k.id_kat = p.id_kat_pr WHERE k.opis = ?")) {

					pst.setString(1, (String) comboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					table_2.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnOdswiez.setBounds(276, 691, 89, 23);
		frame.getContentPane().add(btnOdswiez);

		JLabel lblPrzyj = new JLabel("PRZYJECIE TOWARU");
		lblPrzyj.setFont(new Font("Verdana", Font.BOLD, 16));
		lblPrzyj.setForeground(Color.WHITE);
		lblPrzyj.setBounds(449, 11, 198, 26);
		frame.getContentPane().add(lblPrzyj);

		
		
		/*
		 * odpalenie okna do wyboru
		 */
		JButton btnWrcDoPoprzedniego = new JButton("Wróc do poprzedniego okna");
		btnWrcDoPoprzedniego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pracownik_zamowienie p = new pracownik_zamowienie();
				p.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnWrcDoPoprzedniego.setBounds(20, 16, 206, 23);
		frame.getContentPane().add(btnWrcDoPoprzedniego);
	}
}
