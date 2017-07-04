package ru.andreevskya.jpos.msr.emulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class JposMsrEmulatorServiceRoutine {
    private static final int MAX_TRACKS = 4;

    private Logger logger = LoggerFactory.getLogger(JposMsrEmulatorService.class);
    private JposMsrEmulatorService service;
    private ServerSocket listeningSocket;
    private Thread socketListenerThread;
    private volatile boolean isRunning;

    public JposMsrEmulatorServiceRoutine(JposMsrEmulatorService callbackService) {
        logger.trace("{} constructor called", this.getClass());
        this.service = callbackService;
    }

    public void startListenerService(String host, int port) throws IOException {
        listeningSocket = new ServerSocket(port, 1, InetAddress.getByName(host));
        socketListenerThread = new Thread(() -> {
            while(isRunning) {
                try(Socket clientSocket = listeningSocket.accept()) {
                    logger.info("Client {} connected", clientSocket.getInetAddress().getHostAddress());
                    doClientSocketRoutine(clientSocket);
                    logger.info("Closing client connection {}", clientSocket.getInetAddress().getHostAddress());
                } catch (IOException ioex) {

                }
            }
        });
        isRunning = true;
    }

    public void stopListenerService() {
        if(socketListenerThread == null) {
            return;
        }
        isRunning = false;
        try {
            listeningSocket.close();
        } catch(IOException ioex) {
            logger.error("Failed to gracefully close server socket", ioex);
        }
        listeningSocket = null;
        socketListenerThread = null;
    }

    private void doClientSocketRoutine(Socket client) throws IOException {
        int numTracks;
        List<byte[]> tracks = new ArrayList<>(MAX_TRACKS);
        numTracks = client.getInputStream().read();
        if(numTracks > MAX_TRACKS) {
            logger.error("Num tracks cannot be greater than {} ({} provided).", MAX_TRACKS, numTracks);
            return;
        }
        for(int i = 0; i < numTracks; ++i) {
            byte trackLength = (byte)client.getInputStream().read();
            trackLength &= 0xFF;
            byte[] track = new byte[trackLength];
            client.getInputStream().read(track);
            tracks.add(track);
        }
        // TODO: fire an event
    }
}
