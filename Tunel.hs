module Tunel ( Tunel, newT,  usesT, delayT, connectsT )
   where
--connectsT, despues de new
import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links) = (connectsL city1 (head links) && connectsL city2 (last links)) || (connectsL city2 (head links) && connectsL city1 (last links))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = [x | x <- links, x == link] /= []

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (link:links)) = (delayL link) + (delayT (newT links))