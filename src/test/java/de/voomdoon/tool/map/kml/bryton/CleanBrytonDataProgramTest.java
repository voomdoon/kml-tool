package de.voomdoon.tool.map.kml.bryton;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.voomdoon.testing.logging.tests.LoggingCheckingTestBase;
import de.voomdoon.testing.tests.FileTestingUtil;
import de.voomdoon.util.kml.KmlUtil;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class CleanBrytonDataProgramTest extends LoggingCheckingTestBase {

	@Test
	void test() throws IOException {
		String fileName = getTempDirectory() + "/file.kml";
		FileTestingUtil.provideResourceAsFile("kml/Bryton.kml", fileName);

		CleanBrytonDataProgram.main(new String[] { fileName });

		CleanBrytonDataTest.assertPlacemark(KmlUtil.readKml(fileName));
	}
}
