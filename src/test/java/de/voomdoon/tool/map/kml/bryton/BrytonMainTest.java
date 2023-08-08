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
 * @since DOCME add inception version number
 */
class BrytonMainTest extends LoggingCheckingTestBase {

	/**
	 * DOCME add JavaDoc for method testClean
	 * 
	 * @throws IOException
	 * 
	 * @since DOCME add inception version number
	 */
	@Test
	void test_clean() throws IOException {
		logTestStart();

		String fileName = getTempDirectory() + "/file.kml";
		FileTestingUtil.provideResourceAsFile("kml/Bryton.kml", fileName);

		BrytonMain.main(new String[] { "clean", fileName });

		CleanBrytonDataTestUtil.assertPlacemark(KmlUtil.readKml(fileName));
	}
}
