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

import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;
import shop.valueobjects.Artikel;


public class SwingGuiBuy extends JFrame {

    private final ShopManager sho;

    private JButton addButton;
    private JTextField numberField;
    private JTextField titleField;
    private JTextField searchField;
    private JButton losButton;
    private JTable artikelTable;

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
        sho = new ShopManager(datei);
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(700, 500);

        // Klick auf Kreuz (Fenster schließen) behandeln lassen:
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // obere such panel
        final JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(3, 5));
        setPreferredSize(new Dimension(700, 500));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel("Titel "));
        titleField = new JTextField();
        searchPanel.add(titleField);
        losButton = new JButton("Los");
        searchPanel.add(losButton);
        
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));

        searchPanel.setBorder(BorderFactory.createTitledBorder("Artikelsuche"));

  

      

        // Rechte Seite (Swing-Liste)

        // ListModel als "Datencontainer" anlegen:
        //final DefaultListModel<Buch> lModel = new DefaultListModel<Buch>();
        //bookList = new JList<Buch>(lModel);

   
        	
        // JList in ScrollPane platzieren:
        final JScrollPane down = new JScrollPane(artikelTable);
        

        // oberes such panel und unteres anzeeige panel
        final Container cPane = this.getContentPane();
        cPane.setLayout(new GridLayout(2, 1));
        cPane.add(searchPanel);
        cPane.add(down);

        
        
        
        
        
        // Listener registrieren
        // 1.) Für den Einfügen-Button
        // (Listener als anonyme Klasse)
        losButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent ae) {/*
                final String titel = titleField.getText();
                try {
                    final int nummer = Integer.parseInt(numberField.getText());
                    inform("Füge Buch mit Nummer " + nummer + " und Titel " + titel
                            + " ein.");
                    try {
                        sho.fuegeBuchEin(titel, nummer);
                    } catch (final BuchExistiertBereitsException e) {
                        inform("Buch existierte bereits!");
                    }
                } catch (final NumberFormatException e) {
                    inform("Buchnummer muss eine Zahl sein!");
                }
            */}
        });

        // 2.) Für den Suchen-Button
        // (Listener als lokale Klasse; Klasse steht weiter unten)
        losButton.addActionListener(new SearchActionListener());

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
                }
            }
        */}
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

           /*if (command.equals("Quit")) {
                inform("Programm wird beendet!");
                setVisible(false);
                dispose();
                System.exit(0);
            } else if (command.equals("Save")) {
                try {
                    sho.schreibeBuecher();
                    inform("Bücher gespeichert");
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }*/
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
