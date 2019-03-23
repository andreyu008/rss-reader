CREATE TABLE news.ref_import_rss
(
  num smallint,
  src character varying(50) NOT NULL, -- источник
  state character varying(50), -- Статус последней загрузки
  full_name character varying(200) NOT NULL, -- Наименование тайтла
  short_name character varying(20) NOT NULL, -- Короткое имя
  page character varying(100) NOT NULL, -- Адрес страницы
  CONSTRAINT ref_import_rss_pkey PRIMARY KEY (short_name)
);
COMMENT ON TABLE news.ref_import_rss
  IS 'Таблица параметров загрузки ленты';
COMMENT ON COLUMN news.ref_import_rss.num IS 'Номер в порядке автозугрзки';
COMMENT ON COLUMN news.ref_import_rss.src IS 'источник';
COMMENT ON COLUMN news.ref_import_rss.state IS 'Статус последней загрузки';
COMMENT ON COLUMN news.ref_import_rss.full_name IS 'Наименование ';
COMMENT ON COLUMN news.ref_import_rss.short_name IS 'Короткое имя';
COMMENT ON COLUMN news.ref_import_rss.page IS 'Адрес страницы';