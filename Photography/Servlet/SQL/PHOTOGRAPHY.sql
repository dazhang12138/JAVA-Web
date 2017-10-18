/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.4.0

Source Server         : ORACLE Photography
Source Server Version : 110200
Source Host           : localhost:1521
Source Schema         : PHOTOGRAPHY

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2017-10-14 15:33:42
*/


-- ----------------------------
-- Table structure for PA_ALBUMS
-- ----------------------------
DROP TABLE "PHOTOGRAPHY"."PA_ALBUMS";
CREATE TABLE "PHOTOGRAPHY"."PA_ALBUMS" (
"A_ID" NUMBER(11) NOT NULL ,
"A_NAME" VARCHAR2(255 BYTE) NULL ,
"U_ID" NUMBER(11) NULL ,
"A_TIME" DATE NULL ,
"A_PROFILE" VARCHAR2(3000 BYTE) NULL ,
"A_PIC" BLOB NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_ALBUMS"."A_ID" IS '专辑编号';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_ALBUMS"."A_NAME" IS '专辑名称';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_ALBUMS"."U_ID" IS '创建用户';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_ALBUMS"."A_TIME" IS '创建时间';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_ALBUMS"."A_PROFILE" IS '介绍';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_ALBUMS"."A_PIC" IS '封面图片';

-- ----------------------------
-- Table structure for PA_APPLYADMIN
-- ----------------------------
DROP TABLE "PHOTOGRAPHY"."PA_APPLYADMIN";
CREATE TABLE "PHOTOGRAPHY"."PA_APPLYADMIN" (
"AD_ID" NUMBER(11) NOT NULL ,
"U_ID" NUMBER(11) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Table structure for PA_DOWN
-- ----------------------------
DROP TABLE "PHOTOGRAPHY"."PA_DOWN";
CREATE TABLE "PHOTOGRAPHY"."PA_DOWN" (
"D_ID" NUMBER(11) NOT NULL ,
"U_ID" NUMBER(11) NULL ,
"D_DATE" DATE NULL ,
"D_TYPE" NUMBER(4) NULL ,
"D_UPDATE" VARCHAR2(10 BYTE) NULL ,
"P_ID" NUMBER(11) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_DOWN"."D_TYPE" IS '1-签到，2-下载扣除积分,3-增加积分;4-积分兑换';

-- ----------------------------
-- Table structure for PA_PICTURE
-- ----------------------------
DROP TABLE "PHOTOGRAPHY"."PA_PICTURE";
CREATE TABLE "PHOTOGRAPHY"."PA_PICTURE" (
"P_ID" NUMBER(11) NOT NULL ,
"A_ID" NUMBER(11) NULL ,
"P_NAME" VARCHAR2(255 BYTE) NULL ,
"P_TIME" DATE NULL ,
"P_PIC" BLOB NULL ,
"P_PRICE" NUMBER(10) NULL ,
"P_PROFILE" VARCHAR2(3000 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."P_ID" IS '图片编号';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."A_ID" IS '所属专辑';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."P_NAME" IS '图片名称';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."P_TIME" IS '拍摄时间';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."P_PIC" IS '图片';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."P_PRICE" IS '积分';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_PICTURE"."P_PROFILE" IS '介绍';

-- ----------------------------
-- Table structure for PA_USER
-- ----------------------------
DROP TABLE "PHOTOGRAPHY"."PA_USER";
CREATE TABLE "PHOTOGRAPHY"."PA_USER" (
"U_ID" NUMBER(11) NOT NULL ,
"U_NAME" VARCHAR2(255 BYTE) NULL ,
"U_UNAME" VARCHAR2(255 BYTE) NULL ,
"U_PWD" VARCHAR2(255 BYTE) NULL ,
"U_PHONE" VARCHAR2(50 BYTE) NULL ,
"U_EMAIL" VARCHAR2(50 BYTE) NULL ,
"U_PRICE" NUMBER(10) NULL ,
"U_BALANCE" NUMBER(20,2) NULL ,
"U_SIGNDAY" NUMBER(10) NULL ,
"U_SIGNDATE" DATE NULL ,
"U_ROLE" VARCHAR2(2 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_ID" IS '用户编号';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_NAME" IS '用户姓名';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_UNAME" IS '用户名';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_PWD" IS '密码';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_PHONE" IS '电话';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_EMAIL" IS '邮箱';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_PRICE" IS '积分';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_BALANCE" IS '余额';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_SIGNDAY" IS '签到天数';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_SIGNDATE" IS '上次签到日期';
COMMENT ON COLUMN "PHOTOGRAPHY"."PA_USER"."U_ROLE" IS '角色 0-管理员 1-普通用户';

-- ----------------------------
-- Sequence structure for ALLID
-- ----------------------------
DROP SEQUENCE "PHOTOGRAPHY"."ALLID";
CREATE SEQUENCE "PHOTOGRAPHY"."ALLID"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999
 START WITH 21
 CACHE 20;

-- ----------------------------
-- Indexes structure for table PA_ALBUMS
-- ----------------------------

-- ----------------------------
-- Checks structure for table PA_ALBUMS
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_ALBUMS" ADD CHECK ("A_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PA_ALBUMS
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_ALBUMS" ADD PRIMARY KEY ("A_ID");

-- ----------------------------
-- Checks structure for table PA_APPLYADMIN
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_APPLYADMIN" ADD CHECK ("AD_ID" IS NOT NULL);
ALTER TABLE "PHOTOGRAPHY"."PA_APPLYADMIN" ADD CHECK ("U_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PA_APPLYADMIN
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_APPLYADMIN" ADD PRIMARY KEY ("AD_ID") DISABLE;

-- ----------------------------
-- Indexes structure for table PA_DOWN
-- ----------------------------

-- ----------------------------
-- Checks structure for table PA_DOWN
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_DOWN" ADD CHECK ("D_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PA_DOWN
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_DOWN" ADD PRIMARY KEY ("D_ID");

-- ----------------------------
-- Indexes structure for table PA_PICTURE
-- ----------------------------

-- ----------------------------
-- Checks structure for table PA_PICTURE
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_PICTURE" ADD CHECK ("P_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PA_PICTURE
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_PICTURE" ADD PRIMARY KEY ("P_ID");

-- ----------------------------
-- Indexes structure for table PA_USER
-- ----------------------------

-- ----------------------------
-- Uniques structure for table PA_USER
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_USER" ADD UNIQUE ("U_EMAIL");
ALTER TABLE "PHOTOGRAPHY"."PA_USER" ADD UNIQUE ("U_UNAME");

-- ----------------------------
-- Checks structure for table PA_USER
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_USER" ADD CHECK ("U_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PA_USER
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_USER" ADD PRIMARY KEY ("U_ID");

-- ----------------------------
-- Foreign Key structure for table "PHOTOGRAPHY"."PA_ALBUMS"
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_ALBUMS" ADD FOREIGN KEY ("U_ID") REFERENCES "PHOTOGRAPHY"."PA_USER" ("U_ID") ON DELETE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "PHOTOGRAPHY"."PA_APPLYADMIN"
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_APPLYADMIN" ADD FOREIGN KEY ("U_ID") REFERENCES "PHOTOGRAPHY"."PA_USER" ("U_ID");

-- ----------------------------
-- Foreign Key structure for table "PHOTOGRAPHY"."PA_DOWN"
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_DOWN" ADD FOREIGN KEY ("U_ID") REFERENCES "PHOTOGRAPHY"."PA_USER" ("U_ID");

-- ----------------------------
-- Foreign Key structure for table "PHOTOGRAPHY"."PA_PICTURE"
-- ----------------------------
ALTER TABLE "PHOTOGRAPHY"."PA_PICTURE" ADD FOREIGN KEY ("A_ID") REFERENCES "PHOTOGRAPHY"."PA_ALBUMS" ("A_ID") ON DELETE CASCADE;
