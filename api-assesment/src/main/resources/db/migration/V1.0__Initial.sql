CREATE TABLE job_titles (
    job_id INT NOT NULL auto_increment primary key,
    title varchar(100) NOT NULL,
    created_at timestamp not null default now(),
    deleted_at timestamp
);

INSERT INTO job_titles (job_id, title) VALUES
    (null, 'Architect'),
    (null, 'Software engineer'),
    (null, 'Quantity surveyor'),
    (null, 'Accountant');
