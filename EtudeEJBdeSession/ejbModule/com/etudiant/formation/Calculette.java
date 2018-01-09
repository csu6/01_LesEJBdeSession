package com.etudiant.formation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

// Bean de session sans �tat (stateless)
/**
 * Session Bean implementation class Calculette
 */
@Stateless
@LocalBean
// Un bean de session sans �tat sera utiliser par des clients diff�rents
// Si un client stocke une valeur dans ce genre de bean, un autre client
// peut la lire.
// Un bean sans �tat est partag� par tous les clients.
// servira des clients diff�rents � tour de r�le.

// A la diff�rence, un bean avec �tat (voir le bean Echo), n'affichera la donn�e stock�e 
// qu'au client qui a d�j� utilis� ce bean.
// Un bean avec �tat a de la m�moire: le m�me bean sera mise � la disposition du m�me client.
// Chaque client a son propre bean avec �tat.

// La seule diff�rence entre un bean avec �tat et un bean sans �tat se situe au niveau de
// l'annotation qui est @Statefull


public class Calculette implements CalculetteRemote/*, CalculetteLocal*/ {
	
	private double resultatPrecedent = Double.NaN;

    /**
     * Default constructor. 
     */
    public Calculette() {
    	ecrire_log("Moment de creation EJB");
    }
    
    public double calculer(String operateur, double df1, double df2) throws Exception {
    	ecrire_log("Operation: " + df1 + " " + operateur + " " + df2);
    	
    	double resultat = Double.NaN;
    	
    	if( operateur.equals("+")) {
    		resultat = df1 + df2;
    	} else if( operateur.equals("-")) {
    		resultat = df1 - df2;
    	} else if( operateur.equals("*")) {
    		resultat = df1 * df2;
    	}else if( operateur.equals("/")) {
    		double res = df1 / df2;
    		if(
    				res == Double.NEGATIVE_INFINITY
    				||
    				res == Double.POSITIVE_INFINITY
    			) {
    			ecrire_log("Division par z�ro");
    			throw new Exception("Division par z�ro");
    		}
    		resultat = res;
    	} else {
			ecrire_log("Operateur inconnu: " + operateur);
    		throw new Exception("Operateur inconnu: " + operateur);
    	}
    	
    	resultatPrecedent = resultat;
    	
    	return resultat;
    }
    
    public double recupererResultatPrecedent() {
    	return resultatPrecedent;
    }
    
//    public static void ecrire_log(String message) {
//    	// Cr�er un bjet de formatage de date de SDF (SimpleDateFormat)
//    	
//    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//    	
//    	String fichier_journalisation = "C:\\journaux\\journalCalculette.log";
//
//    	try {
//        	// ouvrir un stream (un flux) vers le fichier de journalisation
//			FileWriter fw = new FileWriter(fichier_journalisation, true);
//			fw.append(sdf.format(new Date() + ": " + message + System.getProperty("line.separator")));
//			fw.close();
//		} catch (IOException ex) {
//			System.out.println(
//						"\n\n"
//						+ "Une exception a �t� rencontr�e:\n " 
//						+ "Source: Calculette \n" 
//						+ "Message: "
//						+ ex.getMessage()
//						+"\n\n"
//					);
//		}
//    }
    
    private static void ecrire_log(String message)
    {
        //cr�er un objet de formatage de date de type sdf(SimpledateFormat)            
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String fic_journalisation = "C:\\journaux\\journalCalculette.log";
        
        try             
        {
            //ouvrir un stream(un flux) vers le fichier de journalisation
            FileWriter fw = new FileWriter(fic_journalisation , true);
            
            
            fw.append(sdf.format(new Date()) + 
            ": " +
            message + System.getProperty("line.separator"));
            fw.close();
            
        }            
        catch(IOException ex)
        {
            System.out.println("Une Exception a �t� rencontr�e : " +
                    "source : Calculette\nMessage:" + 
                     ex.getMessage());
        }
    
    }
}
