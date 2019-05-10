-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 10, 2019 alle 15:22
-- Versione del server: 10.1.38-MariaDB
-- Versione PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wtprojectdb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `campaign`
--

CREATE TABLE `campaign` (
  `id` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf16_bin NOT NULL,
  `customer` varchar(128) COLLATE utf16_bin NOT NULL,
  `status` varchar(8) COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `resolution` varchar(10) COLLATE utf16_bin NOT NULL,
  `shooting_date` date NOT NULL,
  `origin` varchar(32) COLLATE utf16_bin NOT NULL,
  `localityid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `locality`
--

CREATE TABLE `locality` (
  `id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(64) COLLATE utf16_bin NOT NULL,
  `town` varchar(64) COLLATE utf16_bin NOT NULL,
  `region` varchar(64) COLLATE utf16_bin NOT NULL,
  `campaignid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `validity` tinyint(1) NOT NULL,
  `reliability` varchar(10) COLLATE utf16_bin NOT NULL,
  `comment` varchar(256) COLLATE utf16_bin NOT NULL,
  `userid` int(11) NOT NULL,
  `imageid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(128) COLLATE utf16_bin NOT NULL,
  `password` varchar(128) COLLATE utf16_bin NOT NULL,
  `role` tinyint(1) NOT NULL,
  `experience` varchar(10) COLLATE utf16_bin DEFAULT NULL,
  `photo` varchar(256) COLLATE utf16_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `user_campaign`
--

CREATE TABLE `user_campaign` (
  `id` int(11) NOT NULL,
  `campaignid` int(11) NOT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `campaign`
--
ALTER TABLE `campaign`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`);

--
-- Indici per le tabelle `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idlocality` (`localityid`);

--
-- Indici per le tabelle `locality`
--
ALTER TABLE `locality`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idcampaign` (`campaignid`);

--
-- Indici per le tabelle `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`),
  ADD KEY `imageid` (`imageid`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `user_campaign`
--
ALTER TABLE `user_campaign`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idcampaign` (`campaignid`),
  ADD KEY `iduser` (`userid`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `campaign`
--
ALTER TABLE `campaign`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `locality`
--
ALTER TABLE `locality`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `user_campaign`
--
ALTER TABLE `user_campaign`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `campaign`
--
ALTER TABLE `campaign`
  ADD CONSTRAINT `campaign_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`localityid`) REFERENCES `locality` (`id`);

--
-- Limiti per la tabella `locality`
--
ALTER TABLE `locality`
  ADD CONSTRAINT `locality_ibfk_1` FOREIGN KEY (`campaignid`) REFERENCES `campaign` (`id`);

--
-- Limiti per la tabella `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`imageid`) REFERENCES `image` (`id`);

--
-- Limiti per la tabella `user_campaign`
--
ALTER TABLE `user_campaign`
  ADD CONSTRAINT `user_campaign_ibfk_1` FOREIGN KEY (`campaignid`) REFERENCES `campaign` (`id`),
  ADD CONSTRAINT `user_campaign_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
