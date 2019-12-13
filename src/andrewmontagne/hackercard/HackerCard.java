package andrewmontagne.hackercard;

import javacard.framework.*;

public class HackerCard extends Applet
{
    private byte[] echoBytes;
    private static final short LENGTH_ECHO_BYTES = 256;

    /**
     * Only this class's install method should create the applet object.
     */
    protected HackerCard()
    {
        echoBytes = new byte[LENGTH_ECHO_BYTES];
        register();
    }

    /**
     * Installs this applet.
     * @param bArray the array containing installation parameters
     * @param bOffset the starting offset in bArray
     * @param bLength the length in bytes of the parameter data in bArray
     */
    public static void install(byte[] bArray, short bOffset, byte bLength)
    {
        new HackerCard();
    }

    /**
     * Processes an incoming APDU.
     * @see APDU
     * @param apdu the incoming APDU
     * @exception ISOException with the response bytes per ISO 7816-4
     */
    public void process(APDU apdu)
    {
        byte buffer[] = apdu.getBuffer();

		short bytesRead = apdu.setIncomingAndReceive();
		short echoOffset = (short)0;

		while ( bytesRead > 0 ) {
            Util.arrayCopyNonAtomic(buffer, ISO7816.OFFSET_CDATA, echoBytes, echoOffset, bytesRead);
            echoOffset += bytesRead;
            bytesRead = apdu.receiveBytes(ISO7816.OFFSET_CDATA);
        }

        apdu.setOutgoing();
        apdu.setOutgoingLength( (short) (echoOffset + 5) );

        // echo header
        apdu.sendBytes( (short)0, (short) 5);
        // echo data
        apdu.sendBytesLong( echoBytes, (short) 0, echoOffset );
    }

}
