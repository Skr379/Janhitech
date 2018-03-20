
# coding: utf-8

# In[79]:


import numpy as np
import flask
import io

import json
from keras.models import model_from_json, load_model
from keras.preprocessing.text import Tokenizer
from keras.utils import np_utils
from sklearn.preprocessing import LabelEncoder
from sklearn.feature_extraction.text import CountVectorizer
# initialize our Flask application and the Keras model
app = flask.Flask(__name__)
model = None


# In[80]:


def load_model():
    from keras.models import model_from_json
    global model
    # Model reconstruction from JSON file
    with open('D:\\Hackathon\\trained_model\\model.json', 'r') as f:
        model = model_from_json(f.read())
        
    model.load_weights('D:\\Hackathon\\trained_model\\model.h5')


# In[81]:


def prepare_data(symptoms):
    import keras
    print("code related to preprocessing the get request will be put here")
    docs = ["headache","nausea","vomiting","high fever","diarrhea","fatigue","muscle aches","coughing","pain","stiffness",
           "swelling","redness","wheezing","learning disability","epilepsy","chronic pain","blood discharge","sneezing",
           "pain during urination","genital pain","palpitation","weakness","faster heartbeat", "nasal dryness","sore throat",
           "abdominal pain","weight loss","chills","chest pain","back pain","tired","blindness","blurred vision","dry mouth",
           "paralysis","dizziness","memory loss","nose bleed","jaundice","constipation"]

    max_review_length = 200
    vectorizer = CountVectorizer()
    # tokenize and build vocab
    vectorizer.fit(docs)
    print(vectorizer.vocabulary_)
    symptoms_list = []
    symptoms_list.append(symptoms)
    X_vector = vectorizer.transform(symptoms_list)
    X = X_vector.toarray()
    print(X)
    X = keras.preprocessing.sequence.pad_sequences(X, maxlen=max_review_length, dtype='object', 
                                           padding='pre', truncating='pre', value=0.)
    print("done preprocessing!!")
    return X


# In[82]:


def predict_diseases(predictions):
    encoder_Y = LabelEncoder()
    diseases = ["arthritis","asthma","ASD","cancer","chlamydia","ebola","diabetes","malaria","HIV","cirrhosis","migraine", 
                "heart disease","rhinovirus","influenza","stroke","alzheimer disease","tuberculosis","thyroid"]
    encoder_Y.fit(diseases)
    encoded_docs = encoder_Y.transform(diseases)
    # convert integers to dummy variables (i.e. one hot encoded)
    Y = np_utils.to_categorical(encoded_docs)
    print(predictions)
    predicted_probability = np.amax(predictions)
    predicted_probability = float("{0:.2f}".format(predicted_probability*100))
    arg = np.argmax(predictions)
    print(arg)
    predicted_disease = encoder_Y.inverse_transform(arg)
    print(predicted_disease)
    predictions = {'probability':predicted_probability, 'disease':predicted_disease}
#     predictions['probability']= str(predicted_probability)
#     predictions['disease']= str(predicted_disease)
    return predictions


# In[ ]:


@app.route("/predict", methods=["POST"])
def predict():
    # initialize the data dictionary that will be returned from the
    # view
    data = {"success": False}

    # ensure an image was properly uploaded to our endpoint
    if flask.request.method == "POST":
        try:
            post_data = flask.request.get_json(force=True)
            print(post_data.get("symptoms"))
            # preprocess the image and prepare it for classification
            X = prepare_data(str(post_data.get("symptoms")))
            
            data["predictions"] = []
            # classify the input image and then initialize the list
            # of predictions to return to the client
            predicted = model.predict(X)
            predictions = predict_diseases(predicted)
            r = {"probability": str(predictions['probability']), "disease": predictions['disease'] }
            data["predictions"].append(r)
            print("Prediction done")
            
#             # loop over the results and add them to the list of
#             # returned predictions
#             for (imagenetID, label, prob) in results[0]:
#                 r = {"label": label, "probability": float(prob)}
#                 data["predictions"].append(r)

#             # indicate that the request was a success
            data["success"] = True
        except ValueError:
            print(ValueError)

    # return the data dictionary as a JSON response
    return flask.jsonify(data)


# In[ ]:


if __name__ == "__main__":
    print(("* Loading Keras model and Flask starting server..."
        "please wait until server has fully started"))
    load_model()
    app.run(host ='0.0.0.0',port=5000)

