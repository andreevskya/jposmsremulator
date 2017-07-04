package ru.andreevskya.jpos.msr.emulator;

import jpos.JposConst;
import jpos.JposException;
import jpos.MSRConst;
import jpos.loader.JposServiceInstance;
import jpos.services.EventCallbacks;
import jpos.services.MSRService15;

import static jpos.MSRConst.MSR_ERT_CARD;

public class JposMsrEmulatorService implements MSRService15, JposServiceInstance {
    private int errorReportingType = MSR_ERT_CARD;
    private boolean isOpened = false;
    private boolean isClaimed = false;
    private boolean isEnabled = false;
    private boolean isAutodisable = false;
    private boolean isDataEventEnabled = false;
    private boolean isDecodeData = true;
    private boolean isFreezeEvents = false;

    private byte[] track1Data = new byte[0];
    private byte[] track2Data = new byte[0];
    private byte[] track3Data = new byte[0];
    private byte[] track4Data = new byte[0];
    private int numTracksToRead = MSRConst.MSR_TR_1_2_3;
    private int numPendingEvents = 0;
    private int deviceState = JposConst.JPOS_S_CLOSED;
    private EventCallbacks eventCallbacks;

    public JposMsrEmulatorService() {

    }

    @Override
    public void deleteInstance() throws JposException {

    }

    /**
     * Returns true if this device supports ISO cards.
     * @return always true.
     * @throws JposException if device isn't opened.
     */
    @Override
    public boolean getCapISO() throws JposException {
        throwExceptionIfNotOpened();
        return true;
    }

    /**
     * Returns true if this device supports JIS Type-I cards.
     * JIS-I cards are a superset of ISO cards. Therefore, if CapJISOne is true, then it is
     * implied that CapISO is also true.
     * @return always true.
     * @throws JposException if device is not opened.
     */
    @Override
    public boolean getCapJISOne() throws JposException {
        throwExceptionIfNotOpened();
        return true;
    }

    /**
     * Returns true if this device supports JIS Type-II cards.
     * @return always true.
     * @throws JposException if device is not opened.
     */
    @Override
    public boolean getCapJISTwo() throws JposException {
        throwExceptionIfNotOpened();
        return true;
    }

    @Override
    public String getAccountNumber() throws JposException {
        throwExceptionIfNotOpened();
        return "";
    }

    @Override
    public boolean getAutoDisable() throws JposException {
        throwExceptionIfNotOpened();
        return isAutodisable;
    }

    @Override
    public void setAutoDisable(boolean value) throws JposException {
        throwExceptionIfNotOpened();
        this.isAutodisable = value;
    }

    /**
     * Returns the number of queued events.
     * @return number of queued events.
     * @throws JposException if device is not opened by calling {@link #open(String, EventCallbacks)} method.
     */
    @Override
    public int getDataCount() throws JposException {
        throwExceptionIfNotOpened();
        return this.numPendingEvents;
    }

    @Override
    public boolean getDataEventEnabled() throws JposException {
        throwExceptionIfNotOpened();
        return isDataEventEnabled;
    }

    @Override
    public void setDataEventEnabled(boolean enabled) throws JposException {
        throwExceptionIfNotOpened();
        this.isDataEventEnabled = enabled;
    }

    @Override
    public boolean getDecodeData() throws JposException {
        throwExceptionIfNotOpened();
        return this.isDecodeData;
    }

