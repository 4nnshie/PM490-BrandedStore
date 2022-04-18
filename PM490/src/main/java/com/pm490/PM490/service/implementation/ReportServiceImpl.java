package com.pm490.PM490.service.implementation;

import com.pm490.PM490.dto.EmailDto;
import com.pm490.PM490.model.Transaction;
import com.pm490.PM490.repository.TransactionRepository;
import com.pm490.PM490.service.EmailService;
import com.pm490.PM490.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Value("${spring.env.abc.email}")
    private String abcemail;

    @Autowired
    TransactionRepository transactionRepository;

    private JasperPrint getJasperPrint(List<Transaction> phoneCollection, String resourceLocation) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);
        JasperReport jasperReport = JasperCompileManager
                .compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new
                JRBeanCollectionDataSource(phoneCollection);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Sagi");

        JasperPrint jasperPrint = JasperFillManager
                .fillReport(jasperReport,parameters,dataSource);

        return jasperPrint;
    }

    private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String uploadDir = StringUtils.cleanPath("./generated-reports");
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        //generate the report and save it in the just created folder
        if (fileFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, uploadPath+fileName
            );
        }

        return uploadPath;
    }

    private String getPdfFileLink(String uploadPath){
        return uploadPath+"/"+"transaction.pdf";
    }

    @Override
    public String generateReport(LocalDate localDate, String fileFormat) throws JRException, IOException {
        List<Transaction> transactions = transactionRepository.findAll();

        //load the file and compile it
        String resourceLocation = "classpath:jasper_template.jrxml";
        JasperPrint jasperPrint = getJasperPrint(transactions, resourceLocation);

        //create a folder to store the report
        String fileName = "/"+"transaction.pdf";
        Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);
        //create a private method that returns the link to the specific pdf file
        String fileLinke = getPdfFileLink(uploadPath.toString());
        sendEmail("test@gmail.com", fileLinke);
        return fileLinke;
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    private void sendEmail(String toAddress, String fileLinke) {
        EmailDto email = new EmailDto();
        email.setFromAddress(abcemail);
        email.setToAddress(toAddress);
        email.setMailSubject("ABC montly report");
        email.setBodyText("Hi there");
        email.setAttachFileAddress("./"+fileLinke);
        EmailService emailService = new EmailServiceImpl();
        emailService.sendEmail(email);
    }
}
