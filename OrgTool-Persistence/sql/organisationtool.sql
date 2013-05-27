-- create database if not exists


drop table if exists PersoonType;
drop table if exists KlantHistory;
drop table if exists Klant;
drop table if exists PersoonHistory;
drop table if exists Persoon;


create table PersoonType
(
    ptype_id int primary key,
    ptype varchar(14) not null
);

insert into PersoonType
select 0, 'kandidaat' union all
select 1, 'medewerker' union all
select 2, 'freelancer' union all
select 3, 'contactpersoon';



create table Persoon
(
    id int primary key auto_increment,
    naam varchar(45) not null,
    voornaam varchar(45) not null,
    persoonType int(11),
    persoontype_id int not null references PersoonType(ptype_id),
    constraint ptype_constraint unique (id,persoontype_id)
);

create table PersoonHistory
(
    history_id int primary key auto_increment,
    persoon_id int not null,
    persoontype_id int not null,
    date_from date not null,
    date_to date,
    foreign key (persoon_id,persoontype_id) references Persoon (id,persoontype_id) on delete cascade on update cascade
);

create table Klant
(
    klant_id int primary key auto_increment,
    naam varchar(45) not null,
    contactpersoon_id int,
    foreign key (contactpersoon_id) references Persoon (id)
);

create table KlantHistory
(
    kh_id int primary key auto_increment,
    klant_id int not null,
    date_from date not null,
    date_to date,
    persoon_id int not null,
    foreign key (klant_id) references Klant(klant_id),
    foreign key (persoon_id) references Persoon(id)
);

create table KlantPersonen
(
  klant_id int not null,
  persoon_id int not null,
  primary key (klant_id,persoon_id)
);

