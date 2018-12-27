
// Rules for Alzheimer's

a2 := trouble_rememberin_new_events
b2 := trouble_rememberin_previous_conversations
c2 := trouble_recognizing_names OR  trouble_recognizing_faces
d2 := trouble_with_paying_bills 
e2 := mistakes_with_taking_medication
f2 := trouble_with_daily_commute


cognitive_deterioration := a2 + b2 + c2 + d2 + e2 + f2 >= 3

possible_alzheimers := cognitive_deterioration
