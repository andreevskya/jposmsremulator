package ru.andreevskya.jpos.msr.emulator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class JposMsrEmulatorServiceRoutine {
    private JposMsrEmulatorService service;
    private ServerSocket listeningSocket;
    private Thread socketListenerThread;
    private volatile boolean isRunning;

    public JposMsrEmulatorServiceRoutine(JposMsrEmulatorService callbackService) {
        this.service = callbackService;
    }

    public synchronized void startListenerService(String host, int port) throws IOException {
        listeningSocket = new ServerSocket(port, 1, InetAddress.getByName(host));
        socketListenerThread = new Thread(() -> {
            while(isRunning) {
                Socket clientSocket;
                try {
                    clientSocket = listeningSocket.accept();
                    doClientSocketRoutine(clientSocket);
                    clientSocket.close();
                } catch(IOException ioex) {
                    // Do something here
                }
            }
        });
        isRunning = true;
    }

    public synchronized void stopListenerService() {
        if(socketListenerThread == null) {
            return;
        }
        isRunning = false;
        try {
            listeningSocket.close();
        } catch(IOException ioex) {
            // Do nothing
        }
        listeningSocket = null;
        socketListenerThread = null;
    }

    private void doClientSocketRoutine(Socket client) {

    }
}
