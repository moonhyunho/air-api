drop table if exists flight.airport;
CREATE TABLE flight.`airport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '공항 관리 ID',
  `code` char(3) COLLATE utf8mb4_bin NOT NULL COMMENT '공항 코드',
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '공항명',
  `nt_code` char(2) COLLATE utf8mb4_bin COMMENT '국가 코드',
  PRIMARY KEY (`id`),
  KEY `code_idx` (`code`),
  KEY `name_idx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='공항 관리';
