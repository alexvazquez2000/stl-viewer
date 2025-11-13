package hall.collin.christopher.stl4j;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TriangleTest {

	private Triangle t1;
	private Triangle t2;

	@BeforeEach
	void init() {
		Vec3d v1 = new Vec3d(1.0, 1.0, 1.0);
		Vec3d v2 = new Vec3d(2.0, 2.0, 2.0);
		Vec3d v3 = new Vec3d(3.0, 3.0, 3.0);
		t1 = new Triangle(v1, v2, v3);
		t2 = new Triangle(v1, v2, v3);
	}

	@Test
	void testTriangle() {
		assertNotNull(t1);
	}

	@Test
	void testHashCode() {
		assertEquals(1901082029, t1.hashCode());
	}

	@Test
	void testTranslate() {
		t1.translate(new Vec3d(10.0, 20.0, 30.0));
		assertEquals("Triangle[Vec3d[11.0, 21.0, 31.0]Vec3d[12.0, 22.0, 32.0]Vec3d[13.0, 23.0, 33.0]]", t1.toString());
	}

	@Test
	void testToString() {
		assertEquals("Triangle[Vec3d[1.0, 1.0, 1.0]Vec3d[2.0, 2.0, 2.0]Vec3d[3.0, 3.0, 3.0]]", t1.toString());
	}

	@Test
	void testGetVertices() {
		Vec3d[] vertices = t1.getVertices();
		assertEquals(3, vertices.length);
	}

	@Test
	void testGetNormal() {
		Vec3d v1 = new Vec3d(1.0, 0.0, 0.0);
		Vec3d v2 = new Vec3d(0.0, 1.0, 0.0);
		Vec3d v3 = new Vec3d(0.0, 0.0, 1.0);
		t1 = new Triangle(v1, v2, v3);
		Vec3d normal = t1.getNormal();
		// the vector is 1,1,1 so the lenght is sqrt(3) = 0.5773502691896258
		assertEquals(0.5773502691896258, normal.x);
		assertEquals(0.5773502691896258, normal.y);
		assertEquals(0.5773502691896258, normal.z);
	}

	@Test
	void testEqualsObject() {
		assertTrue(t1.equals(t2));
		Vec3d v1 = new Vec3d(10.0, 10.0, 10.0);
		Vec3d v2 = new Vec3d(2.0, 2.0, 2.0);
		Vec3d v3 = new Vec3d(3.0, 3.0, 3.0);
		t2 = new Triangle(v1, v2, v3);
		assertFalse(t1.equals(t2));
		
	}

}
