[ every node/.style
  = { inner sep=0pt
    }
, PT-Net-marking/.style
  = { draw=none
    }
, PT-Net-name/.style
  = { draw=none
    }
, PT-Net-valuation/.style
  = { draw=none
    }
, PT-Net-authors/.style
  = { draw=none
    }
, PT-Net-version/.style
  = { draw=none
    }
, PT-Net-title/.style
  = { draw=none
    }
, PT-Net-date/.style
  = { draw=none
    }
, PT-Net-note/.style
  = { draw=none
    }
, PT-Net-arc/.style
  = { -triangle 90
    , very thick
    , draw=black
    }
$if(hasplace)$
, PT-Net-place/.style
  = { shape=circle
    , draw=black
    , very thick
    , minimum size=$placeSize$cm
    }
$else$$endif$
$if(hastransition)$
, PT-Net-transition/.style
  = { shape=rectangle
    , draw=black
    , very thick
    , minimum width=$transitionWidth$cm
    , minimum height=$transitionHeight$cm
    }
$else$$endif$
]
