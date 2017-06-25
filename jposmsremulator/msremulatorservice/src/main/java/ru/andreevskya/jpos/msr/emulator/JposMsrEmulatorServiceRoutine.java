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

    public void startListenerService(String host, int port) throws IOException {
        listeningSocket = new ServerSocket(port, 1, InetAddress.getByName(host));
        socketListenerThread = new Thread(new Runnable() {
            public void run() {
                while(isRunning) {
                    Socket clientSocket = null;
                    try {
                        clientSocket = listeningSocket.accept();
                    } catch(IOException ioex) {
                        // Do something here
                    }
                }
            }
        });
        isRunning = true;
    }

    public void stopListenerService() {
        isRunning = false;
    }
}
