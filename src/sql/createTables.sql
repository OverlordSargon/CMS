-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-08-03 15:30:07.037

-- tables
-- Table: ORDER
CREATE TABLE `ORDER` (
    ORD_ID int NOT NULL AUTO_INCREMENT,
    ORD_NUMBER int NOT NULL,
    ORD_DESCRIPTION text NOT NULL,
    ORD_DATE date NOT NULL,
    ORD_FROM date NOT NULL,
    ORD_TO date NOT NULL,
    ORD_CLIENT_NAME varchar(100) NOT NULL,
    ORD_CLIENT_NUM int NOT NULL,
    W_ID int NOT NULL,
    ORD_CREATED_AT date NULL,
    ORD_UPDATED_AT date NULL,
    CONSTRAINT ORDER_PK PRIMARY KEY (ORD_ID)
);

-- Table: ROLE
CREATE TABLE ROLE (
    R_ID int NOT NULL AUTO_INCREMENT,
    R_ROLE text NOT NULL,
    R_CREATED_AT date NULL,
    R_UPDATED_AT date NULL,
    CONSTRAINT ROLE_PK PRIMARY KEY (R_ID)
);

-- Table: SCHEDULE
CREATE TABLE SCHEDULE (
    SCH_ID int NOT NULL AUTO_INCREMENT,
    SCH_INTERVAL int NOT NULL,
    SCH_FLAG varchar(50) NOT NULL,
    WP_ID int NOT NULL,
    SCH_CREATED_AT date NULL,
    SCH_UPDATED_AT date NULL,
    CONSTRAINT SCHEDULE_PK PRIMARY KEY (SCH_ID)
);

-- Table: SKILL
CREATE TABLE SKILL (
    S_ID int NOT NULL AUTO_INCREMENT,
    S_NAME varchar(100) NOT NULL,
    S_DESCRIPTION text NOT NULL,
    S_CREATED_AT date NULL,
    S_UPDATED_AT date NULL,
    CONSTRAINT SKILL_PK PRIMARY KEY (S_ID)
);

-- Table: USER
CREATE TABLE USER (
    U_ID int NOT NULL AUTO_INCREMENT,
    U_LOGIN int NOT NULL,
    U_PASSWORD int NOT NULL,
    R_ID int NOT NULL,
    U_CREATED_AT date NULL,
    U_UPDATED_AT date NULL,
    CONSTRAINT USER_PK PRIMARY KEY (U_ID)
);

-- Table: WORKER
CREATE TABLE WORKER (
    W_ID int NOT NULL AUTO_INCREMENT,
    W_NAME varchar(100) NOT NULL,
    W_TELEPHONE int NOT NULL,
    W_CREATED_AT date NULL,
    W_UPDATED_AT date NULL,
    CONSTRAINT WORKER_PK PRIMARY KEY (W_ID)
);

-- Table: WORKER_SKILL
CREATE TABLE WORKER_SKILL (
    WS_ID int NOT NULL AUTO_INCREMENT,
    WS_SKILL_ID int NOT NULL,
    WS_WORKER_ID int NOT NULL,
    CONSTRAINT WORKER_SKILL_PK PRIMARY KEY (WS_ID)
);

-- Table: WORKPLAN
CREATE TABLE WORKPLAN (
    WP_ID int NOT NULL AUTO_INCREMENT,
    WP_DATE date NOT NULL,
    W_ID int NOT NULL,
    WP_CREATED_AT date NULL,
    WP_UPDATED_AT date NULL,
    CONSTRAINT WORKPLAN_PK PRIMARY KEY (WP_ID)
);

-- foreign keys
-- Reference: ORDER_WORKER (table: ORDER)
ALTER TABLE `ORDER` ADD CONSTRAINT ORDER_WORKER FOREIGN KEY ORDER_WORKER (W_ID)
    REFERENCES WORKER (W_ID);

-- Reference: SCHEDULE_WORKPLAN (table: SCHEDULE)
ALTER TABLE SCHEDULE ADD CONSTRAINT SCHEDULE_WORKPLAN FOREIGN KEY SCHEDULE_WORKPLAN (WP_ID)
    REFERENCES WORKPLAN (WP_ID);

-- Reference: USER_ROLE (table: USER)
ALTER TABLE USER ADD CONSTRAINT USER_ROLE FOREIGN KEY USER_ROLE (R_ID)
    REFERENCES ROLE (R_ID);

-- Reference: WORKER_SKILL_SKILL (table: WORKER_SKILL)
ALTER TABLE WORKER_SKILL ADD CONSTRAINT WORKER_SKILL_SKILL FOREIGN KEY WORKER_SKILL_SKILL (WS_SKILL_ID)
    REFERENCES SKILL (S_ID);

-- Reference: WORKER_SKILL_WORKER (table: WORKER_SKILL)
ALTER TABLE WORKER_SKILL ADD CONSTRAINT WORKER_SKILL_WORKER FOREIGN KEY WORKER_SKILL_WORKER (WS_WORKER_ID)
    REFERENCES WORKER (W_ID);

-- Reference: WORKPLAN_WORKER (table: WORKPLAN)
ALTER TABLE WORKPLAN ADD CONSTRAINT WORKPLAN_WORKER FOREIGN KEY WORKPLAN_WORKER (W_ID)
    REFERENCES WORKER (W_ID);

-- End of file.
