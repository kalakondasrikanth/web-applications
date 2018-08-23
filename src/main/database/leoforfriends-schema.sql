-- Droppping the schema if already exist
DROP SCHEMA IF EXISTS leoforfriends CASCADE;

-- Creation of the schema
CREATE SCHEMA leoforfriends;
COMMENT ON SCHEMA leoforfriends IS 'Schema for containing the objects of the project of leoforfriends group';

-- Creation of the tables


-- Actor table
CREATE TYPE certification AS enum ('Yes','No');
CREATE TABLE leoforfriends.Actor 
(
	email VARCHAR(50),
	username VARCHAR(50) NOT NULL UNIQUE,
	hashed_password VARCHAR(20) NOT NULL,
    name VARCHAR(50),
    surname VARCHAR(50),
    academic_title VARCHAR(100),
	rating REAL NOT NULL,
    certified certification,
	PRIMARY KEY (email)
);

COMMENT ON TABLE leoforfriends.Actor IS 'Represents an actor.';
COMMENT ON COLUMN leoforfriends.Actor.email IS 'The unique email of an actor.';
COMMENT ON COLUMN leoforfriends.Actor.username IS 'The unique username of the actor.';
COMMENT ON COLUMN leoforfriends.Actor.hashed_password IS 'The password of the actor saved is hashed form.';
COMMENT ON COLUMN leoforfriends.Actor.name IS 'The name of the actor.';
COMMENT ON COLUMN leoforfriends.Actor.surname IS 'The surname of the actor.';
COMMENT ON COLUMN leoforfriends.Actor.academic_title IS 'The academic title own by the actor.';
COMMENT ON COLUMN leoforfriends.Actor.rating IS 'The rating of the actor, obtaining as an average of his/her questions and answer ratings.';
COMMENT ON COLUMN leoforfriends.Actor.certified IS 'Whether the actor has the status of certified or not.';

