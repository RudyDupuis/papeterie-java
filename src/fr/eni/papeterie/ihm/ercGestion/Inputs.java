package fr.eni.papeterie.ihm.ercGestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Inputs {
	private JLabel lblReference;
	private JTextField txtReference;
	private JLabel lblDesignation;
	private JTextField txtDesignation;
	private JLabel lblMarque;
	private JTextField txtMarque;
	private JLabel lblStock;
	private JTextField txtStock;
	private JLabel lblPrix;
	private JTextField txtPrix;

	private JLabel lblType;
	private JLabel lblGrammage;
	private JLabel lblCouleur;
	private JRadioButton rbRamette;
	private JRadioButton rbStylo;
	private JRadioButton cb80;
	private JRadioButton cb100;
	private JComboBox<String> lCouleur;
	
	private String[] colorOptions = {"Rouge", "Vert", "Bleu", "Noir"};

	public JLabel getLblReference() {
		if(lblReference == null) {
			lblReference = new JLabel("Référence : ");
		}
		return lblReference;
	}

	public JTextField getTxtReference() {
		if(txtReference == null) {
			txtReference = new JTextField(20);
		}
		return txtReference;
	}

	public JLabel getLblDesignation() {
		if(lblDesignation == null) {
			lblDesignation = new JLabel("Désignation : ");
		}
		return lblDesignation;
	}

	public JTextField getTxtDesignation() {
		if(txtDesignation == null) {
			txtDesignation = new JTextField(20);
		}
		return txtDesignation;
	}

	public JLabel getLblMarque() {
		if(lblMarque == null) {
			lblMarque = new JLabel("Marque : ");
		}
		return lblMarque;
	}

	public JTextField getTxtMarque() {
		if(txtMarque == null) {
			txtMarque = new JTextField(20);
		}
		return txtMarque;
	}

	public JLabel getLblStock() {
		if(lblStock == null) {
			lblStock = new JLabel("Stock : ");
		}
		return lblStock;
	}

	public JTextField getTxtStock() {
		if(txtStock == null) {
			txtStock = new JTextField(20);
		}
		return txtStock;
	}

	public JLabel getLblPrix() {
		if(lblPrix == null) {
			lblPrix = new JLabel("Prix : ");
		}
		return lblPrix;
	}

	public JTextField getTxtPrix() {
		if(txtPrix == null) {
			txtPrix = new JTextField(20);
		}
		return txtPrix;
	}
	
	
	public JLabel getLblType() {
		if(lblType == null) {
			lblType = new JLabel("Type : ");
		}
		return lblType;
	}

	public JLabel getLblGrammage() {
		if(lblGrammage == null) {
			lblGrammage = new JLabel("Grammage : ");
		}
		return lblGrammage;
	}

	public JLabel getLblCouleur() {
		if(lblCouleur == null) {
			lblCouleur = new JLabel("Couleur : ");
		}
		return lblCouleur;
	}

	public JRadioButton getRbRamette() {
		if(rbRamette == null) {
			rbRamette = new JRadioButton("Ramette");
			rbRamette.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					getCb80().setEnabled(true);
					getCb100().setEnabled(true);
					getLCouleur().setEnabled(false);		
				}
				
			});
		}
		return rbRamette;
	}
	
	public JRadioButton getRbStylo() {
		if(rbStylo == null) {
			rbStylo = new JRadioButton("Stylo");
			rbStylo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					getCb80().setEnabled(false);
					getCb100().setEnabled(false);
					getLCouleur().setEnabled(true);		
				}
				
			});
		}
		return rbStylo;
	}
	
	public JRadioButton getCb80() {
		if(cb80 == null) {
			cb80 = new JRadioButton("80 grammes");
		}
		return cb80;
	}
	
	public JRadioButton getCb100() {
		if(cb100 == null) {
			cb100 = new JRadioButton("100 grammes");
		}
		return cb100;
	}
	
	public JComboBox<String> getLCouleur() {
		if(lCouleur == null) {
			lCouleur = new JComboBox<String>(colorOptions);
		}
		return lCouleur;
	}

}


