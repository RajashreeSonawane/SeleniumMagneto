package com.utility;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.excel.Utility.Xls_Reader;

public class ExcelUtil {
	static Xls_Reader reader;
	static Logger log = Logger.getLogger(ExcelUtil.class);

	public static ArrayList<Object[]> getDataFromExcel() {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader("src\\test\\java\\ExcelData\\Magnetodata.xlsx");
		} catch (Exception e) {
			log.error("Ops!", e);
		}
		for (int rowNum = 2; rowNum <= reader.getRowCount("MagnetoData"); rowNum++) {
			String email = reader.getCellData("MagnetoData", "email", rowNum);
			String firstname = reader.getCellData("MagnetoData", "firstname", rowNum);
			String lastname = reader.getCellData("MagnetoData", "lastname", rowNum);
			String company = reader.getCellData("MagnetoData", "company", rowNum);
			String street_address = reader.getCellData("MagnetoData", "street_address", rowNum);
			String city = reader.getCellData("MagnetoData", "city", rowNum);
			String postal_code = reader.getCellData("MagnetoData", "postal_code", rowNum);
			String phone = reader.getCellData("MagnetoData", "phone", rowNum);
			Object ob[] = { email, firstname, lastname, company, street_address, city, postal_code, phone };
			myData.add(ob);
		}
		return myData;
	}

}