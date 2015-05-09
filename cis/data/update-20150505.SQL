--新增公告信息表
CREATE TABLE `t_notice` (
  `notice_id` int(11) NOT NULL auto_increment COMMENT '主键',
  `title` varchar(255) default NULL COMMENT '标题',
  `content` mediumtext COMMENT '内容',
  `create_date` datetime default NULL COMMENT '创建时间',
  `create_user` int(11) default NULL COMMENT '创建者id',
  `top_flag` int(11) default '2' COMMENT '置顶标识：1、置顶，2、未置顶',
  `top_date` date default NULL COMMENT '置顶时间',
  `status` int(11) default '1' COMMENT '状态：1、正常，2、不可用',
  PRIMARY KEY  (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告信息表';

CREATE TABLE t_notice_unread
(
	user_id INTEGER NOT NULL COMMENT '员工id',
	notice_id INTEGER NOT NULL COMMENT '公告id',
	PRIMARY KEY (user_id, notice_id)
)  COMMENT='未读公告记录表'
;
