package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (estDansUnGarage() && myStationnements.isEmpty()==false){
			throw new java.lang.Exception("Le garage est déjà occupé");
		}

		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {

		// TODO: Implémenter cette méthode
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement

		if (estDansUnGarage()==false || myStationnements.isEmpty()){
			throw new java.lang.Exception("Il n'y a as de stationnement en cours");
		}
		myStationnements.get(myStationnements.size()-1).terminer();
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
		HashSet<Garage> garagesVisites = new HashSet<Garage>();
		for(Stationnement s : myStationnements){
			garagesVisites.add(s.getGarage());
		}
		return garagesVisites;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// Vrai si le dernier stationnement est en cours
		boolean b = true;
		if(myStationnements.isEmpty()){
			b = false;
		}
		else{
			Stationnement lastStat = myStationnements.get(myStationnements.size()-1);
			if(!lastStat.estEnCours()){
				b = false;
			}
		}
		return b;
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode
		// Utiliser les méthodes toString() de Garage et Stationnement
		for(Garage g : this.garagesVisites()){
			out.println(g + ": ");
			for(Stationnement s : myStationnements){
				if(s.getGarage().equals(g)){
					out.println("       " + s);
				}
			}
	}

	}
}
