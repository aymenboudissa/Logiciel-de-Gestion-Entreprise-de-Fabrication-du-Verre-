package model;

import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Report {

public static void createReport(Connection connection,String path,Integer id,Double versement) {
	JasperDesign jdesign = null;
	try {
		jdesign = JRXmlLoader.load(path);
	} catch (JRException e1) {
		e1.printStackTrace();
	}

	Map<String, Object> params = new HashMap<String, Object>();
	params.put("condition1",id);
	params.put("versement",versement);
		try {
			JasperReport jreport=JasperCompileManager.compileReport(jdesign);
			JasperPrint jprint=JasperFillManager.fillReport(jreport,params,connection);
			JasperViewer.viewReport(jprint,false);
		} catch (JRException e) {
			e.printStackTrace();
		}
}


}
