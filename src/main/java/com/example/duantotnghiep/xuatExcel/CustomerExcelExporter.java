package com.example.duantotnghiep.xuatExcel;

import com.example.duantotnghiep.model.Customer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerExcelExporter {

    public ByteArrayInputStream exportCustomersToExcel(List<Customer> customers) throws IOException {
        String[] columns = {
                "ID", "Mã KH", "Tên KH", "Email", "SĐT", "Giới tính", "Ngày sinh",
                "Quốc gia", "Tỉnh", "Quận", "Phường", "Địa chỉ", "Trạng thái"
        };

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Danh sách khách hàng");

            // Tạo header
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Format ngày tháng nếu có
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Ghi dữ liệu
            int rowIdx = 1;
            for (Customer c : customers) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(c.getId());
                row.createCell(1).setCellValue(c.getCustomerCode());
                row.createCell(2).setCellValue(c.getCustomerName());
                row.createCell(3).setCellValue(c.getEmail());
                row.createCell(4).setCellValue(c.getPhone());

                row.createCell(5).setCellValue(
                        Boolean.TRUE.equals(c.getGender()) ? "Nam" :
                                Boolean.FALSE.equals(c.getGender()) ? "Nữ" : ""
                );

                row.createCell(6).setCellValue(
                        c.getDateOfBirth() != null ? c.getDateOfBirth().format(formatter) : ""
                );

                row.createCell(7).setCellValue(c.getCountry());
                row.createCell(8).setCellValue(c.getProvince());
                row.createCell(9).setCellValue(c.getDistrict());
                row.createCell(10).setCellValue(c.getWard());
                row.createCell(11).setCellValue(c.getHouseName());

                row.createCell(12).setCellValue(
                        c.getStatus() != null && c.getStatus() == 1 ? "Hoạt động" :
                                c.getStatus() != null && c.getStatus() == 0 ? "Ngưng hoạt động" : ""
                );
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
