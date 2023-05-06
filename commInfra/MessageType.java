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

  public static final int SHUTDOWN = 30;
  public static final int SHUTDOWN_DONE = 31;

  public static final int PRINT_STATE = 32;
  public static final int PRINT_STATE_DONE = 33;
  public static final int PRINT_TAIL = 34;
  public static final int PRINT_TAIL_DONE = 35;
  public static final int SET_MASTER_STATE = 36;
  public static final int SET_MASTER_STATE_DONE = 37;
  public static final int SET_ORDINARY_STATE = 38;
  public static final int SET_ORDINARY_STATE_DONE = 39;
  public static final int SET_ASSAULT_PARTY_ROOM = 40;
  public static final int SET_ASSAULT_PARTY_ROOM_DONE = 41;
  public static final int SET_ASSAULT_PARTY_MEMBER = 42;
  public static final int SET_ASSAULT_PARTY_MEMBER_DONE = 43;
  public static final int REMOVE_ASSAULT_PARTY_MEMBER = 44;
  public static final int REMOVE_ASSAULT_PARTY_MEMBER_DONE = 45;
  public static final int DISBAND_ASSAULT_PARTY = 46;
  public static final int DISBAND_ASSAULT_PARTY_DONE = 47;
  public static final int SET_ROOM_STATE = 48;
  public static final int SET_ROOM_STATE_DONE = 49;
  public static final int SET_ROOM_STATE_LESS_ARGS = 50;
  public static final int SET_ROOM_STATE_LESS_ARGS_DONE = 51;
  public static final int SET_INITIAL_ROOM_STATES = 52;
  public static final int SET_INITIAL_ROOM_STATES_DONE = 53;
  public static final int SET_BUSY_HANDS = 54;
  public static final int SET_BUSY_HANDS_DONE = 55;
  public static final int GET_ROOM = 56;
  public static final int GET_ROOM_DONE = 57;
  public static final int GET_NEXT_ROOM = 58;
  public static final int GET_NEXT_ROOM_DONE = 59;
  public static final int GET_ROOM_DISTANCE_MUSEUM = 60;
  public static final int GET_ROOM_DISTANCE_MUSEUM_DONE = 61;
  public static final int GET_ROOM_PAINTINGS_MUSEUM = 62;
  public static final int GET_ROOM_PAINTINGS_MUSEUM_DONE = 63;
  public static final int GET_ROOM_PAINTINGS_COLLECTION_SITE = 64;
  public static final int GET_ROOM_PAINTINGS_COLLECTION_SITE_DONE = 65;
  public static final int GET_ROOM_DISTANCE_COLLECTION_SITE = 66;
  public static final int GET_ROOM_DISTANCE_COLLECTION_SITE_DONE = 67;
  public static final int GET_TOTAL_PAINTINGS = 68;
  public static final int GET_TOTAL_PAINTINGS_DONE = 69;
  public static final int HAS_BUSY_HANDS = 70;
  public static final int HAS_BUSY_HANDS_DONE = 71;
  public static final int REMOVE_MEMBER = 72;
  public static final int REMOVE_MEMBER_DONE = 73;
  public static final int IS_ASSAULT_PARTY_EMPTY = 74;
  public static final int IS_ASSAULT_PARTY_EMPTY_DONE = 75;
  public static final int SET_IN_OPERATION = 76;
  public static final int SET_IN_OPERATION_DONE = 77;
  public static final int SET_MEMBERS = 78;
  public static final int SET_MEMBERS_DONE = 79;
  public static final int IS_ASSAULT_PARTY_IN_OPERATION = 80;
  public static final int IS_ASSAULT_PARTY_IN_OPERATION_DONE = 81;
  public static final int IS_MEMBER = 82;
  public static final int IS_MEMBER_DONE = 83;
  public static final int SET_ROOM = 84;
  public static final int SET_ROOM_DONE = 85;
}
