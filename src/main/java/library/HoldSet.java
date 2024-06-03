package library;

import java.util.*;

/**
 * A class representing a set of holds
 *
 * @author A Jiayi
 * @version 1.0
 */
public class HoldSet {

	/** set is stored as a vector. */
	private Vector<Hold> set = null;

	/**
	 * Class HoldSet constructor.
	 *
	 */
	public HoldSet() {
		set = new Vector<Hold>();
	}

	/**
	 * Class HoldSet constructor
	 * 
	 * @param inSet Vector of holds to initialize this set
	 */
	public HoldSet(Vector<Hold> initialSet) {
		set = new Vector<Hold>(initialSet);
	}

	/**
	 * Returns the Hold at the specified index
	 * 
	 * @param index int index of hold to return
	 * @return Hold
	 */
	public Hold getHoldAt(int index) {
		return (Hold) set.get(index);
	}

	/**
	 * Returns the number of holds in this set
	 * 
	 * @return int
	 */
	public int getHoldCount() {
		return set.size();
	}

	/**
	 * Adds a hold to this set
	 * 
	 * @param hold Hold hold to add to the set
	 * @return void
	 */
	public void addHold(Hold hold) {
		set.add(hold);
	}

	/**
	 * Removes and returns the hold at the specified index
	 * 
	 * @param index int index of hold to remove
	 * @return Hold
	 */
	public Hold removeHoldAt(int index) {
		return (Hold) set.remove(index);
	}

	/**
	 * Removes the specified hold from the set
	 * 
	 * @param hold Hold hold to remove
	 * @return boolean
	 */
	public boolean removeHold(Hold hold) {
		return set.remove(hold);
	}
}
