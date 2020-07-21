package com.concurrency.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * To make a class immutable make:
 * <ul>
 * <li>all its fields final</li>
 * <li>the class declared as final</li>
 * <li>the this reference is not allowed to escape during construction</li>
 * <li>Any fields which refer to mutable data objects are:
 * <ul>
 * <li>private</li>
 * <li>have no setter method</li>
 * <li>they are never directly returned of otherwise exposed to a caller</li>
 * <li>if they are changed internally in the class this change is not visible
 * and has no effect outside of the class</li>
 * </ul>
 * </li>
 * </ul>
 * For all mutable fields, e.g. Arrays, that are passed from the outside to the
 * class during the construction phase, the class needs to make a defensive-copy
 * of the elements to make sure that no other object from the outside still can
 * change the data.
 * 
 * 
 * @author Administrator
 * 
 */
public class ImmutableObjectForSharedResource {
	List<String> list = new ArrayList<String>();

	public void add(String s) {
		list.add(s);
	}

	/**
	 * Makes a defensive copy of the List and return it This way cannot modify
	 * the list itself
	 * 
	 * @return List<String>
	 */

	public List<String> getList() {
		return Collections.unmodifiableList(list);
	}
}