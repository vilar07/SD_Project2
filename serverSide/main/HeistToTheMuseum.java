package serverSide.main;

import serverSide.sharedRegions.AssaultParty;
import serverSide.sharedRegions.CollectionSite;
import serverSide.sharedRegions.ConcentrationSite;
import serverSide.sharedRegions.GeneralRepository;
import serverSide.sharedRegions.Museum;
import serverSide.utils.Constants;

import java.util.Random;

import serverSide.entities.MasterThief;
import serverSide.entities.OrdinaryThief;
import serverSide.interfaces.AssaultPartyInterface;
import serverSide.interfaces.CollectionSiteInterface;
import serverSide.interfaces.ConcentrationSiteInterface;
import serverSide.interfaces.GeneralRepositoryInterface;
import serverSide.interfaces.MuseumInterface;
import serverSide.utils.Room;

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
        Random random = new Random(System.currentTimeMillis());

        GeneralRepository repository = new GeneralRepository();
        
        AssaultParty[] assaultParties = new AssaultParty[Constants.ASSAULT_PARTIES_NUMBER];
        for(int i = 0; i < assaultParties.length; i++) {
            assaultParties[i] = new AssaultParty(i, repository);
        }

        Room[] rooms = new Room[Constants.NUM_ROOMS];
        for(int i = 0; i < rooms.length; i++) {
            int distance = Constants.MIN_ROOM_DISTANCE + random.nextInt(Constants.MAX_ROOM_DISTANCE - Constants.MIN_ROOM_DISTANCE + 1);
            int paintings = Constants.MIN_PAINTINGS + random.nextInt(Constants.MAX_PAINTINGS - Constants.MIN_PAINTINGS + 1);
            rooms[i] = new Room(i, distance, paintings);
        }
        Museum museum = new Museum(repository, assaultParties, rooms);

        CollectionSite collectionSite = new CollectionSite(repository, assaultParties, museum);

        ConcentrationSite concentrationSite = new ConcentrationSite(repository, assaultParties, collectionSite);

        MasterThief masterThief = new MasterThief((CollectionSiteInterface) collectionSite, (ConcentrationSiteInterface) concentrationSite, (AssaultPartyInterface[]) assaultParties, 
                                                    (GeneralRepositoryInterface) repository
        );
        OrdinaryThief ordinaryThieves[] = new OrdinaryThief[Constants.NUM_THIEVES - 1];
        for(int i = 0; i < ordinaryThieves.length; i++) {
            ordinaryThieves[i] = new OrdinaryThief(i, (MuseumInterface) museum, (CollectionSiteInterface) collectionSite, (ConcentrationSiteInterface) concentrationSite, 
                                                    (AssaultPartyInterface[]) assaultParties, (GeneralRepositoryInterface) repository, 
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
        } catch (InterruptedException e) {

        }
    }
}
