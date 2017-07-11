package com;

import org.apache.sis.util.Static;

import ucar.nc2.grib.grib2.Grib2Pds.SatelliteBand;

public class Main {
public Main() {
}

public static void main(String[] xs){
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
