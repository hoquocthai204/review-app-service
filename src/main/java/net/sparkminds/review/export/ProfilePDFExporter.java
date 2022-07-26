package net.sparkminds.review.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.entity.Project;

@AllArgsConstructor
@RequiredArgsConstructor
public class ProfilePDFExporter {

    private List<Profile> listProfile;
    private List<Project> listProject;

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Image", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Github User", font));
        table.addCell(cell);

//        ~~~~~~~~~~~~~~~~~~~~~~~~~

        cell.setPhrase(new Phrase("Project name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Employment mode", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capacity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Duration in months", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Start year", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Role", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Team size", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Link Repo", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Link live", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) throws BadElementException, IOException {
        for (Profile profile : listProfile) {
            for (Project project : listProject) {
                if (profile.getId() == project.getProfile().getId()) {
                    String imgLink = "https://avatars.githubusercontent.com/" + profile.getGithubUser();
                    Image img = Image.getInstance(imgLink);
                    table.addCell(profile.getId().toString());
                    table.addCell(img);
                    table.addCell(profile.getName());
                    table.addCell(profile.getEmailAddress());
                    table.addCell(profile.getGithubUser().toString());

                    table.addCell(project.getName());
                    table.addCell(project.getEmploymentMode().toString());
                    table.addCell(project.getCapacity().toString());
                    table.addCell(project.getDurationInMonths());
                    table.addCell(project.getStartYear());
                    table.addCell(project.getRole());
                    table.addCell(project.getTeamSize().toString());
                    table.addCell(project.getLinkRepo());
                    table.addCell(project.getLinkLive());
                }
            }
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Profiles", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(14);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f });
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
