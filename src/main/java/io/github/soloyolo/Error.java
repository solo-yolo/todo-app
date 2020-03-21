package io.github.soloyolo;

import lombok.Value;

@Value
public class Error {
    int code;
    String message;
}
