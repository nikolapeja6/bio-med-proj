
// Heaviside unit step function.

ls := x  < 0
gr := x  > 0
eq := x == 0

!y := ls*0 + eq*0.5 + gr*1