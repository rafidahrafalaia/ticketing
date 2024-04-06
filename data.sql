
INSERT INTO event (name, description, organizer) VALUES
('Konser Dewa 19', 'Konser Dewa 19', 'Tria Musica'),
('Java Jazz Festival', 'Java Jazz Festival', 'Hard Rock Cafe'),
('Festival Budaya Betawi', 'musik, tari, dan kuliner khas Jakarta.', 'Dinas Pariwisata dan Ekonomi Kreatif DKI Jakarta'),
('Festival Kota Lama Semarang', 'Festival Kota Lama Semarang', 'Pemerintah Kota Semarang');

INSERT INTO event_schedule (time_event_start, time_sell_start, time_sell_end, event_id) VALUES
('2024-04-10 08:00:00', '00:01:00', '23:59:00', 1),
('2024-04-10 08:00:00', '00:01:00', '23:59:00', 1),
('2024-04-11 18:00:00', '00:01:00', '23:59:00', 1),
('2024-04-12 08:00:00', '00:01:00', '23:59:00', 1),
('2024-04-10 18:00:00', '00:01:00', '23:59:00', 2),
('2024-04-10 08:00:00', '00:01:00', '23:59:00', 2),
('2024-04-11 09:00:00', '09:00:00', '19:00:00', 2),
('2024-04-12 10:00:00', '10:00:00', '20:00:00', 2),
('2024-04-13 11:00:00', '11:00:00', '21:00:00', 3),
('2024-04-14 12:00:00', '12:00:00', '22:00:00', 3);

INSERT INTO ticket (code, status, event_schedule_id) VALUES
('FSX001', 'AVAILABLE', 1),
('FSX002', 'AVAILABLE', 1),
('FSX003', 'AVAILABLE', 1),
('GSX001', 'AVAILABLE', 2),
('GSX002', 'AVAILABLE', 2),
('GSX003', 'AVAILABLE', 2),
('JJA001', 'AVAILABLE', 3),
('JJA002', 'AVAILABLE', 3),
('JJA003', 'AVAILABLE', 3),
('HHA001', 'AVAILABLE', 1);