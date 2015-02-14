package no.la1k.labeler;

import java.util.Comparator;

public class Label implements Comparable<Label>{
	
	private String call;
	private String qso_date;
	private String time_on;
	private String band;
	private String station_callsign;
	private String freq;
	private String contest_id;
	private String freq_rx;
	private String mode;
	private String rst_rcvd;
	private String rst_sent;
	private String operator;
	
	
	public String getCall() {
		return call;
	}

	public String getQsoDate() {
		return qso_date;
	}


	public String timeOn() {
		return time_on;
	}

	public String getBand() {
		return band;
	}
	public String stationCallsign() {
		return station_callsign;
	}

	public String freq() {
		return freq;
	}

	public String getContestId() {
		return contest_id;
	}

	public String getFreqRx() {
		return freq_rx;
	}

	public String getMode() {
		return mode;
	}

	public String getRstReceived() {
		return rst_rcvd;
	}

	public String getRstSent() {
		return rst_sent;
	}


	public String getOperator() {
		return operator;
	}

	private Label(LabelBuilder builder) {
		this.call = builder.call;
		this.qso_date = builder.qso_date;
		this.time_on = builder.time_on;
		this.band = builder.band;
		this.station_callsign = builder.station_callsign;
		this.freq = builder.freq;
		this.contest_id= builder.contest_id;
		this.freq_rx = builder.freq_rx;
		this.mode = builder.mode;
		this.rst_rcvd = builder.rst_rcvd;
		this.rst_sent = builder.rst_snt;
		this.operator = builder.operator;
	}
	
	public static class LabelBuilder {
		
		private String call;
		private String qso_date;
		private String time_on;
		private String band;
		private String station_callsign;
		private String freq;
		private String contest_id;
		private String freq_rx;
		private String mode;
		private String rst_rcvd;
		private String rst_snt;
		private String operator;
		
		public LabelBuilder call(String call) {
			this.call = call;
			return this;
		}
		
		public LabelBuilder qsoDate(String qso_date) {
			this.qso_date = qso_date;
			return this;
		}
		
		public LabelBuilder timeOn(String time_on) {
			this.time_on = time_on;
			return this;
		}
		
		public LabelBuilder band(String band) {
			this.band = band;
			return this;
		}
		
		public LabelBuilder stationCallsign(String station_callsign) {
			this.station_callsign = station_callsign;
			return this;
		}
		
		public LabelBuilder freq(String freq) {
			this.freq = freq;
			return this;
		}
		
		public LabelBuilder contestId(String contest_id) {
			this.contest_id = contest_id;
			return this;
		}
		
		public LabelBuilder freqRx(String freq_rx) {
			this.freq_rx = freq_rx;
			return this;
		}
		
		public LabelBuilder mode(String mode) {
			this.mode = mode;
			return this;
		}
		
		public LabelBuilder rstRcvd(String rst_rcvd) {
			this.rst_rcvd = rst_rcvd;
			return this;
		}
		
		public LabelBuilder rstSnt(String rst_snt) {
			this.rst_snt = rst_snt;
			return this;
		}
		
		public LabelBuilder operator(String operator) {
			this.operator = operator;
			return this;
		}
		
		public Label build(){
			return new Label(this);
		}
	}

//	@Override
//	public int compare(Label o1, Label o2) {
//		return o1.call.compareTo(o2.call);
//	}

	@Override
	public int compareTo(Label o) {
		return this.call.compareTo(o.call);
	}
}