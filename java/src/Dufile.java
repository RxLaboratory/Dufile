import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Dufile {

	/**
	 * Dufile (DuDuF File Sequence Manager)
	 * (c) Copyright 2009 Nicolas Dufresne
	 * http://www.duduf.com
	 * duduf@duduf.com
	 *
	 * version 0.0.2b 16/04/2009
	 * 
	 *  This file is part of Dufile.

    Dufile is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Dufile is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Dufile.  If not, see <http://www.gnu.org/licenses/>.

	 */
	public static void main(String[] args) {
		

	String version = "0.0.2 Beta";
		
		//===================================
		//GUI
		//===================================
		
	
	
		//Prendre le L&F du système, c'est mieux...
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       e.getMessage();
	    }
	    catch (ClassNotFoundException e) {
	    	e.getMessage();
	    }
	    catch (InstantiationException e) {
	    	e.getMessage();
	    }
	    catch (IllegalAccessException e) {
	    	e.getMessage();
	    }
		
		//FENETRE PRINCIPALE
	    //==================
		final JFrame Fenetre = new JFrame();
		//Définit un titre pour la fenêtre
		Fenetre.setTitle("Dufile " + version);
        //Définit une taille
		Fenetre.setSize(300, 280);
        //Mettre la fenêtre au milieu de l'écran
		Fenetre.setLocationRelativeTo(null);
        //Quitte lorsqu'on clique sur "Fermer" !
		Fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //un panneau
        JPanel panneau = new JPanel();   
        //un layout pour le panneau
        GridLayout panneauLayout =new GridLayout(8,1);
        panneau.setLayout(panneauLayout);
        
        //barre de menu
		//==========================
		JMenuBar menu = new JMenuBar();
		//sous menus
		JMenu menuFichier = new JMenu("Fichier");
		menu.add(menuFichier);
		JMenu menuAide = new JMenu("?");
		menu.add(menuAide);
		menuFichier.setMnemonic(KeyEvent.VK_F);
		//boutons du menu fichier
		final JMenuItem menuChoisir = new JMenuItem("Choisir les fichiers...");
		menuFichier.add(menuChoisir);
		menuChoisir.setMnemonic(KeyEvent.VK_C);
		menuFichier.addSeparator();
		final JMenuItem menuDossier = new JMenuItem("Dossier de destination...");
		menuDossier.setEnabled(false);
		menuDossier.setMnemonic(KeyEvent.VK_D);
		menuFichier.add(menuDossier);
		final JMenuItem menuValider = new JMenuItem("Renommer !");
		menuValider.setEnabled(false);
		menuFichier.add(menuValider);
		menuValider.setMnemonic(KeyEvent.VK_R);
		menuFichier.addSeparator();
		final JMenuItem menuOptions = new JMenuItem("Options...");
		menuOptions.setEnabled(false);
		menuFichier.add(menuOptions);
		menuOptions.setMnemonic(KeyEvent.VK_O);
		menuFichier.addSeparator();
		JMenuItem menuQuitter = new JMenuItem("Quitter");
		menuFichier.add(menuQuitter);
		menuQuitter.setMnemonic(KeyEvent.VK_Q);
		//boutons du menu aide
		JMenuItem menuHelp = new JMenuItem("Aide...");
		menuHelp.setEnabled(false);
		menuAide.add(menuHelp);
		menuHelp.setMnemonic(KeyEvent.VK_A);
		JMenuItem menuAbout = new JMenuItem("A propos...");
		menuAide.add(menuAbout);
		menuAbout.setMnemonic(KeyEvent.VK_P);
		
		Fenetre.setJMenuBar(menu);
        
        //choix entre créer ou renommer
        JPanel panneauCreerRenommer = new JPanel();
        final JRadioButton boutonCreer = new JRadioButton("Créer");
        boutonCreer.setToolTipText("Crée une séquence de fichiers vides");
        panneauCreerRenommer.add(boutonCreer);
        JRadioButton boutonRenommer = new JRadioButton("Renommer / Renuméroter");
        boutonRenommer.setToolTipText("Renomme et/ou renumérote une séquence de fichiers");
        boutonRenommer.setSelected(true);
        panneauCreerRenommer.add(boutonRenommer);
        ButtonGroup groupeCreerRenommer = new ButtonGroup();
		groupeCreerRenommer.add(boutonCreer);
		groupeCreerRenommer.add(boutonRenommer);
		panneau.add(panneauCreerRenommer);
		 
        //choix du nombre de fichiers a créer
        JPanel panneauNombre = new JPanel();
		JLabel labelNombre = new JLabel("Nombre de fichiers à créer : ");
		panneauNombre.add(labelNombre);
		final JFormattedTextField textNombre = new JFormattedTextField();
		textNombre.setValue(0);
		textNombre.setColumns(4);
		textNombre.setEnabled(false);
		panneauNombre.add(textNombre);
		panneau.add(panneauNombre);
		
		//Choix des fichiers à renommer
		JPanel panneauFichiers = new JPanel();
		JPanel panneauFichiersListe = new JPanel();
		final JButton boutonFichiers = new JButton("Choisir les fichiers ");
		boutonFichiers.setToolTipText("Choisir les fichiers à renommer");
		panneauFichiers.add(boutonFichiers);
		final JComboBox boxFichiers = new JComboBox();
		boxFichiers.setToolTipText("Liste des fichiers prèts à être renommés");
		boxFichiers.setEnabled(false);
		panneauFichiersListe.add(boxFichiers);
		panneau.add(panneauFichiers);
		panneau.add(panneauFichiersListe);
		
		//choix du numéro de départ
		JPanel panneauNumero = new JPanel();
		JLabel labelNumero = new JLabel("Numéro de départ : ");
		panneauNumero.add(labelNumero);
		final JFormattedTextField textNumero = new JFormattedTextField();
		textNumero.setValue(0);
		textNumero.setColumns(4);
		textNumero.setEnabled(false);
		panneauNumero.add(textNumero);
		panneau.add(panneauNumero);
		
		//choix du nom des fichiers
		JPanel panneauNom = new JPanel();
		final JTextField textNom = new JTextField("nom_du_fichier");
		textNom.setColumns(15);
		textNom.setEnabled(false);
		panneauNom.add(textNom);
		String[] inc = {"#1","##2","###3","####4","#####5","######6"};
		final JComboBox boxIncrement = new JComboBox(inc);
		boxIncrement.setSelectedIndex(4);
		boxIncrement.setEnabled(false);
		panneauNom.add(boxIncrement);
		final JTextField textExtension = new JTextField(".ext");
		textExtension.setColumns(5);
		textExtension.setEnabled(false);
		panneauNom.add(textExtension);
		panneau.add(panneauNom);
		
		//options
		JPanel panneauOptions = new JPanel();
		final JButton boutonOptions = new JButton("Options");
		boutonOptions.setEnabled(false);
		panneauOptions.add(boutonOptions);
		panneau.add(panneauOptions);
		
		//destination et validation
		
		JPanel panneauValidation = new JPanel();
		final JButton boutonDossier = new JButton("Dossier");
		boutonDossier.setToolTipText("Dossier de destination");
		boutonDossier.setEnabled(false);
		panneauValidation.add(boutonDossier);
		final JButton boutonValider = new JButton("Renommer !");
		boutonValider.setEnabled(false);
		
		panneauValidation.add(boutonValider);
		panneau.add(panneauValidation);
		
		
		//CHOIX DES FICHIERS
	    //==================
		final JFileChooser choixFichiers = new JFileChooser();
        choixFichiers.setMultiSelectionEnabled(true);
        
		//CHOIX DU DOSSIER
	    //==================
		final JFileChooser choixDossier = new JFileChooser();
		choixDossier.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		//FENETRE OPTIONS
	    //==================
		final JFrame fenetreOptions = new JFrame();
		//Définit un titre pour la fenêtre
		fenetreOptions.setTitle("Dufile Options");
        //Définit une taille
		fenetreOptions.setSize(300, 120);
        //Mettre la fenêtre au milieu de l'écran
		fenetreOptions.setLocationRelativeTo(Fenetre);

		JPanel panneauOptions2 = new JPanel();
		panneauOptions2.setLayout(new GridLayout(3,1));
		
		JPanel panneauInverser = new JPanel();
		final JCheckBox boutonInverser = new JCheckBox("Inverser l'ordre");
		panneauInverser.add(boutonInverser);
		panneauOptions2.add(panneauInverser);
		
		JPanel panneauN = new JPanel();
		JLabel labelNDebut = new JLabel("Numéroter toutes les ");
		panneauN.add(labelNDebut);
		final JFormattedTextField textN = new JFormattedTextField();
		textN.setValue(1);
		textN.setColumns(1);
		panneauN.add(textN);
		JLabel labelNfin = new JLabel(" images.");
		panneauN.add(labelNfin);
		panneauOptions2.add(panneauN);
		//TODO prendre en compte l'option n
		
		JPanel panneauOptOK = new JPanel();
		JButton boutonOptOK = new JButton("OK");
		panneauOptOK.add(boutonOptOK);
		panneauOptions2.add(panneauOptOK);
		
		fenetreOptions.setContentPane(panneauOptions2);
		
		//Fenetre à propos
		//==========================================
		final JFrame aPropos = new JFrame();
		//Définit un titre pour votre fenêtre
		aPropos.setTitle("A propos");
        //Définit une taille pour celle-ci;
		aPropos.setSize(600, 300);
        //Nous allons maintenant dire à notre objet de se positionner au centre
		aPropos.setLocationRelativeTo(Fenetre);
		//le texte
		JPanel panneauAPropos = new JPanel();
		panneauAPropos.setLayout(new BorderLayout());
		JPanel panneauTete = new JPanel();
		panneauTete.setLayout(new GridLayout(5,1));
		JLabel Dufile = new JLabel("Dufile (DuDuF File Sequence Manager)");
		JLabel copyright = new JLabel("(c) Copyright 2009 Nicolas Dufresne");
		//TODO JLabel adresseWeb = new JLabel("http://dufile.duduf.com");
		JLabel mail = new JLabel("duduf@duduf.com");
		JLabel versionA = new JLabel("Version " + version);
		panneauTete.add(Dufile);
		panneauTete.add(versionA);
		panneauTete.add(copyright);
		//TODO panneauTete.add(adresseWeb);
		panneauTete.add(mail);
		JTextArea GNU = new JTextArea("        Bonjour !\n\n" + 
"    Je suis Nicolas Dufresne, même si on m'appelle souvent Duduf,\n" +
"je bosse à Ankama Animations, à Roubaix, où je fais du compositing\n" +
"sur la série Wakfu, actuellement diffusée sur France3.\n\n" + 
"    De temps en temps, j'écris des programmes java et des scripts pour\n" +
"After Effects histoire de simplifier la production...\n\n" +
"    En tant qu'infographiste (je ne suis pas développeur ni\n" +
"programmeur, ne nous trompons pas ! - et je ne veux pas le\n" +
"devenir, entendons-nous bien !) je sais à quel point c'est\n" +
"chiant d'être limité par la technique,de subir des trucs\n" +
"pas pratique, ou de se dire \"eh merde, si ils avaient\n" +
"pensé à ce bouton à la con, ce serait tellement plus pratique !\"...\n" +
"C'est pour ça que je crée ces scripts, et c'est aussi pour ça que\n" +
"Dufile est et restera gratuit et libre (voir les notes\n" +
"sur la license GNU-GPL plus bas). Pour que chacun puisse s'exprimer\n" +
"à la hauteur de son talent ! (rien que ça !)\n\n" + 
"    Par contre vous devez bien imaginer que cela prend\n" +
"du temps à créer, à tester, à améliorer ; donc si vous\n" +
"estimez que Dufile DuDuF File Sequence Manager mérite d'être acheté,\n" +
"vous pouvez le payer, au prix de votre choix,\n" +
"celui que vous estimerez juste.\n\n\n" +
"Dufile is free software: you can redistribute it and/or modify\n" +
"it under the terms of the GNU General Public License as published by\n" + 
"the Free Software Foundation, either version 3 of the License, or\n" + 
"(at your option) any later version.\n\n" + 
"Dufile is distributed in the hope that it will be useful,\n" + 
"but WITHOUT ANY WARRANTY; without even the implied warranty of\n" + 
"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" + 
"GNU General Public License for more details.\n\n" + 
"You should have received a copy of the GNU General Public License\n" + 
"along with Dufile.  If not, see <http://www.gnu.org/licenses/>.");
		GNU.setEditable(false);			
        JScrollPane scrollGNU = new JScrollPane(GNU);
		panneauAPropos.add(panneauTete,BorderLayout.NORTH);
		panneauAPropos.add(scrollGNU);
		aPropos.add(panneauAPropos);
		
		
		Fenetre.setContentPane(panneau);
		Fenetre.setVisible(true);
		

		

		//============================
		//ACTIONS
		//============================
		    
		
		//action du choix de Fichiers
        //==============================
		  ActionListener actionFichiers = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	//afficher la boite de dialogue
	        		int returnChoixFichiers = choixFichiers.showOpenDialog(Fenetre);
	        		
	        		//récupérer les fichiers qui ont été choisis
	        		File[] fichiersdemande = null;
	        		if (returnChoixFichiers == JFileChooser.APPROVE_OPTION){
	        			fichiersdemande = choixFichiers.getSelectedFiles();
	        			if(fichiersdemande[0].exists()){
	        			// TODO trouver les incréments pour les virer du nom, et les compter ou alors voir une facon de trouver par défaut dans les options
	        			String fichierNom = fichiersdemande[0].getName();
	        			String fichierNomS = fichierNom.substring(0,(fichierNom.lastIndexOf(".")));
	        			String fichierNomE = fichierNom.substring(fichierNom.lastIndexOf("."));
		        		textNom.setText(fichierNomS);
		        		textExtension.setText(fichierNomE);
	        			textNumero.setEnabled(true);
	        			textNom.setEnabled(true);
	        			boxIncrement.setEnabled(true);
	        			textExtension.setEnabled(true);
	        			boxFichiers.removeAllItems();
	        			for (File fichier : fichiersdemande){
	        				boxFichiers.addItem(fichier.getName());
	        			}
	        			boxFichiers.setEnabled(true);

	        				boutonValider.setEnabled(true);
	        				menuValider.setEnabled(true);
	        				boutonOptions.setEnabled(true);
	        				menuOptions.setEnabled(true);
	        		}else{

	        			textNumero.setEnabled(false);
	        			textNom.setEnabled(false);
	        			boxIncrement.setEnabled(false);
	        			textExtension.setEnabled(false);
	        			boxFichiers.setEnabled(false);
	        			boxFichiers.removeAllItems();
	        			boutonValider.setEnabled(false);
        				menuValider.setEnabled(false);
        				boutonOptions.setEnabled(false);
        				menuOptions.setEnabled(false);
	        		}}
	            }
	        	};
	        	
	        	boutonFichiers.addActionListener(actionFichiers);
	        	menuChoisir.addActionListener(actionFichiers);
		
		//action du bouton Options
        //==============================
	        	
	        	   ActionListener actionOptions = new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	fenetreOptions.setVisible(true);
		                }
	        	   };
	        	   boutonOptions.addActionListener(actionOptions);
	        	   menuOptions.addActionListener(actionOptions);
	       	
	        	   
	    //action du bouton Options OK
	    //==============================
	       	        	
	       	        	   ActionListener actionOptOK = new ActionListener() {
	       		                public void actionPerformed(ActionEvent e) {
	       		                	fenetreOptions.setVisible(false);
	       		                }
	       	        	   };
	       	        	   boutonOptOK.addActionListener(actionOptOK);	   
	        	   
		
		//action du bouton Dossier
        //==============================
	            ActionListener actionDossier = new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	            		//afficher la boite de dialogue pour choisir le projet
	            		int returnChoixDossier = choixDossier.showDialog(Fenetre,"Choisir un dossier");
	            		//récupérer le projet qui a été choisi
	            		File dossierdemande = null;
	            		if (returnChoixDossier == JFileChooser.APPROVE_OPTION)
	            			dossierdemande = choixDossier.getSelectedFile();
	            		//changer le texte du bouton
	            		if (dossierdemande.exists()){
	            			boutonDossier.setText(dossierdemande.getName());
	            			boutonValider.setEnabled(true);
	            			menuValider.setEnabled(true);
	            		}
	            		else {
	            			boutonDossier.setText("Dossier");
	            			boutonValider.setEnabled(false);
	            			menuValider.setEnabled(false);
	            		}
	                }
	            	};
	            	
	            	boutonDossier.addActionListener(actionDossier);
	            	menuDossier.addActionListener(actionDossier);
		
		//action de créer
        //==============================
	            	final ActionListener actionCreer = new ActionListener() {
	                	public void actionPerformed(ActionEvent e) {
	                		
	                		//curseur attente
	            	    	Fenetre.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	                       
	                		//les variables
	                		int Nombre = ((Number)textNombre.getValue()).intValue();
	                		int Numero = ((Number)textNumero.getValue()).intValue();
	                		String Nom = textNom.getText();
	                		int Increment = boxIncrement.getSelectedIndex() + 1;
	                		String Extension = textExtension.getText();
	                		String Zeros = "";
	                		if(Nombre<20000){
	                		//ajouter des zeros au début de lincrément, en fonction de lincrément demandé
	                		for (int j = 1; j<Increment;j++){
	                			Zeros = Zeros + "0";
	                			}
	                		
	                		//créer les fichiers
	                		int k = ((Number)textN.getValue()).intValue();
	                		if(k<=0)
	                			k=1;
	                		for (int i=0;i<Nombre*k;i=i+k){
	                			
	                			//créer l'incrément
	                			int IncrementDecal = i + Numero;
	                			String IncrementTemp = Zeros + IncrementDecal;
	                			
	                			
	                				//enlever les zéros en trop au début
	                				int longueur = IncrementTemp.length();
	                				int entrop = longueur - Increment;
	                				String IncrementDef = IncrementTemp.substring(entrop);
	                			
	                			
	                			//le nom de fichier
	                			String NomFinal = Nom + IncrementDef + Extension;
	                			
	                			//le dossier
	                			File dossier = choixDossier.getSelectedFile();
	                			File fichier = new File(dossier.getAbsolutePath() + "\\" + NomFinal);
	                			
	                		

	                				try {
	        							fichier.createNewFile();
	        						} catch (IOException e1) {
	        							e1.printStackTrace();
	        						
	                			}
	                		}}
	                		Fenetre.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	                		
	                           }
	                	   };
		
		//action de renommer
        //==============================
	    final ActionListener actionRenommer = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

	    	
	    	//curseur attente
	    	Fenetre.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//le chemin ou on prend le fichier
			File[] fichiers = choixFichiers.getSelectedFiles();
			File fichier = fichiers[0];
	    	String fichierChemin = fichier.getParent();
	    	//les variables
	    	int Nombre = fichiers.length;
    		int Numero = ((Number)textNumero.getValue()).intValue();
    		String Nom = textNom.getText();
    		int Increment = boxIncrement.getSelectedIndex() + 1;
    		String Extension = textExtension.getText();
    		String Zeros = "";
    		for (int j = 1; j<Increment;j++){
    			Zeros = Zeros + "0";
    			}
    		
    		int k = ((Number)textN.getValue()).intValue();
    		if(k<=0)
    			k=1;
    		int l = 0;
    		if(boutonInverser.isSelected()){
    			
				for (int i=Nombre*k;i>0;i=i-k){
					File fichierRenomme = fichiers[l];
					//créer l'incrément
					int IncrementSansZero = i+Numero;
	    			String IncrementTemp = Zeros + IncrementSansZero;
	    			//enlever les zéros en trop au début
	    			int longueur = IncrementTemp.length();
	    			int entrop = longueur - Increment;
	    			String IncrementDef = IncrementTemp.substring(entrop);
	       			//le nom de fichier
	    			String NomFinal = Nom + IncrementDef + Extension;
	    			//renommer le fichier
					File fichierNouveau = new File(fichierChemin + "\\" + NomFinal + "tmp");
					Boolean pbRenomme = fichierRenomme.renameTo(fichierNouveau);
					fichiers[l] = fichierNouveau;
					if(!pbRenomme){
					//TODO en cas de pas réussi a renommer
						System.out.println("oups");
					}
					l++;
					}
				for (int m=0;m<Nombre;m++){
					String fichierFinalChemin = fichiers[m].getAbsolutePath();
					File fichierFinal = new File(fichierFinalChemin.substring(0,fichierFinalChemin.length()-3));
					Boolean pbRenomme = fichiers[m].renameTo(fichierFinal);
					if(!pbRenomme){
						//TODO en cas de pas réussi a renommer
							System.out.println("oups");
						}
				}
    		}else {
    		
				for (int i=0;i<Nombre*k;i=i+k){
				File fichierRenomme = fichiers[l];
				//créer l'incrément
				int IncrementSansZero = i+Numero;
    			String IncrementTemp = Zeros + IncrementSansZero;
    			//enlever les zéros en trop au début
    			int longueur = IncrementTemp.length();
    			int entrop = longueur - Increment;
    			String IncrementDef = IncrementTemp.substring(entrop);
       			//le nom de fichier
    			String NomFinal = Nom + IncrementDef + Extension;
    			//renommer le fichier
				File fichierNouveau = new File(fichierChemin + "\\" + NomFinal + "tmp");
				Boolean pbRenomme = fichierRenomme.renameTo(fichierNouveau);
				fichiers[l] = fichierNouveau;
				if(!pbRenomme){
				//TODO en cas de pas réussi a renommer
					System.out.println("oups");
				}
				l++;
				}
				for (int m=0;m<Nombre;m++){
					String fichierFinalChemin = fichiers[m].getAbsolutePath();
					File fichierFinal = new File(fichierFinalChemin.substring(0,fichierFinalChemin.length()-3));
					Boolean pbRenomme = fichiers[m].renameTo(fichierFinal);
					if(!pbRenomme){
						//TODO en cas de pas réussi a renommer
							System.out.println("oups");
						}
				}
    		}
				Fenetre.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				boutonValider.setEnabled(false);
				menuValider.setEnabled(false);
				boxFichiers.setEnabled(false);
				textNumero.setEnabled(false);
				textNom.setEnabled(false);
				boxIncrement.setEnabled(false);
				textExtension.setEnabled(false);
				boutonOptions.setEnabled(false);
				menuOptions.setEnabled(false);

	    }
	    };
	               	
	    
	    boutonValider.addActionListener(actionRenommer);
		menuValider.addActionListener(actionRenommer);
	                	   
		//action de a propos
        //==============================
		 ActionListener actionAPropos = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	aPropos.setVisible(true);
	            }
	        	};
	        	menuAbout.addActionListener(actionAPropos);
		
		//action du bouton quitter
        //=========================================
        ActionListener actionQuitter = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        	};
	
        menuQuitter.addActionListener(actionQuitter);
        
        //action du choix Creer ou Renommer
        //==============================
        
        final ActionListener actionCreerRenommer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		if(boutonCreer.isSelected()){
        			textNombre.setEnabled(true);
        			boutonFichiers.setEnabled(false);
        			boxFichiers.setEnabled(false);
        			textNumero.setEnabled(true);
        			textNom.setEnabled(true);
        			boxIncrement.setEnabled(true);
        			textExtension.setEnabled(true);
        			boutonDossier.setEnabled(true);
        			boutonValider.setText("Créer !");
        			menuChoisir.setEnabled(false);
        			menuDossier.setEnabled(true);
        			menuValider.setText("Créer !");
        			if(!(boutonDossier.getText().equals("Dossier"))){
        				boutonValider.setEnabled(true);
        				menuValider.setEnabled(true);
        			}else {
        				boutonValider.setEnabled(false);
        				menuValider.setEnabled(false);
        			}
        			boutonValider.removeActionListener(actionRenommer);
        			boutonValider.addActionListener(actionCreer);
        			menuValider.removeActionListener(actionRenommer);
        			menuValider.addActionListener(actionCreer);
        			boutonOptions.setEnabled(true);
    				menuOptions.setEnabled(true);
        		
        		}else{
        			boutonValider.removeActionListener(actionCreer);
        			boutonValider.addActionListener(actionRenommer);
        			menuValider.removeActionListener(actionCreer);
        			menuValider.addActionListener(actionRenommer);
        			textNombre.setEnabled(false);
        			boutonFichiers.setEnabled(true);
        			boutonValider.setText("Renommer !");
        			menuChoisir.setEnabled(true);
        			menuValider.setText("Renommer !");
	        			if(boxFichiers.getItemCount()!=0){
	        			boutonDossier.setEnabled(true);
	        			menuDossier.setEnabled(false);
	        			textNumero.setEnabled(false);
	        			textNom.setEnabled(true);
	        			boxIncrement.setEnabled(true);
	        			textExtension.setEnabled(true);
	        			boxFichiers.setEnabled(true);
	        			boutonValider.setEnabled(true);
	        			menuValider.setEnabled(true);
	        			boutonOptions.setEnabled(true);
        				menuOptions.setEnabled(true);

	        		}else{
	        			boutonDossier.setEnabled(false);
	        			menuDossier.setEnabled(false);
	        			textNumero.setEnabled(false);
	        			textNom.setEnabled(false);
	        			boxIncrement.setEnabled(false);
	        			textExtension.setEnabled(false);
	        			boxFichiers.setEnabled(false);
	        			boutonValider.setEnabled(false);
        				menuValider.setEnabled(false);
        				boutonOptions.setEnabled(false);
        				menuOptions.setEnabled(false);
	        		}
	        			
	        			
	        			
        		}
        		}
        	};
		
        boutonCreer.addActionListener(actionCreerRenommer);
		boutonRenommer.addActionListener(actionCreerRenommer);
		
		
		
		

	}

}
