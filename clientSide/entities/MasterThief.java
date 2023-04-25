package clientSide.entities;

import clientSide.stubs.AssaultPartyStub;
import clientSide.stubs.CollectionSiteStub;
import clientSide.stubs.ConcentrationSiteStub;

/**
 * Master Thief, the thief that commands the heist
 */
public class MasterThief extends Thread {
    public static final int PLANNING_THE_HEIST = 1000;
    public static final int DECIDING_WHAT_TO_DO = 2000;
    public static final int ASSEMBLING_A_GROUP = 3000;
    public static final int WAITING_FOR_ARRIVAL = 4000;
    public static final int PRESENTING_THE_REPORT = 5000;

    /**
     * Current state of the Master Thief
     */
    private int state;

    /**
     * Variable holding the Collection Site shared region
     */
    private final CollectionSiteStub collectionSite;

    /**
     * Variable holding the Concentration Site shared region
     */
    private final ConcentrationSiteStub concentrationSite;

    /**
     * Array holding the Assault Parties shared regions
     */
    private final AssaultPartyStub[] assaultParties;

    /**
     * Public constructor for Master Thief
     * Initializes the state as PLANNING_THE_HEIST and her perception that all rooms of the museum
     * are empty
     * @param collectionSite the Collection Site
     * @param concentrationSite the Concentration Site
     * @param assaultParties the Assault Parties
     * @param repository the General Repository
     */
    public MasterThief(CollectionSiteStub collectionSite,
            ConcentrationSiteStub concentrationSite, AssaultPartyStub[] assaultParties) {
        this.collectionSite = collectionSite;
        this.concentrationSite = concentrationSite;
        this.assaultParties = assaultParties;
        setState(PLANNING_THE_HEIST);
    }

    /**
     * Sets the state of the Master Thief and propagates it to the General Repository
     * @param state the updated Master Thief state
     */
    public void setState(int state) {
        this.state = state;
    }

    public int getMasterThiefState() {
        return state;
    }

    /**
     * Lifecycle of the Master Thief
     */
    @Override
    public void run() {
        System.out.println("startOperations");
        collectionSite.startOperations();
        char operation;
        while ((operation = collectionSite.appraiseSit()) != 'E') {
            switch (operation) {
                case 'P':
                int assaultPartyID = collectionSite.getNextAssaultPartyID();
                System.out.println("initiating prepareAssaultParty " + assaultPartyID);
                concentrationSite.prepareAssaultParty(assaultPartyID); 
                System.out.println("finished prepareAssaultParty " + assaultPartyID + "; initiating sendAssaultParty " + assaultPartyID);
                assaultParties[assaultPartyID].sendAssaultParty();
                System.out.println("finished sendAssaultParty " + assaultPartyID);
                break;
                case 'R':
                System.out.println("initiating takeARest");
                collectionSite.takeARest();
                System.out.println("finished takeARest; initiating collectACanvas");
                collectionSite.collectACanvas();
                System.out.println("finished collectACanvas");
                break;
            }
            System.out.println("appraiseSit");
        }
        System.out.println("sumUpResults");
        concentrationSite.sumUpResults();
        System.out.println("Terminated");
    }
}
