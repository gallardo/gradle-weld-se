package app;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DemoApplication {
    private static final Logger LOGGER = LogManager.getLogger(HelloWorld.class.getName());

    @Inject
    private HelloWorld helloWorld;

    public void run() {
            LOGGER.info(helloWorld.doSalute());
    }
}
