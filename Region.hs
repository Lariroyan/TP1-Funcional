module Region ( Region, newR, foundR, linkR, tunelR,  linkedR,  verifCityInR,availableCapacityForR, verifCitiesLinkedR, cityForLinkR, verifCitiesInLinkR, verifLinkInR, connectedR, delayR, verifLinkInTunelR )
   where
--module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR, verifCityInR, verifCitiesLinkedR, cityForLinkR, verifCitiesInLinkR )
import City
import Quality
import Link
import Tunel 

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR reg@(Reg cities links tunel) city | verifCityInR reg city = error "La ciudad ya existe"
                                         | otherwise = Reg (city:cities) links tunel

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR r@(Reg cities links tunel) c1 c2 q | not (verifLinkInR links c1 c2 q) && verifCityInR r c1 && verifCityInR r c2 = Reg cities ((newL c1 c2 q):links) tunel
                                         | otherwise = error "El link ya existe o las ciudades no existen"

-----------------------------------COMPLETAR--------------------------------------------
tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunel) cities1 | verifCitiesLinkedR region cities1 = Reg cities links (newT (verifCitiesInLinkR region (cityForLinkR cities1)):tunel)
                                               | otherwise = error "Hay ciudades no enlazadas, ciudades que no existen o el tunel ya esta creado"
-- Asumo que el usuario es inteligente e ingresa las ciudades en orden

--1. ver si las ciudades existen en la region
--LISTO 2. ver si los links entre esas ciudades exiten en la region
--LISTO 3. meterlos en una lista
--LISTO 4. hacer un nuevo tunel con la lista
--5. ver si ese tunel ya esta creado en region, sino apendearlo
--6. si la capacidad del link es 0, el tunel no se deberia hacer

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) city1 city2 = [tunel | tunel <- tuneles, connectsT city1 city2 tunel] /= []

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = length ([x | x <- links, linksL city1 city2 x]) == 1

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region@(Reg _ _ tunel) c1 c2 | connectedR region c1 c2 = delayT (head (verifCityInTunelR tunel c1 c2))
                                    | otherwise = error "Las ciudades no estan conectadas por un tunel o no existen"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR reg@(Reg city link tunel) c1 c2 | linkedR reg c1 c2 = capacityL (head (verifCityInCitiesR link c1 c2)) - verifLinkInTunelR tunel (head (verifCityInCitiesR link c1 c2))
                                                      | otherwise = error "Las ciudades no estan enlazadas o no existen"




-----------------------------------FUNCIONES--------------------------------------------
verifCityInR :: Region -> City -> Bool
verifCityInR (Reg cities _ _) city = city `elem` cities

verifCitiesLinkedR :: Region -> [City] -> Bool
verifCitiesLinkedR region [] = True
verifCitiesLinkedR region [_] = True
verifCitiesLinkedR region (x:y:resto) = (linkedR region x y) && (verifCitiesLinkedR region resto)

cityForLinkR :: [City] -> [(City, City)]
cityForLinkR [] = []
cityForLinkR [_] = []
cityForLinkR (x:y:resto) = (x, y) : cityForLinkR (y:resto)

verifCitiesInLinkR :: Region -> [(City,City)] -> [Link]
verifCitiesInLinkR (Reg city links _) cities = [link | link <- links, (c1, c2) <- cities, linksL c1 c2 link]


-----------------------------------FUNCION DE VERIFICACIONES PARA TUNELR--------------------------------------------
--verificationForTunelR :: Region -> [City] -> Bool
--verificationForTunelR region cities = verifCitiesLinkedR region cities && 

verifLinkInR :: [Link] -> City -> City -> Quality -> Bool
verifLinkInR links c1 c2 q = (newL c1 c2 q) `elem` links

verifCityInTunelR :: [Tunel] -> City -> City -> [Tunel]
verifCityInTunelR tuneles c1 c2 = [x | x <- tuneles, connectsT c1 c2 x]

verifCityInCitiesR :: [Link] -> City -> City -> [Link]
verifCityInCitiesR links c1 c2 = [link | link <- links, linksL c1 c2 link]


verifLinkInTunelR :: [Tunel] -> Link -> Int
verifLinkInTunelR tunel link = length ([x | x <- tunel, usesT link x])
-- cuántos tuneles pasan por un link
-- ver cómo usar fold
--countA target = foldr (\each fold -> if target == each then fold + 1 else fold) 0
-- countA 3 [3,2,3,4]    ->     2