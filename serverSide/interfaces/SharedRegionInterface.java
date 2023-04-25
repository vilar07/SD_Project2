package serverSide.interfaces;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.ServerProxyAgent;
import serverSide.utils.Constants;

public class SharedRegionInterface {
    private final AssaultPartyInterface[] assaultPartyInterfaces;

    private final CollectionSiteInterface collectionSiteInterface;

    private final ConcentrationSiteInterface concentrationSiteInterface;

    private final MuseumInterface museumInterface;

    public SharedRegionInterface(AssaultPartyInterface[] assaultPartyInterfaces,
                                CollectionSiteInterface collectionSiteInterface, ConcentrationSiteInterface concentrationSiteInterface, MuseumInterface museumInterface) {
        this.assaultPartyInterfaces = assaultPartyInterfaces;
        this.collectionSiteInterface = collectionSiteInterface;
        this.concentrationSiteInterface = concentrationSiteInterface;
        this.museumInterface = museumInterface;
    }

    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;
        switch (inMessage.getMsgType()) {
            case MessageType.START_OPERATIONS: 
            if (inMessage.getMasterThiefState() != ServerProxyAgent.PLANNING_THE_HEIST) {
                throw new MessageException("Invalid Master Thief state - should be PLANNING_THE_HEIST!", inMessage);
            }
            break;
            case MessageType.APPRAISE_SIT:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.DECIDING_WHAT_TO_DO) {
                throw new MessageException("Invalid Master Thief state - should be DECIDING_WHAT_TO_DO!", inMessage);
            }
            break;
            case MessageType.GET_NEXT_ASSAULT_PARTY_ID:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.DECIDING_WHAT_TO_DO) {
                throw new MessageException("Invalid Master Thief state - should be DECIDING_WHAT_TO_DO!", inMessage);
            }
            break;
            case MessageType.PREPARE_ASSAULT_PARTY:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.DECIDING_WHAT_TO_DO) {
                throw new MessageException("Invalid Master Thief state - should be DECIDING_WHAT_TO_DO!", inMessage);
            } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                throw new MessageException("Invalid Assault Party identification!", inMessage);
            }
            break;
            case MessageType.SEND_ASSAULT_PARTY:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.ASSEMBLING_A_GROUP) {
                throw new MessageException("Invalid Master Thief state - should be ASSEMBLING_A_GROUP!", inMessage);
            }
            break;
            case MessageType.TAKE_A_REST:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.DECIDING_WHAT_TO_DO) {
                throw new MessageException("Invalid Master Thief state - should be ASSEMBLING_A_GROUP!", inMessage);
            }
            break;
            case MessageType.COLLECT_A_CANVAS:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.WAITING_FOR_ARRIVAL) {
                throw new MessageException("Invalid Master Thief state - should be WAITING_FOR_ARRIVAL!", inMessage);
            }
            break;
            case MessageType.SUM_UP_RESULTS:
            if (inMessage.getMasterThiefState() != ServerProxyAgent.DECIDING_WHAT_TO_DO) {
                throw new MessageException("Invalid Master Thief state - should be DECIDING_WHAT_TO_DO!", inMessage);
            }
            break;
            case MessageType.AM_I_NEEDED:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.CONCENTRATION_SITE) {
                throw new MessageException("Invalid Ordinary Thief state - should be CONCENTRATION_SITE!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            case MessageType.PREPARE_EXCURSION:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.CONCENTRATION_SITE) {
                throw new MessageException("Invalid Ordinary Thief state - should be CONCENTRATION_SITE!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            case MessageType.CRAWL_IN:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.CONCENTRATION_SITE) {
                throw new MessageException("Invalid Ordinary Thief state - should be CONCENTRATION_SITE!", inMessage);
            } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                throw new MessageException("Invalid Assault Party identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            case MessageType.ROLL_A_CANVAS:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.CONCENTRATION_SITE) {
                throw new MessageException("Invalid Ordinary Thief state - should be CONCENTRATION_SITE!", inMessage);
            } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                throw new MessageException("Invalid Assault Party identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            case MessageType.REVERSE_DIRECTION:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.AT_A_ROOM) {
                throw new MessageException("Invalid Ordinary Thief state - should be AT_A_ROOM!", inMessage);
            } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                throw new MessageException("Invalid Assault Party identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            case MessageType.CRAWL_OUT:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.AT_A_ROOM) {
                throw new MessageException("Invalid Ordinary Thief state - should be AT_A_ROOM!", inMessage);
            } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                throw new MessageException("Invalid Assault Party identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            case MessageType.HAND_A_CANVAS:
            if (inMessage.getOrdinaryThiefState() != ServerProxyAgent.CRAWLING_OUTWARDS) {
                throw new MessageException("Invalid Ordinary Thief state - should be CRAWLING_OUTWARDS!", inMessage);
            } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                throw new MessageException("Invalid Assault Party identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
            } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
            }
            break;
            default:
            throw new MessageException("Invalid message type!", inMessage);
        }
        if (inMessage.getMsgType() == MessageType.SEND_ASSAULT_PARTY || inMessage.getMsgType() == MessageType.CRAWL_IN || inMessage.getMsgType() == MessageType.REVERSE_DIRECTION
                || inMessage.getMsgType() == MessageType.CRAWL_OUT) {
            outMessage = assaultPartyInterfaces[inMessage.getAssaultParty()].processAndReply(inMessage);
        } else if (inMessage.getMsgType() == MessageType.ROLL_A_CANVAS) {
            outMessage = museumInterface.processAndReply(inMessage);
        } else if (inMessage.getMsgType() == MessageType.PREPARE_ASSAULT_PARTY || inMessage.getMsgType() == MessageType.SUM_UP_RESULTS
                || inMessage.getMsgType() == MessageType.AM_I_NEEDED || inMessage.getMsgType() == MessageType.PREPARE_EXCURSION) {
            outMessage = concentrationSiteInterface.processAndReply(inMessage);
        } else {
            outMessage = collectionSiteInterface.processAndReply(inMessage);
        }
        return outMessage;
    }
}
