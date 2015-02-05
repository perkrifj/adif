package no.la1k.labeler;

import java.util.ArrayList;
import java.util.List;

import no.la1k.adif.ADIFRecord;
import no.la1k.labeler.Label.LabelBuilder;

public class LabelGenerator {

	/**
	 * Builds a list of Label from an ADIFRecord
	 * @param record
	 * @return List of Label
	 */
	public List<Label> createLabels(List<ADIFRecord> record) {
		List<Label> labels = new ArrayList<Label>();
		for (ADIFRecord r : record) {
			LabelBuilder lb = new LabelBuilder();
			lb.call(r.getField(LabelFields.CALL.toString()).getValue());
			lb.qsoDate(r.getField(LabelFields.QSO_DATE.toString()).getValue());
			lb.freq(r.getField(LabelFields.FREQ.toString()).getValue());
			lb.freqRx(r.getField(LabelFields.FREQ_RX.toString()).getValue());
			lb.mode(r.getField(LabelFields.MODE.toString()).getValue());
			lb.rstSnt(r.getField(LabelFields.RST_SENT.toString()).getValue());
			lb.operator(r.getField(LabelFields.OPERATOR.toString()).getValue());
			lb.contestId(r.getField(LabelFields.CONTEST_ID.toString()).getValue());
			labels.add(lb.build());
		}
		return labels;
	}
}