    @Override
    public void setDecodeData(boolean value) throws JposException {
        throwExceptionIfNotOpened();
        this.isDecodeData = value;
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
     * @throws JposException if device is not opened.
     */
    @Override
    public int getErrorReportingType() throws JposException {
        throwExceptionIfNotOpened();
        return this.errorReportingType;
    }

    /**
     * Sets the type of errors to report via ErrorEvents.
     * @see #getErrorReportingType()
     * @param reportingType the type of errors to report via ErrorEvents.
     * @throws JposException if device is not opened.
     */
    @Override
    public void setErrorReportingType(int reportingType) throws JposException {
        throwExceptionIfNotOpened();
        this.errorReportingType = reportingType;
    }

    @Override
    public String getExpirationDate() throws JposException {
        return "";
    }

    @Override
    public String getFirstName() throws JposException {
        return "";
    }

    @Override
    public String getMiddleInitial() throws JposException {
        return "";
    }

    @Override
    public boolean getParseDecodeData() throws JposException {
        return false;
    }

    @Override
    public void setParseDecodeData(boolean b) throws JposException {
        throwNotImplemented();
    }

    @Override
    public String getServiceCode() throws JposException {
        return "";
    }

    @Override
    public String getSuffix() throws JposException {
        return "";
    }

    @Override
    public String getSurname() throws JposException {
        return "";
    }

    @Override
    public String getTitle() throws JposException {
        return "";
    }

    @Override
    public byte[] getTrack1Data() throws JposException {
        return this.track1Data;
    }

    @Override
    public byte[] getTrack1DiscretionaryData() throws JposException {
        return new byte[0];
    }

    @Override
    public byte[] getTrack2Data() throws JposException {
        return this.track2Data;
    }

    @Override
    public byte[] getTrack2DiscretionaryData() throws JposException {
        return new byte[0];
    }

    @Override
    public byte[] getTrack3Data() throws JposException {
        return this.track3Data;
    }

    @Override
    public int getTracksToRead() throws JposException {
        return this.numTracksToRead;
    }

    @Override
    public void setTracksToRead(int tracks) throws JposException {
        switch (tracks) {
            case MSRConst.MSR_TR_1:
            case MSRConst.MSR_TR_2:
            case MSRConst.MSR_TR_3:
            case MSRConst.MSR_TR_4:
            case MSRConst.MSR_TR_1_2:
            case MSRConst.MSR_TR_1_3:
            case MSRConst.MSR_TR_1_4:
            case MSRConst.MSR_TR_2_3:
            case MSRConst.MSR_TR_2_4:
            case MSRConst.MSR_TR_3_4:
            case MSRConst.MSR_TR_1_2_3:
            case MSRConst.MSR_TR_1_2_3_4:
            case MSRConst.MSR_TR_1_2_4:
            case MSRConst.MSR_TR_1_3_4:
            case MSRConst.MSR_TR_2_3_4:
            case MSRConst.MSR_TR_NONE:
                this.numTracksToRead = tracks;
                break;
            default:
                throw new JposException(
                        JposConst.JPOS_E_ILLEGAL,
                        "Unknown number of tracks to read. Number of tracks should be one from contant MSRConst.MSR_TR_xxx"
                );
        }
    }

    @Override
    public void clearInput() throws JposException {
        throwExceptionIfNotOpened();
        throwExceptionIfNotClaimed();
    }

    @Override
    public String getCheckHealthText() throws JposException {
        throwExceptionIfNotOpened();
        return "OK";
    }

    @Override
    public boolean getClaimed() throws JposException {
        throwExceptionIfNotOpened();
        return isClaimed;
    }

    @Override
    public boolean getDeviceEnabled() throws JposException {
        throwExceptionIfNotOpened();
        throwExceptionIfNotClaimed();
        return isEnabled;
    }

    @Override
    public void setDeviceEnabled(boolean enabled) throws JposException {
        throwExceptionIfNotOpened();
        throwExceptionIfNotClaimed();
        this.isEnabled = enabled;
    }

    @Override
    public String getDeviceServiceDescription() throws JposException {
        return "JPOS-compatible magnetic strip reader emulator.";
    }

    @Override
    public int getDeviceServiceVersion() throws JposException {
        return 1000;
    }

    @Override
    public boolean getFreezeEvents() throws JposException {
        return this.isFreezeEvents;
    }

    @Override
    public void setFreezeEvents(boolean doFreeze) throws JposException {
        this.isFreezeEvents = doFreeze;
    }

    /**
     * Returns physical device description.
     * @return physical device description
     * @throws JposException if device is not opened.
     */
    @Override
    public String getPhysicalDeviceDescription() throws JposException {
        throwExceptionIfNotOpened();
        return "JPOS-compatible magnetic strip reader emulator.";
    }

    /**
     * Returns physcal device name.
     * @return physical device name.
     * @throws JposException if device is not opened.
     */
    @Override
    public String getPhysicalDeviceName() throws JposException {
        throwExceptionIfNotOpened();
        // The length of this string should be limited to 30 characters.
        return "JPOS MSR Emulator";
    }

    @Override
    public int getState() throws JposException {
        return this.deviceState;
    }

    @Override
    public void claim(int timeout) throws JposException {
        throwExceptionIfNotOpened();
        this.isClaimed = true;
    }

    @Override
    public void close() throws JposException {
        throwExceptionIfNotOpened();
        isOpened = false;
        this.deviceState = JposConst.JPOS_S_CLOSED;
    }

    /**
     * Performs health-check on the device.
     * This method is not implemented.
     * @param level health-check level.
     * @throws JposException if device is not opened.
     */
    @Override
    public void checkHealth(int level) throws JposException {
        throwExceptionIfNotOpened();
        throwExceptionIfNotClaimed();
        throwExceptionIfNotEnabled();
    }

    @Override
    public void directIO(int i, int[] ints, Object o) throws JposException {
        throwNotImplemented();
    }

    @Override
    public void open(String s, EventCallbacks eventCallbacks) throws JposException {
        isOpened = true;
        this.eventCallbacks = eventCallbacks;
        this.deviceState = JposConst.JPOS_S_IDLE;
    }

    @Override
    public void release() throws JposException {
        throwExceptionIfNotClaimed();
        throwExceptionIfNotOpened();
    }

    @Override
    public boolean getCapTransmitSentinels() throws JposException {
        return false;
    }

    @Override
    public byte[] getTrack4Data() throws JposException {
        return this.track4Data;
    }

    @Override
    public boolean getTransmitSentinels() throws JposException {
        return false;
    }

    @Override
    public void setTransmitSentinels(boolean b) throws JposException {
        throwNotImplemented();
    }

    @Override
    public int getCapPowerReporting() throws JposException {
        return 0;
    }

    @Override
    public int getPowerNotify() throws JposException {
        return 0;
    }

    @Override
    public void setPowerNotify(int i) throws JposException {
        throwNotImplemented();
    }

    @Override
    public int getPowerState() throws JposException {
        return 0;
    }

    EventCallbacks getEventCallbacks() {
        return this.eventCallbacks;
    }

    private void throwExceptionIfNotOpened() throws JposException {
        if(!isOpened) {
            throw new JposException(JposConst.JPOS_E_CLOSED, "Device is not opened.");
        }
    }

    private void throwExceptionIfNotClaimed() throws JposException {
        if(!isClaimed) {
            throw new JposException(JposConst.JPOS_E_NOTCLAIMED, "Device not claimed.");
        }
    }

    private void throwExceptionIfNotEnabled() throws JposException {
        if(!isEnabled) {
            throw new JposException(JposConst.JPOS_E_DISABLED, "Device is not enabled.");
        }
    }

    private void throwNotImplemented() throws JposException {
        throw new JposException(JposConst.JPOS_E_ILLEGAL, "This method is not implemented by the emulator",
                new UnsupportedOperationException("Not implemented"));
    }
}
