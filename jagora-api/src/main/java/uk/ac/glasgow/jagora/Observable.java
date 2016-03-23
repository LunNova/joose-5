package uk.ac.glasgow.jagora;

import java.util.Observer;

public interface Observable {
	/**
	 * Adds an observer to the set of observers for this object, provided
	 * that it is not the same as some observer already in the set.
	 * The order in which notifications will be delivered to multiple
	 * observers is not specified. See the class comment.
	 *
	 * @param   o   an observer to be added.
	 * @throws NullPointerException   if the parameter o is null.
	 */
	void addObserver(Observer o);

	/**
	 * Deletes an observer from the set of observers of this object.
	 * Passing <CODE>null</CODE> to this method will have no effect.
	 * @param   o   the observer to be deleted.
	 */
	void deleteObserver(Observer o);
}
