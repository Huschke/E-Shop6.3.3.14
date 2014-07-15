package shop.ui.gui;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

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

import shop.ui.gui.ArtikelTableModel;
import shop.valueobjects.Artikel;
import shop.ui.gui.SwingGuiLogin.LoginActionListener;
import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;



public class SwingGuiBuy extends JFrame {

    private final ShopManager sho;

    private JButton addButton;
    private JTextField numberField;
    private JTextField titleField;
    private JTextField searchField;
    private JButton losButton;
    private JTable artikelTable;
    private JButton alleArtikelButton;

    //private JList<Buch> bookList;

    private JTextArea infoText;

    /**
     * This is the default constructor
     * 
     * @throws IOException
     */
    public SwingGuiBuy(String titel, final String datei) throws IOException {
        super(titel);
        initialize();
        sho = new ShopManager();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(600, 400);

        // Klick auf Kreuz (Fenster schlie�en) behandeln lassen:
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Linke Seite mitte
        final JPanel searchPanel = new JPanel();
        // Eigentlich br�uchten wir nur 3 Zeilen f�r dieses Panel.
        // Es wird die gleiche Anzahl wie oben verwendet, damit die Textfelder /
        // Buttons etc. gleich skaliert werden:
        searchPanel.setLayout(new GridLayout(5, 2));
        // Leerzeile im Layout...
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        // ... und hier das eigentliche Suchformular:
        searchPanel.add(new JLabel("Artikelsuche"));
        searchPanel.add(new JLabel(""));
        searchField = new JTextField();
        searchField.setToolTipText("Artikelname hier eingeben!");
        searchPanel.add(searchField);
        losButton = new JButton("Los");
        searchPanel.add(losButton);
        searchPanel.add(new JLabel(""));
        alleArtikelButton = new JButton("Alle Artikel");
        searchPanel.add(alleArtikelButton);
        

        searchPanel.setBorder(BorderFactory.createTitledBorder("Home"));

        // Linke Seite unten
        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 1));
        infoText = new JTextArea();
        infoText.setLineWrap(true);
        infoText.setEnabled(false);
        infoPanel.add(infoText);

        infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));

        // Linke Seite...
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        // ... besteht aus dem upper und dem lower Panel
        
        leftPanel.add(searchPanel);
        leftPanel.add(infoPanel);

        // Rechte Seite (Swing-Liste)

        // ListModel als "Datencontainer" anlegen:
        final Vector<String> spalten = new Vector<String>();
        spalten.add("Nr.");
        spalten.add("Titel");
        spalten.add("Verf�gbarkeit");
        // TableModel als "Datencontainer" anlegen:
        final ArtikelTableModel tModel = new ArtikelTableModel(new Vector<Artikel>(), spalten);
        // JTable-Objekt erzeugen und mit Datenmodell initialisieren:
        artikelTable = new JTable(tModel);
        // JTable in ScrollPane platzieren:
        final JScrollPane east = new JScrollPane(artikelTable);

        // Alles als zweispaltiges Layout zusammenbauen ...
        final Container cPane = this.getContentPane();
        cPane.setLayout(new GridLayout(1, 2));
        cPane.add(leftPanel);
        cPane.add(east);

   
        alleArtikelButton.addActionListener(new SearchActionListener());
        
        losButton.addActionListener(new SearchActionListener());

        // Men� definieren ...
        // File-Men� erzeugen
        final JMenu fileMenu = new FileMenu();

        // Help-Men� erzeugen
        final JMenu helpMenu = new HelpMenu();

        // Men�leiste erzeugen und File- und Help-Men� hinzuf�gen
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
            new SwingGuiBuy("The Sheb Wop - Buy Area", shoFile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void inform(final String message) {
        infoText.setText(message);
    }

    class SearchActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent ae)  {/*
            if (ae.getSource().equals(searchButton)) {
                java.util.List<Buch> buecher = null;
                final String suchbegriff = searchField.getText();
                if (suchbegriff.isEmpty()) {
                    buecher = bib.gibAlleBuecher();
                    inform("Liste alle B�cher!");
                } else {
                    buecher = bib.sucheNachTitel(suchbegriff);
                    inform("Liste B�cher mit gegebenem Titel!");
                }
                // Alternative 1: Ausgabe des Suchergebnisses �ber JList
                final DefaultListModel<Buch> lModel = (DefaultListModel<Buch>) bookList
                        .getModel();
                lModel.removeAllElements();
                for (final Buch aktBuch : buecher) {
                    lModel.addElement(aktBuch);
                }
            }
        */
        	if (ae.getSource().equals(alleArtikelButton)){
        		java.util.List<Artikel> art = null;
        		art = sho.gibAlleArtikel();
        		
        		final ArtikelTableModel tModel = (ArtikelTableModel) artikelTable.getModel();
                tModel.setDataVector(art);
        	}
        }
    }

    // Lokale Klasse f�r File-Men�
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

           /*if (command.equals("Quit")) {
                inform("Programm wird beendet!");
                setVisible(false);
                dispose();
                System.exit(0);
            } else if (command.equals("Save")) {
                try {
                    sho.schreibeBuecher();
                    inform("B�cher gespeichert");
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

    // Lokale Klasse f�r Help-Men�
    class HelpMenu extends JMenu {

        public HelpMenu() {
            super("Help");

            // Nur zu Testzwecken: Men� mit Untermen�
            final JMenu m = new JMenu("About");
            m.add(new JMenuItem("Programmers"));
            m.add(new JMenuItem("Stuff"));
            this.add(m);
        }
    }

}
