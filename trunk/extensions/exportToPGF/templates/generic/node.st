% $formalism$-$it.formalism$, id=$it.id$
$if(it.hasForeground)$\definecolor{foregroundcolor}{RGB}{$it.foreground.red$, $it.foreground.green$, $it.foreground.blue$}$else$$endif$
$if(it.hasBackground)$\definecolor{backgroundcolor}{RGB}{$it.background.red$, $it.background.green$, $it.background.blue$}$else$$endif$
\node[ $formalism$-$it.formalism$
$if(it.hasForeground)$     , draw=foregroundcolor$else$$endif$
$if(it.hasBackground)$     , fill=backgroundcolor$else$$endif$
     ] (n-$it.id$) at ($it.x$, $it.y$) {};
