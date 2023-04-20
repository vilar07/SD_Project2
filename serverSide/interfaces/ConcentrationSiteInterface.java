package serverSide.interfaces;

import commInfra.Message;
import serverSide.sharedRegions.ConcentrationSite;

/**
 * Concentration Site where ordinary thieves wait for orders.
 */
public class ConcentrationSiteInterface {
    private final ConcentrationSite concentrationSite;

    public ConcentrationSiteInterface(ConcentrationSite concentrationSite) {
        this.concentrationSite = concentrationSite;
    }
    
    public Message processAndReply(Message inMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processAndReply'");
    }
}
