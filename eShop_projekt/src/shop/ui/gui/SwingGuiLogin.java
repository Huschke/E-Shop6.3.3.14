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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;







import shop.domain.ShopManager;
import shop.exceptions.ArtikelExistiertBereitsException;
import shop.exceptions.PasswortFalschException;
import shop.ui.gui.SwingGuiRegister.RegisterActionListener;
import shop.valueobjects.Artikel;
import shop.valueobjects.Person;

public class SwingGuiLogin extends JFrame{
	
	private final ShopManager sho;

   
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registrierButton;
    //private JList<Buch> bookList;

    public static SwingGuiLogin sgl;
    


    /**
     * This is the default constructor
     * 
     * @throws IOException
     */
    public SwingGuiLogin(String titel, final String datei) throws IOException {
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
        nicknameField.setToolTipText("<html>Hier muss dein Nickname rein!</html>");
        loginPanel.add(new JLabel("						")); // leeres Element für Feld im Grid
        loginPanel.add(new JLabel("						"));
     
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(5);
        loginPanel.add(passwordField);
        passwordField.setToolTipText("<html>Dein geheimes Passwort!</html>");
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
        loginButton.setToolTipText("<html>Logge die einfach ein!</html>");
        loginPanel.add(new JLabel("						"));
        
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        loginPanel.add(new JLabel("						"));
        registrierButton = new JButton("<html><u>Registrieren</u></html>");
        loginPanel.add(registrierButton);
        registrierButton.setToolTipText("<html>Du hast noch, keinen Account,<br>regisrtiere dich zu erst!</html>");
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
        
        //Enter für logIN
        loginPanel.getRootPane().setDefaultButton(loginButton);
        
        registrierButton.addActionListener(new LoginActionListener());
     
     
        loginButton.addActionListener(new LoginActionListener());

    

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



    class LoginActionListener implements ActionListener {
        @SuppressWarnings("deprecation")
		public void actionPerformed(final ActionEvent ae) {
            if (ae.getSource().equals(registrierButton)) {
            
            	setVisible(false);
				dispose();
				
				try {
					SwingGuiRegister sgr = new SwingGuiRegister("The Sheb Wop", sho,  sgl);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      
            }
            if (ae.getSource().equals(loginButton)) {
            	Person p = null;
            	
            	
            
	            final String name = nicknameField.getText();
	            
	            final String passwort= passwordField.getText();
				System.out.println("Eingelogt mit Passwort: " + passwort + " und Name: " + name);
				p = sho.ueberpruefeLogin(name, passwort);
				if(p!= null){
					
					setVisible(false);
					dispose();
					
					if(sho.mitarb == true){
						try {
							System.out.println("mitarbeiter login");
							SwingGuiMitarbeiter sgb = new SwingGuiMitarbeiter("The Sheb Wop", passwort);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						System.out.println("kunden login");
					try {
						SwingGuiBuy sgb = new SwingGuiBuy("The Sheb Wop", passwort);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}}
				
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
                    //inform("Bücher gespeichert");
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
