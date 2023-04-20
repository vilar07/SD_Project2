package serverSide.sharedRegions;

import java.util.ArrayDeque;
import java.util.Deque;

import serverSide.entities.ServerProxyAgent;
import serverSide.utils.Constants;

/**
 * Concentration Site where ordinary thieves wait for orders.
 */
public class ConcentrationSite {
    /**
     * FIFO with the thieves waiting for instructions.
     */
    private final Deque<ServerProxyAgent> thieves;

    /**
     * Boolean variable that is false until the Master Thief announces the end of the heist.
     */
    private boolean finished;

    /**
     * The General Repository where logging occurs.
     */
    private final GeneralRepository generalRepository;

    /**
     * The Assault Party shared regions.
     */
    private final AssaultParty[] assaultParties;

    /**
     * The Collection Site shared region.
     */
    private final CollectionSite collectionSite;

    /**
     * Public constructor for the Concentration Site shared region.
     * @param generalRepository the General Repository.
     * @param assaultParties the Assault Parties.
     * @param collectionSite the Collection Site.
     */
    public ConcentrationSite(GeneralRepository generalRepository, AssaultParty[] assaultParties, CollectionSite collectionSite) {
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
        ServerProxyAgent master = (ServerProxyAgent) Thread.currentThread();
        master.setMasterThiefState(ServerProxyAgent.ASSEMBLING_A_GROUP);
        synchronized (this) {
            while (thieves.size() < Constants.ASSAULT_PARTY_SIZE) {
                try {
                    this.wait();
                } catch (InterruptedException e) {

                }
            } 
        }
        assaultParties[assaultParty].setRoom(this.collectionSite.getNextRoom());
        ServerProxyAgent[] ordinaryThieves = new ServerProxyAgent[Constants.ASSAULT_PARTY_SIZE];
        for (int i = 0; i < ordinaryThieves.length; i++) {
            ordinaryThieves[i] = this.thieves.poll();
        }
        assaultParties[assaultParty].setMembers(ordinaryThieves);
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * The Master Thief announces the end of operations
     * and shares the number of paintings acquired in the heist
     * @param paintings the number of paintings
     */
    public synchronized void sumUpResults(int paintings) {
        finished = true;
        notifyAll();
        ((ServerProxyAgent) Thread.currentThread()).setMasterThiefState(ServerProxyAgent.PRESENTING_THE_REPORT);
        generalRepository.printTail(paintings);
    }

    /**
     * Called by an ordinary thief to wait for orders
     * @return true if needed, false otherwise
     */
    public synchronized boolean amINeeded() {
        ServerProxyAgent thief = (ServerProxyAgent) Thread.currentThread();
        thief.setOrdinaryThiefState(ServerProxyAgent.CONCENTRATION_SITE);
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
        ServerProxyAgent ordinaryThief = (ServerProxyAgent) Thread.currentThread();
        AssaultParty assaultParty = assaultParties[getAssaultParty(ordinaryThief)];
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
    public int getAssaultParty(ServerProxyAgent thief) {
        for (AssaultParty assaultParty: assaultParties) {
            if (assaultParty.isMember(thief)) {
                return assaultParty.getID();
            }
        }
        return -1;
    }
}
