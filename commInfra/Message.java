package commInfra;

import java.io.*;

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

   private int assaultParty = -1;

   private char operation = 0;

   private boolean neededThief = false;

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
    * @param masterThiefStateOrAssaultParty the state of the Master Thief, or the Assault Party ID in the case of the GET_NEXT_ASSAULT_PARTY_ID_DONE message
    */
   public Message(int type, int masterThiefStateOrAssaultParty) {
      msgType = type;
      if (type == MessageType.GET_NEXT_ASSAULT_PARTY_ID_DONE) {
         this.assaultParty = masterThiefStateOrAssaultParty;
      } else {
         this.masterThiefState = masterThiefStateOrAssaultParty;
      }
   }

   public Message(int type, char operation) {
      msgType = type;
      this.operation = operation;
   }

   public Message(int type, int state, int assaultParty) {
      msgType = type;
      this.masterThiefState = state;
      this.assaultParty = assaultParty;
   }

   public Message(int type, int ordinaryThiefID, int ordinaryThiefState, int ordinaryThiefMD) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      this.ordinaryThiefState = ordinaryThiefState;
      this.ordinaryThiefMD = ordinaryThiefMD;
   }

   public Message(int type, int ordinaryThiefID, int ordinaryThiefState, int ordinaryThiefMD, int assaultParty) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      this.ordinaryThiefState = ordinaryThiefState;
      this.ordinaryThiefMD = ordinaryThiefMD;
      this.assaultParty = assaultParty;
   }

   public Message(int type, int ordinaryThiefID, int ordinaryThiefState, int ordinaryThiefMD, boolean neededThief) {
      msgType = type;
      this.ordinaryThiefID = ordinaryThiefID;
      this.ordinaryThiefState = ordinaryThiefState;
      this.ordinaryThiefMD = ordinaryThiefMD;
      this.neededThief = neededThief;
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

   public char getOperation() {
      return this.operation;
   }

   public boolean neededThief() {
      return this.neededThief;
   }

   @Override
   public String toString ()
   {
      return ("Message type = " + msgType +
              "\nMaster Thief state = " + masterThiefState +
              "\nOrdinary Thief state = " + ordinaryThiefState +
              "\nOrdinary Thief ID = " + ordinaryThiefID +
              "\nOrdinary Thief MD = " + ordinaryThiefMD +
              "\nAssault Party ID = " + assaultParty +
              "\nOperation = " + operation + 
              (neededThief ? "\nThief is needed": "\nThief is not needed"));
   }
}
