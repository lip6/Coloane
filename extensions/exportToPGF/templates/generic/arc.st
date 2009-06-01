% $formalism$-$it.formalism$, id=$it.id$
$if(it.hasColor)$\definecolor{arccolor}{RGB}{$it.color.red$, $it.color.green$, $it.color.blue$}$else$$endif$
\path[ $formalism$-$it.formalism$
$if(it.hasColor)$     , draw=arccolor$else$$endif$
$if(it.isCurve)$     , rounded corners=10pt$else$$endif$
     ] (n-$it.source$) $it.inflexions:templates/generic/inflexion()$ -- (n-$it.target$);
$it.attributes:templates/generic/attribute()$
