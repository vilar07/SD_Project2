package clientSide.stubs;

import clientSide.entities.OrdinaryThief;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import utils.Constants;

/**
 * The Museum has rooms inside it. That rooms have paintings that can be stolen by the Ordinary Thieves of the Assault Party
 */
public class MuseumStub {
    private final String hostName;
    private final int portNumber;

    public MuseumStub(int portNumber) {
        this.portNumber = portNumber;
        this.hostName = "localhost";
    }

    public MuseumStub(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    /**
     * Roll a canvas.
     * @param id the room identification.
     * @return true if the thief rolls a canvas, false if the room was already empty (There were no more paintings in the room).
     */
    public void rollACanvas(int id) {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.ROLL_A_CANVAS, ((OrdinaryThief) Thread.currentThread()).getID(),
                ((OrdinaryThief) Thread.currentThread()).getOrdinaryThiefState(),
                ((OrdinaryThief) Thread.currentThread()).getMaxDisplacement(),
                id);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.ROLL_A_CANVAS_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefState() != OrdinaryThief.AT_A_ROOM) {
            System.out.println("Invalid Ordinary Thief state!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getOrdinaryThiefID() != ((OrdinaryThief) Thread.currentThread()).getID()) {
            System.out.println("Invalid Ordinary Thief ID!");
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
    }

    public void shutdown() {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 1000);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.SHUTDOWN);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.SHUTDOWN_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
    }

    public int getRoomDistance(int room) {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.GET_ROOM_DISTANCE_MUSEUM, room);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.GET_ROOM_DISTANCE_MUSEUM_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getDistance() < Constants.MIN_ROOM_DISTANCE || inMessage.getDistance() > Constants.MAX_ROOM_DISTANCE) {
            System.out.println("Invalid room distance!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
        return inMessage.getDistance();
    }

    public int getRoomPaintings(int room) {
        ClientCom com;
        Message outMessage, inMessage;
        com = new ClientCom(hostName, portNumber);
        while (!com.open()) {
            try {
                Thread.sleep((long) 10);
            } catch (InterruptedException ignored) {}
        }
        outMessage = new Message(MessageType.GET_ROOM_PAINTINGS_MUSEUM, room);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.GET_ROOM_PAINTINGS_MUSEUM_DONE) {
            System.out.println("Invalid message type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        if (inMessage.getDistance() > Constants.MAX_PAINTINGS) {
            System.out.println("Invalid number of paintings!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        com.close();
        return inMessage.getPaintings();
    }
}