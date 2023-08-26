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
test_errores_regiones = [
    testF(r1),
    testF(r2),
    testF(r3),
    testF(r4),
    testF(r5),
    testF(r6),
    testF(r7),
    testF(r8),
    testF(r9),
    testF(r10)
    ] -- debe dar todo False
------------------------------------------------ERRORES------------------------------------------------

--Errores de foundR
r11 = foundR r10 c5 -- la ciudad ya esta en la region
r12 = foundR r10 c6 -- la ciudad tiene la misma direccion que una ciudad de la region

--Errores de linkR
r13 = linkR r10 c1 c2 q2 -- el link ya esta creado
r14 = linkR r10 c1 c7 q1 -- la ciudad c7 no esta en la region
r15 = linkR r10 c1 c5 q1 -- Las ciudades son la misma

--Errores de tunelR
r16 = tunelR r10 [c1,c2,c3,c7] -- c7 no esta en la region
r17 = tunelR r10 [c4,c1,c2] -- c4 y c1 no estan enlazadas
r18 = tunelR r10 [c1,c2,c3] -- el tunel esta creado
r19 = tunelR r10 [c1,c2] -- el link de c1 a c2 se quedo sin capacidad
r20 = tunelR r10 [c1,c2,c3,c1] -- no se puede hacer un tunel entre la misma ciudad

--Error delayR
delay2 = delayR r10 c2 c4 -- c2 a c4 no estan conectadas por un tunel

--Error availableCapacityForR
capacity3 = availableCapacityForR r10 c2 c4 -- no estan enlazadas
capacity4 = availableCapacityForR r10 c1 c7 -- c7 no esta en la region


test_errores = [
    testF(r11),
    testF(r12), 
    testF(r13), 
    testF(r14), 
    testF(r15), 
    testF(r16), 
    testF(r17), 
    testF(r18), 
    testF(r19), 
    testF(r20), 
    testF(delay2), 
    testF(capacity3), 
    testF(capacity4)
    ] -- debe dar todo True