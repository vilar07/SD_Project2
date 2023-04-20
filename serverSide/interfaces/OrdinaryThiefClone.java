package serverSide.interfaces;

public interface OrdinaryThiefClone {
    public static final int CONCENTRATION_SITE = 1000;
    public static final int COLLECTION_SITE = 2000;
    public static final int CRAWLING_INWARDS = 3000;
    public static final int AT_A_ROOM = 4000;
    public static final int CRAWLING_OUTWARDS = 5000;
    
    /**
     * Getter for the identification number of the Ordinary Thief
     * @return the identification number of the Ordinary Thief
     */
    public int getOrdinaryThiefID();

    /**
     * Getter for the maximum displacement of the Ordinary Thief
     * @return the maximum displacement of the Ordinary Thief
     */
    public int getOrdinaryThiefMaxDisplacement();

    /**
     * Setter for the state of the Ordinary Thief
     * Propagates information to the GeneralRepository
     * @param state the state of the Ordinary Thief
     */
    public void setOrdinaryThiefState(int state);
}
