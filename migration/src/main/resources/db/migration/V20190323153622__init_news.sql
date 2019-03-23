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