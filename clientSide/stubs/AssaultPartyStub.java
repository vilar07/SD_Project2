package clientSide.stubs;

import clientSide.entities.MasterThief;
import clientSide.entities.OrdinaryThief;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;

/**
 *  Stub to the Assault Party.
 *
 *    It instantiates a remote reference to the Assault Party.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class AssaultPartyStub {
    private String hostName;
    private int portNumber;

    public AssaultPartyStub(int portNumber) {
        this.portNumber = portNumber;
        this.hostName = "localhost";
    }

    public AssaultPartyStub(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    /**
     * Called by the Master Thief to send the Assault Party to the museum
     * After that call, Assault Party can start crawling
     */
    public void sendAssaultParty() {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.SEND_ASSAULT_PARTY, ((MasterThief) Thread.currentThread()).getMasterThiefState());
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.SEND_ASSAULT_PARTY_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getMasterThiefState() != MasterThief.DECIDING_WHAT_TO_DO) {
            System.out.println("Invalid Master Thief state!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
        ((MasterThief) Thread.currentThread()).setState(inMessage.getMasterThiefState());
    }

    /**
     * Called by the Ordinary Thief to crawl in
     * @return false if they have finished the crawling
     */
    public boolean crawlIn() {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.CRAWL_IN, ((OrdinaryThief) Thread.currentThread()).getID(), 
                ((OrdinaryThief) Thread.currentThread()).getOrdinaryThiefState(), ((OrdinaryThief) Thread.currentThread()).getMaxDisplacement());
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.CRAWL_IN_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefID() != ((OrdinaryThief) Thread.currentThread()).getID()) {
            System.out.println("Invalid Ordinary Thief ID!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefState() != OrdinaryThief.CRAWLING_INWARDS) {
            System.out.println("Invalid Ordinary Thief state!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefMD() != ((OrdinaryThief) Thread.currentThread()).getMaxDisplacement()) {
            System.out.println("Invalid Ordinary Thief maximum displacement!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
        ((OrdinaryThief) Thread.currentThread()).setState(inMessage.getOrdinaryThiefState());
        return false;
    }

    /**
     * Called to awake the first member in the line of Assault Party, by the last party member that rolled a canvas,
     * so that the Assault Party can crawl out
     * - Synchronization Point between members of the Assault Party
     */
    public void reverseDirection() {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.REVERSE_DIRECTION, ((OrdinaryThief) Thread.currentThread()).getID(), 
                ((OrdinaryThief) Thread.currentThread()).getOrdinaryThiefState(), ((OrdinaryThief) Thread.currentThread()).getMaxDisplacement());
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.REVERSE_DIRECTION_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
    }

    /**
     * Called by the Ordinary Thief to crawl out
     * @return false if they have finished the crawling
     */
    public boolean crawlOut() {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.CRAWL_OUT, ((OrdinaryThief) Thread.currentThread()).getID(), 
                ((OrdinaryThief) Thread.currentThread()).getOrdinaryThiefState(), ((OrdinaryThief) Thread.currentThread()).getMaxDisplacement());
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.CRAWL_OUT_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefID() != ((OrdinaryThief) Thread.currentThread()).getID()) {
            System.out.println("Invalid Ordinary Thief ID!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefState() != OrdinaryThief.CRAWLING_OUTWARDS) {
            System.out.println("Invalid Ordinary Thief state!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefMD() != ((OrdinaryThief) Thread.currentThread()).getMaxDisplacement()) {
            System.out.println("Invalid Ordinary Thief maximum displacement!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
        ((OrdinaryThief) Thread.currentThread()).setState(inMessage.getOrdinaryThiefState());
        return false;
    }
}
