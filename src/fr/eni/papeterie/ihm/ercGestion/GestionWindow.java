package fr.eni.papeterie.ihm.ercGestion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class GestionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Inputs i = new Inputs();
	private Integer idCourant;
	
	public GestionWindow() {
		this.setTitle("Gestion");
		this.setSize(420, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initIHM();
	}
	
	private void initIHM() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		addLine(0, mainPanel, i.getLblReference(),i.getTxtReference());
		addLine(1, mainPanel, i.getLblDesignation(),i.getTxtDesignation());
		addLine(2, mainPanel, i.getLblMarque(),i.getTxtMarque());		
		addLine(3, mainPanel, i.getLblStock(),i.getTxtStock());		
		addLine(4, mainPanel, i.getLblPrix(),i.getTxtPrix());
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(i.getRbRamette());
		buttonGroup.add(i.getRbStylo());
		
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(i.getRbRamette());
		radioPanel.add(i.getRbStylo());

		addLine(5, mainPanel, i.getLblType(), radioPanel);
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(i.getCb80());
		buttonGroup2.add(i.getCb100());

		JPanel checkBPanel = new JPanel(new GridLayout(0, 1));
		checkBPanel.add(i.getCb80());
		checkBPanel.add(i.getCb100());
		
		addLine(6, mainPanel, i.getLblGrammage(), checkBPanel);
		addLine(7, mainPanel, i.getLblCouleur(), i.getLCouleur());
		
		PannelBoutons pannelBoutons = new PannelBoutons();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 0, 0, 0);
		mainPanel.add(pannelBoutons, gbc);
		
		
		this.setContentPane(mainPanel);
	}
	
	private <T extends JComponent>  void addLine(int lineNumber, JPanel mainPanel, JLabel label,T element) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 0, 3, 0);
		gbc.gridy = lineNumber;
		gbc.gridx = 0;
		
		mainPanel.add(label, gbc);
		
		gbc.gridx = 1;
		
		mainPanel.add(element, gbc);
	}
	
	public void afficherNouveau() {
		Ramette a = new Ramette(-1, "", "", "", 0, 0, 0);

		afficherArticle(a); 
	}
	
	public void afficherArticle(Article a) {
		idCourant = a.getIdArticle();
		
		i.getTxtReference().setText(a.getReference());
		i.getTxtMarque().setText(a.getMarque());
		i.getTxtDesignation().setText(a.getDesignation());
		i.getTxtPrix().setText(String.valueOf(a.getPrixUnitaire()));
		i.getTxtStock().setText(String.valueOf(a.getQteStock()));
		
		if (a.getClass().equals(Stylo.class)) {
			i.getRbStylo().setSelected(true);
			i.getLCouleur().setEnabled(true);
			i.getLCouleur().setSelectedItem(((Stylo) a).getCouleur());
			i.getCb80().setEnabled(false);
			i.getCb100().setEnabled(false);
		}
		if (a.getClass().equals(Ramette.class)) {
			i.getRbRamette().setSelected(true);
			i.getCb80().setEnabled(true);
			i.getCb100().setEnabled(true);
			i.getCb80().setSelected(((Ramette) a).getGrammage() == 80);
			i.getCb100().setSelected(((Ramette) a).getGrammage() == 100);
			i.getLCouleur().setSelectedItem(null);
			i.getLCouleur().setEnabled(false);
		}
		
		i.getRbRamette().setEnabled(a.getIdArticle() == -1);
		i.getRbStylo().setEnabled(a.getIdArticle() == -1);
	}
	
	public Article getArticle() {
		Article article=null;
		if(i.getRbStylo().isSelected()){
			article = new Stylo();
		}else{
			article = new Ramette();
		}
		try {
			article.setIdArticle(idCourant);
			article.setReference( i.getTxtReference().getText());
			article.setMarque(i.getTxtMarque().getText());
			article.setDesignation( i.getTxtDesignation().getText());
			article.setPrixUnitaire(Float.parseFloat(i.getTxtPrix().getText()));
			article.setQteStock(Integer.parseInt(i.getTxtStock().getText()));
			
			if (i.getLCouleur().isEnabled()) {
				((Stylo)article).setCouleur( (String) i.getLCouleur().getSelectedItem());
			} else {
				((Ramette) article).setGrammage(i.getCb80().isSelected()?80:100);
			}
		} catch (Exception e) {
			addError("Erreur lors de la mise des donnée en objet.");
			e.printStackTrace();
		}
		return article;
	}
	
	public void addMessage(String msg) {
		JOptionPane.showMessageDialog(GestionWindow.this, msg, "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void addError(String msg) {
		JOptionPane.showMessageDialog(GestionWindow.this, msg, "", JOptionPane.ERROR_MESSAGE);
	}

}
