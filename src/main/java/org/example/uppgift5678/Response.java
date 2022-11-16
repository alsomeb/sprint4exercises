package org.example.uppgift5678;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Kan va Record, pga finala variablar samt getters bara!
public record Response(boolean isFound, User foundUser, String errorMsg, LocalDateTime timeStamp) implements Serializable {

    public String timestampPretty() {
        // Timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return timeStamp.format(formatter);
    }
}
