  alter table categorie_post
        drop
        foreign key FK2CD99AA849C833B6;

    alter table categorie_post
        drop
        foreign key FK2CD99AA87D03CECB;

    alter table comment
        drop
        foreign key FK38A5EE5FB2590B08;

    alter table post
        drop
        foreign key FK3498A08702C5A6;

    alter table post
        drop
        foreign key FK3498A0B25DB15E;

    alter table post_comment
        drop
        foreign key FK9E178DC0C35B5601;

    alter table post_comment
        drop
        foreign key FK9E178DC0388562DE;

    alter table post_tag
        drop
        foreign key FK2D24377B374D4C6C;

    alter table post_tag
        drop
        foreign key FK2D24377BB2590B08;

    alter table user_post
        drop
        foreign key FK143B0C947D03CECB;

    alter table user_post
        drop
        foreign key FK143B0C9447140EFE;

    drop table if exists categorie;

    drop table if exists categorie_post;

    drop table if exists comment;

    drop table if exists post;

    drop table if exists post_comment;

    drop table if exists post_tag;

    drop table if exists tag;

    drop table if exists user;

    drop table if exists user_post;

    create table categorie (
        id bigint not null auto_increment,
        name varchar(255),
        parentId integer,
        primary key (id)
    );

    create table categorie_post (
        categorie_id bigint not null,
        posts_id bigint not null,
        unique (posts_id)
    );

    create table comment (
        id bigint not null auto_increment,
        content varchar(255),
        date datetime,
        number integer not null,
        status bit not null,
        subscribe bit not null,
        usermail varchar(255),
        username varchar(255),
        usersite varchar(255),
        id_post bigint,
        primary key (id)
    );

    create table post (
        id bigint not null auto_increment,
        content text,
        lastchange datetime,
        nshow integer not null,
        status bit not null,
        title varchar(255),
        id_categorie bigint,
        id_user bigint,
        primary key (id)
    );

    create table post_comment (
        post_id bigint not null,
        comments_id bigint not null,
        unique (comments_id)
    );

    create table post_tag (
        id_post bigint not null,
        id_tag bigint not null
    );

    create table tag (
        id bigint not null auto_increment,
        tag varchar(255),
        primary key (id)
    );

    create table user (
        id bigint not null auto_increment,
        dtCreate datetime,
        role varchar(255),
        usermail varchar(255),
        username varchar(255),
        userpwd varchar(255),
        primary key (id)
    );

    create table user_post (
        user_id bigint not null,
        posts_id bigint not null,
        unique (posts_id)
    );

    alter table categorie_post
        add index FK2CD99AA849C833B6 (categorie_id),
        add constraint FK2CD99AA849C833B6
        foreign key (categorie_id)
        references categorie (id);

    alter table categorie_post
        add index FK2CD99AA87D03CECB (posts_id),
        add constraint FK2CD99AA87D03CECB
        foreign key (posts_id)
        references post (id);

    alter table comment
        add index FK38A5EE5FB2590B08 (id_post),
        add constraint FK38A5EE5FB2590B08
        foreign key (id_post)
        references post (id);

    alter table post
        add index FK3498A08702C5A6 (id_categorie),
        add constraint FK3498A08702C5A6
        foreign key (id_categorie)
        references categorie (id);

    alter table post
        add index FK3498A0B25DB15E (id_user),
        add constraint FK3498A0B25DB15E
        foreign key (id_user)
        references user (id);

    alter table post_comment
        add index FK9E178DC0C35B5601 (comments_id),
        add constraint FK9E178DC0C35B5601
        foreign key (comments_id)
        references comment (id);

    alter table post_comment
        add index FK9E178DC0388562DE (post_id),
        add constraint FK9E178DC0388562DE
        foreign key (post_id)
        references post (id);

    alter table post_tag
        add index FK2D24377B374D4C6C (id_tag),
        add constraint FK2D24377B374D4C6C
        foreign key (id_tag)
        references tag (id);

    alter table post_tag
        add index FK2D24377BB2590B08 (id_post),
        add constraint FK2D24377BB2590B08
        foreign key (id_post)
        references post (id);

    alter table user_post
        add index FK143B0C947D03CECB (posts_id),
        add constraint FK143B0C947D03CECB
        foreign key (posts_id)
        references post (id);

    alter table user_post
        add index FK143B0C9447140EFE (user_id),
        add constraint FK143B0C9447140EFE
        foreign key (user_id)
        references user (id);