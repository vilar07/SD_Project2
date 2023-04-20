package serverSide.interfaces;

import commInfra.Message;
import serverSide.sharedRegions.AssaultParty;

/**
 * Assault Party is constituted by Ordinary Thieves that are going to attack the museum.
 * Assault Party is shared by the thieves
 */
public class AssaultPartyInterface {
    private final AssaultParty assaultParty;

    public AssaultPartyInterface(AssaultParty assaultParty) {
        this.assaultParty = assaultParty;
    }

    public Message processAndReply(Message inMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processAndReply'");
    }
}
