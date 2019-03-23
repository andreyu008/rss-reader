create table news.ref_rss_details
(
  id_rss_detail serial not null,
  title text NOT NULL,
  description text NOT NULL,
  link text,
  language text,
  image_title text,
  image_url text,
  image_link text,
  image_width text,
  image_height text,

  constraint pk_rss_details PRIMARY KEY (id_rss_detail)
);

comment on table news.ref_rss_details is 'Справочник для объектов лент';
comment on column news.ref_rss_details.id_rss_detail is 'ID RSS объекта';
comment on column news.ref_rss_details.title is 'Заголовок новостной записи';
comment on column news.ref_rss_details.description is 'Описание нвовстной записи';
comment on column news.ref_rss_details.link is 'Ссылка';
comment on column news.ref_rss_details.language is 'Язык';
comment on column news.ref_rss_details.image_title is 'Заголовок изображения';
comment on column news.ref_rss_details.image_url is 'Ссылка на изображение';
comment on column news.ref_rss_details.image_link is 'Ссылка на изображение';
comment on column news.ref_rss_details.image_width is 'Ширина изображения';
comment on column news.ref_rss_details.image_height is 'Высота изображения';


create table news.rss_items
(
  id_rss_items serial not null,
  id_rss_detail bigint,
  title text NOT NULL,
  description text NOT NULL,
  link text,
  constraint pk_rss_items PRIMARY KEY (id_rss_items),
  constraint fk_rss_items_rss_details FOREIGN KEY (id_rss_detail)
    references news.ref_rss_details(id_rss_detail)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE
);

comment on table news.rss_items is 'Справочник для объектов лент';
comment on column news.rss_items.id_rss_items is 'ID RSS объекта';
comment on column news.rss_items.id_rss_detail is 'ID для rss_detail';
comment on column news.rss_items.title is 'Заголовок новостной записи';
comment on column news.rss_items.description is 'Описание нвовстной записи';
comment on column news.rss_items.link is 'Ссылка';