package serverSide.interfaces;

public interface MasterThiefClone {
    public static final int PLANNING_THE_HEIST = 1000;
    public static final int DECIDING_WHAT_TO_DO = 2000;
    public static final int ASSEMBLING_A_GROUP = 3000;
    public static final int WAITING_FOR_ARRIVAL = 4000;
    public static final int PRESENTING_THE_REPORT = 5000;
    
    /**
     * Sets the state of the Master Thief and propagates it to the General Repository
     * @param state the updated Master Thief state
     */
    public void setMasterThiefState(int state);
}
