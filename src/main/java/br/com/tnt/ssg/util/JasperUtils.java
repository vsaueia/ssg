package br.com.tnt.ssg.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.log4j.Logger;

public class JasperUtils {

	private static final Logger log = Logger.getLogger(JasperUtils.class);

	/**
	 * Generate jasper print.
	 * 
	 * @param dados
	 *            the dados
	 * @param params
	 *            the params
	 * @param isReport
	 *            the is report
	 * 
	 * @return the jasper print
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static JasperPrint generateJasperPrint(List<?> dados,
			Map<String, Object> params, InputStream isReport) throws Exception {
		JasperReport jasperReport = JasperCompileManager
				.compileReport(isReport);
		JRDataSource ds = new JRBeanCollectionDataSource(dados);
		return JasperFillManager.fillReport(jasperReport, params, ds);
	}

	/**
	 * Generate jasper print.
	 * 
	 * @param params
	 *            the params
	 * @param isReport
	 *            the is report
	 * 
	 * @return the jasper print
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static JasperPrint generateJasperPrint(Map<String, Object> params,
			InputStream isReport) throws Exception {
		JasperReport jasperReport = JasperCompileManager
				.compileReport(isReport);
		return JasperFillManager.fillReport(jasperReport, params);
	}

	/**
	 * Export csv.
	 * 
	 * @param print
	 *            the print
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JRException
	 *             the JR exception
	 */
	public static byte[] exportCsv(JasperPrint print) throws IOException,
			JRException {

		log.info("Exportando csv...");

		ByteArrayOutputStream csvReport = new ByteArrayOutputStream();
		JRCsvExporter exporterCsv = new JRCsvExporter();

		exporterCsv.getParameters().put(JRExporterParameter.CHARACTER_ENCODING,
				"ISO-8859-1");
		exporterCsv.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
		exporterCsv.setParameter(JRCsvExporterParameter.OUTPUT_STREAM,
				csvReport);
		exporterCsv.exportReport();

		byte[] bytes = csvReport.toByteArray();
		csvReport.close();

		log.info("Exportado.");

		return bytes;
	}

	/**
	 * Exibir csv.
	 * 
	 * @param faces
	 *            the faces
	 * @param bytes
	 *            the bytes
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void exibirCsv(FacesContext faces, byte[] bytes,
			String fileName) throws IOException {
		HttpServletResponse response = (HttpServletResponse) faces
				.getExternalContext().getResponse();
		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName + ".csv");

		response.getOutputStream().write(bytes);
		faces.responseComplete();
	}

	/**
	 * Export xls.
	 * 
	 * @param print
	 *            the print
	 * @return the byte[]
	 * @throws JRException
	 *             the JR exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] exportXls(JasperPrint print) throws JRException,
			IOException {
		if (print == null) {
			return null;
		}

		JRXlsExporter exporterXLS = new JRXlsExporter();
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		byte[] bytes = null;

		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
				xlsReport);

		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
				Boolean.TRUE);

		Map<String, String> numberFormats = new HashMap<String, String>();
		numberFormats.put("$ #,##0.00", "\"R$\" #,##0.00");
		exporterXLS.setParameter(JRXlsExporterParameter.FORMAT_PATTERNS_MAP,
				numberFormats);

		exporterXLS.exportReport();

		// converte para bytes
		bytes = xlsReport.toByteArray();
		xlsReport.close();
		return bytes;
	}

	/**
	 * Exibir xls.
	 * 
	 * @param faces
	 *            the faces
	 * @param bytes
	 *            the bytes
	 * @param reportName
	 *            the report name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void exibirXls(FacesContext faces, byte[] bytes,
			String reportName) throws IOException {
		HttpServletResponse response = (HttpServletResponse) faces
				.getExternalContext().getResponse();
		response.setContentType("application/download");
		response.setContentLength(bytes.length);
		response.setHeader("Content-disposition", "attachment; filename=\""
				+ reportName + ".xls\"");
		response.getOutputStream().write(bytes);
		faces.responseComplete();
	}

	/**
	 * Export html.
	 * 
	 * @param print
	 *            the print
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JRException
	 *             the JR exception
	 */
	public static byte[] exportHtml(JasperPrint print) throws IOException,
			JRException {
		if (print == null) {
			return null;
		}

		ByteArrayOutputStream htmlReport = new ByteArrayOutputStream();

		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

		Map<Object, Object> imagesMap = new HashMap<>();
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				"image?image=");
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
				"ISO-8859-1");
		exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, htmlReport);

		exporter.exportReport();

		byte[] bytes = htmlReport.toByteArray();
		htmlReport.close();

		return bytes;
	}

	/**
	 * Exibir html.
	 * 
	 * @param faces
	 *            the faces
	 * @param bytes
	 *            the bytes
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void exibirHtml(FacesContext faces, byte[] bytes)
			throws IOException {
		HttpServletResponse response = (HttpServletResponse) faces
				.getExternalContext().getResponse();

		response.setContentType("text/html");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		faces.responseComplete();
	}

	/**
	 * Export pdf.
	 * 
	 * @param print
	 *            the print
	 * @return the byte[]
	 * @throws JRException
	 *             the JR exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] exportPdf(JasperPrint print) throws JRException,
			IOException {
		if (print == null) {
			return null;
		}
		JRPdfExporter exporterPdf = new JRPdfExporter();
		ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
		byte[] bytes = null;

		exporterPdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
		exporterPdf.setParameter(JRPdfExporterParameter.OUTPUT_STREAM,
				pdfReport);

		exporterPdf.exportReport();

		// converte para bytes
		bytes = pdfReport.toByteArray();
		pdfReport.close();
		return bytes;
	}

	/**
	 * Exibir pdf.
	 * 
	 * @param faces
	 *            the faces
	 * @param bytes
	 *            the bytes
	 * @param reportName
	 *            the report name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void exibirPdf(FacesContext faces, byte[] bytes,
			String reportName) throws IOException {
		HttpServletResponse response = (HttpServletResponse) faces
				.getExternalContext().getResponse();
		response.setContentType("application/download");
		response.setContentLength(bytes.length);
		response.setHeader("Content-disposition", "attachment; filename=\""
				+ reportName + ".pdf\"");
		response.getOutputStream().write(bytes);
		faces.responseComplete();
	}

	/**
	 * Export rtf.
	 * 
	 * @param print
	 * @return
	 * @throws IOException
	 * @throws JRException
	 */
	public static byte[] exportRtf(JasperPrint print) throws IOException,
			JRException {
		log.info("Exportando rtf...");

		ByteArrayOutputStream docReport = new ByteArrayOutputStream();
		JRRtfExporter exporter = new JRRtfExporter();

		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
				"ISO-8859-1");
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, docReport);
		exporter.exportReport();

		byte[] bytes = docReport.toByteArray();
		docReport.close();

		log.info("Exportado.");

		return bytes;
	}

	/**
	 * Exibir rtf.
	 * 
	 * @param faces
	 * @param bytes
	 * @param reportName
	 * @throws IOException
	 */
	public static void exibirRtf(FacesContext faces, byte[] bytes,
			String reportName) throws IOException {
		HttpServletResponse response = (HttpServletResponse) faces
				.getExternalContext().getResponse();
		response.setContentType("application/download");
		response.setContentLength(bytes.length);
		response.setHeader("Content-disposition", "attachment; filename=\""
				+ reportName + ".rtf\"");
		response.getOutputStream().write(bytes);
		faces.responseComplete();
	}

}