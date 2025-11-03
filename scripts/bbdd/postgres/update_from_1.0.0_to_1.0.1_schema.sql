ALTER TABLE public.rfh_lloc ALTER COLUMN codilloc DROP NOT NULL;
ALTER TABLE public.rfh_lloc ADD CONSTRAINT rfh_lloc_codillocpropi_uk UNIQUE (codillocpropi);
