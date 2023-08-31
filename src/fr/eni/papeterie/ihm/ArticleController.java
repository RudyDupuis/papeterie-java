package fr.eni.papeterie.ihm;

import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.ercCatalogue.CatalogueWindow;
import fr.eni.papeterie.ihm.ercGestion.IPanelBoutonsObserver;
import fr.eni.papeterie.ihm.ercGestion.GestionWindow;

public class ArticleController implements IPanelBoutonsObserver {
	
	private GestionWindow gestionWindow ;
	
	private CatalogueWindow catalogueWindow ;
	
	private int indexCatalogue;
	
	private CatalogueManager mger;
	
	private List<Article> catalogue;
	
	private static ArticleController instance;
	
	
	private ArticleController(){
		try {
			mger = new CatalogueManager();
			catalogue = mger.getCatalogue();
		} catch (BLLException e) {
			gestionWindow.addError("Erreur avec la base de donnée.");
			e.printStackTrace();
		}
	}
	

	public static synchronized ArticleController get(){
		if(instance == null){
			instance = new ArticleController();
		}
		return instance;
	}
	
	//Start
	public void startAppGestion(){
		gestionWindow = new GestionWindow();
		
		afficherPremierArticle();
		gestionWindow.setVisible(true);
	}
	
	
	public void startAppCatalogue(){
		catalogueWindow = new CatalogueWindow();
		
		catalogueWindow.updateCatalogueTable(catalogue);
		catalogueWindow.setVisible(true);
	}
	
	public void afficherPremierArticle(){
		if(catalogue.size()>0){
			indexCatalogue = 0;
			gestionWindow.afficherArticle(catalogue.get(indexCatalogue));
		}else{
			indexCatalogue = -1;
			gestionWindow.afficherNouveau();
		}

	}

	
	//Method
	public void precedent(){
		if(indexCatalogue > 0){
			indexCatalogue--;
			gestionWindow.afficherArticle(catalogue.get(indexCatalogue));
		}
		
	}

	public void suivant() {
		if(indexCatalogue < catalogue.size()-1){
			indexCatalogue++;
			gestionWindow.afficherArticle(catalogue.get(indexCatalogue));
		}

	}

	public void nouveau() {
		indexCatalogue = catalogue.size();
		gestionWindow.afficherNouveau();
		
	}
	
	//Crud
	public void enregistrer() {
		Article articleAffiche = gestionWindow.getArticle();
		
		try {
			if(articleAffiche.getIdArticle() == -1){
				mger.addArticle(articleAffiche);
				gestionWindow.addMessage("Article ajouté !");
				catalogue.add(articleAffiche);
				gestionWindow.afficherArticle(articleAffiche);
				
				if(catalogueWindow != null) {
					catalogueWindow.updateCatalogueTable(catalogue);
				}
			}else{
				mger.updateArticle(articleAffiche);
				gestionWindow.addMessage("Article modifié !");
				catalogue.set(indexCatalogue, articleAffiche);
				
				if(catalogueWindow != null) {
					catalogueWindow.updateCatalogueTable(catalogue);
				}
			}
		} catch (BLLException e1) {
			gestionWindow.addError("" + e1);
		}
		
	}

	public void supprimer() {
		Article articleAffiche = gestionWindow.getArticle();
		
		if(articleAffiche.getIdArticle() == -1) {
			gestionWindow.addError("Il n'y a plus d'articles ...");
		} else {
			
		try {
			mger.removeArticle(catalogue.get(indexCatalogue));
			gestionWindow.addMessage("Article supprimé !");
			catalogue.remove(indexCatalogue);
		} catch (BLLException e) {
			gestionWindow.addError("Erreur suppression.");
		}		

		if (indexCatalogue < catalogue.size() ) {
			gestionWindow.afficherArticle(catalogue.get(indexCatalogue));
		} else if (indexCatalogue > 0) {
			indexCatalogue--;
			gestionWindow.afficherArticle(catalogue.get(indexCatalogue));
		} else {
			gestionWindow.afficherNouveau();
		}
		if(catalogueWindow != null) {
			catalogueWindow.updateCatalogueTable(catalogue);
		}
		
		}
	}


}

