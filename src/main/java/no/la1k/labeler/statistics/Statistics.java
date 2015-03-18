package no.la1k.labeler.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import no.la1k.labeler.Label;

/**
 * Generate useful statistics based on the content of the ADIF-file.
 * 
 * Example of statistics that can be useful:
 * - Number of QSOs made
 * - Number of QSOs made to unique calls
 * - Number of QSOs made per operator
 * - Hourly rates per operator
 * - More?
 *
 */
public class Statistics {

	private final int totalQSOs;
	private final int totalQSOsToUniqueCalls;
	private final Map<String, Integer> totalQSOsPerOperator;
	private final Map<String, Integer> totalQSOsToUniqueCallsPerOPerator;
	private final Map<String, Integer> bestHourlyRatePerOperator;

	public int getTotalQSOs() {
		return totalQSOs;
	}


	public int getTotalQSOsToUniqueCalls() {
		return totalQSOsToUniqueCalls;
	}


	public Map<String, Integer> getTotalQSOsPerOperator() {
		return totalQSOsPerOperator;
	}


	public Map<String, Integer> getTotalQSOsToUniqueCallsPerOPerator() {
		return totalQSOsToUniqueCallsPerOPerator;
	}


	public Map<String, Integer> getBestHourlyRatePerOperator() {
		return bestHourlyRatePerOperator;
	}


	private Statistics(StatisticsBuilder builder) {
		this.totalQSOs = builder.totalQSOs;
		this.totalQSOsToUniqueCalls = builder.totalQSOsToUniqueCalls;
		this.totalQSOsPerOperator = builder.totalQSOsPerOperator;
		this.totalQSOsToUniqueCallsPerOPerator = builder.totalQSOsToUniqueCallsPerOPerator;
		this.bestHourlyRatePerOperator = builder.bestHourlyRatePerOperator;
	}
	
	public String print(){
		StringBuilder sb = new StringBuilder();
		sb.append("--STATISTICS--\n\n");
		sb.append("--TOTAL NUMBER OF QSOs--\n");
		sb.append(totalQSOs + "\n");
		sb.append("--TOTAL NUMBER OF QSOs TO UNIQUE CALLS--\n");
		sb.append(totalQSOsToUniqueCalls + "\n");
		sb.append("--TOTAL NUMBER OF QSOs PER OPERATOR--\n");
		for (String operator : totalQSOsPerOperator.keySet()) {
			sb.append(operator + ":" + totalQSOsPerOperator.get(operator) + "\n");
		}
		sb.append("--TOTAL NUMBER OF QSOs TO UNIQUE CALLS PER OPERATOR--\n");
		for (String operator : totalQSOsToUniqueCallsPerOPerator.keySet()) {
			sb.append(operator + ":" + totalQSOsToUniqueCallsPerOPerator.get(operator) + "\n");
		}
		return sb.toString();
	}


	public static class StatisticsBuilder {
		private int totalQSOs;
		private int totalQSOsToUniqueCalls;
		private Map<String, Integer> totalQSOsPerOperator;
		private Map<String, Integer> totalQSOsToUniqueCallsPerOPerator;
		private Map<String, Integer> bestHourlyRatePerOperator;
		List<Label> labels;

		public StatisticsBuilder(List<Label> labels) {
			this.totalQSOsPerOperator = new HashMap<String, Integer>();
			this.totalQSOsToUniqueCallsPerOPerator = new HashMap<String, Integer>();
			this.bestHourlyRatePerOperator = new HashMap<String, Integer>();
			this.labels = labels;
		}
		
		public StatisticsBuilder totalCallsStatistics() {
			this.totalQSOs = labels.size();
			Set<String> uniqueCalls = new HashSet<String>();
			for (Label label : labels) {
				uniqueCalls.add(label.getCall());
			}
			this.totalQSOsToUniqueCalls = uniqueCalls.size();
			
			Multimap<String, String> callsPerOperator = ArrayListMultimap.create();
			for (Label label : labels) {
				callsPerOperator.put(label.getOperator(), label.getCall());
			}
			for (String operator : callsPerOperator.keySet()) {
				uniqueCalls = new HashSet<String>(callsPerOperator.get(operator));
				totalQSOsPerOperator.put(operator, callsPerOperator.get(operator).size());
				totalQSOsToUniqueCallsPerOPerator.put(operator, uniqueCalls.size());
			}
			return this;
		}
		
		public StatisticsBuilder hourlyRates() {
			return null;
		}
		
		
		public Statistics build() {
			return new Statistics(this);
		}
	}
}
