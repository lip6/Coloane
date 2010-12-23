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
$nodes:resources/stringtemplates/generic/node() ; separator="\n"$
$arcs:resources/stringtemplates/generic/arc() ; separator="\n"$
$attributes:resources/stringtemplates/generic/attribute() ; separator="\n"$
$nodes:resources/stringtemplates/generic/nodeAttribute() ; separator="\n"$
$arcs:resources/stringtemplates/generic/arcAttribute() ; separator="\n"$
\end{tikzpicture}
}

\end{document}
