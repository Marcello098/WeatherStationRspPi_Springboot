create table measurements (id bigint generated by default as identity,
timestamp timestamp not null,
temperature_c float,
pressure_hpa float,
relative_humidity_percent float,
gps_latitude double,
gps_longitude double,
pm1_ugper3 integer,
pm2dot5_ugper3 integer,
pm10_ugper3 integer,
air_quality varchar(255),
primary key (id));

create table measurements (id  bigserial not null,
timestamp timestamp not null,
temperature_c float4,
pressure_hpa float4,
relative_humidity_percent float4,
gps_latitude float8,
gps_longitude float8,
pm1_ugper3 int4,
pm2dot5_ugper3 int4,
pm10_ugper3 int4,
air_quality varchar(255),
primary key (id));
