package hall.collin.christopher.stl4j;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import hall.collin.christopher.stl4j.STLWriter.FORMAT;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
class STLWritterTest {

	@Test
	void test() throws IllegalArgumentException, IOException, URISyntaxException {
		URL fileUrl = getClass().getResource("/cube_ascii.stl");
		File f = new File(fileUrl.toURI());
		// read file to array of triangles
		List<Triangle> mesh = new STLParser().parseSTLFile(f.toPath());
		assertEquals(4, mesh.size());

		String outputDir = "./src/test/resources/";
		new STLWriter(Path.of(outputDir, "outTestBinary.stl"), mesh, FORMAT.BINARY);
		
		//Read the newly created file
		mesh = new STLParser().parseSTLFile(Path.of(outputDir, "outTestBinary.stl"));
		//it should contain the same number of elements
		assertEquals(4, mesh.size());

		new STLWriter(Path.of(outputDir, "outTestAscii.stl"), mesh, FORMAT.ASCII);
		mesh = new STLParser().parseSTLFile(Path.of(outputDir, "outTestAscii.stl"));
		//it should contain the same number of elements
		assertEquals(4, mesh.size());

	}

}
