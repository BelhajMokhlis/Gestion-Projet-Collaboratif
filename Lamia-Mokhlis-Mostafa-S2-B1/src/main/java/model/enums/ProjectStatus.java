package model.enums;

/**
 * Represents the various statuses a project can have during its lifecycle.
 */
public enum ProjectStatus {

    /** The project is in the preparation phase. */
    IN_PREPARATION,
    
    /** The project is currently in progress. */
    IN_PROGRESS,
    
    /** The project is temporarily on hold. */
    ON_HOLD,
    
    /** The project has been completed. */
    COMPLETED,
    
    /** The project has been canceled. */
    CANCELED
}
