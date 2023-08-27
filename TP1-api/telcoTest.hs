import Point
import City
import Quality
import Link
import Tunel
import Region
import GHC.Base (TrName(TrNameD))
import Data.Bool (Bool(True))
import Region (cityInCitiesR)


ciudad1 = newC "a" (newP 1 2)
ciudad2 = newC "b" (newP 2 1)
ciudad3 = newC "c" (newP 3 4)
ciudad4 = newC "d" (newP 4 8)
ciudad5 = ciudad1
ciudad6 = newC "e" (newP 1 2)
ciudad7 = newC "f" (newP 9 7)


calidad1 = newQ "fo" 5 10
calidad2 = newQ "co" 2 40
calidad3 = newQ "sa" 4 23

link1 = newL ciudad1 ciudad2 calidad2
link2 = newL ciudad2 ciudad3 calidad1
link3 = newL ciudad3 ciudad1 calidad1
link4 = newL ciudad3 ciudad4 calidad1

--Creo tuneles mediante links-- 
tunel1 = (newT [link1, link2])
tunel2 = (newT [link2, link3])
tunel3 = (newT [link3, link4])
-- Creamos una region y le agregamos ciudades
region = newR
region1 = foundR region ciudad1
region2 = foundR region1 ciudad2
region3 = foundR region2 ciudad3
region4 = foundR region3 ciudad4

-- Le agregamos links
region5 = linkR region4 ciudad1 ciudad2 calidad2
region6 = linkR region5 ciudad2 ciudad3 calidad1
region7 = linkR region6 ciudad3 ciudad1 calidad1
region8 = linkR region7 ciudad3 ciudad4 calidad1

-- Le agregamos tuneles
region9 = tunelR region8 [ciudad1, ciudad2, ciudad3]
region10 = tunelR region9 [ciudad1, ciudad2, ciudad3, ciudad4]

test_funcionalidad_regiones = [region, region1, region2, region3, region4, region5, region6, region7, region8, region9, region10]

--testeo de cada función--

testLink = [ 

    --ConnectsL--
    connectsL ciudad1 ciudad2 == True
    connectsL ciudad2 ciudad3 == True 
    connectsL ciudad1 ciudad4 == False
    connectsL ciudad3 ciudad4 == True

    --LinksL--
    linksL ciudad1 ciudad2 link1 == True
    linksL ciudad3 ciudad4 link4 == True
    linksL ciudad1 ciudad2 link4 == False

    --CapacityL--
    capacityL link1 == 2
    capacityL link4 == 5

    --DelayL--
    delayL link1  == 14.142132
    delayL link2 == 126.491106
    ]

testQuality = [ 

    --CapacityQ--
    capacityQ calidad1 == 5
    capacityQ calidad3 == 4

    --DelayQ-- 
    delayQ calidad1 == 10
    delayQ calidad2 == 40
    delayQ calidad3 == 23
    ]
   
testCity = [ 

        --distanceC-- 
    distanceC ciudad1 ciudad2 == 1.41421 
    distanceC ciudad3 ciudad4 == 4.12310
    distanceC ciudad1 ciudad7 == 9.43398
    distanceC ciudad1 ciudad4 == 6.70820
    ]

testTunel = [ 

    --connectsT--
    connectsT ciudad1 ciudad2 tunel1 == True 
    connectsT ciudad1 ciudad3 tunel1 == True
    connectsT ciudad3 ciudad4 tunel3 == True

    --usesT-- 
    usesT link1 tunel1 == True
    usesT link4 tunel3 == True
    usesT link2 tunel1 == True

    --delayT--
    delayT tunel1 == 50.0
    delayT tunel3 == 20.0

    --cityInLInkT-- (función auxiliar)
    cityInLinkT ciudad1 [link1] == True
    cityInLinkT ciudad4 [link4] == True
    ]

testRegion = [

    --connectedR--
    connectedR region2 ciudad1 ciudad2 tunel1 == True 
    connectedR region3 ciudad2 ciudad3 tunel2 == True

    --linkedR-- 
    linkedR region5 ciudad1 ciudad2 == True
    linkedR region8 ciudad3 ciudad4 == True

    --delayR-- 
    delayR region9 ciudad1 ciudad3 tunel1 == 140.633238

    --availableCapacityForR-- 
    availableCapacityForR region9 ciudad1 ciudad2 == 5
    availableCapacityForR region9 ciudad1 ciudad3 == 10

     ]