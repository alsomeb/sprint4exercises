package org.example.uppgift567;

import java.io.Serializable;

public record Response(boolean isFound, User foundUser, String errorMsg) implements Serializable {
}
