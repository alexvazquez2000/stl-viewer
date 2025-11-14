package hall.collin.christopher.stl4j;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.List;

public class STLWriter {
	enum FORMAT {BINARY, ASCII};

	public STLWriter(Path filepath, List<Triangle> triangles, FORMAT format) {
		try {
			if (format == FORMAT.BINARY) {
				saveAsBinary(filepath, triangles);
			} else {
				saveAsAscii(filepath, triangles);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveAsAscii(Path filepath, List<Triangle> triangles) throws IOException {
		/*
		solid <object_name>
		facet normal ni nj nk
			outer loop
				vertex v1x v1y v1z
				vertex v2x v2y v2z
				vertex v3x v3y v3z
			endloop
		endfacet
		// ... more facets ...
		endsolid <object_name>
		 */
		String objectName = filepath.getFileName().toString();
		try (PrintWriter writer = new PrintWriter(new FileWriter(filepath.toString()))) {
			writer.println("solid " + objectName);

			for (Triangle triangle : triangles) {
				Vec3d normal = triangle.getNormal();
				writer.printf("  facet normal %.6f %.6f %.6f%n", normal.x, normal.y, normal.z);
				writer.println("    outer loop");
				Vec3d[] vertices = triangle.getVertices();
				for (int i=0; i < 3; i++) {
					writer.printf("      vertex %.6f %.6f %.6f%n", vertices[i].x, vertices[i].y, vertices[i].z);
				}
				writer.println("    endloop");
				writer.println("  endfacet");
			}

			writer.println("endsolid " + objectName);
		}
	}

	private void saveAsBinary(Path filepath, List<Triangle> triangles) throws IOException {
		System.out.println(filepath.toString());
		try (FileOutputStream fos = new FileOutputStream(filepath.toString())) {
			// Write 80-byte header (can be empty)
			byte[] header = new byte[80];
			fos.write(header);

			// Write number of triangles
			ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
			// Assuming normals.length equals number of triangles
			buffer.putInt(triangles.size());
			fos.write(buffer.array());

			// Write each triangle
			for (int i = 0; i < triangles.size(); i++) {
				// Write normal vector
				buffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
				Triangle triangle = triangles.get(i);
				Vec3d normal = triangle.getNormal();
				buffer.putFloat((float)normal.x);
				buffer.putFloat((float)normal.y);
				buffer.putFloat((float)normal.z);
				fos.write(buffer.array());

				// Write vertices
				Vec3d[] vertices= triangle.getVertices();
				for (int j = 0; j < 3; j++) {
					buffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
					buffer.putFloat((float)vertices[j].x);
					buffer.putFloat((float)vertices[j].y);
					buffer.putFloat((float)vertices[j].z);
					fos.write(buffer.array());
				}

				// Write 2-byte attribute byte count (usually 0)
				fos.write(new byte[]{0, 0});
			}
		}
	}
}
