package rsi.pie.project.domain.ticket;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketServiceImpl implements TicketService {
    private final @NonNull TicketRepository repository;

    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    @Override
    public Ticket findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public byte[] getTicketConfirmation(String code) throws Exception {
        Ticket ticket = findByCode(code);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf"));
        document.open();
        addMetaData(document);
        addTitlePage(document, ticket);
        document.close();
        return IOUtils.toByteArray(new FileInputStream("ticket.pdf"));
    }

    private static void addMetaData(Document document) {
        document.addTitle("Potwierdzenie odebrania biletu");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("dargenn");
        document.addCreator("dargenn");
    }

    private void addTitlePage(Document document, Ticket ticket)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Potwierdzenie odebrania biletu.", catFont));
        addEmptyLine(preface, 3);

        preface.add(new Paragraph("Kod biletu: " + ticket.getCode(), smallBold));
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Lot z: " + ticket.getFlight().getFrom().getName(), smallBold));
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Lot do: " + ticket.getFlight().getTo().getName(), smallBold));
        addEmptyLine(preface, 1);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        preface.add(new Paragraph("Data: " + dateFormat.format(ticket.getFlight().getDepartureDate()), smallBold));
        addEmptyLine(preface, 1);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        preface.add(new Paragraph("Godzina: " + timeFormat.format(ticket.getFlight().getDepartureHour()), smallBold));
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Pasazer: " + ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getLastName(), smallBold));
        addEmptyLine(preface, 1);

        document.add(preface);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
