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
import pcgen.base.format.BooleanManager;

public class BooleanManagerTest extends TestCase
{
	private BooleanManager manager = new BooleanManager();

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

	public void testConvertFailNotBoolean()
	{
		try
		{
			manager.convert("SomeString");
			fail("null value should fail");
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

	public void testConvertIndirectFailNotBoolean()
	{
		try
		{
			manager.convertIndirect("SomeString");
			fail("null value should fail");
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

	public void testConvertObjectContainerFailNotBoolean()
	{
		try
		{
			manager.convertObjectContainer("SomeString");
			fail("null value should fail");
		}
		catch (IllegalArgumentException e)
		{
			//ok as well
		}
	}

	public void testConvert()
	{
		assertEquals(Boolean.TRUE, manager.convert("true"));
		assertEquals(Boolean.FALSE, manager.convert("false"));
		assertEquals(Boolean.TRUE, manager.convert("True"));
	}

	public void testUnconvert()
	{
		assertEquals("true", manager.unconvert(Boolean.TRUE));
		assertEquals("false", manager.unconvert(Boolean.FALSE));
	}

	public void testConvertIndirect()
	{
		assertEquals(Boolean.TRUE, manager.convertIndirect("true")
			.resolvesTo());
		assertEquals(Boolean.FALSE, manager.convertIndirect("false")
			.resolvesTo());
	}

	public void testConvertObjectContainer()
	{
		Collection<? extends Boolean> co =
				manager.convertObjectContainer("true").getContainedObjects();
		assertEquals(1, co.size());
		assertEquals(Boolean.TRUE, co.iterator().next());
		co = manager.convertObjectContainer("false").getContainedObjects();
		assertEquals(1, co.size());
		assertEquals(Boolean.FALSE, co.iterator().next());
	}

	public void testGetIdentifier()
	{
		assertEquals("BOOLEAN", manager.getIdentifierType());
	}

	public void testHashCodeEquals()
	{
		assertEquals(new BooleanManager().hashCode(), manager.hashCode());
		assertFalse(manager.equals(new Object()));
		assertFalse(manager.equals(new StringManager()));
		assertTrue(manager.equals(new BooleanManager()));
	}

	public void testGetComponent()
	{
		assertNull(manager.getComponentManager());
	}
}
