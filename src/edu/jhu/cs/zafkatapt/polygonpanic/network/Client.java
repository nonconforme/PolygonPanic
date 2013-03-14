/**
 * @author Zafkataft: Roger Xu, Mike Tango, Mike Zaccardo, Sam Clawson-Simmons
 * 
 * Client. Connects to the server and sends/receives packets.
 */

package edu.jhu.cs.zafkatapt.polygonpanic.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Unit;

public class Client
{

	/**
	 * @author rogerxu
	 * 
	 *         A Thread that is started once a player is connected to another
	 *         player. It is responsible for receiving packets
	 */
	private class RecPackets extends Thread
	{
		@SuppressWarnings("unchecked")
		public void run()
		{
			try
			{
				if (isHost())
				{
					Object o;
					while ((o = objIn1.readObject()) != null)
					{
						if (o instanceof ArrayList<?>)
						{
							System.out.println("receive unit");
							ArrayList<Unit> p2 = (ArrayList<Unit>) o;
							p2Units = p2;
						} else if (o instanceof LinkedList<?>)
						{
							System.out.println("receive front");
							LinkedList<Unit> p2 = (LinkedList<Unit>) o;
							p2Front = p2;
						}
					}
				} else
				{
					Object o;
					while ((o = objIn2.readObject()) != null)
					{
						if (o instanceof ArrayList<?>)
						{
							System.out.println("receive unit");
							ArrayList<Unit> p1 = (ArrayList<Unit>) o;
							p1Units = p1;
						} else if (o instanceof LinkedList<?>)
						{
							System.out.println("receive front");
							LinkedList<Unit> p1 = (LinkedList<Unit>) o;
							p1Front = p1;
						}
					}
				}
			} catch (IOException e)
			{
				System.out.println("Failed to receive packets: IOException");
			} catch (ClassNotFoundException e)
			{
				System.out
						.println("Failed to receive packets: ClassNotFoundException");
			}
		}
	}

	/**
	 * @author rogerxu A Thread responsible for sending Unit packets
	 */
	/**
	 * private class SendUnitPackets extends Thread{ public void run(){ try{
	 * if(isHost()){ objOut1.writeObject(p1Units); objOut1.flush();
	 * objOut1.reset(); } else{ objOut2.writeObject(p2Units); objOut2.flush();
	 * objOut2.reset(); } System.out.println("send unit packets");
	 * }catch(InvalidClassException e){
	 * System.out.println("Failed to send packets: InvalidClassException");
	 * }catch(NotSerializableException e){
	 * System.out.println("Failed to send packets: NotSerializableException");
	 * }catch(IOException e){
	 * System.out.println("Failed to send packets: IOException"); }
	 * 
	 * } }
	 */

	/**
	 * @author rogerxu A thread responsible for sending frontline packets
	 */
	/**
	 * private class SendFrontPackets extends Thread{ public void run(){ try{
	 * if(isHost()){ objOut1.writeObject(p1Front); objOut1.flush();
	 * objOut1.reset(); } else{ objOut2.writeObject(p2Front); objOut2.flush();
	 * objOut2.reset(); } System.out.println("Send front packets");
	 * }catch(InvalidClassException e){
	 * System.out.println("Failed to send packets: InvalidClassException");
	 * }catch(NotSerializableException e){
	 * System.out.println("Failed to send packets: NotSerializableException");
	 * }catch(IOException e){
	 * System.out.println("Failed to send packets: IOException"); } } }
	 */

	/**
	 * @author rogerxu A TimerTask responsible for sending Unit packets
	 */
	private class Task1 extends TimerTask
	{
		public void run()
		{
			try
			{
				if (isHost())
				{
					objOut1.writeObject(p1Units);
					objOut1.flush();
					objOut1.reset();
				} else
				{
					objOut2.writeObject(p2Units);
					objOut2.flush();
					objOut2.reset();
				}
				t2.schedule(new Task2(), 500);
			} catch (InvalidClassException e)
			{
				System.out
						.println("Failed to send packets: InvalidClassException");
			} catch (NotSerializableException e)
			{
				System.out
						.println("Failed to send packets: NotSerializableException");
			} catch (IOException e)
			{
				System.out.println("Failed to send packets: IOException");
			}
		}
	}

	/**
	 * @author rogerxu A thread responsible for sending Frontline packets
	 */
	private class Task2 extends TimerTask
	{
		public void run()
		{
			try
			{
				if (isHost())
				{
					objOut1.writeObject(p1Front);
					objOut1.flush();
					objOut1.reset();
				} else
				{
					objOut2.writeObject(p2Front);
					objOut2.flush();
					objOut2.reset();
				}
				t1.schedule(new Task1(), 500);
			} catch (InvalidClassException e)
			{
				System.out
						.println("Failed to send packets: InvalidClassException");
			} catch (NotSerializableException e)
			{
				System.out
						.println("Failed to send packets: NotSerializableException");
			} catch (IOException e)
			{
				System.out.println("Failed to send packets: IOException");
			}
		}
	}

	/**
	 * Server port
	 */
	public static final int SERVERPORT = 4444;
	/**
	 * Server IP
	 */
	public static final String SERVERIP = "192.168.1.123";

