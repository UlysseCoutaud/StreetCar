package main.java.player;

import java.awt.Color;
import java.awt.Point;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import main.java.data.Data;
import main.java.data.Tile;
import main.java.game.ExceptionFullParty;
import main.java.game.ExceptionHostAlreadyExists;
import main.java.game.ExceptionUnknownBoardName;
import main.java.game.ExceptionUsedPlayerColor;
import main.java.game.ExceptionUsedPlayerName;
import main.java.game.Game;
import main.java.game.GameInterface;
import main.java.util.NetworkTools;
import test.java.player.TestIHM;

@SuppressWarnings("serial")
public class PlayerIHM extends PlayerAbstract
{
	
	TestIHM ihm;
	
// --------------------------------------------
// Builder:
// --------------------------------------------

	/**=========================================================================
	 * @return Creates a new Game.  If gameCreation==true the application is launched
	 * @param gameCreation equals true if the player shelters a new game, and false if the player join an existing game
	 * @param applicationIP equals null if the player shelters the game
	 * @throws NotBoundException 		: The app IP or game name is wrong	(caught by IHM)
	 * @throws RemoteException 			: The web host is offline			(caught by IHM)
	 * @throws ExceptionFullParty											(caught by IHM)
	 * @throws ExceptionUnknownBoardName:									(caught by IHM)
	 * @throws ExceptionUsedPlayerColor 
	 * @throws ExceptionUsedPlayerName
	 * @throws ExceptionHostAlreadyExists
 	 ===========================================================================*/
	public static PlayerIHM launchPlayer(String playerName, String gameName, String boardName, int nbrBuildingInLine, Color playerColor, boolean gameCreation, String applicationIP, TestIHM ihm) throws RemoteException, NotBoundException, ExceptionFullParty, ExceptionUsedPlayerName, ExceptionUsedPlayerColor, ExceptionUnknownBoardName, ExceptionHostAlreadyExists
	{
		String localIP = NetworkTools.firstFreeSocketInfo().IP;
		GameInterface game;

		if (gameCreation)															// App thread creation
		{
			Game gameT	= new Game(gameName, localIP, boardName, nbrBuildingInLine);
			Thread t	= new Thread(gameT);
			t.start();
			game		= Game.getRemoteGame(localIP, gameName);					// Remote application pointer
		}
		else	game	= Game.getRemoteGame(applicationIP, gameName);				// Remote application pointer
		return new PlayerIHM(gameCreation, playerName, playerColor, game, ihm);		// Player Creation
	}
	/**=====================================================================
	 * @return Creates a local player that can be called as a local object
	 * @throws RemoteException 			: The web host is offline			(caught by IHM)
	 * @throws ExceptionFullParty											(caught by IHM)
	 * @throws ExceptionUsedPlayerColor 									(caught by IHM)
	 * @throws ExceptionUsedPlayerName 									    (caught by IHM)
	 =======================================================================*/
	private PlayerIHM(boolean isHost, String playerName, Color playerColor, GameInterface app, TestIHM ihm) throws RemoteException, ExceptionFullParty, ExceptionUsedPlayerName, ExceptionUsedPlayerColor
	{
		super(isHost, playerName, playerColor, app);
		this.ihm = ihm;
	}

// --------------------------------------------
// Public methods: may be called by the remote object
// Must implement "throws RemoteException"
// --------------------------------------------
	public void gameHasChanged(Data data) throws RemoteException
	{
		ihm.refresh(data);
	}
	@Override
	public void dealTile(Tile t) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isHumanPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void dealRouteCard(String[] route) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tileHasBeenPlaced(String playerName, Tile t, Point position)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exchangeTile(String playerName, Tile t, Point p)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void receiveTileFromPlayer(String chosenPlayer, Tile t)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void placeStop(Point p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void revealLine(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void revealRoute(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dealLineCard(int cardNumber) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}