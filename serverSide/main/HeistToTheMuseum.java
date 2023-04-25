package serverSide.main;

import serverSide.entities.*;
import serverSide.interfaces.AssaultPartyInterface;
import serverSide.interfaces.CollectionSiteInterface;
import serverSide.interfaces.ConcentrationSiteInterface;
import serverSide.interfaces.MuseumInterface;
import serverSide.interfaces.SharedRegionInterface;
import serverSide.sharedRegions.*;
import utils.Constants;
import utils.Room;
import commInfra.*;
import java.net.*;
import java.util.Random;

/**
 *    Server side of the Heist To The Museum.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class HeistToTheMuseum
{
  /**
   *  Flag signaling the service is active.
   */
   public static boolean waitConnection;

  /**
   *  Main method.
   *
   *    @param args runtime arguments
   *        args[0] - port number for listening to service requests
   */
   public static void main (String [] args)
   {
      GeneralRepository generalRepository;
      AssaultParty[] assaultParties;
      CollectionSite collectionSite;
      ConcentrationSite concentrationSite;
      Museum museum;
      SharedRegionInterface sharedRegionInterface;
      ServerCom serverCom, serverComi;                                         // communication channels
      int portNumber = -1;                                             // port number for listening to service requests

      if (args.length != 1)
         {  System.out.println("Wrong number of parameters!");
           System.exit(1);
         }
      try
      { portNumber = Integer.parseInt (args[0]);
      }
      catch (NumberFormatException e)
      { System.out.println(args[0] + " is not a number!");
        System.exit(1);
      }
      if ((portNumber < 4000) || (portNumber >= 65536))
         { System.out.println(args[0] + " is not a valid port number!");
           System.exit(1);
         }

      /* service is established */

      // Shared regions initialization
      Random random = new Random(System.currentTimeMillis());
      generalRepository = new GeneralRepository();
      assaultParties = new AssaultParty[Constants.ASSAULT_PARTIES_NUMBER];
      for(int i = 0; i < assaultParties.length; i++) {
        assaultParties[i] = new AssaultParty(i, generalRepository);
      }
      Room[] rooms = new Room[Constants.NUM_ROOMS];
      for(int i = 0; i < rooms.length; i++) {
        int distance = Constants.MIN_ROOM_DISTANCE + random.nextInt(Constants.MAX_ROOM_DISTANCE - Constants.MIN_ROOM_DISTANCE + 1);
        int paintings = Constants.MIN_PAINTINGS + random.nextInt(Constants.MAX_PAINTINGS - Constants.MIN_PAINTINGS + 1);
        rooms[i] = new Room(i, distance, paintings);
      }
      museum = new Museum(generalRepository, assaultParties, rooms);
      collectionSite = new CollectionSite(generalRepository, assaultParties, museum);
      concentrationSite = new ConcentrationSite(generalRepository, assaultParties, collectionSite);

      // Shared region interfaces initialization
      AssaultPartyInterface[] assaultPartyInterfaces = new AssaultPartyInterface[Constants.ASSAULT_PARTIES_NUMBER];
      for (int i = 0; i < assaultPartyInterfaces.length; i++) {
        assaultPartyInterfaces[i] = new AssaultPartyInterface(assaultParties[i]);
      }
      MuseumInterface museumInterface = new MuseumInterface(museum);
      CollectionSiteInterface collectionSiteInterface = new CollectionSiteInterface(collectionSite);
      ConcentrationSiteInterface concentrationSiteInterface = new ConcentrationSiteInterface(concentrationSite);
      sharedRegionInterface = new SharedRegionInterface(assaultPartyInterfaces, collectionSiteInterface, concentrationSiteInterface, museumInterface);

      serverCom = new ServerCom(portNumber);                                         // listening channel at the public port is established
      serverCom.start();
      System.out.println("Service is established!");
      System.out.println("Server is listening for service requests.");

     /* service request processing */

      ServerProxyAgent proxy;                                // service provider agent

      waitConnection = true;
      while (waitConnection)
      { try
        { serverComi = serverCom.accept();                                    // enter listening procedure
          proxy = new ServerProxyAgent(serverComi, sharedRegionInterface);    // start a service provider agent to address
          proxy.start();                                                      //   the request of service
        }
        catch (SocketTimeoutException ignored) {}
      }
      serverCom.end();                                                   // operations termination
      System.out.println("Server was shutdown.");
   }
}
