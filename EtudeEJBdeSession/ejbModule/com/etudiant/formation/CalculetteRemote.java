/*
 * Cette interface sera utilis�e pour exporter des m�thodes vers des applications
 * se trouvant � l'ext�rieur du conteneur EJB.
 * 
 */
package com.etudiant.formation;

import javax.ejb.Remote;

@Remote
public interface CalculetteRemote {
	double calculer(String operateur, double df1, double df2) throws Exception;
	double recupererResultatPrecedent();

}
