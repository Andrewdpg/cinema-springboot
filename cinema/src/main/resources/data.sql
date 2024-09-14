INSERT INTO theater (theater_id, name) VALUES
                                           ('d290f1ee-6c54-4b01-90e6-d701748f0851', 'Cinepolis'),
                                           ('58af4e1a-6c54-4b01-90e6-d701748f0851', 'Cinemark');

INSERT INTO auditorium (auditorium_id, number, theater_theater_id) VALUES
                                                                       ('f5d5f1d0-6c54-4b01-90e6-d701748f0851', 1, 'd290f1ee-6c54-4b01-90e6-d701748f0851');

INSERT INTO movie (movie_id, title, director, runtime, release_date, image) VALUES
                                                                         ('1234abcd-6c54-4b01-90e6-d701748f0851', 'The Matrix', 'Wachowski', 136, '1999-03-31', 'https://i.blogs.es/8b8798/06-06-matrix/840_560.jpg'),
                                                                         ('6a5f043c-852e-4aeb-ba8d-50ab5fff3420', 'Inception', 'Christopher Nolan', 148, '2010-07-16', 'https://elcomercio.pe/resizer/MNtB6o92-pW0izK0_PXbSSUU6C0=/1200x800/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/EKQC727E3JGH3PZQO2IEAGAQOM.jpg');

INSERT INTO customer (email, fullname, phone) VALUES
                                                  ('john.doe@example.com', 'John Doe', '555-1234'),
                                                  ('jane.smith@example.com', 'Jane Smith', '555-5678');

INSERT INTO schedule (schedule_id, date, time, movie_movie_id, auditorium_id, price) VALUES
                                                                                  ('3759071a-444d-40fe-9a2d-ef8c04c57814', '2024-09-15', '18:30:00', '1234abcd-6c54-4b01-90e6-d701748f0851', 'f5d5f1d0-6c54-4b01-90e6-d701748f0851', 10),
                                                                                  ('3ce3b8c6-6e64-48b4-a9cd-0a325f18b76b', '2024-09-16', '21:00:00', '6a5f043c-852e-4aeb-ba8d-50ab5fff3420', 'f5d5f1d0-6c54-4b01-90e6-d701748f0851',15);

INSERT INTO seat (seat_id, row, col, auditorium_id) VALUES
                                                        ('8e199ac3-ae11-4902-a356-386abb33a93d', 'E', 1, 'f5d5f1d0-6c54-4b01-90e6-d701748f0851'),
                                                        ('e7979614-ec42-47d1-86f0-53d580992a52', 'E', 2, 'f5d5f1d0-6c54-4b01-90e6-d701748f0851');

-- Define las filas y columnas excluyendo las sillas específicas para cada grupo de filas
WITH seat_data AS (
    -- Filas A, B, C, D excluyen las columnas [1, 2, 3, 4, 17, 18]
    SELECT unnest(ARRAY['A', 'B', 'C', 'D', 'E']) AS row,
           col
    FROM generate_series(1, 20) AS col
    WHERE col NOT IN (1, 2, 3, 4, 15, 16)

    UNION ALL

    -- Filas E, F, G, H, I, J, K excluyen las columnas [3, 4, 17, 18]
    SELECT unnest(ARRAY['F', 'G', 'H', 'I', 'J', 'K']) AS row,
           col
    FROM generate_series(1, 20) AS col
    WHERE col NOT IN (3, 4, 15, 16)

    UNION ALL

    -- Filas L incluye todas las columnas (1-20)
    SELECT 'L' AS row,
           generate_series(1, 20) AS col
)
-- Inserta todas las sillas en el auditorio correspondiente
INSERT INTO seat (seat_id, row, col, auditorium_id)
SELECT md5(random()::text || clock_timestamp()::text)::uuid, row, col, 'f5d5f1d0-6c54-4b01-90e6-d701748f0851'
FROM seat_data;

INSERT INTO ticket (ticket_id, schedule_schedule_id, customer_email) VALUES
                                                                         ('539b8afa-ec2d-4d29-8376-fa121894101e', '3759071a-444d-40fe-9a2d-ef8c04c57814', 'john.doe@example.com'),
                                                                         ('15dfc7cd-2371-4991-9cd0-3cacacc7c50b', '3ce3b8c6-6e64-48b4-a9cd-0a325f18b76b', 'jane.smith@example.com');

INSERT INTO reservation (reservation_id, seat_seat_id, ticket_ticket_id) VALUES
                                                                             ('4caa48e6-3685-4327-a533-cdaf9ba46469', '8e199ac3-ae11-4902-a356-386abb33a93d', '539b8afa-ec2d-4d29-8376-fa121894101e'),
                                                                             ('15f4f808-68be-4bc7-8462-e44a60b4d118', 'e7979614-ec42-47d1-86f0-53d580992a52', '15dfc7cd-2371-4991-9cd0-3cacacc7c50b');
