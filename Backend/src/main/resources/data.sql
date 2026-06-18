-- USERS (password is bcrypt for "123456")
INSERT INTO users (id, name, email, password, created_at, role) VALUES
(1, 'Madhusudan', 'madhu@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'ADMIN'),
(2, 'Ravi Kumar', 'ravi@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(3, 'Sneha Reddy', 'sneha@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'ADMIN'),
(4, 'Rahul Sharma', 'rahul@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(5, 'Priya Singh', 'priya@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(6, 'Ankit Verma', 'ankit@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(7, 'Deepika Das', 'deepika@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(8, 'Kiran Patel', 'kiran@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(9, 'Arjun Nair', 'arjun@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER'),
(10, 'Neha Gupta', 'neha@gmail.com', '$2a$12$tkrOSx6U/VNFxMvHRzJOyODWMFo4iq8aQVu4MBALXsHFnTyJlMArC', NOW(), 'USER');


-- QUESTIONS
INSERT INTO questions (id, title, description, created_at, user_id) VALUES
(1, 'What is HashMap?', 'Explain how HashMap works internally', NOW(), 1),
(2, 'Explain React Hooks', 'What are useState and useEffect?', NOW(), 2),
(3, 'What is JWT?', 'How JWT authentication works?', NOW(), 3),
(4, 'Difference between ArrayList and LinkedList', 'Explain differences', NOW(), 4),
(5, 'What is REST API?', 'Explain REST concepts', NOW(), 5),
(6, 'What is Microservices?', 'Explain microservices architecture', NOW(), 6),
(7, 'How CSS Flexbox works?', 'Explain flexbox layout', NOW(), 7),
(8, 'Explain OOPS concepts', 'Encapsulation, inheritance, etc.', NOW(), 8),
(9, 'What is Spring Boot?', 'Why Spring Boot is used?', NOW(), 9),
(10, 'What is database indexing?', 'Explain indexing benefits', NOW(), 10);

-- ANSWERS
INSERT INTO answers (id, content, vote_count, accepted, created_at, user_id, question_id) VALUES
(1, 'HashMap stores key-value pairs using hashing.', 5, false, NOW(), 2, 1),
(2, 'React hooks manage state in functional components.', 3, false, NOW(), 3, 2),
(3, 'JWT is used for secure authentication using tokens.', 4, false, NOW(), 4, 3),
(4, 'ArrayList uses dynamic array, LinkedList uses nodes.', 2, false, NOW(), 5, 4),
(5, 'REST APIs use HTTP methods like GET and POST.', 6, false, NOW(), 6, 5),
(6, 'Microservices split app into small services.', 3, false, NOW(), 7, 6),
(7, 'Flexbox aligns items in rows/columns.', 4, false, NOW(), 8, 7),
(8, 'OOPS includes encapsulation and inheritance.', 5, false, NOW(), 9, 8),
(9, 'Spring Boot simplifies backend development.', 7, false, NOW(), 10, 9),
(10, 'Indexing improves query performance.', 2, false, NOW(), 1, 10);


-- COMMENTS
INSERT INTO comments (id, content, created_at, user_id, answer_id) VALUES
(1, 'Great answer!', NOW(), 1, 1),
(2, 'Very helpful!', NOW(), 2, 2),
(3, 'Nice explanation!', NOW(), 3, 3),
(4, 'Good example', NOW(), 4, 4),
(5, 'Very clear!', NOW(), 5, 5),
(6, 'Helpful content', NOW(), 6, 6),
(7, 'Nice!', NOW(), 7, 7),
(8, 'Well explained', NOW(), 8, 8),
(9, 'Good job', NOW(), 9, 9),
(10, 'Understood well', NOW(), 10, 10);


-- CHAT ROOMS
INSERT INTO chat_room (id, name, description) VALUES
(1, 'Java Room', 'Discussion about Java'),
(2, 'React Room', 'Frontend discussions'),
(3, 'General Chat', 'General talk'),
(4, 'Backend Talk', 'Spring Boot and APIs discussion'),
(5, 'Frontend Talk', 'UI and React discussions');



-- CHAT MESSAGES
INSERT INTO chat_messages (id, message, created_at, user_id, room_id) VALUES
(1, 'Hello everyone!', NOW(), 1, 1),
(2, 'Anyone knows HashMap?', NOW(), 2, 1),
(3, 'React is awesome!', NOW(), 3, 2),
(4, 'Hooks are confusing', NOW(), 4, 2),
(5, 'Good morning', NOW(), 5, 3),
(6, 'How are you all?', NOW(), 6, 3),
(7, 'Any Java doubts?', NOW(), 7, 1),
(8, 'React help needed', NOW(), 8, 2),
(9, 'Let’s build project', NOW(), 9, 3),
(10, 'Backend ready', NOW(), 10, 3);

INSERT INTO chat_messages (id, message, created_at, user_id, room_id) VALUES
(11, 'Hey everyone!', NOW(), 1, 1),
(12, 'Hi bro 👋', NOW(), 2, 1),
(13, 'Can anyone explain HashMap?', NOW(), 3, 1),
(14, 'It uses hashing for fast lookup', NOW(), 2, 1),
(15, 'Time complexity is O(1) average', NOW(), 4, 1),
(16, 'What about collisions?', NOW(), 3, 1),
(17, 'They are handled using linked list or tree', NOW(), 5, 1),
(18, 'Got it 👍 thanks!', NOW(), 3, 1);

INSERT INTO chat_messages (id, message, created_at, user_id, room_id) VALUES
(19, 'React is confusing 😅', NOW(), 6, 2),
(20, 'Which part?', NOW(), 7, 2),
(21, 'Hooks mainly', NOW(), 6, 2),
(22, 'useState manages state', NOW(), 8, 2),
(23, 'useEffect handles lifecycle', NOW(), 9, 2),
(24, 'Oh okay makes sense now!', NOW(), 6, 2),
(25, 'Practice small components 👍', NOW(), 7, 2);

INSERT INTO chat_messages (id, message, created_at, user_id, room_id) VALUES
(26, 'Good morning team!', NOW(), 1, 3),
(27, 'Morning ☀️', NOW(), 2, 3),
(28, 'Anyone working on project?', NOW(), 3, 3),
(29, 'Yes backend almost done', NOW(), 4, 3),
(30, 'Frontend ready too', NOW(), 5, 3),
(31, 'Great teamwork 💯', NOW(), 6, 3);

INSERT INTO chat_messages (id, message, created_at, user_id, room_id) VALUES
(32, 'How to secure APIs?', NOW(), 7, 4),
(33, 'Use JWT authentication', NOW(), 8, 4),
(34, 'Stateless and scalable', NOW(), 9, 4),
(35, 'Where to store token?', NOW(), 10, 4),
(36, 'Mostly in Authorization header', NOW(), 8, 4),
(37, 'Got it thanks!', NOW(), 7, 4);

INSERT INTO chat_messages (id, message, created_at, user_id, room_id) VALUES
(38, 'How to center div in CSS?', NOW(), 2, 5),
(39, 'Use flexbox', NOW(), 3, 5),
(40, 'justify-content center', NOW(), 4, 5),
(41, 'align-items center', NOW(), 5, 5),
(42, 'Wow that worked 😄', NOW(), 2, 5),
(43, 'Debugging = detective work 🕵️‍♂️', NOW(), 1, 3);


-- VOTES (unique user_id + answer_id)
INSERT INTO votes (id, type, user_id, answer_id) VALUES
(1, 'UPVOTE', 1, 1),
(2, 'UPVOTE', 2, 2),
(3, 'DOWNVOTE', 3, 3),
(4, 'UPVOTE', 4, 4),
(5, 'UPVOTE', 5, 5),
(6, 'DOWNVOTE', 6, 6),
(7, 'UPVOTE', 7, 7),
(8, 'UPVOTE', 8, 8),
(9, 'DOWNVOTE', 9, 9),
(10, 'UPVOTE', 10, 10);


/*
 select * from ai_response;
+----+----------------------------------------------------------------------------------------------------------------+-------------------------+
| id | answer                                                                                                         | keyword                 |
+----+----------------------------------------------------------------------------------------------------------------+-------------------------+
|  1 | Java is object oriented, platform independent, secure, and supports multithreading.                            | java features           |
|  2 | Spring Boot simplifies Java backend development with auto configuration and embedded servers.                  | spring boot             |
|  3 | React hooks like useState and useEffect allow functional components to manage state and lifecycle.             | react hooks             |
|  4 | JWT is a stateless authentication method where a token is used to verify users.                                | jwt authentication      |
|  5 | REST APIs allow communication between frontend and backend using HTTP methods like GET, POST, PUT, DELETE.     | rest api                |
|  6 | Joins combine rows from two or more tables based on related columns like INNER JOIN or LEFT JOIN.              | mysql joins             |
|  7 | OOPS concepts include encapsulation, inheritance, polymorphism, and abstraction.                               | oops concepts           |
|  8 | Flexbox is a layout system that helps align and distribute space between items in a container.                 | css flexbox             |
|  9 | Promises in JavaScript handle asynchronous operations with then, catch, and async await.                       | javascript promises     |
| 10 | Microservices architecture divides applications into small independent services.                               | microservices           |
| 11 | A chat system allows real time communication between users through messages in chat rooms.                     | chat system             |
| 12 | A question answer platform lets users post questions and receive answers from other users.                     | question answer website |
| 13 | Voting helps users upvote or downvote answers based on usefulness and quality.                                 | vote system             |
| 14 | Accepting an answer marks it as the best solution to a question.                                               | accept answer           |
| 15 | Role based access control restricts system access based on user roles like admin or user.                      | role based access       |
| 16 | Java is a high level programming language used for backend applications.                                       | java                    |
| 17 | React is a JavaScript library for building user interfaces.                                                    | react                   |
| 18 | Spring framework is used to build enterprise Java applications.                                                | spring                  |
| 19 | APIs allow different systems to communicate with each other.                                                   | api                     |
| 20 | A database stores structured data that can be retrieved and managed easily.                                    | database                |
| 21 | A 403 error in JWT usually happens when the token is invalid, expired, or missing in the Authorization header. | jwt token               |
| 22 | 403 Forbidden means your request is understood but not allowed. Check authentication and user roles.           | 403 forbidden           |
| 23 | JWT errors occur when the token is expired, malformed, or signature is invalid.                                | jwt error               |
| 24 | If your token is not working, verify it is sent in the Authorization header with Bearer prefix.                | token not working       |
| 25 | Authorization header must be in format: Bearer <token>. Missing or incorrect format leads to 403 errors.       | authorization header    |
| 26 | JWT is used for authentication. A 403 error means access is denied due to invalid or missing token.            | jwt                     |
| 27 | Forbidden error (403) usually means lack of permission or invalid authentication token.                        | forbidden error         |
| 28 | Authentication issues may be due to expired tokens, incorrect headers, or invalid credentials.                 | auth issue              |
+----+----------------------------------------------------------------------------------------------------------------+-------------------------+
28 rows in set (0.00 sec)*/