	/**
	 * Client's IP
	 */
	private String myIP;

	/**
	 * List of hosts
	 */
	private ArrayList<String> hosts;
	/**
	 * The server socket with the SERVERPORT and SERVERIP
	 */
	private Socket serverSocket;
	/**
	 * An Output stream with the Server
	 */
	private PrintWriter out;
	/**
	 * An Input stream from the server
	 */
	private BufferedReader in;

	/**
	 * The port that is used when a client hosts a game
	 */
	private int hostPort;
	/**
	 * The server socket used to host a game
	 */
	private ServerSocket hostSocket;
	/**
	 * The socket that connects to the host socket
	 */
	private Socket playerSocket;
	/**
	 * Output stream on the host with the player socket
	 */
	private ObjectOutputStream objOut1;
	/**
	 * Input stream on the host with the player socket
	 */
	private ObjectInputStream objIn1;

	/**
	 * The port that the client connects to
	 */
	private int connectPort;
	/**
	 * The IP that the client connects to
	 */
	private String connectIP;
	/**
	 * The socket with the host that the client connects to
	 */
	private Socket connectSocket;
	/**
	 * Output stream on connnector with the host
	 */
	private ObjectOutputStream objOut2;
	/**
	 * Input stream on connector with the host
	 */
	private ObjectInputStream objIn2;

	/**
	 * List of player1 (host) units
	 */
	private List<Unit> p1Units = new ArrayList<Unit>();
	/**
	 * List of player2 units
	 */
	private List<Unit> p2Units = new ArrayList<Unit>();

	/**
	 * List of player1 (host) front lines
	 */
	private Queue<Unit> p1Front = new LinkedList<Unit>();
	/**
	 * List of player2 front lines
	 */
	private Queue<Unit> p2Front = new LinkedList<Unit>();

	// private long lastUnitSent = -1;
	// private long lastFrontSent = -1;

	/**
	 * Timer that is assigned to task1
	 */
	private Timer t1;
	/**
	 * Timer that is assigned to task2
	 */
	private Timer t2;

	/**
	 * Thread for receiving packets
	 */
	private Thread t;

	private Set<NetworkListener> networkListeners = new HashSet<NetworkListener>();

