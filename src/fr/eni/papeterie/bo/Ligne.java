package fr.eni.papeterie.bo;

public class Ligne {
	private int qte;
	private Article article;
	private float prix;

	public Ligne(Article article, int qte) {
		this.article = article;
		this.qte = qte;
		this.prix = this.qte * this.article.getPrixUnitaire();
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
		this.prix = this.qte * this.article.getPrixUnitaire();
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
		this.prix = this.qte * this.article.getPrixUnitaire();
	}
	
	public float getPrix() {
		return prix;
	}

	@Override
	public String toString() {
		return "Ligne [qte=" + qte + ", prix=" + prix + ", article=" + article + "]";
	}
	
	
	
	
	
	

}
