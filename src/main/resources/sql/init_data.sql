INSERT INTO book_management_system.users(name, email, password, created_at, updated_at)
VALUES
    -- Admin
    ('admin', 'admin@abc.com', '{bcrypt}$2a$10$u/bLo9Q3EyCDSBMD5a37bOQA/OGLunuycxx4ePpxJlBQG/1vyJRxe', now(), now()),
    -- User
    ('userA', 'user1@abc.com', '{bcrypt}$2a$10$D/Kgk3YrykVchEuVwSGCeetVxOC9lRfbeqnp3x2iLM6K0VEUcla3O', now(), now())
;

INSERT INTO book_management_system.books(title, author, tag, publish_at, is_rented, created_at, updated_at)
VALUES
    ('Effective Java', 'Joshua Bloch', '["Java", "Best Practices", "Programming"]', '2018-01-06', false, now(), now()),
    ('Clean Code', 'Robert C. Martin', '["Software", "Coding", "Best Practices"]', '2008-08-01', false, now(), now()),
    ('The Pragmatic Programmer', 'Andrew Hunt', '["Programming", "Software Development"]', '1999-10-20', true, now(), now()),
    ('Design Patterns', 'Erich Gamma', '["Software Design", "OOP", "Patterns"]', '1994-10-21', false, now(), now()),
    ('Refactoring', 'Martin Fowler', '["Code Improvement", "Software Engineering"]', '1999-07-08', true, now(), now()),
    ('Spring in Action', 'Craig Walls', '["Spring", "Java", "Backend"]', '2018-06-23', false, now(), now()),
    ('Domain-Driven Design', 'Eric Evans', '["DDD", "Software Architecture"]', '2003-08-30', false, now(), now()),
    ('Head First Design Patterns', 'Eric Freeman', '["OOP", "Patterns", "Java"]', '2004-10-25', true, now(), now()),
    ('You Don’t Know JS', 'Kyle Simpson', '["JavaScript", "Frontend", "Programming"]', '2014-12-28', false, now(), now()),
    ('The Mythical Man-Month', 'Frederick P. Brooks Jr.', '["Software Project Management"]', '1975-10-01', false, now(), now())
;