package serverSide.interfaces;

import commInfra.Message;
import commInfra.MessageType;
import serverSide.entities.ServerProxyAgent;
import serverSide.sharedRegions.AssaultParty;

/**
 * Assault Party is constituted by Ordinary Thieves that are going to attack the museum.
 * Assault Party is shared by the thieves
 */
public class AssaultPartyInterface {
    private final AssaultParty assaultParty;

    public AssaultPartyInterface(AssaultParty assaultParty) {
        this.assaultParty = assaultParty;
    }

    public Message processAndReply(Message inMessage) {
        Message outMessage = null;
        switch (inMessage.getMsgType()) {
            case MessageType.SEND_ASSAULT_PARTY:
            ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
            assaultParty.sendAssaultParty();
            outMessage = new Message(MessageType.SEND_ASSAULT_PARTY_DONE, ((ServerProxyAgent) Thread.currentThread()).getMasterThiefState());
            break;
            case MessageType.CRAWL_IN:
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
            assaultParty.crawlIn();
            outMessage = new Message(MessageType.CRAWL_IN_DONE, inMessage.getOrdinaryThiefID(), ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefState());
            break;
            case MessageType.REVERSE_DIRECTION:
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
            assaultParty.reverseDirection();
            outMessage = new Message(MessageType.REVERSE_DIRECTION_DONE);
            break;
            case MessageType.CRAWL_OUT:
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
            assaultParty.crawlOut();
            outMessage = new Message(MessageType.CRAWL_OUT_DONE, inMessage.getOrdinaryThiefID(), ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefState());
            break;
            default:
            System.out.println("Should never happen!");
            System.exit(1);
        }
        return outMessage;
    }
}
