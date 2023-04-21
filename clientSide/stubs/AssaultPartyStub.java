package clientSide.stubs;

import clientSide.entities.OrdinaryThief;
import clientSide.room.Room;

/**
 * Assault Party is constituted by Ordinary Thieves that are going to attack the museum.
 * Assault Party is shared by the thieves
 */
public interface AssaultPartyStub {
    /**
     * Called by the Master Thief to send the Assault Party to the museum
     * After that call, Assault Party can start crawling
     */
    public void sendAssaultParty();

    /**
     * Called by the Ordinary Thief to crawl in
     * @return false if they have finished the crawling
     */
    public boolean crawlIn();

    /**
     * Called to awake the first member in the line of Assault Party, by the last party member that rolled a canvas,
     * so that the Assault Party can crawl out
     * - Synchronization Point between members of the Assault Party
     */
    public void reverseDirection();

    /**
     * Called by the Ordinary Thief to crawl out
     * @return false if they have finished the crawling
     */
    public boolean crawlOut();

    /**
     * Getter for the room destination
     * @return the room
     */
    public Room getRoom();

    /**
     * Getter for the assault party identification
     * @return the assault party number
     */
    public int getID();

    /**
     * Getter for the in operation attribute
     * @return true if Assault Party is operating, false otherwise
     */
    public boolean isInOperation();

    /**
     * Setter for the inOperation attribute
     * @param inOperation true if Assault Party is operating, false if not
     */
    public void setInOperation(boolean inOperation);

    /**
     * Setter for the room destination
     * @param room the room
     * @param generalRepository the General Repository
     */
    public void setRoom(Room room, GeneralRepositoryStub generalRepository);

    /**
     * Sets the members of the Assault Party
     * @param thieves array with the Ordinary Thieves
     * @param generalRepository the General Repository
     */
    public void setMembers(OrdinaryThief[] thieves, GeneralRepositoryStub generalRepository);

    /**
     * Checks if given thief is in the Assault Party
     * @param thief the Ordinary Thief
     * @return true if they are part of the Assault Party, false otherwise
     */
    public boolean isMember(OrdinaryThief thief);

    /**
     * Removes an Ordinary Thief from the Assault Party, if they are a member of it.
     * @param thief the Ordinary Thief.
     */
    public void removeMember(OrdinaryThief thief);

    /**
     * Returns whether the Assault Party is empty, or still has Ordinary Thieves in action.
     * @return true if it is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Sets if an Ordinary Thief has a canvas.
     * @param thief the identification of the Ordinary Thief.
     * @param canvas true if the thief has a canvas in its possession, false otherwise.
     */
    public void setBusyHands(int thief, boolean canvas);

    /**
     * Returns whether an Ordinary Thief has a canvas.
     * @param thief the identification of the Ordinary Thief.
     * @return true if the thief has a canvas in its possession, false otherwise.
     */
    public boolean hasBusyHands(int thief);
}
