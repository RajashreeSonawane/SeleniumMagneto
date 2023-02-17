package com.utility;

import java.util.ArrayList;

import com.excel.Utility.Xls_Reader;

public class ExcelUtil {
	static Xls_Reader reader;

	public static ArrayList<Object[]> getDataFromExcel() {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader(
					"src\\test\\java\\ExcelData\\data .xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String email = reader.getCellData("MagnetoData", "email", 2);
		String firstname = reader.getCellData("MagnetoData", "firstname", 2);
		String lastname = reader.getCellData("MagnetoData", "lastname", 2);
		String company = reader.getCellData("MagnetoData", "company", 2);
		String street_address = reader.getCellData("MagnetoData", "street_address", 2);
		String city = reader.getCellData("MagnetoData", "city", 2);
		String postal_code = reader.getCellData("MagnetoData", "postal_code", 2);
		String phone = reader.getCellData("MagnetoData", "phone", 2);
		Object ob[] = { email, firstname, lastname, company, street_address, city, postal_code, phone };
		myData.add(ob);
		return myData;
	}

}