	/**
	 * Constructor. Initializes TCP sockets, Timers, IP, etc.
	 */
	public Client(String s)
	{
		t1 = new Timer();
		t2 = new Timer();
		// myIP = s;
		objOut1 = null;
		objIn1 = null;
		objOut2 = null;
		objIn2 = null;
		connectPort = -1;
		connectSocket = null;

		hostSocket = null;
		hostPort = -1;
		playerSocket = null;

		hosts = new ArrayList<String>();
		System.out.println("Attemping to connect to host " + SERVERIP
				+ " on port " + SERVERPORT);

		try
		{
			serverSocket = new Socket(SERVERIP, SERVERPORT);
			// myIP = serverSocket.getLocalAddress().toString();
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					serverSocket.getInputStream()));
		} catch (IOException e)
		{
			System.out.println("Could not get I/O for " + SERVERIP
					+ " on port " + SERVERPORT);
			System.exit(1);
		}
		myIP = getLocalIpAddress();
		System.out.println("IP Address: " + myIP);
	}

	/**
	 * @return ArrayList<String> Returns a list of hosts
	 */
	public ArrayList<String> getHosts()
	{
		hosts.clear();
		out.println("request hosts");
		while (true)
		{
			String s = "";
			try
			{
				s = in.readLine();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			if (s.equalsIgnoreCase("end hosts"))
			{
				break;
			} else if (!hosts.contains(s))
			{
				hosts.add(s);
			}
		}
		return hosts;
	}

	/**
	 * @param s
	 *            Attempts to connect to the host indicated by String s
	 */
	public void connect(String s)
	{
		int p = Integer.parseInt(s.substring(0, 4));
		connectIP = s.substring(5, s.length());
		out.println("connect " + p + " " + connectIP);
		try
		{
			if (in.readLine().equalsIgnoreCase("acknowledgement"))
			{
				connectPort = p;
				System.out.println("Attemping to connect to host " + connectIP
						+ " on port " + connectPort);

				try
				{
					connectSocket = new Socket(connectIP, connectPort);
					for (NetworkListener listener : networkListeners)
					{
						listener.receiveEvent(NetworkEvent.connectedToHost);
					}

					objOut2 = new ObjectOutputStream(
							connectSocket.getOutputStream());
					objIn2 = new ObjectInputStream(
							connectSocket.getInputStream());

					t = new RecPackets();
					t.start();
					new Task1().run();
					// new RecFrontPackets().start();

				} catch (IOException e)
				{
					System.out.println("I/O Exception");
				}
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method for a host to stop hosting
	 */
	public void stopHost()
	{
		try
		{
			hostSocket.close();
			playerSocket.close();
			hostSocket = null;
			playerSocket = null;
			objOut1.close();
			objIn1.close();
			objOut1 = null;
			objIn1 = null;
			hostPort = -1;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Host a game
	 */
	public void host()
	{
		out.println("host " + myIP);
		try
		{
			hostPort = Integer.parseInt(in.readLine());

			try
			{
				hostSocket = new ServerSocket(hostPort);
				for (NetworkListener listener : networkListeners)
				{
					listener.receiveEvent(NetworkEvent.startHosting);
				}
				System.out.println("Waiting for connection on port " + hostPort
						+ ".....");
				playerSocket = hostSocket.accept();
				System.out.println("Successfully connected to other player!");
				for (NetworkListener listener : networkListeners)
				{
					listener.receiveEvent(NetworkEvent.hostReceivedConnection);
				}

				objOut1 = new ObjectOutputStream(playerSocket.getOutputStream());
				objIn1 = new ObjectInputStream(playerSocket.getInputStream());
				t = new RecPackets();
				t.start();
				new Task1().run();
				// new RecFrontPackets().start();

			} catch (IOException e)
			{
				System.out.println("I/O Exception");
			}
		} catch (Exception e)
		{
		}
	}

	/**
	 * @return String Gets the IP Address
	 */
	public String getLocalIpAddress()
	{
		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress())
					{
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex)
		{
		}
		return null;
	}

	public static void main(String[] args)
	{
		// Client c = new Client("asdf");
		// c.run();
	}

	/**
	 * @param listener
	 *            Adds a listener
	 */
	public void addListener(NetworkListener listener)
	{
		this.networkListeners.add(listener);
	}

	/**
	 * @param listener
	 *            Removes a listener
	 */
	public void removeListener(NetworkListener listener)
	{
		this.networkListeners.remove(listener);
	}

	/**
	 * @param units
	 *            units Sets player1 units
	 */
	public void setP1Units(List<Unit> units)
	{
		p1Units = units;
		/**
		 * if(lastUnitSent == -1){ lastUnitSent = System.currentTimeMillis();
		 * new SendUnitPackets().start(); } else if(System.currentTimeMillis() -
		 * lastUnitSent >=1000){ lastUnitSent = System.currentTimeMillis(); new
		 * SendUnitPackets().start(); }
		 **/
	}

	/**
	 * @return List<Unit> Get Player1 units
	 */
	public List<Unit> getP1Units()
	{
		return p1Units;
	}

	/**
	 * @param units
	 *            Sets player2 units
	 */
	public void setP2Units(List<Unit> units)
	{
		p2Units = units;
		/**
		 * if(lastUnitSent == -1){ lastUnitSent = System.currentTimeMillis();
		 * new SendUnitPackets().start(); } else if(System.currentTimeMillis() -
		 * lastUnitSent >=1000){ lastUnitSent = System.currentTimeMillis(); new
		 * SendUnitPackets().start(); }
		 **/
	}

	/**
	 * @return ArrayList<Unit> Gets player2 units
	 */
	public List<Unit> getP2Units()
	{
		return p2Units;
	}

	/**
	 * @param units
	 *            Sets player1 front lines
	 */
	public void setP1Front(Queue<Unit> units)
	{
		p1Front = units;
		/**
		 * if(lastFrontSent == -1){ lastFrontSent = lastUnitSent - 500; //new
		 * SendFrontPackets().start(); } else if(System.currentTimeMillis() -
		 * lastFrontSent >=1000){ lastFrontSent = System.currentTimeMillis();
		 * new SendFrontPackets().start(); }
		 **/
	}

	/**
	 * @return Queue<Unit> returns player1 front lines
	 */
	public Queue<Unit> getP1Front()
	{
		return p1Front;
	}

	/**
	 * @param units
	 *            Sets player2 front lines
	 */
	public void setP2Front(Queue<Unit> units)
	{
		p2Front = units;
		/**
		 * if(lastFrontSent == -1){ lastFrontSent = lastUnitSent - 500; //new
		 * SendFrontPackets().start(); } else if(System.currentTimeMillis() -
		 * lastFrontSent >=1000){ lastFrontSent = System.currentTimeMillis();
		 * new SendFrontPackets().start(); }
		 **/
	}

	/**
	 * @return LinkedList<Unit> Returns player2 front lines
	 */
	public Queue<Unit> getP2Front()
	{
		return p2Front;
	}

	/**
	 * @return boolean Returns true if the client is a host, false otherwise
	 */
	public boolean isHost()
	{
		return hostSocket != null;
	}

	/**
	 * SHUT DOWN EVERYTHING!
	 */
	public void end()
	{
		try
		{
			t1.cancel();
			t2.cancel();
			serverSocket.close();
			serverSocket = null;
			out.close();
			out = null;
			in.close();
			in = null;

			hostPort = -1;
			hostSocket.close();
			hostSocket = null;
			playerSocket.close();
			playerSocket = null;
			objOut1.close();
			objOut1 = null;
			objIn1.close();
			objIn1 = null;

			connectPort = -1;
			connectIP = null;
			connectSocket.close();
			connectSocket = null;
			objOut2.close();
			objOut2 = null;
			objIn2.close();
			objIn2 = null;
		} catch (IOException e)
		{

		} catch (Exception e)
		{
		}
	}
}
