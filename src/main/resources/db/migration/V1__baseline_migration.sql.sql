-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Wrz 2021, 20:43
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `physio_node`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ailment`
--

CREATE TABLE `ailment` (
  `idailment` int(11) NOT NULL,
  `ailment_name` varchar(45) NOT NULL,
  `ailment_description` longtext NOT NULL,
  `user_iduser` int(11) NOT NULL,
  `attendingphysician_iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `ailment`
--

INSERT INTO `ailment` (`idailment`, `ailment_name`, `ailment_description`, `user_iduser`, `attendingphysician_iduser`) VALUES
(1, 'Złamanie kostki', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 1, 1),
(2, 'Skręcenie nadgarstka', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 1, 1),
(3, 'Złamanie kostki', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 2, 1),
(4, 'Skręcenie nadgarstka', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 2, 1),
(5, 'Test', 'szda', 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ailment_filepath`
--

CREATE TABLE `ailment_filepath` (
  `idailment_filepath` int(11) NOT NULL,
  `path` varchar(45) DEFAULT NULL,
  `ailment_idailment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ailment_indication`
--

CREATE TABLE `ailment_indication` (
  `idailment_indication` int(11) NOT NULL,
  `indication_header` varchar(45) NOT NULL,
  `indication_description` varchar(45) NOT NULL,
  `ailment_idailment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `ailment_indication`
--

INSERT INTO `ailment_indication` (`idailment_indication`, `indication_header`, `indication_description`, `ailment_idailment`) VALUES
(1, 'Zalecenia - test', 'Lorem Ipsum is simply dummy text of the print', 1),
(2, 'Zalecenia - test', 'Lorem Ipsum is simply dummy text of the print', 2),
(3, 'Zalecenia - test', 'Lorem Ipsum is simply dummy text of the print', 3),
(4, 'Zalecenia - test', 'Lorem Ipsum is simply dummy text of the print', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ailment_note`
--

CREATE TABLE `ailment_note` (
  `idailment_note` int(11) NOT NULL,
  `note_header` varchar(45) NOT NULL,
  `note_description` varchar(45) NOT NULL,
  `ailment_idailment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `ailment_note`
--

INSERT INTO `ailment_note` (`idailment_note`, `note_header`, `note_description`, `ailment_idailment`) VALUES
(1, 'Opis - test - check', 'Lorem Ipsum is simply dummy text of the print', 1),
(2, 'Opis - test', 'Lorem Ipsum is simply dummy text of the print', 2),
(3, 'Opis - test', 'Lorem Ipsum is simply dummy text of the print', 3),
(4, 'Opis - test', 'Lorem Ipsum is simply dummy text of the print', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `mygroup`
--

CREATE TABLE `mygroup` (
  `idmygroup` int(11) NOT NULL,
  `mygroup_name` varchar(45) NOT NULL,
  `mygroup_description` varchar(45) NOT NULL,
  `mygroup_owner` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `mygroup`
--

INSERT INTO `mygroup` (`idmygroup`, `mygroup_name`, `mygroup_description`, `mygroup_owner`) VALUES
(1, 'Test', 'Lorem Ipsum is simply dummy text of the print', 1),
(2, 'Test2', 'Lorem Ipsum is simply dummy text of the print', 1),
(26, 'Test Grupowani', 'Sadasdas', 1),
(27, 'adsasd', 'asdasd', 1),
(28, 'asd', 'd', 1),
(29, 'Test-imie', 'Test', 1),
(30, 'Test-imie', 'Test', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_surname` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_dob` date NOT NULL,
  `user_role_iduser_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`iduser`, `user_name`, `user_surname`, `user_email`, `user_dob`, `user_role_iduser_role`) VALUES
(1, 'Admin', 'Admin', 'admin@gmail.com', '1999-02-12', 1),
(2, 'Adam', 'Machalica', 'machalica@gmail.com', '2001-02-21', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_mygroup`
--

CREATE TABLE `user_mygroup` (
  `user_iduser` int(11) NOT NULL,
  `mygroup_idmygroup` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `user_mygroup`
--

INSERT INTO `user_mygroup` (`user_iduser`, `mygroup_idmygroup`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_role`
--

CREATE TABLE `user_role` (
  `iduser_role` int(11) NOT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  `role_frequency` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `user_role`
--

INSERT INTO `user_role` (`iduser_role`, `role_name`, `role_frequency`) VALUES
(1, 'admin', 1),
(2, 'user', 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `ailment`
--
ALTER TABLE `ailment`
  ADD PRIMARY KEY (`idailment`),
  ADD KEY `fk_ailment_user2_idx` (`attendingphysician_iduser`),
  ADD KEY `fk_ailment_user1_idx` (`user_iduser`);

--
-- Indeksy dla tabeli `ailment_filepath`
--
ALTER TABLE `ailment_filepath`
  ADD PRIMARY KEY (`idailment_filepath`),
  ADD KEY `fk_ailment_filepath_ailment1_idx` (`ailment_idailment`);

--
-- Indeksy dla tabeli `ailment_indication`
--
ALTER TABLE `ailment_indication`
  ADD PRIMARY KEY (`idailment_indication`),
  ADD KEY `fk_ailment_indication_ailment1_idx` (`ailment_idailment`);

--
-- Indeksy dla tabeli `ailment_note`
--
ALTER TABLE `ailment_note`
  ADD PRIMARY KEY (`idailment_note`),
  ADD KEY `fk_ailment_note_ailment1_idx` (`ailment_idailment`);

--
-- Indeksy dla tabeli `mygroup`
--
ALTER TABLE `mygroup`
  ADD PRIMARY KEY (`idmygroup`),
  ADD KEY `fk_mygroup_user1_idx` (`mygroup_owner`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  ADD KEY `fk_user_user_role1_idx` (`user_role_iduser_role`);

--
-- Indeksy dla tabeli `user_mygroup`
--
ALTER TABLE `user_mygroup`
  ADD PRIMARY KEY (`user_iduser`,`mygroup_idmygroup`),
  ADD KEY `fk_user_has_mygroup_mygroup1_idx` (`mygroup_idmygroup`),
  ADD KEY `fk_user_has_mygroup_user1_idx` (`user_iduser`);

--
-- Indeksy dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`iduser_role`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `ailment`
--
ALTER TABLE `ailment`
  MODIFY `idailment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `ailment_filepath`
--
ALTER TABLE `ailment_filepath`
  MODIFY `idailment_filepath` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `ailment_indication`
--
ALTER TABLE `ailment_indication`
  MODIFY `idailment_indication` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `ailment_note`
--
ALTER TABLE `ailment_note`
  MODIFY `idailment_note` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `mygroup`
--
ALTER TABLE `mygroup`
  MODIFY `idmygroup` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `user_role`
--
ALTER TABLE `user_role`
  MODIFY `iduser_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `ailment`
--
ALTER TABLE `ailment`
  ADD CONSTRAINT `fk_ailment_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ailment_user2` FOREIGN KEY (`attendingphysician_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `ailment_filepath`
--
ALTER TABLE `ailment_filepath`
  ADD CONSTRAINT `fk_ailment_filepath_ailment1` FOREIGN KEY (`ailment_idailment`) REFERENCES `ailment` (`idailment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `ailment_indication`
--
ALTER TABLE `ailment_indication`
  ADD CONSTRAINT `fk_ailment_indication_ailment1` FOREIGN KEY (`ailment_idailment`) REFERENCES `ailment` (`idailment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `ailment_note`
--
ALTER TABLE `ailment_note`
  ADD CONSTRAINT `fk_ailment_note_ailment1` FOREIGN KEY (`ailment_idailment`) REFERENCES `ailment` (`idailment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `mygroup`
--
ALTER TABLE `mygroup`
  ADD CONSTRAINT `fk_mygroup_user1` FOREIGN KEY (`mygroup_owner`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_user_role1` FOREIGN KEY (`user_role_iduser_role`) REFERENCES `user_role` (`iduser_role`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `user_mygroup`
--
ALTER TABLE `user_mygroup`
  ADD CONSTRAINT `fk_user_has_mygroup_mygroup1` FOREIGN KEY (`mygroup_idmygroup`) REFERENCES `mygroup` (`idmygroup`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_has_mygroup_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
