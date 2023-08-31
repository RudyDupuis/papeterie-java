package fr.eni.papeteries.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {
	private DAO<Article> articleDAO = DAOFactory.getArticleDAO();

	public CatalogueManager() throws BLLException {

	}

	private void checkArticle(Article article) throws BLLException {
		StringBuilder exceptionMessage = new StringBuilder();
		boolean hasError = false;
		
		// check if attribute is null
		String[] stringAttributs = { article.getReference(), article.getMarque(), article.getDesignation() };

		for (String attribut : stringAttributs) {
			if (attribut == null || attribut.isBlank()) {
				exceptionMessage.append("\nLes attributs ne peuvent pas être vide");
				hasError = true;
				break;
			}
		}

		if (article instanceof Stylo && ((Stylo) article).getCouleur() == null) {
			exceptionMessage.append("\nL'attribut couleur ne peut pas être null");;
			hasError = true;
		}

		// check if number is not negative
		if (article instanceof Ramette && ((Ramette) article).getGrammage() < 0) {
			exceptionMessage.append("\nLe grammage doit être un nombre positif");
			hasError = true;
		}

		if (article.getQteStock() < 0) {
			exceptionMessage.append("\nLa quantité doit être un nombre positif");
			hasError = true;
		}
	
		
		if(hasError) {
			throw new BLLException(exceptionMessage.toString());
		}
		
	}

	public List<Article> getCatalogue() throws BLLException {
		try {
			return articleDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur :" + e);
		}
	}

	public Article getCatalogue(int id) throws BLLException {
		try {
			return articleDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Erreur :" + e);
		}
	}

	public void addArticle(Article article) throws BLLException {
		checkArticle(article);

		try {
			articleDAO.insert(article);
		} catch (DALException e) {
			throw new BLLException("Erreur :" + e);
		}
	}

	public void updateArticle(Article article) throws BLLException {
		checkArticle(article);

		try {
			articleDAO.update(article);
		} catch (DALException e) {
			throw new BLLException("Erreur :" + e);
		}
	}

	public void removeArticle(Article article) throws BLLException {
		try {
			articleDAO.delete(article.getIdArticle());
		} catch (DALException e) {
			throw new BLLException("Erreur :" + e);
		}
	}

}
