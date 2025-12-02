package com.invoiceGeneration.util;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.InvoiceItem;


@Service
public class PdfGeneratorService {

    public byte[] generateInvoicePdf(Invoice invoice) {
        try {
            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();

            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 12);

            // ---------------- Header ----------------
            Paragraph title = new Paragraph("INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ", normalFont));

            // ---------------- From Section ----------------
            document.add(new Paragraph("From:", boldFont));
            document.add(new Paragraph(invoice.getUser().getCompanyName(), normalFont));
            document.add(new Paragraph(invoice.getUser().getUserName(), normalFont));
            document.add(new Paragraph(invoice.getUser().getEmail(), normalFont));
            document.add(new Paragraph(invoice.getUser().getPhone(), normalFont));
            document.add(new Paragraph(invoice.getUser().getAddress(), normalFont));

            document.add(new Paragraph(" ", normalFont));

            // ---------------- Bill To Section ----------------
            document.add(new Paragraph("Bill To:", boldFont));
            document.add(new Paragraph(invoice.getClient().getName(), normalFont));
            document.add(new Paragraph(invoice.getClient().getEmail(), normalFont));
            document.add(new Paragraph(invoice.getClient().getPhone(), normalFont));
            document.add(new Paragraph(invoice.getClient().getBillingAddress(), normalFont));

            document.add(new Paragraph(" ", normalFont));

            // ---------------- Invoice Metadata ----------------
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            PdfPTable metaTable = new PdfPTable(2);
            metaTable.setWidthPercentage(100);
            metaTable.setSpacingBefore(10);

            metaTable.addCell(getMetaCell("Invoice Number:", boldFont));
            metaTable.addCell(getMetaCell(invoice.getInvoiceNumber(), normalFont));

            metaTable.addCell(getMetaCell("Invoice Date:", boldFont));
            metaTable.addCell(getMetaCell(sdf.format(invoice.getInvoiceDate()), normalFont));

            metaTable.addCell(getMetaCell("Due Date:", boldFont));
            metaTable.addCell(getMetaCell(sdf.format(invoice.getDueDate()), normalFont));

            metaTable.addCell(getMetaCell("Status:", boldFont));
            metaTable.addCell(getMetaCell(invoice.getStatus(), normalFont));

            document.add(metaTable);

            document.add(new Paragraph(" ", normalFont));

            // ---------------- Items Table ----------------
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            table.addCell(getHeaderCell("Description"));
            table.addCell(getHeaderCell("Qty"));
            table.addCell(getHeaderCell("Price"));
            table.addCell(getHeaderCell("Total"));

            for (InvoiceItem item : invoice.getItems()) {
                table.addCell(getBodyCell(item.getDescription()));
                table.addCell(getBodyCell(item.getQuantity().toString()));
                table.addCell(getBodyCell(item.getPrice().toString()));
                table.addCell(getBodyCell(item.getTotal().toString()));
            }

            document.add(table);

            document.add(new Paragraph(" ", normalFont));

            // ---------------- Totals ----------------
            PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidthPercentage(40);
            totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

            totalTable.addCell(getMetaCell("Subtotal:", boldFont));
            totalTable.addCell(getMetaCell(invoice.getSubTotal().toString(), normalFont));

            totalTable.addCell(getMetaCell("Tax:", boldFont));
            totalTable.addCell(getMetaCell(invoice.getTax().toString(), normalFont));

            totalTable.addCell(getMetaCell("Total Amount:", boldFont));
            totalTable.addCell(getMetaCell(invoice.getTotalAmount().toString(), boldFont));

            document.add(totalTable);

            document.add(new Paragraph("\nThank you for your business!", boldFont));

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------ Helpers ------------
    private PdfPCell getHeaderCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, new Font(Font.HELVETICA, 12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        return cell;
    }

    private PdfPCell getBodyCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private PdfPCell getMetaCell(String value, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
}