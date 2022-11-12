  /* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Jayden
 * Created: 9/08/2022
 */

DROP TABLE if exists Work_Experience, Postgrad_Courses, Undergrad_Courses, undergraduate_experience, postgraduate_experience, meeting, feedback, "match", mentee, mentor, person;

CREATE TABLE Person(
    person_ID integer not null unique,
    email text not null unique,
    password text not null,
    first_name text not null,
    last_name text not null,
    phone text,
    mobile text,
    interests text[],
    skills text[],
    bio text,
    active boolean default true,
    city text,
    find_out text,
    role text not null,
    notes text,
    profile_photo text,
    
    PRIMARY KEY (person_ID)
    
);
CREATE TABLE Mentor (
    mentor_ID integer not null unique,
    person_ID integer not null unique,
    preferred_communication text not null,
    mentoring_preference text[] not null,
    linked_in text,
    capacity int not null,
    feedback_rating int,
    feeback_comment text,
    PRIMARY KEY (mentor_ID),
    CONSTRAINT fk_person FOREIGN KEY (person_ID) REFERENCES Person(person_ID) on delete cascade
);
CREATE TABLE Mentee(
    mentee_ID integer not null unique,
    person_ID integer not null unique,
    study_year int,
    motivation text,
    random_match boolean default false,
    PRIMARY KEY (mentee_ID),
    CONSTRAINT fk_person FOREIGN KEY  (person_ID) REFERENCES Person(person_ID) on delete cascade,
    CONSTRAINT chk_study_year CHECK (study_year<= 2025)
);
CREATE TABLE Match(
    match_ID integer not null unique,
    mentee_ID integer not null,
    mentor_ID integer not null,
    approved boolean default false,
    admin_approved boolean default false,
    match_date date,
    
    CONSTRAINT uniqueCombo UNIQUE(mentee_ID, mentor_ID),
    PRIMARY KEY (match_ID),
    CONSTRAINT fk_mentee FOREIGN KEY (mentee_ID) REFERENCES Mentee(mentee_ID) on delete cascade,
    CONSTRAINT fk_mentor FOREIGN KEY (mentor_ID) REFERENCES Mentor(mentor_ID) on delete cascade
);
CREATE TABLE Meeting(
    meeting_ID serial,
    match_ID integer not null,
    meeting_time decimal(4,2) not null,
    meeting_length decimal(4,2) not null,
    in_person boolean not null,
    notes text,
    PRIMARY KEY (meeting_ID),
    CONSTRAINT fk_match FOREIGN KEY (match_ID) REFERENCES Match(match_ID) on delete cascade
);
CREATE TABLE Feedback(
    feedback_ID serial,
    person_ID integer not null,
    feedback_rating decimal(4,2),
    feedback_comment text,
    PRIMARY KEY (feedback_ID),
    CONSTRAINT fk_feedback_person FOREIGN KEY (person_ID) REFERENCES Person(person_ID) on delete cascade
);
CREATE TABLE  Undergraduate_Experience(
    undergrad_experience_ID serial,
    person_ID integer not null,
    undergrad_major text not null,
    undergrad_course text not null,
    undergrad_institution text not null,
    undergrad_year int not null,
    
    PRIMARY KEY (undergrad_experience_ID),
    CONSTRAINT fk_undergrad_person FOREIGN KEY (person_ID) REFERENCES Person(person_ID) on delete cascade,
    CONSTRAINT chk_undergrad_year CHECK (undergrad_year <= 2025)
);
CREATE TABLE Postgraduate_Experience(
    postgrad_experience_ID serial,
    person_ID integer not null,
    postgrad_course text not null,
    postgrad_major text not null,
    postgrad_institution text not null,
    postgrad_year int not null,
    
    PRIMARY KEY (postgrad_experience_ID),
    CONSTRAINT fk_postgrad_person FOREIGN KEY (person_ID) REFERENCES Person(person_ID) on delete cascade,
    CONSTRAINT chk_postgrad_year CHECK (postgrad_year <= 2025)
);

CREATE TABLE Undergrad_Courses(
    ug_course text not null,
    ug_majors text[] not null,

    PRIMARY KEY (ug_course)

);


CREATE TABLE Postgrad_Courses(
    pg_course text not null,
    pg_majors text[] not null,

    PRIMARY KEY (pg_course)

);

CREATE TABLE Work_Experience(
    work_ID serial,
    mentor_ID integer not null,
    title text not null,
    organisation text not null,
    industry text not null,
    start_date text not null,
    end_date text,

    PRIMARY KEY (work_ID), 
    CONSTRAINT fk_mentor_ID FOREIGN KEY (mentor_ID) REFERENCES Mentor(mentor_ID) on delete cascade
);

CREATE EXTENSION pgcrypto;
