package com.cooksys.ftd.assignments.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Map<FatCat, Set<Capitalist>> h = new HashMap<FatCat, Set<Capitalist>>();
	List<Capitalist> l = new ArrayList<Capitalist>();

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it and
	 * return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add it
	 * and return false
	 *
	 * @param capitalist the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */

	@Override
	public boolean add(Capitalist capitalist) {
		// if (has(capitalist)) {
		// return false;
		// }
		// if (capitalist.getParent() == null && capitalist instanceof
		// WageSlave) {
		// return false;
		// }
		// if (capitalist.getParent() != null && !has(capitalist.getParent())) {
		// h.put(capitalist.getParent(), new HashSet<Capitalist>(){{
		// add(capitalist);
		// }});
		// return true;
		// }
		// if (capitalist.getParent() == null && capitalist instanceof FatCat) {
		// h.put((FatCat) capitalist, new HashSet<Capitalist>());
		// return true;
		// }
		//
		// return false;

		// if (has(capitalist)) {
		// return false;
		// } else if (capitalist.getParent() != null &&
		// !has(capitalist.getParent())) {
		// l.add(capitalist.getParent());
		// l.add(capitalist);
		// } else if (capitalist.getParent() == null && capitalist instanceof
		// FatCat) {
		// l.add(capitalist);
		// } else {
		// return false;
		// }
		//
		// return false;

		if (has(capitalist)) {
			return false;
		} else if (capitalist == null) {
			return false;
		} else if (capitalist instanceof WageSlave && !capitalist.hasParent()) {
			return false;
		} else {
			add(capitalist.getParent());
		}

		l.add(capitalist);

		return true;
	}

	/**
	 * @param capitalist the element to search for
	 * @return true if the element has been added to the hierarchy, false otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		return l.contains(capitalist);
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements have
	 *         been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		return new HashSet<Capitalist>(l);
		// if (l.size() == 0) {
		// return new HashSet<Capitalist>();
		// } else {
		// Set<Capitalist> elementSet = new HashSet<Capitalist>(l);
		// for (Capitalist c : l) {
		// elementSet.add(c);
		// }
		// HashSet<Capitalist> elementSetCopy = (HashSet<Capitalist>)
		// elementSet;
		// return elementSetCopy;
		// }
		// for (Capitalist capitalist : capitalists) {
		// corp.add(capitalist);
		// while (capitalist != null) {
		// expected.add(capitalist);
		// capitalist = capitalist.getParent();
		// }

	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no parents
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		Set<FatCat> parents = new HashSet<FatCat>();
		for (Capitalist c : getElements()) {
			if (c instanceof FatCat) {
				parents.add((FatCat) c);
			}
		}
		return parents;

	}

	/**
	 * @param fatCat the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a direct
	 *         parent, or an empty set if the parent is not present in the hierarchy
	 *         or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {
		// HashMap<FatCat, Set<Capitalist>> map = (HashMap<FatCat,
		// Set<Capitalist>>) getHierarchy();
		Set<Capitalist> children = new HashSet<Capitalist>();

		// for (Capitalist c : getElements()) {
		// if (c instanceof WageSlave || c.hasParent()) {
		// children.add(c);
		// }
		// }

		// for (Capitalist c : map.keySet()) {
		// for (Capitalist d : map.get(c)) {
		// children.add(d);
		// }
		// }

//		for (Capitalist c : getElements()) {
//			if (c.getParent() != null && !children.contains(c)) {
//				children.add(c);
//			}
//		}

		for (Capitalist c : getElements()) {
			if (c.getParent() == fatCat) {
				children.add(c);
			}
		}

		return children;
	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of the
	 *         associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {
		HashMap<FatCat, Set<Capitalist>> hierarchy = new HashMap<FatCat, Set<Capitalist>>();
		for (FatCat fc : getParents()) {
			hierarchy.put(fc, getChildren(fc));
		}
		return hierarchy;
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the given
	 *         element has no parent or if its parent is not in the hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {

		if (capitalist == null || !capitalist.hasParent() || !has(capitalist.getParent())) {
			return new ArrayList<>();
		}

		List<FatCat> chain = new ArrayList<>();
		FatCat parent = capitalist.getParent();

		while (parent != null) {
			chain.add(parent);
			parent = parent.getParent();
		}

		return chain;

	}

	public static void main(String[] args) {
		FatCat nullValue = null;
		FatCat fcOne = new FatCat(null, 0, null);
		FatCat fcTwo = new FatCat(null, 0, nullValue);

		System.out.println(fcTwo.hasParent());
		System.out.println(fcOne.hasParent());

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		// list.add("test");

	}
}
