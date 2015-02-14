package no.la1k.labeler.latexbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import no.la1k.adif.ADIFException;
import no.la1k.adif.ADIFReader;
import no.la1k.adif.ADIFRecord;
import no.la1k.labeler.Label;
import no.la1k.labeler.LabelGenerator;
import no.la1k.util.FileUtil;

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
//		sb.append("Freq: ");
		sb.append("\\textbf{");
		sb.append(l.freq());
//		sb.append(" MHz");
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

	public String readFile(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}



	public static String pringUsage(){
		StringBuilder sb = new StringBuilder();

		sb.append("1st argument: input file\n");
		sb.append("2nd argument: output file\n");

		sb.append("Example usage: \n");
		sb.append("java -jar <filename>.jar /tmp/input /tmp/output");

		return sb.toString();
	}


	public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("Please supply 2 arguments \n");
			System.out.println(LabelMaker.pringUsage());
			System.exit(1);
		}

		LabelGenerator g = new LabelGenerator();
		LabelMaker m = new LabelMaker();
		List<ADIFRecord> records = null;
		try {
			ADIFReader adi = new ADIFReader(args[0]);
			records = new ArrayList<ADIFRecord>();
			ADIFRecord adif = null;
			/*
			 * If we get the same record again, break the loop
			 */
			ADIFRecord breaker = null;
			for(;;) {
				try {
					adif = adi.next();
				}
				catch(ADIFException ae) {
					if(breaker != null && breaker.equals(adif)) {
						break;
					}
					continue;
				}
				finally{
					breaker = adif;
				}
				if(adif != null) {
					records.add(adif);
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found. " + args[0]);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("File I/O failed");
			System.exit(1);
		}
		try {
			List<Label> labels = g.createLabels(records);
			Collections.sort(labels);
			FileUtil.writeFile(args[1], m.buildLatexDocument(labels));
		}
		catch(IOException e) {
			System.err.println("Failed to write output file: " + args[1]);
			System.exit(1);
		}

	}
}
