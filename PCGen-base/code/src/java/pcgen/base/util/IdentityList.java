/*
 * Copyright 2009 (C) Tom Parker <thpr@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pcgen.base.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * IdentityList is an implementation of the List Interface that uses Identity
 * (==) rather than equality (.equals() ) to establish behavior for remove. This
 * is useful to maintain an input ordered identity list (not possible with
 * IdentityHashMap because it does not maintain input order).
 * 
 * @param <T>
 *            The type of object stored in this IdentityList
 */
@SuppressWarnings("PMD.TooManyMethods")
public class IdentityList<T> implements List<T>
{
	/**
	 * The underlying map providing storage of Identity structures.
	 */
	private final List<Identity<T>> embeddedList =
			new LinkedList<Identity<T>>();

	/**
	 * Creates a new (empty) IdentityList.
	 */
	public IdentityList()
	{
		super();
	}

	/**
	 * Createss a new IdentityList which will be initialized with the contents
	 * of the given List.
	 * 
	 * @param list
	 *            The list of objects used to initialize the contents of this
	 *            IdentityList
	 */
	public IdentityList(List<T> list)
	{
		addAll(list);
	}

	/**
	 * Internal class used to convert an object to the Identity wrapper for that
	 * object.
	 * 
	 * @param <V>
	 *            The type of object for which the identity is being returned
	 * @param value
	 *            The value for which the identity is being returned
	 * @return The Identity object for the given parameter
	 */
	private <V> Identity<V> getIdentity(V value)
	{
		return new Identity<V>(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, T element)
	{
		embeddedList.add(index, getIdentity(element));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean add(T element)
	{
		return embeddedList.add(getIdentity(element));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean addAll(Collection<? extends T> collection)
	{
		for (T element : collection)
		{
			add(element);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> collection)
	{
		int location = index;
		for (T element : collection)
		{
			add(location++, element);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear()
	{
		embeddedList.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object element)
	{
		return embeddedList.contains(getIdentity(element));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<?> collection)
	{
		for (Object element : collection)
		{
			if (!embeddedList.contains(getIdentity(element)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof IdentityList
			&& embeddedList.equals(((IdentityList<?>) obj).embeddedList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(int index)
	{
		return embeddedList.get(index).getUnderlying();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return embeddedList.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object element)
	{
		return embeddedList.indexOf(getIdentity(element));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty()
	{
		return embeddedList.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new IdentityIterator<T>(embeddedList.listIterator());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object element)
	{
		return embeddedList.lastIndexOf(getIdentity(element));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator()
	{
		return new IdentityIterator<T>(embeddedList.listIterator());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int index)
	{
		return new IdentityIterator<T>(embeddedList.listIterator(index));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T remove(int index)
	{
		return embeddedList.remove(index).getUnderlying();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(Object element)
	{
		return embeddedList.remove(getIdentity(element));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAll(Collection<?> collection)
	{
		boolean result = true;
		for (Object o : collection)
		{
			result &= remove(o);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retainAll(Collection<?> collection)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T set(int index, T element)
	{
		return embeddedList.set(index, getIdentity(element)).getUnderlying();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size()
	{
		return embeddedList.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> subList(int startIndex, int endIndex)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray()
	{
		Object[] array = embeddedList.toArray();
		putIntoArray(array, array);
		return array;
	}

	/**
	 * Puts the underlying objects (from the source array of Identity objects)
	 * into the given target array.
	 * 
	 * @param <V>
	 *            The type of of object in the returned array
	 * @param source
	 *            The Wrapped (identity) objects
	 * @param target
	 *            The target array which will be loaded
	 */
	private <V> void putIntoArray(Object[] source, V[] target)
	{
		for (int i = 0; i < source.length; i++)
		{
			@SuppressWarnings("unchecked")
			Identity<V> identity = (Identity<V>) source[i];
			target[i] = identity.getUnderlying();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <V> V[] toArray(V[] newArray)
	{
		Object[] array = embeddedList.toArray();
		int size = embeddedList.size();
		V[] returnArray = newArray;
		// Protect for small array
		if (newArray.length < size)
		{
			returnArray =
					(V[]) java.lang.reflect.Array.newInstance(newArray
						.getClass().getComponentType(), size);
		}
		putIntoArray(array, returnArray);
		return returnArray;
	}

	/**
	 * An object used to wrap an object to ensure checks are done with identity
	 * (==) not equality (.equals()).
	 * 
	 * @param <T>
	 *            The type of object underlying this Identity
	 */
	private static final class Identity<T>
	{

		/**
		 * The underlying item for this Identity.
		 */
		private final T underlying;

		/**
		 * Constructs a new Identity with the given underlying item.
		 * 
		 * @param item
		 *            The underlying item for this Identity
		 */
		public Identity(T item)
		{
			underlying = item;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj)
		{
			return obj instanceof Identity
				&& ((Identity<?>) obj).underlying == underlying;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode()
		{
			return underlying.hashCode();
		}

		/**
		 * Returns the object underlying this Identity.
		 * 
		 * @return The object underlying this Identity
		 */
		public T getUnderlying()
		{
			return underlying;
		}

	}

	/**
	 * An Iterator used to dynamically wrap and unwrap objects inside of
	 * Identity.
	 * 
	 * @param <I>
	 *            The type of object underlying this IdentityIterator
	 */
	private class IdentityIterator<I> implements ListIterator<I>
	{
		/**
		 * The ListIterator underlying this IdentityIterator.
		 */
		private final ListIterator<Identity<I>> iter;

		/**
		 * Constructs a new IdentityIterator with the given underlying
		 * ListIterator.
		 * 
		 * @param iterator
		 *            The ListIterator underlying this IdentityIterator
		 */
		public IdentityIterator(ListIterator<Identity<I>> iterator)
		{
			iter = iterator;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void add(I item)
		{
			iter.add(getIdentity(item));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext()
		{
			return iter.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasPrevious()
		{
			return iter.hasPrevious();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public I next()
		{
			return iter.next().getUnderlying();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int nextIndex()
		{
			return iter.nextIndex();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public I previous()
		{
			return iter.previous().getUnderlying();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int previousIndex()
		{
			return iter.previousIndex();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove()
		{
			iter.remove();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void set(I item)
		{
			iter.set(getIdentity(item));
		}

	}
}
