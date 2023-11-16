create table genre
(
    id   int auto_increment
        primary key,
    name varchar(30) not null
);

create table movie
(
    id       int          not null
        primary key,
    title    varchar(128) not null,
    duration int          null comment '电影时长，以分钟为单位',
    released date         null comment '发行时间',
    rating   float        null comment '电影imdb评分',
    poster   varchar(255) null comment '海报api',
    plot     text         null,
    director varchar(50)  null,
    actors   varchar(255) null
);

create table genre_movie
(
    id    int auto_increment
        primary key,
    movie int null,
    genre int not null,
    constraint genre_movie_genre_id_fk
        foreign key (genre) references genre (id),
    constraint genre_movie_movie_id_fk
        foreign key (movie) references movie (id)
);

create table recommend
(
    movie int      not null,
    user  int      not null,
    date  datetime null,
    primary key (movie, user)
);

create table user
(
    id       int auto_increment
        primary key,
    account  varchar(30)  not null,
    name     varchar(20)  null,
    password varchar(255) not null,
    sex      bit          null
);

create table rating
(
    id      int auto_increment
        primary key,
    user    int      not null,
    movie   int      not null,
    time    datetime not null,
    comment text     null comment '电影评价',
    score   float    not null,
    agree   int      not null,
    constraint rating_movie_id_fk
        foreign key (movie) references movie (id),
    constraint rating_user_id_fk
        foreign key (user) references user (id)
);


