import Point
import City
import Quality
import Link
import Tunel
import Region

p1 = newP 1 2
p2 = newP 2 1
p3 = newP 3 4
p4 = newP 4 8

c1 = newC "a" p1
c2 = newC "b" p2
c3 = newC "c" p3
c4 = newC "d" p4

q1 = newQ "fo" 5 10
q2 = newQ "co" 2 40

--q2 = newQ "co" 3 40

l1 = newL c1 c2 q1
l2 = newL c2 c3 q2
l3 = newL c3 c1 q2
l4 = newL c1 c4 q1

r = newR
r1 = foundR r c1
r2 = foundR r1 c2
r3 = foundR r2 c3
r4 = foundR r3 c4

r5 = linkR r4 c1 c2 q1
r6 = linkR r5 c2 c3 q2
r7 = linkR r6 c3 c1 q2
r8 = linkR r7 c1 c4 q1

r9 = tunelR r8 [c1,c2,c3]
r10 = tunelR r9 [c4,c1,c2,c3]

tunel = newT [l1, l2, l3]

usest = usesT l1 tunel