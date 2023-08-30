package de.voomdoon.tool.map.kml.bryton;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
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
class CleanBrytonDataTest extends LoggingCheckingTestBase {

	/**
	 * DOCME add JavaDoc for method assumePlacemark
	 * 
	 * @param actual
	 * @return
	 * @since 0.1.0
	 */
	private static Placemark assumePlacemark(Kml actual) {
		try {
			CleanBrytonDataTestUtil.assertPlacemark(actual);
		} catch (AssertionError e) {
			Assumptions.abort(e.getMessage());
		}

		return (Placemark) ((Document) actual.getFeature()).getFeature().get(0);
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_LookAt() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual).extracting(Document::getAbstractView).isNull();
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_name() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual).extracting(Document::getName).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_snippet() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual).extracting(Document::getSnippetd).isNull();
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_StyleSelector() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual.getStyleSelector()).hasSize(1);
		assertThat(actual.getStyleSelector().get(0).getId()).isEqualTo("default-path");
	}

	/**
	 * @since DOCME add inception version number
	 */
	@Test
	void test_ignoreDone() throws Exception {
		logTestStart();

		run("kml/Bryton/default.kml");
		String fileName = getTempDirectory() + "/file.kml";

		CleanBrytonData.run(fileName);

		Kml kml = KmlUtil.readKml(fileName);
		Placemark actual = assumePlacemark(kml);

		assertThat(actual).extracting(Placemark::getName).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * DOCME add JavaDoc for method test_name_startTime
	 * 
	 * @throws IOException
	 * 
	 * @since 0.1.0
	 */
	@Test
	void test_noFolders() throws IOException {
		logTestStart();

		Kml actual = run("kml/Bryton/default.kml");

		CleanBrytonDataTestUtil.assertPlacemark(actual);
	}

	/**
	 * DOCME add JavaDoc for method test_Placemark_name
	 * 
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Placemark_name() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual).extracting(Placemark::getName).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * DOCME add JavaDoc for method test_Placemark_name
	 * 
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Placemark_style() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual.getStyleUrl()).isEqualTo("#default-path");
	}

	/**
	 * DOCME add JavaDoc for method test_placememark
	 * 
	 * @throws IOException
	 * 
	 * @since 0.1.0
	 */
	@Test
	void test_Placemark_TimeSpan() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton/default.kml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual.getTimePrimitive()).isInstanceOf(TimeSpan.class);
		TimeSpan timeSpan = (TimeSpan) actual.getTimePrimitive();
		assertThat(timeSpan.getBegin()).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * @since DOCME add inception version number
	 */
	@Test
	void test_withWaypointsFolder() throws Exception {
		logTestStart();

		Kml kml = run("kml/Bryton/Folder_Waypoints.kml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual).extracting(Placemark::getName).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @since 0.1.0
	 */
	private Kml run(String file) throws IOException {
		String fileName = getTempDirectory() + "/file.kml";

		FileTestingUtil.provideResourceAsFile(file, fileName);

		CleanBrytonData.run(fileName);

		try {
			return KmlUtil.readKml(fileName);
		} catch (IOException e) {
			// TODO implement error handling
			throw new RuntimeException("Error at 'run': " + e.getMessage(), e);
		}
	}
}
