module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 qua = Lin city1 city2 qua 

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city1 (Lin city2 city3 _) = (city1 == city2) || (city1 == city3)

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 lin = connectsL city1 lin && connectsL city2 lin

capacityL :: Link -> Int
capacityL (Lin _ _ qua) = capacityQ qua 

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ qua) = delayQ qua