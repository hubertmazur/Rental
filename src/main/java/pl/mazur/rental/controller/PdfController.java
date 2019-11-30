package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.service.ReservationService;
import pl.mazur.rental.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class PdfController {

    private ReservationService reservationService;
    private ServletContext servletContext;
    private UserService userService;

    public PdfController(ReservationService reservationService, ServletContext servletContext, UserService userService) {
        this.reservationService = reservationService;
        this.servletContext = servletContext;
        this.userService = userService;
    }

    @GetMapping("createPdf/user/{userId}")
    public void generatePdf(@PathVariable long userId, HttpServletRequest request, HttpServletResponse response) {
        List<Reservation> reservationList = userService.findAllReservationByUserId(userId);
        boolean isFlag = reservationService.createPdf(reservationList, servletContext, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("resources/reports/" + "reservations" + ".pdf");
            fileDownload(fullPath, response, "reservations_userId_" + userId + ".pdf");
        }
    }

    private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = servletContext.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                ;
                outputStream.close();
                file.delete();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
