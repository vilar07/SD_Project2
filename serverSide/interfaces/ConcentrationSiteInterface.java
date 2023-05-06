package serverSide.interfaces;

import clientSide.entities.MasterThief;
import clientSide.entities.OrdinaryThief;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.ConcentrationSiteProxyAgent;
import serverSide.sharedRegions.ConcentrationSite;
import utils.Constants;

/**
 * Concentration Site where ordinary thieves wait for orders.
 */
public class ConcentrationSiteInterface {
    private final ConcentrationSite concentrationSite;

    public ConcentrationSiteInterface(ConcentrationSite concentrationSite) {
        this.concentrationSite = concentrationSite;
    }

    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;
        switch (inMessage.getMsgType()) {
            case MessageType.PREPARE_ASSAULT_PARTY:
                if (inMessage.getMasterThiefState() != MasterThief.DECIDING_WHAT_TO_DO) {
                    throw new MessageException("Invalid Master Thief state - should be DECIDING_WHAT_TO_DO!", inMessage);
                } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                    throw new MessageException("Invalid Assault Party identification!", inMessage);
                }
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                concentrationSite.prepareAssaultParty(inMessage.getAssaultParty());
                outMessage = new Message(MessageType.PREPARE_ASSAULT_PARTY_DONE, ((ConcentrationSiteProxyAgent) Thread.currentThread()).getMasterThiefState());
                break;
            case MessageType.SUM_UP_RESULTS:
                if (inMessage.getMasterThiefState() != MasterThief.DECIDING_WHAT_TO_DO) {
                    throw new MessageException("Invalid Master Thief state - should be DECIDING_WHAT_TO_DO!", inMessage);
                }
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                concentrationSite.sumUpResults();
                outMessage = new Message(MessageType.SUM_UP_RESULTS_DONE, ((ConcentrationSiteProxyAgent) Thread.currentThread()).getMasterThiefState());
                break;
            case MessageType.AM_I_NEEDED:
                if (inMessage.getOrdinaryThiefState() != OrdinaryThief.CONCENTRATION_SITE && inMessage.getOrdinaryThiefState() != OrdinaryThief.COLLECTION_SITE) {
                    throw new MessageException("Invalid Ordinary Thief state - should be CONCENTRATION_SITE or COLLECTION_SITE!", inMessage);
                } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                    throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
                } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                    throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
                }
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setOrdinaryThiefMaxDisplacement(inMessage.getOrdinaryThiefMD());
                boolean needed = concentrationSite.amINeeded();
                outMessage = new Message(MessageType.AM_I_NEEDED_DONE, inMessage.getOrdinaryThiefID(), ((ConcentrationSiteProxyAgent) Thread.currentThread()).getOrdinaryThiefState(), 
                        inMessage.getOrdinaryThiefMD(), needed);
                break;
            case MessageType.PREPARE_EXCURSION:
                if (inMessage.getOrdinaryThiefState() != OrdinaryThief.CONCENTRATION_SITE) {
                    throw new MessageException("Invalid Ordinary Thief state - should be CONCENTRATION_SITE!", inMessage);
                } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                    throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
                } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                    throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
                }
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
                ((ConcentrationSiteProxyAgent) Thread.currentThread()).setOrdinaryThiefMaxDisplacement(inMessage.getOrdinaryThiefMD());
                int assaultParty = concentrationSite.prepareExcursion();
                outMessage = new Message(MessageType.PREPARE_EXCURSION_DONE, inMessage.getOrdinaryThiefID(), inMessage.getOrdinaryThiefState(),
                        inMessage.getOrdinaryThiefMD(), assaultParty);
                break;
            case MessageType.SHUTDOWN:
                concentrationSite.shutdown();
                outMessage = new Message(MessageType.SHUTDOWN_DONE);
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }
        return outMessage;
    }
}
