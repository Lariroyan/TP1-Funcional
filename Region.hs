module Region ( Region, newR, foundR, linkR,  linkedR,  )
   where
--tunelR, pathR, linksForR, connectedR, despues de linkR
-- delayR, availableCapacityForR, usedCapacityForR despues de linkedR
import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel]

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunel) city | [x | x <- cities, x == city] /= [] = error "La ciudad ya existe"
                                     | otherwise = Reg (city:cities) links tunel
-- verificar que la ciudad no este

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunel) city1 city2 quality = Reg cities ((newL city1 city2 quality):links) tunel

--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región

--connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = length ([x | x <- links, (connectsL city1 x) && (connectsL city2 x)]) == 1

--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
-- ---suma de la demora de los tuneles

--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
-- ---capacidad del link entre las ciudades