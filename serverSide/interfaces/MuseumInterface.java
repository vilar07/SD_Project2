package serverSide.interfaces;

import clientSide.entities.OrdinaryThief;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.MuseumProxyAgent;
import serverSide.sharedRegions.Museum;
import utils.Constants;

/**
 * The Museum has rooms inside of it. That rooms have paintings that can be stolen by the OrdinaryThiefs of the AssaultParty
 */
public class MuseumInterface {
    private final Museum museum;

    public MuseumInterface(Museum museum) {
        this.museum = museum;
    }
    
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;
        switch (inMessage.getMsgType()) {
            case MessageType.ROLL_A_CANVAS:
                if (inMessage.getOrdinaryThiefState() != OrdinaryThief.CRAWLING_INWARDS) {
                    throw new MessageException("Invalid Ordinary Thief state - should be CRAWLING_INWARDS!", inMessage);
                } else if (inMessage.getAssaultParty() < 0 || inMessage.getAssaultParty() >= Constants.ASSAULT_PARTIES_NUMBER) {
                    throw new MessageException("Invalid Assault Party identification!", inMessage);
                } else if (inMessage.getOrdinaryThiefID() < 0 || inMessage.getOrdinaryThiefID() >= Constants.NUM_THIEVES) {
                    throw new MessageException("Invalid Ordinary Thief identification!", inMessage);
                } else if (inMessage.getOrdinaryThiefMD() < Constants.MIN_THIEF_DISPLACEMENT || inMessage.getOrdinaryThiefMD() > Constants.MAX_THIEF_DISPLACEMENT) {
                    throw new MessageException("Invalid Ordinary Thief maximum displacement!", inMessage);
                }
                ((MuseumProxyAgent) Thread.currentThread()).setOrdinaryThiefID(inMessage.getOrdinaryThiefID());
                ((MuseumProxyAgent) Thread.currentThread()).setOrdinaryThiefState(inMessage.getOrdinaryThiefState());
                ((MuseumProxyAgent) Thread.currentThread()).setOrdinaryThiefMaxDisplacement(inMessage.getOrdinaryThiefMD());
                museum.rollACanvas(inMessage.getAssaultParty());
                outMessage = new Message(MessageType.ROLL_A_CANVAS_DONE, inMessage.getOrdinaryThiefID(), ((MuseumProxyAgent) Thread.currentThread()).getOrdinaryThiefState(),
                        ((MuseumProxyAgent) Thread.currentThread()).getOrdinaryThiefMaxDisplacement());
                break;
            case MessageType.GET_ROOM_DISTANCE_MUSEUM:
                if (inMessage.getRoom() < 0 || inMessage.getRoom() > Constants.NUM_ROOMS) {
                    throw new MessageException("Invalid room identification in GET_ROOM_DISTANCE_MUSEUM!", inMessage);
                }
                outMessage = new Message(MessageType.GET_ROOM_DISTANCE_MUSEUM_DONE, museum.getRoomDistance(inMessage.getRoom()));
                break;
            case MessageType.GET_ROOM_PAINTINGS_MUSEUM:
                if (inMessage.getRoom() < 0 || inMessage.getRoom() > Constants.NUM_ROOMS) {
                    throw new MessageException("Invalid room identification in GET_ROOM_DISTANCE_MUSEUM!", inMessage);
                }
                outMessage = new Message(MessageType.GET_ROOM_PAINTINGS_MUSEUM_DONE, museum.getRoomPaintings(inMessage.getRoom()));
                break;
        }
        return outMessage;
    }
}
