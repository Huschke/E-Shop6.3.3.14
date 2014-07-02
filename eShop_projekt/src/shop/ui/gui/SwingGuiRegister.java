package shop.ui.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import shop.ui.gui.SwingGuiRegister.SearchActionListener;
import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;
import shop.valueobjects.Artikel;

public class SwingGuiRegister extends JFrame {
	
		
		private final ShopManager sho;
		
	    private JTextField nicknameField;
	    private JTextField firstNameField;
	    private JTextField lastNameField;
	    private JTextField mailField;
	    private JTextField pwField;
	    private JTextField streetNumberField;
	    private JTextField plzField;
	    private JTextField countryField;
	    private JButton registerButton;
	    private JButton loginLabel;
	    //private JList<Buch> bookList;

	    private JTextArea infoText;

	    public String datei;
	    public static SwingGuiLogin sgl;
	    
	    public String shoFile = "SHO";
	    	
	    
	    
	    /**
	     * This is the default constructor
	     * 
	     * @throws IOException
	     */
	    public SwingGuiRegister(String titel, final ShopManager sho,  SwingGuiLogin sgl) throws IOException {
	        super(titel);
	        initialize();
	       
			this.sho =sho;
	        this.sgl = sgl;
	    }
	    
	    

	    /**
	     * This method initializes this
	     * 
	     * @return void
	     */
	    private void initialize() {
	    	setResizable(true);
			

	        // Klick auf Kreuz (Fenster schlie�en) behandeln lassen:
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
	        lastNameField = new JTextField("Nachname");
	        loginPanel.add(lastNameField);
	        firstNameField = new JTextField("Vorname");
	        loginPanel.add(firstNameField);
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
	        mailField = new JTextField("E-Mail");
	        loginPanel.add(mailField);
	        pwField = new JTextField("Passwort");
	        loginPanel.add(pwField);
	        pwField.setToolTipText("<html>Benutze ein Passwort, dass du<br> dir gut merken kannst!</html>");
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
	        nicknameField = new JTextField("Nickname");
	        loginPanel.add(nicknameField);
	        nicknameField.setToolTipText("<html>Den Nicknamen brauchst du,<br>um dich einzuloggen!</html>");
	        countryField = new JTextField("Land");
	        loginPanel.add(countryField);
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
	        streetNumberField = new JTextField("Stra�e, Nummer");
	        loginPanel.add(streetNumberField);
	        plzField = new JTextField("PLZ");
	        loginPanel.add(plzField);
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
	        registerButton = new JButton("Anmelden");
	        loginPanel.add(registerButton);
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        loginLabel = new JButton("<html><u>Zum Login</u></html>");
	        loginPanel.add(loginLabel);
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        loginPanel.add(new JLabel("						"));
	        
	        
	        
	        loginPanel.setBorder(BorderFactory.createTitledBorder("Registieren"));
	        
	        final Container cPane = this.getContentPane();
	        cPane.setLayout(new GridLayout(1, 1));
	        cPane.add(loginPanel);
	        

	        loginLabel.addActionListener(new SearchActionListener());
	        
	    
	        
	        
	        
	        
	            

	    

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
	       ShopManager sho = null;
		try {
			sho = new ShopManager("datei");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	        try {
	        	SwingGuiRegister sgr = new SwingGuiRegister("The Sheb Wop", sho,  sgl);
	            
	        } catch (final IOException e) {
	            e.printStackTrace();
	        }
	    }

	    //infotext muss == null sein und...
	    private void inform(final String message) {
	        infoText.setText(message);
	    }
	    
	    
	    
	 
	    

	    class SearchActionListener implements ActionListener {
	        public void actionPerformed(final ActionEvent ae) {
	        	
	        	if(ae.getSource().equals(loginLabel)){
                	try {
                		
						setVisible(false);
		                dispose();
                		
						new SwingGuiLogin("The Sheb Wop", shoFile);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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

	            if (command.equals("Quit")) {
	                //inform("Programm wird beendet!");
	                setVisible(false);
	                dispose();
	                System.exit(0);
	            } else if (command.equals("Save")) {
	                try {
	                    sho.schreibeArtikel();
	                    inform("B�cher gespeichert");
	                } catch (final IOException e) {
	                    e.printStackTrace();
	                }
	            }
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
