package de.voomdoon.tool.map.kml.bryton;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.voomdoon.testing.tests.FileTestingUtil;
import de.voomdoon.util.kml.KmlUtil;
import lombok.experimental.UtilityClass;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since DOCME add inception version number
 */
@UtilityClass
public class CleanBrytonDataTestUtil {

	/**
	 * DOCME add JavaDoc for CleanBrytonDataProgramTest
	 *
	 * @author André Schulz
	 *
	 * @since DOCME add inception version number
	 */
	public interface BrytonCleanRunnable {

		/**
		 * DOCME add JavaDoc for method run
		 * 
		 * @param fileName
		 * @throws IOException
		 * @since DOCME add inception version number
		 */
		void run(String fileName) throws IOException;
	}

	/**
	 * DOCME add JavaDoc for method assertPlacemark
	 * 
	 * @param actual
	 * @since 0.1.0
	 */
	public static void assertPlacemark(Kml actual) {
		assertThat(actual.getFeature()).isInstanceOf(Document.class);
		Document document = (Document) actual.getFeature();
		assertThat(document.getFeature()).hasSize(1);
		assertThat(document.getFeature().get(0)).isInstanceOf(Placemark.class);
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @param runnable
	 * @param tempDirectory
	 * @throws IOException
	 * @since DOCME add inception version number
	 */
	public static void run(BrytonCleanRunnable runnable, Path tempDirectory) throws IOException {
		String fileName = tempDirectory + "/file.kml";
		FileTestingUtil.provideResourceAsFile("kml/Bryton/default.kml", fileName);

		runnable.run(fileName);

		assertPlacemark(KmlUtil.readKml(fileName));
	}
}
