CREATE TABLE IF NOT EXISTS content (
                                       reviewid TEXT PRIMARY KEY,
                                       content TEXT NOT NULL
);

INSERT INTO content (reviewid, content) VALUES
                                            ('r101', 'This album is a masterpiece of modern jazz.'),
                                            ('r102', 'Energetic and raw, but lacks depth.'),
                                            ('r103', 'A refreshing blend of genres and styles.');
