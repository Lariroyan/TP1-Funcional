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


q1 = newQ "fo" 5 10
q2 = newQ "co" 2 40

l1 = newL c1 c2 q2
l2 = newL c2 c3 q1
l3 = newL c3 c1 q1
l4 = newL c3 c4 q1

r = newR
r1 = foundR r c1
r2 = foundR r1 c2
r3 = foundR r2 c3
r4 = foundR r3 c4
regionConCiudadExistente = foundR r4 c1
c5 = newC "p" (newP 1 2)

r6 = linkR r4 c1 c2 q2
r7 = linkR r6 c2 c3 q1
r8 = linkR r7 c3 c1 q1
r9 = linkR r8 c3 c4 q1


r10 = tunelR r9 [c1,c2,c3]
r11 = tunelR r10 [c1,c2,c3,c4]
r12 = tunelR r11 [c2,c3,c4]


--errores
r5 = foundR r4 c1 -- la ciudad ya esta en la region
r13 = linkR r9 c1 c2 q2 -- el link ya esta creado
r14 = linkR r9 c1 c5 q1 -- la ciudad c5 no esta en la region
r15 = tunelR r12 [c1,c2] -- el link de c1 a c2 se quedo sin capacidad
r16 = tunelR r12 [c4,c1,c2] -- c4 y c1 no estan enlazadas
r17 = tunelR r12 [c1,c2,c3,c5] -- c5 no esta en la region
r18 = tunelR r12 [c1,c2,c3] -- el tunel esta creado




tunel = newT [l1,l2,l4]
tunel2 = newT [l1,l2]
tunellista = [tunel,tunel2]

test_errores = [testF()]