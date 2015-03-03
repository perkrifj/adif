adif-tools
====
Tools for doing work on ADIF-files - primarily in the context of ham-radio logs.

latex-labeler
====
Generate a .tex-file to be used for generating printable labels for QSL cards.
Compliling the LaTeX file with the ticket-library you can generate nice-looking labels.

More info: https://www.ctan.org/tex-archive/macros/latex/contrib/ticket

java -jar filename.jar -i inputefile.ADI outputfile.tex

Command-line arguments:

-i, path to the input ADIF file (required)

-o, path to the output .tex file (required)

--no-contestname, avoid printing the contest-name at the bottom of the label. Usefull if you've used a contest-template for another contest and do not want to print the wrong contestname.

Statistics
====
Generate statistics based on the supplied ADIF file.

TODO: 
* Number of QSOs
* Number of unique calls in the log
* Number of QSOs per operator
* Max hourly rate per operator
...

