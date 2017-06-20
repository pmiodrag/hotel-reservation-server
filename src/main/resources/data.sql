
INSERT INTO  `hotel` (id, name, rating, total_rooms) VALUES 
												(1,'Rossa de mar', 'THREE_STAR',20),
												 (2, 'Ibis', 'THREE_STAR',35), 
												(3,'Double Tree', 'FOUR_STAR',30);
INSERT INTO `hotel_room_type` (id, hotel_id, room_type, price) VALUES 
											(1, 1,'SINGLE',60.0), (2, 1,'DOUBLE',100.0),
											 (3, 2,'SINGLE',65.0), (4, 2,'DOUBLE',130.0),
											 (5, 3,'SINGLE',80.0), (6, 3,'DOUBLE',140.0) ;