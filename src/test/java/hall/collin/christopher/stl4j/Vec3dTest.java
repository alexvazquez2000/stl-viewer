package hall.collin.christopher.stl4j;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
class Vec3dTest {
	private Vec3d v1 = new Vec3d(1.0, 1.0, 1.0);
	private Vec3d v2 = new Vec3d(0.5, 0.5, 0.5);
	
	@Test
	void testVec3d() {
		assertNotNull(v1);
	}

	@Test
	void testHashCode() {
		assertEquals(32714393, v1.hashCode());
	}


	@Test
	void testMul() {
		Vec3d v3 = v1.mul(2.0);
		assertEquals(2.0, v3.x);
		assertEquals(2.0, v3.y);
		assertEquals(2.0, v3.z);
	}

	@Test
	void testSubVec3dVec3d() {
		Vec3d v3 = Vec3d.sub(v1, v2);
		assertEquals(0.5, v3.x);
		assertEquals(0.5, v3.y);
		assertEquals(0.5, v3.z);
	}

	@Test
	void testSubVec3d() {
		Vec3d v3 = v1.sub(v2);
		assertEquals(0.5, v3.x);
		assertEquals(0.5, v3.y);
		assertEquals(0.5, v3.z);
	}

	@Test
	void testAddVec3dVec3d() {
		Vec3d v3 = Vec3d.add(v1, v2);
		assertEquals(1.5, v3.x);
		assertEquals(1.5, v3.y);
		assertEquals(1.5, v3.z);
	}

	@Test
	void testAddVec3d() {
		Vec3d v3 = v1.add(v2);
		assertEquals(1.5, v3.x);
		assertEquals(1.5, v3.y);
		assertEquals(1.5, v3.z);
	}

	@Test
	void testLength() {
		assertEquals(1.7320508075688772, v1.length());
	}

	@Test
	void testNormalize() {
		Vec3d v3 = v1.normalize();
		assertEquals(0.5773502691896258, v3.x);
		assertEquals(0.5773502691896258, v3.y);
		assertEquals(0.5773502691896258, v3.z);
	}

	@Test
	void testCross() {
		Vec3d v3 = Vec3d.cross(v1,v2);
		assertEquals(0.0, v3.x);
		assertEquals(0.0, v3.y);
		assertEquals(0.0, v3.z);
	}

	@Test
	void testDot() {
		double dot = v1.dot(v2);
		assertEquals(1.5, dot);
	}

	@Test
	void testEqualsObject() {
		//it is equal to itself
		assertTrue(v1.equals(v1));
		//v1 and v2 are not equal
		assertFalse(v1.equals(v2));
		//make both equal but different objects
		v1 = new Vec3d(1.0, 1.0, 1.0);
		v2 = new Vec3d(1.0, 1.0, 1.0);
		assertTrue(v1.equals(v2));
	}

	@Test
	void testToString() {
		assertEquals("Vec3d[1.0, 1.0, 1.0]", v1.toString());
	}

	@Test
	void testGetAngle() {
		//it is NaN if they are on the same line
		v2 = new Vec3d(1.1, 2.2, 3.3);
		double angle = Vec3d.getAngle(v1,v2);
		assertEquals(0.3875966866551805, angle);
	}

}
