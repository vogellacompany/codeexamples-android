package com.vogella.android.listview.draganddrop;

/**
 * Implement to handle an item being dropped. An adapter handling the underlying
 * data will most likely handle this interface.
 * 
 * @author Eric Harlow
 */
public interface DropListener {

	/**
	 * Called when an item is to be dropped.
	 * 
	 * @param from
	 *            - index item started at.
	 * @param to
	 *            - index to place item at.
	 */
	void onDrop(int from, int to);
}
