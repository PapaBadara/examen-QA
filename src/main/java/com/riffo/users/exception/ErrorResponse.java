package com.riffo.users.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Structure standardisée pour les réponses d'erreur de l'API
 *
 * Format JSON retourné en cas d'erreur:
 * {
 *   "status": 404,
 *   "message": "Partenaire non trouve avec id: 99",
 *   "timestamp": "2026-04-24T10:30:00",
 *   "errors": null
 * }
 */
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;  // Pour les erreurs de validation multiples

    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = null;
    }

    public ErrorResponse(int status, String message, LocalDateTime timestamp, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    // Getters pour serialisation JSON
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}