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
   *  Getting message type.
   *
   *     @return message type
   */

   public int getMsgType ()
   {
      return (msgType);
   }

   @Override
   public String toString ()
   {
      return ("Message type = " + msgType);
   }
}
