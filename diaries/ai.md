## StreetCar - Intelligence artificielle

Coutaud Ulysse  
Souchet Julie

-----

### Jour 1 : Lundi 18 mai  
  
  
#### Crit�res de choix de l'intelligence artificielle :  
* construire la voie optimale d'un terminal � l'autre en passant par les arr�ts obligatoires  

* saboter les voies des adversaires  

* bluffer  

* prot�ger sa voie contre les sabotages  

#### Calcul de la voie optimale (pour l'instant sans les arr�ts):  
* On repr�sente le terrain par un graphe, dont les sommets correspondent aux points cardinaux des cases et les ar�tes aux rails sur les tuiles.  
  

* On initialise le graphe avec tous les chemins possibles, c'est-�-dire :  
    + Pour une case vide ou contenant une tuile rempla�able : toutes les ar�tes reliant tous les sommets  
    + Pour une case contenant une tuile *non* rempla�able : les ar�tes repr�sentant les rails sur la tuile.  
  

* Dans chaque case, toute ar�te re�oit un poids :  
    + Si elle �tait d�j� pr�sente sur le plateau : $0$  
    + Si on doit (rem)placer une tuile : co�t de la tuile minimale contenant cette ar�te (et les �ventuelles ar�tes d�j� pr�sentes)    
  *Attention : on joue 2 tuiles / tour*  
    + Si ce mouvement est interdit : $+\infty$  
  *Attention : on peut �changer 2 tuiles simulan�ment pour cr�er une connexion*  
  

* Le co�t d'une tuile d�pend d'où on l'obtient :  
    + de la main du joueur : $0$ (on l'a d�j� sous la main)  
    + de la pioche : $$\frac{nombreDeTuilesTotalesDansLaPioche}{nombreDeTuilesDeCeTypeDansLaPioche}$$     allant de $1$ � $+\infty$  (plus une tuile est rare, plus il faudra de chance pour l'obtenir)  
    + de la main d'un adversaire ayant commenc� son voyage inaugural : $1$ (il suffit de se servir)  
    + du plateau : $1$ (il suffit de l'�changer)  
  

* On se sert ensuite de l'algorithme `A*` pour rechercher le chemin le plus court entre les deux cases de d�part du terminal et les deux cases d'arriv�e (les voies n'�tant pas orient�es, on peut choisir n'importe quel terminal de d�part ou d'arriv�e). Comme heuristique, nous avons choisi de calculer la distance en ligne droite entre le point de d�part et le point d'arriv�e. Comme les voies ne sont pas en diagonales, la distance estim�e est toujours inf�rieure � la distance r�elle.  
  
  
Pour l'instant cet algorithme ne prend pas en compte les arr�ts obligatoires sur le trajet de la ligne, mais nous comptons les ajouter demain.  

-------------------

### Jour 2 : Mardi 19 mai  

#### Calcul de la voie optimale avec les arr�ts :  

* __Algorithme :__  
Afin d'am�liorer l'algorithme ci-dessus, nous allons, pour chaque combinaison des arr�ts obligatoires (1-2-3, 2-1-3, 3-2-1...) calculer les chemins les plus courts, puis les mettre bout � bout. Le r�sultat gagnant sera celui de meilleure qualit�.  

* __Code :__  
Nous avons commenc� � construire la structure du code de l'IA, avec toutes les fonctions n�cessaires.  

-------------------

### Jour 3 : Mercredi 20 mai  

#### Calcul de la voie optimale avec arr�ts :

* __Simplification du mod�le :__
    + les sommets du graphe correspondent aux cases du terrain
    + les arcs aux connexions entre cases
    + les poids des arcs d�pendent des 2 tuiles formant la connexion (� pr�ciser)
  
* __A* :__  
    + Nous allons calculer directement le chemin, pas segment par segment
    + Heuristique : chaque case est num�rot�e avec les distances de Manhattan des diff�rents objectifs (terminus d'arriv�e, arr�ts). (� pr�ciser)
  
* __Code :__  
    + classe Dumbest : pose al�atoire de tuiles sur le terrain (teste les fonctions de pose et pioche)
    + classe Traveler : idem + v�rification du trajet + voyage � vitesse maximale (teste les fonctions de compl�tion d'objectifs + algo de recherche de chemin)  

-------------------

### Jour 4 : Jeudi 21 mai  

#### Calcul de trajet lors du voyage inaugural :  
Impl�ment� dans Traveler avec `A*`, l'algorithme cherche le chemin le plus court d'un terminus � l'autre en passant par les tuiles d�j� pos�es. � faire :

* Tester
* Prendre en compte les arr�ts

#### Calcul du plus court chemin sur un terrain vierge :  
Impl�ment� dans AutomatePlusCourtChemin, calcule le plus court chemin d'une origine � une destination pour une IA seule  
*Test�*  

-------------------
