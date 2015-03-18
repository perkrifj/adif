package no.la1k.labeler.latexbuilder;

import no.la1k.labeler.statistics.Statistics;
import no.la1k.labeler.statistics.Statistics.StatisticsBuilder;
import no.la1k.util.StringUtil;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Runner {

	@Option(name="-i",usage="Path to adif inputfile")
	private String inputFileString;

	@Option(name="-o",usage="Path to tex outputfile")
	private String outputFileString;

	@Option(name="--no-contestname",usage="Disable the printing of contestname on the bottom of each label")
	private boolean disablePrintingOfContestName;
	
	public static void main(String[] args) {
		Runner runner = new Runner();
		CmdLineParser parser = new CmdLineParser(runner);
		try {
			parser.parseArgument(args);
			if(StringUtil.isNullOrEmpty(runner.inputFileString) || StringUtil.isNullOrEmpty(runner.outputFileString)) {
				throw new CmdLineException(parser, "Missing file parameters");
			}
			LabelMaker m = new LabelMaker(runner.inputFileString, runner.outputFileString, runner.disablePrintingOfContestName);
			m.makeLabels();
			StatisticsBuilder sb = new StatisticsBuilder(m.getLabels());
			Statistics statistics = sb.totalCallsStatistics().build();
			System.out.println(statistics.print());
		}
		catch(CmdLineException e) {
			System.err.println(e.getMessage());
			System.err.println("Both input- and output-file required");
			System.err.println("Example: java -jar myprogram.jar -i iputfile -o outputfile");
			return;
		}
	}
}
