/*
 * Copyright (c) 2014 Tom Parker <thpr@users.sourceforge.net>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package pcgen.base.format;

import java.util.Collection;

import junit.framework.TestCase;
import pcgen.base.format.StringManager;

public class StringManagerTest extends TestCase
{
	private StringManager manager = new StringManager();

	public void testConvertFailNull()
	{
		try
		{
			manager.convert(null);
			fail("null value should fail");
		}
		catch (NullPointerException e)
		{
			//ok
		}
		catch (IllegalArgumentException e)
		{
			//ok as well
		}
	}

	public void testUnconvertFailNull()
	{
		try
		{
			manager.unconvert(null);
			fail("null value should fail");
		}
		catch (NullPointerException e)
		{
			//ok
		}
		catch (IllegalArgumentException e)
		{
			//ok as well
		}
	}

	public void testConvertIndirectFailNull()
	{
		try
		{
			manager.convertIndirect(null);
			fail("null value should fail");
		}
		catch (NullPointerException e)
		{
			//ok
		}
		catch (IllegalArgumentException e)
		{
			//ok as well
		}
	}

	public void testConvertObjectContainerFailNull()
	{
		try
		{
			manager.convertObjectContainer(null);
			fail("null value should fail");
		}
		catch (NullPointerException e)
		{
			//ok
		}
		catch (IllegalArgumentException e)
		{
			//ok as well
		}
	}

	public void testConvert()
	{
		assertEquals("1", manager.convert("1"));
		assertEquals("abc", manager.convert("abc"));
	}

	public void testUnconvert()
	{
		assertEquals("1", manager.unconvert("1"));
		assertEquals("abc", manager.unconvert("abc"));
	}

	public void testConvertIndirect()
	{
		assertEquals("1", manager.convertIndirect("1").resolvesTo());
		assertEquals("gfd", manager.convertIndirect("gfd").resolvesTo());
	}

	public void testConvertObjectContainer()
	{
		Collection<? extends String> co =
				manager.convertObjectContainer("1").getContainedObjects();
		assertEquals(1, co.size());
		assertEquals("1", co.iterator().next());
		co = manager.convertObjectContainer("abc").getContainedObjects();
		assertEquals(1, co.size());
		assertEquals("abc", co.iterator().next());
	}

	public void testGetIdentifier()
	{
		assertEquals("STRING", manager.getIdentifierType());
	}

	public void testHashCodeEquals()
	{
		assertEquals(new StringManager().hashCode(), manager.hashCode());
		assertFalse(manager.equals(new Object()));
		assertFalse(manager.equals(new BooleanManager()));
		assertTrue(manager.equals(new StringManager()));
	}

	public void testGetComponent()
	{
		assertNull(manager.getComponentManager());
	}
}
