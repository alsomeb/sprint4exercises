package org.example.uppgift5678;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class Protocol {
    private final int INITIAL = 0;
    private final int INTHELOOP = 1;

    private final FakeDatabase db = new FakeDatabase();

    private int state = INITIAL;

    public Object getOutput(Object fromClient) {
        // INITIAL skickas in med NULL från Servern
        // behöver ej skicka in något (Null) och vi kommer ej kolla av något här heller!

        if (state == INITIAL) {
            state = INTHELOOP; // så vi kan komma vidare till loopen
            return new Intro(true);

        } else if (state == INTHELOOP) {
            return sendResponse(fromClient);
        }

        // Kommer aldrig direkt hamna här men vi måste ha denna enligt Java
        // eller kasta ett fel
        return new Response(false, null, "State error", LocalDateTime.now());
    }

    private Response sendResponse(Object clientMessage) {
        try {
            // Den trimmas i DB metoden
            User foundUser = db.findByFullName((String) clientMessage);
            // Wrap User i en Response Object
            return new Response(true, foundUser, "", LocalDateTime.now());
        } catch (NoSuchElementException ex) {
            return new Response(false, null, ex.getMessage(), LocalDateTime.now());
        }
    }
}
