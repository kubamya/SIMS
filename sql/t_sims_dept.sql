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

 Date: 03/01/2020 17:36:26
*/


-- ----------------------------
-- Table structure for t_sims_dept
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sims_dept";
CREATE TABLE "public"."t_sims_dept" (
  "c_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "c_name" varchar(300) COLLATE "pg_catalog"."default",
  "n_sfyx" int4,
  "n_xssx" int4,
  "c_pid" varchar(32) COLLATE "pg_catalog"."default",
  "c_comid" varchar(32) COLLATE "pg_catalog"."default",
  "d_cjsj" timestamp(6),
  "c_cjrid" varchar(32) COLLATE "pg_catalog"."default",
  "d_xgsj" timestamp(6),
  "c_xgrid" varchar(32) COLLATE "pg_catalog"."default",
  "n_jb" int4
)
;
COMMENT ON COLUMN "public"."t_sims_dept"."c_id" IS '主键';
COMMENT ON COLUMN "public"."t_sims_dept"."c_name" IS '名称';
COMMENT ON COLUMN "public"."t_sims_dept"."n_sfyx" IS '是否有效';
COMMENT ON COLUMN "public"."t_sims_dept"."n_xssx" IS '显示顺序';
COMMENT ON COLUMN "public"."t_sims_dept"."c_pid" IS '父级id';
COMMENT ON COLUMN "public"."t_sims_dept"."c_comid" IS '组织id';
COMMENT ON COLUMN "public"."t_sims_dept"."d_cjsj" IS '创建时间';
COMMENT ON COLUMN "public"."t_sims_dept"."c_cjrid" IS '创建人id';
COMMENT ON COLUMN "public"."t_sims_dept"."d_xgsj" IS '修改时间';
COMMENT ON COLUMN "public"."t_sims_dept"."c_xgrid" IS '修改人id';
COMMENT ON COLUMN "public"."t_sims_dept"."n_jb" IS '级别';
