package ru.andreevskya.jpos.msr.emulator;

import jpos.JposException;
import jpos.loader.JposServiceInstance;
import jpos.services.EventCallbacks;
import jpos.services.MSRService12;

import static jpos.MSRConst.MSR_ERT_CARD;

public class JposMsrEmulatorService implements MSRService12, JposServiceInstance {
    private int errorReportingType = MSR_ERT_CARD;

    public JposMsrEmulatorService() {

    }

    public void deleteInstance() throws JposException {

    }

    /**
     * Returns true if this device supports ISO cards.
     * @return always true.
     * @throws JposException never.
     */
    public boolean getCapISO() throws JposException {
        return true;
    }

    /**
     * Returns true if this device supports JIS Type-I cards.
     * JIS-I cards are a superset of ISO cards. Therefore, if CapJISOne is true, then it is
     * implied that CapISO is also true.
     * @return always true.
     * @throws JposException never.
     */
    public boolean getCapJISOne() throws JposException {
        return true;
    }

    /**
     * Returns true if this device supports JIS Type-II cards.
     * @return always true.
     * @throws JposException never.
     */
    public boolean getCapJISTwo() throws JposException {
        return true;
    }

    public String getAccountNumber() throws JposException {
        return null;
    }

    public boolean getAutoDisable() throws JposException {
        return false;
    }

    public void setAutoDisable(boolean b) throws JposException {

    }

    public int getDataCount() throws JposException {
        return 0;
    }

    public boolean getDataEventEnabled() throws JposException {
        return false;
    }

    public void setDataEventEnabled(boolean b) throws JposException {

    }

    public boolean getDecodeData() throws JposException {
        return false;
    }

    public void setDecodeData(boolean b) throws JposException {

    }

    /**
     * Holds the type of errors to report via ErrorEvents. This property has one of the
     * following values:
     * <br/>
     * {@link jpos.MSRConst.MSR_ERT_CARD} - Report errors at a card level.
     * <br/>
     * {@link jpos.MSRConst.MSR_ERT_TRACK} - Report errors at a track level.
     * <br/>
     * An error is reported by an ErrorEvent when a card is swiped, and one or more of
     * the tracks specified by the TracksToRead property contains data with errors.
     * When the ErrorEvent is delivered to the application, two types of error reporting
     * are supported:
     * <br/>
     * • Card level: A general error status is given, with no data returned.
     * This level should be used when a simple pass/fail of the card data is sufficient.
     * <br/>
     * • Track level: The Control can return an extended status with a separate status
     * for each of the tracks. Also, for those tracks that contain valid data or no data,
     * the track’s properties are updated as with a DataEvent. For those tracks that
     * contain invalid data, the track’s properties are set to empty.
     * This level should be used when the application may be able to utilize a successfully
     * read track or tracks when another of the tracks contains errors.
     * <br/>
     * For example, suppose TracksToRead is MSR_TR_1_2_3, and a swiped card
     * contains good track 1 and 2 data, but track 3 contains “random noise” that is
     * flagged as an error by the MSR. With track level error reporting, the Error-
     * Event sets the track 1 and 2 properties with the valid data, sets the track 3
     * properties to empty, and returns an error code indicating the status of each
     * track.
     * @return the type of errors to report via ErrorEvents.
     * @throws JposException never.
     */
    public int getErrorReportingType() throws JposException {
        return this.errorReportingType;
    }

    /**
     * Sets the type of errors to report via ErrorEvents.
     * @see #getErrorReportingType()
     * @param reportingType the type of errors to report via ErrorEvents.
     * @throws JposException never
     */
    public void setErrorReportingType(int reportingType) throws JposException {
        this.errorReportingType = reportingType;
    }

    public String getExpirationDate() throws JposException {
        return null;
    }

    public String getFirstName() throws JposException {
        return null;
    }

    public String getMiddleInitial() throws JposException {
        return null;
    }

    public boolean getParseDecodeData() throws JposException {
        return false;
    }

    public void setParseDecodeData(boolean b) throws JposException {

    }

    public String getServiceCode() throws JposException {
        return null;
    }

    public String getSuffix() throws JposException {
        return null;
    }

    public String getSurname() throws JposException {
        return null;
    }

    public String getTitle() throws JposException {
        return null;
    }

    public byte[] getTrack1Data() throws JposException {
        return new byte[0];
    }

    public byte[] getTrack1DiscretionaryData() throws JposException {
        return new byte[0];
    }

    public byte[] getTrack2Data() throws JposException {
        return new byte[0];
    }

    public byte[] getTrack2DiscretionaryData() throws JposException {
        return new byte[0];
    }

    public byte[] getTrack3Data() throws JposException {
        return new byte[0];
    }

    public int getTracksToRead() throws JposException {
        return 0;
    }

    public void setTracksToRead(int i) throws JposException {

    }

    public void clearInput() throws JposException {

    }

    public String getCheckHealthText() throws JposException {
        return "OK";
    }

    public boolean getClaimed() throws JposException {
        return false;
    }

    public boolean getDeviceEnabled() throws JposException {
        return false;
    }

    public void setDeviceEnabled(boolean b) throws JposException {

    }

    public String getDeviceServiceDescription() throws JposException {
        return null;
    }

    public int getDeviceServiceVersion() throws JposException {
        return 0;
    }

    public boolean getFreezeEvents() throws JposException {
        return false;
    }

    public void setFreezeEvents(boolean b) throws JposException {

    }

    /**
     * Returns physical device description.
     * @return physical device description
     * @throws JposException never.
     */
    public String getPhysicalDeviceDescription() throws JposException {
        return "JPOS-compatible magnetic strip reader emulator.";
    }

    /**
     * Returns physcal device name.
     * @return physical device name.
     * @throws JposException never
     */
    public String getPhysicalDeviceName() throws JposException {
        // The length of this string should be limited to 30 characters.
        return "JPOS MSR Emulator";
    }

    public int getState() throws JposException {
        return 0;
    }

    public void claim(int i) throws JposException {

    }

    public void close() throws JposException {

    }

    /**
     * Performs health-check on the device.
     * This method is not implemented.
     * @param level health-check level. Shoul
     * @throws JposException never
     */
    public void checkHealth(int level) throws JposException {
        // Not implemented.
    }

    public void directIO(int i, int[] ints, Object o) throws JposException {

    }

    public void open(String s, EventCallbacks eventCallbacks) throws JposException {

    }

    public void release() throws JposException {

    }
}
