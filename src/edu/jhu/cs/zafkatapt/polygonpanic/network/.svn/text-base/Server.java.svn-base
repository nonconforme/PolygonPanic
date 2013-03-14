/**
 * @author Zafkataft: Roger Xu, Mike Tango, Mike Zaccardo, Sam Clawson-Simmons
 * 
 * Server. Listens to client and sends and receives packets
 */

package edu.jhu.cs.zafkatapt.polygonpanic.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread
{
	/**
	 * Server IP
	 */
	public static final String SERVERIP = "192.168.1.123";
	/**
	 * Server port
	 */
	public static final int SERVERPORT = 4444;
	/**
	 * Devices that are hosting games
	 */
	// private ArrayList<String> hosts;

	private Socket clientSocket;

	private int nextAvailablePort;

	/**
	 * Constructor. Initializes UDP sockets, and perhaps any future required
	 * data: Sequence number, RTT, etc
	 */
	public Server(Socket clientsoc, String ip)
	{
		clientSocket = clientsoc;
		nextAvailablePort = 4445;
		start();
	}

	/**
	 * Contains the main loop. Starts listening for client
	 */
	public void run()
	{
		System.out.println("New Communication Thread Started");
		try
		{
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null)
			{
				System.out.println("Server: " + inputLine);

				if (inputLine.equalsIgnoreCase("stop"))
				{
					break;
				} else if (inputLine.equalsIgnoreCase("request hosts"))
				{
					for (int i = 0; i < Global.hosts.size(); i++)
					{
						out.println(Global.hosts.get(i));
					}
					out.println("end hosts");
				} else if (inputLine.substring(0, 4).equalsIgnoreCase("host"))
				{
					out.println(String.valueOf(nextAvailablePort));
					String ip = inputLine.substring(5, inputLine.length());
					Global.hosts.add(String.valueOf(nextAvailablePort) + " "
							+ ip);
					nextAvailablePort++;
				} else if (inputLine.length() >= 7
						&& inputLine.substring(0, 7)
								.equalsIgnoreCase("connect"))
				{
					String s = inputLine.substring(8, inputLine.length());
					if (Global.hosts.contains(s))
					{
						out.println("acknowledgement");
					} else
					{
						out.println("No");
					}
				} else if (inputLine.length() >= 9
						&& inputLine.substring(0, 9).equalsIgnoreCase(
								"stop host"))
				{
					String s = inputLine.substring(10, inputLine.length());
					if (Global.hosts.contains(s))
					{
						Global.hosts.remove(s);
					}
				} else
				{
					out.println(inputLine);
				}
			}
			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e)
		{
			// System.out.println("Problem with Communication Server");
		}

	}

	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;

		try
		{
			serverSocket = new ServerSocket(SERVERPORT);
			System.out.println("Connection Socket Created");
			while (true)
			{
				System.out.println("Waiting for Connection");
				new Server(serverSocket.accept(), serverSocket
						.getLocalSocketAddress().toString());
			}
		} catch (IOException e)
		{
			System.out.println("I/O Exception");
		}

	}

}
