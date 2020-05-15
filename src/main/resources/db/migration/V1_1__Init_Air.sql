drop table if exists flight.airline;
CREATE TABLE flight.`airline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '항공사 관리 ID',
  `code` char(2) COLLATE utf8mb4_bin NOT NULL COMMENT '항공사 코드',
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '항공사명',
  PRIMARY KEY (`id`),
  KEY `code_idx` (`code`),
  KEY `name_idx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='항공사 관리';
