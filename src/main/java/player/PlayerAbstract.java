package main.java.player;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import test.java.engine.TestIHM;
import main.java.game.ExceptionFullParty;
import main.java.game.ExceptionUsedPlayerColor;
import main.java.game.ExceptionUsedPlayerName;
import main.java.game.GameInterface;





/**============================================================
 * Remote IHM
 * URL: rmi://ip:port/gameName/playerName
 * @author kassuskley
 ==============================================================*/



@SuppressWarnings("serial")
public abstract class PlayerAbstract extends UnicastRemoteObject implements PlayerInterface
{
// --------------------------------------------
// Attributes:
// --------------------------------------------
	protected GameInterface	game;
	protected TestIHM		ihm;
	protected String		name;
	protected Color			color;

// --------------------------------------------
// Builder:
// --------------------------------------------
	public PlayerAbstract(String playerName, Color playerColor, GameInterface game, TestIHM ihm) throws RemoteException, ExceptionFullParty, ExceptionUsedPlayerName, ExceptionUsedPlayerColor
	{
		super();

		this.game	= game;												// Init Player
		this.ihm	= ihm;
		this.name	= new String(playerName);
		this.color	= playerColor;
		System.out.println("\n===========================================================");
		System.out.println("Street Car player : playerName  = " + playerName);
		System.out.println("Street Car player : playerColor = " + playerColor);
		System.out.println("Street Car player : ready and logged");
		System.out.println("===========================================================\n");

		this.game.onJoinRequest(playerName, this);									// Log the player to the application
	}

// --------------------------------------------
// Public methods: may be called by the remote object
// Must implement "throws RemoteException"
// --------------------------------------------
	public String 	getName()	throws RemoteException	{return this.name;}
	public Color	getColor()	throws RemoteException	{return this.color;}

// --------------------------------------------
// Local methods:
// --------------------------------------------

}