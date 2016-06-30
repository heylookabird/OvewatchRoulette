from flask import Flask, render_template, request
import json
from flask_sqlalchemy import SQLAlchemy






application = api = Flask(__name__)
#application.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:pass@127.0.0.1:3306/StratRoulette'
application.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://dbmaster:Pa$$w0rd@overwatchroulette.cdlwhilaiijd.us-west-1.rds.amazonaws.com:3306/StratRoulette'

db = SQLAlchemy(application)

class Maps(db.Model):
	__tablename__ = 'Maps'
	id = db.Column('id', db.Integer, primary_key=True)
	name = db.Column('name', db.Unicode)
	map_type = db.Column('map_type', db.Unicode)

	def as_dict(self):
		return {c.name: getattr(self, c.name) for c in self.__table__.columns}


class Strats(db.Model):

	__tablename__ = 'Strats'
	id = db.Column('id', db.Integer, primary_key=True)
	name = db.Column('name', db.Unicode)
	map_id = db.Column('map_id', db.Integer)
	description = db.Column('description', db.Unicode)
	team = db.Column('team', db.Integer)

def get_maps():
	map_list = list()
	for row in Maps.query.all():
		map_list.append(row.as_dict())

	return map_list


@application.route('/')
def landing():
	# Set the path to the database and make the connection
	# Found the account!
	map_dict = json.dumps(get_maps())

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

	return json.dumps(get_maps())


if __name__ == '__main__':
	application.debug = True
	application.run()