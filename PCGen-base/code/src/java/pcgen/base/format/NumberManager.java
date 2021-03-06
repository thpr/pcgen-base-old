/*
 * Copyright 2014-15 (C) Tom Parker <thpr@users.sourceforge.net>
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
package pcgen.base.format;

import pcgen.base.lang.NumberUtilities;
import pcgen.base.util.BasicIndirect;
import pcgen.base.util.BasicObjectContainer;
import pcgen.base.util.Indirect;
import pcgen.base.util.ObjectContainer;

/**
 * A NumberManager is a FormatManager that provides services for Numbers
 * (effectively Double and Integer).
 */
public class NumberManager implements FormatManager<Number>
{

	/**
	 * Converts the given String to an object of the type processed by this
	 * FormatManager.
	 * 
	 * @see pcgen.rules.format.FormatManager#convert(java.lang.String)
	 */
	@Override
	public Number convert(String s)
	{
		return NumberUtilities.getNumber(s);
	}

	/**
	 * Converts the given String to an Indirect containing an object of the type
	 * processed by this FormatManager.
	 * 
	 * @see pcgen.rules.format.FormatManager#convertIndirect(java.lang.String)
	 */
	@Override
	public Indirect<Number> convertIndirect(String s)
	{
		return new BasicIndirect<Number>(this, convert(s));
	}

	/**
	 * Converts the given String to an ObjectContainer containing an object of
	 * the type processed by this FormatManager.
	 * 
	 * @see pcgen.rules.format.FormatManager#convertObjectContainer(java.lang.String)
	 */
	@Override
	public ObjectContainer<Number> convertObjectContainer(String s)
	{
		return new BasicObjectContainer<Number>(this, convert(s));
	}

	/**
	 * "Unconverts" the object (converts the object to a "serializable" String
	 * format that can be reinterpreted by the convert* methods).
	 * 
	 * @see pcgen.rules.format.FormatManager#unconvert(java.lang.Object)
	 */
	@Override
	public String unconvert(Number s)
	{
		return s.toString();
	}

	/**
	 * The Class that this FormatManager can convert or unconvert.
	 * 
	 * @see pcgen.rules.format.FormatManager#getManagedClass()
	 */
	@Override
	public Class<Number> getManagedClass()
	{
		return Number.class;
	}

	/**
	 * The String used to refer to this format in files like the variable
	 * definition file.
	 * 
	 * @see pcgen.rules.format.FormatManager#getIdentifierType()
	 */
	@Override
	public String getIdentifierType()
	{
		return "NUMBER";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return 7987;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o)
	{
		return o instanceof NumberManager;
	}

	/**
	 * Returns null, as this FormatManager does not have a component.
	 * 
	 * @see pcgen.base.format.FormatManager#getComponentManager()
	 */
	@Override
	public FormatManager<?> getComponentManager()
	{
		return null;
	}
}
