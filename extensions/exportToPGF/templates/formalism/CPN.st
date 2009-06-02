[ CPN-marking/.style
  = { draw=none
    }
, CPN-name/.style
  = { draw=none
    }
, CPN-domain/.style
  = { draw=none
    }
, CPN-guard/.style
  = { draw=none
    }
, CPN-valuation/.style
  = { draw=none
    }
, CPN-authors/.style
  = { draw=none
    }
, CPN-version/.style
  = { draw=none
    }
, CPN-title/.style
  = { draw=none
    }
, CPN-date/.style
  = { draw=none
    }
, CPN-note/.style
  = { draw=none
    }
, CPN-declaration/.style
  = { draw=none
    }
, CPN-arc/.style
  = { -triangle 90
    , very thick
    , draw=black
    }
, CPN-inhibitor/.style
  = { -o
    , very thick
    , draw=black
    }
$if(hasplace)$
, CPN-place/.style
  = { shape=circle
    , draw=black
    , very thick
    , minimum size=$placeSize$cm
    }
$else$$endif$
$if(hasqueue)$
, CPN-queue/.style
  = { shape=rectangle
    , rounded corners=$queueHeight$cm
    , draw=black
    , very thick
    , minimum width=$queueWidth$cm
    , minimum height=$queueHeight$cm
    }
$else$$endif$
$if(hastransition)$
, CPN-transition/.style
  = { shape=rectangle
    , draw=black
    , very thick
    , minimum width=$transitionWidth$cm
    , minimum height=$transitionHeight$cm
    }
$else$$endif$
]
