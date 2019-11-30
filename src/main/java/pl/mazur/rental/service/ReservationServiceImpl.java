package pl.mazur.rental.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.model.User;
import pl.mazur.rental.repostiory.MachineGroupRepository;
import pl.mazur.rental.repostiory.ReservationRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private MachineGroupRepository machineGroupRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, MachineGroupRepository machineGroupRepository) {
        this.reservationRepository = reservationRepository;
        this.machineGroupRepository = machineGroupRepository;
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);

    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByMachineGroup(MachineGroup machineGroup) {
        return reservationRepository.findAllByMachineGroupList(machineGroup);
    }

    @Override
    public List<Reservation> findByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }


    @Override
    public Reservation findById(Long id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public boolean checkAvailability(Reservation reservation, Long idGroup) {
        Integer quantityToReservation = reservation.getQuantity();
        Integer amountOfAll = machineGroupRepository.getAmountsOfMachines(idGroup);
        Integer booked = reservationRepository.getCountReservedMachines(idGroup);
        return amountOfAll - booked - quantityToReservation >= 0;
    }

    @Override
    public List<MachineGroup> addGroupsMachine(MachineGroup machineGroup, int quantity) {
        List<MachineGroup> machineGroupList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            machineGroupList.add(machineGroup);
        }
        return machineGroupList;
    }

    @Override
    public boolean checkAvailabilityWhenUpdateReservation(Integer quantityToUpdate, Reservation reservation, Long idGroup) {
        Integer quantityToReservationBeforeUpdate = reservation.getQuantity();
        Integer amountOfAll = machineGroupRepository.getAmountsOfMachines(idGroup);
        Integer booked = reservationRepository.getCountReservedMachines(idGroup);
        return amountOfAll - booked - quantityToUpdate + quantityToReservationBeforeUpdate >= 0;
    }

    @Override
    public boolean createPdf(List<Reservation> reservationList, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        Reservation res = reservationList.get(0);
        try {
            String filePath = servletContext.getRealPath("/resources/reports");
            File file = new File(filePath);
            boolean exists = file.exists();
            if (!exists) {
                new File(filePath).mkdirs();
            }
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "reservations" + ".pdf"));
            document.open();
            Font mainFont = FontFactory.getFont("arial", 10, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Wszystkie rezerwacje - uzytkownik : " + res.getUser().getFirstName() + " " + res.getUser().getLastName(), mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable pdfPTable = new PdfPTable(5);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setSpacingBefore(10);
            pdfPTable.setSpacingAfter(10);
            Font tableHeader = FontFactory.getFont("arial", 10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("arial", 9, BaseColor.BLACK);
            float[] columnWidths = {2f, 2f, 2f, 2f, 2f};
            pdfPTable.setWidths(columnWidths);

            PdfPCell id = new PdfPCell(new Paragraph("Numer rezerwacji", tableHeader));
            id.setBorderColor(BaseColor.BLACK);
            id.setPaddingLeft(10);
            id.setHorizontalAlignment(Element.ALIGN_CENTER);
            id.setVerticalAlignment(Element.ALIGN_CENTER);
            id.setBackgroundColor(BaseColor.GRAY);
            id.setExtraParagraphSpace(5);
            pdfPTable.addCell(id);

            PdfPCell machineName = new PdfPCell(new Paragraph("Informacje o maszynie", tableHeader));
            machineName.setBorderColor(BaseColor.BLACK);
            machineName.setPaddingLeft(10);
            machineName.setHorizontalAlignment(Element.ALIGN_CENTER);
            machineName.setVerticalAlignment(Element.ALIGN_CENTER);
            machineName.setBackgroundColor(BaseColor.GRAY);
            machineName.setExtraParagraphSpace(5);
            pdfPTable.addCell(machineName);

            PdfPCell quantity = new PdfPCell(new Paragraph("Ilosc", tableHeader));
            quantity.setBorderColor(BaseColor.BLACK);
            quantity.setPaddingLeft(10);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setVerticalAlignment(Element.ALIGN_CENTER);
            quantity.setBackgroundColor(BaseColor.GRAY);
            quantity.setExtraParagraphSpace(5);
            pdfPTable.addCell(quantity);

            PdfPCell startRentDate = new PdfPCell(new Paragraph("Początek wynajmu", tableHeader));
            startRentDate.setBorderColor(BaseColor.BLACK);
            startRentDate.setPaddingLeft(10);
            startRentDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            startRentDate.setVerticalAlignment(Element.ALIGN_CENTER);
            startRentDate.setBackgroundColor(BaseColor.GRAY);
            startRentDate.setExtraParagraphSpace(5);
            pdfPTable.addCell(startRentDate);


            PdfPCell endRentDate = new PdfPCell(new Paragraph("Koniec wynajmu", tableHeader));
            endRentDate.setBorderColor(BaseColor.BLACK);
            endRentDate.setPaddingLeft(10);
            endRentDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            endRentDate.setVerticalAlignment(Element.ALIGN_CENTER);
            endRentDate.setBackgroundColor(BaseColor.GRAY);
            endRentDate.setExtraParagraphSpace(5);
            pdfPTable.addCell(endRentDate);

            for (Reservation reservation : reservationList) {

                PdfPCell IDValue = new PdfPCell(new Paragraph(String.valueOf(reservation.getIdReservation()), tableBody));
                IDValue.setBorderColor(BaseColor.BLACK);
                IDValue.setPaddingLeft(10);
                IDValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                IDValue.setVerticalAlignment(Element.ALIGN_CENTER);
                IDValue.setBackgroundColor(BaseColor.WHITE);
                IDValue.setExtraParagraphSpace(5);
                pdfPTable.addCell(IDValue);

                PdfPCell machineInfoValue = new PdfPCell(new Paragraph(String.valueOf(reservation.getMachineGroupList()), tableBody));
                machineInfoValue.setBorderColor(BaseColor.BLACK);
                machineInfoValue.setPaddingLeft(10);
                machineInfoValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                machineInfoValue.setVerticalAlignment(Element.ALIGN_CENTER);
                machineInfoValue.setBackgroundColor(BaseColor.WHITE);
                machineInfoValue.setExtraParagraphSpace(5);
                pdfPTable.addCell(machineInfoValue);

                PdfPCell quantityValue = new PdfPCell(new Paragraph(String.valueOf(reservation.getQuantity()), tableBody));
                quantityValue.setBorderColor(BaseColor.BLACK);
                quantityValue.setPaddingLeft(10);
                quantityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityValue.setVerticalAlignment(Element.ALIGN_CENTER);
                quantityValue.setBackgroundColor(BaseColor.WHITE);
                quantityValue.setExtraParagraphSpace(5);
                pdfPTable.addCell(quantityValue);

                PdfPCell startRentDateValue = new PdfPCell(new Paragraph(String.valueOf(reservation.getStartRentDate()), tableBody));
                startRentDateValue.setBorderColor(BaseColor.BLACK);
                startRentDateValue.setPaddingLeft(10);
                startRentDateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                startRentDateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                startRentDateValue.setBackgroundColor(BaseColor.WHITE);
                startRentDateValue.setExtraParagraphSpace(5);
                pdfPTable.addCell(startRentDateValue);

                PdfPCell endRentDateValue = new PdfPCell(new Paragraph(String.valueOf(reservation.getEndRentDate()), tableBody));
                endRentDateValue.setBorderColor(BaseColor.BLACK);
                endRentDateValue.setPaddingLeft(10);
                endRentDateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                endRentDateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                endRentDateValue.setBackgroundColor(BaseColor.WHITE);
                endRentDateValue.setExtraParagraphSpace(5);
                pdfPTable.addCell(endRentDateValue);

            }

            document.add(pdfPTable);
            document.close();
            writer.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }


}