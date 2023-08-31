package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;

public class ArticleDaoJdbclmpl implements ArticleDAO {

	public Article selectById(int id) throws DALException {
		String sql = "SELECT * FROM articles WHERE idArticle = ?";
		Article article = null;

		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String type = rs.getString("type").trim();
				if ("Stylo".equals(type)) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				} else if ("Ramette".equals(type)) {
					article = new Ramette(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la sélection de l'article par id ", e);
		}

		return article;
	}
	
	public Article selectByMarque(String marque) throws DALException {
		String sql = "SELECT * FROM articles WHERE marque = ?";
		Article article = null;

		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, marque);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String type = rs.getString("type").trim();
				if ("Stylo".equals(type)) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				} else if ("Ramette".equals(type)) {
					article = new Ramette(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la sélection de l'article par id ", e);
		}

		return article;
	}
	
	public Article selectByMotCle(String motCle) throws DALException {
		String sql = "SELECT * FROM articles WHERE designation LIKE ?";
		Article article = null;

		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, "%" + motCle + "%");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String type = rs.getString("type").trim();
				if ("Stylo".equals(type)) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				} else if ("Ramette".equals(type)) {
					article = new Ramette(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la sélection de l'article par id ", e);
		}

		return article;
	}

	public List<Article> selectAll() throws DALException {
		String sql = "SELECT * FROM articles";
		List<Article> articles = new ArrayList<>();

		try (Connection connection = JdbcTools.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String type = rs.getString("type").trim();
				Article article = null;
				if ("Stylo".equals(type)) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				} else if ("Ramette".equals(type)) {
					article = new Ramette(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				if (article != null) {
					articles.add(article);
				}
			}
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la sélection de tout les articles  ", e);
		}

		return articles;
	}

	public void update(Article article) throws DALException {
		String sql = "UPDATE articles SET reference = ?, marque = ?, designation = ?, prixUnitaire = ?, qteStock = ?, grammage = ?, couleur = ?, type = ? WHERE idArticle = ?";

		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, article.getReference());
			ps.setString(2, article.getMarque());
			ps.setString(3, article.getDesignation());
			ps.setFloat(4, article.getPrixUnitaire());
			ps.setInt(5, article.getQteStock());

			if (article instanceof Ramette) {
				ps.setInt(6, ((Ramette) article).getGrammage());
				ps.setNull(7, Types.VARCHAR);
				ps.setString(8, "Ramette");
			} else if (article instanceof Stylo) {
				ps.setNull(6, Types.INTEGER);
				ps.setString(7, ((Stylo) article).getCouleur());
				ps.setString(8, "Stylo");
			}

			ps.setInt(9, article.getIdArticle());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour de l'article  ", e);
		}
	}

	public void insert(Article article) throws DALException {
		String sql = "INSERT INTO articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, article.getReference());
			ps.setString(2, article.getMarque());
			ps.setString(3, article.getDesignation());
			ps.setFloat(4, article.getPrixUnitaire());
			ps.setInt(5, article.getQteStock());
			ps.setObject(6, article instanceof Ramette ? ((Ramette) article).getGrammage() : null);
			ps.setObject(7, article instanceof Stylo ? ((Stylo) article).getCouleur() : null);
			ps.setString(8, article instanceof Ramette ? "Ramette" : "Stylo");

			ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				article.setIdArticle(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'insertion de l'article  ", e);
		}
	}

	public void delete(int id) throws DALException {
		String sql = "DELETE FROM articles WHERE idArticle = ?";

		try (Connection connection = JdbcTools.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppression de l'article  ", e);
		}
	}

}
