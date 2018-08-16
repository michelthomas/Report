package br.com.cpsoftware;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class PdfServiceImpl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		this.generatePDF(response.getOutputStream());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	
	public void generatePDF(OutputStream out){
		
	    System.out.println(objectToJson(createDummyData()));
		
		try {
			
			/*Document document = new Document();
			document.setMargins(40, 40, 15, 10);
			PdfWriter.getInstance(document, out);*/
			
			//Initialize PDF writer
	        PdfWriter writer = new PdfWriter(out);
	 
	        //Initialize PDF document
	        PdfDocument pdf = new PdfDocument(writer);
	 
	        // Initialize document
	        Document document = new Document(pdf);
	        
	      //Add paragraph to the document
			Paragraph p = new Paragraph("Projeto: " + "Edificio XXX")
								.setFontSize(24f)
								.setTextAlignment(TextAlignment.CENTER);
			
			document.add(p);
			
			process(document, (JsonArray) objectToJson(createDummyData()));
	        /*// Create a PdfFont
	        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
	        // Add a Paragraph
	        document.add(new Paragraph("iText is:").setFont(font));
	        // Create a List
	        List list = new List()
	            .setSymbolIndent(12)
	            .setListSymbol("\u2022")
	            .setFont(font);
	        // Add ListItem objects
	        list.add(new ListItem("Never gonna give you up"))
	            .add(new ListItem("Never gonna let you down"))
	            .add(new ListItem("Never gonna run around and desert you"))
	            .add(new ListItem("Never gonna make you cry"))
	            .add(new ListItem("Never gonna say goodbye"))
	            .add(new ListItem("Never gonna tell a lie and hurt you"));
	        // Add the list
	        document.add(list);
	        
	     // Creating a table       
	        float [] pointColumnWidths = {1f, 200f, 1f};   
	        Table table = new Table(pointColumnWidths);    
	        
	        // Adding cells to the table       
	        table.addCell(new Cell().add("Name"));       
	        table.addCell(new Cell().add("Raju"));       
	        table.addCell(new Cell().add("Id"));       
	        table.addCell(new Cell().add("1001"));       
	        table.addCell(new Cell().add("Designation"));       
	        table.addCell(new Cell().add("Programmer"));                 
	           
	        // Adding Table to document        
	        document.add(table);*/                  
	        
	        //Close document
	        document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private java.util.List<Usuario> createDummyData() {
		java.util.List<Usuario> usuarios = new ArrayList<>();
		
		usuarios.add(new Usuario(1l, "Michael", 12345d));
		usuarios.add(new Usuario(2l, "Jailson", 56789d));
		usuarios.add(new Usuario(3l, "Flavio", 148602d));
		
		return usuarios;
	}
	
	private JsonElement objectToJson(java.util.List<Usuario> usuarios) {
		Gson gson = new Gson();
	    
	    return gson.toJsonTree(usuarios);
	}

	private void addHello(Document document, String message) {
		//Add paragraph to the document
		Paragraph p = new Paragraph(message);
		document.add(p);
	}
	
	public static void process(Document document, JsonObject json){
		for (String k : json.keySet()) {
			Object object = json.get(k);
			if (object instanceof JsonArray) {
				JsonArray list = json.getAsJsonArray(k);
				process(document, list);
			} else if (object instanceof JsonObject) {
				process(document, json.getAsJsonObject(k));
			} else {
				document.add(new Paragraph(k + " " + json.get(k)));
			}

		}
	}

	public static void process(Document document, JsonArray json){
		for (int x = 0; x < json.size(); x++) {
			Object object = json.get(x);
			if (object instanceof JsonArray) {
				JsonArray list = json.getAsJsonArray();
				process(document, list);
			} else if (object instanceof JsonObject) {
				process(document, json.getAsJsonObject());
			} else {
				document.add(new Paragraph(json.get(x).toString()));
			}

		}
	}
}

/*
 * document.open();

			Font bigBoldFont = FontFactory.getFont("Arial", "Cp1250", 12.0f, Font.BOLD);
			
			Paragraph p = new Paragraph();
			p.setAlignment(Element.ALIGN_CENTER);
			
			Phrase boldPhrase = new Phrase();
			boldPhrase = new Phrase();
			boldPhrase.setFont(bigBoldFont);
			boldPhrase.add("RELAÇÃO DE ITENS\n");
			p.add(boldPhrase);
			document.add(p);
			
			bigBoldFont = FontFactory.getFont("Arial", "Cp1250", 8.0f);
			
			p = new Paragraph();
			p.setAlignment(Element.ALIGN_CENTER);
			boldPhrase = new Phrase();
			boldPhrase.setFont(bigBoldFont);
			boldPhrase.add("\n");
			p.add(boldPhrase);
			document.add(p);
			
			p = new Paragraph();
			p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
			
	        Font boldFont = FontFactory.getFont("Arial", "Cp1250", 8.0f, Font.BOLD, BaseColor.WHITE);
			
	        Font normalFont = FontFactory.getFont("Arial", "Cp1250", 8.0f);
	       
	        PdfPTable table = null;
	        PdfPCell cell = null;
			
			table = new PdfPTable(2);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        cell = new PdfPCell(new Phrase("______________________________"));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("______________________________"));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(""));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(""));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Turismo", normalFont));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Flávio Medeiros", boldFont));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(""));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(""));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			
			document.add(table);
 * */
