
// Heaviside unit step function.

ls := input  < 0
gr := input  > 0
eq := input == 0

!heaviside := ls*0 + eq*0.5 + gr*1