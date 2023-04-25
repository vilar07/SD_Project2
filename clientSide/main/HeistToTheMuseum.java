package clientSide.main;

import clientSide.stubs.AssaultPartyStub;
import clientSide.stubs.CollectionSiteStub;
import clientSide.stubs.ConcentrationSiteStub;
import clientSide.stubs.MuseumStub;
import utils.Constants;

import java.util.Random;

import clientSide.entities.MasterThief;
import clientSide.entities.OrdinaryThief;

/**
 * Concurrent version of the Heist To The Museum.
 * Runs on a single computer.
 */
public class HeistToTheMuseum
{   
    /**
     * Main method to start the Heist To The Museum.
     * @param args not used.
     */
    public static void main(String[] args) {
        /* getting problem runtime parameters */
        int portNumber = 0;
        if (args.length != 1)
        { System.out.println("Wrong number of parameters!");
            System.exit(1);
        }
        try
        { portNumber = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        { System.out.println(args[1] + " is not a number!");
            System.exit(1);
        }
        if ((portNumber < 4000) || (portNumber >= 65536))
        { System.out.println(args[1] + " is not a valid port number!");
            System.exit(1);
        }

        Random random = new Random(System.currentTimeMillis());
        
        AssaultPartyStub[] assaultParties = new AssaultPartyStub[Constants.ASSAULT_PARTIES_NUMBER];
        for(int i = 0; i < assaultParties.length; i++) {
            assaultParties[i] = new AssaultPartyStub(portNumber);
        }
        MuseumStub museum = new MuseumStub(portNumber);

        CollectionSiteStub collectionSite = new CollectionSiteStub(portNumber);

        ConcentrationSiteStub concentrationSite = new ConcentrationSiteStub(portNumber);

        MasterThief masterThief = new MasterThief(collectionSite, concentrationSite, assaultParties);
        OrdinaryThief ordinaryThieves[] = new OrdinaryThief[Constants.NUM_THIEVES - 1];
        for(int i = 0; i < ordinaryThieves.length; i++) {
            ordinaryThieves[i] = new OrdinaryThief(i, museum, collectionSite, concentrationSite, assaultParties, 
                    random.nextInt(Constants.MAX_THIEF_DISPLACEMENT - Constants.MIN_THIEF_DISPLACEMENT + 1) + Constants.MIN_THIEF_DISPLACEMENT
            );
        }

        masterThief.start();
        for(OrdinaryThief ot: ordinaryThieves) {
            ot.start();
        }

        try {
            masterThief.join();
            for(OrdinaryThief ot: ordinaryThieves) {
                ot.join();
            }
        } catch (InterruptedException ignored) {}
        collectionSite.shutdown();
    }
}
