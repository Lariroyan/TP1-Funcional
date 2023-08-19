module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR, verifCityInR, verifCitiesLinkedR, cityForLinkR, verifCityInLinkR )
   where

import City
import Quality
import Link
import Tunel 

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunel) city | elem city cities = error "La ciudad ya existe"
                                     | otherwise = Reg (city:cities) links tunel

-----------------------------------COMPLETAR--------------------------------------------
linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunel) city1 city2 quality = Reg cities ((newL city1 city2 quality):links) tunel
-- Verificar que el link no existe
-- Verificar que las ciudades esten en la region

-----------------------------------COMPLETAR--------------------------------------------
tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunel) cities1 | verifCitiesLinkedR region cities1 = Reg cities links (newT (verifCityInLinkR region (cityForLinkR cities1)):tunel)
                                               | otherwise = error "Hay ciudades no enlazadas, ciudades que no existen o el tunel ya esta creado"
-- Asumo que el usuario es inteligente e ingresa las ciudades en orden

--1. ver si las ciudades existen en la region
--LISTO 2. ver si los links entre esas ciudades exiten en la region
--LISTO 3. meterlos en una lista
--LISTO 4. hacer un nuevo tunel con la lista
--5. ver si ese tunel ya esta creado en region, sino apendearlo
--6. si la capacidad del link es 0, el tunel no se deberia hacer

-----------------------------------POR QUÉ DA ERROR--------------------------------------------
--connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
--connectedR (Reg _ _ tuneles) city1 city2 = [tunel | tunel <- tuneles, connectsT city1 city2 tunel] /= []

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = length ([x | x <- links, linksL city1 city2 x]) == 1

--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
--delayR region@(Reg _ _ tunel) c1 c2 | connectedR region c1 c2 = 
   ---suma de la demora de los links del tunel

-----------------------------------COMPLETAR--------------------------------------------
--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
--availableCapacityForR reg@(Reg city link tunel) city1 city2
-- Ver si existe el elnace entre las dos ciudades con: linkedR reg city1 city2, otherwise = error "Las ciudades no estan enlazadas o no existen"
--1. ver la capacidad total del link entre city1 city2
--2. ver en cuantos tuneles esta ese link
--3. restar la cantidad de tuneles a la capacidad total



-----------------------------------FUNCIONES--------------------------------------------

verifCityInR :: Region -> City -> Bool
verifCityInR (Reg city _ _) city1 = city1 `elem` city

verifCitiesLinkedR :: Region -> [City] -> Bool
verifCitiesLinkedR region [] = True
verifCitiesLinkedR region [_] = True
verifCitiesLinkedR region (x:y:resto) = (linkedR region x y) && (verifCitiesLinkedR region resto)

cityForLinkR :: [City] -> [(City, City)]
cityForLinkR [] = []
cityForLinkR [_] = []  -- Si queda un elemento solo, no hay pareja
cityForLinkR (x:y:resto) = (x, y) : cityForLinkR (y:resto)

verifCityInLinkR :: Region -> [(City,City)] -> [Link]
verifCityInLinkR (Reg city links _) cities = [link | link <- links, (c1, c2) <- cities, linksL c1 c2 link]


-----------------------------------FUNCION DE VERIFICACIONES PARA TUNELR--------------------------------------------
--verificationForTunelR :: Region -> [City] -> Bool
--verificationForTunelR region cities = verifCitiesLinkedR region cities && 