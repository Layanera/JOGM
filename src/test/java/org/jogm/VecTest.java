package org.jogm;

import static org.junit.Assert.*;
import org.junit.Test;

public class VecTest
{
	@Test
	public void testCreation()
	{
		float[] values = {0, 0, 0};
		Vec vec = new Vec(values);
		assertEquals(vec.getDimensionCount(), values.length);

		Vec vec2 = new Vec(null, 3);
	}
}
