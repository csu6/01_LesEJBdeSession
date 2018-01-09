/*
 * Cette interface sera utilisée pour exporter des méthodes vers des applications
 * se trouvant à l'extérieur du conteneur EJB.
 * 
 */
package com.etudiant.formation;

import javax.ejb.Remote;

@Remote
public interface CalculetteRemote {
	double calculer(String operateur, double df1, double df2) throws Exception;
	double recupererResultatPrecedent();

}
