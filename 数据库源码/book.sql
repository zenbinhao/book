/*
 Navicat Premium Data Transfer

 Source Server         : zengbinhao
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 101.132.237.188:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 21/12/2019 14:26:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_books
-- ----------------------------
DROP TABLE IF EXISTS `t_books`;
CREATE TABLE `t_books`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_title` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `introduce` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_delete` int(255) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL COMMENT '图书数量',
  `create_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user_id` int(11) NULL DEFAULT NULL,
  `update_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_books
-- ----------------------------
INSERT INTO `t_books` VALUES (1, '红楼梦', '《红楼梦》，中国四大名著之一，作者曹雪芹，清代著名小说，曾经被当作禁书。后世为研究《红楼梦》已经成立专门学会，称之为“红学会”。', '曹雪芹等（有争议）', 'http://101.132.237.188:8080/book/img/997e8ba4-d69f-4442-937a-bc76a5668f2b.jpg', 3563, '2019-12-06 21:48:29', '2019-12-20 11:09:54', 0, 7, 0, 100, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_books` VALUES (2, '我叫小鸭子', '丫丫丫丫', '嘿嘿嘿', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 13, '2019-12-06 21:48:29', '2019-12-06 22:53:11', 0, 2, 0, 123, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_books` VALUES (3, '红楼梦', '《红楼梦》，中国四大名著之一，作者曹雪芹，清代著名小说，曾经被当作禁书。后世为研究《红楼梦》已经成立专门学会，称之为“红学会”。', '曹雪芹等（有争议）', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 3560, '2019-12-06 21:48:29', NULL, 0, 0, 0, 100, '曾斌昊', NULL, NULL, 1);
INSERT INTO `t_books` VALUES (4, '红楼梦', '《红楼梦》，中国四大名著之一，作者曹雪芹，清代著名小说，曾经被当作禁书。后世为研究《红楼梦》已经成立专门学会，称之为“红学会”。', '曹雪芹等（有争议）', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 3561, '2019-12-06 21:48:29', NULL, 0, 1, 0, 100, '曾斌昊', NULL, NULL, 1);
INSERT INTO `t_books` VALUES (5, '红楼梦', '《红楼梦》，中国四大名著之一，作者曹雪芹，清代著名小说，曾经被当作禁书。后世为研究《红楼梦》已经成立专门学会，称之为“红学会”。', '曹雪芹等（有争议）', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 3560, '2019-12-06 21:48:29', NULL, 0, 0, 0, 100, '曾斌昊', NULL, NULL, 4);
INSERT INTO `t_books` VALUES (6, '红楼梦', '《红楼梦》，中国四大名著之一，作者曹雪芹，清代著名小说，曾经被当作禁书。后世为研究《红楼梦》已经成立专门学会，称之为“红学会”。', '曹雪芹等（有争议）', 'http://101.132.237.188:8080/book/img/ed8e4e25-e4da-4a9d-a404-a0790a664621.jpg', 3560, '2019-12-06 21:48:29', '2019-12-20 11:17:43', 0, 4, 1, 100, '曾斌昊', 1, '曾斌昊', 4);
INSERT INTO `t_books` VALUES (7, '斗罗大陆', '阿斯哦海带丝哦带式啊是大', '天蚕土豆', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 15632, '2019-12-06 22:24:49', NULL, 0, 0, 1, 115, '曾斌昊', NULL, NULL, 2);
INSERT INTO `t_books` VALUES (8, '斗罗大陆', '阿斯哦海带丝哦带式啊是大', '天蚕土豆', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 15632, '2019-12-06 22:24:54', NULL, 0, 0, 1, 115, '曾斌昊', NULL, NULL, 2);
INSERT INTO `t_books` VALUES (9, '按时的规格', '按时可见度', '按时', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 131, '2019-12-06 22:24:55', '2019-12-07 00:46:43', 0, 2, 1, 12, '曾斌昊', 1, '曾斌昊', 4);
INSERT INTO `t_books` VALUES (10, '斗破苍穹', '阿斯哦海带丝哦带式啊是大', '天蚕土豆', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 15633, '2019-12-06 22:25:11', NULL, 0, 1, 1, 115, '曾斌昊', NULL, NULL, 3);
INSERT INTO `t_books` VALUES (11, '三国演义', '《三国演义》描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一群叱咤风云的三国英雄人物。', '罗贯中', 'http://101.132.237.188:8080/book/img/9fbf87f2-98e5-4830-9063-20e317f15c0a.jpg', 3121, '2019-12-06 22:25:17', '2019-12-20 11:15:34', 0, 5, 1, 122, '曾斌昊', 1, '曾斌昊', 2);
INSERT INTO `t_books` VALUES (12, '我是一只鸭', '门前大桥下游过一群鸭', 'duckli', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 123, '2019-12-07 00:36:03', NULL, 0, 0, 1, 2135, '曾斌昊', NULL, NULL, 3);
INSERT INTO `t_books` VALUES (13, '我是一只鸭', '门前大桥下游过一群鸭', 'duckli', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 123, '2019-12-07 00:45:48', NULL, 0, 0, 1, 2135, '曾斌昊', NULL, NULL, 3);
INSERT INTO `t_books` VALUES (14, '测试', '测试', '测试', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 213, '2019-12-08 22:45:01', NULL, 0, 0, 1, 2123, '曾斌昊', NULL, NULL, 1);
INSERT INTO `t_books` VALUES (15, '阿萨大', '阿萨大', '阿萨大', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 123, '2019-12-08 22:45:46', NULL, 0, 0, 1, 123, '曾斌昊', NULL, NULL, 1);
INSERT INTO `t_books` VALUES (16, '我叫小鸭子', '呀呀呀', '鸭子鸭子', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 1123, '2019-12-08 22:48:05', NULL, 0, 0, 1, 111, '曾斌昊', NULL, NULL, 3);
INSERT INTO `t_books` VALUES (17, '我我我', '我我我', '我我我', 'http://localhost:8080/book/img/20191209001025三国演义.jpg', 126, '2019-12-08 23:35:45', '2019-12-09 00:10:28', 0, 4, 1, 12, '曾斌昊', 1, '曾斌昊', 13);
INSERT INTO `t_books` VALUES (18, '遨四海', '扫i和', '哦啊送i是', 'http://localhost:8080/book/img/20191208234534斗破苍穹.jpg', 16, '2019-12-08 23:45:38', NULL, 0, 4, 1, 123, '曾斌昊', NULL, NULL, 1);
INSERT INTO `t_books` VALUES (19, '斗罗大陆', '玄幻小说', '唐三', 'http://localhost:8080/book/img/20191209142324斗罗大陆.jpg', 680, '2019-12-09 14:23:32', NULL, 0, 14, 1, 12, '曾斌昊', NULL, NULL, 1);
INSERT INTO `t_books` VALUES (20, '西游记', '《西游记》是中国古典神魔小说中的巅峰之作，书中讲述了唐僧师徒四人一路降妖伏魔，历经整整九九八十一难取经的故事。作者细腻而深刻地塑造了四个极其经典的形象：慈悲、宽厚但软弱、迂腐的师父唐僧；富有反叛精神、神通广大的孙悟空；自私狡猾、好吃懒做的猪八戒；任劳任怨、忠心耿耿的沙僧……他们怀着不同的目的走上了同一条取经路，面对各种难以想象的妖魔鬼怪、险恶绝境，他们既有矛盾，又有合作与情谊。', '吴承恩', 'http://101.132.237.188:8080/book/img/abd67109-584e-423e-b278-549665fc9752.jpg', 21323, '2019-12-11 09:26:02', '2019-12-20 11:14:30', 0, 5, 1, 1231, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_books` VALUES (21, '水浒传', '《水浒传》是一部长篇英雄传奇，是中国古代长篇小说的代表作之一，是以宋江起义故事为线索创作出来的。宋江起义发生在北宋徽宗时期，《宋史》的《徽宗本纪》、《侯蒙传》、《张叔夜传》等都有记载。从南宋起，宋江起义的故事就在民间流传，《醉翁谈录》记载了一些独立的有关水浒英雄的传说，《大宋宣和遗事》把许多水浒故事联缀起来，和长篇小说已经很接近。元代出现了不少水浒戏，一批梁山英雄作为舞台形象出现。《水浒传》是宋江起义故事在民间长期流传基础上产生出来的，吸收了民间文学的营养。 《水浒传》是我国人民最喜爱的古典长篇白话小说之一。它产生于明代，是在宋、元以来有关水浒的故事、话本、戏曲的基础上，由作者加工整理、创作而成的。全书以宋江领导的农民起义为主要题材，艺术地再现了中国古代人民反抗压迫、英勇斗争的悲壮画卷。作品充分暴露了封建统治阶级的腐朽和残暴，揭露了当时尖锐对立的社会矛盾和“官逼民反”的残酷现实，成功地塑造了鲁智深、李逵、武松、林冲、阮小七等一批英雄人物。小说故事情节曲折，语言生动，人物性格鲜明，具有高度的艺术成就。但作品歌颂、美化宋江，鼓吹“忠义”和“替天行道”，表现出严重的思想局限。', '施耐庵 罗贯中', 'http://101.132.237.188:8080/book/img/c613c17a-0586-4de2-aea6-8432b2750341.jpg', 213, '2019-12-11 09:37:28', '2019-12-20 11:11:22', 0, 4, 1, 2132, '曾斌昊', 1, '曾斌昊', 3);
INSERT INTO `t_books` VALUES (22, '穆斯林的葬礼', '《穆斯林的葬礼》是霍达创作的一部长篇小说，该小说以回族手工匠人梁亦清的玉器作坊奇珍斋升沉起伏为主线，在历史的背景下描写梁家三代人不同的命运变迁，表现了主人公为追求理想和事业，为完善自身素质所发出的蓬勃不息的命运意识。', '霍达', 'http://101.132.237.188:8080/book/img/cfab9916-0be4-4dcf-9796-d16eac40014d.jpg', 1231, '2019-12-12 14:12:53', '2019-12-20 11:13:24', 0, 2, 1, 2, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_books` VALUES (23, '朝花夕拾', '此文集作为“回忆的记事”，多侧面地反映了作者鲁迅青少年时期的生活，形象地反映了他的性格和志趣的形成经过。前七篇反映他童年时代在绍兴的家庭和私塾中的生活情景，后三篇叙述他从家乡到南京，又到日本留学，然后回国教书的经历；揭露了半封建半殖民地社会种种丑恶的不合理现象，同时反映了有抱负的青年知识分子在旧中国茫茫黑夜中，不畏艰险，寻找光明的困难历程，以及抒发了作者对往日亲友、师长的怀念之情 [2]  。', '鲁迅 ', 'http://101.132.237.188:8080/book/img/5e91f4af-ff34-4dce-a65d-4c73f620d995.jpg', 5314, '2019-12-12 14:16:25', '2019-12-20 11:19:01', 0, 3, 1, 1231, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_books` VALUES (24, '撒看见', '昂山脉南部的', '阿斯蒂', 'http://101.132.237.188:8080/book/img/1b505da8-9a4e-4449-94a4-150455d96921.jpg', 1321, '2019-12-13 10:34:34', '2019-12-20 11:18:26', 0, 1, 1, 132, '曾斌昊', 1, '曾斌昊', 3);

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  `update_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, '经典', '2019-12-07 09:46:44', '2019-12-12 14:21:22', 0, 1, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_category` VALUES (2, '古代文学', '2019-12-07 09:47:01', NULL, 0, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (3, '小丫丫丫丫吖', '2019-12-07 09:47:11', '2019-12-07 09:50:03', 0, 1, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_category` VALUES (4, '小包子区域', '2019-12-07 09:47:18', NULL, 0, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (5, '神话传说', '2019-12-07 09:47:27', NULL, 0, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (6, '军事', '2019-12-07 09:52:48', NULL, 0, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (7, '考古', '2019-12-07 09:52:55', NULL, 0, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (8, '科技', '2019-12-07 09:53:03', NULL, 0, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (9, '1', '2019-12-09 10:07:08', NULL, 1, 0, '曾斌昊', 1, NULL, NULL);
INSERT INTO `t_category` VALUES (10, '再测试一下', '2019-12-09 10:12:21', '2019-12-09 10:32:29', 1, 1, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_category` VALUES (11, '测试一下', '2019-12-09 10:16:26', '2019-12-09 10:32:04', 1, 1, '曾斌昊', 1, '曾斌昊', 1);
INSERT INTO `t_category` VALUES (12, '包子666的一批', '2019-12-09 10:32:40', '2019-12-09 10:32:52', 1, 1, '曾斌昊', 1, '曾斌昊', 1);

-- ----------------------------
-- Table structure for t_collect
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect`  (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`collect_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collect
-- ----------------------------
INSERT INTO `t_collect` VALUES (5, 16, 22);
INSERT INTO `t_collect` VALUES (6, 16, 17);
INSERT INTO `t_collect` VALUES (8, 16, 8);
INSERT INTO `t_collect` VALUES (9, 16, 24);
INSERT INTO `t_collect` VALUES (10, 16, 21);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 16, '小包子', '<p><img src=\"https://btsstatic.oss-cn-shanghai.aliyuncs.com/admin/emoji/13.gif\" alt=\"[呲牙]\" data-w-e=\"1\"><br></p>', '2019-12-20 11:19:58', 0, 24);
INSERT INTO `t_comment` VALUES (2, 16, '小包子', '<p style=\"text-align: center;\">还行&nbsp; 我挺喜欢<img src=\"https://btsstatic.oss-cn-shanghai.aliyuncs.com/admin/emoji/6.gif\" alt=\"[害羞]\" data-w-e=\"1\" style=\"font-size: 1rem;\"></p><p style=\"text-align: center;\"><img src=\"http://localhost:8080/book/img/b921822d-a174-4366-95b7-fc7147950747.png\" style=\"font-size: 1rem; max-width: 100%;\"></p>', '2019-12-20 11:20:57', 0, 8);
INSERT INTO `t_comment` VALUES (3, 16, '小包子', '<p>盖楼<img src=\"https://btsstatic.oss-cn-shanghai.aliyuncs.com/admin/emoji/84.gif\" alt=\"[勾引]\" data-w-e=\"1\" style=\"font-size: 1rem;\"></p>', '2019-12-20 11:21:25', 0, 21);
INSERT INTO `t_comment` VALUES (4, 16, '小包子', '<p>真醉了<img src=\"https://btsstatic.oss-cn-shanghai.aliyuncs.com/admin/emoji/27.gif\" alt=\"[流汗]\" data-w-e=\"1\" style=\"font-size: 1rem;\"></p>', '2019-12-20 11:27:39', 0, 16);

-- ----------------------------
-- Table structure for t_examine
-- ----------------------------
DROP TABLE IF EXISTS `t_examine`;
CREATE TABLE `t_examine`  (
  `examine_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_book` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `examine_state` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user_id` int(11) NULL DEFAULT NULL,
  `update_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`examine_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_examine
-- ----------------------------
INSERT INTO `t_examine` VALUES (1, '我觉得可以', 0, '2019-12-07 17:07:20', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (2, '我是小黑鸭子', 0, '2019-12-07 17:07:41', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (3, '这个有点冷', 0, '2019-12-07 17:07:50', '2019-12-07 17:15:04', 0, 1, 1, '曾斌昊', 1, '曾斌昊');
INSERT INTO `t_examine` VALUES (4, '这个杀手有点冷', 0, '2019-12-07 17:07:59', NULL, 0, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (5, '战舰', 0, '2019-12-07 17:08:09', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (6, '中国科技', 0, '2019-12-07 17:08:19', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (7, '我们的国', 0, '2019-12-07 17:08:51', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (8, '鲁迅', 0, '2019-12-07 17:08:57', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (9, '鲁班大师', 0, '2019-12-07 17:12:03', NULL, 1, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (10, '阿萨大', 0, '2019-12-09 11:11:50', NULL, 0, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (11, '这个杀手有点冷', 3, '2019-12-09 11:15:06', '2019-12-17 14:10:50', 0, 1, 1, '曾斌昊', 1, '曾斌昊');
INSERT INTO `t_examine` VALUES (12, '我草好叼', 3, '2019-12-09 11:15:14', '2019-12-17 14:10:56', 0, 1, 1, '曾斌昊', 1, '曾斌昊');
INSERT INTO `t_examine` VALUES (13, '哇哇哇', 0, '2019-12-09 11:18:00', NULL, 0, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (14, '你不懂我比你更难过', 0, '2019-12-09 11:18:14', NULL, 0, 0, 1, '曾斌昊', NULL, NULL);
INSERT INTO `t_examine` VALUES (15, '有鬼', 2, '2019-12-09 11:34:34', '2019-12-09 11:34:43', 0, 1, 1, '曾斌昊', 1, '曾斌昊');
INSERT INTO `t_examine` VALUES (16, '我有点疼', 1, '2019-12-09 12:27:54', '2019-12-17 14:10:43', 0, 1, 1, '曾斌昊', 1, '曾斌昊');
INSERT INTO `t_examine` VALUES (17, '但是我还能忍', 1, '2019-12-09 12:28:03', '2019-12-17 14:10:39', 0, 1, 1, '曾斌昊', 1, '曾斌昊');
INSERT INTO `t_examine` VALUES (18, '人与人', 0, '2019-12-09 12:28:10', '2019-12-17 14:10:33', 0, 4, 1, '曾斌昊', 1, '曾斌昊');

-- ----------------------------
-- Table structure for t_loginlog
-- ----------------------------
DROP TABLE IF EXISTS `t_loginlog`;
CREATE TABLE `t_loginlog`  (
  `loginLog_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_date` datetime NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`loginLog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 320 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_loginlog
-- ----------------------------
INSERT INTO `t_loginlog` VALUES (1, 1, 'zengbinhao', '曾斌昊', '2019-12-06 18:57:13', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (2, 2, 'xiaoyaya', '李文魁', '2019-12-06 19:05:27', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (3, 1, 'zengbinhao', '曾斌昊', '2019-12-06 19:06:01', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (4, 1, 'zengbinhao', '曾斌昊', '2019-12-06 19:12:45', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (5, 1, 'zengbinhao', '曾斌昊', '2019-12-06 21:40:08', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (6, 1, 'zengbinhao', '曾斌昊', '2019-12-06 21:48:23', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (7, 1, 'zengbinhao', '曾斌昊', '2019-12-06 22:23:10', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (8, 1, 'zengbinhao', '曾斌昊', '2019-12-06 22:32:25', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (9, 1, 'zengbinhao', '曾斌昊', '2019-12-06 22:44:42', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (10, 1, 'zengbinhao', '曾斌昊', '2019-12-06 22:48:45', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (11, 1, 'zengbinhao', '曾斌昊', '2019-12-06 22:52:18', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (12, 1, 'zengbinhao', '曾斌昊', '2019-12-06 22:53:39', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (13, 2, 'xiaoyaya', '李文魁', '2019-12-06 22:53:55', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (14, 1, 'zengbinhao', '曾斌昊', '2019-12-06 23:09:47', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (15, 2, 'xiaoyaya', '李文魁', '2019-12-06 23:10:31', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (16, 2, 'xiaoyaya', '李文魁', '2019-12-06 23:19:20', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (17, 1, 'zengbinhao', '曾斌昊', '2019-12-06 23:19:39', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (18, 1, 'zengbinhao', '曾斌昊', '2019-12-07 00:35:57', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (19, 1, 'zengbinhao', '曾斌昊', '2019-12-07 00:36:11', NULL);
INSERT INTO `t_loginlog` VALUES (20, 1, 'zengbinhao', '曾斌昊', '2019-12-07 00:38:16', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (21, 1, 'zengbinhao', '曾斌昊', '2019-12-07 00:45:38', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (22, 2, 'xiaoyaya', '李文魁', '2019-12-07 09:40:44', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (23, 1, 'zengbinhao', '曾斌昊', '2019-12-07 09:41:08', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (24, 1, 'zengbinhao', '曾斌昊', '2019-12-07 09:43:31', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (25, 1, 'zengbinhao', '曾斌昊', '2019-12-07 09:46:38', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (26, 1, 'zengbinhao', '曾斌昊', '2019-12-07 09:56:56', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (27, 1, 'zengbinhao', '曾斌昊', '2019-12-07 12:56:52', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (28, 2, 'xiaoyaya', '李文魁', '2019-12-07 12:57:12', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (29, 16, 'xiaobaozi', '小包子', '2019-12-07 12:57:48', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (30, 2, 'xiaoyaya', '李文魁', '2019-12-07 12:58:49', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (31, 1, 'zengbinhao', '曾斌昊', '2019-12-07 13:02:20', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (32, 1, 'zengbinhao', '曾斌昊', '2019-12-07 14:46:37', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (33, 2, 'xiaoyaya', '李文魁', '2019-12-07 14:46:59', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (34, 1, 'zengbinhao', '曾斌昊', '2019-12-07 14:49:01', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (35, 1, 'zengbinhao', '曾斌昊', '2019-12-07 14:51:41', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (36, 2, 'xiaoyaya', '李文魁', '2019-12-07 14:57:14', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (37, 1, 'zengbinhao', '曾斌昊', '2019-12-07 15:02:30', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (38, 2, 'xiaoyaya', '李文魁', '2019-12-07 15:07:01', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (39, 1, 'zengbinhao', '曾斌昊', '2019-12-07 15:08:56', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (40, 1, 'zengbinhao', '曾斌昊', '2019-12-07 15:32:17', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (41, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:04:59', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (42, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:07:08', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (43, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:10:56', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (44, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:14:58', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (45, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:26:48', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (46, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:41:38', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (47, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:41:53', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (48, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:49:32', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (49, 1, 'zengbinhao', '曾斌昊', '2019-12-07 17:49:36', NULL);
INSERT INTO `t_loginlog` VALUES (50, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:05:46', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (51, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:09:38', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (52, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:10:39', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (53, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:11:03', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (54, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:11:18', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (55, 2, 'xiaoyaya', '李文魁', '2019-12-07 18:11:52', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (56, 2, 'xiaoyaya', '李文魁', '2019-12-07 18:13:06', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (57, 2, 'xiaoyaya', '李文魁', '2019-12-07 18:15:00', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (58, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:15:36', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (59, 16, 'xiaobaozi', '小包子', '2019-12-07 18:16:00', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (60, 16, 'xiaobaozi', '小包子', '2019-12-07 18:16:31', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (61, 16, 'xiaobaozi', '小包子', '2019-12-07 18:17:09', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (62, 16, 'xiaobaozi', '小包子', '2019-12-07 18:19:00', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (63, 16, 'xiaobaozi', '小包子', '2019-12-07 18:19:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (64, 16, 'xiaobaozi', '小包子', '2019-12-07 18:19:20', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (65, 16, 'xiaobaozi', '小包子', '2019-12-07 18:19:22', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (66, 16, 'xiaobaozi', '小包子', '2019-12-07 18:19:23', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (67, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:19:30', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (68, 16, 'xiaobaozi', '小包子', '2019-12-07 18:19:43', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (69, 16, 'xiaobaozi', '小包子', '2019-12-07 18:24:04', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (70, 16, 'xiaobaozi', '小包子', '2019-12-07 18:24:26', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (71, 2, 'xiaoyaya', '李文魁', '2019-12-07 18:24:29', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (72, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:24:41', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (73, 16, 'xiaobaozi', '小包子', '2019-12-07 18:27:36', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (74, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:29:02', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (75, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:29:02', NULL);
INSERT INTO `t_loginlog` VALUES (76, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:29:34', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (77, 1, 'zengbinhao', '曾斌昊', '2019-12-07 18:29:57', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (78, 16, 'xiaobaozi', '小包子', '2019-12-07 18:30:12', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (79, 16, 'xiaobaozi', '小包子', '2019-12-07 18:36:06', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (80, 16, 'xiaobaozi', '小包子', '2019-12-07 18:36:06', NULL);
INSERT INTO `t_loginlog` VALUES (81, 16, 'xiaobaozi', '小包子', '2019-12-07 18:36:13', NULL);
INSERT INTO `t_loginlog` VALUES (82, 16, 'xiaobaozi', '小包子', '2019-12-07 18:36:40', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (83, 2, 'xiaoyaya', '李文魁', '2019-12-07 18:44:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (84, 2, 'xiaoyaya', '李文魁', '2019-12-07 18:44:20', NULL);
INSERT INTO `t_loginlog` VALUES (85, 16, 'xiaobaozi', '小包子', '2019-12-07 18:52:27', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (86, 16, 'xiaobaozi', '小包子', '2019-12-07 18:52:45', NULL);
INSERT INTO `t_loginlog` VALUES (87, 16, 'xiaobaozi', '小包子', '2019-12-07 18:52:46', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (88, 16, 'xiaobaozi', '小包子', '2019-12-07 18:52:49', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (89, 1, 'zengbinhao', '曾斌昊', '2019-12-07 19:30:11', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (90, 16, 'xiaobaozi', '小包子', '2019-12-07 19:30:25', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (91, 2, 'xiaoyaya', '李文魁', '2019-12-07 23:11:47', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (92, 2, 'xiaoyaya', '李文魁', '2019-12-07 23:12:31', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (93, 2, 'xiaoyaya', '李文魁', '2019-12-07 23:16:40', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (94, 2, 'xiaoyaya', '李文魁', '2019-12-07 23:18:39', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (95, 2, 'xiaoyaya', '李文魁', '2019-12-07 23:24:29', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (96, 1, 'zengbinhao', '曾斌昊', '2019-12-08 00:38:08', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (97, 1, 'zengbinhao', '曾斌昊', '2019-12-08 00:51:11', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (98, 16, 'xiaobaozi', '小包子', '2019-12-08 12:37:42', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (99, 16, 'xiaobaozi', '小包子', '2019-12-08 12:38:08', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (100, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:05:45', '122.238.168.80');
INSERT INTO `t_loginlog` VALUES (101, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:47:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (102, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:52:45', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (103, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:53:03', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (104, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:53:24', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (105, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:53:43', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (106, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:53:54', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (107, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:54:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (108, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:54:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (109, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:54:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (110, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:54:18', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (111, 1, 'zengbinhao', '曾斌昊', '2019-12-08 14:59:47', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (112, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:03:25', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (113, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:04:26', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (114, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:07:00', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (115, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:07:53', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (116, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:09:32', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (117, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:17:30', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (118, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:21:48', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (119, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:24:31', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (120, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:37:37', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (121, 16, 'xiaobaozi', '小包子', '2019-12-08 15:38:03', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (122, 1, 'zengbinhao', '曾斌昊', '2019-12-08 15:38:46', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (123, 1, 'zengbinhao', '曾斌昊', '2019-12-08 16:01:45', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (124, 16, 'xiaobaozi', '小包子', '2019-12-08 16:03:49', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (125, 16, 'xiaobaozi', '小包子', '2019-12-08 16:03:56', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (126, 1, 'zengbinhao', '曾斌昊', '2019-12-08 16:04:23', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (127, 1, 'zengbinhao', '曾斌昊', '2019-12-08 16:38:11', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (128, 1, 'zengbinhao', '曾斌昊', '2019-12-08 16:56:41', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (129, 1, 'zengbinhao', '曾斌昊', '2019-12-08 16:56:43', NULL);
INSERT INTO `t_loginlog` VALUES (130, 1, 'zengbinhao', '曾斌昊', '2019-12-08 16:56:47', NULL);
INSERT INTO `t_loginlog` VALUES (131, 1, 'zengbinhao', '曾斌昊', '2019-12-08 18:08:28', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (132, 1, 'zengbinhao', '曾斌昊', '2019-12-08 20:25:23', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (133, 1, 'zengbinhao', '曾斌昊', '2019-12-08 20:25:36', NULL);
INSERT INTO `t_loginlog` VALUES (134, 1, 'zengbinhao', '曾斌昊', '2019-12-08 20:25:37', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (135, 1, 'zengbinhao', '曾斌昊', '2019-12-08 20:26:16', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (136, 1, 'zengbinhao', '曾斌昊', '2019-12-08 21:01:07', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (137, 1, 'zengbinhao', '曾斌昊', '2019-12-08 22:44:37', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (138, 1, 'zengbinhao', '曾斌昊', '2019-12-09 09:29:36', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (139, 1, 'zengbinhao', '曾斌昊', '2019-12-09 12:26:21', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (140, 1, 'zengbinhao', '曾斌昊', '2019-12-09 13:18:23', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (141, 1, 'zengbinhao', '曾斌昊', '2019-12-09 13:18:35', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (142, 16, 'xiaobaozi', '小包子', '2019-12-09 13:19:51', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (143, 16, 'xiaobaozi', '小包子', '2019-12-09 13:19:59', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (144, 1, 'zengbinhao', '曾斌昊', '2019-12-09 13:21:01', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (145, 16, 'xiaobaozi', '小包子', '2019-12-09 13:47:22', '0:0:0:0:0:0:0:1');
INSERT INTO `t_loginlog` VALUES (146, 1, 'zengbinhao', '曾斌昊', '2019-12-09 13:56:51', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (147, 1, 'zengbinhao', '曾斌昊', '2019-12-09 14:22:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (148, 16, 'xiaobaozi', '小包子', '2019-12-09 14:28:38', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (149, 1, 'zengbinhao', '曾斌昊', '2019-12-09 14:30:34', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (150, 16, 'xiaobaozi', '小包子', '2019-12-09 14:31:13', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (151, 1, 'zengbinhao', '曾斌昊', '2019-12-09 14:32:21', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (152, 16, 'xiaobaozi', '小包子', '2019-12-09 14:33:03', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (153, 1, 'zengbinhao', '曾斌昊', '2019-12-09 14:34:09', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (154, 16, 'xiaobaozi', '小包子', '2019-12-09 15:25:00', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (155, 16, 'xiaobaozi', '小包子', '2019-12-09 16:20:39', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (156, 16, 'xiaobaozi', '小包子', '2019-12-09 16:20:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (157, 16, 'xiaobaozi', '小包子', '2019-12-09 16:25:18', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (158, 16, 'xiaobaozi', '小包子', '2019-12-09 18:41:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (159, 16, 'xiaobaozi', '小包子', '2019-12-09 18:41:59', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (160, 1, 'zengbinhao', '曾斌昊', '2019-12-09 18:49:07', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (161, 1, 'zengbinhao', '曾斌昊', '2019-12-09 19:03:25', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (162, 16, 'xiaobaozi', '小包子', '2019-12-09 19:03:30', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (163, 1, 'zengbinhao', '曾斌昊', '2019-12-09 19:07:09', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (164, 2, 'xiaoyaya', '鸭子', '2019-12-09 19:07:53', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (165, 16, 'xiaobaozi', '小包子', '2019-12-09 21:10:34', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (166, 16, 'xiaobaozi', '小包子', '2019-12-09 21:10:44', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (167, 16, 'xiaobaozi', '小包子', '2019-12-09 21:39:26', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (168, 16, 'xiaobaozi', '小包子', '2019-12-10 09:33:59', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (169, 16, 'xiaobaozi', '小包子', '2019-12-10 09:34:54', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (170, 16, 'xiaobaozi', '小包子', '2019-12-10 09:35:20', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (171, 16, 'xiaobaozi', '小包子', '2019-12-10 09:38:32', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (172, 16, 'xiaobaozi', '小包子', '2019-12-10 09:38:59', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (173, 16, 'xiaobaozi', '小包子', '2019-12-10 09:40:19', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (174, 16, 'xiaobaozi', '小包子', '2019-12-10 09:41:52', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (175, 16, 'xiaobaozi', '小包子', '2019-12-10 09:42:02', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (176, 16, 'xiaobaozi', '小包子', '2019-12-10 09:43:54', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (177, 16, 'xiaobaozi', '小包子', '2019-12-10 09:46:21', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (178, 16, 'xiaobaozi', '小包子', '2019-12-10 09:49:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (179, 16, 'xiaobaozi', '小包子', '2019-12-10 09:50:39', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (180, 16, 'xiaobaozi', '小包子', '2019-12-10 09:51:30', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (181, 1, 'zengbinhao', '曾斌昊', '2019-12-10 09:53:43', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (182, 1, 'zengbinhao', '曾斌昊', '2019-12-10 09:55:58', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (183, 1, 'zengbinhao', '曾斌昊', '2019-12-10 09:56:46', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (184, 1, 'zengbinhao', '曾斌昊', '2019-12-10 09:57:06', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (185, 16, 'xiaobaozi', '小包子', '2019-12-10 09:58:05', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (186, 16, 'xiaobaozi', '小包子', '2019-12-10 10:01:17', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (187, 16, 'xiaobaozi', '小包子', '2019-12-10 10:03:58', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (188, 16, 'xiaobaozi', '小包子', '2019-12-10 10:04:29', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (189, 16, 'xiaobaozi', '小包子', '2019-12-10 10:06:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (190, 16, 'xiaobaozi', '小包子', '2019-12-10 10:14:22', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (191, 1, 'zengbinhao', '曾斌昊', '2019-12-10 10:15:15', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (192, 16, 'xiaobaozi', '小包子', '2019-12-10 10:27:11', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (193, 16, 'xiaobaozi', '小包子', '2019-12-10 10:33:13', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (194, 1, 'zengbinhao', '曾斌昊', '2019-12-10 14:36:36', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (195, 16, 'xiaobaozi', '小包子', '2019-12-10 14:45:53', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (196, 2, 'xiaoyaya', '鸭子', '2019-12-10 14:47:07', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (197, 16, 'xiaobaozi', '小包子', '2019-12-10 14:47:40', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (198, 16, 'xiaobaozi', '小包子', '2019-12-10 14:49:37', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (199, 16, 'xiaobaozi', '小包子', '2019-12-10 14:51:24', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (200, 16, 'xiaobaozi', '小包子', '2019-12-10 14:54:24', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (201, 16, 'xiaobaozi', '小包子', '2019-12-10 14:56:44', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (202, 16, 'xiaobaozi', '小包子', '2019-12-10 14:57:10', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (203, 16, 'xiaobaozi', '小包子', '2019-12-10 14:58:05', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (204, 16, 'xiaobaozi', '小包子', '2019-12-10 16:17:52', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (205, 16, 'xiaobaozi', '小包子', '2019-12-10 19:32:38', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (206, 16, 'xiaobaozi', '小包子', '2019-12-10 19:32:47', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (207, 1, 'zengbinhao', '曾斌昊', '2019-12-10 22:11:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (208, 20, 'zenbinhao98', '测试', '2019-12-11 00:03:23', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (209, 20, 'zenbinhao98', '测试', '2019-12-11 00:04:17', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (210, 1, 'zengbinhao', '曾斌昊', '2019-12-11 00:06:48', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (211, 1, 'zengbinhao', '曾斌昊', '2019-12-11 09:21:20', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (212, 21, 'zbh1999', '曾斌昊', '2019-12-11 16:18:25', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (213, 23, 'ceshi1', '测试', '2019-12-11 18:12:29', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (214, 23, 'ceshi1', '测试', '2019-12-12 09:54:53', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (215, 23, 'ceshi1', '测试', '2019-12-12 10:03:18', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (216, 23, 'ceshi1', '测试', '2019-12-12 10:07:26', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (217, 16, 'xiaobaozi', '小包子', '2019-12-12 10:12:35', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (218, 23, 'ceshi1', '测试', '2019-12-12 10:13:12', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (219, 23, 'ceshi1', '测试', '2019-12-12 13:38:09', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (220, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:39:29', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (221, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:40:19', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (222, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:40:40', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (223, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:44:57', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (224, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:45:24', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (225, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:46:10', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (226, 23, 'ceshi1', '测试', '2019-12-12 13:46:26', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (227, 23, 'ceshi1', '测试', '2019-12-12 13:48:21', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (228, 1, 'zengbinhao', '曾斌昊', '2019-12-12 13:48:33', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (229, 1, 'zengbinhao', '曾斌昊', '2019-12-12 14:02:52', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (230, 1, 'zengbinhao', '曾斌昊', '2019-12-12 14:32:07', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (231, 23, 'ceshi1', '测试', '2019-12-12 15:06:30', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (232, 1, 'zengbinhao', '曾斌昊', '2019-12-12 15:53:22', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (233, 1, 'zengbinhao', '曾斌昊', '2019-12-12 18:49:48', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (234, 1, 'zengbinhao', '曾斌昊', '2019-12-13 00:48:33', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (235, 1, 'zengbinhao', '曾斌昊', '2019-12-13 00:49:02', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (236, 1, 'zengbinhao', '曾斌昊', '2019-12-13 00:49:49', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (237, 1, 'zengbinhao', '曾斌昊', '2019-12-13 00:50:00', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (238, 1, 'zengbinhao', '曾斌昊', '2019-12-13 00:51:12', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (239, 1, 'zengbinhao', '曾斌昊', '2019-12-13 00:51:31', '122.238.168.10');
INSERT INTO `t_loginlog` VALUES (240, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:18:16', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (241, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:19:26', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (242, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:24:32', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (243, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:24:44', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (244, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:25:38', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (245, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:26:20', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (246, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:26:34', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (247, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:27:55', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (248, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:28:17', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (249, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:28:58', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (250, 1, 'zengbinhao', '曾斌昊', '2019-12-13 01:43:30', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (251, 23, 'ceshi1', '测试', '2019-12-13 01:43:42', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (252, 24, 'ceshi2', '测试2', '2019-12-13 02:00:48', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (253, 25, 'ceshi666', '测试666', '2019-12-13 10:30:13', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (254, 1, 'zengbinhao', '曾斌昊', '2019-12-13 10:34:01', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (255, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:38:21', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (256, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:41:03', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (257, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:54:57', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (258, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:57:37', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (259, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:58:41', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (260, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:59:11', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (261, 1, 'zengbinhao', '曾斌昊', '2019-12-16 09:59:24', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (262, 23, 'ceshi1', '测试', '2019-12-16 09:59:36', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (263, 1, 'zengbinhao', '曾斌昊', '2019-12-16 10:20:44', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (264, 1, 'zengbinhao', '曾斌昊', '2019-12-16 10:31:46', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (265, 23, 'ceshi1', '测试', '2019-12-16 10:37:13', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (266, 23, 'ceshi1', '测试', '2019-12-16 10:39:37', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (267, 1, 'zengbinhao', '曾斌昊', '2019-12-16 10:43:14', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (268, 23, 'ceshi1', '测试', '2019-12-16 10:43:31', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (269, 23, 'ceshi1', '测试', '2019-12-16 11:00:37', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (270, 26, 'ceshi999', '测试999', '2019-12-16 16:13:55', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (271, 1, 'zengbinhao', '曾斌昊', '2019-12-16 16:15:20', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (272, 26, 'ceshi999', '测试999', '2019-12-16 16:16:56', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (273, 23, 'ceshi1', '测试', '2019-12-16 22:56:36', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (274, 23, 'ceshi1', '测试', '2019-12-16 23:41:22', '122.238.170.120');
INSERT INTO `t_loginlog` VALUES (275, 1, 'zengbinhao', '曾斌昊', '2019-12-17 09:26:20', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (276, 23, 'ceshi1', '测试', '2019-12-17 09:27:18', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (277, 23, 'ceshi1', '测试', '2019-12-17 09:54:28', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (278, 1, 'zengbinhao', '曾斌昊', '2019-12-17 11:18:45', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (279, 1, 'zengbinhao', '曾斌昊', '2019-12-17 11:19:01', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (280, 1, 'zengbinhao', '曾斌昊', '2019-12-17 11:21:09', '122.225.60.238');
INSERT INTO `t_loginlog` VALUES (281, 1, 'zengbinhao', '曾斌昊', '2019-12-17 14:09:55', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (282, 23, 'ceshi1', '测试', '2019-12-17 14:13:31', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (283, 1, 'zengbinhao', '曾斌昊', '2019-12-17 14:42:17', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (284, 1, 'zengbinhao', '曾斌昊', '2019-12-17 15:03:23', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (285, 1, 'zengbinhao', '曾斌昊', '2019-12-17 15:25:32', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (286, 23, 'ceshi1', '测试', '2019-12-17 15:26:26', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (287, 23, 'ceshi1', '测试', '2019-12-17 16:14:26', '183.249.231.252');
INSERT INTO `t_loginlog` VALUES (288, 1, 'zengbinhao', '曾斌昊', '2019-12-18 09:17:18', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (289, 25, 'ceshi666', '测试666', '2019-12-18 09:18:23', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (290, 1, 'zengbinhao', '曾斌昊', '2019-12-18 09:21:51', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (291, 1, 'zengbinhao', '曾斌昊', '2019-12-18 12:18:25', '125.123.73.121');
INSERT INTO `t_loginlog` VALUES (292, 23, 'ceshi1', '测试', '2019-12-18 13:36:40', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (293, 23, 'ceshi1', '测试', '2019-12-18 13:40:46', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (294, 23, 'ceshi1', '测试', '2019-12-18 13:55:10', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (295, 23, 'ceshi1', '测试', '2019-12-18 14:14:11', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (296, 23, 'ceshi1', '测试', '2019-12-18 14:52:35', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (297, 23, 'ceshi1', '测试', '2019-12-18 15:09:20', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (298, 23, 'ceshi1', '测试', '2019-12-18 15:10:00', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (299, 1, 'zengbinhao', '曾斌昊', '2019-12-18 15:13:07', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (300, 23, 'ceshi1', '测试', '2019-12-18 15:13:33', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (301, 1, 'zengbinhao', '曾斌昊', '2019-12-18 15:25:10', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (302, 23, 'ceshi1', '测试', '2019-12-18 15:34:59', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (303, 1, 'zengbinhao', '曾斌昊', '2019-12-19 09:07:35', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (304, 23, 'ceshi1', '测试', '2019-12-19 09:08:20', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (305, 23, 'ceshi1', '测试', '2019-12-19 10:02:54', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (306, 23, 'ceshi1', '测试', '2019-12-19 10:04:50', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (307, 16, 'xiaobaozi', '小包子', '2019-12-19 11:29:57', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (308, 16, 'xiaobaozi', '小包子', '2019-12-19 12:03:08', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (309, 16, 'xiaobaozi', '小包子', '2019-12-19 12:57:03', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (310, 16, 'xiaobaozi', '小包子', '2019-12-19 14:11:21', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (311, 16, 'xiaobaozi', '小包子', '2019-12-19 14:25:54', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (312, 16, 'xiaobaozi', '小包子', '2019-12-19 14:29:43', '127.0.0.1');
INSERT INTO `t_loginlog` VALUES (313, 1, 'zengbinhao', '曾斌昊', '2019-12-20 11:09:29', '125.123.74.91');
INSERT INTO `t_loginlog` VALUES (314, 16, 'xiaobaozi', '小包子', '2019-12-20 11:19:42', '125.123.74.91');
INSERT INTO `t_loginlog` VALUES (315, 1, 'zengbinhao', '曾斌昊', '2019-12-20 12:18:33', '125.123.74.91');
INSERT INTO `t_loginlog` VALUES (316, 1, 'zengbinhao', '曾斌昊', '2019-12-20 13:37:56', '183.141.114.48');
INSERT INTO `t_loginlog` VALUES (317, 1, 'zengbinhao', '曾斌昊', '2019-12-20 13:49:53', '183.141.114.48');
INSERT INTO `t_loginlog` VALUES (318, 16, 'xiaobaozi', '小包子', '2019-12-20 13:50:11', '183.141.114.48');
INSERT INTO `t_loginlog` VALUES (319, 16, 'xiaobaozi', '小包子', '2019-12-20 20:45:39', '122.238.168.54');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `borrow_time` datetime NULL DEFAULT NULL,
  `return_time` datetime NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT NULL,
  `update_user_id` int(11) NULL DEFAULT NULL,
  `update_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_state` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL,
  `book_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES (1, '2019-12-07 14:47:14', '2019-12-09 13:58:33', 2, '李文魁', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (2, '2019-12-07 14:57:33', '2019-12-07 15:02:35', 2, '李文魁', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (3, '2019-12-07 15:07:56', '2019-12-07 15:09:11', 2, '李文魁', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (4, '2019-12-07 15:08:42', '2019-12-07 15:09:20', 2, '李文魁', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (5, '2019-12-07 15:08:44', '2019-12-07 15:09:26', 2, '李文魁', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (6, '2019-12-09 13:20:00', '2019-12-09 13:21:05', 16, '小包子', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (7, '2019-12-09 13:47:42', '2019-12-09 13:59:53', 16, '小包子', 0, 1, '曾斌昊', 1, 1, '红楼梦');
INSERT INTO `t_record` VALUES (8, '2019-12-09 15:25:14', NULL, 16, '小包子', 0, NULL, NULL, 0, 19, '斗罗大陆');
INSERT INTO `t_record` VALUES (9, '2019-12-11 00:06:00', '2019-12-11 00:07:08', 20, '测试', 0, 1, '曾斌昊', 1, 17, '我我我');
INSERT INTO `t_record` VALUES (10, '2019-12-12 10:13:26', '2019-12-18 15:13:18', 23, '测试', 0, 1, '曾斌昊', 1, 18, '遨四海');
INSERT INTO `t_record` VALUES (11, '2019-12-13 10:32:33', NULL, 25, '测试666', 0, NULL, NULL, 0, 19, '斗罗大陆');
INSERT INTO `t_record` VALUES (12, '2019-12-18 15:37:48', NULL, 23, '测试', 0, NULL, NULL, 0, 23, '朝花夕拾');

-- ----------------------------
-- Table structure for t_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock`  (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_stock
-- ----------------------------
INSERT INTO `t_stock` VALUES (1, '2019-12-09 21:19:18', 16, 19);
INSERT INTO `t_stock` VALUES (10, '2019-12-09 21:49:39', 16, 18);
INSERT INTO `t_stock` VALUES (11, '2019-12-09 21:49:49', 16, 17);
INSERT INTO `t_stock` VALUES (12, '2019-12-11 00:04:54', 20, 4);
INSERT INTO `t_stock` VALUES (13, '2019-12-11 00:05:44', 20, 17);
INSERT INTO `t_stock` VALUES (14, '2019-12-12 10:03:28', 23, 19);
INSERT INTO `t_stock` VALUES (15, '2019-12-13 10:30:26', 25, 19);
INSERT INTO `t_stock` VALUES (16, '2019-12-16 16:14:45', 26, 1);
INSERT INTO `t_stock` VALUES (17, '2019-12-18 09:18:39', 25, 18);
INSERT INTO `t_stock` VALUES (18, '2019-12-18 09:18:56', 25, 20);
INSERT INTO `t_stock` VALUES (19, '2019-12-18 15:35:24', 23, 23);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT NULL,
  `update_user_id` int(11) NULL DEFAULT NULL,
  `update_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '曾斌昊', 'zengbinhao', 'l2FHt9UpwiE=', '13387830000', 2, '2019-12-06 00:00:00', '2019-12-06 19:08:31', 1, 0, 1, '曾斌昊');
INSERT INTO `t_user` VALUES (2, '鸭子', 'xiaoyaya', 'l2FHt9UpwiE=', '13387330000', 0, '2019-12-06 18:58:25', '2019-12-08 15:21:59', 7, 0, 1, '曾斌昊');
INSERT INTO `t_user` VALUES (16, '小包子', 'xiaobaozi', 'l2FHt9UpwiE=', '18687330506', 0, '2019-12-06 19:26:45', '2019-12-08 16:04:29', 6, 0, 1, '曾斌昊');
INSERT INTO `t_user` VALUES (17, '刘思奇', 'liusiqi', 'siqi666', '18573334227', 0, '2019-12-10 21:08:51', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (18, '有点烦', 'sdaas', 'asd121', '18673381010', 0, '2019-12-10 21:08:50', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (19, '我叫小鸭子', 'xiaoyazi', 'yazi666', '17674792582', 0, '2019-12-10 22:43:07', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (20, '测试', 'zenbinhao98', '981126', '13387330597', 0, '2019-12-11 00:00:06', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (21, '曾斌昊', 'zbh1999', '981126', '13387330205', 0, '2019-12-11 16:17:36', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (22, '测试', 'ceshi123', '666666', '13387330507', 0, '2019-12-11 16:25:59', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (23, '测试', 'ceshi1', 'l2FHt9UpwiE=', '13387330001', 0, '2019-12-11 17:21:25', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (24, '测试2', 'ceshi2', 'l2FHt9UpwiE=', '13387330001', 0, '2019-12-13 01:58:24', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (25, '测试666', 'ceshi666', 'l2FHt9UpwiE=', '13387330599', 0, '2019-12-13 10:29:21', NULL, 0, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (26, '测试999', 'ceshi999', 'l2FHt9UpwiE=', '13387330001', 0, '2019-12-16 16:13:33', NULL, 0, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
