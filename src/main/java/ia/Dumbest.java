package main.java.ia;

import java.awt.Point;
import java.util.Random;

import main.java.data.Action;
import main.java.data.Data;
import main.java.data.Hand;
import main.java.data.Tile;

/**
 * 
 * @author Ulysse
 *	
 *	Ce joueur automatique est bidon:
 *	il ne fait que poser des tuiles au pif sur la carte.
 *	Il ne tente jamais de faire un voyage, ne sais pas si il a atteint un objectif
 *	Il ne fait que des coups simples
 *
 */
public class Dumbest extends PlayerAutomaton {
	
	Dumbest (){
		this.name = "Dumbest";
	}
	
	
	public Action makeChoice(Hand myHand, Data currentconfig) {
		Action choix ;
		Random rand = new Random();
		Point p;
		Tile t;
		int i,j, k;
		
		do{
			// On choisit un emplacement au hasard
			i = rand.nextInt(currentconfig.getWidth());
			j = rand.nextInt(currentconfig.getHeight());
			p = new Point(i,j);
			
			// On choisit une tuile parmi les 5 de notre main
			k = rand.nextInt(myHand.size());
			t = myHand.get(k);
		}while( !currentconfig.isAcceptableTilePlacement(i, j, t));
		
		choix = Action.newBuildSimpleAction(p, t);
		
		return choix;
	}

}
