package com.isi.demo.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
