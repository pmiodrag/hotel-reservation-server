
INSERT INTO  `hotel` (id, name, rating, total_rooms) VALUES 
												(1,'Rossa de mar', 'THREE_STAR', 2),
												 (2, 'Ibis', 'THREE_STAR', 6), 
												(3,'Double Tree', 'FOUR_STAR',10);
INSERT INTO `hotel_room_type` (id, hotel_id, room_type, price) VALUES 
											(1, 1,'SINGLE',60.0), (2, 1,'DOUBLE',100.0),
											 (3, 2,'SINGLE',65.0), (4, 2,'DOUBLE',130.0),
											 (5, 2,'SINGLE',65.0), (6, 2,'DOUBLE',130.0),
											 (7, 2,'SINGLE',65.0), (8, 2,'DOUBLE',130.0),
											 (9, 3,'SINGLE',80.0), (10, 3,'DOUBLE',140.0),
											 (11, 3,'SINGLE',80.0), (12, 3,'DOUBLE',140.0),
											 (13, 3,'SINGLE',80.0), (14, 3,'DOUBLE',140.0),
											 (15, 3,'SINGLE',80.0), (16, 3,'DOUBLE',140.0),
											 (17, 3,'SINGLE',80.0), (18, 3,'DOUBLE',140.0) ;