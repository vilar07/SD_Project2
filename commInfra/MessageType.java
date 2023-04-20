package commInfra;

/**
 *   Type of the exchanged messages.
 *the
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType
{
  public static final int START_OPERATIONS = 0;
  public static final int APPRAISE_SIT = 1;
  public static final int GET_NEXT_ASSAULT_PARTY_ID = 2;
  public static final int PREPARE_ASSAULT_PARTY = 3;
  public static final int SEND_ASSAULT_PARTY = 4;
  public static final int TAKE_A_REST = 5;
  public static final int COLLECT_A_CANVAS = 6;
  public static final int SUM_UP_RESULTS = 7;
  public static final int AM_I_NEEDED = 8;
  public static final int PREPARE_EXCURSION = 9;
  public static final int CRAWL_IN = 10;
  public static final int ROLL_A_CANVAS = 11;
  public static final int REVERSE_DIRECTION = 12;
  public static final int CRAWL_OUT = 13;
  public static final int HAND_A_CANVAS = 14;

  public static final int START_OPERATIONS_DONE = 15;
  public static final int APPRAISE_SIT_DONE = 16;
  public static final int GET_NEXT_ASSAULT_PARTY_ID_DONE = 17;
  public static final int PREPARE_ASSAULT_PARTY_DONE = 18;
  public static final int SEND_ASSAULT_PARTY_DONE = 19;
  public static final int TAKE_A_REST_DONE = 20;
  public static final int COLLECT_A_CANVAS_DONE = 21;
  public static final int SUM_UP_RESULTS_DONE = 22;
  public static final int AM_I_NEEDED_DONE = 23;
  public static final int PREPARE_EXCURSION_DONE = 24;
  public static final int CRAWL_IN_DONE = 25;
  public static final int ROLL_A_CANVAS_DONE = 26;
  public static final int REVERSE_DIRECTION_DONE = 27;
  public static final int CRAWL_OUT_DONE = 28;
  public static final int HAND_A_CANVAS_DONE = 29;
}
