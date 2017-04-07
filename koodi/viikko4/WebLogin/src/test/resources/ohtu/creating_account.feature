Feature: A new user account can be created if a proper unused username and password are given

	Scenario: creation successful with correct username and password
		Given new user is selected
		When unused username "toimiva" and correct password "salainen1" are given
		Then new account is created and user is redirected to welcoming page

	Scenario: creation fails with too short username and valid password
		Given new user is selected
		When too short username "a" and correct password "salainen1" are given
		Then user is not created and error "username should have at least 3 characters" is reported

	Scenario: creation fails with correct username and too short password
		Given new user is selected
		When unused username "toimiva" and too short password "huono1" are given
		Then user is not created and error "password should have at least 8 characters" is reported

	Scenario: creation fails with correct username and password consisting of letters
		Given new user is selected
		When unused username "toimiva" and alphabetical password "edelleenhuono" are given
		Then user is not created and error "password can not contain only letters" is reported

	Scenario: creation fails with already taken username and valid password
		Given new user is selected
		When taken username "varattu" and correct password "salainen1" are given
		Then user is not created and error "username is already taken" is reported

	Scenario: creation fails when password and password confirmation do not match
		Given new user is selected
		When mismatching passwords are given
		Then user is not created and error "password and password confirmation do not match" is reported
