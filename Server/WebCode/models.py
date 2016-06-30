#map = Maps(name=<name>, map_type=<map_type>)
#session.add(map)
#session.commit()
from application import db

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