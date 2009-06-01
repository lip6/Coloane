% Attribute $formalism$-$it.formalism$
$if(it.hasForeground)$\definecolor{foregroundcolor}{RGB}{$it.foreground.red$, $it.foreground.green$, $it.foreground.blue$}$else$$endif$
$if(it.hasBackground)$\definecolor{backgroundcolor}{RGB}{$it.background.red$, $it.background.green$, $it.background.blue$}$else$$endif$
\node[ $formalism$-$it.formalism$
     , font=$if(it.isBold)$\bfseries$else$$endif$$if(it.isItalic)$\itshape$else$$endif$
$if(it.isMultiLine)$     , text width=$it.width$$else$$endif$
$if(it.hasForeground)$     , draw=foregroundcolor$else$$endif$
$if(it.hasBackground)$     , fill=backgroundcolor$else$$endif$
     ] (n-$it.id$-$it.name$) at ($it.x$, $it.y$) {$it.value$};