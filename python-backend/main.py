import os
from dotenv import load_dotenv
import pronotepy
from flask import Flask, jsonify


client = pronotepy.Client(pronote_url=os.getenv("PRONOTE_URL"),
                          username=os.getenv("USERNAME"),
                          password=os.getenv("PASSWORD")
                          )

app = Flask(__name__)

@app.route('/grades', methods = ["GET"])
def getgrades():
    period = client.current_period
    grades = []
    for grade in period.grades :
        grades.append(grade.to_dict())
    return jsonify(grades)