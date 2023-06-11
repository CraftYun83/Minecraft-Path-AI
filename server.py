from flask import Flask, Response,request
import csv, json
app = Flask(__name__)

mappings = {
   
}

count = 1

header = ['spawn', 'checkpoint1', 'destination']

nextrow = ["", "", ""]

with open('dataset.csv', 'w', encoding='UTF8', newline='') as f:
    writer = csv.writer(f)
    writer.writerow(header)

    f.close()

@app.route('/collect',methods = ['POST'])
def index():
    global nextrow

    with open('dataset.csv', 'a', encoding='UTF8', newline='') as f:
        writer = csv.writer(f)
        if request.args["args"] == "spawn":
            nextrow[0] = str(mappings[request.args["args"]][0])
        if "1" in request.args["args"]:
            nextrow[1] = str(mappings[request.args["args"]][0])
        if "2" in request.args["args"] or "3" in request.args["args"]:
            nextrow[2] = str(mappings[request.args["args"]][0])
            writer.writerow(nextrow)
            nextrow = ["", "", ""]

        f.close()
    
    return Response("", status=200)

@app.route("/createLocation",methods = ['POST'])
def createLocation():
    global count, mappings
    arguments = request.args["args"].split(";")
    if len(arguments) == 4:
      mappings[arguments[0]] = [count, {
          "x": arguments[1],
          "y": arguments[2],
          "z": arguments[3],
      }]

      count += 1
    with open('mappings.json', 'w') as fp:
        json.dump(mappings, fp)

    return Response("", status=200)

app.run(port=6942)