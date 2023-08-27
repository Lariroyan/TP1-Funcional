import Control.Exception
import System.IO.Unsafe
import Point
import City
import Quality
import Link
import Tunel
import Region

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


c1 = newC "a" (newP 1 2)
c2 = newC "b" (newP 2 1)
c3 = newC "c" (newP 3 4)
c4 = newC "d" (newP 4 8)
c5 = c1
c6 = newC "e" (newP 1 2)
c7 = newC "f" (newP 9 7)


q1 = newQ "fo" 5 10
q2 = newQ "co" 2 40

--l1 = newL c1 c2 q2
--l2 = newL c2 c3 q1
--l3 = newL c3 c1 q1
--l4 = newL c3 c4 q1

-- Creamos una region y le agregamos ciudades
r = newR
r1 = foundR r c1
r2 = foundR r1 c2
r3 = foundR r2 c3
r4 = foundR r3 c4

-- Le agregamos links
r5 = linkR r4 c1 c2 q2
r6 = linkR r5 c2 c3 q1
r7 = linkR r6 c3 c1 q1
r8 = linkR r7 c3 c4 q1

-- Le agregamos tuneles
r9 = tunelR r8 [c1,c2,c3]
r10 = tunelR r9 [c1,c2,c3,c4]

test_funcionalidad_regiones = [r, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10]