-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2020 a las 17:13:31
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `idfactura` int(11) NOT NULL,
  `fac_fecha` varchar(45) NOT NULL,
  `id_usuariovendedor` int(11) NOT NULL,
  `id_usuariocomprador` int(11) NOT NULL,
  `formaspagos_idformaspagos` int(11) NOT NULL,
  `fac_estado` varchar(1) NOT NULL DEFAULT 'X'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`idfactura`, `fac_fecha`, `id_usuariovendedor`, `id_usuariocomprador`, `formaspagos_idformaspagos`, `fac_estado`) VALUES
(1, 'Sun Jun 07 17:42:42 COT 2020', 1, 1, 1, 'X'),
(2, 'Sun Jun 07 17:44:32 COT 2020', 1, 1, 1, 'X'),
(3, 'Sun Jun 07 21:13:04 COT 2020', 1, 1, 1, 'X'),
(4, 'Mon Jun 08 10:23:07 COT 2020', 1, 1, 1, 'X'),
(5, 'Mon Jun 08 10:38:39 COT 2020', 1, 1, 1, 'X'),
(6, 'Mon Jun 08 10:39:32 COT 2020', 1, 1, 1, '/'),
(7, 'Mon Jun 08 10:41:48 COT 2020', 1, 1, 1, 'L');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formaspagos`
--

CREATE TABLE `formaspagos` (
  `idformaspagos` int(11) NOT NULL,
  `forpa_descipcion` varchar(45) NOT NULL,
  `forpa_estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `formaspagos`
--

INSERT INTO `formaspagos` (`idformaspagos`, `forpa_descipcion`, `forpa_estado`) VALUES
(1, 'Pago en efectivo', 'a');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `pro_descripcion` varchar(45) NOT NULL,
  `pro_nombre` varchar(45) NOT NULL,
  `pro_marca` varchar(45) NOT NULL,
  `pro_codigobarra` varchar(45) NOT NULL,
  `pro_cantidad_disponible` int(45) NOT NULL,
  `pro_precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idproducto`, `pro_descripcion`, `pro_nombre`, `pro_marca`, `pro_codigobarra`, `pro_cantidad_disponible`, `pro_precio`) VALUES
(1, 'saddsdf', 'sdfsf', 'sdfsdf', 'sdfdsf', 13, 2133),
(2, 'Jabon en barra axion', 'jabon', 'axion', 'sdifn4oinf0f3in4rfporfn', 28, 1200),
(3, 'jabon de baño ariel', 'jabon ariel', 'ariel', 'pdskfn34ruipcneurf34', 28, 1800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_de_factura`
--

CREATE TABLE `productos_de_factura` (
  `idproductos_de_factura` int(11) NOT NULL,
  `factura_idfactura` int(11) NOT NULL,
  `producto_idproducto` int(11) NOT NULL,
  `profac_cantidad` int(11) NOT NULL,
  `profa_estado` varchar(1) NOT NULL DEFAULT 'X'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos_de_factura`
--

INSERT INTO `productos_de_factura` (`idproductos_de_factura`, `factura_idfactura`, `producto_idproducto`, `profac_cantidad`, `profa_estado`) VALUES
(21, 6, 1, 6, 'X'),
(22, 5, 2, 5, 'X'),
(23, 5, 3, 3, 'X');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `user_nombres` varchar(45) NOT NULL,
  `user_apellidos` varchar(45) NOT NULL,
  `user_tipo` varchar(45) NOT NULL,
  `user_telefono` varchar(45) NOT NULL,
  `user_identificacion` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `user_nombres`, `user_apellidos`, `user_tipo`, `user_telefono`, `user_identificacion`, `user_email`, `user_password`) VALUES
(1, 'Andres Felipe ', 'Parra Ceballos', 'UsuarioVendedor', '313 2285831', '1075313871', 'andresparraparra-@hotmail.com', '160998Felipe*_'),
(2, 'Juan Edison', 'Giraldo Zuluaga', 'UsuarioVendedor', '313 2285831', '1075313872', 'juan-1@hotmail.com', '1234567');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`idfactura`),
  ADD KEY `fk_factura_usuario_idx` (`id_usuariovendedor`),
  ADD KEY `fk_factura_usuario1_idx` (`id_usuariocomprador`),
  ADD KEY `fk_factura_formaspagos1_idx` (`formaspagos_idformaspagos`);

--
-- Indices de la tabla `formaspagos`
--
ALTER TABLE `formaspagos`
  ADD PRIMARY KEY (`idformaspagos`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idproducto`);

--
-- Indices de la tabla `productos_de_factura`
--
ALTER TABLE `productos_de_factura`
  ADD PRIMARY KEY (`idproductos_de_factura`),
  ADD KEY `fk_productos_de_factura_factura1_idx` (`factura_idfactura`),
  ADD KEY `fk_productos_de_factura_producto1_idx` (`producto_idproducto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `user_identificacion_UNIQUE` (`user_identificacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `idfactura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `formaspagos`
--
ALTER TABLE `formaspagos`
  MODIFY `idformaspagos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos_de_factura`
--
ALTER TABLE `productos_de_factura`
  MODIFY `idproductos_de_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_formaspagos1` FOREIGN KEY (`formaspagos_idformaspagos`) REFERENCES `formaspagos` (`idformaspagos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_factura_usuario` FOREIGN KEY (`id_usuariovendedor`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_factura_usuario1` FOREIGN KEY (`id_usuariocomprador`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productos_de_factura`
--
ALTER TABLE `productos_de_factura`
  ADD CONSTRAINT `fk_productos_de_factura_factura1` FOREIGN KEY (`factura_idfactura`) REFERENCES `factura` (`idfactura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_productos_de_factura_producto1` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
