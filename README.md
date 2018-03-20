# Janhitech

Centralised, Nationalised and Secure Repository of Medical Records linked to AADHAAR.

SUMMARY:
The proposed solution is a centralised repository of all the medical records linked to Aadhaar of a citizen. Hadoop is used to store 
the bigdata. Deep neural nets for analysis. RESTful APIs to seamlessly decouple logic layer from Frontend(Angular4+).
A doctor can access the medical records of a person by taking his/her approval(OTP authentication to the registered mobile
number) and hence he/she can access information from a more trustworthy source in a more streamlined manner without a prevalent 
equivocation. Deep learning models can be trained on such huge amounts of data and the doctor will be provided with some meaningful 
insights from patterns observed through the modelled data. The quality of diagnosis will improve, greatly helping the doctors and the
cases of wrongful diagnosis will be greatly reduced. 


PROBLEM STATEMENT:
This problem is related to how the medical history of the people is handled by various hospitals. In the age of digitalisation, when 
something abstract such as education to something representational such as a currency has been digitalised to a great depth, we still 
see a very important domain of healthcare still being handled non-centralised, non-digitalised in a great sense. The problem statement 
can be seen by taking a very general example as follows:
An old man comes to a hospital with pain in his chest for the past few days. Now, the doctor has to give him medicines based on his
medical history because the prescriptions can have side effects depending on the previous and current ailments that the person has. 
But the patient might not be having all the previous records of his diagnosis and might miss some important details while providing
a verbal record. Now the doctor does some scans and finds out the patient has X, and armed with the half-baked knowledge provided by 
the patient, he/she provides a diagnosis which can cause further complications, may not treat the patient or the effect of it can b
e slower. Further, the lack of centralisation of these medical records depending upon the hospital of origination leads to a state of
turmoil hindering any efficient analysis.


TECH STACK:

1. Front End: HTML5, CSS3, JavaScript on Angular 4+ framework.
2. MiddleWare(Database): JVM based RESTful API server.
3. Database: Stored as Big Data using HADOOP framework.
4. Data Analytics: Keras Framework for serving Deep Neural Networks for Sequence Models.
5. MiddleWare(Data Analytics): Flask framework for serving requests.
