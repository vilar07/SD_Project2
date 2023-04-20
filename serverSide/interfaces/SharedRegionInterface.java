package serverSide.interfaces;

import commInfra.Message;
import commInfra.MessageException;

public class SharedRegionInterface {
    private final AssaultPartyInterface[] assaultPartyInterfaces;

    private final CollectionSiteInterface collectionSiteInterface;

    private final ConcentrationSiteInterface concentrationSiteInterface;

    private final MuseumInterface museumInterface;

    public SharedRegionInterface(AssaultPartyInterface[] assaultPartyInterfaces,
                                CollectionSiteInterface collectionSiteInterface, ConcentrationSiteInterface concentrationSiteInterface, MuseumInterface museumInterface) {
        this.assaultPartyInterfaces = assaultPartyInterfaces;
        this.collectionSiteInterface = collectionSiteInterface;
        this.concentrationSiteInterface = concentrationSiteInterface;
        this.museumInterface = museumInterface;
    }

    public Message processAndReply(Message inMessage) throws MessageException {
        return null;
    }
}
