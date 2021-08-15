-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-08-2021 a las 07:12:51
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_youtube_ecommerce`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `vigencia` bit(1) DEFAULT NULL,
  `foto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `vigencia`, `foto_id`) VALUES
(1, 'Tallarines', b'1', 8),
(2, 'Arroz', b'1', 9),
(3, 'Postres', b'1', 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `departamento` varchar(100) DEFAULT NULL,
  `direccion_envio` varchar(500) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `nombres` varchar(100) DEFAULT NULL,
  `num_doc` varchar(11) DEFAULT NULL,
  `provincia` varchar(100) DEFAULT NULL,
  `tipo_doc` varchar(20) DEFAULT NULL,
  `foto_id` bigint(20) DEFAULT NULL,
  `apellido_materno` varchar(100) DEFAULT NULL,
  `apellido_paterno` varchar(100) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `departamento`, `direccion_envio`, `distrito`, `nombres`, `num_doc`, `provincia`, `tipo_doc`, `foto_id`, `apellido_materno`, `apellido_paterno`, `telefono`) VALUES
(1, 'Lambayeque', 'Calle sucre 903', 'Ferreñafe', 'Alexander', '78019778', 'Ferreñafe', 'DNI', 2, 'Medina', 'Fuentes', '917967148'),
(2, 'Lambayeque', 'Calle sucre 903', 'Ferreñafe', 'Jorge', '56654123', 'Ferreñafe', 'DNI', 3, 'Medina', 'Fuentes', '963159632'),
(3, 'Lambayeque', 'Calle Sucre 903', 'Ferreñafe', 'Mary Leonor', '14589632', 'Ferreñafe', 'DNI', 4, 'Meoño', 'Medina', '965423158'),
(5, 'Lambayeque', 'Calle Sucre 903', 'Ferreñafe', 'Yadhira', '25639874', 'Ferreñafe', 'DNI', 6, 'Medina', 'Fuentes', '902963852'),
(6, 'Lambayeque', 'Calle Nicanor Carmona N° 345', 'Ferreñafe', 'Ximena', '36985621', 'Ferreñafe', 'DNI', 7, 'Samame', 'Quezada', '963258741');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

CREATE TABLE `detalle_pedido` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `pedido_id` int(11) DEFAULT NULL,
  `platillo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documento_almacenado`
--

