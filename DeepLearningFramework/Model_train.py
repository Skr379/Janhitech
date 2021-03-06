
# coding: utf-8

# In[248]:


import keras


# In[249]:


import os
import numpy
# from keras.datasets import patient
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM
from keras.layers.embeddings import Embedding
from keras.preprocessing import sequence
from keras.wrappers.scikit_learn import KerasClassifier
from keras.utils import np_utils
from keras.models import model_from_json

import pandas
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import KFold
from sklearn.preprocessing import LabelEncoder
from sklearn.feature_extraction.text import CountVectorizer
from io import StringIO

# fix random seed for reproducibility
numpy.random.seed(7)


# In[250]:


pandas.set_option('display.max_colwidth', 1000)
print(os.getcwd())
# dataset = pandas.read_csv(StringIO(s), quotechar='"', skipinitialspace=True, error_bad_lines = False)
dataset = pandas.read_csv('record.csv')
dataset = pandas.DataFrame(dataset)
dataset = dataset.dropna(axis=0, how='any')
X = dataset['symptoms']
Y = dataset['disease']

# encode class values as integers
encoder_Y = LabelEncoder()
docs2 = ["arthritis","asthma","ASD","cancer","chlamydia","ebola","diabetes","malaria","HIV","cirrhosis","migraine", 
        "heart disease","rhinovirus","influenza","stroke","alzheimer disease","tuberculosis","thyroid"]
encoder_Y.fit(docs2)
encoded_Y = encoder_Y.transform(Y)
# convert integers to dummy variables (i.e. one hot encoded)
Y = np_utils.to_categorical(encoded_Y)
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2)


# In[251]:



# define 5 documents
docs = ["headache","nausea","vomiting","high fever","diarrhea","fatigue","muscle aches","coughing","pain","stiffness",
        "swelling","redness","wheezing","learning disability","epilepsy","chronic pain","blood discharge","sneezing",
        "pain during urination","genital pain","palpitation","weakness","faster heartbeat", "nasal dryness","sore throat",
        "abdominal pain","weight loss","chills","chest pain","back pain","tired","blindness","blurred vision","dry mouth",
        "paralysis","dizziness","memory loss","nose bleed","jaundice","constipation"]

vocab_size = 70
max_review_length = 200
MAX_NUM_WORDS = 1000
vectorizer = CountVectorizer()
# tokenize and build vocab
vectorizer.fit(docs)
# encode document
X_train_vector = vectorizer.transform(X_train)
X_test_vector = vectorizer.transform(X_test)
X_train =  X_train_vector.toarray()
X_test =  X_test_vector.toarray()
#padding the data
X_train = keras.preprocessing.sequence.pad_sequences(X_train, maxlen=max_review_length, dtype='object', 
                                           padding='pre', truncating='pre', value=0.)
X_test = keras.preprocessing.sequence.pad_sequences(X_test, maxlen=max_review_length, dtype='object', 
                                           padding='pre', truncating='pre', value=0.)


# In[252]:


# create the model
embedding_vecor_length = 32
model = Sequential()
model.add(Embedding(200, embedding_vecor_length, input_length=max_review_length))
model.add(LSTM(100))
model.add(Dense(18, activation='softmax'))
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
print(model.summary())
model.fit(X_train, Y_train, validation_data=(X_test, Y_test), epochs=3, batch_size=64)


# In[ ]:


# Final evaluation of the model
scores = model.evaluate(X_train, Y_train, verbose=0)
print("Accuracy: %.2f%%" % (scores[1]*100))


# In[ ]:


# Final evaluation of the model
scores = model.evaluate(X_test, Y_test, verbose=0)
print("Accuracy: %.2f%%" % (scores[1]*100))


# In[ ]:


# serialize model to JSON
model_json = model.to_json()
with open("D:\\Hackathon\\trained_model\\model_categorical.json", "w") as json_file:
    json_file.write(model_json)
# serialize weights to HDF5
model.save_weights("D:\\Hackathon\\trained_model\\model_categorical.h5")
print("Saved model to disk")

