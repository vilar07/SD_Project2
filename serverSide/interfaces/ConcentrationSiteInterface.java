package serverSide.interfaces;

import commInfra.Message;
import commInfra.MessageType;
import serverSide.entities.ServerProxyAgent;
import serverSide.sharedRegions.ConcentrationSite;

/**
 * Concentration Site where ordinary thieves wait for orders.
 */
public class ConcentrationSiteInterface {
    private final ConcentrationSite concentrationSite;

    public ConcentrationSiteInterface(ConcentrationSite concentrationSite) {
        this.concentrationSite = concentrationSite;
    }

    public Message processAndReply(Message inMessage) {
        Message outMessage = null;
        switch (inMessage.getMsgType()) {
            case MessageType.PREPARE_ASSAULT_PARTY:
            ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
            concentrationSite.prepareAssaultParty(inMessage.getAssaultParty());
            outMessage = new Message(MessageType.PREPARE_ASSAULT_PARTY_DONE, ((ServerProxyAgent) Thread.currentThread()).getMasterThiefState());
            break;
            case MessageType.SUM_UP_RESULTS:
            ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
            concentrationSite.sumUpResults();
            outMessage = new Message(MessageType.SUM_UP_RESULTS_DONE, ((ServerProxyAgent) Thread.currentThread()).getMasterThiefState());
            break;
            case MessageType.AM_I_NEEDED:
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefMaxDisplacement(inMessage.getOrdinaryThiefMD());
            outMessage = new Message(MessageType.AM_I_NEEDED_DONE, inMessage.getOrdinaryThiefID(), ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefState(), 
                                            ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefMaxDisplacement(), concentrationSite.amINeeded());
            break;
            case MessageType.PREPARE_EXCURSION:
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
            ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefMaxDisplacement(inMessage.getOrdinaryThiefMD());
            outMessage = new Message(MessageType.AM_I_NEEDED_DONE, inMessage.getOrdinaryThiefID(), ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefState(), 
                                            ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefMaxDisplacement(), concentrationSite.prepareExcursion());
            break;
        }
        return outMessage;
    }
}
