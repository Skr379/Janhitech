
# coding: utf-8

# In[1]:


import csv
from random import randint


# In[2]:


diseases= ["arthritis","asthma","ASD","cancer","chlamydia","ebola","diabetes","malaria","HIV","cirrhosis","migraine", 
           "heart disease","rhinovirus","influenza","stroke","alzheimer disease","tuberculosis","thyroid"]


# In[3]:


symptoms= ["headache","nausea","vomiting","high fever","diarrhea","fatigue","muscle aches","coughing","pain","stiffness",
           "swelling","redness","wheezing","learning disability","epilepsy","chronic pain","blood discharge","sneezing",
           "pain during urination","genital pain","palpitation","weakness","faster heartbeat", "nasal dryness","sore throat",
           "abdominal pain","weight loss","chills","chest pain","back pain","tired","blindness","blurred vision","dry mouth",
           "paralysis","dizziness","memory loss","nose bleed","jaundice","constipation"]


# In[4]:


disease_associations= {}
disease_associations['arthritis'] = {'pain', 'stiffness', 'swelling', 'redness'}

disease_associations['asthma'] = {'chest pain','coughing','wheezing'}

disease_associations['ASD'] = {'epilepsy', 'learning disability'}

disease_associations['cancer'] = {'chronic pain', 'swelling', 'blood discharge'}

disease_associations['chlamydia'] = {'pain during urination', 'blood discharge'}

disease_associations['ebola'] = {'fever', 'headache', 'muscle aches', 'diarrhea'}

disease_associations['diabetes'] = {'blurred vision', 'dry mouth', 'fatigue', 'nausea', 'vomiting'}

disease_associations['malaria'] = {'high fever', 'headache', 'nausea', 'vomiting', 'muscle aches', 'chills'}

disease_associations['HIV'] = {'nausea', 'fatigue', 'vomiting', 'muscle aches', 'genital pain'}

disease_associations['heart disease'] = {'palpitation', 'weakness', 'faster heartbeat', 'sweating'}

disease_associations['rhinovirus'] = {'nasal dryness', 'sore throat', 'headache', 'sneezing'}

disease_associations['influenza'] = {'high fever', 'headache', 'muscle aches', 'weakness', 'sore throat', 'coughing'}

disease_associations['stroke'] = {'weakness', 'paralysis', 'dizziness', 'headache', 'blurred vision'}

disease_associations['alzheimer disease'] = {'memory loss', 'blurry vision'}

disease_associations['tuberculosis'] = {'coughing', 'chest pain', 'fatigue', 'fever', 'blood discharge'}

disease_associations['cirrhosis'] = {'nose bleed', 'weight loss', 'jaundice'}

disease_associations['thyroid'] = {'fatigue', 'constipation', 'chills', 'muscle aches'}

disease_associations['migraine'] = {'constipation', 'tired', 'headache'}


# In[30]:


def generator(disease_associations, diseases, symptoms, noise=5):
    with open('record.csv', 'w', newline='') as csvfile:
        fieldnames = ['symptoms', 'disease']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for i in range(1,100000):
            rID = randint(0, 17)
            if i%7 == 0:
                if rID==17:
                    writer.writerow({'symptoms':disease_associations[diseases[rID]], 'disease':diseases[rID-1]})
                else:
                    writer.writerow({'symptoms':disease_associations[diseases[rID]], 'disease':diseases[rID+1]})
            else:
                writer.writerow({'symptoms':disease_associations[diseases[rID]], 'disease':diseases[rID]})
    return;


# In[31]:


if __name__== "__main__":
    print(disease_associations)
    generator(disease_associations, diseases, symptoms, 5)

