import tensorflow as tf
import numpy as np
from tensorflow import keras
import os

indexOfPrediction = 1

# Predicting Spawn to Checkpoint 1

datasetArray = np.loadtxt("dataset.csv", delimiter=",", dtype=str)

starting_points = np.array([], dtype=float)
checkpoint1 = np.array([], dtype=float)
destinations = np.array([], dtype=float)

for i in range(1, datasetArray.shape[0]):
    starting_points = np.append(starting_points, int(datasetArray[i][0]))
    checkpoint1 = np.append(checkpoint1, int(datasetArray[i][1]))
    destinations = np.append(destinations, int(datasetArray[i][2]))

model_name = "spawnToCP1"

if not os.path.exists(model_name):
    

  model = tf.keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])

  model.compile(optimizer="sgd", loss="mean_squared_error")

  model.fit(starting_points, checkpoint1, epochs=20000)

  model.save(model_name)

model = tf.keras.models.load_model(model_name)

def find_nearest(array, value):
    array = np.asarray(array)
    idx = (np.abs(array - value)).argmin()
    return array[idx]

results = model.predict([1])
rounded_results = [int(find_nearest(checkpoint1, num)) for num in checkpoint1]

predicted_cp1 = rounded_results[indexOfPrediction]

# Predicting Checkpoint 1 to Destination


model_name = "CP1Todestination"

if not os.path.exists(model_name):
    

  model = tf.keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])

  model.compile(optimizer="sgd", loss="mean_squared_error")

  model.fit(checkpoint1, destinations, epochs=20000)

  model.save(model_name)

model = tf.keras.models.load_model(model_name)

def find_nearest(array, value):
    array = np.asarray(array)
    idx = (np.abs(array - value)).argmin()
    return array[idx]

results = model.predict([predicted_cp1])
rounded_results = [int(find_nearest(destinations, num)) for num in destinations]

predicted_destination = rounded_results[indexOfPrediction]

print("1 => "+str(predicted_cp1)+" => "+str(predicted_destination))