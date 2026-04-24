package com.riffo.users.exception;

/**
 * Exception levée lorsqu'une ressource demandée n'existe pas en base de données
 *
 * CORRECTION: Exception spécifique pour les ressources non trouvées
 * Problème avant: Utilisation générique d'IllegalArgumentException
 * Solution: Exception métier claire avec code HTTP 404 associé
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}