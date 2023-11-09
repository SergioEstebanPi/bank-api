package com.challenge.bankapi.util;

import com.challenge.bankapi.dto.ReporteDTO;
import com.challenge.bankapi.service.ReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

public class PDFGenerator {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    public String generate(List<ReporteDTO> datos) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);

        document.open();
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(16);

        Paragraph paragraph = new Paragraph("Reporte de movimientos del cliente", fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 3, 3, 3, 3, 3, 3, 3, 3 });
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("Fecha", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("NumeroCuenta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("SaldoInicial", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Estado", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Movimiento", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Saldo", font));
        table.addCell(cell);

        for (ReporteDTO reporte : datos) {
            table.addCell(String.valueOf(reporte.getFechaMovimiento()));
            table.addCell(reporte.getNombreCliente());
            table.addCell(reporte.getNumeroCuenta());
            table.addCell(reporte.getTipoCuenta());
            table.addCell(String.valueOf(reporte.getSaldoInicial()));
            table.addCell(String.valueOf(reporte.getEstado()));
            table.addCell(String.valueOf(reporte.getValorMovimiento()));
            table.addCell(String.valueOf(reporte.getSaldoMovimiento()));
        }

        document.add(table);
        document.close();
        return convertirBase64Str(out);
    }

    private String convertirBase64Str(ByteArrayOutputStream out) {
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }
}
