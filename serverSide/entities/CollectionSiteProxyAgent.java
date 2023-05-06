package serverSide.entities;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.ServerCom;
import serverSide.interfaces.CollectionSiteInterface;
import serverSide.interfaces.MasterThiefClone;
import serverSide.interfaces.OrdinaryThiefClone;

public class CollectionSiteProxyAgent extends Thread implements MasterThiefClone, OrdinaryThiefClone {
     /**
    *  Communication channel.
    */
    private final ServerCom serverCom;

    private int ordinaryThiefState;

    private int masterThiefState;

    private int ordinaryThiefID;

    private int ordinaryThiefMaxDisplacement;

    private final CollectionSiteInterface collectionSiteInterface;

    public CollectionSiteProxyAgent(ServerCom serverCom, CollectionSiteInterface collectionSiteInterface) {
        this.serverCom = serverCom;
        this.collectionSiteInterface = collectionSiteInterface;
    }

    @Override
    public int getOrdinaryThiefID() {
        return ordinaryThiefID;
    }

    @Override
    public int getOrdinaryThiefMaxDisplacement() {
        return ordinaryThiefMaxDisplacement;
    }

    public int getOrdinaryThiefState() {
        return this.ordinaryThiefState;
    }

    public int getMasterThiefState() {
        return this.masterThiefState;
    }

    public char getOrdinaryThiefSituation() {
        return (ordinaryThiefState == CONCENTRATION_SITE || ordinaryThiefState == COLLECTION_SITE) ? 'W' : 'P';
    }

    public void setOrdinaryThiefID(int id) {
        this.ordinaryThiefID = id;
    }

    @Override
    public void setOrdinaryThiefState(int state) {
        this.ordinaryThiefState = state;
    }

    public void setOrdinaryThiefMaxDisplacement(int maxDisplacement) {
        this.ordinaryThiefMaxDisplacement = maxDisplacement;
    }

    @Override
    public void setMasterThiefState(int state) {
        this.masterThiefState = state;
    }
    
    @Override
    public void run() {
        Message inMessage = null, outMessage = null;
        inMessage = (Message) serverCom.readObject();
        try {
            outMessage = collectionSiteInterface.processAndReply(inMessage);
        } catch (MessageException e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMessageVal().toString());
            System.exit (1);
        }
        serverCom.writeObject(outMessage);
        serverCom.close();
    }
}
