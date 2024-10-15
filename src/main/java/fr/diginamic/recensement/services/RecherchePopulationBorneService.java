package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exeptions.MinMaxExeption;
import fr.diginamic.recensement.exeptions.NaNExeption;
import fr.diginamic.recensement.exeptions.BorneServiceExeption;
import fr.diginamic.recensement.exeptions.CodeDepartementExeption;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws BorneServiceExeption {

            System.out.println("Quel est le code du département recherché ? ");
            String choix = scanner.nextLine();

            System.out.println("Choississez une population minimum (en milliers d'habitants): ");
            String saisieMin = scanner.nextLine();

            System.out.println("Choississez une population maximum (en milliers d'habitants): ");
            String saisieMax = scanner.nextLine();

			if (!NumberUtils.isDigits(saisieMax)||!NumberUtils.isDigits(saisieMin)){
				throw new NaNExeption("Veuillez saisir un chiffre");
			}

			int min = Integer.parseInt(saisieMin) * 1000;
            int max = Integer.parseInt(saisieMax) * 1000;

			if (min>max){
				throw new MinMaxExeption("Le min ne peut pas être plus grand que le max");
			}
            List<Ville> villes = rec.getVilles();
            for (Ville ville : villes) {
                if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
                    if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
                        System.out.println(ville);
                    }
                } else {
					throw new CodeDepartementExeption("Le département saisi n'existe pas");
				}
            }

    }
}
