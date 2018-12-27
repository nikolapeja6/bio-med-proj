

// Autism.

a1 := Abnormal_Body_Posturing OR Abnormal_Facial_Expressions
b1 := Avoidance_of_Eye_Contact OR Poor_Eye_Contact
c1 := Delay_in_Learning_to_Speak
d1 := Intense_Focus_on_One_Topic
e1 := Lack_of_Empathy OR Lack_of_Understanding_Social_Cues
f1 := Repetitive_Movements
g1 := Social_Withdrawal

autism := a1 * 0.1 + b1 * 0.1 + c1 * 0.5 + d1 * 0.2 + e1 * 0.6 + f1 * 0.6 +  g1 * 0.4 >= 90
