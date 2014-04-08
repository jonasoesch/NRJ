NRJ API
=======
/* penser à changer kilowatt en watt */



/apartment/consumption
	(changer apartment en home)
	GET		overall consumption

			
				| /rooms		|
----------------|---------------|
GET				|	all rooms	|
POST			|	new room	|



|	|/rooms/{id}		|
------------------------------|
GET	|room id			|
PUT	|update room id		|
DELETE|delete room id		|

|	|/rooms/{id}/consumption								|
------------------------------------------------------------------------|
GET	|room id current (last minute average) consumption				|

|	|/plugs			|
------------------------------|
GET	|all plugs			|
POST	|new plug			|

|	|/plugs/{id}			|
------------------------------------|
GET	|plug id				|
PUT	|update plug id			|
DELETE|delete plug id			|

|	|/plugs/{id}/consumption								|
------------------------------------------------------------------------|
GET	|plug id current (last minute average) consumption				|
POST	|New measure of plug ids consumption						|
	

/plugs/{id}/consumption?from={timestamp}&to={timestamp}
/apartment/consumption?today
/apartment/bill
