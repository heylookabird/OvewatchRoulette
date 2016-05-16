from flask import Flask, render_template
from flask_sqlalchemy import SQLAlchemy
import models
import json

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:pass@localhost:3306/StratRoulette'
db = SQLAlchemy(app)



@app.route('/')
def hello_world():
	return 'Hello World!'


@app.route('/map')
def activate_account():

	#Set the path to the database and make the connection


	# Found the account!
	map_dict = json.dumps(models.get_maps())

	return render_template('hello.html', header="Welcome to Overwatch StratRoulette!", map_list=map_dict)


if __name__ == '__main__':
	app.debug = True
	app.run(host='192.168.33.10')

