package ch.heigvd.nrj.exceptions;

import javax.ejb.ApplicationException;

/**
 * This exception is thrown when an entity was not found (which can happen,
 * for instance, if a client using a REST API sends a request with a resource
 * id that does not exist in the persistence store
 * 
 * @author Olivier Liechti
 */
@ApplicationException(rollback = true)
public class EntityNotFoundException extends Exception {

}
