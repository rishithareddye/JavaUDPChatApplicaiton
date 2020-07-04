/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpchat;

/**
 *
 * @author MadhusudhanReddy
 */
import java.io.*;
import java.net.*;

/**
 *
 * @author MadhusudhanReddy
 */
public class Function {
/*
    method used to get the group address based on gid
    */
   
public static String getGroupAddr(int i)
	{
		int gnet=1+i;
		return "224.0.0."+gnet;
		
	}
/*
    method used to get the group port based on gid
    */
   
	public static int getGroupport(int i)
	{
		int gport=8888+i;
		return gport;
		
	}
        /*
        method used to send the msg to server
        */
    public static String UDPclient(String msg) {
        
            String ret="";
        try {
            String serverHostname = new String("127.0.0.1");

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(serverHostname);
          //  System.out.println("Attemping to connect to " + IPAddress
            //        + ") via UDP port 9876");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            //System.out.print("Enter Message: ");

            sendData = msg.getBytes();

            //System.out.println("Sending data to " + sendData.length
              //      + " bytes to server.");
            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket
                    = new DatagramPacket(receiveData, receiveData.length);

            //System.out.println("Waiting for return packet");
            clientSocket.setSoTimeout(10000);

            try {
                clientSocket.receive(receivePacket);
                String modifiedSentence
                        = new String(receivePacket.getData());

                InetAddress returnIPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

              //  System.out.println("From server at: " + returnIPAddress
                //        + ":" + port);
                //System.out.println("Message: " + modifiedSentence);
                ret=modifiedSentence;
            } catch (SocketTimeoutException ste) {
                System.out.println("Timeout Occurred: Packet assumed lost");
            }

            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return ret;
    }
    

}
