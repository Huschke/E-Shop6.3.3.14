package shop.ui.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;

import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;
import shop.valueobjects.Artikel;

public class SwingGuiLogin extends JFrame{
	
	private final ShopManager sho;

   
    private JTextField nicknameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JLabel registrierLabel;
    //private JList<Buch> bookList;

    private JTextArea infoText;

    /**
     * This is the default constructor
     * 
     * @throws IOException
     */
    public SwingGuiLogin(String titel, final String datei) throws IOException {
        super(titel);
        initialize();
        sho = new ShopManager(datei);
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
    	setResizable(true);
		

        // Klick auf Kreuz (Fenster schließen) behandeln lassen:
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Linke Seite oben
        final JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(15,6));
        setPreferredSize(new Dimension(700, 500));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("Nickname:"));
        nicknameField = new JTextField();
        loginPanel.add(nicknameField);
        loginPanel.add(new JLabel("						")); // leeres Element für Feld im Grid
        loginPanel.add(new JLabel("						"));
     
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginButton = new JButton("LogIn");
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        registrierLabel = new JLabel("<html><u>Registrieren</u></html>");
        loginPanel.add(registrierLabel);
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        
        final Container cPane = this.getContentPane();
        cPane.setLayout(new GridLayout(1, 1));
        cPane.add(loginPanel);
        

        
     
    

        // Listener registrieren
        // 1.) Für den login-Button
        // (Listener als anonyme Klasse)
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent ae) {
                final String titel = nicknameField.getText();
                try {
                    final String nummer = passwordField.getText();
                    inform("Füge Buch mit Nummer " + nummer + " und Titel " + titel
                            + " ein.");
                    try {
                        //sho.schreibeArtikel();
                    	System.out.println("Nickname" + nicknameField);
                    } catch (final ArtikelExistiertBereitsException e) {
                        inform("Buch existierte bereits!");
                    }
                } catch (final NumberFormatException e) {
                    inform("Buchnummer muss eine Zahl sein!");
                }
            }
        });

    

        // Menü definieren ...
        // File-Menü erzeugen
        final JMenu fileMenu = new FileMenu();

        // Help-Menü erzeugen
        final JMenu helpMenu = new HelpMenu();

        // Menüleiste erzeugen und File- und Help-Menü hinzufügen
        final JMenuBar mBar = new JMenuBar();
        mBar.add(fileMenu);
        mBar.add(helpMenu);
        // ... und beim Fenster anmelden
        this.setJMenuBar(mBar);

        // Fenster anzeigen
        this.setVisible(true);
        this.pack();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final String shoFile = "SHO";
        try {
            new SwingGuiLogin("The Sheb Wop", shoFile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void inform(final String message) {
        infoText.setText(message);
    }

    class SearchActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent ae) {
            if (ae.getSource().equals(loginButton)) {
                //java.util.List<Buch> buecher = null;
                final String suchbegriff = nicknameField.getText();
                /*if (suchbegriff.isEmpty()) {
                    buecher = bib.gibAlleBuecher();
                    inform("Liste alle Bücher!");
                } else {
                    buecher = bib.sucheNachTitel(suchbegriff);
                    inform("Liste Bücher mit gegebenem Titel!");
                }
                // Alternative 1: Ausgabe des Suchergebnisses über JList
                final DefaultListModel<Buch> lModel = (DefaultListModel<Buch>) bookList
                        .getModel();
                lModel.removeAllElements();
                for (final Buch aktBuch : buecher) {
                    lModel.addElement(aktBuch);
                }*/
            }
        }
    }

    // Lokale Klasse für File-Menü
    class FileMenu extends JMenu implements ActionListener {

        public FileMenu() {
            super("File");
            JMenuItem mi = new JMenuItem("Save");
            mi.addActionListener(this);
            this.add(mi);

            this.addSeparator();

            mi = new JMenuItem("Quit");
            mi.addActionListener(this);
            this.add(mi);
        }

        @Override
        public void actionPerformed(final ActionEvent ae) {
            final String command = ae.getActionCommand();
            System.out.println(command);

            if (command.equals("Quit")) {
            	
                //inform("Programm wird beendet!");
                setVisible(false);
                dispose();
                System.exit(0);
            } else if (command.equals("Save")) {
                try {
                    sho.schreibeArtikel();
                    inform("Bücher gespeichert");
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Lokale Klasse für Help-Menü
    class HelpMenu extends JMenu {

        public HelpMenu() {
            super("Help");

            // Nur zu Testzwecken: Menü mit Untermenü
            final JMenu m = new JMenu("About");
            m.add(new JMenuItem("Programmers"));
            m.add(new JMenuItem("Stuff"));
            this.add(m);
        }
    }

	
}