CREATE TABLE `documento_almacenado` (
  `id` bigint(20) NOT NULL,
  `eliminado` bit(1) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `documento_almacenado`
--

INSERT INTO `documento_almacenado` (`id`, `eliminado`, `estado`, `extension`, `file_name`, `nombre`) VALUES
(1, b'0', 'A', '.jpg', '0fa5c76a-521e-4fb5-b9c9-1d84a183d892', 'Arroz con pato'),
(2, b'0', 'A', '.jpg', '37813d3d-da13-4777-bf14-da55f376e69b', 'Alexander Photo'),
(3, b'0', 'A', '.jpg', '8609515d-d99c-406e-9729-abdf1f00175d', 'Jorge Photo'),
(4, b'0', 'A', '.jpg', '2ef59ec9-d6f4-4dbc-b2ef-050ec63231d7', 'profilePh168202117195'),
(6, b'0', 'A', '.jpg', '51bae59b-a942-44a5-bc51-d7025c742603', 'profilePh2182021143528'),
(7, b'0', 'A', '.jpg', '0e50bd47-3325-40ef-976e-3b8394a76c58', 'profilePh218202115471'),
(8, b'0', 'A', '.png', '4ccffdc9-7beb-4d7b-9056-1a92414c0fb7', 'Tallarines'),
(9, b'0', 'A', '.png', '353d7d4d-8e02-4acc-a311-35664c574b72', 'Arroz'),
(10, b'0', 'A', '.png', '42402522-20fe-4492-8632-d3cffbfa79df', 'Arroz Chaufa'),
(11, b'0', 'A', '.png', 'd5e6d889-de10-4bbb-984d-a42cb69f2ce3', 'Causa Limeña'),
(12, b'0', 'A', '.png', 'd3682e52-9932-4093-a5fa-0be72668990c', 'Saltado de Carne'),
(13, b'0', 'A', '.png', '7d743dab-55f0-4e2e-84f6-01f968da1c57', 'Ají de Gallina'),
(14, b'0', 'A', '.png', '5b21f3a6-b117-445a-9155-e1d4d1a2d072', 'Locro de Zapallo'),
(15, b'0', 'A', '.png', '6a3acd4d-a554-4b0d-ba34-8512021d0a5b', 'Arroz con Pollo'),
(16, b'0', 'A', '.png', '56b67c0f-94f3-4366-bf36-f376119d4f06', 'Tallarines Rojos'),
(17, b'0', 'A', '.png', 'd3c03e01-aeff-4bb1-9c41-f59db483dbd5', 'Seco de Cabrito'),
(18, b'0', 'A', '.png', '93b013a8-5801-4d8c-9ebe-7c3f2ee52ae5', 'Humitas'),
(19, b'0', 'A', '.png', 'c0d7483f-b37f-40a6-a98d-b975ae64d08c', 'Queso Helado'),
(20, b'0', 'A', '.png', 'ea79db70-4557-43c6-9dbc-4edb0e6f77e5', 'Mondonguito'),
(21, b'0', 'A', '.png', '89a37742-9eb2-4727-95a5-aa59eb713908', 'Tallarines Verdes'),
(22, b'0', 'A', '.jpg', '569ef8c3-0c11-4047-9218-d7094d39406d', 'Postres');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `anular_pedido` bit(1) DEFAULT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platillo`
--

CREATE TABLE `platillo` (
  `id` int(11) NOT NULL,
  `descripcion_platillo` varchar(5000) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `vigencia` bit(1) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `foto_id` bigint(20) DEFAULT NULL,
  `recomendado` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `platillo`
--

INSERT INTO `platillo` (`id`, `descripcion_platillo`, `nombre`, `precio`, `stock`, `vigencia`, `categoria_id`, `foto_id`, `recomendado`) VALUES
(1, 'Es un sabroso plato muy popular en Perú que se prepara con arroz frito, diversas carnes, verduras y salsa de soja. Posiblemente es uno de los platos más consumidos en la gastronomía peruana por su fácil elaboración y su rico sabor. Hay muchas variedades ya que puede prepararse con pollo, carne de ternera, huevo o salchicha.', 'Arroz Chaufa', 15, 50, b'1', 2, 10, b'1'),
(2, 'El arroz con pollo es un plato típico de América Latina y España 1​ con variaciones regionales según el país. Consiste en arroz cocinado con pollo, en presas o desmechado, verduras (ají pimentón, zanahoria en cubos, apio, habichuelas, cebolla, maíz desgranado, aceitunas, arvejas, alcaparras), y sazonado con especias (laurel, tomillo, cilantro, ajo)', 'Arroz con Pollo', 20, 50, b'1', 2, 15, b'1'),
(3, 'Los Tallarines rojos con pollo es un plato de fondo que se come mucho en el Perú. Este plato es una fusión de la comida peruana e italiana, ya que se origina en el spaghetti ala bolognese.', 'Tallarines Rojos', 6, 50, b'1', 1, 16, b'1'),
(4, 'Una de las comidas de la costar que suele acompañar los platos de carne, se tratan de unos tallarines a base de leche evaporada, espinaca, nueces pecanas, albahaca y parmesano. Todos estos ingredientes forman la característica salsa verde.', 'Tallarines Verdes', 12, 50, b'1', 1, 21, b'1'),
(5, 'Como su nombre indica, el arroz con pato es un plato de arroz, picante y ácido, cuyos ingredientes principales son el pato, macerado en ajo y vinagre, ají amarillo que le da el picor y un arroz verdoso gracias al culantro que se añade.​ Es común añadir en la cocción una cantidad al gusto de cerveza negra o de chicha de jora.​ Se sirve acompañado con salsa criolla. Existen variantes que remplazan el culantro por palillo, para darle un color amarillo.', 'Arroz con Pato', 25, 50, b'1', 2, 1, b'1'),
(6, 'El siguiente plato se sirve con una base de arroz blanco y papas, se prepara un sofrito con ají amarillo, ajo, pan, cebolla y parmesano, se le añade la gallina hervida desmenuzada y nueces molidas. Listo para servir.', 'Ají de Gallina', 18, 50, b'1', 2, 13, b'1'),
(7, 'Uno de los platos típicos de la costa, también conocido como guiso de auyama o de calabaza. Básicamente se trata de un locro de zapallo y papa, acompañado de maíz, cebolla, ajo, guisantes, sazonado con orégano y sal al gusto, y aceite. Es un plato que suele acompañarse con arroz.', 'Locro de Zapallo', 25, 50, b'1', 2, 14, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `clave` varchar(20) DEFAULT NULL,
  `email` varchar(500) DEFAULT NULL,
  `vigencia` bit(1) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `clave`, `email`, `vigencia`, `cliente_id`) VALUES
(1, 'admin123', 'afuentes@live.com', b'1', 1),
(2, 'jorge123', 'jorgefuentesmedina@live.com', b'1', 2),
(3, '123', 'mary@live.com', b'1', 3),
(5, 'estrella123', 'yadhi_2002@outlook.es', b'1', 5),
(6, 'xime123', 'ximena@hotmail.es', b'1', 6);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbyh5eextelhhs5gyxj7giuspd` (`foto_id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKorx40wtfkkqg46bht8mt4vb3a` (`foto_id`);

--
-- Indices de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgqvba9e7dildyw45u0usdj1k2` (`pedido_id`),
  ADD KEY `FKhyip16w63t7nx78o23cu3fks4` (`platillo_id`);

--
-- Indices de la tabla `documento_almacenado`
--
ALTER TABLE `documento_almacenado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`);

--
-- Indices de la tabla `platillo`
--
ALTER TABLE `platillo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkchwn4n0badohpdsfeb2qwbca` (`categoria_id`),
  ADD KEY `FK42mi3n2tu0656xlm58cpaojb` (`foto_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp3dqdf5mc3lqr5y9p3b0omjl9` (`cliente_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `documento_almacenado`
--
ALTER TABLE `documento_almacenado`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `platillo`
--
ALTER TABLE `platillo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `FKbyh5eextelhhs5gyxj7giuspd` FOREIGN KEY (`foto_id`) REFERENCES `documento_almacenado` (`id`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FKorx40wtfkkqg46bht8mt4vb3a` FOREIGN KEY (`foto_id`) REFERENCES `documento_almacenado` (`id`);

--
-- Filtros para la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD CONSTRAINT `FKgqvba9e7dildyw45u0usdj1k2` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKhyip16w63t7nx78o23cu3fks4` FOREIGN KEY (`platillo_id`) REFERENCES `platillo` (`id`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `platillo`
--
ALTER TABLE `platillo`
  ADD CONSTRAINT `FK42mi3n2tu0656xlm58cpaojb` FOREIGN KEY (`foto_id`) REFERENCES `documento_almacenado` (`id`),
  ADD CONSTRAINT `FKkchwn4n0badohpdsfeb2qwbca` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKp3dqdf5mc3lqr5y9p3b0omjl9` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
