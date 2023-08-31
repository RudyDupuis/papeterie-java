package fr.eni.papeterie.ihm.ercCatalogue;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Stylo;

public class CatalogueWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CatalogueWindow() {
		this.setTitle("Catalogue");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void updateCatalogueTable(List<Article> articles) {
		DefaultTableModel tableModel = new DefaultTableModel(
	            new Object[]{" ", "References", "Marque", "Designation", "Prix Unitaire", "Stock"}, 
	            0
	        );

	    JTable mainTable = new JTable(tableModel);
	    mainTable.getColumnModel().getColumn(3).setPreferredWidth(200); 

        JScrollPane scrollPane = new JScrollPane(mainTable);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        
		for(Article article : articles) {
			Object[] rowData = {
					article instanceof Stylo ? "Stylo" : "Ramette", 
					article.getReference(), article.getMarque(),
					article.getDesignation(), 
					article.getPrixUnitaire(), 
					article.getQteStock()
			};
			
	        tableModel.addRow(rowData);
		}
		
		this.add(scrollPane); 
        this.pack(); 
        this.setLocationRelativeTo(null);
	}
}
