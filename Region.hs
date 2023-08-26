module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR, cityInR, linkInR, citiesLinkedR, citiesForLinkR, citiesInLinkR, citiesInR, linksCapacityInR, cityInTunelR, cityInCitiesR, linkInTunelR, differentPoint )
   where

import City
import Quality
import Link
import Tunel


data Region = Reg [City] [Link] [Tunel] deriving (Eq)
instance Show Region
   where show (Reg cities links tuneles) = "Ciudades: " ++ show cities ++ "\n Links: " ++ show links ++ "\n Tuneles: " ++ show tuneles ++ "\n \n"

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR r@(Reg cities links tunel) city | cityInR r city = error "La ciudad ya existe"
                                       | not (differentPoint cities city) = error "No pueden haber dos ciudades en el mismo punto"
                                       | otherwise = Reg (city:cities) links tunel

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR r@(Reg c l t) c1 c2 q | c1 == c2 = error "Las ciudades no pueden ser iguales"
                            | not (cityInR r c1 && cityInR r c2) = error "Las ciudades no estan en la region"
                            | linkInR r c1 c2 = error "El link ya existe"
                            | otherwise = Reg c ((newL c1 c2 q):l) t 

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR r@(Reg cities links tunel) c | not (citiesInR r c) = error "Las ciudades no estan en la region"
                                    | not (citiesLinkedR r c) = error "Las ciudades no estan enlazadas"
                                    | connectedR r (head c) (last c) = error "El tunel ya esta creado"
                                    | not (linksCapacityInR r c) = error "Un link no tiene capacidad disponible"
                                    | head c == last c = error "No se puede hacer un tunel entre la misma ciudad"
                                    | otherwise = Reg cities links (newT (citiesInLinkR r (citiesForLinkR c)):tunel)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) city1 city2 = length [tunel | tunel <- tuneles, connectsT city1 city2 tunel] == 1

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = length ([x | x <- links, linksL city1 city2 x]) == 1

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR r@(Reg _ _ tunel) c1 c2 | connectedR r c1 c2  = delayT (head (cityInTunelR tunel c1 c2))
                               | otherwise = error "Las ciudades no estan conectadas por un tunel o alguna/ambas no existen"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR r@(Reg city link tunel) c1 c2 | linkedR r c1 c2 = capacityL (head (cityInCitiesR link c1 c2)) - linkInTunelR tunel (head (cityInCitiesR link c1 c2))
                                                    | otherwise = error "Las ciudades no estan enlazadas o alguna/ambas no existen"


-----------------------------------FUNCIONES--------------------------------------------
cityInR :: Region -> City -> Bool
cityInR (Reg cities _ _) city = city `elem` cities

citiesInR :: Region -> [City] -> Bool
citiesInR _ [] = True
citiesInR region (city:cities) = if (cityInR region city) then citiesInR region cities else False

linkInR :: Region -> City -> City -> Bool
linkInR (Reg _ links _) c1 c2 = length [link | link <- links, linksL c1 c2 link] /= 0

differentPoint :: [City] -> City -> Bool
differentPoint [] city = True
differentPoint (city:cities) c = distanceC city c /= 0 && differentPoint cities c

citiesLinkedR :: Region -> [City] -> Bool
citiesLinkedR region [] = True
citiesLinkedR region [_] = True
citiesLinkedR region (x:y:resto) = (linkedR region x y) && (citiesLinkedR region (y:resto))

citiesForLinkR :: [City] -> [(City, City)]
citiesForLinkR [] = []
citiesForLinkR [_] = []
citiesForLinkR (x:y:resto) | x /= y = (x, y) : citiesForLinkR (y:resto)
                           | otherwise = citiesForLinkR (y:resto)

citiesInLinkR :: Region -> [(City,City)] -> [Link]
citiesInLinkR (Reg city links _) cities = [link | link <- links, (c1, c2) <- cities, linksL c1 c2 link]

linksCapacityInR :: Region -> [City] -> Bool
linksCapacityInR _ [] = True
linksCapacityInR _ [_] = True
linksCapacityInR region (c1:c2:cities) = if ((availableCapacityForR region c1 c2) > 0) then linksCapacityInR region (c2:cities) else False

cityInTunelR :: [Tunel] -> City -> City -> [Tunel]
cityInTunelR tuneles c1 c2 = [tunel | tunel <- tuneles, connectsT c1 c2 tunel]

cityInCitiesR :: [Link] -> City -> City -> [Link]
cityInCitiesR links c1 c2 = [link | link <- links, linksL c1 c2 link]

linkInTunelR :: [Tunel] -> Link -> Int
linkInTunelR tunel link = foldr (\x tunelThroughLink -> if usesT link x then tunelThroughLink + 1 else tunelThroughLink) 0 tunel