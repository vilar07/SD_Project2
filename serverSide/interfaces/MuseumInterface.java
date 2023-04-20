package serverSide.interfaces;

import commInfra.Message;
import commInfra.MessageType;
import serverSide.entities.ServerProxyAgent;
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
        ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
        ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
        museum.rollACanvas(inMessage.getAssaultParty());
        return new Message(MessageType.ROLL_A_CANVAS_DONE, inMessage.getOrdinaryThiefID(), ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefState());
    }
}
