package org.stackroute;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GeneratePdf {
    public static void main(String[] args) throws FileNotFoundException , IOException {
        String path = "Invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        float threecol = 190f;
        float twocol = 230f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {twocol150 , twocol};
        float fullwidth[] = {threecol * 3};
        float threeColumnWidth[] = {threecol , threecol , threecol};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("Invoice").setFontSize(18f).setBorder(Border.NO_BORDER).setBold());
        Table nestedTable = new Table(new float[]{twocol/2 , twocol/2});
        nestedTable.addCell(getHeaderTextCell("Invoice No :"));
        nestedTable.addCell(getHeaderTextCellValue("USHODAYA17010"));
        nestedTable.addCell(getHeaderTextCell("Invoice Date :"));
        nestedTable.addCell(getHeaderTextCellValue("11-January-2023"));
        nestedTable.addCell(getHeaderTextCell("GSTIN :"));
        nestedTable.addCell(getHeaderTextCellValue("12CUJPB8751J1Z3"));
        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
        Border border = new SolidBorder(Color.GRAY,2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(border);
        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Billing Information"));
        twoColTable.addCell(getBillingandShippingCell("Shipping Information"));
        document.add(twoColTable.setMarginBottom(10f));

        Table twoColTable2 = new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10fLeft("Company" , true));
        twoColTable2.addCell(getCell10fLeft("Name" , true));
        twoColTable2.addCell(getCell10fLeft("AMSoftGlobal" , false));
        twoColTable2.addCell(getCell10fLeft("B Manoj Kumar Reddy" , false));
        document.add(twoColTable2);

        Table twoColTable3 = new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10fLeft("Name" , true));
        twoColTable3.addCell(getCell10fLeft("Address" , true));
        twoColTable3.addCell(getCell10fLeft("Bhimavarapu Prasanthi" , false));
        twoColTable3.addCell(getCell10fLeft("3-551/4 , Tulasi Nilayam,\nAndhra Pradesh - 522501,\nIndia." , false));
        document.add(twoColTable3);

        float oneColumnWidth[] = {twocol150};

        Table oneColTable1 = new Table(oneColumnWidth);
        oneColTable1.addCell(getCell10fLeft("Address" , true));
        oneColTable1.addCell(getCell10fLeft("3-551/4 , Tulasi Nilayam,\nAndhra Pradesh - 522501,\nIndia." , false));
        oneColTable1.addCell(getCell10fLeft("Email" , true));
        oneColTable1.addCell(getCell10fLeft("manojbh1999@gmail.com" , false));
        oneColTable1.addCell(getCell10fLeft("Mobile Number" , true));
        oneColTable1.addCell(getCell10fLeft("+91 9010917345" , false));
        document.add(oneColTable1.setMarginBottom(10f));

        Table tableDivider2 = new Table(fullwidth);
        Border border1 = new DashedBorder(Color.GRAY , 1f);
        document.add(tableDivider2.setBorder(border1));

        Paragraph productParagraph = new Paragraph("Products");
        document.add(productParagraph.setBold());

        Table threeColumnTable1 = new Table(threeColumnWidth);
        threeColumnTable1.setBackgroundColor(Color.BLACK , 0.7f);
        threeColumnTable1.addCell(new Cell().add("Description").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        threeColumnTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColumnTable1.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        document.add(threeColumnTable1);

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products("Apple" , 5 , 50));
        productsList.add(new Products("Banana" , 15 , 15));
        productsList.add(new Products("Kiwi" , 10 , 45));
        productsList.add(new Products("Mango" , 30 , 50));
        productsList.add(new Products("Guava" , 15 , 10));
        productsList.add(new Products("Coconut" , 2 , 20));
        productsList.add(new Products("Grapes" , 3 , 55));

        Table threeColTable2 = new Table(threeColumnWidth);

        float totalSum=0f;
        for (Products products : productsList) {
            float total = products.getQuantity()*products.getPricePerPiece();
            totalSum += total;
            threeColTable2.addCell(new Cell().add(products.getProductName()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
            threeColTable2.addCell(new Cell().add(String.valueOf(products.getQuantity())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColTable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        }
        document.add(threeColTable2.setMarginBottom(20f));

        float totalDashedLine[] = {threecol+125f , threecol*2};
        Table threeColTable4 = new Table(totalDashedLine);
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
        document.add(threeColTable4);

        Table threeColTable3 = new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
        threeColTable3.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable3.addCell(new Cell().add(String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(new Paragraph("\n"));
        document.add(divider.setBorder(new SolidBorder(Color.GRAY , 1)).setMarginBottom(15f));

        Table table1 = new Table(fullwidth);
        table1.addCell(new Cell().add("TERMS AND CONDITIONS\n").setFontSize(10f).setBold().setBorder(Border.NO_BORDER));
        List<String> tncList = new ArrayList<>();
        tncList.add("1. The seller shall not be liable to the Buyer directly or indirectly for any loss or damage suffered by the Buyer.");
        tncList.add("2. The seller warrants the product for one (1) year from the Date of Shipment.");

        for (String tnc:tncList) {
            table1.addCell(new Cell().add(tnc).setFontSize(10f).setBorder(Border.NO_BORDER));
        }

        document.add(table1);

        document.close();
        System.out.println("PDF Generated");

    }

    static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getBillingandShippingCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue , Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ?myCell.setBold():myCell;
    }

}
