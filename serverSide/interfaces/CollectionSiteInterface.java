package serverSide.interfaces;

import commInfra.Message;
import commInfra.MessageType;
import serverSide.entities.ServerProxyAgent;
import serverSide.sharedRegions.CollectionSite;

/**
 * Collection Site where Master Thief plans and paintings are stored
 */
public class CollectionSiteInterface {
    private final CollectionSite collectionSite;

    public CollectionSiteInterface(CollectionSite collectionSite) {
        this.collectionSite = collectionSite;
    }

    public Message processAndReply(Message inMessage) {
        Message outMessage = null;
        switch (inMessage.getMsgType()) {
            case MessageType.START_OPERATIONS:
                ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                collectionSite.startOperations();
                outMessage = new Message(MessageType.START_OPERATIONS_DONE,
                        ((ServerProxyAgent) Thread.currentThread()).getMasterThiefState());
                break;
            case MessageType.APPRAISE_SIT:
                ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                outMessage = new Message(MessageType.APPRAISE_SIT_DONE, collectionSite.appraiseSit());
                break;
            case MessageType.TAKE_A_REST:
                ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                outMessage = new Message(MessageType.TAKE_A_REST_DONE,
                        ((ServerProxyAgent) Thread.currentThread()).getMasterThiefState());
                break;
            case MessageType.COLLECT_A_CANVAS:
                ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                outMessage = new Message(MessageType.COLLECT_A_CANVAS_DONE,
                        ((ServerProxyAgent) Thread.currentThread()).getMasterThiefState());
                break;
            case MessageType.HAND_A_CANVAS:
                ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
                ((ServerProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
                outMessage = new Message(MessageType.HAND_A_CANVAS_DONE,
                        ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefState(),
                        ((ServerProxyAgent) Thread.currentThread()).getOrdinaryThiefID());
                break;
            case MessageType.GET_NEXT_ASSAULT_PARTY_ID:
                ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(inMessage.getMasterThiefState());
                outMessage = new Message(MessageType.GET_NEXT_ASSAULT_PARTY_ID_DONE);
                break;
        }
        return outMessage;
    }
}
