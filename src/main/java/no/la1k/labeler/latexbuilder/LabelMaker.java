package no.la1k.labeler.latexbuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.la1k.adif.ADIFException;
import no.la1k.adif.ADIFReader;
import no.la1k.adif.ADIFRecord;
import no.la1k.labeler.Label;
import no.la1k.labeler.LabelGenerator;
import no.la1k.util.FileUtil;

public class LabelMaker {

	private String inputFile;
	private String outpufFile;
	/**
	 * Allowing user to skip the printing of contestname at the bottom of the label
	 * This is done so that we can turn of the printing of a contest if the user has used
	 * a template for another contest than the one you actually operated
	 */
	private boolean skipContestName = false;

	public boolean isSkipContestName(){
		return skipContestName;
	}

	public LabelMaker(String inputFile, String outputFile, boolean skipContestName) {
		this.inputFile = inputFile;
		this.outpufFile = outputFile;
		this.skipContestName = skipContestName;
	}

	private String buildConfpin(Label l) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\confpin{");
		sb.append(l.getCleanCall());
		sb.append("}");
		sb.append("{");
		sb.append(l.getFormattedDate());
		sb.append("} ");
		sb.append("{");
		sb.append("\\textbf{");
		sb.append(l.getFrequencyInfo());
		sb.append("} ");
		sb.append("Mode: ");
		sb.append("\\textbf{");
		sb.append(l.getMode());
		sb.append("} ");
		sb.append("RST: ");
		sb.append("\\textbf{");
		sb.append(l.getRstSent());
		sb.append("}}");
		sb.append("{");
		sb.append(isSkipContestName() ? "" : "\\scriptsize " +  l.getContestId());
		sb.append("}");
		return sb.toString();
	}

	private String buildLatexDocument(List<Label> labels) {
		StringBuilder sb = new StringBuilder();
		sb.append(LabelConfig.buildLatexHeader());
		for (Label l: labels) {
			sb.append(buildConfpin(l) + "\n");
		}
		sb.append(LabelConfig.getEndDocument());
		return sb.toString();
	}

	public void makeLabels() {
		LabelGenerator generator = new LabelGenerator();

		List<ADIFRecord> extractedRecords = null;
		try {
			ADIFReader adi = new ADIFReader(inputFile);
			extractedRecords = new ArrayList<ADIFRecord>();
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
					extractedRecords.add(adif);
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found. " + inputFile);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("File I/O failed");
			System.exit(1);
		}
		try {
			List<Label> labels = generator.createLabels(extractedRecords);
			Collections.sort(labels);
			FileUtil.writeFile(outpufFile, buildLatexDocument(labels));
		}
		catch(IOException e) {
			System.err.println("Failed to write output file: " + outpufFile);
			System.exit(1);
		}
	}
}
