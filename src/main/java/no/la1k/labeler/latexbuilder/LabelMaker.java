package no.la1k.labeler.latexbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import no.la1k.adif.ADIFException;
import no.la1k.adif.ADIFReader;
import no.la1k.adif.ADIFRecord;
import no.la1k.labeler.Label;
import no.la1k.labeler.LabelGenerator;

public class LabelMaker {
	
	private String buildConfpin(Label l) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\confpin{");
		sb.append(l.getCall().replaceAll("0", "Ø"));
		sb.append("}");
		sb.append("{");
		sb.append(l.getQsoDate());
		sb.append(" ");
		sb.append(l.timeOn());
		sb.append("} ");
		sb.append("{");
		sb.append("Freq: ");
		sb.append("\\textbf{");
		sb.append(l.freq());
		sb.append("} ");
		sb.append("Mode: ");
		sb.append("\\textbf{");
		sb.append(l.getMode());
		sb.append("} ");
		sb.append("RST: ");
		sb.append("\\textbf{");
		sb.append(l.getRstSent());
		sb.append("}}");
		return sb.toString();
	}
	
	public String buildLatexDocument(List<Label> labels) {
		StringBuilder sb = new StringBuilder();
		sb.append(LabelConfig.buildLatexHeader());
		for (Label l: labels) {
			sb.append(buildConfpin(l) + "\n");
		}
		sb.append(LabelConfig.getEndDocument());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		LabelGenerator g = new LabelGenerator();
		LabelMaker m = new LabelMaker();
		List<ADIFRecord> records = null;
    	try {
			Reader r = new FileReader(new File("/tmp/test.adi"));
			ADIFReader adi = new ADIFReader(r);
			records = new ArrayList<ADIFRecord>();
			ADIFRecord adif = null;
			while((adif = adi.next()) != null) {
				records.add(adif);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ADIFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(records.size());
    	System.out.println(m.buildLatexDocument(g.createLabels(records)));
	}
}
