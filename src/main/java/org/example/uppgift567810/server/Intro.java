package org.example.uppgift567810.server;

import java.io.Serializable;

// Kan va Record, pga final boolean samt getter
public record Intro(boolean isConnected) implements Serializable {
}
