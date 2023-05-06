package serverSide.sharedRegions;

import java.util.ArrayDeque;
import java.util.Deque;

import clientSide.entities.MasterThief;
import clientSide.entities.OrdinaryThief;
import clientSide.stubs.AssaultPartyStub;
import clientSide.stubs.CollectionSiteStub;
import clientSide.stubs.GeneralRepositoryStub;
import serverSide.entities.ConcentrationSiteProxyAgent;
import utils.Constants;

/**
 * Concentration Site where ordinary thieves wait for orders.
 */
public class ConcentrationSite {
    /**
     * FIFO with the thieves waiting for instructions.
     */
    private final Deque<ConcentrationSiteProxyAgent> thieves;

    /**
     * Boolean variable that is false until the Master Thief announces the end of the heist.
     */
    private boolean finished;

    /**
     * The General Repository where logging occurs.
     */
    private final GeneralRepositoryStub generalRepository;

    /**
     * The Assault Party shared regions.
     */
    private final AssaultPartyStub[] assaultParties;

    /**
     * The Collection Site shared region.
     */
    private final CollectionSiteStub collectionSite;

    /**
     * Public constructor for the Concentration Site shared region.
     * @param generalRepository the General Repository.
     * @param assaultParties the Assault Parties.
     * @param collectionSite the Collection Site.
     */
    public ConcentrationSite(GeneralRepositoryStub generalRepository, AssaultPartyStub[] assaultParties, CollectionSiteStub collectionSite) {
        this.generalRepository = generalRepository;
        this.assaultParties = assaultParties;
        this.collectionSite = collectionSite;
        thieves = new ArrayDeque<>(Constants.NUM_THIEVES - 1);
        finished = false;
    }

    /**
     * Called by the master thief, when enough ordinary thieves are available and there is still a
     * room with paintings.
     * - Synchronization point between Master Thief and every Ordinary Thief constituting the Assault Party.
     * @param assaultParty the Assault Party identification
     */
    public void prepareAssaultParty(int assaultParty) {
        ConcentrationSiteProxyAgent master = (ConcentrationSiteProxyAgent) Thread.currentThread();
        master.setMasterThiefState(MasterThief.ASSEMBLING_A_GROUP);
        generalRepository.setMasterThiefState(master.getMasterThiefState());
        synchronized (this) {
            while (thieves.size() < Constants.ASSAULT_PARTY_SIZE) {
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            } 
        }
        int room = this.collectionSite.getNextRoom();
        assaultParties[assaultParty].setRoom(room, this.collectionSite.getRoomDistance(room), this.collectionSite.getRoomPaintings(room));
        int[] ordinaryThieves = new int[Constants.ASSAULT_PARTY_SIZE];
        for (int i = 0; i < ordinaryThieves.length; i++) {
            ordinaryThieves[i] = this.thieves.poll().getOrdinaryThiefID();
        }
        assaultParties[assaultParty].setMembers(ordinaryThieves);
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * The Master Thief announces the end of operations
     * and shares the number of paintings acquired in the heist
     */
    public synchronized void sumUpResults() {
        finished = true;
        notifyAll();
        ((ConcentrationSiteProxyAgent) Thread.currentThread()).setMasterThiefState(MasterThief.PRESENTING_THE_REPORT);
        generalRepository.setMasterThiefState(((ConcentrationSiteProxyAgent) Thread.currentThread()).getMasterThiefState());
        generalRepository.printTail(collectionSite.getPaintings());
    }

    /**
     * Called by an ordinary thief to wait for orders
     * @return true if needed, false otherwise
     */
    public synchronized boolean amINeeded() {
        ConcentrationSiteProxyAgent thief = (ConcentrationSiteProxyAgent) Thread.currentThread();
        thief.setOrdinaryThiefState(OrdinaryThief.CONCENTRATION_SITE);
        generalRepository.setOrdinaryThiefState(thief.getOrdinaryThiefID(), thief.getOrdinaryThiefState(), thief.getOrdinaryThiefSituation(), thief.getOrdinaryThiefMaxDisplacement());
        thieves.add(thief);
        this.notifyAll();
        if (finished) {
            return false;
        }
        while (!finished && getAssaultParty(thief) == -1) {
            try {
                this.wait();
            } catch (InterruptedException e) {

            } finally {
                if (finished) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ordinary Thief waits for the Master Thief to dispatch the designed Assault Party
     * @return the Assault Party identification 
     */
    public int prepareExcursion() {
        ConcentrationSiteProxyAgent ordinaryThief = (ConcentrationSiteProxyAgent) Thread.currentThread();
        AssaultPartyStub assaultParty = assaultParties[getAssaultParty(ordinaryThief)];
        synchronized (assaultParty) {
            while (!assaultParty.isInOperation()) {
                try {
                    assaultParty.wait();
                } catch (InterruptedException e) {

                }
            }
        }
        return assaultParty.getID();
    }

    /**
     * Returns the Assault Party the Ordinary Thief is a part of
     * @return the identification of the Assault Party the Ordinary Thief belongs to or -1 if none
     */
    public int getAssaultParty(ConcentrationSiteProxyAgent thief) {
        for (AssaultPartyStub assaultParty: assaultParties) {
            if (assaultParty.isMember(thief.getOrdinaryThiefID())) {
                return assaultParty.getID();
            }
        }
        return -1;
    }
}
