\documentclass{article}
\usepackage{xcolor}
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{arrows}

\begin{document}

{
\fontfamily{\sfdefault}
\fontsize{14}{14}
\selectfont
\begin{tikzpicture}
$(preamble)()$
$attributes:templates/generic/attribute() ; separator="\n"$
$nodes:templates/generic/node() ; separator="\n"$
$arcs:templates/generic/arc() ; separator="\n"$
\end{tikzpicture}
}

\end{document}
