package no.la1k.labeler.latexbuilder;

/**
 * Class to control the layout of the labels
 * 
 * @author la6xta
 */

public final class LabelConfig {
	
	private static String getLatexPackages(){
		StringBuilder sb = new StringBuilder();
		sb.append("\\documentclass[a4paper,10pt]{letter}\n");
		sb.append("\\usepackage{fullpage}\n");
		sb.append("\\usepackage[freepin]{ticket}\n");
		sb.append("\\usepackage[T1]{fontenc}\n");
		sb.append("\\usepackage[utf8]{inputenc}\n");
		sb.append("\\usepackage{graphicx}\n");
		return sb.toString();
	}
	
	private static String getFreepinConfig(){
		StringBuilder sb = new StringBuilder();
		sb.append("\\unitlength=1mm\n");
		sb.append("\\hoffset=-22mm\n");
		sb.append("\\voffset=-22mm\n");
		sb.append("\\ticketNumbers{3}{8}\n");
		sb.append("\\ticketSize{60}{38}\n");
		sb.append("\\ticketDistance{7}{-2.4}\n");
		return sb.toString();
	}
	
	private static String getTicketDefault(){
		StringBuilder sb = new StringBuilder();
		sb.append("\\renewcommand{\\ticketdefault}{\n");
		sb.append("\\put( 5, 14){\\line(1,0){60}}\n");
		sb.append("}\n");
		return sb.toString();
	}
	
	private static String getConfpin(){
		StringBuilder sb = new StringBuilder();
		sb.append("\\newcommand{\\confpin}[4]{\\ticket{\n");
		sb.append("\\put(35,29){\\makebox[0mm]{\\bfseries\\Large #1}}\n");
		sb.append("\\put(35,22){\\makebox[0mm]{ #2}}\n");
		sb.append("\\put(35,17){\\makebox[0mm]{ #3}}\n");
		sb.append("\\put(35,10){\\makebox[0mm]{ #4}}\n");
		sb.append("}}\n");
		return sb.toString();
	}
	
	public static String getEndDocument(){
		return "\\end{document}";
	}
	
	public static final String buildLatexHeader(){
		StringBuilder sb = new StringBuilder();
		sb.append(getLatexPackages());
		sb.append("\\begin{document}\n");
		sb.append("\n");
		sb.append(getFreepinConfig());
		sb.append("\n");
		sb.append(getTicketDefault());
		sb.append("\n");
		sb.append(getConfpin());
		return sb.toString();
	}
}
