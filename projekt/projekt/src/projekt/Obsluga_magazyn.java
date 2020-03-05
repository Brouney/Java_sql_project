package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Obsluga_magazyn {

	JFrame frame;
	private JTable table;
	private JTextField text_nazwa;
	private JTextField text_opis;
	private JTextField text_cena;
	private JTextField text_miejsce;
	private JScrollPane scrollPane;
	private JComboBox<String> combo_kat;
	private String ID = "";
	private JComboBox<String> combosort;

	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projekt_baza";
	String user = "postgres";
	String password = "admin";

	String idmagazyn = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Obsluga_magazyn window = new Obsluga_magazyn();
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
	public Obsluga_magazyn() {
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

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int wiersz = table.getSelectedRow();
				ID = (table.getModel().getValueAt(wiersz, 0)).toString();
				idmagazyn = (table.getModel().getValueAt(wiersz, 1)).toString();
				String nazwa = (table.getModel().getValueAt(wiersz, 2)).toString();
				String opis = (table.getModel().getValueAt(wiersz, 3)).toString();
				String miejsce = (table.getModel().getValueAt(wiersz, 6)).toString();
				String cena = (table.getModel().getValueAt(wiersz, 4)).toString();
				text_nazwa.setText(nazwa);
				text_opis.setText(opis);
				text_cena.setText(cena);
				text_miejsce.setText(miejsce);
			}
		});
		scrollPane.setBounds(388, 116, 582, 567);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		text_nazwa = new JTextField();
		text_nazwa.setBounds(123, 113, 173, 20);
		frame.getContentPane().add(text_nazwa);
		text_nazwa.setColumns(10);

		JButton btnZaaduj = new JButton("Załaduj");
		btnZaaduj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				combo_kat.removeAllItems();

				combosort.removeAllItems();

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement("SELECT * FROM kategorie ");
						ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						combo_kat.addItem(rs.getString("opis"));
					}

					pst.close();
					rs.close();
				} catch (Exception ex) {
				}

				combosort.addItem("nazwa");
				combosort.addItem("miejsce");
				combosort.addItem("nie przydzielone");

			}
		});
		btnZaaduj.setBounds(351, 65, 89, 23);
		frame.getContentPane().add(btnZaaduj);

		text_opis = new JTextField();
		text_opis.setBounds(123, 134, 173, 20);
		frame.getContentPane().add(text_opis);
		text_opis.setColumns(10);

		text_cena = new JTextField();
		text_cena.setBounds(123, 154, 173, 20);
		frame.getContentPane().add(text_cena);
		text_cena.setColumns(10);

		text_miejsce = new JTextField();
		text_miejsce.setBounds(123, 174, 173, 20);
		frame.getContentPane().add(text_miejsce);
		text_miejsce.setColumns(10);

		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setForeground(Color.WHITE);
		lblNazwa.setBounds(46, 116, 46, 14);
		frame.getContentPane().add(lblNazwa);

		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setForeground(Color.WHITE);
		lblOpis.setBounds(46, 137, 46, 14);
		frame.getContentPane().add(lblOpis);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setForeground(Color.WHITE);
		lblCena.setBounds(46, 157, 46, 14);
		frame.getContentPane().add(lblCena);

		JLabel lblMiejsce = new JLabel("Miejsce");
		lblMiejsce.setForeground(Color.WHITE);
		lblMiejsce.setBounds(46, 177, 46, 14);
		frame.getContentPane().add(lblMiejsce);

		combo_kat = new JComboBox();
		combo_kat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection con = DriverManager.getConnection(url, user, password);
						PreparedStatement pst = con.prepareStatement(
								"SELECT p.id_prod,m.id_magazyn,p.nazwa,p.opis,p.cena,m.ilosc,m.miejsce FROM produkt p JOIN magazyn m ON p.id_prod = m.id_prod_m JOIN kategorie k ON k.id_kat = p.id_kat_pr WHERE k.opis = ?")) {

					pst.setString(1, (String) combo_kat.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
		});
		combo_kat.setBounds(481, 66, 128, 20);
		frame.getContentPane().add(combo_kat);

		JButton btnEdytuj = new JButton("Edytuj miejsce");
		btnEdytuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement pstn = conn.prepareStatement("UPDATE  magazyn SET  miejsce = '"
							+ text_miejsce.getText() + "' WHERE id_prod_m = " + ID + " and id_magazyn = " + idmagazyn);
					// ResultSet rsssn = pst.executeQuery();
					pstn.executeUpdate();
					conn.close();
					pstn.close();

				} catch (Exception exxcc) {
					exxcc.getMessage();
				}
				JOptionPane.showMessageDialog(frame, "Zmieniono opis ");
			}
		});
		btnEdytuj.setBounds(141, 225, 155, 23);
		frame.getContentPane().add(btnEdytuj);

		combosort = new JComboBox();
		combosort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = (String) combosort.getSelectedItem();

				if (str.equals("nazwa")) {

					try (Connection con = DriverManager.getConnection(url, user, password);
							PreparedStatement pst = con.prepareStatement("SELECT *from sortnazwa")) {

						ResultSet rs = pst.executeQuery();

						table.setModel(DbUtils.resultSetToTableModel(rs));

						pst.close();
						rs.close();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}

				} else if (str.equals("miejsce")) {

					try (Connection con = DriverManager.getConnection(url, user, password);
							PreparedStatement pst = con.prepareStatement("SELECT *from sortmiejsce")) {

						ResultSet rs = pst.executeQuery();

						table.setModel(DbUtils.resultSetToTableModel(rs));

						pst.close();
						rs.close();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}

				} else if (str.equals("nie przydzielone")) {

					try (Connection con = DriverManager.getConnection(url, user, password);
							PreparedStatement pst = con.prepareStatement("SELECT *from sortniep")) {

						ResultSet rs = pst.executeQuery();

						table.setModel(DbUtils.resultSetToTableModel(rs));

						pst.close();
						rs.close();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}

				}

			}
		});
		combosort.setBounds(695, 66, 128, 20);
		frame.getContentPane().add(combosort);

		JLabel lblKategorie = new JLabel("Kategorie");
		lblKategorie.setForeground(Color.WHITE);
		lblKategorie.setBounds(481, 41, 46, 14);
		frame.getContentPane().add(lblKategorie);

		JLabel lblSortuj = new JLabel("Sortuj z całego magazynu");
		lblSortuj.setForeground(Color.WHITE);
		lblSortuj.setBounds(695, 41, 173, 14);
		frame.getContentPane().add(lblSortuj);

		JButton btnOdwie = new JButton("Odśwież");
		btnOdwie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection con = DriverManager.getConnection(url, user, password);

						PreparedStatement pst = con.prepareStatement(
								"SELECT p.id_prod,m.id_magazyn,p.nazwa,p.opis,p.cena,m.ilosc,m.miejsce FROM produkt p JOIN magazyn m ON p.id_prod = m.id_prod_m JOIN kategorie k ON k.id_kat = p.id_kat_pr WHERE k.opis = ?")) {

					pst.setString(1, (String) combo_kat.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}

		});
		btnOdwie.setBounds(876, 706, 89, 23);
		frame.getContentPane().add(btnOdwie);

		JButton btnUsuWszystkie = new JButton("usuń wszystkie 0");
		btnUsuWszystkie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection con = DriverManager.getConnection(url, user, password);

						PreparedStatement pst = con.prepareStatement("DELETE FROM magazyn WHERE ilosc =0")) {

					pst.executeUpdate();

					pst.close();

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				JOptionPane.showMessageDialog(frame, "usunięto ");

			}
		});
		btnUsuWszystkie.setBounds(141, 301, 155, 23);
		frame.getContentPane().add(btnUsuWszystkie);

		JButton btnWr = new JButton("Wróć");
		btnWr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pracownik_zamowienie dod = new pracownik_zamowienie();
				dod.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnWr.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnWr);
	}
}
