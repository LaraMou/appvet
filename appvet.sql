-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 18-05-2021 a las 10:16:21
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `appvet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
CREATE TABLE IF NOT EXISTS `mensajes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcg59bj1bktu3y2qololmbokqd` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `description`, `task_id`) VALUES
(1, 'radiografia', 4),
(2, 'analitica', 4),
(3, 'cirugia', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_date` date DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `estado` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `finish` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `motivo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `task`
--

INSERT INTO `task` (`id`, `created_date`, `last_modified_date`, `description`, `estado`, `finish`, `last_modified_by`, `motivo`, `title`) VALUES
(1, '2021-05-18 11:24:14.466795', '2021-05-18', 'descripcion1', 'PENDIENTE', b'0', 'Pablo', 'radiografia', 'task1'),
(2, '2021-05-18 11:24:14.466795', '2021-05-18', 'descripcion2', 'FINALIZADO', b'1', 'Ana', 'radiografia', 'task2'),
(3, '2021-05-18 11:24:14.466795', '2021-05-18', 'descripcion3', 'PENDIENTE', b'0', 'Pablo', 'radiografia', 'task3'),
(4, '2021-05-18 11:24:14.586977', '2021-05-18', 'Flipy en consulta', 'pendiente', b'0', 'Tano', 'analítica', 'Consulta'),
(5, '2021-05-18 11:24:14.586977', '2021-05-18', 'Rufo en consulta', 'pendiente', b'0', 'Tano', 'analítica', 'Consulta'),
(6, '2021-05-18 11:24:14.586977', '2021-05-18', 'Pepito en consulta', 'pendiente', b'0', 'Tano', 'analítica', 'Consulta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `disponibilidad` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `foto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `apellido`, `create_at`, `disponibilidad`, `email`, `foto`, `nombre`) VALUES
(1, 'testApellido', '2021-05-18', NULL, 'mlra@gmail.com', NULL, 'Test1'),
(2, 'testApellido', '2021-05-18', NULL, 'mlra2@gmail.com', NULL, 'Test2'),
(3, 'testApellido', '2021-05-18', NULL, 'mlra3@gmail.com', NULL, 'Test3'),
(4, 'Torroja', '2021-05-18', NULL, 'Anaunomas@gmail.com', NULL, 'Ana'),
(5, 'Prieto', '2021-05-18', NULL, 'unomas@gmail.com', NULL, 'Pablo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_task`
--

DROP TABLE IF EXISTS `user_task`;
CREATE TABLE IF NOT EXISTS `user_task` (
  `user_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  KEY `FKvs34bjkmpbk2e54qlrol3ilt` (`task_id`),
  KEY `FKr2jik008e3jx6r1fal5e9aq1n` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `user_task`
--

INSERT INTO `user_task` (`user_id`, `task_id`) VALUES
(4, 1),
(4, 2),
(4, 3);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `FKcg59bj1bktu3y2qololmbokqd` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`);

--
-- Filtros para la tabla `user_task`
--
ALTER TABLE `user_task`
  ADD CONSTRAINT `FKr2jik008e3jx6r1fal5e9aq1n` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKvs34bjkmpbk2e54qlrol3ilt` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
