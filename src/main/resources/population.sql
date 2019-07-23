INSERT INTO roles(
	role)
	VALUES ('admin');
INSERT INTO roles(
	role)
	VALUES ('user');

INSERT INTO users(
	first_name, last_name, email, password, role_id)
	VALUES ('Pavel', 'Ezhkov', 'pavel_ezhkov@epam.com', '36fc2acc4450f761f6ce53c194f9b37d:b0d3fe6c3298aab7c2cff9de20bc7efd', 2);
INSERT INTO users(
	first_name, last_name, email, password, role_id)
	VALUES ('Aleksandr', 'Isaev', 'Aleksandr_Isaev@epam.com', 'b8079747a62f7c62b6877a44bc7cc7ce:bd49013ebb7d96744ae8cf73e726ccec', 2);
INSERT INTO users(
	first_name, last_name, email, password, role_id)
	VALUES ('Maksim', 'Golota', 'Maksim_Golota@epam.com', '2411a7c6dc91c19a466277426111cd16:6efc9aaee374510d65a2f588e145eefd', 1);
INSERT INTO users(
	first_name, last_name, email, password, role_id)
	VALUES ('Aleksandr', 'Lutkov', 'Aleksandr_Lutkov@epam.com', '308de82c96f44a392e1c9963cc8f3f39:c5dc2d5b445eecef0ad895ffd291386f', 2);


INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000001, 1000, 1, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000004, 2000, 1, true);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000005, 3000, 1, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000002, 2000.10, 2, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000006, 5000.10, 2, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000007, 0.10, 2, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000003, 3000, 4, true);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000008, 1000.10, 1, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000009, 4000, 1, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000010, 2000.50, 2, true);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000011, 3000, 2, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000012, 4000.0, 4, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000013, 5000, 4, false);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000014, 0, 2, true);
INSERT INTO accounts(
	account_id, balance, user_id, isblocked)
	VALUES (12345678900000000015, 3000, 4, false);

	
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000013, 12345678900000000001);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000021, 12345678900000000002);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000039, 12345678900000000002);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000047, 12345678900000000003);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000054, 12345678900000000004);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000062, 12345678900000000005);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000070, 12345678900000000006);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000088, 12345678900000000007);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000096, 12345678900000000003);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000104, 12345678900000000006);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000112, 12345678900000000008);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000120, 12345678900000000009);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000138, 12345678900000000010);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000146, 12345678900000000011);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000153, 12345678900000000012);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000161, 12345678900000000013);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000179, 12345678900000000014);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000187, 12345678900000000015);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000195, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000203, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000211, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000229, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000237, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000245, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000252, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000260, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000278, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000286, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000294, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000302, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000310, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000328, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000336, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000344, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000351, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000369, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000377, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000385, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000393, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000401, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000419, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000427, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000435, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000443, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000450, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000468, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000476, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000484, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000492, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000500, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000518, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000526, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000534, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000542, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000559, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000567, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000575, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000583, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000591, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000609, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000617, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000625, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000633, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000641, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000658, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000666, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000674, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000682, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000690, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000708, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000716, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000724, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000732, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000740, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000757, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000765, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000773, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000781, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000799, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000807, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000815, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000823, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000831, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000849, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000856, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000864, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000872, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000880, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000898, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000906, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000914, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000922, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000930, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000948, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000955, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000963, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000971, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000989, null);
INSERT INTO cards(
	card_number, account_id)
	VALUES (2123450000000997, null);

INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000013, '2019-07-10 14:52:00', 1000, 12345678900000000005);
INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000013, '2019-07-11 14:52:00', 100, 12345678900000000006);
INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000021, '2019-07-10 14:52:00', 200, 12345678900000000005);
INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000039, '2019-07-10 14:52:00', 300, 12345678900000000006);
INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000039, '2019-07-11 14:52:00', 400, 12345678900000000006);
INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000096, '2019-07-11 14:52:00', 100, 12345678900000000006);
INSERT INTO transactions(
	card_number, date, amount, target_account)
	VALUES (2123450000000013, '2019-07-12 14:52:00', 500, 12345678900000000006);
