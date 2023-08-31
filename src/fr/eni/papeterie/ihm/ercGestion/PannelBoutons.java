package fr.eni.papeterie.ihm.ercGestion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.eni.papeterie.ihm.ArticleController;

public class PannelBoutons extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton bBefore;
	private JButton bAfter;
	private JButton bCreate;
	private JButton bUpdate;
	private JButton bDelete;
	
	public PannelBoutons() {
		super(new GridLayout(0, 5));
		addPanelBouton();
	}

	private void addPanelBouton() {
		this.add(getbBefore());
		this.add(getbCreate());
		this.add(getbUpdate());
		this.add(getbDelete());
		this.add(getbAfter());
	}

	private JButton getbBefore() {
		ImageIcon icon = new ImageIcon(getClass().getResource("img/Back24.gif"));
		
		if(bBefore == null) {
			bBefore = new JButton(icon);
			bBefore.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().precedent();
				}

			});
		}
		return bBefore;
	}
	
	private JButton getbAfter() {
		ImageIcon icon = new ImageIcon(getClass().getResource("img/Forward24.gif"));
		
		if(bAfter == null) {
			bAfter = new JButton(icon);
			bAfter.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().suivant();;
				}

			});
		}
		return bAfter;
	}
	
	private JButton getbCreate() {
		ImageIcon icon = new ImageIcon(getClass().getResource("img/New24.gif"));
		
		if(bCreate == null) {
			bCreate = new JButton(icon);
			bCreate.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().nouveau();
				}

			});
		}
		return bCreate;
	}
	
	private JButton getbUpdate() {
		ImageIcon icon = new ImageIcon(getClass().getResource("img/Save24.gif"));
		
		if(bUpdate == null) {
			bUpdate = new JButton(icon);
			bUpdate.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().enregistrer();
				}

			});
		}
		return bUpdate;
	}
	
	private JButton getbDelete() {
		ImageIcon icon = new ImageIcon(getClass().getResource("img/Delete24.gif"));
		
		if(bDelete == null) {
			bDelete = new JButton(icon);
			bDelete.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().supprimer();
				}

			});
		}
		return bDelete;
	}
	

}
