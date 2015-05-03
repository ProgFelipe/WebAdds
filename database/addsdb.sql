-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-05-2015 a las 21:36:50
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `addsdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_canales`
--

CREATE TABLE IF NOT EXISTS `t_canales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `author` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `img_url` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `t_canales`
--

INSERT INTO `t_canales` (`id`, `nombre`, `descripcion`, `author`, `fecha`, `img_url`) VALUES
(1, 'Nike', 'Feliz Nuevo año nuevo precio', 'felipe', '2015-04-27 14:37:19', 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQU9GWrfSsDWvgRdcrKt3uissP9sKsHMiQTLG1pRdDKUFHOoA4B'),
(2, 'Adidas', 'Adidas tiene nuevos precios 75% de descuento', 'Pepe', '2015-04-27 14:44:00', 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw8QEBQNEBQUFBAWFxAUEBgYFxIUFxUVFBQXFxUXFxQYHCggGBolGxUVIjEiJSkrLi4uFx8zODMsNygtLisBCgoKDA0NDg0MDysZHxksKysrKyssLCsrKysrLCsrKysrKysrNysrKysrLCsrKysrKysrKysrKysrKywsKysrK//AABEIAOEA4QMBIgACEQEDEQH/'),
(3, 'Adidas', 'adidas 75% off', 'Pepe', '2015-04-27 14:45:33', 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQasl-yrceQ-XyNHoS7V045vKA8yEy05t8PB1dhHtRlBsphfwND'),
(5, 'ESPN', 'ESPN', 'felipe', '2015-04-27 18:44:16', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwq3qKE83t7s6kNgVW-P33CF5BNIe75wsYR_9Wk6ZN_6Y0XjAA'),
(6, 'Art', 'Canal de Arte contemporaneo', 'felipe', '2015-04-27 18:54:31', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPonSGvOL4TrHtZXWKWkz0xd5PWBPxBNU64fq_m7S7-kO6wbgK'),
(7, 'Ciencia', 'Canal sobre lo Ãºiltimo en ciencia y tecnologÃ­a ', 'Pepe', '2015-04-27 19:05:28', 'http://www.elarsenal.net/wp-content/uploads/2014/12/ccs.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_messages`
--

CREATE TABLE IF NOT EXISTS `t_messages` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT,
  `idauthor` int(11) NOT NULL,
  `idchannel` int(11) NOT NULL,
  `message` text COLLATE utf8_spanish_ci NOT NULL,
  `urlMedia` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idMessage`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `t_messages`
--

INSERT INTO `t_messages` (`idMessage`, `idauthor`, `idchannel`, `message`, `urlMedia`, `date`) VALUES
(1, 1, 7, 'La ciencia moderna transforma los limites de la imaginación.', 'http://espiritualidadcontemporanea.com/blog/wp-content/uploads/2014/11/ciencia-2..png', '2015-05-02 20:42:16'),
(2, 1, 7, 'Google now', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAiYsfutX_iwrJ8QR9VGP8IzQcccTHDLnAHedThwySGRZmzdIb3A', '2015-05-02 22:36:26'),
(3, 1, 7, 'Tesla Motors, car panel', 'https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQpMQDx90OV3vMPAv3ZCEy8UccqYtHOAxEqHmCi1xgWxKTsdqu-rg', '2015-05-02 22:39:09'),
(4, 1, 5, 'El campeón defensor, el Real Madrid, jugará contra la Juventus en una de las semifinales de la Liga de Campeones, torneo en el que busca convertirse en el primer club en ganar el título en años consecutivos.', 'http://ichef.bbci.co.uk/news/ws/304/amz/worldservice/live/assets/images/2015/04/24/150424110001_champions_304x171_getty.jpg', '2015-05-02 22:41:36'),
(5, 1, 2, 'Adidas es una compaÃ±Ã­a multinacional fabricante de calzado, ropa deportiva y otros productos relacionados con el deporte y la moda; cuya sede central se encuentra en Herzogenaurach, Alemania', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD6kmyInIvNxGP6QRLw25AG_ChzBfr4Qns2Wxl339b15yM6HWhAA', '2015-05-02 23:46:22'),
(6, 2, 7, 'Prueba', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD6kmyInIvNxGP6QRLw25AG_ChzBfr4Qns2Wxl339b15yM6HWhAA', '2015-05-02 23:47:29'),
(7, 1, 7, 'Test3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD6kmyInIvNxGP6QRLw25AG_ChzBfr4Qns2Wxl339b15yM6HWhAA', '2015-05-02 23:49:10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_suscriptores`
--

CREATE TABLE IF NOT EXISTS `t_suscriptores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcanal` int(11) NOT NULL,
  `idsuscriptor` int(11) NOT NULL,
  `fecha_suscripcion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mailme` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=15 ;

--
-- Volcado de datos para la tabla `t_suscriptores`
--

INSERT INTO `t_suscriptores` (`id`, `idcanal`, `idsuscriptor`, `fecha_suscripcion`, `mailme`) VALUES
(10, 7, 1, '2015-04-28 00:46:10', 0),
(12, 5, 2, '2015-05-02 23:37:40', 1),
(13, 7, 2, '2015-05-02 23:38:04', 1),
(14, 2, 1, '2015-05-02 23:45:42', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_users`
--

CREATE TABLE IF NOT EXISTS `t_users` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `t_users`
--

INSERT INTO `t_users` (`id_usuario`, `username`, `password`, `email`) VALUES
(1, 'felipe', 'felipe', 'felipegi91@gmail.com'),
(2, 'Pepe', 'addsfree', 'felipegutierrezi@hotmail.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
