package shop.ui.gui;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
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
import shop.valueobjects.Mitarbeiter;
import shop.ui.gui.SwingGuiLogin.LoginActionListener;
import shop.ui.gui.SwingGuiRegister.RegisterActionListener;
import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;



public class SwingGuiMitarbeiter extends JFrame {

    private final ShopManager sho;

    private JTextField searchField;
    private JButton losButton;
    private JTable artikelTable;
    private JButton alleArtikelButton;
    private JButton logOutButton;
    private JButton warenkorbButton;
    private JButton addButton;
    private JButton aendereBestandButton;
    private JTextField artikelIdField;
    private JTextField artikelNameField;
    private JTextField preisField;
    private JTextField bestandField;
    private JTextField verfuegbarField;
    private JTextField bestandAendernField;
    private JTextField idAendernField;
    
  

    /**
     * This is the default constructor
     * 
     * @throws IOException
     */
    public SwingGuiMitarbeiter(String titel, final String datei) throws IOException {
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
        logOutButton = new JButton("Abmelden");
        searchPanel.add(logOutButton);
        searchPanel.add(new JLabel(""));
        warenkorbButton = new JButton("Warenkorb");
        searchPanel.add(warenkorbButton);

        // ... und hier das eigentliche Suchformular:
        searchPanel.add(new JLabel("Artikelsuche"));
        searchPanel.add(new JLabel(""));
        searchField = new JTextField();
        searchField.setToolTipText("Artikelname hier eingeben!");
        searchPanel.add(searchField);
        losButton = new JButton("Los");
        searchPanel.add(losButton);
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        alleArtikelButton = new JButton("Alle Artikel");
        searchPanel.add(alleArtikelButton);
        
        

        searchPanel.setBorder(BorderFactory.createTitledBorder("Home"));
      
 
        final JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(6, 2));
        
        artikelIdField = new JTextField("Artikel ID");
        addPanel.add(artikelIdField);
        artikelNameField = new JTextField("Artikel Name");
        addPanel.add(artikelNameField);
        
        preisField = new JTextField("Preis");
        addPanel.add(preisField);
        bestandField = new JTextField("Anzahl");
        addPanel.add(bestandField);
        
        addPanel.add(new JLabel(""));
        addButton = new JButton("Hinzufügen");
        addPanel.add(addButton);
        
        addPanel.add(new JLabel(""));
        addPanel.add(new JLabel(""));
        idAendernField = new JTextField("ID");
        addPanel.add(idAendernField);
        bestandAendernField = new JTextField("Neuer Bestand");
        addPanel.add(bestandAendernField); 
        addPanel.add(new JLabel(""));
        aendereBestandButton = new JButton("Bestand ändern");
        addPanel.add(aendereBestandButton);
        
        
 

        addPanel.setBorder(BorderFactory.createTitledBorder("Wareneinspeisung"));

        // Linke Seite...
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        // ... besteht aus dem upper und dem lower Panel
        
        leftPanel.add(searchPanel);
        leftPanel.add(addPanel);

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
        
        
        alleArtikelButton.addActionListener(new SearchActionListener());
        logOutButton.addActionListener(new SearchActionListener());
        losButton.addActionListener(new SearchActionListener());
        warenkorbButton.addActionListener(new SearchActionListener());
        addButton.addActionListener(new SearchActionListener());
        aendereBestandButton.addActionListener(new SearchActionListener());
        
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
            new SwingGuiBuy("The Sheb Wop - Mitarbeiter-Lounge", shoFile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

   

    class SearchActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent ae)  {
            if (ae.getSource().equals(losButton)) {
            	java.util.List<Artikel> art = null;
        		art = sho.gibAlleArtikel();
                final String suchbegriff = searchField.getText();
                if (suchbegriff.isEmpty()){
                	JOptionPane.showMessageDialog(null,
                            "Geben Sie den Namen des Artikels ein!",
                            "Artikelsuche",					      
                            JOptionPane.WARNING_MESSAGE);
                }else{
                art = sho.sucheArtikel(suchbegriff);
                final ArtikelTableModel tModel = (ArtikelTableModel) artikelTable.getModel();
                tModel.setDataVector(art);
                }
            }
        
        	if (ae.getSource().equals(alleArtikelButton)){
        		java.util.List<Artikel> art = null;
        		try {
					sho.artikelIDSortieren();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		art = sho.gibAlleArtikel();
        		
        		final ArtikelTableModel tModel = (ArtikelTableModel) artikelTable.getModel();
                tModel.setDataVector(art);
        	}
        	if(ae.getSource().equals(logOutButton)){
        		try {
   
					setVisible(false);
					dispose();
					SwingGuiLogin sgl = new SwingGuiLogin("The Sheb Wop", "shoFile");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	if(ae.getSource().equals(warenkorbButton)){
        		//setVisible(false);
				//dispose();
        		System.out.println("warenkorb");
				SwingGuiWarenkorb sgw = new SwingGuiWarenkorb("The Sheb Wop - Warenkorb");
        	}
        	if(ae.getSource().equals(addButton)){
        		try {
                    final String Id = artikelIdField.getText();
                    final String name = artikelNameField.getText();
                    final String preis = preisField.getText();
                    final Float flPreis = Float.valueOf(preis);
                    final int anzahl = Integer.parseInt(bestandField.getText());
                    boolean verfuegbar = false;
                    if(anzahl > 0){
                    	verfuegbar = true;
                    } else{
                    	verfuegbar = false;
                    }
                    
                    JOptionPane.showMessageDialog(null,
                            "Artikel mit name " + name + " und ID " + Id
                            + " wurde hinzugefügt.",
                            "Artikel hinzugefügt",					      
                            JOptionPane.WARNING_MESSAGE);
                   
                    try {
                        sho.fuegeEinenArtikelEin( Id, name, flPreis, anzahl, verfuegbar);
                        sho.schreibeArtikel();
                    } catch (final ArtikelExistiertBereitsException e) {
                      
                    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } catch (final NumberFormatException e) {

                }
        	}
        		if(ae.getSource().equals(aendereBestandButton)){
        			final String alteId = idAendernField.getText();
                    final int neuerBestand = Integer.parseInt(bestandAendernField.getText());
                    JOptionPane.showMessageDialog(null,
                            "Anzahl von Artikel " + alteId +  " wurde geändert.",
                            "Artikelbestand geändert",					      
                            JOptionPane.WARNING_MESSAGE);
                    sho.artikelBestandVeraendern(alteId, neuerBestand);
               
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
