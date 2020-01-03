/*
 Navicat Premium Data Transfer

 Source Server         : localhost练习库
 Source Server Type    : PostgreSQL
 Source Server Version : 100005
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100005
 File Encoding         : 65001

 Date: 03/01/2020 09:01:15
*/


-- ----------------------------
-- Table structure for t_sims_com
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sims_com";
CREATE TABLE "public"."t_sims_com" (
  "c_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "c_name" varchar(300) COLLATE "pg_catalog"."default",
  "n_sfyx" int4,
  "n_xssx" int4,
  "d_cjsj" timestamp(6),
  "c_cjrid" varchar(32) COLLATE "pg_catalog"."default",
  "d_xgsj" timestamp(6),
  "c_xgrid" varchar(300) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_sims_com"."c_id" IS '主键';
COMMENT ON COLUMN "public"."t_sims_com"."c_name" IS '名称';
COMMENT ON COLUMN "public"."t_sims_com"."n_sfyx" IS '是否有效';
COMMENT ON COLUMN "public"."t_sims_com"."n_xssx" IS '显示顺序';
COMMENT ON COLUMN "public"."t_sims_com"."d_cjsj" IS '创建时间';
COMMENT ON COLUMN "public"."t_sims_com"."c_cjrid" IS '创建人id';
COMMENT ON COLUMN "public"."t_sims_com"."d_xgsj" IS '修改时间';
COMMENT ON COLUMN "public"."t_sims_com"."c_xgrid" IS '修改人id';
