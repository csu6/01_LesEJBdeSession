package com.etudiant.m2i;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.etudiant.formation.CalculetteRemote;

public class Main {

	public static void main(String[] args) {
		// Cr�er un objet de classe InitialContext qui nous servira pour trouver
		// l'EJB distant
		InitialContext ctx = null;
		CalculetteRemote proxy = null;
		try {
			// cr�er le contexte
			ctx = new InitialContext();

			// Chercher le bean � l'aide de la m�thode lookup():
			// Cette m�thode retourne une r�f�rence sur un proxy
			// Proxy: Objet local qui nous l'impression
			// que le bean distant se trouve dans mon application (on travaille avec
			// le proxy comme si c'�tait le bean)
			// Le r�le du proxy: cacher les d�tails des �change des donn�es
			// entre notre application et le bean distant.
			// Le retour de lookup() est un objet non typ�:
			Object obj = ctx.lookup(com.etudiant.formation.CalculetteRemote.class.getName());

			// Apres avoir obtenu la r�f�rence retourn�e par lookup(), je dois utiliser
			// la m�thode narrow() pour reconstituer l'objet.
			// La reconstitution retourne un objet non typ�
			// J'utilise un transtypage pour le transformer en un objet de type
			// (CalculetteRemote)
			proxy = (CalculetteRemote) PortableRemoteObject.narrow(obj, CalculetteRemote.class);
		} catch (Exception ex) {
			System.out
					.println("Type exception: " + ex.getClass().getName() + "\nMessage exception: " + ex.getMessage());
			System.exit(99);
		}

		// D�sormais, je peux utiliser le proxy comme si c'�tait le "bean m�me"
		try {
			
			// R�cup�rer le r�sultat de l'ex�cution pr�c�dente
			System.out.println("Le dernier r�sultat de l'ex�cution pr�c�dente: " 
					+ proxy.recupererResultatPrecedent());
			String op = "*";
			double d1 = 5;
			double d2 = 2;
			double resultat = proxy.calculer(op, d1, d2);
	
			System.out.println(d1 + " " + op + " " + d2 + " = " + resultat);
	
			op = "/";
			d2 = 0;
			resultat = proxy.calculer(op, d1, d2);
			
			
		} catch (Exception ex) {
			System.out.println("Une exception a �t� lev�e:\n " + ex.getMessage());
		}

		System.out.println("*********** Fin normal ex�cution *************");

	}

}
