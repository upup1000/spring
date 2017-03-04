DROP TABLE IF EXISTS `role_count`;
CREATE TABLE `role_count` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '数值\r\n',
  `last_update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`role_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色计数用表';

alter table  heeking_role.role_title_name  add column get_time  bigint(20) DEFAULT '0' COMMENT '获得称号的时间';
alter table  heeking_role.role_data_cache modify  turned_level varchar(1000);
alter table  heeking_role.role_data_cache modify  turned_skills varchar(1000);
alter table  heeking_role.role_buff ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_contacts ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_dungeon_mercen ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_dungeon_output ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_instance ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_mercen ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_recharge_shop ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_shop_guild ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_skill ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_skill_passsive ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_task ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_task ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_task_novice ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_title_name ADD INDEX ( `role_id` )USING BTREE;
alter table  heeking_role.role_welfare ADD INDEX ( `role_id` )USING BTREE;









