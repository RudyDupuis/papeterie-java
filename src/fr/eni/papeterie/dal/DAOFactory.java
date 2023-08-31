package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDaoJdbclmpl;

public class DAOFactory {
	public static ArticleDAO getArticleDAO() {
		ArticleDAO articleDAO = new ArticleDaoJdbclmpl();
		return articleDAO;
	}

}
