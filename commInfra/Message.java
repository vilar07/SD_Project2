package commInfra;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *   Internal structure of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class Message implements Serializable
{
  /**
   *  Serialization key.
   */

   private static final long serialVersionUID = 2023L;

  /**
   *  Message type.
   */

   private int msgType = -1;

   private int masterThiefState = -1;

   private int ordinaryThiefState = -1;

   private int ordinaryThiefID = -1;

   private int ordinaryThiefMD = -1;

   private char ordinaryThiefSituation = 0;

   private int ordinaryThiefPosition = -1;

   private int ordinaryThiefCanvas = -1;

   private int assaultParty = -1;

   private char operation = 0;

   private boolean neededThief = false;

   private int room = -1;

   private int paintings = -1;

   private int distance = -1;

   private int[] initialPaintings = new int[0];

   private int[] initialDistances = new int[0];

   private boolean emptyAssaultParty = false;

   private boolean assaultPartyInOperation = false;

   private boolean isMember = false;

   private int[] ordinaryThieves = new int[0];

  /**
   *  Message instantiation (form 1).
   *
   *     @param type message type
   */
   public Message (int type)
   {
      msgType = type;
   }

   /**
    * Message instantiation (form 2).
    * @param type message type
    * @param id the state of the Master Thief, the Assault Party ID in the case of the GET_NEXT_ASSAULT_PARTY_ID_DONE or DISBAND_ASSAULT_PARTY messages, 
    * the Room ID in GET_ROOM_DONE, GET_ROOM_DISTANCE_MUSEUM, GET_ROOM_PAINTINGS_MUSEUM, GET_ROOM_DISTANCE_COLLECTION_SITE and GET_ROOM_PAINTINGS_COLLECTION_SITE
    * or the number of paintings in PRINT_TAIL, GET_ROOM_PAINTINGS_COLLECTION_SITE_DONE, GET_ROOM_PAINTINGS_MUSEUM_DONE, GET_TOTAL_PAINTINGS_DONE
    * or the identification of the Ordinary Thief for HAS_BUSY_HANDS or REMOVE_MEMBER
    * or the room distance in GET_ROOM_DISTANCE_COLLECTION_SITE_DONE, GET_ROOM_DISTANCE_MUSEUM_DONE
    */
   public Message(int type, int id) {
      msgType = type;
      if (type == MessageType.GET_NEXT_ASSAULT_PARTY_ID_DONE || type == MessageType.DISBAND_ASSAULT_PARTY) {
         this.assaultParty = id;
      } else if (type == MessageType.GET_ROOM_DONE || type == MessageType.GET_ROOM_DISTANCE_MUSEUM || type == MessageType.GET_ROOM_PAINTINGS_MUSEUM
            || type == MessageType.GET_ROOM_DISTANCE_COLLECTION_SITE || type == MessageType.GET_ROOM_PAINTINGS_COLLECTION_SITE || type == MessageType.GET_NEXT_ROOM_DONE) {
         this.room = id;
      } else if (type == MessageType.PRINT_TAIL || type == MessageType.GET_ROOM_PAINTINGS_COLLECTION_SITE_DONE || type == MessageType.GET_ROOM_PAINTINGS_MUSEUM_DONE
            || type == MessageType.GET_TOTAL_PAINTINGS_DONE) {
         this.paintings = id;
      } else if (type == MessageType.HAS_BUSY_HANDS || type == MessageType.REMOVE_MEMBER || type == MessageType.IS_MEMBER) {
         this.ordinaryThiefID = id;
      } else if (type == MessageType.GET_ROOM_DISTANCE_COLLECTION_SITE_DONE || type == MessageType.GET_ROOM_DISTANCE_MUSEUM_DONE) {
         this.distance = id;
      } else {
         this.masterThiefState = id;
      }
   }

   public Message(int type, boolean res) {
      msgType = type;
      if (type == MessageType.SET_IN_OPERATION || type == MessageType.IS_ASSAULT_PARTY_IN_OPERATION_DONE) {
         this.assaultPartyInOperation = res;
      } else if (type == MessageType.HAS_BUSY_HANDS_DONE) {
         this.ordinaryThiefCanvas = (res ? 1: 0);
      } else if (type == MessageType.IS_MEMBER_DONE) {
         this.isMember = res;
      } else {
         this.emptyAssaultParty = res;
      }
   }

   public Message(int type, char operation) {
      msgType = type;
      this.operation = operation;
   }

   public Message(int type, int[] ordinaryThieves) {
      msgType = type;
      this.ordinaryThieves = ordinaryThieves;
   }

   public Message(int type, int id, int assaultPartyOrPaintings) {
      msgType = type;
      if (type == MessageType.SET_ASSAULT_PARTY_ROOM || type == MessageType.SET_ROOM_STATE_LESS_ARGS) {
         this.room = id;
      } else if (type == MessageType.REMOVE_ASSAULT_PARTY_MEMBER) {
         this.ordinaryThiefID = id;
      } else {
         this.masterThiefState = id;
      }
      if (type == MessageType.SET_ROOM_STATE_LESS_ARGS) {
         this.paintings = assaultPartyOrPaintings;
      } else {
         this.assaultParty = assaultPartyOrPaintings;
      }
   }

   public Message(int type, int ordinaryThiefOrRoomID, int ordinaryThiefStateOrRoomPaintings, int ordinaryThiefMDOrRoomDistance) {
      msgType = type;
      if (type == MessageType.SET_ROOM_STATE || type == MessageType.SET_ROOM) {
         this.room = ordinaryThiefOrRoomID;
         this.paintings = ordinaryThiefStateOrRoomPaintings;
         this.distance = ordinaryThiefMDOrRoomDistance;
      } else {
         this.ordinaryThiefID = ordinaryThiefOrRoomID;
         this.ordinaryThiefState = ordinaryThiefStateOrRoomPaintings;
         this.ordinaryThiefMD = ordinaryThiefMDOrRoomDistance;
      }
   }

   public Message(int type, int ordinaryThiefID, int ordinaryThiefState, char ordinaryThiefSituation, int ordinaryThiefMD) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      this.ordinaryThiefState = ordinaryThiefState;
      this.ordinaryThiefSituation = ordinaryThiefSituation;
      this.ordinaryThiefMD = ordinaryThiefMD;
   }

   public Message(int type, int ordinaryThiefID, int ordinaryThiefStateOrPos, int ordinaryThiefMDOrCanvas, int assaultParty) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      if (type == MessageType.SET_ASSAULT_PARTY_MEMBER) {
         this.ordinaryThiefPosition = ordinaryThiefStateOrPos;
         this.ordinaryThiefCanvas = ordinaryThiefMDOrCanvas;
      } else {
         this.ordinaryThiefState = ordinaryThiefStateOrPos;
         this.ordinaryThiefMD = ordinaryThiefMDOrCanvas;
      }
      this.assaultParty = assaultParty;
   }

   public Message(int type, int ordinaryThiefID, int ordinaryThiefState, int ordinaryThiefMD, boolean neededThief) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      this.ordinaryThiefState = ordinaryThiefState;
      this.ordinaryThiefMD = ordinaryThiefMD;
      this.neededThief = neededThief;
   }

   public Message(int type, int[] paintings, int[] distances) {
      msgType = type;
      this.initialPaintings = paintings;
      this.initialDistances = distances;
   }

   public Message(int type, int ordinaryThiefID, boolean canvas) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      this.ordinaryThiefCanvas = (canvas ? 1 : 0);
   }

   /**
   *  Getting message type.
   *
   *     @return message type
   */
   public int getMsgType ()
   {
      return (msgType);
   }

   public int getMasterThiefState() {
      return this.masterThiefState;
   }

   public int getOrdinaryThiefState() {
      return this.ordinaryThiefState;
   }

   public int getAssaultParty() {
      return this.assaultParty;
   }

   public int getOrdinaryThiefID() {
      return this.ordinaryThiefID;
   }

   public int getOrdinaryThiefMD() {
      return this.ordinaryThiefMD;
   }

   public char getOrdinaryThiefSituation() {
      return this.ordinaryThiefSituation;
   }

   public int getOrdinaryThiefPosition() {
      return this.ordinaryThiefPosition;
   }

   public int getOrdinaryThiefCanvas() {
      return this.ordinaryThiefCanvas;
   }

   public char getOperation() {
      return this.operation;
   }

   public boolean neededThief() {
      return this.neededThief;
   }

   public int getRoom() {
      return this.room;
   }

   public int getPaintings() {
      return this.paintings;
   }

   public int getDistance() {
      return this.distance;
   }

   public int[] getInitialPaintings() {
      return this.initialPaintings;
   }

   public int[] getInitialDistances() {
      return this.initialDistances;
   }

   public boolean isAssaultPartyEmpty() {
      return this.emptyAssaultParty;
   }

   public boolean isAssaultPartyInOperation() {
      return this.assaultPartyInOperation;
   }

   public boolean isMember() {
      return this.isMember;
   }

   public int[] getOrdinaryThieves() {
      return this.ordinaryThieves;
   }

   @Override
   public String toString ()
   {
      return ("Message type = " + msgType +
              "\nMaster Thief state = " + masterThiefState +
              "\nOrdinary Thief state = " + ordinaryThiefState +
              "\nOrdinary Thief ID = " + ordinaryThiefID +
              "\nOrdinary Thief MD = " + ordinaryThiefMD +
              "\nOrdinary Thief Situation = " + ordinaryThiefSituation +
              "\nOrdinary Thief Position = " + ordinaryThiefPosition +
              "\nOrdinary Thief Canvas = " + ordinaryThiefCanvas +
              "\nThief is " + (neededThief ? "needed": "not needed") +
              "\nAssault Party ID = " + assaultParty +
              "\nOperation = " + operation + 
              "\nRoom = " + room +
              "\nPaintings = " + paintings +
              "\nDistance = " + distance +
              "\nInitial Room Paintings = " + (Arrays.asList(initialPaintings).stream().map(Object::toString)).collect(Collectors.joining(", ")).toString() +
              "\nInitial Room Distances = " + (Arrays.asList(initialDistances).stream().map(Object::toString)).collect(Collectors.joining(", ")).toString() +
              "\nAssault Party is " + (emptyAssaultParty ? "empty" : "not empty") +
              "\nAssault Party is " + (assaultPartyInOperation ? "in operation": "not in operation") +
              "\nOrdinary Thief is " + (isMember ? "member": "not member") +
              "\nOrdinary Thieves = " + (Arrays.asList(ordinaryThieves).stream().map(Object::toString)).collect(Collectors.joining(", ")).toString());
   }
}
