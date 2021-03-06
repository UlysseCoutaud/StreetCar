package test.java.player;

import java.awt.Color;
import java.rmi.RemoteException;
import java.util.Scanner;

import main.java.data.Data;
import main.java.player.PlayerIHM;






public class TestIHM 
{
// --------------------------------------------
// Attributs:
// --------------------------------------------
	private DataViewerFrame frame;

// --------------------------------------------
// Builder:
// --------------------------------------------
	public TestIHM()
	{
		Scanner sc = new Scanner(System.in);
		PlayerIHM player = null;
		String str, name, gameName, ip;
		boolean create;

		while (true)
		{
			System.out.println("Please enter:");
			System.out.println("\t- \"J\" to join an existing game");
			System.out.println("\t- \"C\" to create a new game");
			System.out.print("\tchoice : ");str = sc.nextLine();
			if (str.equals("J"))	{create = false;	break;}
			if (str.equals("C"))	{create = true;		break;}
			System.out.println("Unhandeled choice");
		}
		System.out.print("\n\n");
		System.out.print("\t- Player name\t\t :");						name		= sc.next();
		System.out.print("\t- Game name\t\t :");						gameName	= sc.next();
		Color color = askColor(sc);
		if(!create){System.out.print("\t- IP of the application\t:");	ip			= sc.next();}
		else															ip			= null;
String boardName = "newOrleans";	/////// Nom par defaut
int nbrBuildingInLine= 3;	/////// Nom par defaut


		try					{player = PlayerIHM.launchPlayer(name, gameName, boardName, nbrBuildingInLine,  color, create, ip, this);}
		catch (Exception e)	{e.printStackTrace(); System.exit(0);}

		// Game data viewer
		this.frame = new DataViewerFrame();
		try						{this.frame.setGameData(player.getGameData());}
		catch(RemoteException e){e.printStackTrace(); System.exit(0);}
		frame.setVisible(true);

		if (create)
		{
			str = "";
			while (!str.equals("start"))
			{
				System.out.print("\n\n\tEnter \"start\" to start the game: ");
				str = sc.next();
			}
			try					{player.hostStartGame();}
			catch (Exception e)	{e.printStackTrace();}
		}
		sc.close();
	}

// --------------------------------------------
// Local methods:
// --------------------------------------------
	public void refresh(Data data)
	{
		System.out.println("------------------------------------");
		System.out.println("Refresh");
		System.out.println("\t Host\t: "	+ data.getHost());
		System.out.println("\t Round\t: "	+ data.getRound());
		this.frame.setGameData(data);
	}

// --------------------------------------------
// Private Local methods:
// --------------------------------------------
	private Color askColor(Scanner sc)
	{
		String color;
		while(true)
		{
			System.out.print("\t- Color\t\t\t :"); color = sc.next();
			if		(color.equals("red"))	return Color.red;
			else if	(color.equals("green"))	return Color.green;
			else if	(color.equals("blue"))	return Color.blue;
			else if	(color.equals("cyan"))	return Color.cyan;
			else if	(color.equals("gray"))	return Color.gray;
		}
	}
}