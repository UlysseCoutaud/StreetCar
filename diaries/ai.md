## StreetCar - Intelligence artificielle
----------------
Coutaud Ulysse  
Souchet Julie  

### Jour 1 : Lundi 18 mai  
  
  
#### Crit�res de choix de l'intelligence artificielle :  
  - construire la voie optimale d'un terminal � l'autre en passant par les arr�ts obligatoires  
  - saboter les voies des adversaires  
  - bluffer

#### Calcul de la voie optimale (pour l'instant sans les arr�ts):  
On repr�sente le terrain par un graphe, dont les sommets correspondent aux points cardinaux des cases et les ar�tes aux rails sur les tuiles.  

On initialise le graphe avec tous les chemins possibles, c'est-�-dire :  
  - Pour une case vide ou contenant une tuile rempla�able : toutes les ar�tes reliant tous les sommets  
  - Pour une case contenant une tuile *non* rempla�able : les ar�tes repr�sent�es par les rails sur la tuile.

Dans chaque case, toute ar�te re�oit un poids :  
  - Si elle �tait d�j� pr�sente sur le plateau : $0$
  - Si on doit (rem)placer une tuile : "co�t" de la tuile minimale contenant cette ar�te (et les �ventuelles ar�tes d�j� pr�sentes)  
  - Si ce mouvement est interdit : $+\infty$  

On se sert ensuite de l'algorithme A* pour rechercher le chemin le plus court entre les deux cases de d�part du terminal et les deux cases d'arriv�e (les voies n'�tant pas orient�es, on peut choisir n'importe quel d�part ou arriv�e). Comme heuristique, nous avons choisi de calculer la distance en ligne droite entre le point de d�part et le point d'arriv�e. Comme les voies ne sont pas en diagonales, la distance estim�e est toujours inf�rieure � la distance r�elle.  

Le co�t d'une tuile d�pend d'o� on l'obtient :  
  - de la main du joueur : $0$ (on l'a d�j� sous la main)  
  - de la pioche : $$\frac{nombreDeTuilesTotalesDansLaPioche}{nombreDeTuilesDeCeTypeDansLaPioche}$$ allant de $1$ � $+\infty$  (plus une tuile est rare, plus il faudra de chance pour l'obtenir)  
  - de la main d'un adversaire ayant commenc� son voyage inaugural : $1$ (il suffit de se servir)  
  - du plateau : $1$ (il suffit de l'�changer)  
  
Pour l'instant cet algorithme ne prend pas en compte les arr�ts obligatoires sur le trajet de la ligne, mais nous comptons les ajouter demain.