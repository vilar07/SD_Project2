package serverSide.interfaces;

import commInfra.Message;
import serverSide.sharedRegions.Museum;

/**
 * The Museum has rooms inside of it. That rooms have paintings that can be stolen by the OrdinaryThiefs of the AssaultParty
 */
public class MuseumInterface {
    private final Museum museum;

    public MuseumInterface(Museum museum) {
        this.museum = museum;
    }
    
    public Message processAndReply(Message inMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processAndReply'");
    }
    
}
