SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `SVBE` ;
CREATE SCHEMA IF NOT EXISTS `SVBE` DEFAULT CHARACTER SET utf8 ;
USE `SVBE` ;

-- -----------------------------------------------------
-- Table `Sessao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sessao` ;

CREATE TABLE IF NOT EXISTS `Sessao` (
  `ID` VARCHAR(150) NOT NULL,
  `Nome` VARCHAR(150) NOT NULL,
  `Descricao` VARCHAR(250) NOT NULL,
  `Tipo` VARCHAR(1) NOT NULL,
  `Opcao1` VARCHAR(3) NULL,
  `Opcao2` VARCHAR(3) NULL,
  `Participacao` VARCHAR(1) NOT NULL,
  `Data_Inicial` VARCHAR(10) NOT NULL,
  `Data_Final` VARCHAR(10) NULL,
  `Controle` VARCHAR(1) NULL,
  `Qtd_Max` INT NULL,
  `Qtd_VotosPos` INT NULL,
  `Qtd_VotosNeg` INT NULL,
  `Estado` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Politico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Politico` ;

CREATE TABLE IF NOT EXISTS `Politico` (
  `ID` VARCHAR(150) NOT NULL,
  `Usuario` VARCHAR(200) NOT NULL,
  `Login` VARCHAR(100) NOT NULL,
  `Senha` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  UNIQUE INDEX `Usuario_UNIQUE` (`Usuario` ASC),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Voto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voto` ;

CREATE TABLE IF NOT EXISTS `Voto` (
  `ID_Sessao` VARCHAR(150) NOT NULL,
  `ID_Politico` VARCHAR(150) NOT NULL,
  `Resposta` INT NULL,
  `Estado` VARCHAR(1) NOT NULL,
  INDEX `ID_Sessao_idx` (`ID_Sessao` ASC),
  INDEX `ID_Politico_idx` (`ID_Politico` ASC),
  CONSTRAINT `ID_Sessao`
    FOREIGN KEY (`ID_Sessao`)
    REFERENCES `Sessao` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Politico`
    FOREIGN KEY (`ID_Politico`)
    REFERENCES `Politico` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