-- Profile_picture table
CREATE TABLE leoforfriends.Profile_picture 
(
    actor VARCHAR(50),
    photo TEXT,
    PRIMARY KEY (actor),
    FOREIGN KEY (actor) REFERENCES leoforfriends.Actor(username) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Profile_picture IS 'Represents the profile image of an actor.';
COMMENT ON COLUMN leoforfriends.Profile_picture.actor IS 'The unique username of an actor.';
COMMENT ON COLUMN leoforfriends.Profile_picture.photo IS 'The profile picture of the actor.';

-- Question table
CREATE TABLE leoforfriends.Question
(
    question_number serial,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    actor VARCHAR(50) NOT NULL,
    PRIMARY KEY(question_number),
    FOREIGN KEY(actor) REFERENCES leoforfriends.Actor(username) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Question IS 'Represents a question posted by an actor.';
COMMENT ON COLUMN leoforfriends.Question.question_number IS 'The unique autoincremented number provided by the DBMS.';
COMMENT ON COLUMN leoforfriends.Question.title IS 'The title of the question.';
COMMENT ON COLUMN leoforfriends.Question.content IS 'The content of the question.';
COMMENT ON COLUMN leoforfriends.Question.actor IS 'The unique username of an actor.';

-- Rates1 table
CREATE TYPE rate AS enum ('1','2','3','4','5');
CREATE TYPE complex AS enum ('Easy','Medium','Hard');
CREATE TABLE leoforfriends.Rates1
(
    question INT,
    actor VARCHAR(50),
    rating rate,
    complexity complex,
    PRIMARY KEY(question,actor),
    FOREIGN KEY(question) REFERENCES leoforfriends.Question(question_number) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(actor) REFERENCES leoforfriends.Actor(username) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Rates1 IS 'Represents the rate gived to a question by an actor.';
COMMENT ON COLUMN leoforfriends.Rates1.question IS 'The unique number provided to the question by the DBMS.';
COMMENT ON COLUMN leoforfriends.Rates1.actor IS 'The unique username of an actor.';
COMMENT ON COLUMN leoforfriends.Rates1.rating IS 'The rate gived to the question.';
COMMENT ON COLUMN leoforfriends.Rates1.complexity IS 'The complexity gived to the question.';

-- Topic table
CREATE TABLE leoforfriends.Topic
(
    name VARCHAR(50) PRIMARY KEY
);

COMMENT ON TABLE leoforfriends.Topic IS 'Represents a topic.';
COMMENT ON COLUMN leoforfriends.Topic.name IS 'The name of the topic.';

-- Is_contained table
CREATE TABLE leoforfriends.Is_contained
(
    subtopic VARCHAR(50),
    supertopic VARCHAR(50) NOT NULL,
    PRIMARY KEY(subtopic),
    FOREIGN KEY(subtopic) REFERENCES leoforfriends.Topic(name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(supertopic) REFERENCES leoforfriends.Topic(name) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Is_contained IS 'Represents the membership of topics.';
COMMENT ON COLUMN leoforfriends.Is_contained.subtopic IS 'The name of the subtopic.';
COMMENT ON COLUMN leoforfriends.Is_contained.supertopic IS 'The name of the supertopic.';

-- Has table
CREATE TABLE leoforfriends.Has
(
    question INT,
    topic VARCHAR(50),
    PRIMARY KEY(question,topic),
    FOREIGN KEY(question) REFERENCES leoforfriends.Question(question_number) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(topic) REFERENCES leoforfriends.Topic(name) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Has IS 'Represents which topic has a question.';
COMMENT ON COLUMN leoforfriends.Has.question IS 'The unique number provided to the question by the DBMS.';
COMMENT ON COLUMN leoforfriends.Has.topic IS 'The name of the topic.';

-- Answer table
CREATE TABLE leoforfriends.Answer
(
    question INT,
    actor VARCHAR(50),
    content TEXT NOT NULL,
    PRIMARY KEY(question,actor),
    FOREIGN KEY(question) REFERENCES leoforfriends.Question(question_number) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(actor) REFERENCES leoforfriends.Actor(username) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Answer IS 'Represents the answer gived to a question by an actor.';
COMMENT ON COLUMN leoforfriends.Answer.question IS 'The unique number provided to the question by the DBMS.';
COMMENT ON COLUMN leoforfriends.Answer.actor IS 'The unique username of an actor.';
COMMENT ON COLUMN leoforfriends.Answer.content IS 'The content of the answer.';

-- Company table
CREATE TABLE leoforfriends.Company
(
    name VARCHAR(100),
    logo TEXT NOT NULL,
    PRIMARY KEY(name)
);

COMMENT ON TABLE leoforfriends.Company IS 'Represents a company.';
COMMENT ON COLUMN leoforfriends.Company.name IS 'The name of the company.';
COMMENT ON COLUMN leoforfriends.Company.logo IS 'The logo of the company.';

-- Is_about table
CREATE TABLE leoforfriends.Is_about
(
    question INT,
    company VARCHAR(100),
    PRIMARY KEY(question, company),
    FOREIGN KEY(question) REFERENCES leoforfriends.Question(question_number) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(company) REFERENCES leoforfriends.Company(name) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Is_about IS 'Represents which company is reffered to in the question.';
COMMENT ON COLUMN leoforfriends.Is_about.question IS 'The unique number provided to the question by the DBMS.';
COMMENT ON COLUMN leoforfriends.Is_about.company IS 'The name of the company.';

-- Comment table
CREATE TABLE leoforfriends.Comment
(
    comment_number serial,
    actor VARCHAR(50) NOT NULL,
    answer_question INT NOT NULL,
    answer_actor VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY(comment_number),
    FOREIGN KEY(actor) REFERENCES leoforfriends.Actor(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(answer_question,answer_actor) REFERENCES leoforfriends.Answer(question,actor) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Comment IS 'Represents a comment to an answer.';
COMMENT ON COLUMN leoforfriends.Comment.comment_number IS 'The unique autoincremented number provided by the DBMS.';
COMMENT ON COLUMN leoforfriends.Comment.actor IS 'The unique username of the actor who gives the comment.';
COMMENT ON COLUMN leoforfriends.Comment.answer_question IS 'The unique number provided to the question by the DBMS.';
COMMENT ON COLUMN leoforfriends.Comment.answer_actor IS 'The unique email of the actor who wrote the answer.';
COMMENT ON COLUMN leoforfriends.Comment.content IS 'The content of the comment.';

-- Rates2 table
CREATE TABLE leoforfriends.Rates2
(
    actor VARCHAR(50),
    answer_question INT NOT NULL,
    answer_actor VARCHAR(50) NOT NULL,
    rating rate,
    FOREIGN KEY(actor) REFERENCES leoforfriends.Actor(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(answer_question,answer_actor) REFERENCES leoforfriends.Answer(question,actor) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE leoforfriends.Rates2 IS 'Represents the rate gived to an answer by an actor.';
COMMENT ON COLUMN leoforfriends.Rates2.actor IS 'The unique username of an actor who gives the rate to the answer.';
COMMENT ON COLUMN leoforfriends.Rates2.answer_question IS 'The unique number provided to the question by the DBMS.';
COMMENT ON COLUMN leoforfriends.Rates2.answer_actor IS 'The unique email of an actor who wrote the answer.';
COMMENT ON COLUMN leoforfriends.Rates2.rating IS 'The rate gived to the answer.';