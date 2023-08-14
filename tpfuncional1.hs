{-Telco

Es una compañia que se dedica a comunicar las ciudades que se susbcriben a su servicio.
Primero las ingresa al mapa de la región. 
Luego establece vínculos entre ellas de cierta calidad y capacidad.
Finalmente establece canales que conectan distintas ciudades ocupando una unidad de 
capacidad por cada enlace recorrido.

Para sostener este modelo se cuenta con las siguientes entidades:
-}

--module Point ( Point, newP, difP )
--   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x1 y1) (Poi x2 y2) = sqrt (fromIntegral ((x2 - x1)^2 + (y2 - y1)^2))
-----------------
--module City ( City, newC, nameC, distanceC )
--   where
--
data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC string point = Cit string point

nameC :: City -> String
nameC (Cit string point) = string

distanceC :: City -> City -> Float
distanceC (Cit _ point1) (Cit _ point2) = difP point1 point2
-------------------
--module Quality ( Quality, newQ, capacityQ, delayQ )
--   where
--
data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ string tunnels delay = Qua string tunnels delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua string tunnels delay) = tunnels

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua string tunnels delay) = delay
---------------------
--module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
--   where
--
data Link = Lin City City Quality deriving (Eq, Show)
--
newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 qua = Lin city1 city2 qua 
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city1 (Lin city2 city3 _) = (nameC city1 == nameC city2) || (nameC city1 == nameC city3)
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 lin = connectsL city1 lin && connectsL city2 lin
capacityL :: Link -> Int
capacityL (Lin _ _ qua) = capacityQ qua 
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ qua) = delayQ qua
---------------------
--module Tunel ( Tunel, newT, connectsT, usesT, delayT )
--   where
--
data Tunel = Tun [Link] deriving (Eq, Show)
--
newT :: [Link] -> Tunel
newT [lin] = Tun [lin]
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun[lin]) = linksL city1 city2 lin 
--usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link

--delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
---------------------
--module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
--   where
--
--data Region = Reg [City] [Link] [Tunel]
--
--newR :: Region
--foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
--linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
--linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades