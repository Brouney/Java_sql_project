package projekt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pracownik_zamowienie {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pracownik_zamowienie window = new pracownik_zamowienie();
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
	public pracownik_zamowienie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 899, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * funkcja przekierowywująca na okno "wydanie towaru"
		 */
		JButton btnWydanieTowaru = new JButton("Wydanie Towaru");
		btnWydanieTowaru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Wydanie_towaru dod = new Wydanie_towaru ();

        		try {
        		dod.frame.setVisible(true);
        		frame.dispose();
        		} catch (Exception e) {
					e.printStackTrace();
        		}
				
				
			}
		});
		btnWydanieTowaru.setBounds(176, 244, 180, 69);
		frame.getContentPane().add(btnWydanieTowaru);
		
		
		/**
		 * funkcja przekierowywująca na okno "przyjęcie towaru"
		 */
		JButton btnNewButton = new JButton("Przyjęcie Towaru");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Przyjecie_towaru dod = new Przyjecie_towaru ();

        		try {
        		dod.frame.setVisible(true);
        		frame.dispose();
        		} catch (Exception ex) {
					ex.printStackTrace();
        		}
			}
		});
		btnNewButton.setBounds(496, 244, 180, 69);
		frame.getContentPane().add(btnNewButton);
		
		/**
		 * funkcja przekierowywująca na okno "magazyn"
		 */
		JButton btnMagazyn = new JButton("Magazyn");
		btnMagazyn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Obsluga_magazyn p = new Obsluga_magazyn();
				p.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnMagazyn.setBounds(342, 380, 180, 69);
		frame.getContentPane().add(btnMagazyn);
		
		
		/**
		 * funkcja przekierowywująca na okno "logowanie"
		 */
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_sw lo = new login_sw();
				lo.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnWyloguj.setBounds(737, 484, 89, 23);
		frame.getContentPane().add(btnWyloguj);
	}
}
