-- Actor table population
insert into leoforfriends.actor (email,username,hashed_password,name,surname,academic_title,rating,certified)
	values ('zhang@gmail.com','zhangguang','-946852072','Guangzheng','Zhang','Bachelor Degree in Information Engineering',5,'Yes');

insert into leoforfriends.actor (email,username,hashed_password,name,surname,academic_title,rating,certified)
	values ('giovannini@gmail.com','giova','-946852072','Stefano','Giovannini','Bachelor Degree in Information Engineering',5,'Yes');

insert into leoforfriends.actor (email,username,hashed_password,name,surname,academic_title,rating,certified)
	values ('bonotto@gmail.com','mat','-946852072','Matteo','Bonotto','Bachelor Degree in Information Engineering',5,'Yes');

insert into leoforfriends.actor (email,username,hashed_password,name,surname,academic_title,rating,certified)
	values ('rossi@gmail.com','gian','-946852072','Gianmaria','Rossi','Bachelor Degree in Information Engineering',5,'Yes');

insert into leoforfriends.actor (email,username,hashed_password,name,surname,academic_title,rating,certified)
	values ('kalakonda@gmail.com','sri','-946852072','Srikanth','Kalakonda Reddy','sfdf',5,'Yes');

insert into leoforfriends.actor (email,username,hashed_password,name,surname,academic_title,rating,certified)
	values ('admin@email.com ','admin','92668751','admin','admin',' ',0,'No');

-- Question table population
insert into leoforfriends.question (title,content,actor)
	values ('job interview in Wtd company','In my CV do I need to put all the previous work experiences?','zhangguang');

insert into leoforfriends.question (title,content,actor)
	values ('job interview in Google company','Tomorrow I have a job interview in the Google company. What I need to bring with me?','gian');

insert into leoforfriends.question (title,content,actor)
	values ('job interview in Amazon company','Yesterday I had a job interview in the Amazon company and they said that they will call me when they have the result. I want to ask more or less how many days do I need to wait?','zhangguang');

insert into leoforfriends.question (title,content,actor)
	values ('job interview in Tesla company','Yesterday I received a call from Tesla, is this a good company?','zhangguang');			

insert into leoforfriends.question (title,content,actor)
	values ('job interview in Wtd company','In my CV do I need to put all the previous work experiences and my english level?','giova');

insert into leoforfriends.question (title,content,actor)
	values ('Testing questions','Is this the proper way of testing questions?','giova');

insert into leoforfriends.question (title,content,actor)
	values ('Is the water wet?','blablabla','admin');

insert into leoforfriends.question (title,content,actor)
	values ('Final HW1 testing question','I''d like to know if I can end up with inserting testing questions','giova');	


-- Rates1 table population
insert into leoforfriends.rates1 (question,actor,rating,complexity)
	values (1,'zhangguang','4','Medium');

insert into leoforfriends.rates1 (question,actor,rating,complexity)
	values (2,'gian','2','Easy');

insert into leoforfriends.rates1 (question,actor,rating,complexity)
	values (3,'zhangguang','3','Hard');

insert into leoforfriends.rates1 (question,actor,rating,complexity)
	values (4,'zhangguang','1','Easy');

insert into leoforfriends.rates1 (question,actor,rating,complexity)
	values (5,'giova','5','Hard');

-- Answer table population
insert into leoforfriends.answer(question, actor, content)
	values (1,'giova','Yes, you need to put all the previous job experiences.');

insert into leoforfriends.answer(question, actor, content)
	values (1,'mat','Yes, is better if you put all the previous job experiences, for a better presentation.');

insert into leoforfriends.answer(question, actor, content)
	values (2,'zhangguang','You need to bring with you your CV and go there with a formal clothes.');

insert into leoforfriends.answer(question, actor, content)
	values (2,'giova','All the neccesary for a good presentation of yourself.');

insert into leoforfriends.answer(question, actor, content)
	values (2,'sri','CV, your documents, formal clothes.');

-- company table population
insert into leoforfriends.company (name,logo)
	values ('Wtd','star.png');

insert into leoforfriends.company (name,logo)
	values ('Google','circle.png');

insert into leoforfriends.company (name,logo)
	values ('Amazon','home.png');

insert into leoforfriends.company (name,logo)
	values ('Tesla','token.png');

-- topic table population
insert into leoforfriends.topic (name) values ('physics');
insert into leoforfriends.topic (name) values ('ICT');
insert into leoforfriends.topic (name) values ('electronics');
insert into leoforfriends.topic (name) values ('teaching');
insert into leoforfriends.topic (name) values ('artisanship');
insert into leoforfriends.topic (name) values ('tourism');
insert into leoforfriends.topic (name) values ('physics research');
insert into leoforfriends.topic (name) values ('database manager');
insert into leoforfriends.topic (name) values ('network manager');
insert into leoforfriends.topic (name) values ('computer');
insert into leoforfriends.topic (name) values ('electronics shop');
insert into leoforfriends.topic (name) values ('scientifics');
insert into leoforfriends.topic (name) values ('humanistics');

--is contained table population
insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('physics research','physics');

insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('database manager','ICT');

insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('network manager','ICT');

insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('computer','electronics');

insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('electronics shop','electronics');

insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('scientifics','teaching');

insert into leoforfriends.is_contained (subtopic,supertopic)
	values ('humanistics','teaching');