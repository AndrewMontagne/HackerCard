package andrewmontagne.filesystem;

import javacard.framework.*;

public class Filesystem extends Applet {

    private final static byte APDU_CLASS = (byte)0xB1;
    private byte[] data;

    /**
     * Only this class's install method should create the applet object.
     */
    protected Filesystem(short size) {
        register();
        data = new byte[size];
    }

    /**
     * Installs this applet.
     * @param bArray the array containing installation parameters
     * @param bOffset the starting offset in bArray
     * @param bLength the length in bytes of the parameter data in bArray
     */
    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new Filesystem((short)2000);
    }

    /**
     * Processes an incoming APDU.
     * @see APDU
     * @param apdu the incoming APDU
     * @exception ISOException with the response bytes per ISO 7816-4
     */
    public void process(APDU apdu) {
        byte[] buffer = apdu.getBuffer();

        if (buffer[ISO7816.OFFSET_CLA] == 0) {
            return;
        }

        if (buffer[ISO7816.OFFSET_CLA] != APDU_CLASS) {
            ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
        }

        switch (buffer[ISO7816.OFFSET_INS]) {
            case INSTRUCTION_GET_FREE_SPACE:
                getFreeSpace(apdu);
                return;
            default:
                ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        }
    }

    private final static byte INSTRUCTION_GET_FREE_SPACE = (byte)0x01;

    public void getFreeSpace(APDU apdu) {
        short freeMem = JCSystem.getAvailableMemory(JCSystem.MEMORY_TYPE_PERSISTENT);

        byte[] buffer = apdu.getBuffer();
        short le = apdu.setOutgoing();
        if ( le < 2 )
            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
        apdu.setOutgoingLength((byte)2);
        buffer[0] = (byte)(freeMem >> 8);
        buffer[1] = (byte)(freeMem & 0xFF);
        apdu.sendBytes((short)0, (short)2);
    }
}