from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy
import models
import json

application = api = Flask(__name__)
#application.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:pass@127.0.0.1:3306/StratRoulette'
application.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://dbmaster:Pa$$w0rd@overwatchroulette.cdlwhilaiijd.us-west-1.rds.amazonaws.com:3306/StratRoulette'
db = SQLAlchemy(application)



@application.route('/')
def landing():
	# Set the path to the database and make the connection
	# Found the account!
	map_dict = json.dumps(models.get_maps())

	return render_template('hello.html', header="Welcome to Overwatch StratRoulette!", map_list=map_dict)
@application.route('/map/<map_name>')
def map_page():

	return render_template('')
@application.route('/suggestions')
def suggestions():
	map = request.args.get('map')
	team = request.args.get('team')
	strat = request.args.get('strat')

	return map

@application.route('/get_strats')
def activate_account(map_name):

	return json.dumps(models.get_maps())


if __name__ == '__main__':
	application.debug = True
	application.run()