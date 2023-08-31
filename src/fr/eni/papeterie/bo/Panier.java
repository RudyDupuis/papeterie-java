package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	private List<Ligne> lignes = new ArrayList<>();
	private float montant;

	public float getMontant() {
		return montant;
	}

	public Ligne getLigne(int index) {
		return lignes.get(index);
	}
	
	public List<Ligne> getLignesPanier(int index) {
		return lignes;
	}
	
	public void addLigne(Article article, int qte) {
		Ligne ligne = new Ligne(article, qte);
		lignes.add(ligne);
		this.montant += ligne.getPrix();
	}
	
	public void updateLigne(int index, int newQte) {
		Ligne ligne = lignes.get(index);
		this.montant -= ligne.getPrix();
		ligne.setQte(newQte);
		this.montant += ligne.getPrix();
		lignes.set(index, ligne);
	}
	
	public void removeLigne(int index) {
		Ligne ligne = lignes.get(index);
		lignes.remove(index);
		this.montant -= ligne.getPrix();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Panier: \n");
		
		for(Ligne ligne : lignes) {
			string.append("ligne ")
					.append(ligne.getArticle().getIdArticle() - 1)
					.append("     ")
					.append(ligne)
					.append("\n");
		}
		
		string.append("\nValeur du panier : ").append(montant);
		return string.toString();
	}
	
	

}
