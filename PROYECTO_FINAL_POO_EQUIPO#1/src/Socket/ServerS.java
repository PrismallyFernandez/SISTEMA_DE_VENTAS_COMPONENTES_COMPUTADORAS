package Socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerS extends Thread
{
    
  @SuppressWarnings("resource")
public static void main (String args[])
  {
    ServerSocket sfd = null;
    try
    {
      sfd = new ServerSocket(7000);
    }
    catch (IOException ioe)
    {
      System.out.println("Comunicaci�n rechazada."+ioe);
      System.exit(1);
    }

    while (true)
    {
      try
      {
        Socket nsfd = sfd.accept();
        System.out.println("Conexion aceptada de: "+nsfd.getInetAddress());
        DataInputStream oss = new DataInputStream(nsfd.getInputStream());
        DataOutputStream escritor=new DataOutputStream(new FileOutputStream(new File("empresa_respaldo.dat")));
        
        //DataInputStream FlujoLectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
       // String linea = FlujoLectura.readUTF();
        //String text = "";
        /*
        if (!linea.equals(""))
 	     {
          text = text+" "+ linea;
          System.out.println(text);
  	   }*/
        
        int unByte;
        try {
        	
        	while((unByte=oss.read()) != -1)
        	escritor.write(unByte);
        	oss.close();
        	escritor.close();
        }catch(IOException e) {
        	
        	e.printStackTrace();
        	System.out.println("------------------------------------");
        }
        }catch(IOException ioe)
      {
        System.out.println("Error: "+ioe);
      }
    }
  }
}

