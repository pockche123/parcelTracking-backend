INSERT INTO admins (admin_id, username, password) VALUES (NEXTVAL('admin_seq'), 'admin1', 'goodman123');
INSERT INTO admins (admin_id, username, password) VALUES (NEXTVAL('admin_seq'), 'admin2', 'braveheart54');
INSERT INTO admins (admin_id, username, password) VALUES (NEXTVAL('admin_seq'), 'admin3', 'loverperson22');

INSERT INTO parcels (parcel_id, tracking, name, status, address, estimated_delivery_days, admin_id) VALUES (NEXTVAL('parcel_seq'), 'fifo123', 'Roger Smith', 'Accepted at the post office. Waiting to be picked up', '45 Cooper Street, Wrexham, WX12 5HH', 8, 1);
INSERT INTO parcels (parcel_id, tracking, name, status, address, estimated_delivery_days, admin_id) VALUES (NEXTVAL('parcel_seq'), 'getWreckedGB', 'Maggie Taylor', 'Dispatched', '65 Groove Road, Hamstead, H3 6AB', 5, 1);
INSERT INTO parcels (parcel_id, tracking, name, status, address, estimated_delivery_days, admin_id) VALUES (NEXTVAL('parcel_seq'), 'fight0flightGB', 'Layla Kai', 'In transit', '9 Julia Street, London, E5 5AE', 3, 2);
INSERT INTO parcels (parcel_id, tracking, name, status, address, estimated_delivery_days, admin_id) VALUES (NEXTVAL('parcel_seq'), 'hotlineBli99', 'Draford Sank', 'Accepted at the post office. Waiting to be picked up', '6 Nylon Road, Birmingham, B7 7AF', 7, 2);
INSERT INTO parcels (parcel_id, tracking, name, status, address, estimated_delivery_days, admin_id) VALUES (NEXTVAL('parcel_seq'), 'junejulyGB', 'Keira Daily', 'In your local courier. Waiting to be delivered.', '8 NewCastle Road, Manchester, M44 8AL', 2, 3);
INSERT INTO parcels (parcel_id, tracking, name, status, address, estimated_delivery_days, admin_id) VALUES (NEXTVAL('parcel_seq'), 'yankeed00dleGB', 'Peter Bush', 'In your local courier. Waiting to be delivered.', '90 Viper Road, Chelsea, CH3 8AH', 1, 3);

