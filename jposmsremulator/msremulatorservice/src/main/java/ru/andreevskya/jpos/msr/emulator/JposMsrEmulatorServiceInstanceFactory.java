package ru.andreevskya.jpos.msr.emulator;

import jpos.JposException;
import jpos.config.JposEntry;
import jpos.loader.JposServiceInstance;
import jpos.loader.JposServiceInstanceFactory;

public class JposMsrEmulatorServiceInstanceFactory implements JposServiceInstanceFactory {
    private JposMsrEmulatorService serviceInstance = null;

    public JposServiceInstance createInstance(String s, JposEntry jposEntry) throws JposException {
        if(serviceInstance != null) {
            return serviceInstance;
        }
        serviceInstance = new JposMsrEmulatorService();

        return serviceInstance;
    }
}
