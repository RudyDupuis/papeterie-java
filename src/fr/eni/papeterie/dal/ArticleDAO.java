package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public Article selectByMarque(String marque) throws DALException;
	public Article selectByMotCle(String motCle) throws DALException;
}
