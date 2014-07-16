package shop.ui.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;
import shop.ui.gui.SwingGuiBuy.FileMenu;
import shop.ui.gui.SwingGuiBuy.HelpMenu;
import shop.ui.gui.SwingGuiBuy.SearchActionListener;
import shop.valueobjects.Artikel;

public class SwingGuiWarenkorb extends JFrame{

	

	private final ShopManager sho;

    private JTextField searchField;
    private JTextField kaufIdField;
    private JTextField kaufAnzField;
    private JButton losButton;
    private JTable artikelTable;

    private JButton warenkorbLeerenButton;
    private JButton kaufButton;
    
  

    private JTextArea infoText;

    /**
     * This is the default constructor
     * 
     * @throws IOException
     */
    public SwingGuiWarenkorb(String titel) throws IOException {
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

        // Klick auf Kreuz (Fenster schließen) behandeln lassen:
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Linke Seite mitte
        final JPanel searchPanel = new JPanel();
        // Eigentlich bräuchten wir nur 3 Zeilen für dieses Panel.
        // Es wird die gleiche Anzahl wie oben verwendet, damit die Textfelder /
        // Buttons etc. gleich skaliert werden:
        searchPanel.setLayout(new GridLayout(7, 2));
        // Leerzeile im Layout...
        
        searchPanel.add(new JLabel(""));
        kaufButton = new JButton("Jetzt Bestellen");
        searchPanel.add(kaufButton);
        searchPanel.add(new JLabel(""));
        warenkorbLeerenButton = new JButton("Warenkorb leeren");
        searchPanel.add(warenkorbLeerenButton);
        
        

        searchPanel.setBorder(BorderFactory.createTitledBorder("Home"));

        // Linke Seite unten
        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 2));
        infoPanel.add(new JLabel(""));
        infoPanel.add(new JLabel(""));
        kaufIdField = new JTextField("ID");
        infoPanel.add(kaufIdField);
        
        kaufAnzField = new JTextField("Stückzahl");
        infoPanel.add(kaufAnzField);
        
        infoPanel.add(new JLabel(""));
        infoPanel.add(new JLabel(""));
        
        infoPanel.add(new JLabel(""));
        
        kaufButton = new JButton("in den Warenkorb");
        infoPanel.add(kaufButton);
        
        infoPanel.add(new JLabel(""));
        infoPanel.add(new JLabel(""));
        

        infoPanel.setBorder(BorderFactory.createTitledBorder("Kaufen"));
        
        // Linke Seite...
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        // ... besteht aus dem upper und dem lower Panel
        
        leftPanel.add(searchPanel);
        leftPanel.add(infoPanel);

        // Rechte Seite (Swing-Liste)

        // ListModel als "Datencontainer" anlegen:
        final Vector<String> spalten = new Vector<String>();
        spalten.add("ID");
        spalten.add("Artikel");
        spalten.add("Preis");
        spalten.add("Anzahl");
        spalten.add("Verfügbarkeit");
        
        // TableModel als "Datencontainer" anlegen:
        final ArtikelTableModel tModel = new ArtikelTableModel(new Vector<Artikel>(), spalten);
        // JTable-Objekt erzeugen und mit Datenmodell initialisieren:
        artikelTable = new JTable(tModel);
        // JTable in ScrollPane platzieren:
        final JScrollPane east = new JScrollPane(artikelTable);

        //zweispaltiges Layout
        final Container cPane = this.getContentPane();
        cPane.setLayout(new GridLayout(1, 2));
        cPane.add(leftPanel);
        cPane.add(east);

        leftPanel.getRootPane().setDefaultButton(losButton);
        
       
        warenkorbLeerenButton.addActionListener(new SearchActionListener());
        kaufButton.addActionListener(new SearchActionListener());
        
        // Menüs
        final JMenu fileMenu = new FileMenu();

        final JMenu helpMenu = new HelpMenu();
        final JMenuBar mBar = new JMenuBar();
        mBar.add(fileMenu);
        mBar.add(helpMenu);

        this.setJMenuBar(mBar);

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
        public void actionPerformed(final ActionEvent ae)  {
            if (ae.getSource().equals(warenkorbLeerenButton)){
            	JOptionPane.showMessageDialog(null,
                        "Ihr Warenkorb wurde geleert! ",
                        "Bestellung",					      
                        JOptionPane.WARNING_MESSAGE);
               //sho.leereWarenkorb();
           
            } 

        	if(ae.getSource().equals(kaufButton)){
    			
                JOptionPane.showMessageDialog(null,
                        "Ihre Bestellung wurde abgeschickt! ",
                        "Bestellung",					      
                        JOptionPane.WARNING_MESSAGE);
               //sho.zeigeRechnung();
           
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
                setVisible(false);
                dispose();
                System.exit(0);
 
            }
        }
    }

    // Lokale Klasse für Help-Menü
    class HelpMenu extends JMenu {

        public HelpMenu() {
            super("Help");

            // Nur zu Testzwecken: Menü mit Untermenü
            final JMenu m = new JMenu("About");
            m.add(new JMenuItem("Programmers: Adam Czogallik, Johannes Masur"));
            m.add(new JMenuItem("Stuff"));
            this.add(m);
        }
    }

}

