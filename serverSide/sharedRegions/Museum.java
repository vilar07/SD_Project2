package serverSide.sharedRegions;

import serverSide.entities.ServerProxyAgent;
import serverSide.utils.Room;

/**
 * The Museum has rooms inside of it. Those rooms have paintings that can be stolen 
 * by the Ordinary Thieves of the Assault Party.
 */
public class Museum {
    /**
     * Rooms inside the museum.
     */
    private final Room[] rooms;

    /**
     * The General Repository where logging occurs.
     */
    private final GeneralRepository generalRepository;

    /**
     * The Assault Parties shared region.
     */
    private final AssaultParty[] assaultParties;

    /**
     * Museum constructor, initializes rooms.
     * @param generalRepository the General Repository.
     */
    public Museum(GeneralRepository generalRepository, AssaultParty[] assaultParties, Room[] rooms) {
        this.generalRepository = generalRepository;
        this.assaultParties = assaultParties;
        this.rooms = rooms;
        generalRepository.setInitialRoomStates(this.rooms);
    }

    /**
     * The Ordinary Thief tries to roll a canvas.
     * @param party the party identification.
     * @return true if the thief rolls a canvas, false if the room was already empty.
     */
    public boolean rollACanvas(int party) {
        ServerProxyAgent thief = (ServerProxyAgent) Thread.currentThread();
        thief.setOrdinaryThiefState(ServerProxyAgent.AT_A_ROOM);
        generalRepository.setOrdinaryThiefState(thief.getOrdinaryThiefID(), thief.getOrdinaryThiefState());
        boolean res = false;
        Room room = assaultParties[party].getRoom();
        synchronized (this) {
            res = room.rollACanvas();
            if (res) {
                assaultParties[party].setBusyHands(thief.getOrdinaryThiefID(), res);
                generalRepository.setRoomState(room.getID(), room.getPaintings());
            }
        }
        return res;
    }

    /**
     * Getter for a specific room of the Museum.
     * @param id the room identification.
     * @return the room.
     */
    public Room getRoom(int id) {
        return rooms[id];
    }
}
