from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy
import models
import json

app = api = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://dbmaster:Pa$$w0rd@overwatchroulette.cdlwhilaiijd.us-west-1.rds.amazonaws.com:3306/StratRoulette'
db = SQLAlchemy(app)



@app.route('/')
def landing():
	# Set the path to the database and make the connection
	# Found the account!
	map_dict = json.dumps(models.get_maps())

	return render_template('hello.html', header="Welcome to Overwatch StratRoulette!", map_list=map_dict)
@app.route('/map/<map_name>')
def map_page():

	return render_template('')
@app.route('/suggestions')
def suggestions():
	map = request.args.get('map')
	team = request.args.get('team')
	strat = request.args.get('strat')

	return map

@app.route('/get_strats')
def activate_account(map_name):

	return json.dumps(models.get_maps())


if __name__ == '__main__':
	app.debug = True
	app.run()