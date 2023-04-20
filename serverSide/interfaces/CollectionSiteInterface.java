package serverSide.interfaces;

import commInfra.Message;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processAndReply'");
    }
}