[ every node/.style
  = { inner sep=0pt
    }
, RG-type/.style
  = { draw=none
    }
, RG-authors/.style
  = { draw=none
    }
, RG-information/.style
  = { draw=none
    }
, RG-event/.style
  = { -triangle 90
    , very thick
    , draw=black
    }
$if(hasinitial_state)$
, RG-initial_state/.style
  = { shape=circle
    , double
    , draw=black
    , very thick
    , minimum size=$initial_stateSize$cm
    }
$else$$endif$
$if(hasterminal_state)$
, RG-terminal_state/.style
  = { shape=circle
    , fill=foregroundcolor
    , draw=black
    , very thick
    , minimum size=$terminal_stateSize$cm
    }
$else$$endif$
$if(hasstate)$
, RG-state/.style
  = { shape=circle
    , draw=black
    , very thick
    , minimum size=$stateSize$cm
    }
$else$$endif$
]
