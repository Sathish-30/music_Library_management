Create table users (userId SERIAL primary key , userName varchar(50) , emailId varchar(50) , password varchar(40) , playListId SERIAL);
drop table users;


Create table publishers (publisherId SERIAL primary key , publisherName varchar(50) , emailId varchar(50) , password varchar(40));
drop table publishers;

Create table songs (songId SERIAL primary key , songName varchar(60) , publisherId integer , genre varchar(30) , language varchar(30) , releaseDate date);
drop table songs;

Create table playlists (playlistId integer not null , songId integer unique not null , songName varchar(60) not null );
drop table playlists;