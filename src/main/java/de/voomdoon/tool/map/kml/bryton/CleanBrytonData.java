package de.voomdoon.tool.map.kml.bryton;

import java.io.IOException;
import java.util.Collections;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineStyle;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
import de.voomdoon.util.kml.KmlStyleUtil;
import de.voomdoon.util.kml.KmlUtil;
import lombok.experimental.UtilityClass;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
@UtilityClass
public class CleanBrytonData {

	/**
	 * @since 0.1.0
	 */
	private static final Style DEFAULT_PATH_STYLE;

	static {
		DEFAULT_PATH_STYLE = new Style();
		DEFAULT_PATH_STYLE.setId("default-path");
		LineStyle lineStyle = new LineStyle();
		lineStyle.setWidth(3);
		lineStyle.setColor("ffffffff");
		DEFAULT_PATH_STYLE.setLineStyle(lineStyle);
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @param fileName
	 * @throws IOException
	 * @since 0.1.0
	 */
	public static void run(String fileName) throws IOException {
		Kml kml = KmlUtil.readKml(fileName);

		run(kml);

		KmlUtil.writeKml(kml, fileName);
	}

	/**
	 * DOCME add JavaDoc for method getBrytonPlacemark
	 * 
	 * @param kml
	 * @return
	 * @since 0.1.0
	 */
	private static Placemark getBrytonPlacemark(Kml kml) {
		return (Placemark) ((Folder) ((Folder) ((Document) kml.getFeature()).getFeature().get(0)).getFeature().get(0))
				.getFeature().get(0);
	}

	/**
	 * DOCME add JavaDoc for method improveBrytonData
	 * 
	 * @param kml
	 * @param placemark
	 * @param folder
	 * @since 0.1.0
	 */
	private static void improveBrytonData(Kml kml, Placemark placemark, Folder folder) {
		Document document = (Document) kml.getFeature();

		updateDocument(document, placemark);
		updatePlacemark(kml, placemark, folder);
		document.setName(placemark.getName());
		removeFolders(kml);
		setStyle(placemark, document);
	}

	/**
	 * 
	 * DOCME add JavaDoc for method moveTimeSpan
	 * 
	 * @param placemark
	 * @param folder
	 * @since 0.1.0
	 */
	private static void moveTimeSpan(Placemark placemark, Folder folder) {
		placemark.setTimePrimitive(folder.getTimePrimitive());
	}

	/**
	 * DOCME add JavaDoc for method removeFolders
	 * 
	 * @param kml
	 * @since 0.1.0
	 */
	private static void removeFolders(Kml kml) {
		Document document = (Document) kml.getFeature();
		Placemark placemark = (Placemark) ((Folder) ((Folder) document.getFeature().get(0)).getFeature().get(0))
				.getFeature().get(0);
		document.setFeature(Collections.singletonList(placemark));
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @param kml
	 * @since 0.1.0
	 */
	private static void run(Kml kml) {
		Folder folder = (Folder) ((Folder) ((Document) kml.getFeature()).getFeature().get(0)).getFeature().get(0);
		Placemark placemark = getBrytonPlacemark(kml);

		improveBrytonData(kml, placemark, folder);
	}

	/**
	 * DOCME add JavaDoc for method setName
	 * 
	 * @param kml
	 * 
	 * @param placemark
	 * @param folder
	 * @since 0.1.0
	 */
	private static void setName(Kml kml, Placemark placemark, Folder folder) {
		placemark.setName(((TimeSpan) folder.getTimePrimitive()).getBegin());
	}

	/**
	 * DOCME add JavaDoc for method setStyle
	 * 
	 * @param placemark
	 * @param document
	 * @since 0.1.0
	 */
	private static void setStyle(Placemark placemark, Document document) {
		KmlStyleUtil.setStyleUrl(placemark, DEFAULT_PATH_STYLE, document);
	}

	/**
	 * DOCME add JavaDoc for method updateDocument
	 * 
	 * @param document
	 * @param placemark
	 * @since 0.1.0
	 */
	private static void updateDocument(Document document, Placemark placemark) {
		document.setSnippetd(null);
		document.setAbstractView(null);
		document.setStyleSelector(null);
	}

	private static void updatePlacemark(Kml kml, Placemark placemark, Folder folder) {
		moveTimeSpan(placemark, folder);
		setName(kml, placemark, folder);
		placemark.setStyleUrl(null);
	}
}
