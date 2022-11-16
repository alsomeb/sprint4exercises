package org.example.uppgift5678;

import java.io.Serializable;

// Kan va Record, pga finala variablar samt getters bara!
public record Response(boolean isFound, User foundUser, String errorMsg) implements Serializable {
}
