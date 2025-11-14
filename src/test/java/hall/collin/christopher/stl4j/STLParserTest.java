package hall.collin.christopher.stl4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
class STLParserTest {

	@Test
	void testParseAsciiStlFile() throws IllegalArgumentException, IOException, URISyntaxException {
		URL fileUrl = getClass().getResource("/cube_ascii.stl");
		File f = new File(fileUrl.toURI());
		// read file to array of triangles
		List<Triangle> mesh = new STLParser().parseSTLFile(f.toPath());
		assertEquals(4, mesh.size());
	}
	

	@Test
	void testParseBinaryStlFile() throws IllegalArgumentException, IOException, URISyntaxException {
		URL fileUrl = getClass().getResource("/3D_model_of_a_Cube.stl");
		File f = new File(fileUrl.toURI());
		// read file to array of triangles
		List<Triangle> mesh = new STLParser().parseSTLFile(f.toPath());
		assertEquals(12, mesh.size());
	}

}
