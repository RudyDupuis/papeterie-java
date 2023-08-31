package fr.eni.papeterie.bo;

public class Stylo extends Article {
	private String Couleur;
	
	public Stylo() {
		
	}

	public Stylo(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock) {
		super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
	}

	public Stylo(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock,
			String couleur) {
		super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
		Couleur = couleur;
	}
	
	public Stylo(String reference, String marque, String designation, float prixUnitaire, int qteStock,
			String couleur) {
		super(reference, marque, designation, prixUnitaire, qteStock);
		Couleur = couleur;
	}

	public String getCouleur() {
		return Couleur;
	}

	public void setCouleur(String couleur) {
		Couleur = couleur;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Stylo[couleur=" + Couleur + "]";
	}
	
	
	
}
