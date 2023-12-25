package com.example.qrcodedemo.utils;




import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.example.qrcodedemo.model.Order;
import com.example.qrcodedemo.model.TableDetails;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {
	
	public static void generateQRCode(TableDetails table) throws WriterException, IOException {
		String qrCodePath = "F:\\Modoo\\Springboot-Microservices\\Projects\\qrcode-demo\\QRCode\\";
		String qrCodeName = qrCodePath +
				 "Table_" + table.getId() +
				"-QRCode.png";
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		if(table.getOrders().size() > 0) {
			Order order = table.getOrders().get(table.getOrders().size() -1);
			BitMatrix 	bitMatrix = qrCodeWriter.encode("Table_ID: " + table.getId() +
									"\n" + order.toString()+
									"\nTotal_Price: " + table.getTotalPrice(),
									BarcodeFormat.QR_CODE, 200, 200);
			Path path = FileSystems.getDefault().getPath(qrCodeName);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		}
	}

}
