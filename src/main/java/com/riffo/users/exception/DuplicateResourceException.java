package com.riffo.users.exception;

/**
 * Exception levée lorsqu'on tente de créer une ressource qui existe déjà
 * (ex: email déjà utilisé)
 *
 * CORRECTION: Exception spécifique pour les conflits
 * Problème avant: Mauvaise distinction entre erreur 400 et 409
 * Solution: Exception avec code HTTP 409 (Conflict)
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}